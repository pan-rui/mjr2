package com.p2p.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dao.TAccountDao;
import com.p2p.dao.TWithdrawCashDao;
import com.p2p.dto.InfoMsg;
import com.p2p.dto.TWithdrawCashDto;
import com.p2p.enums.FundRecordType;
import com.p2p.enums.InfoTemplate;
import com.p2p.exception.ZeroSQLException;
import com.p2p.model.CMessage;
import com.p2p.model.CUser;
import com.p2p.model.TAccount;
import com.p2p.model.TFundRecord;
import com.p2p.model.TWithdrawCash;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.util.NumberUtil;
import com.p2p.util.OrdIdUtil;
import com.p2p.util.OrdIdUtil.OrdType;


/** 提现金额**/
@Service
public class TWithdrawCashService extends BaseService {

	private static Log log = LogFactory.getLog(TWithdrawCashService.class);
	@Autowired
	private TWithdrawCashDao tWithdrawCashDao;
	@Autowired
	private TAccountService tAccountService;
	@Autowired
	private CMessageService cMessageService;
 
	public long addTWithdrawCash(TWithdrawCash tWithdrawCash) {
		return tWithdrawCashDao.insert(tWithdrawCash);
	}
 
	public long updateTWithdrawCash(TWithdrawCash tWithdrawCash) {
		return tWithdrawCashDao.update(tWithdrawCash);
	}
 
	public TWithdrawCash getTWithdrawCash(TWithdrawCash tWithdrawCash) {
		return tWithdrawCashDao.get(tWithdrawCash);
	}
 
	public PageList queryTWithdrawCashPage(TWithdrawCash tWithdrawCash,Map<String, SearchOperator> options, PageBounds pageBounds) {
		return (PageList)tWithdrawCashDao.getAllBy(tWithdrawCash, options, pageBounds);
	}
	
	public PageList selectTWithDrawCashDtoList(TWithdrawCashDto twithdrawCashDto,PageBounds pageBounds){
		return (PageList)tWithdrawCashDao.selectTWithDrawCashDtoList(twithdrawCashDto, pageBounds);
	}
	
	public TWithdrawCashDto selectTWithDrawCashDtoDeatil(Long id){
		return tWithdrawCashDao.selectTWithDrawCashDtoDeatil(id);
	}
	
	/**
	 * 提现被动回调处理
	 */
	public InfoMsg insertAllUserWithdrawHandle(String Order_id,String Amount){
		
		TWithdrawCash twc=new TWithdrawCash();
		twc.setAccountAmount(new BigDecimal(Amount));
		twc.setOrdId(Order_id);
		InfoMsg msg=updatetwithdrawforhd(twc);
		
		//根据订单号查询userid
		TWithdrawCash tWithdrawCash=tWithdrawCashDao.selectUserIdforOrdid(Order_id);
		
		TFundRecord tFundRecord = new TFundRecord();
		tFundRecord.setOperAmount(tWithdrawCash.getWithdrawAmount());
		String remarks = FundRecordType.WITHDRAW_FROZEN.getRemarks();
		remarks = remarks.replace("#amount",NumberUtil.fomatAmount(tWithdrawCash.getWithdrawAmount()));
		tFundRecord.setRemarks(remarks);
		tFundRecord.setFundRecordType(FundRecordType.WITHDRAW_FROZEN);
		tFundRecord.setUserId(tWithdrawCash.getUserId());
		long result1=-1;
		result1 = tAccountService.updateFrozenAmount(tFundRecord);
		if(result1<=0){
			InfoMsg msg2 = new InfoMsg("提现操作失败","n");
			throw new ZeroSQLException(msg2);
		}
		
		//投资人提现已受理
		CMessage cMessage = new CMessage();
		cMessage.setCreateTime(new Date());
		cMessage.setReceiverId(tWithdrawCash.getUserId());
		InfoTemplate infoTemplate = InfoTemplate.WITHDRAW_RECEIVED;
		cMessage.setMessageTitle(infoTemplate.getSmsType());
		String messageContent = infoTemplate.getContentTemplate();
		messageContent = messageContent.replace("#amount", NumberUtil.fomatAmount(tWithdrawCash.getWithdrawAmount()));
		messageContent = messageContent.replace("#trueamount", NumberUtil.fomatAmount(tWithdrawCash.getAccountAmount()));
		messageContent = messageContent.replace("#fee", NumberUtil.fomatAmount(tWithdrawCash.getFeeAmount()));
		
		
		cMessage.setMessageContent(messageContent);
		Long result = cMessageService.addCMessage(cMessage);
		if (result <= 0) {
			InfoMsg msg2 = new InfoMsg("提现站内信发送失败！", "n");
			throw new ZeroSQLException(msg2);
		}
		
			InfoMsg msg2 = new InfoMsg("提现成功", "y");
			
			return msg;
	}
	
