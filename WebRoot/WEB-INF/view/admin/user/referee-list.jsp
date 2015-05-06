<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!--右边框架开始-->
<div class="alert alert-info">
	当前位置<b class="tip"></b>推荐关系列表
</div>
<form id="searchForm" action="queryTRefereeRelationList">
	<table class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<td colspan="6" class="auto-style2">&nbsp;请填写查询条件 <input
					id="curPage" name="curPage" type="hidden" value="1" />
				</td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>用户名</td>
				<td class="detail"><input id="userName" class="dinp"
					name="userName" type="text" value="${model.userName }" /></td>
				<td>手机号码</td>
				<td><input id="cellPhone" name="cellPhone" class="dinp"
					type="text" value="${model.cellPhone }" /></td>
				<td>真实姓名</td>
				<td><input id="realName" name="realName" class="dinp"
					type="text" value="${model.realName }" /></td>
			</tr>
			<tr>
				<td>推荐人用户名</td>
				<td class="detail"><input id="tUserName" class="dinp"
					name="tUserName" type="text" value="${model.tUserName }" /></td>
				<td>推荐人手机号码</td>
				<td><input id="tCellPhone" name="tCellPhone" class="dinp"
					type="text" value="${model.tCellPhone }" /></td>
				<td>推荐人真实姓名</td>
				<td><input id="tRealName" name="tRealName" class="dinp"
					type="text" value="${model.tRealName }" /></td>
			</tr>
			<tr>
				<td colspan="6" align="right"><input class="btn btn-inverse"
					onclick="queryForm()" type="button" value="查询" />&nbsp; <input
					class="btn btn-inverse" onclick="resetForm()" type="button"
					value="清空" />&nbsp;<input class="btn btn-inverse" type="button"
					onclick="goUrl('addTRefereeRelationIndex')" value="添加" />&nbsp;</td>
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
			<td class="auto-style1">手机号码</td>
			<td class="auto-style1">推荐人用户名</td>
			<td class="auto-style1">推荐人真实姓名</td>
			<td class="auto-style1">推荐人手机号码</td>
			<td class="auto-style1">推荐时间</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageBean}" var="bean" varStatus="st">
			<tr>
				<td><a>${bean.id } </a></td>
				<td>${bean.userName}</td>
				<td>${bean.realName}</td>
				<td>${bean.cellPhone}</td>
				<td>${bean.tUserName}</td>
				<td>${bean.tRealName}</td>
				<td>${bean.tCellPhone}</td>
				<td>
					<fmt:formatDate value="${bean.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
		</c:forEach>
		<tr class="tr_pagenumber">
			<td colspan="100"><my:pager curPage="${page.page }"
					pageSize="${page.limit }" totalCount="${page.totalCount }" />
			</td>
		</tr>
	</tbody>
</table>
<!--右边框架结束-->