package com.likelion.neighbor.insurance.controller.dto.response;

public record Coverage( String resNumber,
						String resType,
						String resCoverageName,
						String commStartDate,
						String commEndDate,
						String resCoverageAmount,
						String resCoverageStatus
) {
}

