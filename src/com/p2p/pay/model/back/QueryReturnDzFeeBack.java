package com.p2p.pay.model.back;

import java.util.Arrays;
import java.util.List;

import com.p2p.pay.model.HFModelBack;

/**
 * 
 * @author Administrator
 * 
 */
public class QueryReturnDzFeeBack extends HFModelBack {
	private String BeginDate;
	private String EndDate;
	private String DatedBatchList;

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

	public String getDatedBatchList() {
		return DatedBatchList;
	}

	public void setDatedBatchList(String datedBatchList) {
		DatedBatchList = datedBatchList;
	}

	@Override
	public String getMerData() {
		List<String> fileNameList = Arrays.asList("CmdId", "RespCode",
				"MerCustId", "BeginDate", "EndDate");
		return getMerData(fileNameList);
	}

}
