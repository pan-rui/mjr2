<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>

<jsp:include page="/WEB-INF/view/admin/comm/comm-admin-top.jsp"></jsp:include>
<!--右边框架开始-->

<div class="alert alert-info">
	当前位置<b class="tip"></b>投资人收款列表
</div>
<form id="searchForm" action="queryTRepayList">
	<table class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<td colspan="6" class="auto-style2">&nbsp;请填写查询条件 <input
					id="curPage" name="curPage" type="hidden" value="1" /></td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>投资人用户名</td>
				<td class="detail"><input id="userName" name="userName"
					type="text" value="${model.userName }" />
					<input id="borrowId" name="borrowId"
					type="hidden" value="${model.borrowId }" />
					<input id="numOfPeriods" name="numOfPeriods"
					type="hidden" value="${model.numOfPeriods }"  />
				</td>
				<td>投资者真实姓名</td>
				<td><input id="realName" name="realName" type="text"
					value="${model.realName}" />
				</td>
				
			</tr>
			<tr>
				<td colspan="4" align="right"><input class="btn btn-inverse"
					onclick="queryForm()" type="button" value="查询" />&nbsp; <input
					class="btn btn-inverse" type="reset" value="清空" />&nbsp; &nbsp;</td>
			</tr>
		</tbody>
	</table>
</form>


<table class="table table-striped table-bordered table-condensed"
	id="top">
	<thead>
		<tr class="tr_detail">
			<td class="auto-style1">投资人用户名</td>
			<td class="auto-style1">投资人真实姓名</td>
			<td class="auto-style1">当前期数/总期数</td>
			<td class="auto-style1">还款本金</td>
			<td class="auto-style1">还款利息</td>
			<td class="auto-style1">还款本息</td>
			<td class="auto-style1">预期还款时间</td>
			<td class="auto-style1">实际还款时间</td>
			<td class="auto-style1">是否已还款</td>
		


		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageBean}" var="bean" varStatus="st">
			<tr>
					<td><a>${bean.userName } </a></td>
				<td>${bean.realName}</td>
				<td>${bean.numOfPeriods}/${bean.peroids}</td>
				<td>${bean.capitalAmount}</td>
				<td>${bean.profitAmount}</td>
				<td>${bean.capitalAmount+bean.profitAmount}</td>
				<td><fmt:formatDate value="${bean.repayDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${bean.realRepayTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><c:choose>
						<c:when test="${bean.repayStatus==1}">否</c:when>
						<c:otherwise>是</c:otherwise>
					</c:choose></td>
				
			</tr>
		</c:forEach>


		<tr class="tr_pagenumber">
			<td colspan="100"><my:pager curPage="${page.page }"
					pageSize="${page.limit }" totalCount="${page.totalCount }" /></td>
		</tr>
	</tbody>
</table>

<script type="text/javascript">
<!--
	$(function() {
		$(".repayStatus").val('${model.repayStatus}');

	});

//-->
</script>
<!--右边框架结束-->

<jsp:include page="/WEB-INF/view/admin/comm/comm-admin-footer.jsp"></jsp:include>
