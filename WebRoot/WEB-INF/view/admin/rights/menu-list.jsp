<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!--右边框架开始-->
<div class="alert alert-info">
	当前位置<b class="tip"></b>菜单列表
</div>
<form id="searchForm" action="queryBMenuList">
	<table class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<td colspan="6" class="auto-style2">&nbsp;请填写查询条件 <input
					id="curPage" name="curPage" type="hidden" value="1" /></td>

			</tr>
		</thead>
		<tbody>
			<tr>
				<td>菜单名称</td>
				<td class="detail"><input id="menuName" name="menuName"
					type="text" value="${model.menuName }" />
				</td>
				<td>父级菜单</td>
				<td><select name="parentId" id="parentId" class="parentIdCls">
						<option value="">--请选择--</option>
						<c:forEach items="${parentMenuList }" var="menu">
							<option value="${menu.id }">${menu.menuName }</option>
						</c:forEach>


				</select>
				</td>
				<td>是否拦截</td>
				<td><select name="isIntercept" id="isIntercept"
					class="isInterceptCls">
						<option value="">--请选择--</option>
						<option value="0">不拦截</option>
						<option value="1">拦截</option>
				</select>
				</td>
			</tr>
			<tr>
				<td colspan="6" align="right"><input class="btn btn-inverse"
					onclick="queryForm()" type="button" value="查询" />&nbsp; <input
					class="btn btn-inverse" type="reset" value="清空" />&nbsp; <input
					class="btn btn-inverse" type="button"
					onclick="goUrl('addBMenuIndex')" value="添加" />&nbsp;</td>
			</tr>
		</tbody>
	</table>
</form>


<table class="table table-striped table-bordered table-condensed"
	id="top">
	<thead>
		<tr class="tr_detail">
			<td class="auto-style1">序号</td>
			<td class="auto-style1">父级菜单</td>
			<td class="auto-style1">菜单名称</td>
			<td class="auto-style1">菜单URL</td>
			<td class="auto-style1">菜单层级</td>
			<td class="auto-style1">是否拦截</td>
			<td class="auto-style1">操作</td>


		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageBean}" var="bean" varStatus="st">
			<tr>
				<td><a>${bean.id } </a>
				</td>
				<td>${bean.parentName}</td>
				<td>${bean.menuName}</td>
				<td>${bean.url}</td>
				<td><c:choose>
						<c:when test="${bean.menuLevel==1 }">1级</c:when>
						<c:when test="${bean.menuLevel==2 }">2级</c:when>
						<c:when test="${bean.menuLevel==3 }">3级</c:when>
					</c:choose>
				</td>
				<td><c:choose>
						<c:when test="${bean.isIntercept==0 }">不拦截</c:when>
						<c:when test="${bean.isIntercept==1 }">拦截</c:when>
					</c:choose>
				</td>
				<td><input class="btn btn-inverse" type="button" value="修改"
					onclick="goUrl('updateBMenuIndex?id=${bean.id}');" />&nbsp; <input
					class="btn btn-inverse" type="button" value="查看"
					onclick="goUrl('queryBMenuIndex?id=${bean.id}');" />&nbsp;</td>
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
		$(".parentIdCls").val('${model.parentId}');
		$(".isInterceptCls").val('${model.isIntercept}');

	});
//-->
</script>
<!--右边框架结束-->

