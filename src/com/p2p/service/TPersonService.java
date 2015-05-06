package com.p2p.service;

import java.util.Date;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baofoo.p2p.dto.receive.ResultDto;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dao.TPersonDao;
import com.p2p.dao.TThridAccountDao;
import com.p2p.dto.InfoMsg;
import com.p2p.exception.ZeroSQLException;
import com.p2p.model.TPerson;
import com.p2p.model.TThridAccount;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.util.IdcardValidator;


/** 实名认证**/
@Service
public class TPersonService extends BaseService {

	private static Log log = LogFactory.getLog(TPersonService.class);
	@Autowired
	private TPersonDao tPersonDao;

	@Autowired
	private TThridAccountDao tThridAccountDao;

	public long addTPerson(TPerson tPerson) {
		return tPersonDao.insert(tPerson);
	}

	public long updateTPerson(TPerson tPerson) {
		return tPersonDao.update(tPerson);
	}

	public TPerson getTPerson(TPerson tPerson) {
		return tPersonDao.get(tPerson);
	}

	public PageList queryTPersonPage(TPerson tPerson,Map<String, SearchOperator> options, PageBounds pageBounds) {
		return (PageList)tPersonDao.getAllBy(tPerson, options, pageBounds);
	}
	/**
	 * 根据用户id获取实名信息
	 * @param userId
	 * @return
	 */
	public TPerson getPersonByUserId(Long userId){
		return tPersonDao.getPersonByUserId(userId);
	}

	public TPerson verificationNewPersonIdcard(TPerson tPerson){
		return tPersonDao.verificationNewPersonIdcard(tPerson);
	}
	public Long updatePersonAuth(TPerson tPerson){
		return tPersonDao.updatePersonAuth(tPerson);
	};
	/**
	 * 判断身份证是否存在，是，修改。否，添加
	 * @param tPerson
	 * @return
	 */
	public InfoMsg addOrUpdatePerson(TPerson tPerson){
		long rest =-1;
		TPerson tptmd = verificationNewPersonIdcard(tPerson);
		TThridAccount tta = new TThridAccount();
		TThridAccount tThridAccount = tThridAccountDao.getThridAccountByUserId(tPerson.getUserId());
		tta.setId(tThridAccount.getId());
		tta.setThirdUserId("9800897"+tPerson.getUserId());
		rest = tThridAccountDao.update(tta);
		if(rest<0){
			InfoMsg msg = new InfoMsg("实名认证失败，请稍后重试","n");
			return msg;
		}
		if(tptmd!=null){
			InfoMsg msg = new InfoMsg("身份证号码已存在","n");
			return msg;
		}
		TPerson tptmd2 = getPersonByUserId(tPerson.getUserId());
	
		if(tptmd2!=null){
			tPerson.setId(tptmd2.getId());
			rest = updateTPerson(tPerson);
		}else{
			rest = addTPerson(tPerson);
		}
		if(rest<0){
			InfoMsg msg = new InfoMsg("实名认证失败，请稍后重试","n");
			return msg;
		}
		InfoMsg msg = new InfoMsg("ok","y");
		return msg;
		

	}
	/**
	 * 第三方账户开通，相应数据更新
	 * @param yesOrNo 是否成功，1成，2否
	 * @param resultDto 三方返回数据
	 * @return
	 */
	public long updateYesOrNo(int yesOrNo,ResultDto resultDto){
		long rest =-1;
		TThridAccount tta = new TThridAccount();
		TThridAccount tThridAccount = tThridAccountDao.getThridAccountByThirdUserId(resultDto.getUser_id());
		if(tThridAccount==null){
			log.error("用户id不存在："+resultDto.getUser_id());
			return rest;
		}
		TPerson tp = tPersonDao.getPersonByUserId(tThridAccount.getUserId());
		tp.setBirthDate(IdcardValidator.getBirthday(tp.getCardNo()));
		tp.setSex(IdcardValidator.isGrilOrboy(tp.getCardNo()));
		tp.setIsAuth(yesOrNo);
		tp.setRemark(resultDto.getMsg());
		//tp.setUserId(tThridAccount.getUserId());
		tp.setCreateTime(new Date());
		rest = updatePersonAuth(tp);
		if(rest <=0){
			throw new ZeroSQLException();
		}
		tta.setUserId(tThridAccount.getUserId());
		tta.setIsOpen(yesOrNo);
		tta.setOpenTime(new Date());
		rest = tThridAccountDao.updateThridOpenOrNo(tta);
		if(rest <=0){
			throw new ZeroSQLException();
		}
		return rest;
	}
}