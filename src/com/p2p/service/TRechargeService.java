package com.p2p.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dao.TRechargeDao;
import com.p2p.dto.InfoMsg;
import com.p2p.dto.RechargeRecordDto;
import com.p2p.enums.FundRecordType;
import com.p2p.enums.InfoTemplate;
import com.p2p.exception.ZeroSQLException;
import com.p2p.model.CMessage;
import com.p2p.model.TFundRecord;
import com.p2p.model.TRecharge;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.util.NumberUtil;


/** 充值表**/
@Service
public class TRechargeService extends BaseService {
	
	private static Log log = LogFactory.getLog(TRechargeService.class);
	
	@Autowired
	private TAccountService tAccountService;
	
	@Autowired
	private TRechargeDao tRechargeDao;
	@Autowired
	private CMessageService cMessageService;
	
	
 
	public long addTRecharge(TRecharge tRecharge) {
		return tRechargeDao.insert(tRecharge);
	}
 
	public long updateTRecharge(TRecharge tRecharge) {
		return tRechargeDao.update(tRecharge);
	}
 
	public TRecharge getTRecharge(TRecharge tRecharge) {
		return tRechargeDao.get(tRecharge);
	}
 
	public PageList queryTRechargePage(TRecharge tRecharge,Map<String, SearchOperator> options, PageBounds pageBounds) {
		return (PageList)tRechargeDao.getAllBy(tRecharge, options, pageBounds);
	}
	
	public PageList queryRechargeRecord(RechargeRecordDto rechargeRecordDto,PageBounds pageBounds){
		return (PageList) tRechargeDao.queryRechargeRecord(rechargeRecordDto,pageBounds);
	}
	
	public RechargeRecordDto selectRechargeRecord(Long id){
		return tRechargeDao.selectRechargeRecord(id);
	}
	
	/**
	 * 往账户充值金额
	 * @param tRecharge
	 * @return
	 */
	public InfoMsg updateTRechargeAccount(TRecharge tRecharge) {
		
		TRecharge newRecharge = tRechargeDao.get(tRecharge);
		
		if(!newRecharge.getResult().equals(0)){
			InfoMsg msg = new InfoMsg("重复处理充值","n");
			return msg;
		}
		
		if(newRecharge.getRechargeAmount().compareTo(tRecharge.getRechargeAmount())!= 0){
			InfoMsg msg = new InfoMsg("充值金额不相等","n");
			return msg;
		}
		
		//更新充值表
		long result = tRechargeDao.updateRechargeSuccess(tRecharge);
		if(result<=0){
			InfoMsg msg = new InfoMsg("充值失败，请联系客服","n");
			return msg;
		}
		
		//添加金额
		TFundRecord tFundRecord = new TFundRecord();
		tFundRecord.setOperAmount(tRecharge.getRechargeAmount());
		String remarks = FundRecordType.RECHAGE.getRemarks();
		remarks = remarks.replace("#amount", NumberUtil.fomatAmount(tRecharge.getRechargeAmount()));
		tFundRecord.setRemarks(remarks);
		tFundRecord.setFundRecordType(FundRecordType.RECHAGE);
		tFundRecord.setUserId(newRecharge.getUserId());
		result = tAccountService.updateAddUsableAmount(tFundRecord);
		if(result<=0){
			InfoMsg msg = new InfoMsg("充值失败，请联系客服","n");
			return msg;
		}
		InfoMsg msg = new InfoMsg("充值成功","y");
		return msg;
	}
	
	/**
	 * 个人中心充值记录
	 * @param trecharge
	 * @param pagebounds
	 * @return
	 */
	public PageList selectTRechargeList(TRecharge trecharge,PageBounds pagebounds){
		return (PageList)tRechargeDao.selectTRechargeList(trecharge,pagebounds);
	}
	
	/**
	 * 插入状态为0的充值记录
	 * @param tRecharge
	 * @return
	 */
	public int insertUserRecharge(TRecharge tRecharge){
		
		return tRechargeDao.insert(tRecharge);
	}
	
	/**
	 * 充值成功调用
	 * @param order_id
	 * @return
	 */
	public InfoMsg updateUserRecharge(TRecharge tRecharge){
		TRecharge tRecharge1=tRechargeDao.selectUserTrecharge(tRecharge.getOrdId());
		
		if(tRecharge1==null){
			InfoMsg msg = new InfoMsg("该充值订单ID不存在", "n");
			return msg;
		}
		if(tRecharge1.getResult()==1){
			InfoMsg msg = new InfoMsg("该充值订单已成功", "y");
			return msg;
		}
		int result=tRechargeDao.updateUserTrecharge(tRecharge);
		if(result<=0){
			InfoMsg msg = new InfoMsg("该充值订单正在处理中，请稍后再试", "n");
			return msg;
		}
		
		
		//增加用户可用余额添加资金流水
		TFundRecord tFundRecord=new TFundRecord();
		tFundRecord.setOperAmount(tRecharge.getRechargeRealAmount());
		String remarks = FundRecordType.RECHAGE.getRemarks();
		remarks = remarks.replace("#amount",NumberUtil.fomatAmount(tRecharge.getRechargeRealAmount()));
		tFundRecord.setRemarks(remarks);
		tFundRecord.setFundRecordType(FundRecordType.RECHAGE);
		tFundRecord.setUserId(tRecharge1.getUserId());
		long result2=tAccountService.updateAddUsableAmount(tFundRecord);
		if (result2 <= 0) {
			InfoMsg msg = new InfoMsg("增加可用余额失败", "n");
			throw new ZeroSQLException(msg);
		}
		
		//充值成功站内信
		CMessage cMessage = new CMessage();
		cMessage.setCreateTime(new Date());
		cMessage.setReceiverId(tRecharge1.getUserId());
		InfoTemplate infoTemplate = InfoTemplate.REARGE_SUCCESS;
		cMessage.setMessageTitle(infoTemplate.getSmsType());
		String messageContent = infoTemplate.getContentTemplate();
		messageContent = messageContent.replace("#rechargeAmount", NumberUtil.fomatAmount(tRecharge.getRechargeRealAmount()));
		messageContent = messageContent.replace("#realAmount", NumberUtil.fomatAmount(tRecharge.getRechargeRealAmount()));
		
		
		
		cMessage.setMessageContent(messageContent);
		Long result3 = cMessageService.addCMessage(cMessage);
		if (result3 <= 0) {
			InfoMsg msg2 = new InfoMsg("充值站内信发送失败！", "n");
			throw new ZeroSQLException(msg2);
		}
		
		InfoMsg msg =new InfoMsg("充值成功","y");
		return msg;
	}
	
	//查询15天内的充值额
	public BigDecimal selecttrechargefortime(Long userId){
		return tRechargeDao.selecttrechargefortime(userId);
	}
 }