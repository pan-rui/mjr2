package com.p2p.pay.model.back;

import java.util.Arrays;
import java.util.List;

import com.p2p.pay.model.HFModelBack;

/**
 * 用户绑卡
 * 
 * @author Administrator
 * 
 */
public class UserBindCardBack extends HFModelBack {

	private String OpenAcctId;
	private String OpenBankId;
	private String UsrCustId;
	private String TrxId;
	private String BgRetUrl;
	private String MerPriv;

	public String getOpenAcctId() {
		return OpenAcctId;
	}

	public void setOpenAcctId(String openAcctId) {
		OpenAcctId = openAcctId;
	}

	public String getOpenBankId() {
		return OpenBankId;
	}

	public void setOpenBankId(String openBankId) {
		OpenBankId = openBankId;
	}

	public String getUsrCustId() {
		return UsrCustId;
	}

	public void setUsrCustId(String usrCustId) {
		UsrCustId = usrCustId;
	}

	public String getTrxId() {
		return TrxId;
	}

	public void setTrxId(String trxId) {
		TrxId = trxId;
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
		List<String> fileNameList = Arrays.asList("CmdId","RespCode","MerCustId","OpenAcctId","OpenBankId","UsrCustId","TrxId","BgRetUrl","MerPriv");
		return getMerData(fileNameList);
	}
}
