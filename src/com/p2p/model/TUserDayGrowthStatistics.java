package com.p2p.model;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/** 每日用户注册统计**/
@Table(name="t_user_day_growth_statistics")
public class TUserDayGrowthStatistics{
	/**id**/
	@Id
	private Long id;

	/**统计日期**/
	@Column
	private Date statisticsTime;
	/**搜索起始时间**/
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date starTime;
	/**搜索结束时间**/
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endTime;
	
	/**当日新注册用户数**/
	@Column
	private Integer newUserNums;

	/**用户总人数**/
	@Column
	private Integer usersNumber;

	/**当日实名人数**/
	@Column
	private Integer newPersonNums;

	/**总实名用户数**/
	@Column
	private Integer personNumber;

	/**当日投资人数**/
	@Column
	private Integer newInvestNums;

	/**总投资人数**/
	@Column
	private Integer investNumber;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setStatisticsTime(Date statisticsTime) {
		this.statisticsTime=statisticsTime;
	}

	public Date getStatisticsTime() {
		return this.statisticsTime;
	}

	public Date getStarTime() {
		return starTime;
	}

	public void setStarTime(Date starTime) {
		this.starTime = starTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public void setNewUserNums(Integer newUserNums) {
		this.newUserNums=newUserNums;
	}

	public Integer getNewUserNums() {
		return this.newUserNums;
	}

	public void setUsersNumber(Integer usersNumber) {
		this.usersNumber=usersNumber;
	}

	public Integer getUsersNumber() {
		return this.usersNumber;
	}

	public void setNewPersonNums(Integer newPersonNums) {
		this.newPersonNums=newPersonNums;
	}

	public Integer getNewPersonNums() {
		return this.newPersonNums;
	}

	public void setPersonNumber(Integer personNumber) {
		this.personNumber=personNumber;
	}

	public Integer getPersonNumber() {
		return this.personNumber;
	}

	public void setNewInvestNums(Integer newInvestNums) {
		this.newInvestNums=newInvestNums;
	}

	public Integer getNewInvestNums() {
		return this.newInvestNums;
	}

	public void setInvestNumber(Integer investNumber) {
		this.investNumber=investNumber;
	}

	public Integer getInvestNumber() {
		return this.investNumber;
	}

}