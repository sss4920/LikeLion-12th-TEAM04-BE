package com.likelion.neighbor.insurance.domain;

public enum CompensationDetails {
	DEMENTIA("치매"),
	MENTAL_ILLNESS("정신질환"),
	HEMORRIHOIDS("치질"),
	URINARY("비뇨기계"),
	DENTAL("치과치료"),
	ORIENTAL_MEDICINE_TREATMENT("한방치료"),
	CATARACT("백내장"),
	NATURAL_DISASTER("천재지변"),
	;

	private String name;

	CompensationDetails(String name) {
		this.name = name;
	}
}
