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
	<!--右边框架开始-->
<div class="alert alert-info">
	当前位置<b class="tip"></b>资金流水
</div>
<form id="searchForm" action="selectTFundRecordList">
<input id="userId" name="userId" value="${iddd }" type="hidden" value="1" />
<input id="curPage" name="curPage" type="hidden" value="1" />
</form>
<table class="table table-striped table-bordered table-condensed">
<tr>
	<td class="auto-style1">宝付余额：${baofoomoney}</td>
	<td class="auto-style1">账户余额：${tac.usableAmount}</td>
	<td class="auto-style1">账户冻结金额：${tac.frozenAmount}</td>
	</tr>
	</table>
<table class="table table-striped table-bordered table-condensed"
	id="top">
	
	<thead>
		<tr class="tr_detail">
			<td class="auto-style1">用户名</td>
			<td class="auto-style1">姓名</td>
			<td class="auto-style1">电话</td>
			<td class="auto-style1">类型</td>
			<td class="auto-style1">操作金额</td>
			<td class="auto-style1">操作时间</td>
			
			<td class="auto-style1">操作</td>


		</tr>
	</thead>
	
	<tbody>
	
		<c:forEach items="${pageBean}" var="bean" varStatus="st">
			<tr>
				<td>${bean.userName}</td>
				<td>${bean.realName }</td>
				<td>${bean.cellPhone}</td>
				<td>${bean.fundMode}</td>
				<td>${bean.operAmount}</td>
				<td><fmt:formatDate value="${bean.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				
				
				<td><input
					class="btn btn-inverse" type="button" value="查看"
					onclick="openUserFundrecorddetail('${bean.id}');" />&nbsp;</td> 
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
	<script type="text/javascript" src="${basePath }/js/plugins/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="${basePath }/js/plugins/jBox/i18n/jquery.jBox-zh-CN.js"></script>
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
var jbox2;
function openUserFundrecorddetail(id){
	jbox2 = $.jBox.open("post:selectTFundRecordDetail?id="+id, "用户资金流水列表", 1024, 600, {
		buttons : {
			'关闭' : true
		}
	});
}

//-->
</script>
</body>
</html>
