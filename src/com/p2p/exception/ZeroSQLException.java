package com.p2p.exception;

import com.p2p.dto.InfoMsg;

public class ZeroSQLException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8349019682525154649L;
	
	private InfoMsg infoMsg;
	
	public ZeroSQLException(){
		super("更新影响0条数据！");
	}

	public ZeroSQLException(InfoMsg infoMsg){
		super("更新影响0条数据！");
		this.infoMsg = infoMsg;
	}
	public InfoMsg getInfoMsg() {
		return infoMsg;
	}

	public void setInfoMsg(InfoMsg infoMsg) {
		this.infoMsg = infoMsg;
	}
	
	
}
