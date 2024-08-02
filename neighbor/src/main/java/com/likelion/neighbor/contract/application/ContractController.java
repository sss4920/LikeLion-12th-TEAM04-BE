package com.likelion.neighbor.contract.application;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.likelion.neighbor.contract.application.response.ContractResponseDto;
import com.likelion.neighbor.contract.domain.ContractInformation;
import com.likelion.neighbor.contract.service.ContractService;
import com.likelion.neighbor.global.exception.model.BaseResponse;
import com.likelion.neighbor.global.exception.model.Success;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/contract")
@RequiredArgsConstructor
public class ContractController {

	private final ContractService contractService;

	@GetMapping
	public BaseResponse<?> getContractsByUser(@AuthenticationPrincipal String userId) {
		List<ContractResponseDto> contractInformationList = contractService.getContractInformationList(userId);;
		return BaseResponse.success(Success.GET_INSURANCE_SUCCESS, contractInformationList);
	}
}
