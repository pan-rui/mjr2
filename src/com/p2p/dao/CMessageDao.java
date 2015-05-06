package com.p2p.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.p2p.model.CMessage;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.mybatis.util.SQLProvider;


/** 消息表**/
@Repository
public interface CMessageDao extends BaseDao<CMessage>{

	@SelectProvider(type = SQLProvider.class,method = "getAll")
	public List<CMessage> getAll(CMessage cMessage,PageBounds row);

	@SelectProvider(type = SQLProvider.class,method = "getAllBy")
	public List<CMessage> getAllBy(CMessage cMessage, Map<String,SearchOperator> options, PageBounds row);


	@SelectProvider(type = SQLProvider.class,method = "get")
	public CMessage get(CMessage cMessage);
	
	/**
	 * 根据用户id查询要显示的站内信
	 */
	public CMessage selectAllMsg(CMessage cMessage);
	
	public List<CMessage> queryOneUserMessage (CMessage cMessage, PageBounds pageBounds);
}