package com.p2p.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.p2p.dto.TFundRecordDto;
import com.p2p.dto.UserMoneyDto;
import com.p2p.model.TFundRecord;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.mybatis.util.SQLProvider;


/** 资金记录表**/
@Repository
public interface TFundRecordDao extends BaseDao<TFundRecord>{

	@SelectProvider(type = SQLProvider.class,method = "getAll")
	public List<TFundRecord> getAll(TFundRecord tFundRecord,PageBounds row);

	@SelectProvider(type = SQLProvider.class,method = "getAllBy")
	public List<TFundRecord> getAllBy(TFundRecord tFundRecord, Map<String,SearchOperator> options, PageBounds row);


	@SelectProvider(type = SQLProvider.class,method = "get")
	public TFundRecord get(TFundRecord tFundRecord);
	
	public List<TFundRecordDto> selectTFundRecordlist(TFundRecordDto tFundRecordDto,PageBounds pageBounds);
	public TFundRecordDto selectTFundRecordDetail(Long id);
	
	//查看用户列表
	public List<UserMoneyDto> selectUserList(UserMoneyDto userMoneyDto,PageBounds pageBounds);
	
	//个人中心资金流水
	public List<TFundRecord> selectFundRecord(TFundRecord tfundrecord,PageBounds pagebounds);
}