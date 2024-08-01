package com.likelion.neighbor.insurance.controller.dto.response;

import java.util.List;

public record ResActualLossContract(
	String resNumber,
	String resCompanyNm,
	String resCompanyNmCode,
	String resPolicyNumber,
	String resPolicyNumberHid,
	String resInsuranceName,String resContractStatus,
	String resPhoneNo,
	String resHomePage,
	String resInsuredPerson,
	List<Coverage> resCoverageLists
) {
}
