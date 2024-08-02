package com.likelion.neighbor.insurance.controller.dto.request;

import com.likelion.neighbor.insurance.domain.DentalInsurance;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DentalInsuranceSaveReqDto {

    @NotBlank(message = "보험 이름은 필수 항목입니다.")
    private String insuranceName;

    @NotBlank(message = "치료 이름은 필수 항목입니다.")
    private String treatmentName;

    @NotNull(message = "보장 가격은 필수 항목입니다.")
    private Long assuredPrice;

    private String caution;

    private String site;

    private String note;

    public DentalInsurance toEntity() {
        return DentalInsurance.builder()
                .insuranceName(insuranceName)
                .treatmentName(treatmentName)
                .assuredPrice(assuredPrice)
                .caution(caution)
                .site(site)
                .note(note)
                .build();
    }
}
