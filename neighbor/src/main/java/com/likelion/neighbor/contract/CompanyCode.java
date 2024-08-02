package com.likelion.neighbor.contract;

public enum CompanyCode {
	MERITZ_FIRE("메리츠화재", "0101"),
	HANWHA_GENERAL("한화손보", "0102"),
	LOTTE_INSURANCE("롯데손보", "0103"),
	MG_INSURANCE("MG손보", "0104"),
	HUNGUK_FIRE("흥국화재", "0105"),
	SAMSUNG_FIRE("삼성화재", "0108"),
	HYUNDAI_HEALTH("현대해상", "0109"),
	KB_INSURANCE("KB손보", "0110"),
	DB_INSURANCE("DB손보", "0111"),
	AXA_INSURANCE("AXA손보", "0112"),
	AIG_INSURANCE("AIG손보", "0151"),
	HANNA_INSURANCE("하나손보(구 더케이손보)", "0152"),
	NH_NONGHYUP("NH농협손해보험", "0171"),
	BNP_PARIBAS_CARDIFF("BNP파리바카디프손보", "0193"),
	CHUBB_INSURANCE("Chubb손보(구 ACE손보)", "0194"),
	HANWHA_LIFE("한화생명", "0201"),
	ALLIANZ_LIFE("알리안츠생명", "0202"),
	SAMSUNG_LIFE("삼성생명", "0203"),
	HUNGUK_LIFE("흥국생명", "0204"),
	KYOBOLIFE("교보생명", "0205"),
	SHINHAN_LIFE("신한라이프(구 신한생명)", "0211"),
	FUBON_HYUNDAI_LIFE("푸본현대생명(구 현대라이프생명)", "0217"),
	KB_LIFE("KB라이프(구 KB생명)", "0218"),
	DGB_LIFE("DGB생명", "0231"),
	KDB_LIFE("KDB생명", "0233"),
	MIRAESSET_LIFE("미래에셋생명", "0234"),
	NONGHYUP_LIFE("농협생명", "0242"),
	SUHYUP("수협", "0504"),
	LINA_LIFE("라이나생명", "0251"),
	AIA_LIFE("AIA생명", "0252"),
	HANNA_LIFE("하나생명", "0263"),
	DB_LIFE("DB생명", "0271"),
	METLIFE_LIFE("메트라이프생명", "0272"),
	DONGYANG_LIFE("동양생명", "0274"),
	CHUBB_LIFE("쳐브라이프생명(구 ACE생명)", "0277"),
	BNP_PARIBAS_CARDIFF_LIFE("BNP파리바카디프생명", "0278"),
	IBK_PENSION("IBK연금보험", "0291"),
	POST_OFFICE_INSURANCE("우체국보험", "0501"),
	MG_SAEMAEUL("MG새마을금고", "0502"),
	SHINHYP("신협", "0503");
	private final String name;
	private final String code;

	CompanyCode(String name, String code){
		this.name = name;
		this.code = code;
	}

	public static String ifIsSameReturnCode(String name){
		String temp;
		for (CompanyCode companyCode: values()){
			if (name.equals(companyCode.name)){
				return companyCode.code;
			}
			else if (companyCode.name.contains("손보")){
				if (name.contains("손해보험")){
					temp = companyCode.name.replaceAll("손보", "손해보험");
					if (temp.equals(name)){
						return companyCode.code;
					}
				}
			}
		}
		return "다모여보험 코드 예외";
	}


}
