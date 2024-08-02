package com.likelion.neighbor.insurance.controller.dto.response;

import com.likelion.neighbor.insurance.domain.DentalInsurance;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DentalInsuranceInfoResDto {

    private Long id;
    private String insuranceName;
    private String treatmentName;
    private Long assuredPrice;
    private String caution;
    private String site;
    private String note;

    public static DentalInsuranceInfoResDto fromEntity(DentalInsurance dentalInsurance) {
        return DentalInsuranceInfoResDto.builder()
                .id(dentalInsurance.getId())
                .insuranceName(dentalInsurance.getInsuranceName())
                .treatmentName(dentalInsurance.getTreatmentName())
                .assuredPrice(dentalInsurance.getAssuredPrice())
                .caution(dentalInsurance.getCaution())
                .site(dentalInsurance.getSite())
                .note(dentalInsurance.getNote())
                .build();
    }
}
