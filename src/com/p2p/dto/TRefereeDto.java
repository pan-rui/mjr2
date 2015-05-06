package com.p2p.dto;

import java.io.Serializable;

public class TRefereeDto implements Serializable{
	private Long investorId;
	private Long refereeId;
	
	public Long getInvestorId() {
		return investorId;
	}
	public void setInvestorId(Long investorId) {
		this.investorId = investorId;
	}
	public Long getRefereeId() {
		return refereeId;
	}
	public void setRefereeId(Long refereeId) {
		this.refereeId = refereeId;
	}
	
	
	
}
