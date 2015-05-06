package com.p2p.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author HXL
 * 
 */
public class UserInvestLevelDto extends BaseDto {

	/**
	 * 奖励级别V1,V2,V3
	 */
	private String awardLevel;
	/**
	 * 奖励加成利率
	 */
	private BigDecimal awardRate;

	/**
	 * 待收金额
	 */
	private BigDecimal dueinAmount;

	public String getAwardLevel() {
		return awardLevel;
	}

	public void setAwardLevel(String awardLevel) {
		this.awardLevel = awardLevel;
	}

	public BigDecimal getAwardRate() {
		return awardRate;
	}

	public void setAwardRate(BigDecimal awardRate) {
		this.awardRate = awardRate;
	}

	public BigDecimal getDueinAmount() {
		return dueinAmount;
	}

	public void setDueinAmount(BigDecimal dueinAmount) {
		this.dueinAmount = dueinAmount;
	}

}
