package com.likelion.neighbor.user.domain.controller.dto.request;

public record TwoWayInfo(
	int jobIndex,
	int threadIndex,
	String jti,
	long twoWayTimestamp
) {
}
