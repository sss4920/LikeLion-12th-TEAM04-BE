package com.likelion.neighbor.user.domain.controller.dto.request;

import lombok.Builder;

@Builder
public record DamoaSignUpDto(
	String userName,
	String identity,
	String birthDate,
	String telecom,
	String phoneNo,
	String id,
	String password,
	String email,
	String smsAuthNo,
	Boolean is2Way,
	TwoWayInfo twoWayInfo
){

}
