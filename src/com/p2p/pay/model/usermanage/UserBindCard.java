package com.p2p.pay.model.usermanage;

import java.util.Arrays;
import java.util.List;

import com.p2p.pay.model.HFModel;

/**
 *  用户绑卡
 * 
 * @author Administrator
 * 
 */
public class UserBindCard extends HFModel {
	private String BgRetUrl;
	private String UsrCustId;
	private String MerPriv;

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

	public String getMerPriv() {
		return MerPriv;
	}

	public void setMerPriv(String merPriv) {
		MerPriv = merPriv;
	}

	@Override
	public String getMerData() {
		List<String> fileNameList = Arrays.asList("Version", "CmdId",
				"MerCustId", "UsrCustId", "BgRetUrl", "MerPriv");
		return getMerData(fileNameList);
	}

}
