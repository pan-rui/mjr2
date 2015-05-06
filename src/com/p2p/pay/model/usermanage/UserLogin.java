package com.p2p.pay.model.usermanage;

import java.util.Arrays;
import java.util.List;

import com.p2p.pay.model.HFModel;

/**
 * 用户登录
 * @author Administrator
 *
 */
public class UserLogin extends HFModel {
	private String UsrCustId;

	public String getUsrCustId() {
		return UsrCustId;
	}

	public void setUsrCustId(String usrCustId) {
		UsrCustId = usrCustId;
	}
	
	@Override
	public String getMerData() {
		List<String> fileNameList = Arrays.asList();
		return getMerData(fileNameList);
	}

}
