package com.p2p.pay.model.query;

import java.util.Arrays;
import java.util.List;

import com.p2p.pay.model.HFModel;

/**
 * 充值对账
 * 
 * @author Administrator
 * 
 */
public class SaveReconciliation extends HFModel {

	private String BeginDate;
	private String EndDate;
	private String PageNum;
	private String PageSize;

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

	public String getPageNum() {
		return PageNum;
	}

	public void setPageNum(String pageNum) {
		PageNum = pageNum;
	}

	public String getPageSize() {
		return PageSize;
	}

	public void setPageSize(String pageSize) {
		PageSize = pageSize;
	}

	@Override
	public String getMerData() {
		List<String> fileNameList = Arrays.asList("Version", "CmdId",
				"MerCustId", "BeginDate", "EndDate", "PageNum", "PageSize");
		return getMerData(fileNameList);
	}
}
