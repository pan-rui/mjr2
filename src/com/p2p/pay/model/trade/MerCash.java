package com.p2p.pay.model.trade;

import java.util.Arrays;
import java.util.List;

import com.p2p.pay.model.HFModel;

/**
 * 商户代取现接口
 * 
 * @author Administrator
 * 
 */
public class MerCash extends HFModel {

	private String OrdId;
	private String UsrCustId;
	private String TransAmt;
	private String RetUrl;
	private String BgRetUrl;
	private String Remark;
	private String CharSet;
	private String MerPriv;

	public String getOrdId() {
		return OrdId;
	}

	public void setOrdId(String ordId) {
		OrdId = ordId;
	}

	public String getUsrCustId() {
		return UsrCustId;
	}

	public void setUsrCustId(String usrCustId) {
		UsrCustId = usrCustId;
	}

	public String getTransAmt() {
		return TransAmt;
	}

	public void setTransAmt(String transAmt) {
		TransAmt = transAmt;
	}

	public String getRetUrl() {
		return RetUrl;
	}

	public void setRetUrl(String retUrl) {
		RetUrl = retUrl;
	}

	public String getBgRetUrl() {
		return BgRetUrl;
	}

	public void setBgRetUrl(String bgRetUrl) {
		BgRetUrl = bgRetUrl;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

	public String getCharSet() {
		return CharSet;
	}

	public void setCharSet(String charSet) {
		CharSet = charSet;
	}

	public String getMerPriv() {
		return MerPriv;
	}

	public void setMerPriv(String merPriv) {
		MerPriv = merPriv;
	}

	@Override
	public String getMerData() {
		List<String> fileNameList = Arrays.asList("Version", "CmdId",
				"MerCustId", "OrdId", "UsrCustId", "TransAmt", "RetUrl",
				"BgRetUrl", "Remark", "MerPriv");
		return getMerData(fileNameList);
	}
}
