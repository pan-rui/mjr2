package com.p2p.pay.model.query;


/**
 * 
 * @author Administrator
 * 
 */
public class ReconciliationResult {

	private String OrdId;
	private String OrdDate;
	private String MerCustId;
	private String InvestCustId;
	private String TransAmt;
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

	public String getMerCustId() {
		return MerCustId;
	}

	public void setMerCustId(String merCustId) {
		MerCustId = merCustId;
	}

	public String getInvestCustId() {
		return InvestCustId;
	}

	public void setInvestCustId(String investCustId) {
		InvestCustId = investCustId;
	}

	public String getTransAmt() {
		return TransAmt;
	}

	public void setTransAmt(String transAmt) {
		TransAmt = transAmt;
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
