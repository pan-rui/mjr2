package com.p2p.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UserMoneyDto implements Serializable{
	
	
	private Long id;
	/**用户名**/
	private String userName;


	/**手机号码**/
	private String cellPhone;

	
	//关联表字段
	/**真实姓名**/
	private String realName;

	

	/**性别（1，男，2，女）**/
	private Integer sex;
	
	private BigDecimal usableAmount;
	private BigDecimal frozenAmount;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCellPhone() {
		return cellPhone;
	}
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public BigDecimal getUsableAmount() {
		return usableAmount;
	}
	public void setUsableAmount(BigDecimal usableAmount) {
		this.usableAmount = usableAmount;
	}
	public BigDecimal getFrozenAmount() {
		return frozenAmount;
	}
	public void setFrozenAmount(BigDecimal frozenAmount) {
		this.frozenAmount = frozenAmount;
	}
	
	
}
