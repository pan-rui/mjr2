package com.p2p.pay.model.query;

import java.util.Arrays;
import java.util.List;

import com.p2p.pay.model.HFModel;
/**
 * 商户子账户信息查询
 * @author Administrator
 *
 */
public class QueryAccts extends HFModel {
	@Override
	public String getMerData() {
		List<String> fileNameList = Arrays.asList("Version","CmdId","MerCustId");
		return getMerData(fileNameList);
	}
}
