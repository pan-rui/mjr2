package com.p2p.job;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baofoo.p2p.dto.receive.Order;
import com.baofoo.p2p.dto.request.QueryDto;
import com.baofoo.p2p.util.XMLBuild;
import com.p2p.constans.BaofooConstans;
import com.p2p.model.TBorrow;
import com.p2p.model.TInvest;
import com.p2p.model.TRepayBorrower;
import com.p2p.model.TTransfer;
import com.p2p.model.TWithdrawCash;
import com.p2p.service.BaofooQueryService;
import com.p2p.service.CUserService;
import com.p2p.service.TBankCardService;
import com.p2p.service.TBorrowService;
import com.p2p.service.TInvestService;
import com.p2p.service.TRepayBorrowerService;
import com.p2p.service.TTransferService;
import com.p2p.service.TWithdrawCashService;
import com.p2p.util.DateUtil;

@Component
public class BaofooQueryJob {
	private static Log log = LogFactory.getLog(BaofooQueryJob.class);

	@Autowired
	private BaofooQueryService baofooQueryService;

	@Autowired
	private TInvestService tInvestService;

	@Autowired
	private TBorrowService tBorrowService;

	@Autowired
	private TRepayBorrowerService tRepayBorrowerService;

	@Autowired
	private TWithdrawCashService tWithdrawCashService;

	@Autowired
	private CUserService cUserService;

	@Autowired
	private TBankCardService tBankCardService;

	@Autowired
	private TTransferService tTransferService;

	private static final int MIN = -2;

	public void queryInvestResult() {
		log.info("========投资核对========");

		TInvest tInvest = new TInvest();
		tInvest.setResult(0);
		tInvest.setInvestTime(DateUtil.dateAddMinute(new Date(), MIN));
		List<TInvest> investList = tInvestService.queryTInvestList(tInvest);
		if (investList != null) {
			for (TInvest tempInvest : investList) {
				try {
					QueryDto queryDto = new QueryDto();
					queryDto.setOrder_id(tempInvest.getOrdId());
					queryDto.setType("1");
					String xml = BaofooConstans.REQUEST_SERVICE
							.serv_p2pQuery(queryDto);
					log.info("xml==>" + xml);
					com.baofoo.p2p.dto.receive.QueryDto receiveQueryDto = BaofooConstans.RECEIVE_SERVICE
							.serv_p2pQuery(xml);
					if (receiveQueryDto == null
							|| receiveQueryDto.getResult() == null
							|| receiveQueryDto.getResult().size() == 0) {
						baofooQueryService.updateSigleInvestResult(null,
								tempInvest.getOrdId());
					}
					if (receiveQueryDto != null
							&& "CSD000".equals(receiveQueryDto.getCode())) {
						List<Order> orderList = receiveQueryDto.getResult();
						if (orderList.size() > 0) {
							Order order = orderList.get(0);
							baofooQueryService.updateSigleInvestResult(order,
									tempInvest.getOrdId());
						}
					}
				} catch (Exception e) {
					log.error("投资查询异常", e);
				}
			}
		}

	}

	public void queryFullBorrowResult() {
		log.info("========满标核对========");

		TBorrow tBorrow = new TBorrow();
		tBorrow.setBorrowStatus(10);
		tBorrow.setAuditTime(DateUtil.dateAddMinute(new Date(), MIN));
		List<TBorrow> borrowList = tBorrowService.queryTBorrowList(tBorrow);
		if (borrowList != null) {
			for (TBorrow tempBorrow : borrowList) {
				try {
					QueryDto queryDto = new QueryDto();
					queryDto.setOrder_id(tempBorrow.getOrdId());
					queryDto.setType("2");
					String xml = BaofooConstans.REQUEST_SERVICE
							.serv_p2pQuery(queryDto);
					log.info("xml==>" + xml);
					com.baofoo.p2p.dto.receive.QueryDto receiveQueryDto = BaofooConstans.RECEIVE_SERVICE
							.serv_p2pQuery(xml);
					if (receiveQueryDto == null
							|| receiveQueryDto.getResult() == null
							|| receiveQueryDto.getResult().size() == 0) {
						baofooQueryService.updateSigleFullBorrowResult(null,
								tempBorrow.getOrdId());
					}
					if (receiveQueryDto != null
							&& "CSD000".equals(receiveQueryDto.getCode())) {
						List<Order> orderList = receiveQueryDto.getResult();
						if (orderList.size() > 0) {
							Order order = orderList.get(0);
							baofooQueryService.updateSigleFullBorrowResult(
									order, tempBorrow.getOrdId());
						}
					}
				} catch (Exception e) {
					log.error("满标查询异常", e);
				}
			}

		}

	}

	public void queryRepayBorrowResult() {
		log.info("========还款核对========");

		TRepayBorrower tRepayBorrower = new TRepayBorrower();
		tRepayBorrower.setRepayStatus(3);
		tRepayBorrower
				.setRealRepayTime(DateUtil.dateAddMinute(new Date(), MIN));
		List<TRepayBorrower> repayBorrowerList = tRepayBorrowerService
				.queryTRepayBorrowerList(tRepayBorrower);
		if (repayBorrowerList != null) {
			for (TRepayBorrower tempTRepayBorrower : repayBorrowerList) {
				try {
					QueryDto queryDto = new QueryDto();
					queryDto.setOrder_id(tempTRepayBorrower.getOrdId());
					queryDto.setType("4");
					String xml = BaofooConstans.REQUEST_SERVICE
							.serv_p2pQuery(queryDto);
					log.info("xml==>" + xml);
					com.baofoo.p2p.dto.receive.QueryDto receiveQueryDto = BaofooConstans.RECEIVE_SERVICE
							.serv_p2pQuery(xml);

					if (receiveQueryDto == null
							|| receiveQueryDto.getResult() == null
							|| receiveQueryDto.getResult().size() == 0) {
						baofooQueryService.updateSigleRepayBorrowResult(null,
								tempTRepayBorrower.getOrdId());
					}
					if (receiveQueryDto != null
							&& "CSD000".equals(receiveQueryDto.getCode())) {
						List<Order> orderList = receiveQueryDto.getResult();
						if (orderList.size() > 0) {
							Order order = orderList.get(0);
							baofooQueryService.updateSigleRepayBorrowResult(
									order, tempTRepayBorrower.getOrdId());
						}
					}
				} catch (Exception e) {
					log.error("还款查询异常", e);
				}
			}
		}

	}

