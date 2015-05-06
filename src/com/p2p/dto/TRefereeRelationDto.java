package com.p2p.dto;

import java.util.Date;

public class TRefereeRelationDto extends BaseDto{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 被推荐人ID**/
	private Long userId;
	private String userName;
	private String cellPhone;
	private String realName;
	/**推荐人ID**/
	private Long refereeId;
	private String tUserName;
	private String tCellPhone;
	private String tRealName;
	/**创建时间**/
	private Date createTime;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
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
	public Long getRefereeId() {
		return refereeId;
	}
	public void setRefereeId(Long refereeId) {
		this.refereeId = refereeId;
	}
	public String gettUserName() {
		return tUserName;
	}
	public void settUserName(String tUserName) {
		this.tUserName = tUserName;
	}
	public String gettCellPhone() {
		return tCellPhone;
	}
	public void settCellPhone(String tCellPhone) {
		this.tCellPhone = tCellPhone;
	}
	public String gettRealName() {
		return tRealName;
	}
	public void settRealName(String tRealName) {
		this.tRealName = tRealName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
