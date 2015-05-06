package com.p2p.pay.model.trade;

import java.util.Arrays;
import java.util.List;

import com.p2p.pay.model.HFModel;

/**
 * 前台用户间转账接口
 * 
 * @author Administrator
 * 
 */
public class UsrTransfer extends HFModel {

	private String OrdId;
	private String RetUrl;
	private String BgRetUrl;
	private String UsrCustId;
	private String InUsrCustId;
	private String TransAmt;
	private String MerPriv;
	private String ReqExt;

	public String getOrdId() {
		return OrdId;
	}

	public void setOrdId(String ordId) {
		OrdId = ordId;
	}

	public String getRetUrl() {
		return RetUrl;
	}

	public void setRetUrl(String retUrl) {
		RetUrl = retUrl;
	}

	public String getBgRetUrl() {
		return BgRetUrl;
	}

	public void setBgRetUrl(String bgRetUrl) {
		BgRetUrl = bgRetUrl;
	}

	public String getUsrCustId() {
		return UsrCustId;
	}

	public void setUsrCustId(String usrCustId) {
		UsrCustId = usrCustId;
	}

	public String getInUsrCustId() {
		return InUsrCustId;
	}

	public void setInUsrCustId(String inUsrCustId) {
		InUsrCustId = inUsrCustId;
	}

	public String getTransAmt() {
		return TransAmt;
	}

	public void setTransAmt(String transAmt) {
		TransAmt = transAmt;
	}

	public String getMerPriv() {
		return MerPriv;
	}

	public void setMerPriv(String merPriv) {
		MerPriv = merPriv;
	}

	public String getReqExt() {
		return ReqExt;
	}

	public void setReqExt(String reqExt) {
		ReqExt = reqExt;
	}

	@Override
	public String getMerData() {
		List<String> fileNameList = Arrays.asList("Version", "CmdId", "OrdId",
				"UsrCustId", "MerCustId", "TransAmt", "InUsrCustId", "RetUrl",
				"BgRetUrl", "MerPriv", "ReqExt");
		return getMerData(fileNameList);
	}

}
