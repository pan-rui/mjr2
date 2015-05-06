package com.p2p.model;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;

/** **/
@Table(name="t_contract")
public class TContract{
	/****/
	@Id
	private Long id;

	/**合同标题**/
	@Column
	private String contracttitle;

	/**合同类型（1：范本 2：空白）预留**/
	@Column
	private Integer contracttyp;

	/**合同内容**/
	@Column
	private String contractcontent;

	public void setId(Long id) {
		this.id=id;
	}

	public Long getId() {
		return this.id;
	}

	public void setContracttitle(String contracttitle) {
		this.contracttitle=contracttitle;
	}

	public String getContracttitle() {
		return this.contracttitle;
	}

	public void setContracttyp(Integer contracttyp) {
		this.contracttyp=contracttyp;
	}

	public Integer getContracttyp() {
		return this.contracttyp;
	}

	public void setContractcontent(String contractcontent) {
		this.contractcontent=contractcontent;
	}

	public String getContractcontent() {
		return this.contractcontent;
	}

}