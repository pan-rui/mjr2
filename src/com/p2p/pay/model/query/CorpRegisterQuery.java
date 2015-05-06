package com.p2p.pay.model.query;

import java.util.Arrays;
import java.util.List;

import com.p2p.pay.model.HFModel;

/**
 * 企业开户
 * 
 * @author Administrator
 * 
 */
public class CorpRegisterQuery extends HFModel {

	private String BusiCode;
	private String ReqExt;

	public String getBusiCode() {
		return BusiCode;
	}

	public void setBusiCode(String busiCode) {
		BusiCode = busiCode;
	}

	public String getReqExt() {
		return ReqExt;
	}

	public void setReqExt(String reqExt) {
		ReqExt = reqExt;
	}
	
	@Override
	public String getMerData() {
		List<String> fileNameList = Arrays.asList("Version","CmdId","MerCustId","BusiCode","ReqExt");
		return getMerData(fileNameList);
	}

}
