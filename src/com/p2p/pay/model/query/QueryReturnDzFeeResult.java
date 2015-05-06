package com.p2p.pay.model.query;

import java.util.Arrays;
import java.util.List;

import com.p2p.pay.model.HFModelBack;

/**
 * 
 * @author Administrator
 * 
 */
public class QueryReturnDzFeeResult {

	private String MerCustId;
	private String ReturnFee;
	private String ReturnAcctType;
	private String ReturnAcctId;
	private String ReturnDate;
	private String Stat;

	public String getMerCustId() {
		return MerCustId;
	}

	public void setMerCustId(String merCustId) {
		MerCustId = merCustId;
	}

	public String getReturnFee() {
		return ReturnFee;
	}

	public void setReturnFee(String returnFee) {
		ReturnFee = returnFee;
	}

	public String getReturnAcctType() {
		return ReturnAcctType;
	}

	public void setReturnAcctType(String returnAcctType) {
		ReturnAcctType = returnAcctType;
	}

	public String getReturnAcctId() {
		return ReturnAcctId;
	}

	public void setReturnAcctId(String returnAcctId) {
		ReturnAcctId = returnAcctId;
	}

	public String getReturnDate() {
		return ReturnDate;
	}

	public void setReturnDate(String returnDate) {
		ReturnDate = returnDate;
	}

	public String getStat() {
		return Stat;
	}

	public void setStat(String stat) {
		Stat = stat;
	}
	
	

}
