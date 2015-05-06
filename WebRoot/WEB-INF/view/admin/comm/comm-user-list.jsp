<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>

<!DOCTYPE html>
<html>
<head>

<title>后台管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="keyword1,keyword2,keyword3">
<meta name="description" content="This is my page">

<link rel="stylesheet" type="text/css" href="${basePath}/css/admin/admin-all.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/admin/base.css" />
<link rel="stylesheet" type="text/css"
	href="${basePath}/css/admin/bootstrap-responsive.min.css" />
	<link rel="stylesheet" type="text/css"
	href="${basePath}/css/admin/bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="${basePath}/css/validform.css" />	
<link type="text/css" rel="stylesheet" href="${basePath }/js/plugins/jBox/Skins/Blue/jbox.css"/>
	

<script type="text/javascript" src="${basePath}/js/admin/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${basePath}/js/lion-jquery.js"></script>
<script type="text/javascript"
	src="${basePath}/js/admin/jquery-ui-1.8.22.custom.min.js"></script>
<script type="text/javascript" src="${basePath}/js/admin/index.js"></script>
<script type="text/javascript" src="${basePath}/js/Validform_v5.3.2_min.js"></script>
<body style="width: 1024px;height: 100%;">
	<div class="warp" style="width: 1024px;height: 100%;">
	
	<!--右边框架开始-->
		<div id="mainContent" class="Conframe" style="left:0px;top:0px;bottom:0px; width: 100%;height: 100%;">
	
<div class="alert alert-info">
	当前位置<b class="tip"></b>用户列表
</div>
<form id="searchForm" action="queryCommCUserList">
	<table class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<td colspan="6" class="auto-style2">&nbsp;请填写查询条件 <input
					id="curPage" name="curPage" type="hidden" value="1" /></td>

			</tr>
		</thead>
		<tbody>
			<tr>
				<td>用户名</td>
				<td class="detail"><input id="userName" name="userName"
					type="text" value="${model.userName }" />
				</td>
				<td>手机号码</td>
				<td>
					<input id="cellPhone" name="cellPhone"
					type="text" value="${model.cellPhone }" />
				</td>
				<td>是否禁用</td>
				<td><select name="isEnable" id="isEnable"
					class="isEnableCls">
						<option value="">--请选择--</option>
						<option value="0">不禁用</option>
						<option value="1">禁用</option>
				</select>
				</td>
				
			</tr>
			<tr>
				<td colspan="6" align="right"><input class="btn btn-inverse"
					onclick="queryForm()" type="button" value="查询" />&nbsp; <input
					class="btn btn-inverse" onclick="resetForm()" type="button" value="清空" /> 
			</tr>
		</tbody>
	</table>
</form>
<table class="table table-striped table-bordered table-condensed"
	id="top">
	<thead>
		<tr class="tr_detail">
			<td class="auto-style1">序号</td>
			<td class="auto-style1">用户名</td>
			<td class="auto-style1">真实姓名</td>
			<td class="auto-style1">性别</td>
			<td class="auto-style1" style="width: 150px;">邮箱地址</td>
			<td class="auto-style1" style="width: 100px;">手机号码</td>
			<td class="auto-style1" style="width: 120px;">创建时间</td>
			<td class="auto-style1">是否禁用</td>
			<td class="auto-style1">操作</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageBean}" var="bean" varStatus="st">
			<tr>
				<td><a>${bean.id } </a>
				</td>
				<td>${bean.userName}</td>
				<td>
					${bean.realName}
				</td>
				<td>
					<c:choose>
						<c:when test="${bean.sex==1 }">先生</c:when>
						<c:when test="${bean.sex==2 }">女士</c:when>
						<c:otherwise>未知</c:otherwise>
					</c:choose>
				</td>
				<td>${bean.email}</td>
				<td>${bean.cellPhone}</td>
				<td>
					<fmt:formatDate value="${bean.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td><c:choose>
						<c:when test="${bean.isEnable==0 }">不禁用</c:when>
						<c:when test="${bean.isEnable==1 }">禁用</c:when>
					</c:choose>
				</td>
				
				<td><input class="btn btn-inverse" type="button" value="选择"
					onclick="checkUser('${bean.id }','${bean.userName}','${bean.realName}');" />
				
				</td>
			</tr>


		</c:forEach>


		<tr class="tr_pagenumber">
			<td colspan="100"><my:pager curPage="${page.page }"
					pageSize="${page.limit }" totalCount="${page.totalCount }" /></td>
		</tr>
	</tbody>
</table>
  

<!--右边框架结束-->
		</div>
	
		<!--右边框架结束-->

		
	</div>
<script type="text/javascript">

	function checkUser(userId,userName,realName){
		var arrays = {};
		arrays["userId"] = userId;
		arrays["userName"] = userName;
		arrays["realName"] = realName;
		window.parent.checkUser(arrays);
	}
	function goUrl(url){
		$.lionPost(url,{},function(data){
			$("#mainContent").html(data);
		});
	}
	
	
	function queryForm(){
		var url = $("#searchForm").attr("action");
		var arrays = $("#searchForm").serializeArray();
		$.lionPost(url,arrays,function(data){
			$("#mainContent").html(data);
		});
	}
	
	function resetForm(){
		var url = $("#searchForm").attr("action");
		var arrays = null;
		$.lionPost(url,arrays,function(data){
			$("#mainContent").html(data);
		});
	}
	
	function turnPage(curPage){
		$("#curPage").val(curPage);
		queryForm();
	}
	

	
</script>
<script type="text/javascript">
<!--
	$(function() {
		$(".isEnableCls").val('${model.isEnable}');
		$(".whereSource").val('${model.source}');
	});
//-->
</script>
</body>
</html>