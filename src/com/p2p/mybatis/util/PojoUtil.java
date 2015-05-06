package com.p2p.mybatis.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;

import com.p2p.mybatis.annotion.SearchOperator;

/**
 * 解析POJO的类
 */
public class PojoUtil {

	private static final String PROPERTYNAME = "property";// 属性名This
	private static final String COLUMNNAME = "column"; // 列名@Column
	private static final String CONCATID = "concatId"; // 列唯一@Column

	/**
	 * 获取POJO对应的表名 需要POJO中的属性定义@Table(name)
	 * 
	 * @return
	 */
	public static String tablename(Object obj) {
		Table table = obj.getClass().getAnnotation(Table.class);
		if (table != null)
			return table.name();
		else
			throw new RuntimeException(
					"undefine POJO @Table, need Tablename(@Table(name))");
	}

	/**
	 * 获取POJO中的主键字段名 需要定义@Id
	 * 
	 * @return
	 */
	public static String id(Object obj) {
		for (Field field : obj.getClass().getDeclaredFields()) {
			if (field.isAnnotationPresent(Id.class))
				return field.getName();
		}

		throw new RuntimeException("undefine POJO @Id");
	}

	/**
	 * 用于存放POJO的列信息
	 */
	@SuppressWarnings("rawtypes")
	private static Map<Class, List<Map<String, String>>> columnMap = new HashMap<Class, List<Map<String, String>>>();

