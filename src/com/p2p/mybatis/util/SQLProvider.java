package com.p2p.mybatis.util;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.DELETE_FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.p2p.mybatis.annotion.SearchOperator;

public class SQLProvider {

	public String insert(Object obj) throws Exception {
		BEGIN();

		INSERT_INTO(PojoUtil.tablename(obj));
		PojoUtil.caculationColumnList(obj);
		VALUES(PojoUtil.returnInsertColumnsName(obj),
				PojoUtil.returnInsertColumnsDefine(obj));

		return SQL();
	}

	public String update(Object obj) {
		String idname = PojoUtil.id(obj);

		BEGIN();
		UPDATE(PojoUtil.tablename(obj));
		PojoUtil.caculationColumnList(obj);
		SET(PojoUtil.returnUpdateSet(obj));
		WHERE(idname + "=#{" + idname + "}");

		return SQL();
	}

	public String deleteById(Object obj) {
		String idname = PojoUtil.id(obj);

		BEGIN();
		DELETE_FROM(PojoUtil.tablename(obj));
		WHERE(idname + "=#{" + idname + "}");

		return SQL();
	}

	public String delete(Object obj) {

		BEGIN();
		DELETE_FROM(PojoUtil.tablename(obj));

		String paramString = PojoUtil.returnWhereDefine(obj);
		if (StringUtils.isNotBlank(paramString)) {
			WHERE(paramString);
		}
		return SQL();
	}

	public String get(Object obj) throws Exception {
		PojoUtil.caculationColumnList(obj);
		String idname = PojoUtil.id(obj);
		BEGIN();
		SELECT(PojoUtil.returnSelectColumnsName(obj));
		FROM(PojoUtil.tablename(obj));
		WHERE(idname + "=#{" + idname + "}");
		return SQL();
	}

	/**
	 * 查询所有
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public String getAll(Object obj) throws Exception {
		PojoUtil.caculationColumnList(obj);
		BEGIN();
		SELECT(PojoUtil.returnSelectColumnsName(obj));
		FROM(PojoUtil.tablename(obj));
		return SQL();
	}

	public String getAllByCondition(Object obj) throws Exception {

		PojoUtil.caculationColumnList(obj);
		BEGIN();
		SELECT(PojoUtil.returnSelectColumnsName(obj));
		FROM(PojoUtil.tablename(obj));
		String paramString = PojoUtil.returnWhereDefine(obj);
		if (StringUtils.isNotBlank(paramString)) {
			WHERE(paramString);
		}
		return SQL();
	}

	@SuppressWarnings("unchecked")
	public String getAllBy(Map<String, Object> para) throws Exception {

		Object obj = para.get("0");
		Map<String, SearchOperator> options = (Map<String, SearchOperator>) para
				.get("1");

		PojoUtil.caculationColumnList(obj);
		BEGIN();
		SELECT(PojoUtil.returnSelectColumnsName(obj));
		FROM(PojoUtil.tablename(obj));
		String paramString = PojoUtil.returnWhereDefine(obj, options);
		if (StringUtils.isNotBlank(paramString)) {
			WHERE(paramString);
		}
		return SQL();
	}

}
