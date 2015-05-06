<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!--右边框架开始-->

<div class="alert alert-info">
	当前位置<b class="tip"></b>参数列表
</div>
<form id="searchForm" action="queryTRepayBorrowerList">
	<table class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<td colspan="6" class="auto-style2">&nbsp;请填写查询条件 <input
					id="curPage" name="curPage" type="hidden" value="1" /></td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>借款名称</td>
				<td class="detail"><input id="borrowTitle" name="borrowTitle"
					type="text" value="${model.borrowTitle }" />
				</td>
				<td>借款人用户名</td>
				<td><input id="borrowerName" name="borrowerName" type="text"
					value="${model.borrowerName}" />
				</td>
				<td>还款状态</td>
				<td><select name="repayStatus" id="repayStatus" class="repayStatusCls">
						<option value="">--请选择--</option>
						<option value="1">未还款</option>
						<option value="2">已还款</option>
						<option value="3">处理中</option>
				</select>
				</td>
			</tr>
			<tr>
				<td colspan="6" align="right"><input class="btn btn-inverse"
					onclick="queryForm()" type="button" value="查询" />&nbsp; <input
					class="btn btn-inverse" type="reset" value="清空" />&nbsp; &nbsp;</td>
			</tr>
		</tbody>
	</table>
</form>


<table class="table table-striped table-bordered table-condensed"
	id="top">
	<thead>
		<tr class="tr_detail">
			<td class="auto-style1">借款标题</td>
			<td class="auto-style1">借款人用户名</td>
			<td class="auto-style1">借款期限</td>
			<td class="auto-style1">当前期数/总期数</td>
			<td class="auto-style1">还款本金</td>
			<td class="auto-style1">还款利息</td>
			<td class="auto-style1">还款本息</td>
			<td class="auto-style1">账户余额</td>
			<td class="auto-style1">预期还款时间</td>
			<td class="auto-style1">实际还款时间</td>
			<td class="auto-style1">是否已还款</td>
			<td class="auto-style1">操作</td>


		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageBean}" var="bean" varStatus="st">
			<tr>
				<td><a>${bean.borrowTitle } </a></td>
				<td>${bean.borrowerName}</td>
				<td>${bean.deadline}<c:choose>
					<c:when test="${bean.deadlineType==1 }">天</c:when>
					<c:otherwise>个月</c:otherwise>
				</c:choose></td>
				<td>${bean.numOfPeriods}/${bean.peroids}</td>
				<td>${bean.capitalAmount}</td>
				<td>${bean.profitAmount}</td>
				<td><span style="font-weight: bold;">${bean.capitalAmount+bean.profitAmount}</span></td>
				<td>
				<c:choose>
					<c:when test="${bean.usableAmount-bean.capitalAmount-bean.profitAmount > 0}">
						<span style="font-weight: bold;">${bean.usableAmount}</span>
					</c:when>
					<c:otherwise><span style="color: red;">${bean.usableAmount}</span></c:otherwise>
				</c:choose>
				</td>
				<td><fmt:formatDate value="${bean.repayDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${bean.realRepayTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><c:choose>
						<c:when test="${bean.repayStatus==1}">未还款</c:when>
						<c:when test="${bean.repayStatus==2}">已还款</c:when>
						<c:otherwise>处理中</c:otherwise>
					</c:choose></td>
				<td>
				<c:if test="${bean.repayStatus==1}"><input class="btn btn-inverse" type="button" value="还款"
					onclick="repayBorrow('${bean.id}','${bean.capitalAmount+bean.profitAmount}');" />&nbsp; </c:if>
				<input class="btn btn-inverse" type="button" value="查看收款列表"
					onclick="openInvestRepay('${bean.borrowId}','${bean.numOfPeriods }');" />	
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
		$("#repayStatus").val('${model.repayStatus}');
		
	});
	
	function repayBorrow(id,amount){
		if(confirm("还款金额为 "+amount+" 元，确定还款？")){
			$.lionPost("repayBorrow",{"id":id},function(data){
				alert(data.info);
				queryForm();
			});
		}

	}
	
	var jbox;
	function openInvestRepay(borrowId,numOfPeriods){
		jbox = $.jBox.open("iframe:queryTRepayList?borrowId="+borrowId+"&numOfPeriods="+numOfPeriods, "投资人收款列表", 1024, 600, {
			buttons : {
				'关闭' : true
			}
		});
	}
//-->
</script>
<!--右边框架结束-->
<script type="text/javascript" src="${basePath }/js/plugins/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="${basePath }/js/plugins/jBox/i18n/jquery.jBox-zh-CN.js"></script>
