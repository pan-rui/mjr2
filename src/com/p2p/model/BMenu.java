package com.p2p.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;

/** 后台菜单表**/
@Table(name="b_menu")
public class BMenu implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1203743995895387081L;

	/****/
	@Id
	private Long id;

	/**名称**/
	@Column
	private String menuName;

	/**路径**/
	@Column
	private String url;

	/**级别菜单（1为1及，2为2级）**/
	@Column
	private Integer menuLevel;

	/**是否拦截（0不拦截，1拦截）**/
	@Column
	private Integer isIntercept;

	/****/
	@Column
	private Long parentId;
	
	private String parentName;
	
	public void setId(Long id) {
		this.id=id;
	}

	public Long getId() {
		return this.id;
	}

	public void setMenuName(String menuName) {
		this.menuName=menuName;
	}

	public String getMenuName() {
		return this.menuName;
	}

	public void setUrl(String url) {
		this.url=url;
	}

	public String getUrl() {
		return this.url;
	}

	public void setMenuLevel(Integer menuLevel) {
		this.menuLevel=menuLevel;
	}

	public Integer getMenuLevel() {
		return this.menuLevel;
	}

	public void setIsIntercept(Integer isIntercept) {
		this.isIntercept=isIntercept;
	}

	public Integer getIsIntercept() {
		return this.isIntercept;
	}

	public void setParentId(Long parentId) {
		this.parentId=parentId;
	}

	public Long getParentId() {
		return this.parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

}