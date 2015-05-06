package com.p2p.pay.model.back;

import java.util.Arrays;
import java.util.List;

import com.p2p.pay.model.HFModelBack;

/**
 * 
 * @author Administrator
 * 
 */
public class QueryAcctsBack extends HFModelBack {
	private String AcctDetails;

	public String getAcctDetails() {
		return AcctDetails;
	}

	public void setAcctDetails(String acctDetails) {
		AcctDetails = acctDetails;
	}

	@Override
	public String getMerData() {
		List<String> fileNameList = Arrays.asList("CmdId", "RespCode",
				"MerCustId");
		return getMerData(fileNameList);
	}
}
