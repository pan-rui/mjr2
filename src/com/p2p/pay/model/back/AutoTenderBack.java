package com.p2p.pay.model.back;

import java.util.Arrays;
import java.util.List;

import com.p2p.pay.model.HFModelBack;

/**
 * 
 * @author Administrator
 * 
 */
public class AutoTenderBack extends HFModelBack {

	private String OrdId;
	private String OrdDate;
	private String TransAmt;
	private String UsrCustId;
	private String TrxId;
	private String IsFreeze;
	private String FreezeOrdId;
	private String FreezeTrxId;
	private String RetUrl;
	private String BgRetUrl;
	private String MerPriv;
	private String RespExt;

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

	public String getTrxId() {
		return TrxId;
	}

	public void setTrxId(String trxId) {
		TrxId = trxId;
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

	public String getRespExt() {
		return RespExt;
	}

	public void setRespExt(String respExt) {
		RespExt = respExt;
	}

	@Override
	public String getMerData() {
		List<String> fileNameList = Arrays.asList("CmdId", "RespCode",
				"MerCustId", "OrdId", "OrdDate", "TransAmt", "UsrCustId",
				"TrxId", "IsFreeze", "FreezeOrdId", "FreezeTrxId", "RetUrl",
				"BgRetUrl", "MerPriv", "RespExt");
		return getMerData(fileNameList);
	}

}