	/**
	 * 个人中心提现记录
	 * @param twithdrawcash
	 * @param pagebounds
	 * @return
	 */
	public PageList selectWithdraw(TWithdrawCash twithdrawcash,PageBounds pagebounds){
		return (PageList) tWithdrawCashDao.selectWithdraw(twithdrawcash, pagebounds);
	}
	
	/**
	 * 提现成功调用 第一次调用 状态处理中
	 * @param tWithdrawCash
	 * @return
	 */
	public InfoMsg updatetwithdrawforhd(TWithdrawCash tWithdrawCash){
		TWithdrawCash tWithdrawCash2=tWithdrawCashDao.selectwithdrawforordid(tWithdrawCash.getOrdId());
		if(tWithdrawCash2==null){
			InfoMsg msg=new InfoMsg("该订单号不存在", "n");
			return msg;
		}
		if(tWithdrawCash2.getWithdrawStatus()==2){
			InfoMsg msg=new InfoMsg("该订单号已在处理中", "y");
			return msg;
		}
		if(tWithdrawCash2.getWithdrawStatus()==3){
			InfoMsg msg=new InfoMsg("该订单号已成功", "y");
			return msg;
		}
		int result=tWithdrawCashDao.updatewithdrawtwo(tWithdrawCash.getOrdId());
		if(result<=0){
			InfoMsg msg = new InfoMsg("该提现订单处理失败，请稍后再试", "n");
			return msg;
		}
		
		
		
		InfoMsg msg=new InfoMsg("提现处理中", "y");
		return msg;
	}
	
	
	public int inserttwithdraw(TWithdrawCash tWithdrawCash){
		return tWithdrawCashDao.insert(tWithdrawCash);
	}
	public List<TWithdrawCash> selectWithdrawList(TWithdrawCash tWithdrawCash){
		return tWithdrawCashDao.selectWithdrawList(tWithdrawCash);
	}
	
