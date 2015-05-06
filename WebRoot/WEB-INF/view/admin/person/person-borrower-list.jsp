<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!--右边框架开始-->
<div class="alert alert-info">
	当前位置<b class="tip"></b>借款人信息列表
</div>
<form id="searchForm" action="queryTPersonBorrowerList">
	<table class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<td colspan="6" class="auto-style2">&nbsp;请填写查询条件 <input
					id="curPage" name="curPage" type="hidden" value="1" /></td>

			</tr>
		</thead>
		<tbody>
			<tr>
				<td>借款人姓名</td>
				<td class="detail"><input id="borrowerName" name="borrowerName"
					type="text" value="${model.borrowerName }" />
				</td>

				<td>借款人手机号</td>
				<td colspan="3"><input id="borrowerCellPhone" name="borrowerCellPhone"
					type="text" value="${model.borrowerCellPhone }" />
				</td>
			</tr>
			<tr>
				<td colspan="6" align="right"><input class="btn btn-inverse"
					onclick="queryForm()" type="button" value="查询" />&nbsp; <input
					class="btn btn-inverse" type="reset" value="清空" />&nbsp; <input
					class="btn btn-inverse" type="button"
					onclick="goUrl('addTPersonBorrowerIndex')" value="添加" />&nbsp;</td>
			</tr>
		</tbody>
	</table>
</form>


<table class="table table-striped table-bordered table-condensed"
	id="top">
	<thead>
		<tr class="tr_detail">
			<td class="auto-style1">序号</td>
			<td class="auto-style1">借款人姓名</td>
			<td class="auto-style1">借款人身份证</td>
			<td class="auto-style1">借款人手机号</td>
			<td class="auto-style1">月收入水平</td>
			<td class="auto-style1">创建时间</td>
			<td class="auto-style1">操作</td>


		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageBean}" var="bean" varStatus="st">
			<tr>
				<td><a>${bean.id } </a>
				</td>
				<td>${bean.borrowerName}</td>
				<td>${bean.borrowerIdCard}</td>
				<td>${bean.borrowerCellPhone}</td>
				<td>${bean.monthlyIncomeLevel}</td>
				<td><fmt:formatDate value="${bean.createTime}"
						pattern="yyyy-MM-dd HH:mm:ss" />
				</td>
				<td><input class="btn btn-inverse" type="button" value="修改"
					onclick="goUrl('updateTPersonBorrowerIndex?id=${bean.id}');" />&nbsp; <input
					class="btn btn-inverse" type="button" value="查看"
					onclick="goUrl('queryTPersonBorrowerIndex?id=${bean.id}');" />&nbsp;</td>
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
		

	});
//-->
</script>
<!--右边框架结束-->

