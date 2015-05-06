<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!--右边框架开始-->
<div class="alert alert-info">
	当前位置<b class="tip"></b>角色列表
</div>
<form id="searchForm" action="queryBRoleList">
	<table class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<td colspan="6" class="auto-style2">&nbsp;请填写查询条件 <input
					id="curPage" name="curPage" type="hidden" value="1" /></td>

			</tr>
		</thead>
		<tbody>
			<tr>
				<td>角色名称</td>
				<td class="detail" colspan="5"><input id="roleName"
					name="roleName" type="text" value="${model.roleName }" />
				</td>


			</tr>
			<tr>
				<td colspan="6" align="right"><input class="btn btn-inverse"
					onclick="queryForm()" type="button" value="查询" />&nbsp; <input
					class="btn btn-inverse" type="reset" value="清空" />&nbsp; <input
					class="btn btn-inverse" type="button"
					onclick="goUrl('addBRoleIndex')" value="添加" />&nbsp;</td>
			</tr>
		</tbody>
	</table>
</form>


<table class="table table-striped table-bordered table-condensed"
	id="top">
	<thead>
		<tr class="tr_detail">
			<td class="auto-style1">序号</td>
			<td class="auto-style1">角色名称</td>
			<td class="auto-style1">角色描述</td>
			<td class="auto-style1">操作</td>


		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageBean}" var="bean" varStatus="st">
			<tr>
				<td><a>${bean.id } </a>
				</td>
				<td>${bean.roleName}</td>
				<td>${bean.description}</td>
				<td><input class="btn btn-inverse" type="button" value="修改"
					onclick="goUrl('updateBRoleIndex?id=${bean.id}');" />&nbsp; <input
					class="btn btn-inverse" type="button" value="查看"
					onclick="goUrl('queryBRoleIndex?id=${bean.id}');" />&nbsp;</td>
			</tr>


		</c:forEach>


		<tr class="tr_pagenumber">
			<td colspan="100"><my:pager curPage="${page.page }"
					pageSize="${page.limit }" totalCount="${page.totalCount }" /></td>
		</tr>
	</tbody>
</table>

<!--右边框架结束-->

