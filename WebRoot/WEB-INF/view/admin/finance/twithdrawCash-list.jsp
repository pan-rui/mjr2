<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!--右边框架开始-->
<div class="alert alert-info">
	当前位置<b class="tip"></b>银行卡管理
</div>
<form id="searchForm" action="queryTWithdrawCashList">
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
				<td>提现状态</td>
				<td><select name="withdrawStatus" id="isEnable"
					class="isEnableCls">
						<option value="">--请选择--</option>
						<option value="1">审核中</option>
						<option value="2">审核成功</option>
						<option value="3">审核失败</option>
				</select>
				</td>
				
				
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
			<td class="auto-style1">用户名</td>
			<td class="auto-style1">姓名</td>
			<td class="auto-style1">电话</td>
			<td class="auto-style1">银行卡号</td>
			<td class="auto-style1">提现金额</td>
			<td class="auto-style1">到账金额</td>
			<td class="auto-style1">手续费</td>
			<td class="auto-style1">申请时间</td>
			<td class="auto-style1">提现状态</td>
			<td class="auto-style1">操作</td>


		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageBean}" var="bean" varStatus="st">
			<tr>
				<td>${bean.userName}</td>
				<td>${bean.realName }</td>
				<td>${bean.cellPhone}</td>
				<td>${bean.bankCardId}</td>
				<td>${bean.withdrawAmount}</td>
				
				<td>${bean.accountAmount}</td>
				<td>${bean.feeAmount}</td>
				<td><fmt:formatDate value="${bean.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>
					<c:choose>
						<c:when test="${bean.withdrawStatus ==1}">审核中</c:when>
						<c:when test="${bean.withdrawStatus ==2}">审核通过</c:when>
						<c:when test="${bean.withdrawStatus ==3}">审核不通过</c:when>
					</c:choose>
				</td>
				
				
				<td><input
					class="btn btn-inverse" type="button" value="查看"
					onclick="goUrl('selectTWithdrawCashDeatil?id=${bean.id}');" />&nbsp;<c:if test="${bean.withdrawStatus ==1}"><input
					class="btn btn-inverse" type="button" value="审核"
					onclick="" /></c:if></td> 
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
		$(".isEnableCls").val('${model.withdrawStatus}');
		
	});
//-->
</script>
<!--右边框架结束-->

