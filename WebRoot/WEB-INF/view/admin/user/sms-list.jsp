<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!--右边框架开始-->
<div class="alert alert-info">
	当前位置<b class="tip"></b>用户列表
</div>
<form id="searchForm" action="queryCSmsList">
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
				<td class="detail"><input id="userName" name="userName"
					type="text" value="${model.userName }" />
				</td>
				<td>真实姓名</td>
				<td class="detail" ><input width="100px;" id="realName" name="realName"
					type="text" value="${model.realName }" />
				</td>
				<td>手机号码</td>
				<td class="detail" >
					<input id="cellPhone" name="cellPhone"
					type="text" value="${model.cellPhone }" />
				</td>
			</tr>
			<tr>
				<td>发送状态</td>
				<td><select name="sendStatus" id="sendStatus"
					class="issendStatus">
						<option value="">--请选择--</option>
						<option value="1">失败</option>
						<option value="2">成功</option>
				</select>
				</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td colspan="6" align="right"><input class="btn btn-inverse"
					onclick="queryForm()" type="button" value="查询" />&nbsp; <input
					class="btn btn-inverse" type="reset" value="清空" /> 
				</td>
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
			<td class="auto-style1" width="125px;">手机号码</td>
			<td class="auto-style1" width="250px;">短信内容</td>
			<td class="auto-style1" width="125px;">发送时间</td>
			<td class="auto-style1">发送类型</td>
			<td class="auto-style1">发送状态</td>
			<td class="auto-style1">操作</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageBean}" var="bean" varStatus="st">
			<tr>
				<td><a>${bean.id }</a></td>
				<td>${bean.userName}</td>
				<td>${bean.realName}</td>
				<td>${bean.cellPhone}</td>
				<td>${bean.smsContent }</td>
				<td>
					<fmt:formatDate value="${bean.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>${bean.smsType }</td>
				<td><c:choose>
						<c:when test="${bean.sendStatus==1 }">未发送</c:when>
						<c:when test="${bean.sendStatus==2 }">发送成功</c:when>
						<c:when test="${bean.sendStatus==2 }">发送失败</c:when>
					</c:choose>
				</td>
				<td>
				&nbsp;<input class="btn btn-inverse" type="button" value="查看"
					onclick="goUrl('queryCSmsIndex?id=${bean.id}');" />&nbsp;
				</td>
			</tr>


		</c:forEach>


		<tr class="tr_pagenumber">
			<td colspan="100"><my:pager curPage="${page.page }"
					pageSize="${page.limit }" totalCount="${page.totalCount }" /></td>
		</tr>
	</tbody>
</table>
<!--右边框架结束-->
<script type="text/javascript">
<!--
	$(function() {
		$(".issendStatus").val('${model.sendStatus}');
	});
//-->
</script>