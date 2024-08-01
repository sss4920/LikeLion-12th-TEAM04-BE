package com.likelion.neighbor.insurance.domain;

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
public class DentalInsurance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "INSURANCE_ID")
	private Long id;

	@Column(name = "INSURANCE_NAME")
	private String insuranceName;

	@Column(name = "TREATMENT_NAME")
	private String treatmentName;

	@Column(name = "ASSURED_PRICE")
	private Long assuredPrice; //null이 될 수 있는가?

	@Column(name = "CAUTION")
	private String caution; //주의사항

	@Column(name = "SITE")
	private String site; // 사이트 url

	@Column(name = "NOTE")
	private String note; // 기타사항

	@Builder
	private DentalInsurance(String insuranceName, String treatmentName, Long assuredPrice, String caution,
							String site, String note) {
		this.insuranceName = insuranceName;
		this.treatmentName = treatmentName;
		this.assuredPrice = assuredPrice;
		this.caution = caution;
		this.site = site;
		this.note = note;
	}

	public void update(String insuranceName, String treatmentName, Long assuredPrice, String caution, String site, String note) {
		this.insuranceName = insuranceName;
		this.treatmentName = treatmentName;
		this.assuredPrice = assuredPrice;
		this.caution = caution;
		this.site = site;
		this.note = note;
	}
}
