<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!--右边框架开始-->
<div class="alert alert-info">
	当前位置<b class="tip"></b>用户列表
</div>
<form id="searchForm" action="queryCUserList">
	<table class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<td colspan="6" class="auto-style2">&nbsp;请填写查询条件 <input
					id="curPage" name="curPage" type="hidden" value="1" /></td>

			</tr>
		</thead>
		<tbody>
			<tr>
				<td>用户名</td>
				<td class="detail"><input id="userName" class="dinp" name="userName"
					type="text" value="${model.userName }" />
				</td>
				<td>手机号码</td>
				<td>
					<input id="cellPhone" name="cellPhone" class="dinp"
					type="text" value="${model.cellPhone }" />
				</td>
				<td>真实姓名</td>
				<td>
					<input id="realName" name="realName" class="dinp"
					type="text" value="${model.realName }" />
				</td>
			</tr>
			<tr>
				<td>是否禁用</td>
				<td><select name="isEnable" id="isEnable"
					class="isEnableCls">
						<option value="">--请选择--</option>
						<option value="0">不禁用</option>
						<option value="1">禁用</option>
				</select>
				</td>
				<td>用户来源</td>
				<td><select name="source" id="source"
					class="whereSource">
						<option value="">--请选择--</option>
						<option value="0">普通</option>
						<option value="1">CPA</option>
						<option value="2">CPS</option>
						<option value="3">百度</option>
				</select>
				</td>
				<td></td>
				<td></td>
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
			<td class="auto-style1">用户名</td>
			<td class="auto-style1">真实姓名</td>
			<td class="auto-style1">是否实名</td>
			<td class="auto-style1">性别</td>
			<td class="auto-style1" style="width: 100px;">手机号码</td>
			<td class="auto-style1" style="width: 120px;">创建时间</td>
			<td class="auto-style1">是否禁用</td>
			<td class="auto-style1">用户来源</td>
			<td class="auto-style1">操作</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageBean}" var="bean" varStatus="st">
			<tr>
				<td><a>${bean.id } </a>
				</td>
				<td>${bean.userName}</td>
				<td> ${bean.realName} </td>
				<td>
					<c:choose>
						<c:when test="${bean.isAuth == 1 }">实名</c:when>
						<c:otherwise>未实名</c:otherwise>
					</c:choose>
				</td>
				<td>
					<c:choose>
						<c:when test="${bean.sex==1 }">先生</c:when>
						<c:when test="${bean.sex==2 }">女士</c:when>
						<c:otherwise>未知</c:otherwise>
					</c:choose>
				</td>
				<td>${bean.cellPhone}</td>
				<td>
					<fmt:formatDate value="${bean.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td><c:choose>
						<c:when test="${bean.isEnable==0 }">不禁用</c:when>
						<c:when test="${bean.isEnable==1 }">禁用</c:when>
					</c:choose>
				</td>
				<td><c:choose>
						<c:when test="${bean.source==0 }">普通</c:when>
						<c:when test="${bean.source==1 }">CPA</c:when>
						<c:when test="${bean.source==2 }">CPS</c:when>
						<c:when test="${bean.source==3 }">百度</c:when>
					</c:choose>
				</td>
				<td><input class="btn btn-inverse" type="button" value="修改"
					onclick="goUrl('updateCUserInfo?id=${bean.id}');" />&nbsp;
					&nbsp;<input class="btn btn-inverse" type="button" value="查看"
					onclick="goUrl('queryCUserIndex?id=${bean.id}');" />&nbsp;
				</td>
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
		$(".isEnableCls").val('${model.isEnable}');
		$(".whereSource").val('${model.source}');
	});
//-->
</script>
<!--右边框架结束-->