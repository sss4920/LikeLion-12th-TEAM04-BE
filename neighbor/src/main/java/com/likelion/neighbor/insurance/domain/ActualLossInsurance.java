package com.likelion.neighbor.insurance.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ActualLossInsurance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ACTUALLOSS_ID")
	private Long id;

	@Column(name = "GENERATION")
	private Integer generation; //세대는 null이 될 수 있는가

	@Column(name = "OUTPATIENT_VISIT")
	private boolean outPatientVisit; //통원 지원되는지

	@Column(name = "HOSPITALIZED")
	private boolean hospitalized; //입원 지원되는지

	@Column(name = "ARCHITECTURE")
	private String architecture; //null이 될 수 있는가?

	@Column(name="DEDUCTIBLE")
	private Long deductible; // 자기부담금

	@Column(name="COMPENSATION_DETAILS")
	private String compensationDetails; //보상해주는 내용들 리스트 -> 문자열로 convert

	@Column(name="END_DATE")
	private LocalDateTime endDate; //판매종료시기

	@Column(name = "COMPANY_NAME")
	private String companyName; // 회사명

	@Builder
	private ActualLossInsurance(Integer generation, boolean outPatientVisit, boolean hospitalized, String architecture,
		Long deductible, String compensationDetails, LocalDateTime endDate, String companyName) {
		this.generation = generation;
		this.outPatientVisit = outPatientVisit;
		this.hospitalized = hospitalized;
		this.architecture = architecture;
		this.deductible = deductible;
		this.compensationDetails = compensationDetails;
		this.endDate = endDate;
		this.companyName = companyName;
	}
}
