package com.p2p.pay.model.query;

import java.util.Arrays;
import java.util.List;

import com.p2p.pay.model.HFModel;

/**
 * 垫资手续费返还查询
 * 
 * @author Administrator
 * 
 */
public class QueryReturnDzFee extends HFModel {

	private String BeginDate;
	private String EndDate;

	public String getBeginDate() {
		return BeginDate;
	}

	public void setBeginDate(String beginDate) {
		BeginDate = beginDate;
	}

	public String getEndDate() {
		return EndDate;
	}

	public void setEndDate(String endDate) {
		EndDate = endDate;
	}

	@Override
	public String getMerData() {
		List<String> fileNameList = Arrays.asList("Version", "CmdId",
				"MerCustId", "BeginDate", "EndDate");
		return getMerData(fileNameList);
	}

}
