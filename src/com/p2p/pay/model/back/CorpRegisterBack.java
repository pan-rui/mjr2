package com.p2p.pay.model.back;

import java.util.Arrays;
import java.util.List;

import com.p2p.pay.model.HFModelBack;

/**
 * 企业开户返回
 * 
 * @author Administrator
 * 
 */
public class CorpRegisterBack extends HFModelBack {

	private String UsrId;
	private String UsrName;
	private String UsrCustId;
	private String AuditStat;
	private String AuditDesc;
	private String MerPriv;
	private String TrxId;
	private String OpenBankId;
	private String CardId;
	private String BgRetUrl;
	private String RespExt;

	public String getUsrId() {
		return UsrId;
	}

	public void setUsrId(String usrId) {
		UsrId = usrId;
	}

	public String getUsrName() {
		return UsrName;
	}

	public void setUsrName(String usrName) {
		UsrName = usrName;
	}

	public String getUsrCustId() {
		return UsrCustId;
	}

	public void setUsrCustId(String usrCustId) {
		UsrCustId = usrCustId;
	}

	public String getAuditStat() {
		return AuditStat;
	}

	public void setAuditStat(String auditStat) {
		AuditStat = auditStat;
	}

	public String getAuditDesc() {
		return AuditDesc;
	}

	public void setAuditDesc(String auditDesc) {
		AuditDesc = auditDesc;
	}

	public String getMerPriv() {
		return MerPriv;
	}

	public void setMerPriv(String merPriv) {
		MerPriv = merPriv;
	}

	public String getTrxId() {
		return TrxId;
	}

	public void setTrxId(String trxId) {
		TrxId = trxId;
	}

	public String getOpenBankId() {
		return OpenBankId;
	}

	public void setOpenBankId(String openBankId) {
		OpenBankId = openBankId;
	}

	public String getCardId() {
		return CardId;
	}

	public void setCardId(String cardId) {
		CardId = cardId;
	}

	public String getBgRetUrl() {
		return BgRetUrl;
	}

	public void setBgRetUrl(String bgRetUrl) {
		BgRetUrl = bgRetUrl;
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
				"MerCustId", "UsrId", "UsrName", "UsrCustId", "AuditStat",
				"TrxId", "OpenBankId", "CardId", "BgRetUrl", "RespExt");
		return getMerData(fileNameList);
	}

}
