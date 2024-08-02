package com.likelion.neighbor.contract.application.response;

import com.likelion.neighbor.contract.CompanyCode;
import com.likelion.neighbor.contract.domain.ContractInformation;

import lombok.Builder;

@Builder
public record ContractResponseDto(
	String resCompanyNm,
	String resCompanyCode,
	String resHomePage,
	boolean isDentalInsurance
) {
	public static ContractResponseDto from(ContractInformation contractInformation){
		return ContractResponseDto.builder()
			.resCompanyNm(contractInformation.getResCompanyNm())
			.resCompanyCode(CompanyCode.ifIsSameReturnCode(contractInformation.getResCompanyNm()))
			.resHomePage(contractInformation.getResHomePage())
			.isDentalInsurance(contractInformation.isDentalInsurance())
			.build();
	}
}
