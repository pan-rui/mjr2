<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!--右边框架开始-->
<div class="alert alert-info">
	当前位置<b class="tip"></b>投资列表
</div>
<form id="searchForm" action="selectTInvestList">
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
			<td class="auto-style1">产品名</td>
			<td class="auto-style1">还款方式</td>
			<td class="auto-style1">申购金额</td>
			<td class="auto-style1">产品状态</td>
			<td class="auto-style1">时间</td>
			
			<td class="auto-style1">操作</td>


		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageBean}" var="bean" varStatus="st">
			<tr>
				<td>${bean.userName}</td>
				<td>${bean.realName }</td>
				<td>${bean.cellPhone}</td>
				<td>${bean.borrowTitle}</td>
				<td><c:choose>
						<c:when test="${bean.repayType ==1}">一次性还款</c:when>
						<c:when test="${bean.repayType ==2}">按月付息，到期还本</c:when>
						<c:when test="${bean.repayType ==3}">等额本息</c:when>
					</c:choose></td>
				
				<td>${bean.investAmount}</td>
				<td><c:choose>
						<c:when test="${bean.borrowStatus ==1}">审核中</c:when>
						<c:when test="${bean.borrowStatus ==2}">初审通过</c:when>
						<c:when test="${bean.borrowStatus ==3}">招标中</c:when>
						<c:when test="${bean.borrowStatus ==4}">复审中</c:when>
						<c:when test="${bean.borrowStatus ==5}">还款中</c:when>
						<c:when test="${bean.borrowStatus ==6}">已还款</c:when>
						<c:when test="${bean.borrowStatus ==7}">借款失败</c:when>
						<c:when test="${bean.borrowStatus ==8}">复审失败</c:when>
						<c:when test="${bean.borrowStatus ==9}">流标</c:when>
					</c:choose></td>
				<td><fmt:formatDate value="${bean.investTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td></td> 
			</tr>


		</c:forEach>


		<tr class="tr_pagenumber">
			<td colspan="100"><my:pager curPage="${page.page }"
					pageSize="${page.limit }" totalCount="${page.totalCount }" /></td>
		</tr>
	</tbody>
</table>


<!--右边框架结束-->

