package com.p2p.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.constans.RepayType;
import com.p2p.dao.TInvestAwardDao;
import com.p2p.dto.InfoMsg;
import com.p2p.dto.InvestAwardRepayDto;
import com.p2p.dto.InvestAwardUserDto;
import com.p2p.dto.RefereeInvestDto;
import com.p2p.dto.UserInvestLevelDto;
import com.p2p.exception.ZeroSQLException;
import com.p2p.model.CUserDetail;
import com.p2p.model.TBorrow;
import com.p2p.model.TInvestAward;
import com.p2p.model.TRepay;
import com.p2p.model.TTransfer;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.util.CalculateInterestUtil;

/** 奖励推荐 **/
@Service
public class TInvestAwardService extends BaseService {

	private static Log log = LogFactory.getLog(TInvestAwardService.class);
	@Autowired
	private TInvestAwardDao tInvestAwardDao;
	@Autowired
	private TInvestService tInvestService;
	
	@Autowired
	private CUserDetailService cUserDetailService;
	
	@Autowired
	private TRepayService tRepayService;

	@Autowired
	private TBorrowService tBorrowService;
	
	@Autowired 
	private BaofooServiceImpl baofooService;
	
	@Autowired
	private TRefereeRelationService tRefereeRelationService;
	
	public long addTInvestAward(TInvestAward tInvestAward) {
		return tInvestAwardDao.insert(tInvestAward);
	}

	/**
	 * 获取相应的投资奖励加成
	 * @param capitalAmount
	 * @param awardTimeLevel
	 * @return
	 */
	private UserInvestLevelDto getInvestLevel(BigDecimal capitalAmount,
			String awardTimeLevel) {
		String awardLevel = "V0";
		BigDecimal awardRate = BigDecimal.ZERO;
		if (capitalAmount == null
				|| capitalAmount.compareTo(new BigDecimal("10000")) < 0) {
			awardLevel = "V0";
		} else if (capitalAmount.compareTo(new BigDecimal("50000")) < 0) {
			awardLevel = "V1";

		} else if (capitalAmount.compareTo(new BigDecimal("100000")) < 0) {
			awardLevel = "V2";

		} else if (capitalAmount.compareTo(new BigDecimal("100000")) >= 0) {
			awardLevel = "V3";
		}
		if (awardTimeLevel != null && awardLevel.compareTo(awardTimeLevel) < 0) {
			awardLevel = awardTimeLevel;
		}
		if ("V1".equals(awardLevel)) {
			awardRate = new BigDecimal("0.4");
		} else if ("V2".equals(awardLevel)) {
			awardRate = new BigDecimal("0.8");
		} else if ("V3".equals(awardLevel)) {
			awardRate = new BigDecimal("1.6");
		}
		UserInvestLevelDto userInvestLevelDto = new UserInvestLevelDto();
		userInvestLevelDto.setAwardLevel(awardLevel);
		userInvestLevelDto.setDueinAmount(capitalAmount);
		userInvestLevelDto.setAwardRate(awardRate);
		
		return userInvestLevelDto;
	}
	
	/**
	 * 获取相应的投资奖励加成
	 * @param capitalAmount
	 * @param awardTimeLevel
	 * @return
	 */
	public UserInvestLevelDto getInvestLevel(Long userId) {
		TRepay tRepay = tRepayService.queryDueinRepayCapitalProfitAmount(userId);
		BigDecimal capitalAmount = BigDecimal.ZERO;
		if(tRepay != null){
			capitalAmount = tRepay.getCapitalAmount();
		}
		CUserDetail cUserDetail = cUserDetailService.getCUserDetailByUserId(userId);
		UserInvestLevelDto userInvestLevelDto  = getInvestLevel(capitalAmount,cUserDetail.getAwardTimeLevel());
		return userInvestLevelDto;
	}

