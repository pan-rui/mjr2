package com.p2p.enums;

public enum FundRecordType {
	RECHAGE("充值成功", 1,1,"成功充值，充值金额为#amount元。"), 
	
	WITHDRAW_FROZEN("提现冻结", 2,2,"提现冻结金额#amount元。"),
	WITHDRAW_SUCCESS_UNFREEZE("提现成功解冻", 3,3,"提现成功解冻金额#amount元。"),
	WITHDRAW_SUCCESS("提现成功", 4,4,"提现成功扣除金额#amount元。"),
	
	WITHDRAW_UNFREEZE("提现失败解冻", 5,3,"提现失败解冻金额#amount元。"),
	
	INVEST_FROZEN("投资冻结", 6,2,"投资了项目[#borrowTitle]，冻结投资金额为#amount元。"),
	INVEST_SUCCESS_UNFREEZE("投资成功解冻", 7,3,"投资项目[#borrowTitle]成功，解冻投资金额为#amount元。"),
	INVEST_SUCCESS("投资成功", 8,4,"投资项目[#borrowTitle]成功，扣除投资金额为#amount元。"),
	INVEST_UNFREEZE("投资失败解冻", 9,3,"投资项目[#borrowTitle]失败，解冻投资金额为#amount元。"),
	INVEST_MANAGER_FEE("居间管理费", 14,4,"投资项目[#borrowTitle]第#peroid期已回款，扣除居间管理费#amount元。"),
	
	BORROW_SUCCESS("借款成功", 10,1,"借款项目[#borrowTitle]成功,借款金额为#amount元。"),
	BORROW_REPAY("还款成功", 11,4,"借款项目[#borrowTitle]第#peroid期还款成功，还款本息总和为#allAmount元。"),
	
	REPAY_CAPITAL("回收本金", 12,1,"投资项目[#borrowTitle]第#peroid期已回款，回收本金#amount元。"),
	REPAY_PROFIT("回收收益", 13,1,"投资项目[#borrowTitle]第#peroid期已回款，回收收益#amount元。"),
	
	CLAIM_BUY_SUCCESS("受让债权成功", 13,4,"购买债权项目[#claimTime]成功，扣除受让金额为#amount元。"),
	CLAIM_SELL_SUCCESS("转让债权成功", 14,1,"转让债权项目[#claimTime]成功，添加转让金额为#amount元。"),
	CLAIM_MANAGE("转让债权手续费", 15,4,"转让债权项目[#claimTime]成功，扣除转让手续费金额为#amount元。"),
	MER_RED_TRANS("红包", 16,1,""),
	NEW_INVEST_AWARD("新手奖励", 17,1,"投资项目[#borrowTitle]还款成功，新手奖励利率为#rate%,奖励金额为#amount元。"),
	ADD_INVEST_AWARD("奖励加成", 18,1,"投资项目[#borrowTitle]还款成功，奖励加成利率为#rate%,奖励金额为#amount元。"),
	REFEREE_INCOME_AWARD("推荐收益奖励", 19,1,"推荐收益奖励金额为#amount元。"),
	WITHDRAW_SUCCESS_FEE("提现扣除手续费", 20,4,"提现成功扣除手续费金额#fee元。"),
	;
	private String fundMode;
	private int fundType;
	/**
	 * （1为添加可用余额，2，冻结可用余额，3为解冻可用余额，4为减少可用余额）
	 */
	private int operType;
	private String remarks;

	// 构造方法
	private FundRecordType(String fundMode, int fundType,int operType,String remarks) {
		this.fundMode = fundMode;
		this.fundType = fundType;
		this.operType = operType;
		this.remarks = remarks;
	}

	public String getFundMode() {
		return fundMode;
	}

	public int getFundType() {
		return fundType;
	}

	public int getOperType() {
		return operType;
	}

	public String getRemarks() {
		return remarks;
	}

	
	
	

}
