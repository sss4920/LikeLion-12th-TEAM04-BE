package com.likelion.neighbor.insurance.service;

import java.net.URLDecoder;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.config.authentication.PasswordEncoderParser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.likelion.neighbor.contract.domain.ContractInformation;
import com.likelion.neighbor.contract.domain.repository.ContractInformationRepository;
import com.fasterxml.jackson.databind.JsonNode;

import com.likelion.neighbor.insurance.controller.dto.request.InsuranceRequestDto;
import com.likelion.neighbor.insurance.controller.dto.response.ContractBaseResponse;
import com.likelion.neighbor.insurance.controller.dto.response.ResActualLossContract;
import com.likelion.neighbor.user.domain.User;
import com.likelion.neighbor.user.domain.controller.dto.request.DamoaSignUpDto;
import com.likelion.neighbor.user.domain.controller.dto.request.SignUpRequestDto;

import com.likelion.neighbor.user.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InsuranceDamoaService {

	@Value("${api.client-id}")
	private String clientId;

	@Value("${api.client-secret}")
	private String clientSecret;


	@Value("${api.client-public}")
	private String clientPublic;

	private final String CONTRACT_URL = "https://development.codef.io/v1/kr/insurance/0001/credit4u/contract-info";

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	private final ContractInformationRepository contractInformationRepository;

//TODO: 암호화가져오는 부분 bcrypt하기전 회원가입하면 비동기로 돌리게끔 fix.
	@Transactional
	public void saveContractResult(InsuranceRequestDto damoaSignUpDto,User user, String token) throws Exception {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(CONTRACT_URL);


		RestTemplate restTemplate = new RestTemplate();
		ObjectMapper objectMapper = new ObjectMapper();

		// Map<String, Object> res = getEncryptedPasswordByRSA(damoaSignUpDto.password(), damoaSignUpDto.identity());
		// InsuranceRequestDto insuranceRequestDto = InsuranceRequestDto.builder()
		// 	.organization("0001")
		// 	.id(user.getSignUpId())
		// 	.password((String)res.get("encryptedPassword"))
		// 	.identity((String)res.get("identity"))
		// 	.type("0")
		// 	.userName(user.getName())
		// 	.phoneNo(user.getPhoneNo())
		// 	.birthDate(user.getBirthDate())
		// 	.telecom(user.getTelecom())
		// 	.build();


		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/x-www-form-urlencoded");
		headers.set("Authorization", "Bearer "+token);

		String jsonRequestBody;
		try {
			jsonRequestBody = objectMapper.writeValueAsString(damoaSignUpDto);

		} catch (Exception e) {
			throw new RuntimeException("Failed to convert DTO to JSON", e);
		}
		HttpEntity<String> entity = new HttpEntity<>(jsonRequestBody, headers);

		// POST 요청 전송
		ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, String.class);
		String decodedResponse = URLDecoder.decode(response.getBody(), "UTF-8");

		// JSON 파싱
		ContractBaseResponse jsonNode = objectMapper.readValue(decodedResponse, ContractBaseResponse.class);
		List<ResActualLossContract> resActualLossContractList = jsonNode.data().resActualLossContractList();


		// 결과 출력
		System.out.println("Decoded Response: " + decodedResponse);
		System.out.println("JSON Result: " + resActualLossContractList.toString());

		//TODO: 결과 저장하는 엔티티 만들어서 저장하기.
		List<ContractInformation> contractInformations =  resActualLossContractList.stream().map(
			(ResActualLossContract i) -> ContractInformation.builder()
				.resCompanyNm(i.resCompanyNm())
				.resInsuranceName(i.resInsuranceName())
				.resInsuredPerson(i.resInsuredPerson())
				.resHomePage(i.resHomePage())
				.resPhoneNo(i.resPhoneNo())
				.user(user)
				.isDentalInsurance(i.resInsuranceName().contains("치아"))
				.build()
				).toList();
		contractInformationRepository.saveAll(contractInformations);
	}

	public Map<String,Object> getEncryptedPasswordByRSA(String password, String identity) throws Exception {
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");

		KeyPair keyPair = keyPairGen.generateKeyPair();
		PrivateKey privateKey = keyPair.getPrivate();

		byte[] publicBytes = Base64.getDecoder().decode(clientPublic);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey pubKey = keyFactory.generatePublic(keySpec);

		// Encrypt the password
		String encryptedPassword = encrypt(password,  pubKey);
		String encryptedIdentity = encrypt(identity, pubKey);
		System.out.println("Encrypted Password: " + encryptedPassword);
		Map<String, Object> res = new HashMap<>();
		res.put("privateKey", privateKey);
		res.put("encryptedPassword", encryptedPassword);
		res.put("identity", encryptedIdentity);
		return res;

	}

	public String encrypt(String data, PublicKey publicKey) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] encryptedBytes = cipher.doFinal(data.getBytes("UTF-8"));
		return Base64.getEncoder().encodeToString(encryptedBytes);
	}

	//보험 다보여 서드파티 토큰 발급
	@Transactional
	public HashMap<String, Object> publishAccessToken() {
		String url = "https://oauth.codef.io/oauth/token";
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
			.queryParam("grant_type", "client_credentials")
			.queryParam("scope", "read");

		RestTemplate restTemplate = new RestTemplate();

		// 클라이언트 아이디, 시크릿코드 Base64 인코딩
		String auth = clientId + ":" + clientSecret;
		byte[] authEncBytes = Base64.getEncoder().encode(auth.getBytes());
		String authStringEnc = new String(authEncBytes);
		String authHeader = "Basic " + authStringEnc;

		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/x-www-form-urlencoded");
		headers.set("Authorization", authHeader);

		HttpEntity<String> entity = new HttpEntity<>(null, headers);

		ResponseEntity<Map> response = restTemplate.exchange(
			builder.toUriString(),
			HttpMethod.POST,
			entity,
			Map.class
		);

		if (response.getStatusCode() == HttpStatus.OK) {
			return new HashMap<>(response.getBody());
		} else {
			throw new RuntimeException("Failed to get access token: " + response.getStatusCode());
		}
	}


}
