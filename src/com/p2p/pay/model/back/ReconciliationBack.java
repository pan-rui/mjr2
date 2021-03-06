package com.p2p.pay.model.back;

import java.util.Arrays;
import java.util.List;

import com.p2p.pay.model.HFModelBack;

/**
 * 
 * @author Administrator
 * 
 */
public class ReconciliationBack extends HFModelBack {

	private String BeginDate;
	private String EndDate;
	private String PageNum;
	private String PageSize;
	private String TotalItems;
	private String QueryTransType;
	private String ReconciliationDtoList;

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

	public String getTotalItems() {
		return TotalItems;
	}

	public void setTotalItems(String totalItems) {
		TotalItems = totalItems;
	}

	public String getQueryTransType() {
		return QueryTransType;
	}

	public void setQueryTransType(String queryTransType) {
		QueryTransType = queryTransType;
	}

	public String getReconciliationDtoList() {
		return ReconciliationDtoList;
	}

	public void setReconciliationDtoList(String reconciliationDtoList) {
		ReconciliationDtoList = reconciliationDtoList;
	}

	@Override
	public String getMerData() {
		List<String> fileNameList = Arrays.asList("CmdId", "RespCode",
				"MerCustId", "BeginDate", "EndDate", "PageNum", "PageSize",
				"TotalItems", "QueryTransType");
		return getMerData(fileNameList);
	}

}
