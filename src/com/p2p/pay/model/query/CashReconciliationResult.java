package com.p2p.pay.model.query;

import com.p2p.pay.model.HFModelBack;

/**
 * 
 * @author Administrator
 * 
 */
public class CashReconciliationResult {

	private String OrdId;
	private String MerCustId;
	private String UsrCustId;
	private String CardId;
	private String TransAmt;
	private String TransStat;
	private String PnrDate;
	private String PnrSeqId;
	private String FeeAmt;
	private String ServFee;
	private String ServFeeAcctId;

	public String getOrdId() {
		return OrdId;
	}

	public void setOrdId(String ordId) {
		OrdId = ordId;
	}

	public String getMerCustId() {
		return MerCustId;
	}

	public void setMerCustId(String merCustId) {
		MerCustId = merCustId;
	}

	public String getUsrCustId() {
		return UsrCustId;
	}

	public void setUsrCustId(String usrCustId) {
		UsrCustId = usrCustId;
	}

	public String getCardId() {
		return CardId;
	}

	public void setCardId(String cardId) {
		CardId = cardId;
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

	public String getFeeAmt() {
		return FeeAmt;
	}

	public void setFeeAmt(String feeAmt) {
		FeeAmt = feeAmt;
	}

	public String getServFee() {
		return ServFee;
	}

	public void setServFee(String servFee) {
		ServFee = servFee;
	}

	public String getServFeeAcctId() {
		return ServFeeAcctId;
	}

	public void setServFeeAcctId(String servFeeAcctId) {
		ServFeeAcctId = servFeeAcctId;
	}

}
