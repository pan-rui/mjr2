package com.p2p.pay.model.trade;

import java.util.Arrays;
import java.util.List;

import com.p2p.pay.model.HFModel;

/**
 * 自动投标计划
 * 
 * @author Administrator
 * 
 */
public class AutoTenderPlan extends HFModel {

	private String UsrCustId;
	private String TenderPlanType;
	private String TransAmt;
	private String RetUrl;
	private String MerPriv;

	public String getUsrCustId() {
		return UsrCustId;
	}

	public void setUsrCustId(String usrCustId) {
		UsrCustId = usrCustId;
	}

	public String getTenderPlanType() {
		return TenderPlanType;
	}

	public void setTenderPlanType(String tenderPlanType) {
		TenderPlanType = tenderPlanType;
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

	public String getMerPriv() {
		return MerPriv;
	}

	public void setMerPriv(String merPriv) {
		MerPriv = merPriv;
	}

	@Override
	public String getMerData() {
		List<String> fileNameList = Arrays.asList("Version", "CmdId",
				"MerCustId", "UsrCustId", "TenderPlanType", "TransAmt",
				"RetUrl", "MerPriv");
		return getMerData(fileNameList);
	}

}
