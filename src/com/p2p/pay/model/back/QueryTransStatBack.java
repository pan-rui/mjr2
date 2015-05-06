package com.p2p.pay.model.back;

import java.util.Arrays;
import java.util.List;

import com.p2p.pay.model.HFModelBack;

/**
 * 
 * @author Administrator
 * 
 */
public class QueryTransStatBack extends HFModelBack {

	private String OrdId;
	private String OrdDate;
	private String QueryTransType;
	private String TransStat;

	public String getOrdId() {
		return OrdId;
	}

	public void setOrdId(String ordId) {
		OrdId = ordId;
	}

	public String getOrdDate() {
		return OrdDate;
	}

	public void setOrdDate(String ordDate) {
		OrdDate = ordDate;
	}

	public String getQueryTransType() {
		return QueryTransType;
	}

	public void setQueryTransType(String queryTransType) {
		QueryTransType = queryTransType;
	}

	public String getTransStat() {
		return TransStat;
	}

	public void setTransStat(String transStat) {
		TransStat = transStat;
	}

	@Override
	public String getMerData() {
		List<String> fileNameList = Arrays
				.asList("CmdId", "RespCode", "MerCustId", "OrdId", "OrdDate",
						"QueryTransType", "TransStat");
		return getMerData(fileNameList);
	}

}
