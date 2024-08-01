package com.likelion.neighbor.insurance.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonDeserialize(builder = InsuranceRequestDto.InsuranceRequestDtoBuilder.class)
@Builder
@NoArgsConstructor(force = true)
public class InsuranceRequestDto {
	private String organization;
	private String id;
	private String password;
	private String type;
	private String userName;
	private String identity;
	private String birthDate;
	private String identityEncYn;
	private String phoneNo;
	private String telecom;

	@JsonCreator
	public InsuranceRequestDto(
		@JsonProperty("organization") String organization,
		@JsonProperty("id") String id,
		@JsonProperty("password") String password,
		@JsonProperty("type") String type,
		@JsonProperty("userName") String userName,
		@JsonProperty("identity") String identity,
		@JsonProperty("birthDate") String birthDate,
		@JsonProperty("identityEncYn") String identityEncYn,
		@JsonProperty("phoneNo") String phoneNo,
		@JsonProperty("telecom") String telecom)
	{
		this.organization = organization;
		this.id = id;
		this.password = password;
		this.type = type;
		this.userName = userName;
		this.identity = identity;
		this.birthDate = birthDate;
		this.identityEncYn = identityEncYn;
		this.phoneNo = phoneNo;
		this.telecom = telecom;
	}
}

