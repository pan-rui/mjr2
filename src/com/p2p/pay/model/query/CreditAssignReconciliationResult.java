package com.p2p.pay.model.query;


/**
 * 
 * @author Administrator
 * 
 */
public class CreditAssignReconciliationResult {

	private String OrdId;
	private String OrdDate;
	private String SellCustId;
	private String CreditAmt;
	private String CreditDealAmt;
	private String Fee;
	private String BuyCustId;
	private String TransStat;
	private String PnrDate;
	private String PnrSeqId;

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

	public String getFee() {
		return Fee;
	}

	public void setFee(String fee) {
		Fee = fee;
	}

	public String getBuyCustId() {
		return BuyCustId;
	}

	public void setBuyCustId(String buyCustId) {
		BuyCustId = buyCustId;
	}

	public String getTransStat() {
		return TransStat;
	}

	public void setTransStat(String transStat) {
		TransStat = transStat;
	}

	public String getPnrDate() {
		return PnrDate;
	}

	public void setPnrDate(String pnrDate) {
		PnrDate = pnrDate;
	}

	public String getPnrSeqId() {
		return PnrSeqId;
	}

	public void setPnrSeqId(String pnrSeqId) {
		PnrSeqId = pnrSeqId;
	}

}
