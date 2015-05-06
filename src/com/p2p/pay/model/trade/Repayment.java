package com.p2p.pay.model.trade;

import java.util.Arrays;
import java.util.List;

import com.p2p.pay.model.HFModel;

/**
 * 自动扣款（还款） 如果不收取手续费，Fee 字段填 0.00, DivDetails 字段请填空。
 * 
 * @author Administrator
 * 
 */
public class Repayment extends HFModel {

	private String OrdId;
	private String OrdDate;
	private String OutCustId;
	private String SubOrdId;
	private String SubOrdDate;
	private String OutAcctId;
	private String TransAmt;
	private String Fee;
	private String InCustId;
	private String InAcctId;
	private String DivDetails;
	private String FeeObjFlag;
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

	public String getOutCustId() {
		return OutCustId;
	}

	public void setOutCustId(String outCustId) {
		OutCustId = outCustId;
	}

	public String getSubOrdId() {
		return SubOrdId;
	}

	public void setSubOrdId(String subOrdId) {
		SubOrdId = subOrdId;
	}

	public String getSubOrdDate() {
		return SubOrdDate;
	}

	public void setSubOrdDate(String subOrdDate) {
		SubOrdDate = subOrdDate;
	}

	public String getOutAcctId() {
		return OutAcctId;
	}

	public void setOutAcctId(String outAcctId) {
		OutAcctId = outAcctId;
	}

	public String getTransAmt() {
		return TransAmt;
	}

	public void setTransAmt(String transAmt) {
		TransAmt = transAmt;
	}

	public String getFee() {
		return Fee;
	}

	public void setFee(String fee) {
		Fee = fee;
	}

	public String getInCustId() {
		return InCustId;
	}

	public void setInCustId(String inCustId) {
		InCustId = inCustId;
	}

	public String getInAcctId() {
		return InAcctId;
	}

	public void setInAcctId(String inAcctId) {
		InAcctId = inAcctId;
	}

	public String getDivDetails() {
		return DivDetails;
	}

	public void setDivDetails(String divDetails) {
		DivDetails = divDetails;
	}

	public String getFeeObjFlag() {
		return FeeObjFlag;
	}

	public void setFeeObjFlag(String feeObjFlag) {
		FeeObjFlag = feeObjFlag;
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
				"MerCustId", "OrdId", "OrdDate", "OutCustId", "SubOrdId",
				"SubOrdDate", "OutAcctId", "TransAmt", "Fee", "InCustId",
				"InAcctId", "DivDetails", "FeeObjFlag", "BgRetUrl", "MerPriv",
				"ReqExt");
		return getMerData(fileNameList);
	}

}
