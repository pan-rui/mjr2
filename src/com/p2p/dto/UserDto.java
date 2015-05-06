package com.p2p.dto;

import java.util.Date;

/**
 * 用户表数据传输类
 * @author HXL
 *
 */
public class UserDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**用户名**/
	private String userName;

	/**邮箱地址**/
	private String email;

	/**用户密码**/
	private String pwd;
	
	/**注册用户，密码接收**/
	private String password;
	
	/**注册用户，重复密码**/
	private String password2;
	
	
	
	/**推荐人推荐编号**/
	private String refereeUser;

	/**手机号码**/
	private String cellPhone;

	/**是否禁用（0不禁用，1禁用）**/
	private Integer isEnable;

	/**注册IP**/
	private String regIp;

	/**最后登录Ip**/
	private String lastLoginIp;

	/**最后登录时间**/
	private Date lastLoginTime;

	/**用户来源 0-普通客户，1-cpa**/
	private Integer source;

	//前台验证码
	private String loginCode;
	
	//注册验证码
	private String regCode;
	//关联表字段
	/**真实姓名**/
	private String realName;

	/**身份证号**/
	private String cardNo;

	/**性别（1，男，2，女）**/
	private Integer sex;

	/**实名时间**/
	private Date pcreateTime;

	/**出生日期**/
	private Date birthDate;
	/**
	 * 是否实名成功
	 */
	private int isAuth;
	
	private Integer isOpen;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getRefereeUser() {
		return refereeUser;
	}

	public void setRefereeUser(String refereeUser) {
		this.refereeUser = refereeUser;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public Integer getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}

	public String getRegIp() {
		return regIp;
	}

	public void setRegIp(String regIp) {
		this.regIp = regIp;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLoginCode() {
		return loginCode;
	}

	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}

	public String getRegCode() {
		return regCode;
	}

	public void setRegCode(String regCode) {
		this.regCode = regCode;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Date getPcreateTime() {
		return pcreateTime;
	}

	public void setPcreateTime(Date pcreateTime) {
		this.pcreateTime = pcreateTime;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public int getIsAuth() {
		return isAuth;
	}

	public void setIsAuth(int isAuth) {
		this.isAuth = isAuth;
	}

	public Integer getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Integer isOpen) {
		this.isOpen = isOpen;
	}
	
	

}
