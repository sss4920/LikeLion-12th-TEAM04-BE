package com.likelion.neighbor.user.domain.controller.dto.response;

import com.likelion.neighbor.insurance.controller.dto.response.ResultDto;

import lombok.Builder;

public record NeedToSecondaryDto(ResultDto result, SecondaryDataDto data) {
}
