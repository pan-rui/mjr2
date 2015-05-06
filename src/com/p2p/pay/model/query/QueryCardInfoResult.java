package com.p2p.pay.model.query;

import com.p2p.pay.model.HFModel;

/**
 * 银行卡查询接口
 * 
 * @author Administrator
 * 
 */
public class QueryCardInfoResult {

	private String MerCustId;
	private String UsrCustId;
	private String UsrName;
	private String CertId;
	private String BankId;
	private String CardId;
	private String RealFlag;
	private String UpdDateTime;
	private String ProvId;
	private String AreaId;
	private String IsDefault;

	public String getMerCustId() {
		return MerCustId;
	}

	public void setMerCustId(String merCustId) {
		MerCustId = merCustId;
	}

	public String getUsrCustId() {
		return UsrCustId;
	}

	public void setUsrCustId(String usrCustId) {
		UsrCustId = usrCustId;
	}

	public String getUsrName() {
		return UsrName;
	}

	public void setUsrName(String usrName) {
		UsrName = usrName;
	}

	public String getCertId() {
		return CertId;
	}

	public void setCertId(String certId) {
		CertId = certId;
	}

	public String getBankId() {
		return BankId;
	}

	public void setBankId(String bankId) {
		BankId = bankId;
	}

	public String getCardId() {
		return CardId;
	}

	public void setCardId(String cardId) {
		CardId = cardId;
	}

	public String getRealFlag() {
		return RealFlag;
	}

	public void setRealFlag(String realFlag) {
		RealFlag = realFlag;
	}

	public String getUpdDateTime() {
		return UpdDateTime;
	}

	public void setUpdDateTime(String updDateTime) {
		UpdDateTime = updDateTime;
	}

	public String getProvId() {
		return ProvId;
	}

	public void setProvId(String provId) {
		ProvId = provId;
	}

	public String getAreaId() {
		return AreaId;
	}

	public void setAreaId(String areaId) {
		AreaId = areaId;
	}

	public String getIsDefault() {
		return IsDefault;
	}

	public void setIsDefault(String isDefault) {
		IsDefault = isDefault;
	}

}
