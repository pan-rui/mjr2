package com.p2p.pay.model.query;

import java.util.Arrays;
import java.util.List;

import com.p2p.pay.model.HFModel;

/**
 * 自动投标计划状态查询
 * 
 * @author Administrator
 * 
 */
public class QueryTenderPlan extends HFModel {
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
