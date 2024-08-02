package com.likelion.neighbor.insurance.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.likelion.neighbor.global.exception.model.BaseResponse;
import com.likelion.neighbor.global.exception.model.Error;
import com.likelion.neighbor.global.exception.model.Success;
import com.likelion.neighbor.insurance.service.DentalInsuranceService;
import com.likelion.neighbor.insurance.service.InsuranceDamoaService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/insurance")
@Slf4j
public class InsuranceController {
	private final InsuranceDamoaService insuranceDamoaService;

	// @PostMapping("/contract")
	// public BaseResponse<?> getMyInsuranceContract(@AuthenticationPrincipal String userId, @RequestParam("thirdPartyToken")String token) {
	// 	try	{
	// 		return BaseResponse.success(Success.GET_INSURANCE_SUCCESS, insuranceDamoaService.saveContractResult(userId, token));
	// 	}catch (Exception e){
	// 		log.info(e.getMessage());
	// 		return BaseResponse.error(Error.INTERNAL_SERVER_ERROR, "서버에 문의하세요.");
	// 	}
	// }


	@PostMapping("/contract/token")
	public BaseResponse<?> publishDamoaAccessToken(){
		return BaseResponse.success(Success.INSURANCE_TOKEN_GENERATED_SUCCESS, insuranceDamoaService.publishAccessToken());
	}

}
