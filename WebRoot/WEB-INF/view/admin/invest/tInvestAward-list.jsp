<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!--右边框架开始-->
<div class="alert alert-info">
	当前位置<b class="tip"></b>投资奖励列表
</div>
<form id="searchForm" action="selectInvestAward">
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
				<td>奖励级别</td>
				<td>
					<select name="awardLevel" id="isEnable"
					class="isEnableCls">
						<option value="">--请选择--</option>
						<option value="V0">V0</option>
						<option value="V1">V1</option>
						<option value="V2">V2</option>
						<option value="V3">V3</option>
				</select>
				</td>
				<td>奖励状态</td>
				<td>
					<select name="awardStatus" id="awardStatus"
					class="awardStatusCls">
						<option value="">--请选择--</option>
						<option value="0">不发放</option>
						<option value="1">准备发放</option>
						<option value="2">发放中</option>
						<option value="3">发放成功</option>
						<option value="4">禁止发放</option>
				</select>
				</td>
				<td>奖励类型</td>
				<td>
					<select name="awardType" id="awardType"
					class="awardTypeCls">
						<option value="">--请选择--</option>
						<option value="1">新手奖励</option>
						<option value="2">投资奖励</option>
						<option value="3">推荐收益奖励</option>
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
			<td class="auto-style1">电话</td>
			<td class="auto-style1">奖励金额</td>
			<td class="auto-style1">奖励类型</td>
			<td class="auto-style1">奖励状态</td>
			<td class="auto-style1">奖励级别</td>
			<td class="auto-style1">待收本金</td>
			<td class="auto-style1">时间</td>
			
			<td class="auto-style1">借款标题</td>


		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageBean}" var="bean" varStatus="st">
			<tr>
				<td>${bean.userName}</td>
				<td>${bean.cellPhone }</td>
				<td>${bean.awardAmount}</td>
				<td><c:choose>
						<c:when test="${bean.awardType ==1}">新手奖励</c:when>
						<c:when test="${bean.awardType ==2}">投资奖励</c:when>
						<c:when test="${bean.awardType ==3}">推荐收益奖励</c:when>
					</c:choose></td>
				<td><c:choose>
						<c:when test="${bean.awardStatus ==0}">不发放</c:when>
						<c:when test="${bean.awardStatus ==1}">准备发放</c:when>
						<c:when test="${bean.awardStatus ==2}">发放中</c:when>
						<c:when test="${bean.awardStatus ==3}">发放成功</c:when>
						<c:when test="${bean.awardStatus ==4}">禁止发放</c:when>
					</c:choose></td>
				
				<td>${bean.awardLevel}</td>
				<td>${bean.dueinAmount }</td>
				<td><fmt:formatDate value="${bean.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${bean.borrowTitle }</td> 
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
		$(".isEnableCls").val('${model.awardLevel}');
		
	});
$(function() {
	$(".awardStatusCls").val('${model.awardStatus}');
	
});
$(function() {
	$(".awardTypeCls").val('${model.awardType}');
	
});
//-->
</script>
<!--右边框架结束-->

