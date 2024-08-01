package com.likelion.neighbor.user.domain.controller.dto.request;

public record TwoWayRequestDto(
	String smsAuthNo,
	Boolean is2Way,
	TwoWayInfo twoWayInfo
) {
}
