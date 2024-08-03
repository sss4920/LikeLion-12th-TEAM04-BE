package com.likelion.neighbor.contract.application.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.likelion.neighbor.contract.CompanyCode;
import com.likelion.neighbor.contract.domain.ContractInformation;

import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ContractResponseDto(
	String resCompanyNm,
	String resCompanyCode,

	String insuranceNm,
	String resHomePage,
	boolean isDentalInsurance
) {
	public static ContractResponseDto from(ContractInformation contractInformation){
		return ContractResponseDto.builder()
			.resCompanyNm(contractInformation.getResCompanyNm())
			.resCompanyCode(CompanyCode.ifIsSameReturnCode(contractInformation.getResCompanyNm()))
			.insuranceNm(contractInformation.getResInsuranceName())
			.resHomePage(contractInformation.getResHomePage())
			.isDentalInsurance(contractInformation.isDentalInsurance())
			.build();
	}
	public static ContractResponseDto makeSimleResponse(ContractInformation contractInformation){
		return ContractResponseDto.builder()
			.resCompanyNm(contractInformation.getResCompanyNm())
			.insuranceNm(contractInformation.getResInsuranceName())
			.build();
	}
}
