package com.likelion.neighbor.user.domain.controller.dto.response;

public record SecondaryDataDto(
	Boolean continue2Way,
	String method,
	int jobIndex,
	int threadIndex,
	String jti,
	long twoWayTimestamp,
	ExtraInfo extraInfo

	) {
}
