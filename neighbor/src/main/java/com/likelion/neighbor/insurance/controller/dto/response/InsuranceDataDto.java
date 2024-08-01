package com.likelion.neighbor.insurance.controller.dto.response;

import java.util.List;

public record InsuranceDataDto(
	List<?> resActualLossPaymentList,
	List<?> resSavingsContractList,
	List<?> resCarContractList,
	List<?> resPropertyContractList,
	List<?> resActualLossStatisticsList,
	List<ResActualLossContract> resActualLossContractList,
	List<?> resFlatRateStatisticsList,
	List<?> resFlatRateContractList) {
}
