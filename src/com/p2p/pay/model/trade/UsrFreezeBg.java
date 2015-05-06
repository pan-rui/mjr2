package com.p2p.pay.model.trade;

import java.util.Arrays;
import java.util.List;

import com.p2p.pay.model.HFModel;

/**
 * 资金（货款）冻结
 * 
 * @author Administrator
 * 
 */
public class UsrFreezeBg extends HFModel {

	private String UsrCustId;
	private String SubAcctType;
	private String SubAcctId;
	private String OrdId;
	private String OrdDate;
	private String TransAmt;
	private String RetUrl;
	private String BgRetUrl;
	private String MerPriv;

	public String getUsrCustId() {
		return UsrCustId;
	}

	public void setUsrCustId(String usrCustId) {
		UsrCustId = usrCustId;
	}

	public String getSubAcctType() {
		return SubAcctType;
	}

	public void setSubAcctType(String subAcctType) {
		SubAcctType = subAcctType;
	}

	public String getSubAcctId() {
		return SubAcctId;
	}

	public void setSubAcctId(String subAcctId) {
		SubAcctId = subAcctId;
	}

	public String getOrdId() {
		return OrdId;
	}

	public void setOrdId(String ordId) {
		OrdId = ordId;
	}

	public String getOrdDate() {
		return OrdDate;
	}

	public void setOrdDate(String ordDate) {
		OrdDate = ordDate;
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

	public String getMerPriv() {
		return MerPriv;
	}

	public void setMerPriv(String merPriv) {
		MerPriv = merPriv;
	}

	@Override
	public String getMerData() {
		List<String> fileNameList = Arrays.asList("Version", "CmdId",
				"MerCustId", "UsrCustId", "SubAcctType", "SubAcctId", "OrdId",
				"OrdDate", "TransAmt", "RetUrl", "BgRetUrl", "MerPriv");
		return getMerData(fileNameList);
	}

}
