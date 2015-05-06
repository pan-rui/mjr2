package com.p2p.pay.model.usermanage;

import java.util.Arrays;
import java.util.List;

import com.p2p.pay.model.HFModel;

/**
 * 企业开户
 * 
 * @author Administrator
 * 
 */
public class CorpRegister extends HFModel {

	private String UsrId;
	private String UsrName;
	private String InstuCode;
	private String BusiCode;
	private String TaxCode;
	private String MerPriv;
	private String Charset;
	private String GuarType;
	private String BgRetUrl;
	private String ReqExt;

	public String getUsrId() {
		return UsrId;
	}

	public void setUsrId(String usrId) {
		UsrId = usrId;
	}

	public String getUsrName() {
		return UsrName;
	}

	public void setUsrName(String usrName) {
		UsrName = usrName;
	}

	public String getInstuCode() {
		return InstuCode;
	}

	public void setInstuCode(String instuCode) {
		InstuCode = instuCode;
	}

	public String getBusiCode() {
		return BusiCode;
	}

	public void setBusiCode(String busiCode) {
		BusiCode = busiCode;
	}

	public String getTaxCode() {
		return TaxCode;
	}

	public void setTaxCode(String taxCode) {
		TaxCode = taxCode;
	}

	public String getMerPriv() {
		return MerPriv;
	}

	public void setMerPriv(String merPriv) {
		MerPriv = merPriv;
	}

	public String getCharset() {
		return Charset;
	}

	public void setCharset(String charset) {
		Charset = charset;
	}

	public String getGuarType() {
		return GuarType;
	}

	public void setGuarType(String guarType) {
		GuarType = guarType;
	}

	public String getBgRetUrl() {
		return BgRetUrl;
	}

	public void setBgRetUrl(String bgRetUrl) {
		BgRetUrl = bgRetUrl;
	}

	public String getReqExt() {
		return ReqExt;
	}

	public void setReqExt(String reqExt) {
		ReqExt = reqExt;
	}

	@Override
	public String getMerData() {
		List<String> fileNameList = Arrays.asList("Version", "CmdId",
				"MerCustId", "UsrId", "UsrName", "InstuCode", "BusiCode",
				"TaxCode", "MerPriv", "GuarType", "BgRetUrl", "ReqExt");
		return getMerData(fileNameList);
	}

}
