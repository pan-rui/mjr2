package com.p2p.pay.model.query;

import java.util.Arrays;
import java.util.List;

import com.p2p.pay.model.HFModel;

/**
 * 投标对账 PageSize 最大1000
 * 
 * @author Administrator
 * 
 */
public class Reconciliation extends HFModel {

	private String BeginDate;
	private String EndDate;
	private String PageNum;
	private String PageSize;
	private String QueryTransType;

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

	public String getQueryTransType() {
		return QueryTransType;
	}

	public void setQueryTransType(String queryTransType) {
		QueryTransType = queryTransType;
	}

	@Override
	public String getMerData() {
		List<String> fileNameList = Arrays.asList("Version", "CmdId",
				"MerCustId", "BeginDate", "EndDate", "PageNum", "PageSize",
				"QueryTransType");
		return getMerData(fileNameList);
	}

}
