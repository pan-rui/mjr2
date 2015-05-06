package com.p2p.model;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;

/** 参数值**/
@Table(name="b_para")
public class BPara{
	/****/
	@Id
	private Long id;

	/**名称**/
	@Column
	private String paraName;

	/**编码Code**/
	@Column
	private String paraCode;

	/**key**/
	@Column
	private String paraKey;

	/**值**/
	@Column
	private String paraValue;

	/**是否删除（0为未删除，1为已删除）**/
	@Column
	private Integer isDelete;

	public void setId(Long id) {
		this.id=id;
	}

	public Long getId() {
		return this.id;
	}

	public void setParaName(String paraName) {
		this.paraName=paraName;
	}

	public String getParaName() {
		return this.paraName;
	}

	public void setParaCode(String paraCode) {
		this.paraCode=paraCode;
	}

	public String getParaCode() {
		return this.paraCode;
	}

	public void setParaKey(String paraKey) {
		this.paraKey=paraKey;
	}

	public String getParaKey() {
		return this.paraKey;
	}

	public void setParaValue(String paraValue) {
		this.paraValue=paraValue;
	}

	public String getParaValue() {
		return this.paraValue;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete=isDelete;
	}

	public Integer getIsDelete() {
		return this.isDelete;
	}

}