	/**
	 * 提现对账修改类(成功时调用)
	 * @return
	 */
	public InfoMsg updateThirdWithdrawBack(String orderId){
		//修改提现表状态
		int resultt=tWithdrawCashDao.updatewithdrawthree(orderId);
		if(resultt<=0){
			InfoMsg msg=new InfoMsg("修改提现状态失败", "n");
			return msg;
		}
		//增加资金流水
		TWithdrawCash twc=tWithdrawCashDao.selectwithdrawforordid(orderId);
		//解冻提现金额
		
		TFundRecord tFundRecord = new TFundRecord();
		tFundRecord.setOperAmount(twc.getWithdrawAmount());
		String remarks = FundRecordType.WITHDRAW_SUCCESS_UNFREEZE.getRemarks();
		remarks = remarks.replace("#amount",NumberUtil.fomatAmount(twc.getWithdrawAmount()));
		tFundRecord.setRemarks(remarks);
		tFundRecord.setFundRecordType(FundRecordType.WITHDRAW_SUCCESS_UNFREEZE);
		tFundRecord.setUserId(twc.getUserId());
		Long result = tAccountService.updateUnfreezeAmount(tFundRecord);
		if(result<=0){
			InfoMsg msg = new InfoMsg("提现操作失败","n");
			
			return msg;
		}
		
		BigDecimal accountAmount = twc.getWithdrawAmount().subtract(twc.getFeeAmount());
		//扣除提现到账金额
		tFundRecord = new TFundRecord();
		tFundRecord.setOperAmount(accountAmount);
		remarks = FundRecordType.WITHDRAW_SUCCESS.getRemarks();
		remarks = remarks.replace("#amount",NumberUtil.fomatAmount(accountAmount));
		tFundRecord.setRemarks(remarks);
		tFundRecord.setFundRecordType(FundRecordType.WITHDRAW_SUCCESS);
		tFundRecord.setUserId(twc.getUserId());
		result = tAccountService.updateReduceUsableAmount(tFundRecord);
		if(result<=0){
			InfoMsg msg = new InfoMsg("提现操作失败","n");
			
			return msg;
		}
		
		//扣除提现手续费
		tFundRecord = new TFundRecord();
		tFundRecord.setOperAmount(twc.getFeeAmount());
		remarks = FundRecordType.WITHDRAW_SUCCESS_FEE.getRemarks();
		remarks = remarks.replace("#fee",NumberUtil.fomatAmount(twc.getFeeAmount()));
		tFundRecord.setRemarks(remarks);
		tFundRecord.setFundRecordType(FundRecordType.WITHDRAW_SUCCESS_FEE);
		tFundRecord.setUserId(twc.getUserId());
		result = tAccountService.updateReduceUsableAmount(tFundRecord);
		if(result<=0){
			InfoMsg msg = new InfoMsg("提现操作失败","n");
			
			return msg;
		}
		
		//投资人提现成功信息
		CMessage cMessage = new CMessage();
		cMessage.setCreateTime(new Date());
		cMessage.setReceiverId(twc.getUserId());
		InfoTemplate infoTemplate = InfoTemplate.WITHDRAW_SUCCESS;
		cMessage.setMessageTitle(infoTemplate.getSmsType());
		String messageContent = infoTemplate.getContentTemplate();
		messageContent = messageContent.replace("#amount", NumberUtil.fomatAmount(twc.getWithdrawAmount()));
		messageContent = messageContent.replace("#trueamount", NumberUtil.fomatAmount(twc.getAccountAmount()));
		messageContent = messageContent.replace("#fee", NumberUtil.fomatAmount(twc.getFeeAmount()));
		
		
		cMessage.setMessageContent(messageContent);
		result = cMessageService.addCMessage(cMessage);
		if (result <= 0) {
			InfoMsg msg = new InfoMsg("提现站内信发送失败！", "n");
			throw new ZeroSQLException(msg);
		}
		
		InfoMsg msg=new InfoMsg("提现成功", "y");
		return msg;
	}
	
	/**
	 * 提现对账修改类(失败时调用)
	 * @return
	 */
	public InfoMsg updateThirdWithdrawBackFail(String orderId){
		//修改提现表状态
		int resultt=tWithdrawCashDao.updatewithdrawfour(orderId);
		if(resultt<=0){
			InfoMsg msg=new InfoMsg("修改提现状态失败", "n");
			return msg;
		}
		//增加资金流水
		TWithdrawCash twc=tWithdrawCashDao.selectwithdrawforordid(orderId);
		TFundRecord tFundRecord = new TFundRecord();
		tFundRecord.setOperAmount(twc.getAccountAmount());
		String remarks = FundRecordType.WITHDRAW_UNFREEZE.getRemarks();
		remarks = remarks.replace("#amount",NumberUtil.fomatAmount(twc.getAccountAmount()));
		tFundRecord.setRemarks(remarks);
		tFundRecord.setFundRecordType(FundRecordType.WITHDRAW_UNFREEZE);
		tFundRecord.setUserId(twc.getUserId());
		Long result = tAccountService.updateUnfreezeAmount(tFundRecord);
		if(result<=0){
			InfoMsg msg = new InfoMsg("提现操作失败","n");
			
			return msg;
		}
		
		//投资人提现已受理
		CMessage cMessage = new CMessage();
		cMessage.setCreateTime(new Date());
		cMessage.setReceiverId(twc.getUserId());
		InfoTemplate infoTemplate = InfoTemplate.WITHDRAW_RECEIVED;
		cMessage.setMessageTitle(infoTemplate.getSmsType());
		String messageContent = infoTemplate.getContentTemplate();
		messageContent = messageContent.replace("#amount", NumberUtil.fomatAmount(twc.getWithdrawAmount()));
		
		
		
		cMessage.setMessageContent(messageContent);
		Long result2 = cMessageService.addCMessage(cMessage);
		if (result2 <= 0) {
			InfoMsg msg2 = new InfoMsg("提现站内信发送失败！", "n");
			throw new ZeroSQLException(msg2);
		}
		
		
		InfoMsg msg=new InfoMsg("提现对账成功", "y");
		return msg;
	}
	
