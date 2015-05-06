<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!--右边框架开始-->

<div class="alert alert-info">
	当前位置<b class="tip"></b>参数列表
</div>
<form id="searchForm" action="queryBParaList">
	<table class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<td colspan="6" class="auto-style2">&nbsp;请填写查询条件 <input
					id="curPage" name="curPage" type="hidden" value="1" /></td>

			</tr>
		</thead>
		<tbody>
			<tr>
				<td>参数名称</td>
				<td class="detail"><input id="paraName" name="paraName"
					type="text" value="${model.paraName }" />
				</td>
				<td>编码</td>
				<td><input id="paraCode" name="paraCode" type="text"
					value="${model.paraCode }" />
				</td>
				<td>是否删除</td>
				<td><select name="isDelete" id="isDelete" class="isDeleteCls">
						<option value="">--请选择--</option>
						<option value="0">否</option>
						<option value="1">是</option>
				</select>
				</td>
			</tr>
			<tr>
				<td colspan="6" align="right"><input class="btn btn-inverse"
					onclick="queryForm()" type="button" value="查询" />&nbsp; <input
					class="btn btn-inverse" type="reset" value="清空" />&nbsp; <input
					class="btn btn-inverse" type="button"
					onclick="goUrl('addBParaIndex')" value="添加" />&nbsp;</td>
			</tr>
		</tbody>
	</table>
</form>


<table class="table table-striped table-bordered table-condensed"
	id="top">
	<thead>
		<tr class="tr_detail">
			<td class="auto-style1">序号</td>
			<td class="auto-style1">名称</td>
			<td class="auto-style1">编码</td>
			<td class="auto-style1">键</td>
			<td class="auto-style1">值</td>
			<td class="auto-style1">是否删除</td>
			<td class="auto-style1">操作</td>


		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageBean}" var="bean" varStatus="st">
			<tr>
				<td><a>${bean.id } </a>
				</td>
				<td>${bean.paraName}</td>
				<td>${bean.paraCode}</td>
				<td>${bean.paraKey}</td>
				<td>${bean.paraValue}</td>
				<td><c:choose>
						<c:when test="${bean.isDelete==0}">否</c:when>
						<c:otherwise>是</c:otherwise>
					</c:choose></td>
				<td><input class="btn btn-inverse" type="button" value="修改"
					onclick="goUrl('updateBParaIndex?id=${bean.id}');" />&nbsp; <input
					class="btn btn-inverse" type="button" value="查看"
					onclick="goUrl('queryBParaIndex?id=${bean.id}');" />&nbsp;</td>
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
		$(".isDeleteCls").val('${model.isDelete}');

	});
//-->
</script>
<!--右边框架结束-->

