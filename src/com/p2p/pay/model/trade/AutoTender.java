package com.p2p.pay.model.trade;

import java.util.Arrays;
import java.util.List;

import com.p2p.pay.model.HFModel;

/**
 * 自动投标(Version 20)
 * 
 * @author Administrator
 * 
 */
public class AutoTender extends HFModel {

	private String OrdId;
	private String OrdDate;
	private String TransAmt;
	private String UsrCustId;
	private String MaxTenderRate;
	private String BorrowerDetails;
	private String IsFreeze;
	private String FreezeOrdId;
	private String RetUrl;
	private String BgRetUrl;
	private String MerPriv;
	private String ReqExt;

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

	public String getUsrCustId() {
		return UsrCustId;
	}

	public void setUsrCustId(String usrCustId) {
		UsrCustId = usrCustId;
	}

	public String getMaxTenderRate() {
		return MaxTenderRate;
	}

	public void setMaxTenderRate(String maxTenderRate) {
		MaxTenderRate = maxTenderRate;
	}

	public String getBorrowerDetails() {
		return BorrowerDetails;
	}

	public void setBorrowerDetails(String borrowerDetails) {
		BorrowerDetails = borrowerDetails;
	}

	public String getIsFreeze() {
		return IsFreeze;
	}

	public void setIsFreeze(String isFreeze) {
		IsFreeze = isFreeze;
	}

	public String getFreezeOrdId() {
		return FreezeOrdId;
	}

	public void setFreezeOrdId(String freezeOrdId) {
		FreezeOrdId = freezeOrdId;
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

	public String getReqExt() {
		return ReqExt;
	}

	public void setReqExt(String reqExt) {
		ReqExt = reqExt;
	}

	@Override
	public String getMerData() {
		List<String> fileNameList = Arrays.asList("Version", "CmdId",
				"MerCustId", "OrdId", "OrdDate", "TransAmt", "UsrCustId",
				"MaxTenderRate", "BorrowerDetails", "IsFreeze", "FreezeOrdId",
				"RetUrl", "BgRetUrl", "MerPriv", "ReqExt");
		return getMerData(fileNameList);
	}

}
