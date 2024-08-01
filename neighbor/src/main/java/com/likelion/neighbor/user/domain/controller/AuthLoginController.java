package com.likelion.neighbor.user.domain.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.likelion.neighbor.global.dto.Token;
import com.likelion.neighbor.global.exception.model.BaseResponse;
import com.likelion.neighbor.user.domain.controller.dto.request.DamoaSignUpDto;
import com.likelion.neighbor.user.domain.controller.dto.request.SignUpRequestDto;
import com.likelion.neighbor.user.domain.controller.dto.request.TwoWayRequestDto;
import com.likelion.neighbor.user.domain.service.AuthLoginService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthLoginController {
	private final AuthLoginService authLoginService;

	// @GetMapping("/code/{registrationId}")
	// public void googleLogin(@RequestParam String code, @PathVariable String registrationId){
	// 	authLoginService.socialLogin(code, registrationId);
	// }
	// @GetMapping("/code/google")
	// public Token googleCallback(@RequestParam(name = "code") String code){
	// 	String googleAccessToken = authLoginService.getGoogleAccessToken(code);
	// 	return loginOrSignup(googleAccessToken); // 인가코드를 통해 로그인이나 회원가입.
	// }

	@PostMapping("/sign-up")
	public BaseResponse<?> signup(@RequestParam("thirdPartyToken")String token, @RequestBody DamoaSignUpDto signUpRequestDto) throws
		UnsupportedEncodingException,
		JsonProcessingException {
		return authLoginService.signUp(token, signUpRequestDto);
	}

	@PostMapping("/sign-up/two-way")
	public BaseResponse<?> signUpByTwoWayAuthentification(@RequestParam("thirdPartyToken")String token, @RequestBody DamoaSignUpDto twoWayRequestDto) throws
		UnsupportedEncodingException, JsonProcessingException {
		return authLoginService.twoWaySignUp(token, twoWayRequestDto);
	}

}