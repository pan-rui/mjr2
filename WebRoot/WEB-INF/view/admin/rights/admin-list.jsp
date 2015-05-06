<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!--右边框架开始-->
<div class="alert alert-info">
	当前位置<b class="tip"></b>管理员列表
</div>
<form id="searchForm" action="queryBAdminList">
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
				<td>管理员用户名</td>
				<td class="detail"><input id="adminName" name="adminName"
					type="text" value="${model.adminName }" /></td>
				<td>是否可用</td>
				<td colspan="3"><select name="isEnable" id="isEnable"
					class="isEnableCls">
						<option value="">--请选择--</option>
						<option value="1">是</option>
						<option value="0">否</option>
				</select></td>
			</tr>
			<tr>
				<td colspan="6" align="right"><input class="btn btn-inverse"
					onclick="queryForm()" type="button" value="查询" />&nbsp; <input
					class="btn btn-inverse" type="reset" value="清空" />&nbsp; <input
					class="btn btn-inverse" type="button"
					onclick="goUrl('addBAdminIndex')" value="添加" />&nbsp;</td>
			</tr>
		</tbody>
	</table>
</form>


<table class="table table-striped table-bordered table-condensed"
	id="top">
	<thead>
		<tr class="tr_detail">
			<td class="auto-style1">序号</td>
			<td class="auto-style1">管理员用户名</td>
			<td class="auto-style1">创建时间</td>
			<td class="auto-style1">是否禁用</td>
			<td class="auto-style1">操作</td>


		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageBean}" var="bean" varStatus="st">
			<tr>
				<td><a>${bean.id } </a></td>
				<td>${bean.adminName}</td>
				<td><fmt:formatDate value="${bean.createTime}"
						pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td><c:choose>
						<c:when test="${bean.isEnable==0 }">否</c:when>
						<c:otherwise>是</c:otherwise>
					</c:choose></td>
				<td><input class="btn btn-inverse" type="button" value="修改"
					onclick="goUrl('updateBAdminIndex?id=${bean.id}');" />&nbsp; <input
					class="btn btn-inverse" type="button" value="查看"
					onclick="goUrl('queryBAdminIndex?id=${bean.id}');" />&nbsp; <input
					class="btn btn-inverse" type="button" value="分配权限"
					onclick="goUrl('updateBRoleAdminIndex?id=${bean.id}&adminName=${bean.adminName}');" />&nbsp;
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
<script type="text/javascript">
<!--
	$(function() {
		$(".isEnableCls").val('${model.isEnable}');
	});
//-->
</script>