	public void queryTransferResult() {
		log.info("========商户转账核对========");

		TTransfer tTransfer = new TTransfer();
		tTransfer.setTranStatus(1);
		List<TTransfer> transferList = tTransferService
				.queryTTransferList(tTransfer);
		if (transferList != null) {
			for (TTransfer tempTTransfer : transferList) {
				try {
					QueryDto queryDto = new QueryDto();
					queryDto.setOrder_id(tempTTransfer.getOrdId());
					queryDto.setType("7");
					log.info("商户转账查询请求参数："+XMLBuild.parseEntityToXML(queryDto));
					String xml = BaofooConstans.REQUEST_SERVICE
							.serv_p2pQuery(queryDto);
					log.info("xml==>" + xml);
					com.baofoo.p2p.dto.receive.QueryDto receiveQueryDto = BaofooConstans.RECEIVE_SERVICE
							.serv_p2pQuery(xml);

					if (receiveQueryDto == null
							|| receiveQueryDto.getResult() == null
							|| receiveQueryDto.getResult().size() == 0) {
						baofooQueryService.updateSigleTransferResult(null,
								tempTTransfer.getOrdId());
					}
					if (receiveQueryDto != null
							&& "CSD000".equals(receiveQueryDto.getCode())) {
						List<Order> orderList = receiveQueryDto.getResult();
						if (orderList.size() > 0) {
							Order order = orderList.get(0);
							baofooQueryService.updateSigleTransferResult(order,
									tempTTransfer.getOrdId());
						}
					}
				} catch (Exception e) {
					log.error("转账查询异常", e);
				}
			}

		}

	}

	/**
	 * 自动查询提现信息对账
	 */
	public void queryWithdrawBorrowResult() {
		log.info("========提现核对状态为2时========");

		TWithdrawCash tWithdrawCash = new TWithdrawCash();
		tWithdrawCash.setWithdrawStatus(2);
		List<TWithdrawCash> list = tWithdrawCashService
				.selectWithdrawList(tWithdrawCash);
		if (list != null) {
			for (TWithdrawCash tWithdrawCash2 : list) {
				try {
					QueryDto queryDto = new QueryDto();
					queryDto.setOrder_id(tWithdrawCash2.getOrdId());
					queryDto.setType("6");
					String xml;
					xml = BaofooConstans.REQUEST_SERVICE
							.serv_p2pQuery(queryDto);

					log.info("xml==>" + xml);
					com.baofoo.p2p.dto.receive.QueryDto receiveQueryDto = BaofooConstans.RECEIVE_SERVICE
							.serv_p2pQuery(xml);
					if (receiveQueryDto != null
							&& "CSD000".equals(receiveQueryDto.getCode())) {
						List<Order> orderList = receiveQueryDto.getResult();
						if (orderList.size() > 0) {
							Order order = orderList.get(0);
							baofooQueryService
									.updateSigleWithdrawCashResult(order);
						}
					}
				} catch (Exception e) {
					log.error("提现对账异常", e);
				}
			}

		}

	}

	/**
	 * 查询宝付的用户银行卡信息，更新未保存数据
	 */
	public void queryUserBank() {
		log.info("~~~~~~用户银行卡开始~~~~~~");
		List<Long> userIdList = cUserService.queryAlluserid();
		for (Long long1 : userIdList) {
			tBankCardService.selectBaofooBankCard(long1);
		}
		log.info("~~~~~~用户银行卡结束~~~~~~");
	}

	/**
	 * 自动查询提现信息对账状态为1时
	 */
	public void queryWithdrawBorrowResultone() {
		log.info("========提现核对状态为1时========");

		TWithdrawCash tWithdrawCash = new TWithdrawCash();
		tWithdrawCash.setWithdrawStatus(1);
		List<TWithdrawCash> list = tWithdrawCashService
				.selectWithdrawList(tWithdrawCash);
		if (list != null) {
			for (TWithdrawCash tWithdrawCash2 : list) {
				try {
					QueryDto queryDto = new QueryDto();
					queryDto.setOrder_id(tWithdrawCash2.getOrdId());
					queryDto.setType("6");
					String xml;
					xml = BaofooConstans.REQUEST_SERVICE
							.serv_p2pQuery(queryDto);

					log.info("xml==>" + xml);
					com.baofoo.p2p.dto.receive.QueryDto receiveQueryDto = BaofooConstans.RECEIVE_SERVICE
							.serv_p2pQuery(xml);
					if (receiveQueryDto != null
							&& "CSD000".equals(receiveQueryDto.getCode())) {
						List<Order> orderList = receiveQueryDto.getResult();
						if (orderList.size() > 0) {
							Order order = orderList.get(0);
							baofooQueryService
									.updateSigleWithdrawCashResultone(order);
						}
					}
				} catch (Exception e) {
					log.error("提现对账异常", e);
				}
			}

		}

	}

}
