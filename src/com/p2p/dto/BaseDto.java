package com.p2p.dto;

import java.io.Serializable;
import java.util.Date;

public class BaseDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3601615152795256910L;
	/**
	 * 数据序号，公共字段
	 */
	private Long id;
	/**
	 * 创建时间，大众字段
	 */
	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
