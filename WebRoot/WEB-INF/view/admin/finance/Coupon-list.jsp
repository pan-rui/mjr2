<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!--右边框架开始-->
<div class="alert alert-info">
	当前位置<b class="tip"></b>红包管理
</div>
<form id="searchForm" action="queryTCouponList">
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
			</tr>
			<tr>
				<td>红包类型</td>
				<td>
					<select name="couponType" id="isEnable" class="isEnable">
							<option value="">--请选择--</option>
							<option value="1">首投红包</option>
							<option value="2">推荐人首投红包</option>
					</select>
				</td>
				<td>红包状态</td>
				<td>
					<select name="couponStatus" id="isCouponStatus" class="isCouponStatus">
							<option value="">--请选择--</option>
							<option value="1">未领取</option>
							<option value="2">申请中</option>
							<option value="3">已领取</option>
							<option value="4">未领取过期</option>
							<option value="5">未使用过期</option>
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
			<td class="auto-style1">红包类型</td>
			<td class="auto-style1">红包名称</td>
			<td class="auto-style1">红包金额</td>
			<td class="auto-style1">红包状态</td>
			<td class="auto-style1">过期时间</td>
			<td class="auto-style1">备注</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageBean}" var="bean" varStatus="st">
			<tr>
				<td>${bean.userName}</td>
				<td>${bean.cellPhone}</td>
				<td>
					<c:choose>
						<c:when test="${bean.couponType ==1}">首投红包</c:when>
						<c:when test="${bean.couponType ==2}">推荐人首投红包</c:when>
					</c:choose>
				</td>
				<td>${bean.couponName}</td>
				<td>${bean.couponAmount }</td>
				<td>
					<c:choose>
						<c:when test="${bean.couponStatus ==1}">未领取</c:when>
						<c:when test="${bean.couponStatus ==2}">申请中</c:when>
						<c:when test="${bean.couponStatus ==3}">已领取</c:when>
						<c:when test="${bean.couponStatus ==4}">未领取过期</c:when>
						<c:when test="${bean.couponStatus ==5}">未使用过期</c:when>
					</c:choose>
				</td>
				<td><fmt:formatDate value="${bean.expirationDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${bean.couponRemarks }</td>
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
		$(".isCouponType").val('${model.couponType}');
		$(".isCouponStatus").val('${model.couponStatus}');
	});
//-->
</script>
<!--右边框架结束-->

