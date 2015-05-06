package com.p2p.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.p2p.dto.TBankCardDto;
import com.p2p.model.TBankCard;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.mybatis.util.SQLProvider;


/** 银行卡信息**/
@Repository
public interface TBankCardDao extends BaseDao<TBankCard>{

	@SelectProvider(type = SQLProvider.class,method = "getAll")
	public List<TBankCard> getAll(TBankCard tBankCard,PageBounds row);

	@SelectProvider(type = SQLProvider.class,method = "getAllBy")
	public List<TBankCard> getAllBy(TBankCard tBankCard, Map<String,SearchOperator> options, PageBounds row);


	@SelectProvider(type = SQLProvider.class,method = "get")
	public TBankCard get(TBankCard tBankCard);
	
	public List<TBankCardDto> selectTBankCardList(TBankCardDto tbankCardDto,PageBounds pageBounds);
	public TBankCardDto selectTBankCardDeatil(Long id);
	
	/**
	 * 个人中心用户银行卡管理
	 * @param tBankCard
	 * @param pageBounds
	 * @return
	 */
	public List<TBankCard> selectBankCardlist(TBankCard tBankCard);
	public int insertBankCard(TBankCard tBankCard);
	public int deleteBankCardforid(TBankCard tBankCard);
	public int updateBankCard(TBankCard tBankCard);
	/**
	 * 获取某用户所有银行卡账号
	 * @param userId
	 * @return
	 */
	public List<String> querybankCardNoListByUserId(Long userId);
	
}