package com.p2p.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baofoo.p2p.dto.receive.ResultDto;
import com.baofoo.p2p.dto.request.BalanceDto;
import com.baofoo.p2p.util.XMLBuild;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.constans.BaofooConstans;
import com.p2p.dao.TAccountDao;
import com.p2p.dao.TFundRecordDao;
import com.p2p.dao.TThridAccountDao;
import com.p2p.dto.TFundRecordDto;
import com.p2p.dto.UserMoneyDto;
import com.p2p.enums.FundRecordType;
import com.p2p.model.TAccount;
import com.p2p.model.TFundRecord;
import com.p2p.model.TThridAccount;
import com.p2p.mybatis.annotion.SearchOperator;


/** 资金记录表**/
@Service
public class TFundRecordService extends BaseService {

	private static Log log = LogFactory.getLog(TFundRecordService.class);
	@Autowired
	private TFundRecordDao tFundRecordDao;
 
	@Autowired
	private TAccountDao tAccountDao;
	@Autowired
	private TThridAccountDao tThridAccountDao;
	
	public long addTFundRecord(TFundRecord tFundRecord) {
		TAccount afterAccount = tAccountDao.getByUserId(tFundRecord.getUserId());
		FundRecordType fundRecordType = tFundRecord.getFundRecordType();
		if(fundRecordType.getOperType()==1 || fundRecordType.getOperType()==3){
			tFundRecord.setInAmount(tFundRecord.getOperAmount());
		}else {
			tFundRecord.setOutAmount(tFundRecord.getOperAmount());
		}
		tFundRecord.setFrozenAmount(afterAccount.getFrozenAmount());
		tFundRecord.setUsableAmount(afterAccount.getUsableAmount());
		tFundRecord.setCreateTime(new Date());
		tFundRecord.setFundMode(fundRecordType.getFundMode());
		tFundRecord.setFundType(fundRecordType.getFundType());
		tFundRecord.setOperType(fundRecordType.getOperType());
		return tFundRecordDao.insert(tFundRecord);
	}
 
	public long updateTFundRecord(TFundRecord tFundRecord) {
		return tFundRecordDao.update(tFundRecord);
	}
 
	public TFundRecord getTFundRecord(TFundRecord tFundRecord) {
		return tFundRecordDao.get(tFundRecord);
	}
 
	public PageList queryTFundRecordPage(TFundRecord tFundRecord,Map<String, SearchOperator> options, PageBounds pageBounds) {
		return (PageList)tFundRecordDao.getAllBy(tFundRecord, options, pageBounds);
	}
	
	public PageList selectTFundRecordlist(TFundRecordDto tFundRecordDto,PageBounds pageBounds){
		return (PageList)tFundRecordDao.selectTFundRecordlist(tFundRecordDto,pageBounds);
	}
	public TFundRecordDto selectTFundRecordDetail(Long id){
		return tFundRecordDao.selectTFundRecordDetail(id);
	}
	
	//查看用户列表
		public PageList selectUserList(UserMoneyDto userMoneyDto,PageBounds pageBounds){
			return (PageList)tFundRecordDao.selectUserList(userMoneyDto, pageBounds);
		}
		
		/**
		 * 个人中心资金流水
		 * @param tfundrecord
		 * @param pagebounds
		 * @return
		 */
		public PageList selectFundRecord(TFundRecord tfundrecord,PageBounds pagebounds){
			PageList pl = new PageList<>();
			try{
				pl=  (PageList)tFundRecordDao.selectFundRecord(tfundrecord, pagebounds);}
			catch (Exception e) {
				log.error("个人资金流水查询异常", e);
			}
			return pl;
		}
		
		/**
		 * 宝付个人资金查询
		 * @param userId
		 * @return
		 */
		public BigDecimal selectusermoney(Long userId){
			BigDecimal money=null;
			try {
				TThridAccount tthrid =tThridAccountDao.getThridAccountByUserId(userId);
				BalanceDto entity=new BalanceDto(BaofooConstans.merchantId, tthrid.getThirdUserId());
				log.info("宝付个人资金查询请求参数:"+XMLBuild.parseEntityToXML(entity));
				String xml=BaofooConstans.REQUEST_SERVICE.serv_AccountBalance(entity);
				log.info("宝付个人资金查询回调参数:"+xml);
				ResultDto rsdto=BaofooConstans.RECEIVE_SERVICE.serv_AccountBalance(xml);
				
				if(!rsdto.getCode().equals("CSD000")){
					log.error("宝付个人资金查询返回验签失败");
					return null;
				}
				if(rsdto.getBalance().equals("-1")){
					log.error("宝付个人资金查询返回结果失败");
					return null;
				}else{
					money=new BigDecimal(rsdto.getBalance());
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return money;
		}
		
		
		/**
		 * 宝付总资金查询
		 * @param userId
		 * @return
		 */
		public BigDecimal selectallmoney(Long userId){
			BigDecimal money=null;
			try {
				
				String xml=BaofooConstans.REQUEST_SERVICE.serv_accountAllBalance();
				
				ResultDto rsdto=BaofooConstans.RECEIVE_SERVICE.serv_accountAllBalance(xml);
				log.info("宝付总资金查询回调参数:"+xml);
				if(!rsdto.getCode().equals("CSD000")){
					log.error("宝付总资金查询返回验签失败");
					return null;
				}
				if(rsdto.getBalance().equals("-1")){
					log.error("宝付总资金查询返回结果失败");
					return null;
				}else{
					money=new BigDecimal(rsdto.getBalance());
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return money;
		}
 }