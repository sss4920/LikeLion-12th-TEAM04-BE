package com.likelion.neighbor.contract.domain;

import com.likelion.neighbor.user.domain.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ContractInformation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String resCompanyNm; //상호(사업장명)
	private String resInsuranceName; //보험상품명
	private String resPhoneNo; //보험사 전화번호
	private String resHomePage; //보험사 홈페이지 url 근데 클릭해보니 안나옴;

	private String resInsuredPerson; //피보험자 이름
	private boolean isDentalInsurance; //치아보험인지 아닌지
	@ManyToOne
	private User user;
	@Builder
	private ContractInformation(String resCompanyNm, String resInsuranceName, String resPhoneNo, String resHomePage,
		String resInsuredPerson, boolean isDentalInsurance, User user) {
		this.resCompanyNm = resCompanyNm;
		this.resInsuranceName = resInsuranceName;
		this.resPhoneNo = resPhoneNo;
		this.resHomePage = resHomePage;
		this.resInsuredPerson = resInsuredPerson;
		this.isDentalInsurance = isDentalInsurance;
		this.user = user;
	}
}
