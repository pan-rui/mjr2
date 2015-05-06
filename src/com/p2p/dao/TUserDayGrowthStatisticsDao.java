package com.p2p.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.model.TUserDayGrowthStatistics;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.mybatis.util.SQLProvider;


/** 每日用户注册统计**/
@Repository
public interface TUserDayGrowthStatisticsDao extends BaseDao<TUserDayGrowthStatistics>{

	@SelectProvider(type = SQLProvider.class,method = "getAll")
	public List<TUserDayGrowthStatistics> getAll(TUserDayGrowthStatistics tUserDayGrowthStatistics,PageBounds row);

	@SelectProvider(type = SQLProvider.class,method = "getAllBy")
	public List<TUserDayGrowthStatistics> getAllBy(TUserDayGrowthStatistics tUserDayGrowthStatistics, Map<String,SearchOperator> options, PageBounds row);


	@SelectProvider(type = SQLProvider.class,method = "get")
	public TUserDayGrowthStatistics get(TUserDayGrowthStatistics tUserDayGrowthStatistics);
	/**获取当天注册人数**/
	public Integer getNewUserNums();
	/**获取总注册数**/
	public Integer getUsersNumber();
	/**获取当天实名人数**/
	public Integer getNewPersonNums();
	/**获取总实名数**/
	public Integer getPersonNumber();
	/**获取当天投资人数**/
	public Integer getNewInvestNums();
	/**获取总投资数**/
	public Integer getInvestNumber();
	
	public PageList queryTUserDayGrowthStatisticsPage(TUserDayGrowthStatistics tUserDayGrowthStatistics, PageBounds pageBounds);
}