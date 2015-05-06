package com.p2p.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class CalculateInterestUtil {

	private static final BigDecimal MONTHS = new BigDecimal("12");
	private static final BigDecimal YEAR_DAY = new BigDecimal("365");
	private static final BigDecimal HUNDRED = new BigDecimal("100");
	private static final int SCALE = 2;
	private static final int FOUR_SCALE = 4;
	private static final int VALUE_SCALE = 2;

	/**
	 * 按月还款（等额本息还款法）计算每月还款金额 Aβ(1+β)^m/[(1+β)^m-1]
	 * 
	 * @param money
	 *            金额
	 * @param month
	 *            月份
	 * @param annualRate
	 *            年利率
	 * @return
	 */
	public static BigDecimal calculateEqualMonthly(BigDecimal money, Integer month,
			BigDecimal annualRate) {

		BigDecimal bdMonthRate = annualRate.divide(MONTHS, SCALE,
				RoundingMode.HALF_UP).divide(HUNDRED,FOUR_SCALE, RoundingMode.HALF_UP);
		BigDecimal bdMonthAddRate = BigDecimal.ONE.add(bdMonthRate);
		BigDecimal monthlyPayments = money
				.multiply(bdMonthRate)
				.multiply(bdMonthAddRate.pow(month))
				.divide(bdMonthAddRate.pow(month).subtract(BigDecimal.ONE),
						SCALE, RoundingMode.HALF_UP);
		monthlyPayments.setScale(VALUE_SCALE, RoundingMode.HALF_UP);
		return monthlyPayments;
	}
	
	/**
	 * 按月还款（等额本息还款法）计算每月还款金额 Aβ(1+β)^m/[(1+β)^m-1]
	 * 
	 * @param money
	 *            金额
	 * @param month
	 *            月份
	 * @param annualRate
	 *            年利率
	 * @return
	 */
	public static List<Map<String,Object>> calculateEqualMonthlyList(BigDecimal money, Integer month,
			BigDecimal annualRate) {
		BigDecimal bdMonth = new BigDecimal(Integer.toString(month));
		BigDecimal bdMonthRate = annualRate.divide(MONTHS, FOUR_SCALE,
				RoundingMode.HALF_UP).divide(HUNDRED,FOUR_SCALE, RoundingMode.HALF_UP);
		BigDecimal bdMonthAddRate = BigDecimal.ONE.add(bdMonthRate);
		BigDecimal monthlyPayments = money
				.multiply(bdMonthRate)
				.multiply(bdMonthAddRate.pow(month))
				.divide(bdMonthAddRate.pow(month).subtract(BigDecimal.ONE),
						SCALE, RoundingMode.HALF_UP);
		
		
		
		List<Map<String,Object>> repayList = new ArrayList<Map<String,Object>>();
		Date nowDate = new Date();
		Date startInterestDate = DateUtil.dateAddDay(nowDate, 1);
		BigDecimal capitalAmount = null;
		BigDecimal profitAmount = null;
		BigDecimal remainCapitalAmount = money;
		BigDecimal remainProfitAmount = bdMonth.multiply(monthlyPayments).subtract(money).setScale(VALUE_SCALE, RoundingMode.HALF_UP);
		
//		等额本息还贷第n个月还贷本金：
//		B=a*i(1+i)^(n-1)/[(1+i)^N-1]
		for(int i=1;i<=month;i++){
			Map<String,Object> repayMap = new HashMap<String, Object>();
			repayMap.put("period", i);
			profitAmount = calculateOneRepay(remainCapitalAmount, 1, annualRate, false);
			capitalAmount = monthlyPayments.subtract(profitAmount);
			remainCapitalAmount = remainCapitalAmount.subtract(capitalAmount);
			remainProfitAmount = remainProfitAmount.subtract(profitAmount);
//			remainCapitalAndProfitAmount = remainCapitalAndProfitAmount.subtract(monthlyPayments);
			if(i==month){
				
				remainCapitalAmount = BigDecimal.ZERO;
				remainProfitAmount = BigDecimal.ZERO;
				
			}
			repayMap.put("capitalAmount", capitalAmount);
			repayMap.put("profitAmount", profitAmount);
			repayMap.put("remainCapitalAmount", remainCapitalAmount);
			repayMap.put("remainProfitAmount", remainProfitAmount);
			repayMap.put("repayDate", DateUtil.dateAddMonth(startInterestDate, i));
			repayList.add(repayMap);
		}
		return repayList;
	}
	
	/**
	 * 一次性还款
	 * 
	 * @param money
	 *            金额
	 * @param month
	 *            月份
	 * @param annualRate
	 *            年利率
	 * @param isDay 是否天标，true是，否则为否。
	 * @return
	 */
	public static  List<Map<String,Object>>  calculateOneRepayList(BigDecimal money, Integer month,
			BigDecimal annualRate,boolean isDay) {
		List<Map<String,Object>> repayList = new ArrayList<Map<String,Object>>();
		if(isDay){
			BigDecimal bdDay = new BigDecimal(Integer.toString(month));
			BigDecimal profitAmount = money.multiply(annualRate).multiply(bdDay).divide(YEAR_DAY, SCALE,
					RoundingMode.HALF_UP).divide(HUNDRED,SCALE, RoundingMode.HALF_UP);
			Date nowDate = new Date();
			Date startInterestDate = DateUtil.dateAddDay(nowDate, 1);
			
			Map<String,Object> repayMap = new HashMap<String, Object>();
			repayMap.put("period", 	1);
			repayMap.put("capitalAmount", money);
			repayMap.put("profitAmount", profitAmount);
			repayMap.put("remainCapitalAmount", BigDecimal.ZERO);
			repayMap.put("remainProfitAmount", BigDecimal.ZERO);
			repayMap.put("repayDate", DateUtil.dateAddDay(startInterestDate, month));
			repayList.add(repayMap);
		}else{
			BigDecimal bdMonth = new BigDecimal(Integer.toString(month));
			BigDecimal profitAmount = money.multiply(annualRate).multiply(bdMonth).divide(MONTHS, SCALE,
					RoundingMode.HALF_UP).divide(HUNDRED,SCALE, RoundingMode.HALF_UP);
			Date nowDate = new Date();
			Date startInterestDate = DateUtil.dateAddDay(nowDate, 1);
			
			Map<String,Object> repayMap = new HashMap<String, Object>();
			repayMap.put("period", 	1);
			repayMap.put("capitalAmount", money);
			repayMap.put("profitAmount", profitAmount);
			repayMap.put("remainCapitalAmount", BigDecimal.ZERO);
			repayMap.put("remainProfitAmount", BigDecimal.ZERO);
			repayMap.put("repayDate", DateUtil.dateAddMonth(startInterestDate, month));
			repayList.add(repayMap);
		}
		return repayList;
	}
	
	/**
	 * 按月付息，到期还本
	 * 
	 * @param money
	 *            金额
	 * @param month
	 *            月份
	 * @param annualRate
	 *            年利率
	 * @param isDay 是否天标，true是，否则为否。
	 * @return
	 */
	public static  List<Map<String,Object>>  calculateMonthRepayInterestList(BigDecimal money, Integer month,
			BigDecimal annualRate) {
		BigDecimal profitMonthAmount = money.multiply(annualRate).divide(MONTHS, SCALE,
				RoundingMode.HALF_UP).divide(HUNDRED,SCALE, RoundingMode.HALF_UP);
		Date nowDate = new Date();
		Date startInterestDate = DateUtil.dateAddDay(nowDate, 1);
		List<Map<String,Object>> repayList = new ArrayList<Map<String,Object>>();
		
		for(int i=1;i<=month;i++){
			Map<String,Object> repayMap = new HashMap<String, Object>();
			repayMap.put("period", 	i);
			repayMap.put("profitAmount", profitMonthAmount);
			
			if(i==month){
				repayMap.put("capitalAmount", money);
				repayMap.put("remainCapitalAmount", BigDecimal.ZERO);
				repayMap.put("remainProfitAmount", BigDecimal.ZERO);
			}else{
				repayMap.put("capitalAmount", BigDecimal.ZERO);
				repayMap.put("remainCapitalAmount",money);
				repayMap.put("remainProfitAmount", profitMonthAmount.multiply(new BigDecimal(Integer.toString(month-i))));
			}
			repayMap.put("repayDate", DateUtil.dateAddMonth(startInterestDate, i));
			repayList.add(repayMap);
		}
		return repayList;
	}
	
	/**
	 * 计算一次性还款利息
	 * 
	 * @param money
	 *            金额
	 * @param month
	 *            月份
	 * @param annualRate
	 *            年利率
	 * @param isDay 是否天标，true是，否则为否。
	 * @return
	 */
	public static BigDecimal calculateOneRepay(BigDecimal money, Integer month,
			BigDecimal annualRate,boolean isDay) {
		BigDecimal profitAmount = null;
		if(isDay){
			BigDecimal bdDay = new BigDecimal(Integer.toString(month));
			profitAmount = money.multiply(annualRate).multiply(bdDay).divide(YEAR_DAY, SCALE,
					RoundingMode.HALF_UP).divide(HUNDRED,SCALE, RoundingMode.HALF_UP);
		}else{
			BigDecimal bdMonth = new BigDecimal(Integer.toString(month));
			profitAmount = money.multiply(annualRate).multiply(bdMonth).divide(MONTHS, SCALE,
					RoundingMode.HALF_UP).divide(HUNDRED,SCALE, RoundingMode.HALF_UP);
		}
		
		
		return profitAmount;
	}
	
	/**
	 * 等额本息总还款利息 Aβ(1+β)^m/[(1+β)^m-1]
	 * 
	 * @param money
	 *            金额
	 * @param month
	 *            月份
	 * @param annualRate
	 *            年利率
	 * @return
	 */
	public static BigDecimal calculateEqualMonthlyInterest(BigDecimal money, Integer month,
			BigDecimal annualRate) {

		BigDecimal bdMonthRate = annualRate.divide(MONTHS, SCALE,
				RoundingMode.HALF_UP).divide(HUNDRED,FOUR_SCALE, RoundingMode.HALF_UP);
		BigDecimal bdMonthAddRate = BigDecimal.ONE.add(bdMonthRate);
		BigDecimal monthlyPayments = money
				.multiply(bdMonthRate)
				.multiply(bdMonthAddRate.pow(month))
				.divide(bdMonthAddRate.pow(month).subtract(BigDecimal.ONE),
						SCALE, RoundingMode.HALF_UP);
		BigDecimal interest = monthlyPayments.multiply(new BigDecimal(month)).subtract(money);
		interest.setScale(VALUE_SCALE, RoundingMode.HALF_UP);
		return interest;
	}
	
	/**
	 * 计算年化利率
	 * @param profitAmount
	 * @param money
	 * @param days
	 * @return
	 */
	public static BigDecimal calculateAnnualRate(BigDecimal profitAmount,BigDecimal money,int days){
		BigDecimal bdDays = new BigDecimal(Integer.toString(days));
		BigDecimal annualRate = profitAmount.multiply(YEAR_DAY).multiply(HUNDRED).divide(bdDays, SCALE,
				RoundingMode.HALF_UP).divide(money,SCALE, RoundingMode.HALF_UP);
		return annualRate;
	}
	

	@Test
	public void testMonthlyPayments() {
		//等额还款
		List<Map<String,Object>> repayList = calculateEqualMonthlyList(new BigDecimal("33323.3"), 3, new BigDecimal("18"));
		for(Map<String,Object> map : repayList){
			System.out.println(map);
		}
		System.out.println("=================");
		repayList = calculateMonthRepayInterestList(new BigDecimal("20000"), 5, new BigDecimal("18"));
		for(Map<String,Object> map : repayList){
			System.out.println(map);
		}
		
		System.out.println("======一次性===========");
		repayList = calculateOneRepayList(new BigDecimal("20000"), 5, new BigDecimal("18"),true);
		for(Map<String,Object> map : repayList){
			System.out.println(map);
		}
//		System.out.println(calculateEqualMonthly(new BigDecimal("2000"), 2, new BigDecimal("0.18")));
		
		System.out.println(calculateAnnualRate(new BigDecimal("1200"), new BigDecimal("10000"), 180));
	}
}
