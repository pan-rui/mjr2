package com.p2p.pay.model.back;

import java.util.Arrays;
import java.util.List;

import com.p2p.pay.model.HFModelBack;

/**
 * 
 * @author Administrator
 * 
 */
public class QueryCardInfoBack extends HFModelBack {

	private String UsrCustId;
	private String CardId;
	private String UsrCardInfolist;

	public String getUsrCustId() {
		return UsrCustId;
	}

	public void setUsrCustId(String usrCustId) {
		UsrCustId = usrCustId;
	}

	public String getCardId() {
		return CardId;
	}

	public void setCardId(String cardId) {
		CardId = cardId;
	}

	public String getUsrCardInfolist() {
		return UsrCardInfolist;
	}

	public void setUsrCardInfolist(String usrCardInfolist) {
		UsrCardInfolist = usrCardInfolist;
	}

	@Override
	public String getMerData() {
		List<String> fileNameList = Arrays.asList("CmdId", "RespCode",
				"MerCustId", "UsrCustId", "CardId");
		return getMerData(fileNameList);
	}
}