	public BigDecimal selecttwithdrwafortime(Long userId){
		return tWithdrawCashDao.selecttwithdrwafortime(userId);
	}
	
	public TWithdrawCash selectUserIdforOrdid(String ordId){
		return tWithdrawCashDao.selectUserIdforOrdid(ordId);
	}
	
	
	/**
	 * 提现状态为5的时候调用
	 * @param orderId
	 * @return
	 */
	public InfoMsg twithdrawCashforresult(String orderId){
		//根据订单号查询userid和提现金额
		TWithdrawCash tWithdrawCash=tWithdrawCashDao.selectUserIdforOrdid(orderId);
		tWithdrawCash.setOrdId(orderId);
		InfoMsg msg=updatetwithdrawforhd(tWithdrawCash);
		
		
		
		TFundRecord tFundRecord = new TFundRecord();
		tFundRecord.setOperAmount(tWithdrawCash.getWithdrawAmount());
		String remarks = FundRecordType.WITHDRAW_FROZEN.getRemarks();
		remarks = remarks.replace("#amount",NumberUtil.fomatAmount(tWithdrawCash.getWithdrawAmount()));
		tFundRecord.setRemarks(remarks);
		tFundRecord.setFundRecordType(FundRecordType.WITHDRAW_FROZEN);
		tFundRecord.setUserId(tWithdrawCash.getUserId());
		long result1=-1;
		result1 = tAccountService.updateReduceUsableAmount(tFundRecord);
		if(result1<=0){
			InfoMsg msg2 = new InfoMsg("提现操作失败","n");
			throw new ZeroSQLException(msg2);
		}
		
				//投资人提现已受理
				CMessage cMessage = new CMessage();
				cMessage.setCreateTime(new Date());
				cMessage.setReceiverId(tWithdrawCash.getUserId());
				InfoTemplate infoTemplate = InfoTemplate.WITHDRAW_RECEIVED;
				cMessage.setMessageTitle(infoTemplate.getSmsType());
				String messageContent = infoTemplate.getContentTemplate();
				messageContent = messageContent.replace("#amount", NumberUtil.fomatAmount(tWithdrawCash.getWithdrawAmount()));
				messageContent = messageContent.replace("#trueamount", NumberUtil.fomatAmount(tWithdrawCash.getAccountAmount()));
				messageContent = messageContent.replace("#fee", NumberUtil.fomatAmount(tWithdrawCash.getFeeAmount()));
				
				
				cMessage.setMessageContent(messageContent);
				Long result = cMessageService.addCMessage(cMessage);
				if (result <= 0) {
					InfoMsg msg2 = new InfoMsg("提现站内信发送失败！", "n");
					throw new ZeroSQLException(msg2);
				}
		
		InfoMsg msg3=new InfoMsg("提现状态修改成功", "y");
		return msg3;
	}
	
	/**
	 * 提现状态为5的时候调用
	 * @param orderId
	 * @return
	 */
	public InfoMsg twithdrawCashforresult2(String orderId){
		int i=tWithdrawCashDao.updateonetofour(orderId);
		if(i<1){
			InfoMsg msg=new InfoMsg("处理失败", "n");
			return msg;
		}
		InfoMsg msg=new InfoMsg("处理成功", "y");
		return msg;
	}
 }