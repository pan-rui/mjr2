package com.p2p.pay.model.trade;

import java.util.Arrays;
import java.util.List;

import com.p2p.pay.model.HFModel;

/**
 * 商户无卡代扣充值
 * 
 * @author Administrator
 * 
 */
public class PosWhSave extends HFModel {

	private String UsrCustId;
	private String OpenAcctId;
	private String TransAmt;
	private String OrdId;
	private String OrdDate;
	private String CheckDate;
	private String RetUrl;
	private String BgRetUrl;
	private String MerPriv;

	public String getUsrCustId() {
		return UsrCustId;
	}

	public void setUsrCustId(String usrCustId) {
		UsrCustId = usrCustId;
	}

	public String getOpenAcctId() {
		return OpenAcctId;
	}

	public void setOpenAcctId(String openAcctId) {
		OpenAcctId = openAcctId;
	}

	public String getTransAmt() {
		return TransAmt;
	}

	public void setTransAmt(String transAmt) {
		TransAmt = transAmt;
	}

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

	public String getCheckDate() {
		return CheckDate;
	}

	public void setCheckDate(String checkDate) {
		CheckDate = checkDate;
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

	public String getMerPriv() {
		return MerPriv;
	}

	public void setMerPriv(String merPriv) {
		MerPriv = merPriv;
	}

	@Override
	public String getMerData() {
		List<String> fileNameList = Arrays.asList("Version", "CmdId",
				"MerCustId", "UsrCustId", "OpenAcctId", "TransAmt", "OrdId",
				"OrdDate", "CheckDate", "RetUrl", "BgRetUrl", "MerPriv");
		return getMerData(fileNameList);
	}

}
