package com.p2p.service;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dao.TUserDayGrowthStatisticsDao;
import com.p2p.dto.InfoMsg;
import com.p2p.exception.ZeroSQLException;
import com.p2p.model.TUserDayGrowthStatistics;


/** 每日用户注册统计**/
@Service
public class TUserDayGrowthStatisticsService extends BaseService {
	private static Log log = LogFactory.getLog(TUserDayGrowthStatisticsService.class);
	@Autowired
	private TUserDayGrowthStatisticsDao tUserDayGrowthStatisticsDao;
 
	public long addTUserDayGrowthStatistics(TUserDayGrowthStatistics tUserDayGrowthStatistics) {
		return tUserDayGrowthStatisticsDao.insert(tUserDayGrowthStatistics);
	}
 
	public long updateTUserDayGrowthStatistics(TUserDayGrowthStatistics tUserDayGrowthStatistics) {
		return tUserDayGrowthStatisticsDao.update(tUserDayGrowthStatistics);
	}
 
	public TUserDayGrowthStatistics getTUserDayGrowthStatistics(TUserDayGrowthStatistics tUserDayGrowthStatistics) {
		return tUserDayGrowthStatisticsDao.get(tUserDayGrowthStatistics);
	}
 
	public PageList queryTUserDayGrowthStatisticsPage(TUserDayGrowthStatistics tUserDayGrowthStatistics, PageBounds pageBounds) {
		return tUserDayGrowthStatisticsDao.queryTUserDayGrowthStatisticsPage(tUserDayGrowthStatistics, pageBounds);
	}
	
	public void addDayUserStatistics(){
		TUserDayGrowthStatistics dayStatistics = new  TUserDayGrowthStatistics();
		Long rest = -1l;
		dayStatistics.setStatisticsTime(new Date());
		try{
			dayStatistics.setNewUserNums(tUserDayGrowthStatisticsDao.getNewUserNums());
			dayStatistics.setUsersNumber(tUserDayGrowthStatisticsDao.getUsersNumber());
			dayStatistics.setNewPersonNums(tUserDayGrowthStatisticsDao.getNewPersonNums());
			dayStatistics.setPersonNumber(tUserDayGrowthStatisticsDao.getPersonNumber());
			dayStatistics.setNewInvestNums(tUserDayGrowthStatisticsDao.getNewInvestNums());
			dayStatistics.setInvestNumber(tUserDayGrowthStatisticsDao.getInvestNumber());
			rest = this.addTUserDayGrowthStatistics(dayStatistics);
			if(rest<=0){
				InfoMsg msg = new InfoMsg("统计每日新用户注册，实名，投资人数写入异常", "n");
				throw new ZeroSQLException();
			}
		}catch (Exception e) {
			InfoMsg msg = new InfoMsg("统计每日新用户注册，实名，投资人数异常", "n");
			log.error("统计每日新用户注册，实名，投资人数异常", e);
		}
	}
 }