package com.p2p.pay.model.usermanage;

import java.util.Arrays;
import java.util.List;

import com.p2p.pay.model.HFModel;

/**
 * 删除银行卡
 * 
 * @author Administrator
 * 
 */
public class DelCard extends HFModel {
	private String UsrCustId;
	private String CardId;

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

	@Override
	public String getMerData() {
		List<String> fileNameList = Arrays.asList("Version", "CmdId",
				"MerCustId", "UsrCustId", "CardId");
		return getMerData(fileNameList);
	}
}