	private static boolean isNull(Object obj, String fieldname) {
		try {
			Field field = obj.getClass().getDeclaredField(fieldname);
			return isNull(obj, field);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		return false;
	}

	private static boolean isNull(Object obj, Field field) {
		try {
			// 私有变量权限放开，请切记关闭
			boolean accessible = field.isAccessible();
			field.setAccessible(true);

			boolean result = field.get(obj) == null;
			field.setAccessible(accessible);
			return result;
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * 用于计算类定义 需要POJO中的属性定义@Column(name)
	 */
	public static void caculationColumnList(Object obj) {
		if (columnMap.containsKey(obj.getClass()))
			return;

		Field[] fields = obj.getClass().getDeclaredFields();
		List<Map<String, String>> columnList = new ArrayList<Map<String, String>>(
				fields.length);

		for (Field field : fields) {
			Map<String, String> columnsMap = new HashMap<String, String>();
			// 用于拼接#{id}
			Boolean concatId = false;

			if (field.isAnnotationPresent(Column.class)) {
				Column column = field.getAnnotation(Column.class);
				String propertyName = field.getName();
				String columnName = StringUtils.isEmpty(column.name()) ? propertyName
						: column.name();
				columnsMap.put(COLUMNNAME, columnName);
				columnsMap.put(PROPERTYNAME, propertyName);
				columnsMap.put(CONCATID, concatId.toString());
				columnList.add(columnsMap);
			} else if (field.isAnnotationPresent(Id.class)) {
				String propertyName = field.getName();
				String columnsName = field.getName();
				columnsMap.put(PROPERTYNAME, propertyName);
				columnsMap.put(COLUMNNAME, columnsName);
				columnsMap.put(CONCATID, "false");// 因为ID已经是唯一，就不做唯一处理
				columnList.add(columnsMap);
			}
		}
		columnMap.put(obj.getClass(), columnList);
	}

	/**
	 * 获取用于WHERE的 有值字段表
	 * 
	 * @return
	 */
	public List<WhereColumn> returnWhereColumnsName(Object obj) {
		Field[] fields = obj.getClass().getDeclaredFields();
		List<WhereColumn> columnList = new ArrayList<WhereColumn>(fields.length);

		for (Field field : fields) {
			if (field.isAnnotationPresent(Column.class) && !isNull(obj, field))
				columnList.add(new WhereColumn(field.getName(), field
						.getGenericType().equals(String.class)));
		}

		return columnList;
	}

	/**
	 * Where条件信息
	 * 
	 */
	public class WhereColumn {
		public String name;
		public boolean isString;

		public WhereColumn(String name, boolean isString) {
			this.name = name;
			this.isString = isString;
		}
	}

	/**
	 * 用于获取Insert的字段累加
	 * 
	 * @return
	 */
	public static String returnInsertColumnsName(Object obj) {
		StringBuilder sb = new StringBuilder();

		List<Map<String, String>> list = columnMap.get(obj.getClass());
		int i = 0;
		String objId = id(obj);
		for (Map<String, String> column : list) {
			if (isNull(obj, column.get(PROPERTYNAME))
					&& !column.get(PROPERTYNAME).equals(objId))
				continue;

			if (i++ != 0)
				sb.append(',');
			sb.append(column.get(COLUMNNAME));
		}
		return sb.toString();
	}

	/**
	 * 用于获取Insert的字段映射累加
	 * 
	 * @return
	 */
	public static String returnInsertColumnsDefine(Object obj) {
		StringBuilder sb = new StringBuilder();

		List<Map<String, String>> list = columnMap.get(obj.getClass());
		int i = 0;
		String objId = id(obj);
		for (Map<String, String> column : list) {
			if (isNull(obj, column.get(PROPERTYNAME))
					&& !column.get(PROPERTYNAME).equals(objId))
				continue;

			if (i++ != 0)
				sb.append(',');
			sb.append("#{").append(column.get(PROPERTYNAME)).append('}');
			if (column.get(CONCATID).equals("true"))
				sb.append("||#{" + objId + "}");
		}
		return sb.toString();
	}

	/**
	 * 用于获取Update Set的字段累加
	 * 
	 * @return
	 */
	public static String returnUpdateSet(Object obj) {
		StringBuilder sb = new StringBuilder();

		List<Map<String, String>> list = columnMap.get(obj.getClass());
		int i = 0;
		String objId = id(obj);
		for (Map<String, String> column : list) {
			if (isNull(obj, column.get(PROPERTYNAME))
					|| column.get(PROPERTYNAME).equals(objId))
				continue;

			if (i++ != 0)
				sb.append(',');
			sb.append(column.get(COLUMNNAME)).append("=#{")
					.append(column.get(PROPERTYNAME)).append('}');
		}
		return sb.toString();
	}

	/**
	 * 用于获取Select的字段映射
	 * 
	 * @param obj
	 * @return
	 */
	public static String returnSelectColumnsName(Object obj) {
		StringBuilder sb = new StringBuilder();
		List<Map<String, String>> list = columnMap.get(obj.getClass());
		for (int i = 0; i < list.size(); i++) {
			Map<String, String> p = list.get(i);
			String columnname = p.get(COLUMNNAME);
			String propertyname = p.get(PROPERTYNAME);
			if (columnname.equals(propertyname)) {
				sb.append(columnname);
			} else {
				sb.append(columnname);
				sb.append(" ");
				sb.append(propertyname);
			}
			if (i != list.size() - 1) {
				sb.append(" , ");
			}
		}
		return sb.toString();
	}

	/**
	 * 打印类字段信息
	 */
	public String toPojoString(Object obj) {
		Field[] fields = obj.getClass().getDeclaredFields();
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for (Field f : fields) {
			if (Modifier.isStatic(f.getModifiers())
					|| Modifier.isFinal(f.getModifiers()))
				continue;
			Object value = null;
			try {
				f.setAccessible(true);
				value = f.get(obj);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			if (value != null)
				sb.append(f.getName()).append('=').append(value).append(',');
		}
		sb.append(']');

		return sb.toString();
	}

	public static String returnWhereDefine(Object obj,
			Map<String, SearchOperator> options) {
		StringBuilder sb = new StringBuilder();
		List<Map<String, String>> list = columnMap.get(obj.getClass());

		int i = 0;
		sb.append(" 1=1 ");
		for (Map<String, String> column : list) {
			if (isNull(obj, column.get(PROPERTYNAME)))
				continue;
			String propertyname = column.get(PROPERTYNAME);
			String columnname = column.get(COLUMNNAME);
			SearchOperator so = options.get(propertyname);
			if (so != null) {

				sb.append(" and ");

				sb.append(columnname);
				switch (so) {
				case EQ:
					sb.append(" = ");
					sb.append("#{param1." + propertyname + "}");
					break;
				case LIKE:
					sb.append(" like ");
					sb.append("\"%\"#{param1." + propertyname + "}\"%\"");
					break;
				case GT:
					sb.append(" > ");
					sb.append("#{param1." + propertyname + "}");
					break;
				case LT:
					sb.append(" < ");
					sb.append("#{param1." + propertyname + "}");
					break;
				case GTE:
					sb.append(" >= ");
					sb.append("#{param1." + propertyname + "}");
					break;
				case LTE:
					sb.append(" <= ");
					sb.append("#{param1." + propertyname + "}");
					break;
				}
			}
		}
		return sb.toString();
	}

	public static String returnWhereDefine(Object obj) {
		StringBuilder sb = new StringBuilder();
		List<Map<String, String>> list = columnMap.get(obj.getClass());

		int i = 0;
		sb.append(" 1=1 ");
		for (Map<String, String> column : list) {
			if (isNull(obj, column.get(PROPERTYNAME))) {
				continue;
			}
			String propertyname = column.get(PROPERTYNAME);
			String columnname = column.get(COLUMNNAME);

			sb.append(" and ");

			sb.append(columnname);

			sb.append(" = ");
			sb.append("#{" + propertyname + "}");
		}
		return sb.toString();
	}
}
