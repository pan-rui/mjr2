package com.p2p.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baofoo.p2p.dto.receive.BankDto;
import com.baofoo.p2p.dto.receive.ResultDto;
import com.baofoo.p2p.util.CommonUtil;
import com.baofoo.p2p.util.Constant;
import com.baofoo.p2p.util.XMLBuild;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.constans.BaofooConstans;
import com.p2p.dao.TBankCardDao;
import com.p2p.dao.TThridAccountDao;
import com.p2p.dto.InfoMsg;
import com.p2p.dto.TBankCardDto;
import com.p2p.exception.ZeroSQLException;
import com.p2p.model.TBankCard;
import com.p2p.model.TThridAccount;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.security.AES;


/** 银行卡信息**/
@Service
public class TBankCardService extends BaseService {

	private static Log log = LogFactory.getLog(TBankCardService.class);
	@Autowired
	private TBankCardDao tBankCardDao;
	
	@Autowired
	private TThridAccountDao tThridAccountDao;
	
	public long addTBankCard(TBankCard tBankCard) {
		return tBankCardDao.insert(tBankCard);
	}

	public long updateTBankCard(TBankCard tBankCard) {
		return tBankCardDao.update(tBankCard);
	}

	public TBankCard getTBankCard(TBankCard tBankCard) {
		return tBankCardDao.get(tBankCard);
	}

	public PageList queryTBankCardPage(TBankCard tBankCard,Map<String, SearchOperator> options, PageBounds pageBounds) {
		return (PageList)tBankCardDao.getAllBy(tBankCard, options, pageBounds);
	}

	public PageList selectTBankCardList(TBankCardDto tbankCardDto,PageBounds pageBounds){
		return (PageList)tBankCardDao.selectTBankCardList(tbankCardDto, pageBounds);
	}
	public TBankCardDto selectTBankCardDeatil(Long id){
		return tBankCardDao.selectTBankCardDeatil(id);
	}
	/**
	 * 查询第三方，用户银行卡绑定情况
	 * @param baofooUserId 用户id
	 */
	public void selectBaofooBankCard(Long userId){
		 TThridAccount thd = tThridAccountDao.getThridAccountByUserId(userId);
			log.info("获取用户银行卡信息，请求参数："+thd.getThirdUserId());
			List<BankDto> userBanklist = null;
			try {
				String xml = BaofooConstans.REQUEST_SERVICE.serv_BankCardList(thd.getThirdUserId());
				if(xml.contains("CSD000")){
					userBanklist = BaofooConstans.RECEIVE_SERVICE.serv_BankCardList(xml);
				}
			} catch (Exception e) {
				log.info("error",e);
			}
				
			
			if(userBanklist!=null && userBanklist.size()>=1){
					List<String> strBanklist = tBankCardDao.querybankCardNoListByUserId(userId);
					if(strBanklist==null || strBanklist.size()<1){
						for (BankDto bankDto : userBanklist) {
							addUserBankCount(bankDto,userId);
						}
					}else{
						for (BankDto bankDto2 : userBanklist) {
							if(!strBanklist.contains(bankDto2.getBank_no())){
								addUserBankCount(bankDto2,userId);
							}
						}
					}
				}
	}
	public void addUserBankCount(BankDto bankDto,Long userId){
		int rest = -1; 
		TBankCard tBankCard = new TBankCard();
		tBankCard.setUserId(userId);
		tBankCard.setCreateTime(new Date());
		tBankCard.setBankName(bankDto.getBank_name());
		tBankCard.setBankCardNo(bankDto.getBank_no());
		tBankCard.setSubBankName(bankDto.getBank_address());
		tBankCard.setCardStatus(2);
		rest = tBankCardDao.insert(tBankCard);
		if(rest<=0){
			InfoMsg msg = new InfoMsg("银行卡添加失败", "n");
			throw new ZeroSQLException(msg);
		}
	}
	/**
	 * 个人中心银行卡管理
	 * @param tBankCard
	 * @param pageBounds
	 * @return
	 */
	public List<TBankCard> selectBankCardlist(TBankCard tBankCard){
		return tBankCardDao.selectBankCardlist(tBankCard);
	}
	public int insertBankCard(TBankCard tBankCard){
		return tBankCardDao.insertBankCard(tBankCard);
	}
	public int deleteBankCardforid(TBankCard tBankCard){
		return tBankCardDao.deleteBankCardforid(tBankCard);
	}
	public int updateBankCard(TBankCard tBankCard){
		return tBankCardDao.updateBankCard(tBankCard);
	}


}