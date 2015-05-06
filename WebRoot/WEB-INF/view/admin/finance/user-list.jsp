<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!--右边框架开始-->
<div class="alert alert-info">
	当前位置<b class="tip"></b>资金流水
</div>
<form id="searchForm" action="selectUserList">
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
				<td class="detail"><input id="userName" class="dinp" name="userName"
					type="text" value="${model.userName }" />
				</td>
				<td>手机号码</td>
				<td>
					<input id="cellPhone" name="cellPhone" class="dinp"
					type="text" value="${model.cellPhone }" />
				</td>
				<td>真实姓名</td>
				<td>
					<input id="realName" name="realName" class="dinp"
					type="text" value="${model.realName }" />
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
			<td class="auto-style1">用户名</td>
			<td class="auto-style1">姓名</td>
			<td class="auto-style1">电话</td>
			
			<td class="auto-style1">可用余额</td>
			<td class="auto-style1">冻结金额</td>
			
			<td class="auto-style1">操作</td>


		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageBean}" var="bean" varStatus="st">
			<tr>
				<td>${bean.userName}</td>
				<td>${bean.realName }</td>
				<td>${bean.cellPhone}</td>
				<td>${bean.usableAmount}</td>
				<td>${bean.frozenAmount}</td>
				
				
				
				
				<td><input
					class="btn btn-inverse" type="button" value="查看"
					onclick="openUserFundrecord('${bean.id}');" />&nbsp;</td> 
			</tr>


		</c:forEach>


		<tr class="tr_pagenumber">
			<td colspan="100"><my:pager curPage="${page.page }" 
					pageSize="${page.limit }" totalCount="${page.totalCount }" /></td>
		</tr>
	</tbody>
</table>


<!--右边框架结束-->
<script type="text/javascript" src="${basePath }/js/plugins/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="${basePath }/js/plugins/jBox/i18n/jquery.jBox-zh-CN.js"></script>
<script type="text/javascript">
<!--
var jbox;
function openUserFundrecord(id){
	jbox = $.jBox.open("iframe:selectTFundRecordList?userId="+id, "用户资金流水列表", 1024, 600, {
		buttons : {
			'关闭' : true
		}
	});
}
//iframe
//-->

</script>
