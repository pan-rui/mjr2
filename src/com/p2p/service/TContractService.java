package com.p2p.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dao.TContractDao;
import com.p2p.model.TContract;
import com.p2p.mybatis.annotion.SearchOperator;


/** **/
@Service
public class TContractService extends BaseService {

	private static Log log = LogFactory.getLog(TContractService.class);
	@Autowired
	private TContractDao tContractDao;
 
	public long addTContract(TContract tContract) {
		return tContractDao.insert(tContract);
	}
 
	public long updateTContract(TContract tContract) {
		return tContractDao.update(tContract);
	}
 
	public TContract getTContract(TContract tContract) {
		return tContractDao.get(tContract);
	}
 
	public PageList queryTContractPage(TContract tContract,Map<String, SearchOperator> options, PageBounds pageBounds) {
		return (PageList)tContractDao.getAllBy(tContract, options, pageBounds);
	}
	
	public List<TContract> selectcontracttitle(){
		return tContractDao.selectcontracttitle();
	}
 }