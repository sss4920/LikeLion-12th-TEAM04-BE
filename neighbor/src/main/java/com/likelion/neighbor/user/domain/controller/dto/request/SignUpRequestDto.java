package com.likelion.neighbor.user.domain.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(force = true)
@Builder
public class SignUpRequestDto{
	private String organization;
	private String id;
	private String password;
	private String email;
	private String type;
	private String userName;
	private String identity;
	private String birthDate;
	private String identityEncYn;
	private String phoneNo;
	private String telecom;
	private String smsAuthNo;
	private Boolean is2Way;
	private TwoWayInfo twoWayInfo;
	public SignUpRequestDto(
		@JsonProperty("organization") String organization,
		@JsonProperty("id") String id,
		@JsonProperty("password") String password,
		@JsonProperty("email") String email,
		@JsonProperty("type") String type,
		@JsonProperty("userName") String userName,
		@JsonProperty("identity") String identity,
		@JsonProperty("birthDate") String birthDate,
		@JsonProperty("identityEncYn") String identityEncYn,
		@JsonProperty("phoneNo") String phoneNo,
		@JsonProperty("telecom") String telecom) {

		this.organization = organization;
		this.id = id;
		this.password = password;
		this.email = email;
		this.type = type;
		this.userName = userName;
		this.identity = identity;
		this.birthDate = birthDate;
		this.identityEncYn = identityEncYn;
		this.phoneNo = phoneNo;
		this.telecom = telecom;
	}
	public SignUpRequestDto(
		@JsonProperty("organization") String organization,
		@JsonProperty("id") String id,
		@JsonProperty("password") String password,
		@JsonProperty("email") String email,
		@JsonProperty("type") String type,
		@JsonProperty("userName") String userName,
		@JsonProperty("identity") String identity,
		@JsonProperty("birthDate") String birthDate,
		@JsonProperty("identityEncYn") String identityEncYn,
		@JsonProperty("phoneNo") String phoneNo,
		@JsonProperty("telecom") String telecom,
		@JsonProperty("smsAutoNo") String smsAuthNo,
		@JsonProperty("is2Way") Boolean is2Way,
		@JsonProperty("twoWayInfo")TwoWayInfo twoWayInfo
		) {

		this.organization = organization;
		this.id = id;
		this.password = password;
		this.email = email;
		this.type = type;
		this.userName = userName;
		this.identity = identity;
		this.birthDate = birthDate;
		this.identityEncYn = identityEncYn;
		this.phoneNo = phoneNo;
		this.telecom = telecom;
		this.smsAuthNo = smsAuthNo;
		this.is2Way= is2Way;
		this.twoWayInfo = twoWayInfo;
	}


}
