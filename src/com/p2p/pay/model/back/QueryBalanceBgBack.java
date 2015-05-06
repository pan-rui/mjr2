package com.p2p.pay.model.back;

import java.util.Arrays;
import java.util.List;

import com.p2p.pay.model.HFModelBack;

/**
 * 
 * @author Administrator
 * 
 */
public class QueryBalanceBgBack extends HFModelBack {

	private String UsrCustId;
	private String AvlBal;
	private String AcctBal;
	private String FrzBal;

	public String getUsrCustId() {
		return UsrCustId;
	}

	public void setUsrCustId(String usrCustId) {
		UsrCustId = usrCustId;
	}

	public String getAvlBal() {
		return AvlBal;
	}

	public void setAvlBal(String avlBal) {
		AvlBal = avlBal;
	}

	public String getAcctBal() {
		return AcctBal;
	}

	public void setAcctBal(String acctBal) {
		AcctBal = acctBal;
	}

	public String getFrzBal() {
		return FrzBal;
	}

	public void setFrzBal(String frzBal) {
		FrzBal = frzBal;
	}

	@Override
	public String getMerData() {
		List<String> fileNameList = Arrays.asList("CmdId", "RespCode",
				"MerCustId", "UsrCustId", "AvlBal", "AcctBal", "FrzBal");
		return getMerData(fileNameList);
	}

}
