package com.p2p.pay.model.query;

import java.util.Arrays;
import java.util.List;

import com.p2p.pay.model.HFModel;

/**
 * 余额查询 ( 页 面)
 * 
 * @author Administrator
 * 
 */
public class QueryBalance extends HFModel {
	private String UsrCustId;

	public String getUsrCustId() {
		return UsrCustId;
	}

	public void setUsrCustId(String usrCustId) {
		UsrCustId = usrCustId;
	}

	@Override
	public String getMerData() {
		List<String> fileNameList = Arrays.asList("Version","CmdId","MerCustId","UsrCustId");
		return getMerData(fileNameList);
	}
}
