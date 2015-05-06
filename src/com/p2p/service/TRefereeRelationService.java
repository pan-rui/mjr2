package com.p2p.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dao.TRefereeRelationDao;
import com.p2p.dto.InfoMsg;
import com.p2p.dto.RefereeInvestDto;
import com.p2p.dto.TRefereeDto;
import com.p2p.dto.TRefereeRelationDto;
import com.p2p.model.TRefereeRelation;
import com.p2p.mybatis.annotion.SearchOperator;


/** 推荐关系表**/
@Service
public class TRefereeRelationService extends BaseService {
	private static Log log = LogFactory.getLog(TRefereeRelationService.class);

	@Autowired
	private TRefereeRelationDao tRefereeRelationDao;
	@Autowired
	private TCouponService couponService;
	
	/**
	 * 分页取值，推荐关系
	 * @param cSms
	 * @param pageBounds
	 * @return
	 */
	public PageList queryRefereeUserAndTUser(TRefereeRelationDto trr, PageBounds pageBounds){
		return (PageList) tRefereeRelationDao.queryRefereeUserAndTUser(trr, pageBounds);
	};
	public PageList queryRefereeUserAndTUser2(TRefereeRelationDto trr, PageBounds pageBounds){
		return (PageList) tRefereeRelationDao.queryRefereeUserAndTUser2(trr, pageBounds);
	};
	
	public long addTRefereeRelation(TRefereeRelation tRefereeRelation) {
		return tRefereeRelationDao.insert(tRefereeRelation);
	}
 
	public long updateTRefereeRelation(TRefereeRelation tRefereeRelation) {
		return tRefereeRelationDao.update(tRefereeRelation);
	}
 
	public TRefereeRelation getTRefereeRelation(TRefereeRelation tRefereeRelation) {
		return tRefereeRelationDao.get(tRefereeRelation);
	}
 
	public PageList queryTRefereeRelationPage(TRefereeRelation tRefereeRelation,Map<String, SearchOperator> options, PageBounds pageBounds) {
		return (PageList)tRefereeRelationDao.getAllBy(tRefereeRelation, options, pageBounds);
	}
	
	/**
	 * 循环添加推荐奖励
	 * @return
	 */
	public InfoMsg selectRefereelist(){
		
		List<TRefereeDto> list=tRefereeRelationDao.selectRefereelist();
		if(list!=null){
			for (TRefereeDto tRefereeDto : list) {
				//增加推荐奖励
				long boo=couponService.insertcouponforuserid(tRefereeDto.getRefereeId());
				if(boo<0){
					InfoMsg msg=new InfoMsg("添加推荐奖励失败", "n");
					return msg;
				}
				//修改推荐表的推荐奖励领取字段
				int i=tRefereeRelationDao.updateReferee(tRefereeDto.getInvestorId());
				if(i<0){
					InfoMsg msg=new InfoMsg("修改推荐奖励状态失败", "n");
					return msg;
				}
			}
		}
		
		InfoMsg msg=new InfoMsg("推荐奖励发放完成", "y");
		return msg;
	}
	
	public Long selectrefereeIdforuserId(Long userId){
		return tRefereeRelationDao.selectrefereeIdforuserId(userId);
	}
	public List<RefereeInvestDto> queryRefereeInvestListByBorrowId(Long borrowId) {
		return tRefereeRelationDao.queryRefereeInvestListByBorrowId(borrowId);
	}
 }