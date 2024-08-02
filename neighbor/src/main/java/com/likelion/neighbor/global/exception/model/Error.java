package com.likelion.neighbor.global.exception.model;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Error {



	/**
	 * 404 NOT FOUND
	 */

	MEMBERS_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "해당 유저가 없습니다."),

	/**
	 * 400 BAD REQUEST
	 */
	VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "유효성 검사에 맞지 않습니다."),
	PARAMETER_NOT_VALID_ERROR(HttpStatus.BAD_REQUEST, "파라미터가 적절치 않습니다."),
	EXTENSION_NOT_VALID_ERROR(HttpStatus.BAD_REQUEST, "지원하지 않는 확장자입니다."),

	/**
	 * 403 FORBIDDEN
	 */
	FORBIDDEN_ERROR(HttpStatus.FORBIDDEN, "해당 리소스에 접근할 권한이 없습니다."),

	/**
	 * 408 REQUEST TIME OUT
	 */
	REQUEST_TIME_OUT_ERROR(HttpStatus.REQUEST_TIMEOUT, "요청에 대해 타임아웃이 발생했습니다."),

	/**
	 * 500 INTERNAL SERVER ERROR
	 */
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 서버 에러.");

	private final HttpStatus httpStatus;
	private final String message;

	public int getErrorCode() {
		return httpStatus.value();
	}
}
