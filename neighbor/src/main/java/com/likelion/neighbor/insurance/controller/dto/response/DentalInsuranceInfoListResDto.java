package com.likelion.neighbor.insurance.controller.dto.response;

import com.likelion.neighbor.insurance.domain.DentalInsurance;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
public class DentalInsuranceInfoListResDto {

    private List<DentalInsuranceInfoResDto> dentalInsurances;

    public static DentalInsuranceInfoListResDto fromEntityList(List<DentalInsurance> dentalInsuranceList) {
        List<DentalInsuranceInfoResDto> dtos = dentalInsuranceList.stream()
                .map(DentalInsuranceInfoResDto::fromEntity)
                .collect(Collectors.toList());
        return DentalInsuranceInfoListResDto.builder()
                .dentalInsurances(dtos)
                .build();
    }
}
