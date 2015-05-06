package com.p2p.model;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;
import java.util.Date;

/** 帮助**/
@Table(name="c_help")
public class CHelp{
	/****/
	@Id
	private Long id;

	/**问题**/
	@Column
	private String questionName;

	/**答案**/
	@Column
	private String  answer;

	/**创建时间**/
	@Column
	private Date createTime;

	/**帮助类型ID**/
	@Column
	private Long helpTypeId;

	public void setId(Long id) {
		this.id=id;
	}

	public Long getId() {
		return this.id;
	}

	public void setQuestionName(String questionName) {
		this.questionName=questionName;
	}

	public String getQuestionName() {
		return this.questionName;
	}

	public void setAnswer(String  answer) {
		this.answer=answer;
	}

	public String  getAnswer() {
		return this.answer;
	}

	public void setCreateTime(Date createTime) {
		this.createTime=createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setHelpTypeId(Long helpTypeId) {
		this.helpTypeId=helpTypeId;
	}

	public Long getHelpTypeId() {
		return this.helpTypeId;
	}

}