package com.p2p.pay.model.trade;

import java.util.Arrays;
import java.util.List;

import com.p2p.pay.model.HFModel;

/**
 * 取现复核接口
 * 
 * @author Administrator
 * 
 */
public class CashAudit extends HFModel {

	private String OrdId;
	private String UsrCustId;
	private String TransAmt;
	private String AuditFlag;
	private String RetUrl;
	private String BgRetUrl;
	private String MerPriv;

	public String getOrdId() {
		return OrdId;
	}

	public void setOrdId(String ordId) {
		OrdId = ordId;
	}

	public String getUsrCustId() {
		return UsrCustId;
	}

	public void setUsrCustId(String usrCustId) {
		UsrCustId = usrCustId;
	}

	public String getTransAmt() {
		return TransAmt;
	}

	public void setTransAmt(String transAmt) {
		TransAmt = transAmt;
	}

	public String getAuditFlag() {
		return AuditFlag;
	}

	public void setAuditFlag(String auditFlag) {
		AuditFlag = auditFlag;
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
				"MerCustId", "OrdId", "UsrCustId", "TransAmt", "AuditFlag",
				"RetUrl", "BgRetUrl", "MerPriv");
		return getMerData(fileNameList);
	}

}