	/**
	 * 满标后添加投资奖励记录
	 * 
	 * @param borrowId
	 * @return
	 */
	public long addTInvestAwards(Long borrowId) {
		long result = -1;
		List<InvestAwardRepayDto> investAwardRepayDtoList = tInvestService
				.queryTInvestAwardListByBorrowId(borrowId);
		Date nowDate = new Date();
		TBorrow queryBorrow = new TBorrow();
		queryBorrow.setId(borrowId);
		TBorrow tBorrow = tBorrowService.getTBorrow(queryBorrow);

		boolean isDay = tBorrow.getDeadlineType().equals(1);

		if (investAwardRepayDtoList == null) {
			result = 1;
			return result;
		}

		for (InvestAwardRepayDto investAwardRepayDto : investAwardRepayDtoList) {
			BigDecimal capitalAmount = investAwardRepayDto.getCapitalAmount();
			String awardTimeLevel = investAwardRepayDto.getAwardTimeLevel();
			UserInvestLevelDto userInvestLevelDto  = getInvestLevel(capitalAmount,
					awardTimeLevel);
			String awardLevel = userInvestLevelDto.getAwardLevel();
			BigDecimal awardRate = userInvestLevelDto.getAwardRate();
			// 投资奖励
			if ("V1".equals(awardLevel) || "V2".equals(awardLevel)
					|| "V3".equals(awardLevel)) {
				List<Map<String, Object>> repayList = null;
				if (tBorrow.getRepayType().equals(RepayType.EQUAL_MONTHLY)) {
					repayList = CalculateInterestUtil
							.calculateEqualMonthlyList(
									investAwardRepayDto.getRealAmount(),
									tBorrow.getDeadline(), awardRate);

				} else if (tBorrow.getRepayType().equals(RepayType.ONE_REPAY)) {
					repayList = CalculateInterestUtil.calculateOneRepayList(
							investAwardRepayDto.getRealAmount(),
							tBorrow.getDeadline(), awardRate, isDay);
				} else {
					repayList = CalculateInterestUtil
							.calculateMonthRepayInterestList(
									investAwardRepayDto.getRealAmount(),
									tBorrow.getDeadline(), awardRate);
				}
				if (repayList != null) {
					for (Map<String, Object> repayMap : repayList) {

						TInvestAward tInvestAward = new TInvestAward();
						tInvestAward.setNumOfPeroids((Integer) repayMap
								.get("period"));
						tInvestAward.setAwardAmount((BigDecimal) repayMap
								.get("profitAmount"));
						tInvestAward.setAwardLevel(awardLevel);
						tInvestAward.setAwardRate(awardRate);
						tInvestAward.setAwardStatus(0);
						tInvestAward.setAwardType(2);
						tInvestAward.setBorrowId(borrowId);
						tInvestAward.setCreateTime(nowDate);
						tInvestAward.setDueinAmount(investAwardRepayDto
								.getCapitalAmount());
						tInvestAward.setInvestId(investAwardRepayDto.getId());
						tInvestAward.setRemarks("投资奖励");
						tInvestAward.setUserId(investAwardRepayDto
								.getInvestorId());
						tInvestAward.setBorrowTitle(tBorrow.getBorrowTitle());
						result = tInvestAwardDao.insert(tInvestAward);
						if (result <= 0) {
							throw new ZeroSQLException();
						}
					}
				}

			}

			
			
			
		}
		
		BigDecimal newInvestAwardRate = new BigDecimal("0.4");
		for (InvestAwardRepayDto investAwardRepayDto : investAwardRepayDtoList) {		
			// 新手奖励
			if (investAwardRepayDto.getIsNew().equals(1)) {
				
				List<Map<String, Object>> repayList = null;
				if (tBorrow.getRepayType().equals(RepayType.EQUAL_MONTHLY)) {
					repayList = CalculateInterestUtil
							.calculateEqualMonthlyList(
									investAwardRepayDto.getRealAmount(),
									tBorrow.getDeadline(), newInvestAwardRate);

				} else if (tBorrow.getRepayType().equals(RepayType.ONE_REPAY)) {
					repayList = CalculateInterestUtil.calculateOneRepayList(
							investAwardRepayDto.getRealAmount(),
							tBorrow.getDeadline(), newInvestAwardRate, isDay);
				} else {
					repayList = CalculateInterestUtil
							.calculateMonthRepayInterestList(
									investAwardRepayDto.getRealAmount(),
									tBorrow.getDeadline(), newInvestAwardRate);
				}
				if (repayList != null) {
					for (Map<String, Object> repayMap : repayList) {
					
						TInvestAward tInvestAward = new TInvestAward();
						tInvestAward.setNumOfPeroids((Integer) repayMap
								.get("period"));
						tInvestAward.setAwardAmount((BigDecimal) repayMap
								.get("profitAmount"));
						tInvestAward.setAwardRate(newInvestAwardRate);
						tInvestAward.setAwardStatus(0);
						tInvestAward.setAwardType(1);
						tInvestAward.setBorrowId(borrowId);
						tInvestAward.setCreateTime(nowDate);
						tInvestAward.setInvestId(investAwardRepayDto.getId());
						tInvestAward.setRemarks("新手奖励");
						tInvestAward.setUserId(investAwardRepayDto
								.getInvestorId());
						tInvestAward.setBorrowTitle(tBorrow.getBorrowTitle());
						result = tInvestAwardDao.insert(tInvestAward);
						if (result <= 0) {
							throw new ZeroSQLException();
						}

					}
				}

			}
			
			
		}

		//推荐收益奖励咯
		List<RefereeInvestDto> refereeInvestList = tRefereeRelationService.queryRefereeInvestListByBorrowId(borrowId);
		if (refereeInvestList != null) {
			BigDecimal newAwardRate = new BigDecimal("2");
			for(RefereeInvestDto refereeInvestDto:refereeInvestList){
				List<Map<String, Object>> repayList = null;
				if (tBorrow.getRepayType().equals(RepayType.EQUAL_MONTHLY)) {
					repayList = CalculateInterestUtil
							.calculateEqualMonthlyList(
									refereeInvestDto.getRealAmount(),
									tBorrow.getDeadline(), newAwardRate);

				} else if (tBorrow.getRepayType().equals(RepayType.ONE_REPAY)) {
					repayList = CalculateInterestUtil.calculateOneRepayList(
							refereeInvestDto.getRealAmount(),
							tBorrow.getDeadline(), newAwardRate, isDay);
				} else {
					repayList = CalculateInterestUtil
							.calculateMonthRepayInterestList(
									refereeInvestDto.getRealAmount(),
									tBorrow.getDeadline(), newAwardRate);
				}
				if (repayList != null) {
					for (Map<String, Object> repayMap : repayList) {
						
						TInvestAward tInvestAward = new TInvestAward();
						tInvestAward.setNumOfPeroids((Integer) repayMap
								.get("period"));
						tInvestAward.setAwardAmount((BigDecimal) repayMap
								.get("profitAmount"));
						tInvestAward.setAwardRate(newAwardRate);
						tInvestAward.setAwardStatus(0);
						tInvestAward.setAwardType(3);
						tInvestAward.setBorrowId(borrowId);
						tInvestAward.setCreateTime(nowDate);
						tInvestAward.setInvestId(refereeInvestDto.getId());
						tInvestAward.setRemarks("推荐收益奖励");
						tInvestAward.setUserId(refereeInvestDto.getRefereeId());
						tInvestAward.setBorrowTitle(tBorrow.getBorrowTitle());
						result = tInvestAwardDao.insert(tInvestAward);
						if (result <= 0) {
							throw new ZeroSQLException();
						}

					}
				}
			}
			
			

		}
		return result;
	}
	
	
	
