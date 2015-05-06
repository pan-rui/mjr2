<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!--右边框架开始-->
<div class="alert alert-info">
	当前位置<b class="tip"></b>帮助列表
</div>
<form id="searchForm" action="queryCHelpList">
	<table class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<td colspan="6" class="auto-style2">&nbsp;请填写查询条件 <input
					id="curPage" name="curPage" type="hidden" value="1" /></td>

			</tr>
		</thead>
		<tbody>
			<tr>
				<td>帮助类型</td>
				<td class="detail"><select name="helpTypeId"
					class="helpTypeIdCls">
						<option value="">--请选择--</option>
						<c:forEach items="${helpTypeList }" var="bean">
							<option value="${bean.id }">${bean.helpTypeName }</option>
						</c:forEach>
				</select></td>
				<td>问题</td>
				<td colspan="3"><input id="questionName" name="questionName"
					type="text" value="${model.questionName }" />
				</td>

			</tr>
			<tr>
				<td colspan="6" align="right"><input class="btn btn-inverse"
					onclick="queryForm()" type="button" value="查询" />&nbsp; <input
					class="btn btn-inverse" type="reset" value="清空" />&nbsp; <input
					class="btn btn-inverse" type="button"
					onclick="goUrl('addCHelpIndex')" value="添加" />&nbsp;</td>
			</tr>
		</tbody>
	</table>
</form>


<table class="table table-striped table-bordered table-condensed"
	id="top">
	<thead>
		<tr class="tr_detail">
			<td class="auto-style1">序号</td>
			<td class="auto-style1">帮助类型</td>
			<td class="auto-style1">问题</td>
			<td class="auto-style1">创建时间</td>
			<td class="auto-style1">操作</td>


		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageBean}" var="bean" varStatus="st">
			<tr>
				<td><a>${bean.id } </a>
				</td>
				<td>${bean.questionName}</td>
				<td>${bean.questionName}</td>
				<td><fmt:formatDate value="${bean.createTime}"
						pattern="yyyy-MM-dd HH:mm:ss" />
				</td>
				<td><input class="btn btn-inverse" type="button" value="修改"
					onclick="goUrl('updateCHelpIndex?id=${bean.id}');" />&nbsp; <input
					class="btn btn-inverse" type="button" value="查看"
					onclick="goUrl('queryCHelpIndex?id=${bean.id}');" />&nbsp;</td>
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
		$(".helpTypeIdCls").val('${model.helpTypeId}');

	});
//-->
</script>
<!--右边框架结束-->

