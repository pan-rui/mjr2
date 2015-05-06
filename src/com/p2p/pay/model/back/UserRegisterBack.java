package com.p2p.pay.model.back;

import java.util.Arrays;
import java.util.List;

import com.p2p.pay.model.HFModelBack;

/**
 * 用户开户
 * 
 * @author Administrator
 * 
 */
public class UserRegisterBack extends HFModelBack {

	private String UsrId;
	private String UsrCustId;
	private String BgRetUrl;
	private String TrxId;
	private String RetUrl;
	private String MerPriv;
	private String IdType;
	private String IdNo;
	private String UsrMp;
	private String UsrEmail;
	private String UsrName;

	public String getUsrId() {
		return UsrId;
	}

	public void setUsrId(String usrId) {
		UsrId = usrId;
	}

	public String getUsrCustId() {
		return UsrCustId;
	}

	public void setUsrCustId(String usrCustId) {
		UsrCustId = usrCustId;
	}

	public String getBgRetUrl() {
		return BgRetUrl;
	}

	public void setBgRetUrl(String bgRetUrl) {
		BgRetUrl = bgRetUrl;
	}

	public String getTrxId() {
		return TrxId;
	}

	public void setTrxId(String trxId) {
		TrxId = trxId;
	}

	public String getRetUrl() {
		return RetUrl;
	}

	public void setRetUrl(String retUrl) {
		RetUrl = retUrl;
	}

	public String getMerPriv() {
		return MerPriv;
	}

	public void setMerPriv(String merPriv) {
		MerPriv = merPriv;
	}

	public String getIdType() {
		return IdType;
	}

	public void setIdType(String idType) {
		IdType = idType;
	}

	public String getIdNo() {
		return IdNo;
	}

	public void setIdNo(String idNo) {
		IdNo = idNo;
	}

	public String getUsrMp() {
		return UsrMp;
	}

	public void setUsrMp(String usrMp) {
		UsrMp = usrMp;
	}

	public String getUsrEmail() {
		return UsrEmail;
	}

	public void setUsrEmail(String usrEmail) {
		UsrEmail = usrEmail;
	}

	public String getUsrName() {
		return UsrName;
	}

	public void setUsrName(String usrName) {
		UsrName = usrName;
	}

	@Override
	public String getMerData() {
		List<String> fileNameList = Arrays.asList("CmdId","RespCode","MerCustId","UsrId","UsrCustId","BgRetUrl","TrxId","RetUrl","MerPriv");
		return getMerData(fileNameList);
	}
}
