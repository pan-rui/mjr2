<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!--右边框架开始-->

<div class="alert alert-info">
	当前位置<b class="tip"></b>借款申请管理
</div>
<form id="searchForm" action="queryTApplyBorrowList">
	<table class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<td colspan="6" class="auto-style2">&nbsp;请填写查询条件 <input
					id="curPage" name="curPage" type="hidden" value="1" /></td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>借款人用户名</td>
				<td><input id="userName" name="userName" type="text"
					value="${model.userName}" />
				</td>
				<td>借款人真实姓名</td>
				<td><input id="borrowerName" name="borrowerName" type="text"
					value="${model.borrowerName}" />
				</td>
				<td>借款人手机号码</td>
				<td><input id="borrowerCellPhone" name="borrowerCellPhone" type="text"
					value="${model.borrowerCellPhone}" />
				</td>
			</tr>
			
			<tr>
				<td colspan="6" align="right"><input class="btn btn-inverse"
					onclick="queryForm()" type="button" value="查询" />&nbsp;
					<input class="btn btn-inverse"
					onclick="goUrl('addTBorrowIndex')" type="button" value="录入借款" /> <input
					class="btn btn-inverse" type="reset" value="清空" />&nbsp; &nbsp;</td>
			</tr>
		</tbody>
	</table>
</form>


<table class="table table-striped table-bordered table-condensed"
	id="top">
	<thead>
		<tr class="tr_detail">
			<td class="auto-style1">收款人用户名</td>
			<td class="auto-style1">借款人真实姓名</td>
			<td class="auto-style1">借款人手机号码</td>
			<td class="auto-style1">借款用途</td>
			<td class="auto-style1">借款金额</td>
			<td class="auto-style1">借款期限</td>
			<td class="auto-style1">借款人所在地</td>
			<td class="auto-style1">借款类型</td>
			<td class="auto-style1">备注</td>
			<td class="auto-style1">申请时间</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageBean}" var="bean" varStatus="st">
			<tr>
				<td>${bean.userName } </td>
				<td>${bean.borrowerName}</td>
				<td>${bean.borrowerCellPhone}</td>
				<td>${bean.borrowUse}</td>
				<td>${bean.borrowAmount} </td>
				<td>${bean.borrowDealine }月</td>
				<td>广东省深圳市${bean.houseCity} </td>
				<td><c:choose>
						<c:when test="${bean.borrowType==1 }">房贷</c:when>
						<c:when test="${bean.borrowType==2 }">车贷</c:when>
						<c:when test="${bean.borrowType==3 }">信用贷</c:when>
					</c:choose>
				</td>
				<td>${bean.remarks }</td>
				<td><fmt:formatDate value="${bean.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
		</c:forEach>

		<tr class="tr_pagenumber">
			<td colspan="100"><my:pager curPage="${page.page }"
					pageSize="${page.limit }" totalCount="${page.totalCount }" /></td>
		</tr>
	</tbody>
</table>
