package com.p2p.pay.model.trade;

import java.util.Arrays;
import java.util.List;

import com.p2p.pay.model.HFModel;

/**
 * 投标撤销
 * 
 * @author Administrator
 * 
 */
public class TenderCancle extends HFModel {

	private String OrdId;
	private String OrdDate;
	private String TransAmt;
	private String UsrCustId;
	private String IsUnFreeze;
	private String UnFreezeOrdId;
	private String FreezeTrxId;
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

	public String getIsUnFreeze() {
		return IsUnFreeze;
	}

	public void setIsUnFreeze(String isUnFreeze) {
		IsUnFreeze = isUnFreeze;
	}

	public String getUnFreezeOrdId() {
		return UnFreezeOrdId;
	}

	public void setUnFreezeOrdId(String unFreezeOrdId) {
		UnFreezeOrdId = unFreezeOrdId;
	}

	public String getFreezeTrxId() {
		return FreezeTrxId;
	}

	public void setFreezeTrxId(String freezeTrxId) {
		FreezeTrxId = freezeTrxId;
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
				"IsUnFreeze", "UnFreezeOrdId", "FreezeTrxId", "RetUrl",
				"BgRetUrl", "MerPriv", "ReqExt");
		return getMerData(fileNameList);
	}

}
