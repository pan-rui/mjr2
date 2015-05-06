package com.p2p.model;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;
import java.util.Date;

/** 消息表**/
@Table(name="c_message")
public class CMessage{
	/****/
	@Id
	private Long id;

	/**发送者ID**/
	@Column
	private Long senderId;

	/**接收者ID**/
	@Column
	private Long receiverId;

	/**消息标题**/
	@Column
	private String messageTitle;

	/**消息内容**/
	@Column
	private String messageContent;

	/**发送时间**/
	@Column
	private Date createTime;

	/**是否删除（0，否，1是）**/
	@Column
	private Integer isDelete;

	/**消息类型（1，发送，2接收）**/
	@Column
	private Integer messageType;

	public void setId(Long id) {
		this.id=id;
	}

	public Long getId() {
		return this.id;
	}

	public void setSenderId(Long senderId) {
		this.senderId=senderId;
	}

	public Long getSenderId() {
		return this.senderId;
	}

	public void setReceiverId(Long receiverId) {
		this.receiverId=receiverId;
	}

	public Long getReceiverId() {
		return this.receiverId;
	}

	public void setMessageTitle(String messageTitle) {
		this.messageTitle=messageTitle;
	}

	public String getMessageTitle() {
		return this.messageTitle;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent=messageContent;
	}

	public String getMessageContent() {
		return this.messageContent;
	}

	public void setCreateTime(Date createTime) {
		this.createTime=createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete=isDelete;
	}

	public Integer getIsDelete() {
		return this.isDelete;
	}

	public void setMessageType(Integer messageType) {
		this.messageType=messageType;
	}

	public Integer getMessageType() {
		return this.messageType;
	}

}