	public List<TInvestAward> querySetoutUsersAward(){
		return tInvestAwardDao.querySetoutUsersAward();
	}
	
	
	
	public long updateTInvestAwardByBorrowId(Long borrowId, Integer numOfPeroid) {
		long result = -1;

		long count = tInvestAwardDao.queryTInvestAwardByBorrowIdAndPeroidCount(
				borrowId, numOfPeroid);

		long updateCount = tInvestAwardDao
				.updateTInvestAwardByBorrowIdAndPeroid(borrowId, numOfPeroid);
		if (count == updateCount) {
			result = 1;
		} else {
			throw new ZeroSQLException();
		}
		return result;
	}

	public long updateTInvestAward(TInvestAward tInvestAward) {
		return tInvestAwardDao.update(tInvestAward);
	}

	public TInvestAward getTInvestAward(TInvestAward tInvestAward) {
		return tInvestAwardDao.get(tInvestAward);
	}

	public PageList queryTInvestAwardPage(TInvestAward tInvestAward,
			Map<String, SearchOperator> options, PageBounds pageBounds) {
		return (PageList) tInvestAwardDao.getAllBy(tInvestAward, options,
				pageBounds);
	}

	public long updateTInvestAwardSuccess(TInvestAward tInvestAward) {
		return tInvestAwardDao.updateTInvestAwardSuccess(tInvestAward) ;
	}

	public long updateTInvestAwardReback(TInvestAward tInvestAward) {
		return tInvestAwardDao.updateTInvestAwardReback(tInvestAward) ;
	}

	public Long updateUserAwardType(TInvestAward tInvestAward) {
		return tInvestAwardDao.updateUserAwardType(tInvestAward) ;
	}

	public PageList selectInvestAward(InvestAwardUserDto investAwardUserDto,PageBounds pageBounds){
		return (PageList) tInvestAwardDao.selectInvestAward(investAwardUserDto,pageBounds);
	}
}