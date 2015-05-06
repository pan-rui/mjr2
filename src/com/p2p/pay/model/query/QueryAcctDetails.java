package com.p2p.pay.model.query;

import java.util.Arrays;
import java.util.List;

import com.p2p.pay.model.HFModel;

/**
 * 账户明细查询
 * 
 * @author Administrator
 * 
 */
public class QueryAcctDetails extends HFModel {
	private String UsrCustId;

	public String getUsrCustId() {
		return UsrCustId;
	}

	public void setUsrCustId(String usrCustId) {
		UsrCustId = usrCustId;
	}

	@Override
	public String getMerData() {
		List<String> fileNameList = Arrays.asList("Version", "CmdId",
				"MerCustId", "UsrCustId");
		return getMerData(fileNameList);
	}

}
