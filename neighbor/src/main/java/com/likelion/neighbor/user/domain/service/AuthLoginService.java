package com.likelion.neighbor.user.domain.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.likelion.neighbor.global.exception.NotFoundException;
import com.likelion.neighbor.global.exception.model.BaseResponse;
import com.likelion.neighbor.global.exception.model.Error;
import com.likelion.neighbor.global.exception.model.Success;
import com.likelion.neighbor.global.jwt.TokenProvider;
import com.likelion.neighbor.insurance.controller.dto.request.InsuranceRequestDto;
import com.likelion.neighbor.insurance.service.InsuranceDamoaService;
import com.likelion.neighbor.user.domain.Role;
import com.likelion.neighbor.user.domain.Status;
import com.likelion.neighbor.user.domain.User;
import com.likelion.neighbor.user.domain.controller.dto.request.DamoaSignUpDto;
import com.likelion.neighbor.user.domain.controller.dto.request.SignUpRequestDto;
import com.likelion.neighbor.user.domain.controller.dto.request.TwoWayRequestDto;
import com.likelion.neighbor.user.domain.controller.dto.response.NeedToSecondaryDto;
import com.likelion.neighbor.user.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthLoginService {


	private final String SIGN_UP_URL = "https://development.codef.io/v1/kr/insurance/0001/credit4u/register";

	private final UserRepository userRepository;
	private final InsuranceDamoaService insuranceDamoaService;
	private final TokenProvider tokenProvider;
	private final PasswordEncoder passwordEncoder;



	@Transactional
	public BaseResponse<?> signUp(String token, DamoaSignUpDto signUpRequestDto) throws UnsupportedEncodingException, JsonProcessingException {

		 NeedToSecondaryDto signUpSuccess = signUpForDamoaService(token, signUpRequestDto);
		 if (signUpSuccess.result().code().equals("CF-03002")){
			 return BaseResponse.success(Success.GET_INSURANCE_SUCCESS, signUpSuccess.data());
		 }
		 if (signUpSuccess!=null){ // 회원가입 2차인증을 이미 완료하고 요청하는 경우.
			 User user = createUser(signUpRequestDto);
			 return BaseResponse.success(Success.MEMBER_SAVE_SUCCESS,tokenProvider.createToken(user));
		 }
		 return BaseResponse.error(Error.INTERNAL_SERVER_ERROR, "회원가입 도중 예기치 못한 에러가 발생.");
	}
	@Transactional
	public BaseResponse<?> twoWaySignUp(String token, DamoaSignUpDto twoWayRequestDto) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();

		Map<String, Object> encryptedPasswordAndIdentityAndPrivateKeyByRSA = getEncryptedData(twoWayRequestDto);
		if (encryptedPasswordAndIdentityAndPrivateKeyByRSA.isEmpty()){
			return null;
		}
		SignUpRequestDto encryptSignUpDto = (SignUpRequestDto)createSignUpRequestDto(encryptedPasswordAndIdentityAndPrivateKeyByRSA, twoWayRequestDto, Status.TWO_WAY);

		String encodedData = encodeRequestBody(encryptSignUpDto);

		// POST 요청 전송
		ResponseEntity<String> response = sendSignUpRequest(token,encodedData);
		String decodedResponse = URLDecoder.decode(response.getBody(), "UTF-8");

		NeedToSecondaryDto jsonNode = objectMapper.readValue(decodedResponse, NeedToSecondaryDto.class);
		System.out.println(jsonNode);
		if (jsonNode.result().code().equals("CF-01004")){
			return BaseResponse.error(Error.REQUEST_TIME_OUT_ERROR, Error.REQUEST_TIME_OUT_ERROR.getMessage());
		}
		if(jsonNode.result().code().equals("CF-12069")){
			User user = userRepository.findByEmail(twoWayRequestDto.email()).orElse(createUser(twoWayRequestDto)); // 유저가 우리 서비스에 없으면 새로 만듬.
			insuranceDamoaService.saveContractResult((InsuranceRequestDto)createSignUpRequestDto(encryptedPasswordAndIdentityAndPrivateKeyByRSA, twoWayRequestDto, Status.CONTRACT_SAVE),user,token); //배포하면 없애기
			return BaseResponse.success(Success.EXIST_USER_LOGIN, tokenProvider.createToken(user));
		}
		User user = createUser(twoWayRequestDto);
		insuranceDamoaService.saveContractResult((InsuranceRequestDto)createSignUpRequestDto(encryptedPasswordAndIdentityAndPrivateKeyByRSA, twoWayRequestDto, Status.CONTRACT_SAVE),user,token);
		return BaseResponse.success(Success.MEMBER_SAVE_SUCCESS, tokenProvider.createToken(user));
	}

	public NeedToSecondaryDto signUpForDamoaService(String token, DamoaSignUpDto damoaSignUpDto) throws UnsupportedEncodingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();

		Map<String, Object> encryptedPasswordAndIdentityAndPrivateKeyByRSA = getEncryptedData(damoaSignUpDto);
		if (encryptedPasswordAndIdentityAndPrivateKeyByRSA.isEmpty()){
			return null;
		}
		SignUpRequestDto encryptSignUpDto = (SignUpRequestDto)createSignUpRequestDto(encryptedPasswordAndIdentityAndPrivateKeyByRSA, damoaSignUpDto, Status.FIRST_REQUEST);

		String encodedData = encodeRequestBody(encryptSignUpDto);
		// POST 요청 전송
		ResponseEntity<String> response = sendSignUpRequest(token, encodedData) ;

		String decodedResponse = URLDecoder.decode(response.getBody(), "UTF-8");

		NeedToSecondaryDto jsonNode = objectMapper.readValue(decodedResponse, NeedToSecondaryDto.class);
		System.out.println(jsonNode);
		if (jsonNode.data().continue2Way()){
			return jsonNode;
		}
		return null;

	}
	private Object createSignUpRequestDto(Map<String, Object> encryptedData, DamoaSignUpDto damoaSignUpDto, Status status) {
		if (status.equals(Status.CONTRACT_SAVE)){
			InsuranceRequestDto insuranceRequestDto = InsuranceRequestDto.builder()
				.organization("0001")
				.id(damoaSignUpDto.id())
				.password((String)encryptedData.get("encryptedPassword"))
				.identity((String)encryptedData.get("identity"))
				.type("0")
				.userName(damoaSignUpDto.userName())
				.phoneNo(damoaSignUpDto.phoneNo())
				.birthDate(damoaSignUpDto.birthDate())
				.telecom(damoaSignUpDto.telecom())
				.build();
			return insuranceRequestDto;
		}
		SignUpRequestDto.SignUpRequestDtoBuilder builder = SignUpRequestDto.builder()
			.organization("0001")
			.id(damoaSignUpDto.id())
			.password((String) encryptedData.get("encryptedPassword"))
			.type("0") // 0이면 sms인증, pass인증.
			.userName(damoaSignUpDto.userName())
			.identity((String) encryptedData.get("identity"))
			.birthDate(damoaSignUpDto.birthDate())
			.identityEncYn("Y")
			.phoneNo(damoaSignUpDto.phoneNo())
			.telecom(damoaSignUpDto.telecom());

		switch (status) {
			case TWO_WAY -> {
				builder.is2Way(true)
					.email(damoaSignUpDto.email())
					.smsAuthNo(damoaSignUpDto.smsAuthNo())
					.twoWayInfo(damoaSignUpDto.twoWayInfo());
			}
			case FIRST_REQUEST -> {
				// 첫 번째 요청에 대한 추가 설정이 필요하면 여기에 추가
							builder
								.email(damoaSignUpDto.email());

			}
		}
		return builder.build();
	}

	private String encodeRequestBody(SignUpRequestDto signUpRequestDto) {
		try {
			String jsonRequestBody = new ObjectMapper().writeValueAsString(signUpRequestDto);
			return URLEncoder.encode(jsonRequestBody, "UTF-8");
		} catch (Exception e) {
			throw new RuntimeException("Failed to convert DTO to JSON", e);
		}
	}

	private Map<String, Object> getEncryptedData(DamoaSignUpDto twoWayRequestDto) {
		try {
			return insuranceDamoaService.getEncryptedPasswordByRSA(twoWayRequestDto.password(), twoWayRequestDto.identity());
		} catch (Exception e) {
			log.error("Error while encrypting data: {}", e.getMessage());
			return null;
		}
	}

	private ResponseEntity<String> sendSignUpRequest(String token, String encodedRequestBody) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/x-www-form-urlencoded");
		headers.set("Authorization", "Bearer " + token);
		HttpEntity<String> entity = new HttpEntity<>(encodedRequestBody, headers);

		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.exchange(SIGN_UP_URL, HttpMethod.POST, entity, String.class);
	}

	private User createUser(DamoaSignUpDto signUpRequestDto){
		User user = User.builder()
			.name(signUpRequestDto.userName())
			.email(signUpRequestDto.email())
			.signUpId(signUpRequestDto.id())
			.password(passwordEncoder.encode(signUpRequestDto.password()))
			.birthDate(passwordEncoder.encode(signUpRequestDto.birthDate()))
			.identity(passwordEncoder.encode(signUpRequestDto.identity()))
			.telecom(signUpRequestDto.telecom())
			.phoneNo(signUpRequestDto.phoneNo())
			.role(Role.ROLE_USER)
			.build();
		userRepository.save(user);
		return user;
	}


}