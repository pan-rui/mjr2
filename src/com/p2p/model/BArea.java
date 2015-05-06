package com.p2p.model;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;

/** 地区表**/
@Table(name="b_area")
public class BArea{
	/****/
	@Id
	private Long id;

	/**区域代码**/
	@Column
	private String areaId;

	/**区域名称**/
	@Column
	private String areaName;

	/**父ID**/
	@Column
	private Long parentId;

	/**上级区域代码**/
	@Column
	private String parentAareaId;

	/**级别**/
	@Column
	private Integer areaLevel;

	public void setId(Long id) {
		this.id=id;
	}

	public Long getId() {
		return this.id;
	}

	public void setAreaId(String areaId) {
		this.areaId=areaId;
	}

	public String getAreaId() {
		return this.areaId;
	}

	public void setAreaName(String areaName) {
		this.areaName=areaName;
	}

	public String getAreaName() {
		return this.areaName;
	}

	public void setParentId(Long parentId) {
		this.parentId=parentId;
	}

	public Long getParentId() {
		return this.parentId;
	}

	public void setParentAareaId(String parentAareaId) {
		this.parentAareaId=parentAareaId;
	}

	public String getParentAareaId() {
		return this.parentAareaId;
	}

	public void setAreaLevel(Integer areaLevel) {
		this.areaLevel=areaLevel;
	}

	public Integer getAreaLevel() {
		return this.areaLevel;
	}

}