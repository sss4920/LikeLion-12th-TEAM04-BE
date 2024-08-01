package com.likelion.neighbor.global.exception.model;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Success {


	/**
	 * 200 OK
	 */
	GET_INSURANCE_SUCCESS(HttpStatus.OK, "내가 계약한 보험 찾기 성공"),
	POST_UPDATE_SUCCESS(HttpStatus.OK, "게시물 수정 성공"),
	MEMBER_UPDATE_SUCCESS(HttpStatus.OK, "유저 수정 성공"),
	POST_DELETE_SUCCESS(HttpStatus.OK, "글 삭제 성공"),
	MEMBER_DELETE_SUCCESS(HttpStatus.OK, "글 삭제 성공"),

	/**
	 * 201 CREATED
	 */
	POST_SAVE_SUCCESS(HttpStatus.CREATED, "게시물 생성 성공"),
	MEMBER_SAVE_SUCCESS(HttpStatus.CREATED, "멤버 생성 성공"),
	INSURANCE_TOKEN_GENERATED_SUCCESS(HttpStatus.CREATED, "토큰 생성 성공"),

	/**
	 * 202 ACCEPTED
	 */
	SIGN_UP_TWO_WAY_NEED(HttpStatus.ACCEPTED, "2차 추가인증이 요구됩니다.");

	private final HttpStatus httpStatus;
	private final String message;
	public int getHttpStatusCode(){
		return httpStatus.value();
	}
}
