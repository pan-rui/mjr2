package com.p2p.model;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;
import java.util.Date;

/** 借款人工作情况 **/
@Table(name = "t_job_borrower")
public class TJobBorrower {
	/****/
	@Id
	private Long id;

	/** 工作省份 **/
	@Column
	private String jobProvince;

	/** 工作城市 **/
	@Column
	private String jobCity;

	/** 工作年限 **/
	@Column
	private String jobYear;

	/** 公司行业 **/
	@Column
	private String companyIndustry;

	/** 公司性质 **/
	@Column
	private String companyNature;

	/** 岗位或职位 **/
	@Column
	private String position;

	/** 创建时间 **/
	@Column
	private Date createTime;

	/** 借款人信息ID **/
	@Column
	private Long personBorrowerId;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setJobProvince(String jobProvince) {
		this.jobProvince = jobProvince;
	}

	public String getJobProvince() {
		return this.jobProvince;
	}

	public void setJobCity(String jobCity) {
		this.jobCity = jobCity;
	}

	public String getJobCity() {
		return this.jobCity;
	}

	public void setJobYear(String jobYear) {
		this.jobYear = jobYear;
	}

	public String getJobYear() {
		return this.jobYear;
	}

	public void setCompanyIndustry(String companyIndustry) {
		this.companyIndustry = companyIndustry;
	}

	public String getCompanyIndustry() {
		return this.companyIndustry;
	}

	public void setCompanyNature(String companyNature) {
		this.companyNature = companyNature;
	}

	public String getCompanyNature() {
		return this.companyNature;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPosition() {
		return this.position;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public Long getPersonBorrowerId() {
		return personBorrowerId;
	}

	public void setPersonBorrowerId(Long personBorrowerId) {
		this.personBorrowerId = personBorrowerId;
	}

}