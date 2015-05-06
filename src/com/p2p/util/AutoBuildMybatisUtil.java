package com.p2p.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AutoBuildMybatisUtil {
	// 驱动程序名
	private static String driver = "com.mysql.jdbc.Driver";

	// URL指向要访问的数据库名scutcs
	private static String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&amp;characterEncoding=UTF-8&amp;zeroDateTimeBehavior=convertToNull";

	// MySQL配置时的用户名
	private static String user = "root";

	// MySQL配置时的密码
	private static String password = "admin";

	private Connection conn = null;
	
	private String basePath = "D:\\Workspaces\\p2p\\src\\com\\p2p\\";

	private String modelPackName = "com.p2p.model";
	private String modelFilePath = basePath+"model\\";
	private String daoPackName = "com.p2p.dao";
	private String daoFilePath = basePath+"dao\\";
	private String servicePackName = "com.p2p.service";
	private String serviceFilePath = basePath+"service\\";
	private String adminActionPackName = "com.p2p.web.controller.admin";
	private String adminActionFilePath = basePath+"web\\controller\\admin\\";

	public boolean connect() {
		boolean isConnect = false;
		if (conn != null) {
			return true;
		}
		// 加载驱动程序
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			if (!conn.isClosed()) {
				System.out.println("Succeeded connecting to the Database!");
			}
			isConnect = true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		// 连续数据库
		return isConnect;
	}

	public void disConnect() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void createJavaModel(String dbname, String tblName)
			throws SQLException {
		connect();
		String tableSql = "select t.table_name,t.table_comment from information_schema.tables  t where t.table_schema=? and t.table_comment != 'VIEW'";
		if (tblName != null) {
			tableSql += " and t.table_name=?";
		}
		String columnSql = "select table_name, data_type,column_name,column_comment,column_key from information_schema.columns WHERE table_name =? and table_schema=? ";

		PreparedStatement ps = conn.prepareStatement(tableSql);
		PreparedStatement columnPs = conn.prepareStatement(columnSql);
		ps.setString(1, dbname);
		if (tblName != null) {
			ps.setString(2, tblName);
		}
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			String tableName = rs.getString("table_name");
			String tableComment = rs.getString("table_comment");
			// System.out.println("tableName==>"+tableName+"====tableComment==>"+tableComment);

			columnPs.setString(1, tableName);
			columnPs.setString(2, dbname);
			ResultSet columnRs = columnPs.executeQuery();
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			while (columnRs.next()) {
				String dataType = columnRs.getString("data_type");
				String columnName = columnRs.getString("column_name");
				String columnComment = columnRs.getString("column_comment");
				String columnKey = columnRs.getString("column_key");
				// System.out.println("dataType==>"+dataType+"====columnName==>"+columnName
				// + "==>columnComment==>"+columnComment);
				Map<String, String> map = new HashMap<String, String>();
				map.put("dataType", dataType);
				map.put("columnName", columnName);
				map.put("columnComment", columnComment);
				map.put("columnKey", columnKey);// 主键为PRI
				list.add(map);
			}
			createJavaModel(tableName, tableComment, list);
			columnRs.close();
		}
		rs.close();
		ps.close();
		columnPs.close();
		disConnect();
	}

	private void createJavaModel(String tableName, String tableComment,
			List<Map<String, String>> list) throws SQLException {
		System.out.println("==============" + tableName + "====开始=====");

		// model
		Map<String, String> modelMap = this.getModelClass(tableName,
				tableComment, list);
		String modelClassName = modelMap.get("className");
		// dao
		Map<String, String> daoMap = this.getDaoClass(modelClassName,
				tableComment);

		// service
		Map<String, String> serviceMap = this.getServiceClass(modelClassName,
				tableComment);

		Map<String, String> actionMap = this.getAdminActionClass(
				modelClassName, tableComment);

		// adminAction
		try {
			createJavaFile(modelFilePath, modelMap.get("className"),
					modelMap.get("classContent"));
			createJavaFile(daoFilePath, daoMap.get("className"),
					daoMap.get("classContent"));
			createJavaFile(serviceFilePath, serviceMap.get("className"),
					serviceMap.get("classContent"));
			createJavaFile(adminActionFilePath, actionMap.get("className"),
					actionMap.get("classContent"));

		} catch (IOException e) {

			e.printStackTrace();
		}
		System.out.println("==============" + tableName + "====结束=====");

	}

	private Map<String, String> getModelClass(String tableName,
			String tableComment, List<Map<String, String>> list) {
		StringBuffer importBuf = new StringBuffer();
		StringBuffer classBuf = new StringBuffer();
		StringBuffer fieldBuf = new StringBuffer();
		StringBuffer methodBuf = new StringBuffer();

		importBuf.append("package ");
		importBuf.append(modelPackName);
		importBuf.append(";\n\n");
		importBuf.append("import javax.persistence.Column;\n");
		importBuf.append("import javax.persistence.Table;\n");
		importBuf.append("import javax.persistence.Id;\n");
		for (Map<String, String> map : list) {
			String dataType = map.get("dataType");
			String columnName = map.get("columnName");
			String columnComment = map.get("columnComment");
			String columnKey = map.get("columnKey");
			String javaType = getJavaType(dataType);
			if ("Date".equals(javaType)
					&& !importBuf.toString().contains("java.util.Date")) {
				importBuf.append("import java.util.Date;\n");
			}else if ("BigDecimal".equals(javaType)
					&& !importBuf.toString().contains("java.math.BigDecimal")) {
				importBuf.append("import java.math.BigDecimal;\n");
			}
			// 属性添加
			fieldBuf.append("\t/**");
			fieldBuf.append(columnComment);
			fieldBuf.append("**/\n");
			if ("PRI".equals(columnKey)) {
				fieldBuf.append("\t@Id\n");
			} else {
				fieldBuf.append("\t@Column\n");
			}

			fieldBuf.append("\tprivate ");
			fieldBuf.append(javaType);
			fieldBuf.append(" ");
			fieldBuf.append(columnName);
			fieldBuf.append(";\n\n");
			// 方法添加
			methodBuf.append("\tpublic void set");
			methodBuf.append(columnName.substring(0, 1).toUpperCase());
			methodBuf.append(columnName.substring(1));
			methodBuf.append("(");
			methodBuf.append(javaType);
			methodBuf.append(" ");
			methodBuf.append(columnName);
			methodBuf.append(") {\n");
			methodBuf.append("\t\tthis.");
			methodBuf.append(columnName);
			methodBuf.append("=");
			methodBuf.append(columnName);
			methodBuf.append(";\n");
			methodBuf.append("\t}\n\n");

			methodBuf.append("\tpublic ");
			methodBuf.append(javaType);
			methodBuf.append(" get");
			methodBuf.append(columnName.substring(0, 1).toUpperCase());
			methodBuf.append(columnName.substring(1));
			methodBuf.append("(");
			methodBuf.append(") {\n");
			methodBuf.append("\t\treturn this.");
			methodBuf.append(columnName);
			methodBuf.append(";\n");
			methodBuf.append("\t}\n\n");
		}
		StringBuffer className = new StringBuffer();
		String[] talbeNames = tableName.split("_");
		if (talbeNames.length == 1) {
			className.append(talbeNames[0].substring(0, 1).toUpperCase());
			className.append(talbeNames[0].substring(1));
		} else {
			for (int i = 0; i < talbeNames.length; i++) {
				className.append(talbeNames[i].substring(0, 1).toUpperCase());
				className.append(talbeNames[i].substring(1));
			}
		}
		classBuf.append(importBuf);
		classBuf.append("\n/** ");
		classBuf.append(tableComment);
		classBuf.append("**/\n");
		classBuf.append("@Table(name=\"");
		classBuf.append(tableName);
		classBuf.append("\")\n");
		classBuf.append("public class ");
		classBuf.append(className);
		classBuf.append("{\n");
		classBuf.append(fieldBuf);
		classBuf.append(methodBuf);
		classBuf.append("}");
		Map<String, String> map = new HashMap<String, String>();
		map.put("classContent", classBuf.toString());
		map.put("className", className.toString());
		return map;
	}

	private Map<String, String> getDaoClass(String modelClassName,
			String tableComment) {
		StringBuffer daoBuf = new StringBuffer();
		daoBuf.append("package ");
		daoBuf.append(daoPackName);
		daoBuf.append(";\n\n");
		daoBuf.append("import java.util.List;\n");
		daoBuf.append("import java.util.Map;\n");
		daoBuf.append("import org.apache.ibatis.annotations.SelectProvider;\n");
		daoBuf.append("import org.springframework.stereotype.Repository;\n");
		daoBuf.append("import com.github.miemiedev.mybatis.paginator.domain.PageBounds;\n");
		daoBuf.append("import com.p2p.mybatis.annotion.SearchOperator;\n");
		daoBuf.append("import com.p2p.mybatis.util.SQLProvider;\n");
		daoBuf.append("import ");
		daoBuf.append(modelPackName);
		daoBuf.append(".");
		String daoName = modelClassName.toString() + "Dao";
		daoBuf.append(modelClassName);
		daoBuf.append(";\n\n");
		daoBuf.append("\n/** ");
		daoBuf.append(tableComment);
		daoBuf.append("**/\n");
		daoBuf.append("@Repository");
		daoBuf.append("\n");
		daoBuf.append("public interface ");
		daoBuf.append(daoName);
		daoBuf.append(" extends BaseDao<");
		daoBuf.append(modelClassName);
		daoBuf.append(">{\n");
		daoBuf.append("\n\t@SelectProvider(type = SQLProvider.class,method = \"getAll\")\n");
		daoBuf.append("\tpublic List<");
		daoBuf.append(modelClassName);
		daoBuf.append("> getAll(");
		daoBuf.append(modelClassName);
		daoBuf.append(" ");
		daoBuf.append(modelClassName.substring(0, 1).toLowerCase());
		daoBuf.append(modelClassName.substring(1));
		daoBuf.append(",PageBounds row);\n");

		daoBuf.append("\n\t@SelectProvider(type = SQLProvider.class,method = \"getAllBy\")\n");
		daoBuf.append("\tpublic List<");
		daoBuf.append(modelClassName);
		daoBuf.append("> getAllBy(");
		daoBuf.append(modelClassName);
		daoBuf.append(" ");
		daoBuf.append(modelClassName.substring(0, 1).toLowerCase());
		daoBuf.append(modelClassName.substring(1));
		daoBuf.append(", Map<String,SearchOperator> options, PageBounds row);\n\n");

		daoBuf.append("\n\t@SelectProvider(type = SQLProvider.class,method = \"get\")\n");
		daoBuf.append("\tpublic ");
		daoBuf.append(modelClassName);
		daoBuf.append(" get(");
		daoBuf.append(modelClassName);
		daoBuf.append(" ");
		daoBuf.append(modelClassName.substring(0, 1).toLowerCase());
		daoBuf.append(modelClassName.substring(1));
		daoBuf.append(");\n\n");
		daoBuf.append("}");
		Map<String, String> map = new HashMap<String, String>();
		map.put("classContent", daoBuf.toString());
		map.put("className", daoName);
		return map;
	}

	private Map<String, String> getServiceClass(String modelClassName,
			String tableComment) {
		StringBuffer serviceBuf = new StringBuffer();
		String daoClassName = modelClassName.toString() + "Dao";
		String modelName = modelClassName.substring(0, 1).toLowerCase()
				+ modelClassName.substring(1);
		String daoName = daoClassName.substring(0, 1).toLowerCase()
				+ daoClassName.substring(1);
		String serviceClassName = modelClassName.toString() + "Service";
		serviceBuf.append("package ");
		serviceBuf.append(servicePackName);
		serviceBuf.append(";\n\n");
		serviceBuf.append("import java.util.List;\n");
		serviceBuf.append("import java.util.Map;\n\n");
		serviceBuf
				.append("import org.springframework.beans.factory.annotation.Autowired;\n");
		serviceBuf.append("import org.springframework.stereotype.Service;\n\n");
		serviceBuf
				.append("import com.github.miemiedev.mybatis.paginator.domain.PageBounds;\n");
		serviceBuf
				.append("import com.github.miemiedev.mybatis.paginator.domain.PageList;\n");
		serviceBuf.append("import com.p2p.mybatis.annotion.SearchOperator;\n");
		serviceBuf.append("import ");
		serviceBuf.append(daoPackName);
		serviceBuf.append(".");

		serviceBuf.append(daoClassName);
		serviceBuf.append(";\n");
		serviceBuf.append("import ");
		serviceBuf.append(modelPackName);
		serviceBuf.append(".");
		serviceBuf.append(modelClassName);
		serviceBuf.append(";\n\n");

		serviceBuf.append("\n/** ");
		serviceBuf.append(tableComment);
		serviceBuf.append("**/\n");
		serviceBuf.append("@Service");
		serviceBuf.append("\n");
		serviceBuf.append("public class ");
		serviceBuf.append(serviceClassName);
		serviceBuf.append(" extends BaseService {\n");
		serviceBuf.append("\n\n\t@Autowired\n");
		serviceBuf.append("\tprivate ");
		serviceBuf.append(daoClassName);
		serviceBuf.append(" ");
		serviceBuf.append(daoName);
		serviceBuf.append(";\n ");
		// add
		serviceBuf.append("\n\tpublic long add");
		serviceBuf.append(modelClassName);
		serviceBuf.append("(");
		serviceBuf.append(modelClassName);
		serviceBuf.append(" ");
		serviceBuf.append(modelName);
		serviceBuf.append(") {");

		serviceBuf.append("\n\t\treturn ");
		serviceBuf.append(daoName);
		serviceBuf.append(".insert(");
		serviceBuf.append(modelName);
		serviceBuf.append(");\n");
		serviceBuf.append("\t}\n ");

		// upate
		serviceBuf.append("\n\tpublic long update");
		serviceBuf.append(modelClassName);
		serviceBuf.append("(");
		serviceBuf.append(modelClassName);
		serviceBuf.append(" ");
		serviceBuf.append(modelName);
		serviceBuf.append(") {");

		serviceBuf.append("\n\t\treturn ");
		serviceBuf.append(daoName);
		serviceBuf.append(".update(");
		serviceBuf.append(modelName);
		serviceBuf.append(");\n");
		serviceBuf.append("\t}\n ");
		// get
		serviceBuf.append("\n\tpublic ");
		serviceBuf.append(modelClassName);
		serviceBuf.append(" get");
		serviceBuf.append(modelClassName);
		serviceBuf.append("(");
		serviceBuf.append(modelClassName);
		serviceBuf.append(" ");
		serviceBuf.append(modelName);
		serviceBuf.append(") {");

		serviceBuf.append("\n\t\treturn ");
		serviceBuf.append(daoName);
		serviceBuf.append(".get(");
		serviceBuf.append(modelName);
		serviceBuf.append(");\n");
		serviceBuf.append("\t}\n ");

		// query
		serviceBuf.append("\n\tpublic PageList query");
		serviceBuf.append(modelClassName);
		serviceBuf.append("Page(");
		serviceBuf.append(modelClassName);
		serviceBuf.append(" ");
		serviceBuf.append(modelName);
		serviceBuf
				.append(",Map<String, SearchOperator> options, PageBounds pageBounds) {");

		serviceBuf.append("\n\t\treturn (PageList)");
		serviceBuf.append(daoName);
		serviceBuf.append(".getAllBy(");
		serviceBuf.append(modelName);
		serviceBuf.append(", options, pageBounds);\n");
		serviceBuf.append("\t}\n ");

		serviceBuf.append("}");
		Map<String, String> map = new HashMap<String, String>();
		map.put("classContent", serviceBuf.toString());
		map.put("className", serviceClassName);
		return map;
	}

	private Map<String, String> getAdminActionClass(String modelClassName,
			String tableComment) {
		StringBuffer buf = new StringBuffer();

		String modelName = modelClassName.substring(0, 1).toLowerCase()
				+ modelClassName.substring(1);

		String serviceClassName = modelClassName.toString() + "Service";
		String serviceName = serviceClassName.substring(0, 1).toLowerCase()
				+ serviceClassName.substring(1);

		String actionClassName = modelClassName.toString() + "Controller";
		buf.append("package ");
		buf.append(adminActionPackName);
		buf.append(";\n\n");
		buf.append("import java.util.List;\n");
		buf.append("import java.util.Map;\n\n");
		buf.append("import java.util.HashMap;\n\n");
		buf.append("import org.springframework.beans.factory.annotation.Autowired;\n");
		buf.append("import org.springframework.context.annotation.Scope;\n");
		buf.append("import org.springframework.stereotype.Controller;\n");
		buf.append("import org.springframework.web.bind.annotation.RequestMapping;\n\n");
		buf.append("import com.github.miemiedev.mybatis.paginator.domain.PageList;\n");
		buf.append("import com.p2p.dto.InfoMsg;\n");
		buf.append("import com.p2p.mybatis.annotion.SearchOperator;\n");
		buf.append("import ");
		buf.append(servicePackName);
		buf.append(".");

		buf.append(serviceClassName);
		buf.append(";\n");
		buf.append("import ");
		buf.append(modelPackName);
		buf.append(".");
		buf.append(modelClassName);
		buf.append(";\n");
		buf.append("import com.p2p.web.controller.BaseController;\n\n");

		buf.append("@Controller\n");
		buf.append("@Scope(\"prototype\")\n");
		buf.append("@RequestMapping(value = \"/admin\")\n");
		buf.append("public class ");
		buf.append(actionClassName);
		buf.append(" extends BaseController {\n");
		buf.append("\n\n\t@Autowired\n");
		buf.append("\tprivate ");
		buf.append(serviceClassName);
		buf.append(" ");
		buf.append(serviceName);
		buf.append(";\n ");
		// query
		buf.append("\n\t@RequestMapping(value = \"/query");
		buf.append(modelClassName);
		buf.append("List\")\n");
		buf.append("\tpublic String query");
		buf.append(modelClassName);
		buf.append("List (");
		buf.append(modelClassName);
		buf.append(" ");
		buf.append(modelName);
		buf.append(") {");

		buf.append("\n\t\tMap<String, SearchOperator> options = new HashMap<String, SearchOperator>(); ");
		buf.append("\n\t\tPageList pageList = ");
		buf.append(serviceName);
		buf.append(".query");
		buf.append(modelClassName);
		buf.append("Page(");
		buf.append(modelName);
		buf.append(", options,");
		buf.append("pageBounds);\n\n");
		buf.append("\t\trequest.setAttribute(\"pageBean\", pageList);\n");
		buf.append("\t\trequest.setAttribute(\"page\", pageList.getPaginator());\n");
		buf.append("\t\trequest.setAttribute(\"model\", ");
		buf.append(modelName);
		buf.append(");\n");
		buf.append("\t\treturn \"admin/xx/xx-list\";\n");
		buf.append("\t}\n ");

		// addIndex
		buf.append("\n\t@RequestMapping(value = \"/add");
		buf.append(modelClassName);
		buf.append("Index\")\n");
		buf.append("\tpublic String add");
		buf.append(modelClassName);
		buf.append("Index () {\n");

		buf.append("\t\treturn \"admin/xx/xx-add\";\n");
		buf.append("\t}\n ");

		// add
		buf.append("\n\t@RequestMapping(value = \"/add");
		buf.append(modelClassName);
		buf.append("\")\n");
		buf.append("\tpublic String add");
		buf.append(modelClassName);
		buf.append(" (");
		buf.append(modelClassName);
		buf.append(" ");
		buf.append(modelName);
		buf.append(") {");

		buf.append("\n\t\tlong result = ");
		buf.append(serviceName);
		buf.append(".add");
		buf.append(modelClassName);
		buf.append("(");
		buf.append(modelName);
		buf.append(");\n");
		buf.append("\t\tif (result > 0) {\n");
		buf.append("\t\t\tthis.writeJson(new InfoMsg(\"保存成功\", \"y\"));\n");
		buf.append("\t\t} else {\n");
		buf.append("\t\t\tthis.writeJson(new InfoMsg(\"保存失败\", \"n\"));\n");
		buf.append("\t\t}\n");
		buf.append("\t\treturn null;\n");
		buf.append("\t}\n ");

		// updateIndex
		buf.append("\n\t@RequestMapping(value = \"/update");
		buf.append(modelClassName);
		buf.append("Index\")\n");
		buf.append("\tpublic String update");
		buf.append(modelClassName);
		buf.append("Index ");
		buf.append(" (");
		buf.append(modelClassName);
		buf.append(" ");
		buf.append(modelName);
		buf.append(") {");
		buf.append("\n\t\t");
		buf.append(modelClassName);
		buf.append(" ");
		buf.append(modelName);
		buf.append("Model = ");
		buf.append(serviceName);
		buf.append(".get");
		buf.append(modelClassName);
		buf.append("(");
		buf.append(modelName);
		buf.append(");\n");
		buf.append("\t\trequest.setAttribute(\"");
		buf.append(modelName);
		buf.append("Model\", ");
		buf.append(modelName);
		buf.append("Model);\n");
		buf.append("\t\treturn \"admin/xx/xx-update\";\n");
		buf.append("\t}\n ");

		// update
		buf.append("\n\t@RequestMapping(value = \"/update");
		buf.append(modelClassName);
		buf.append("\")\n");
		buf.append("\tpublic String update");
		buf.append(modelClassName);
		buf.append(" (");
		buf.append(modelClassName);
		buf.append(" ");
		buf.append(modelName);
		buf.append(") {");

		buf.append("\n\t\tlong result = ");
		buf.append(serviceName);
		buf.append(".update");
		buf.append(modelClassName);
		buf.append("(");
		buf.append(modelName);
		buf.append(");\n");
		buf.append("\t\tif (result > 0) {\n");
		buf.append("\t\t\tthis.writeJson(new InfoMsg(\"保存成功\", \"y\"));\n");
		buf.append("\t\t} else {\n");
		buf.append("\t\t\tthis.writeJson(new InfoMsg(\"保存失败\", \"n\"));\n");
		buf.append("\t\t}\n");
		buf.append("\t\treturn null;\n");
		buf.append("\t}\n ");

		// queryIndex
		buf.append("\n\t@RequestMapping(value = \"/query");
		buf.append(modelClassName);
		buf.append("Index\")\n");
		buf.append("\tpublic String query");
		buf.append(modelClassName);
		buf.append("Index ");
		buf.append(" (");
		buf.append(modelClassName);
		buf.append(" ");
		buf.append(modelName);
		buf.append(") {");
		buf.append("\n\t\t");
		buf.append(modelClassName);
		buf.append(" ");
		buf.append(modelName);
		buf.append("Model = ");
		buf.append(serviceName);
		buf.append(".get");
		buf.append(modelClassName);
		buf.append("(");
		buf.append(modelName);
		buf.append(");\n");
		buf.append("\t\trequest.setAttribute(\"");
		buf.append(modelName);
		buf.append("Model\", ");
		buf.append(modelName);
		buf.append("Model);\n");
		buf.append("\t\treturn \"admin/xx/xx-detail\";\n");
		buf.append("\t}\n ");
		buf.append("}");
		Map<String, String> map = new HashMap<String, String>();
		map.put("classContent", buf.toString());
		map.put("className", actionClassName);
		return map;
	}

	private String getJavaType(String dataType) {
		String type = "";
		if ("bigint".equals(dataType)) {
			type = "Long";
		} else if ("int".equals(dataType)) {
			type = "Integer";
		} else if ("text".equals(dataType) || "varchar".equals(dataType)) {
			type = "String";
		} else if ("datetime".equals(dataType) || "date".equals(dataType)) {
			type = "Date";
		} else if ("decimal".equals(dataType)) {
			type = "BigDecimal";
		} else if ("char".equals(dataType)) {
			type = "Char";
		} else if ("tinytext".equals(dataType)) {

			type = "String ";
		} else {

			type = "unknow";
		}
		return type;
	}

	private void createJavaFile(String filePath, String fileName, String content)
			throws IOException {
		File file = new File(filePath + fileName + ".java");
		OutputStream output = null;
		try {
			output = new FileOutputStream(file);
			output.write(content.getBytes());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (output != null) {
				output.close();
			}
		}

	}

	public static void main(String[] args) throws SQLException {

		AutoBuildMybatisUtil db = new AutoBuildMybatisUtil();
		db.createJavaModel("test", "t_user_day_growth_statistics");

	}
}
