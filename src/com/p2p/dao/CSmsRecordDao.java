package com.p2p.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.p2p.model.CSmsRecord;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.mybatis.util.SQLProvider;


/** 短信发送记录**/
@Repository
public interface CSmsRecordDao extends BaseDao<CSmsRecord>{

	@SelectProvider(type = SQLProvider.class,method = "getAll")
	public List<CSmsRecord> getAll(CSmsRecord cSmsRecord,PageBounds row);

	@SelectProvider(type = SQLProvider.class,method = "getAllBy")
	public List<CSmsRecord> getAllBy(CSmsRecord cSmsRecord, Map<String,SearchOperator> options, PageBounds row);


	@SelectProvider(type = SQLProvider.class,method = "get")
	public CSmsRecord get(CSmsRecord cSmsRecord);

	public CSmsRecord getCSmsRecordByPhone(CSmsRecord csr);

}