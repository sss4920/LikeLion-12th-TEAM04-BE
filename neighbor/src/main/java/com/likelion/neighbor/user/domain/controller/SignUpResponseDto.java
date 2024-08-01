package com.likelion.neighbor.user.domain.controller;

public record SignUpResponseDto(
	String resRegistrationStatus,
	String resResultDesc,
	String resLoginId,
	String resEmail
) {
}
