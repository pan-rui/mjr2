package com.p2p.pay.model.back;

import java.util.Arrays;
import java.util.List;

import com.p2p.pay.model.HFModelBack;

/**
 * 
 * @author Administrator
 * 
 */
public class UsrTransferBack extends HFModelBack {

	private String OrdId;
	private String RetUrl;
	private String BgRetUrl;
	private String UsrCustId;
	private String InUsrCustId;
	private String TransAmt;
	private String MerPriv;
	private String RespExt;

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

	public String getRespExt() {
		return RespExt;
	}

	public void setRespExt(String respExt) {
		RespExt = respExt;
	}

	@Override
	public String getMerData() {
		List<String> fileNameList = Arrays.asList("CmdId", "RespCode", "OrdId",
				"MerCustId", "UsrCustId", "TransAmt", "InUsrCustId", "RetUrl",
				"BgRetUrl", "MerPriv", "RespExt");
		return getMerData(fileNameList);
	}

}
