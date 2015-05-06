<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!--右边框架开始-->
<div class="alert alert-info">
	当前位置<b class="tip"></b>用户列表
</div>
<form id="searchForm" action="queryTUserDayGrowthStatisticsList">
	<table class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<td colspan="6" class="auto-style2">&nbsp;请填写查询条件 <input
					id="curPage" name="curPage" type="hidden" value="1" /></td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>时间：</td>
				<td class="detail"><input id="starTime" class="dinp" name="starTime"
					type="text" value="<fmt:formatDate value="${model.starTime }" pattern="yyyy-MM-dd"/>" onClick="WdatePicker()" />
				</td>
				<td>到</td>
				<td>
					<input id="endTime" name="endTime" class="dinp"
					type="text" value="<fmt:formatDate value="${model.endTime }" pattern="yyyy-MM-dd"/>" onClick="WdatePicker()" />
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
			<td class="auto-style1">统计日期</td>
			<td class="auto-style1">新注册人数</td>
			<td class="auto-style1">总注册人数</td>
			<td class="auto-style1">新实名人数</td>
			<td class="auto-style1">总实名人数</td>
			<td class="auto-style1">新投资人数</td>
			<td class="auto-style1">总投资人数</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageBean}" var="bean" varStatus="st">
			<tr>
				<td><a>${bean.id } </a>
				<td>
					<fmt:formatDate value="${bean.statisticsTime}" pattern="yyyy-MM-dd"/>
				</td>
				</td>
				<td>${bean.newUserNums}</td>
				<td>${bean.usersNumber}</td>
				<td>${bean.newPersonNums}</td>
				<td>${bean.personNumber}</td>
				<td>${bean.newInvestNums}</td>
				<td>${bean.investNumber}</td>
			</tr>


		</c:forEach>


		<tr class="tr_pagenumber">
			<td colspan="100"><my:pager curPage="${page.page }"
					pageSize="${page.limit }" totalCount="${page.totalCount }" /></td>
		</tr>
	</tbody>
</table>
<script type="text/javascript" src="${path }js/plugins/My97DatePicker/WdatePicker.js"></script>
<!--右边框架结束-->