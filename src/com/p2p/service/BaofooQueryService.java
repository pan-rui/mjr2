package com.p2p.service;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baofoo.p2p.dto.receive.Order;
import com.p2p.dto.InfoMsg;
import com.p2p.model.TTransfer;

@Service
public class BaofooQueryService extends BaseService {
	private static Log log = LogFactory.getLog(BaofooQueryService.class);

	@Autowired
	private TInvestService tInvestService;

	@Autowired
	private TBorrowService tBorrowService;

	@Autowired
	private TRepayService tRepayService;

	@Autowired
	private TWithdrawCashService tWithdrawCashService;
	
	@Autowired
	private TTransferService tTransferService;

	public void updateSigleInvestResult(Order order, String ordId) {
		if (order == null) {
			tInvestService.updateThirdFailInvest(ordId, null);
		} else {
			String state = order.getState();
			String succAmount = order.getSucc_amount();
			String baofooFee = order.getBaofoo_fee();
			String orderId = order.getOrder_id();
			String fee = order.getFee();
			String succTime = order.getSucc_time();
			log.info("orderId==>" + orderId + ",state=>" + state);
			if ("1".equals(state)) {
				InfoMsg msg = tInvestService.updateThirdSuccessInvest(orderId,
						null);
			} else {
				InfoMsg msg = tInvestService.updateThirdFailInvest(orderId,
						null);
			}
		}

	}

	public void updateSigleFullBorrowResult(Order order,String ordId) {
		if(order!=null){
			String state = order.getState();
			String succAmount = order.getSucc_amount();
			String baofooFee = order.getBaofoo_fee();
			String orderId = order.getOrder_id();
			String fee = order.getFee();
			String succTime = order.getSucc_time();
			
			if ("1".equals(state)) {
				log.info("orderId==>" + orderId + ",state=>" + state);
				InfoMsg msg = tBorrowService.updateBorrowThirdFullBack(orderId);
			}else{
				
				tBorrowService.updateBorrowThirdFullResetBack(ordId);
			}
		}else{
			log.info("ordId==>" + ordId );
			tBorrowService.updateBorrowThirdFullResetBack(ordId);
		}
		
	}

	public void updateSigleRepayBorrowResult(Order order,String ordId) {
		if(order != null){
			String state = order.getState();
			String succAmount = order.getSucc_amount();
			String baofooFee = order.getBaofoo_fee();
			String orderId = order.getOrder_id();
			String fee = order.getFee();
			String succTime = order.getSucc_time();
			log.info("orderId==>" + orderId + ",state=>" + state);
			if ("1".equals(state)) {
				InfoMsg msg = tRepayService.updateThirdRepayBack(orderId);
			}else{
				InfoMsg msg = tRepayService.updateThirdRepayResetStatusBack(ordId);
			}
		}else{
			InfoMsg msg = tRepayService.updateThirdRepayResetStatusBack(ordId);
		}
		
	}

	/**
	 * 修改提现各类状态
	 * 
	 * @param order
	 */
	public void updateSigleWithdrawCashResult(Order order) {
		String state = order.getState();
		String succAmount = order.getSucc_amount();
		String baofooFee = order.getBaofoo_fee();
		String orderId = order.getOrder_id();
		String fee = order.getFee();
		String succTime = order.getSucc_time();

		// 成功
		if ("1".equals(state)) {
			InfoMsg msg = tWithdrawCashService.updateThirdWithdrawBack(orderId);

		}
	                                                                                                                        	// 失败
		if ("-1".equals(state)) {
			System.out.println("进入失败了----------------");
			InfoMsg msg = tWithdrawCashService
					.updateThirdWithdrawBackFail(orderId);
		}
		
		
	}
	
	
	/**
	 * 修改提现各类状态one
	 * 
	 * @param order
	 */
	public void updateSigleWithdrawCashResultone(Order order) {
		String state = order.getState();
		String succAmount = order.getSucc_amount();
		String baofooFee = order.getBaofoo_fee();
		String orderId = order.getOrder_id();
		String fee = order.getFee();
		String succTime = order.getSucc_time();

		// 成功
		if ("1".equals(state)) {
			InfoMsg msg = tWithdrawCashService.insertAllUserWithdrawHandle(orderId,succAmount);

		}
		// 失败
		if ("-1".equals(state)) {
			System.out.println("进入失败了----------------");
			InfoMsg msg = tWithdrawCashService.twithdrawCashforresult2(orderId);
		}
		//处理中
		if ("5".equals(state)) {
			InfoMsg msg = tWithdrawCashService.insertAllUserWithdrawHandle(orderId,succAmount);
			
		}
		//状态为0的时候 证明客户中途退出操作
		if ("0".equals(state)) {
			
			int i=(int) ((new Date().getTime()-tWithdrawCashService.selectUserIdforOrdid(orderId).getCreateTime().getTime())/60000);
			if(i>31){
				InfoMsg msg = tWithdrawCashService.twithdrawCashforresult2(orderId);
			}
		}
	}

	public void updateSigleTransferResult(Order order, String ordId) {
		TTransfer tTransfer = new TTransfer();
		tTransfer.setOrdId(ordId);
		tTransfer.setFinishTime(new Date());
		if(order != null){
			String state = order.getState();
			String succAmount = order.getSucc_amount();
			String baofooFee = order.getBaofoo_fee();
			String orderId = order.getOrder_id();
			String fee = order.getFee();
			String succTime = order.getSucc_time();
			log.info("orderId==>" + orderId + ",state=>" + state);
			if ("1".equals(state)) {
				tTransfer.setTranStatus(2);
				tTransfer.setFinishTime(new Date());
				InfoMsg msg = tTransferService.updateTTransferSuccess(tTransfer);
			}else{
				tTransfer.setTranStatus(3);
				InfoMsg msg = tTransferService.updateTTransferFail(tTransfer);
			}
		}else{
			tTransfer.setTranStatus(3);
			InfoMsg msg = tTransferService.updateTTransferFail(tTransfer);
		}
	}

}
