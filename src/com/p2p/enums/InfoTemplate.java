package com.p2p.enums;

/**
 * 信息模版
 * @author Administrator
 *
 */
public enum InfoTemplate {
	/**
	 * 注册验证码
	 */
	REG_CODE("注册验证码","注册验证码为:#regCode，请及时验证。"),
	
	/**
	 * 注册验证码
	 */
	FORGETPASS_CODE("忘记密码验证码","您的账号正在进行忘记密码操作，验证码为:#regCode，请及时验证。如不是本人操作，请及时与客服联系"),
	
	/**
	 * 借款申请验证码
	 */
	QUICK_BORROW_CODE("借款验证码","借款申请验证码为:#qbCode，请及时验证。"),
	/**
	 * 充值成功
	 */
	REARGE_SUCCESS("充值成功","您成功充值￥#rechargeAmount元，实际到账#realAmount元。"),
	
	/**
	 * 投资成功
	 */
	INVEST_BORROW("投资","您投资项目[#borrowTitle]成功，投资金额为#amount。"),
	/**
	 * 投标成功
	 */
	TENDER_SUCCESS("投标成功","您于[#times]投标成功,投标金额为#investAmount元。"),
	/**
	 * 投资成功
	 */
	INVEST_SUCCESS("投资成功","您投资的项目[#borrowTitle]已审核通过,投资金额为#investAmount元，投资期限：#dealine，预期总收益为#profitAmount元。"),
	
	/**
	 * 投资失败
	 */
	INVEST_FAIL("投标失败","您投资的项目[#borrowTitle]投标失败,解冻金额为#investAmount元。"),
	
	/**
	 * 借款成功
	 */
	BORROW_SUCCESS("借款成功","您借款项目[#borrowTitle]审核通过,成功借款金额为#borrowAmount元，借款期限：#dealine。"),
	/**
	 * 借款失败
	 */
	BORROW_FAIL("借款失败","您借款项目[#borrowTitle]审核不通过。"),
	
	/**
	 * 回款成功
	 */
	REPAY_SUCCESS("回款成功","您投资的项目[#borrowTitle]第#period期已回款，回款总额为#allAmount元。欢迎续投。"),
	
	/**
	 * 还款成功
	 */
	BORROW_REPAY_SUCCESS("还款成功","您的借款[#borrowTitle]第#period期已还款成功，还款总金额为#allAmount元。"),
	/**
	 * 提现申请
	 */
	WITHDRAW_APPLY("提现申请","您于申请了提现，提现金额为#amount元。"),
	/**
	 * 提现验证码
	 */
	WITHDRAW_CODE("提现验证码","提现验证码：${code},请及时验证。"),
	/**
	 * 提现已受理
	 */
	WITHDRAW_RECEIVED("提现已受理","您申请的一笔提现已受理，金额为：#amount元，到账金额为：#trueamount元，手续费为：#fee元。"),
	/**
	 * 提现成功
	 */
	WITHDRAW_SUCCESS("提现成功","您申请的一笔提现已成功，金额为：#amount元，到账金额为：#trueamount元，手续费为：#fee元。"),
	/**
	 * 提现失败
	 */
	WITHDRAW_ERROR("提现失败","您本次申请的提现已失败，解冻提现金额：#amount元，请登陆平台账户查收。"),
	;
	private String smsType;
	private String contentTemplate;
	
	private InfoTemplate(String smsType,String contentTemplate){
		this.smsType = smsType;
		this.contentTemplate = contentTemplate;
	}
	public String getSmsType() {
		return smsType;
	}
	
	public String getContentTemplate() {
		return contentTemplate;
	}
	
	
	
}
