package com.likelion.neighbor.user.domain.controller.dto.response;

public record ExtraInfo(
	String reqSecureNo,
	String reqSecureNoRefresh,
	String reqSMSAuthNo,
	String commSimpleAuth,
	String reqUserId,
	String reqUserPass,
	String reqEmail,
	String errorMessage,
	String reqEmailAuthNo) {
}
