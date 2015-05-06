package com.p2p.pay.model.back;

import java.util.Arrays;
import java.util.List;

import com.p2p.pay.model.HFModelBack;

/**
 * 
 * @author Administrator
 * 
 */
public class CorpRegisterQueryBack extends HFModelBack {

	private String UsrCustId;
	private String UsrId;
	private String AuditStat;
	private String BusiCode;
	private String RespExt;

	public String getUsrCustId() {
		return UsrCustId;
	}

	public void setUsrCustId(String usrCustId) {
		UsrCustId = usrCustId;
	}

	public String getUsrId() {
		return UsrId;
	}

	public void setUsrId(String usrId) {
		UsrId = usrId;
	}

	public String getAuditStat() {
		return AuditStat;
	}

	public void setAuditStat(String auditStat) {
		AuditStat = auditStat;
	}

	public String getBusiCode() {
		return BusiCode;
	}

	public void setBusiCode(String busiCode) {
		BusiCode = busiCode;
	}

	public String getRespExt() {
		return RespExt;
	}

	public void setRespExt(String respExt) {
		RespExt = respExt;
	}

	@Override
	public String getMerData() {
		List<String> fileNameList = Arrays.asList("CmdId", "RespCode",
				"MerCustId", "UsrCustId", "UsrId", "AuditStat", "BusiCode",
				"RespExt");
		return getMerData(fileNameList);
	}

}
