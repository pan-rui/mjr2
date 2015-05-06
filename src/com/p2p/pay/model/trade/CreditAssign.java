package com.p2p.pay.model.trade;

import java.util.Arrays;
import java.util.List;

import com.p2p.pay.model.HFModel;

/**
 * 债权转让接口
 * 
 * @author Administrator
 * 
 */
public class CreditAssign extends HFModel {

	private String SellCustId;
	private String CreditAmt;
	private String CreditDealAmt;
	private String BidDetails;
	private String Fee;
	private String DivDetails;
	private String BuyCustId;
	private String OrdId;
	private String OrdDate;
	private String RetUrl;
	private String BgRetUrl;
	private String MerPriv;
	private String ReqExt;

	public String getSellCustId() {
		return SellCustId;
	}

	public void setSellCustId(String sellCustId) {
		SellCustId = sellCustId;
	}

	public String getCreditAmt() {
		return CreditAmt;
	}

	public void setCreditAmt(String creditAmt) {
		CreditAmt = creditAmt;
	}

	public String getCreditDealAmt() {
		return CreditDealAmt;
	}

	public void setCreditDealAmt(String creditDealAmt) {
		CreditDealAmt = creditDealAmt;
	}

	public String getBidDetails() {
		return BidDetails;
	}

	public void setBidDetails(String bidDetails) {
		BidDetails = bidDetails;
	}

	public String getFee() {
		return Fee;
	}

	public void setFee(String fee) {
		Fee = fee;
	}

	public String getDivDetails() {
		return DivDetails;
	}

	public void setDivDetails(String divDetails) {
		DivDetails = divDetails;
	}

	public String getBuyCustId() {
		return BuyCustId;
	}

	public void setBuyCustId(String buyCustId) {
		BuyCustId = buyCustId;
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
				"MerCustId", "SellCustId", "CreditAmt", "CreditDealAmt",
				"BidDetails", "Fee", "DivDetails", "BuyCustId", "OrdId",
				"OrdDate", "RetUrl", "BgRetUrl", "MerPriv", "ReqExt");
		return getMerData(fileNameList);
	}

}
