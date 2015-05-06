package com.p2p.pay.model.query;

import java.util.Arrays;
import java.util.List;

import com.p2p.pay.model.HFModel;

/**
 * 债权查询接口
 * 
 * @author Administrator
 * 
 */
public class CreditAssignReconciliation extends HFModel {

	private String OrdId;
	private String BeginDate;
	private String EndDate;
	private String SellCustId;
	private String BuyCustId;
	private String PageNum;
	private String PageSize;
	private String ReqExt;

	public String getOrdId() {
		return OrdId;
	}

	public void setOrdId(String ordId) {
		OrdId = ordId;
	}

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

	public String getSellCustId() {
		return SellCustId;
	}

	public void setSellCustId(String sellCustId) {
		SellCustId = sellCustId;
	}

	public String getBuyCustId() {
		return BuyCustId;
	}

	public void setBuyCustId(String buyCustId) {
		BuyCustId = buyCustId;
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

	public String getReqExt() {
		return ReqExt;
	}

	public void setReqExt(String reqExt) {
		ReqExt = reqExt;
	}

	@Override
	public String getMerData() {
		List<String> fileNameList = Arrays.asList("Version", "CmdId",
				"MerCustId", "OrdId", "BeginDate", "EndDate", "SellCustId",
				"BuyCustId", "PageNum", "PageSize", "ReqExt");
		return getMerData(fileNameList);
	}

}
