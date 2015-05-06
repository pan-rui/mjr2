<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!--右边框架开始-->

<div class="alert alert-info">
	当前位置<b class="tip"></b>产品参数
</div>
<form id="searchForm" action="queryTBorrowList">
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
				<td><input id="borrowerUserName" name="borrowerUserName" type="text"
					value="${model.borrowerUserName}" />
				</td>
				<td>借款人真实姓名</td>
				<td><input id="borrowerName" name="borrowerName" type="text"
					value="${model.borrowerName}" />
				</td>
			</tr>
			<tr>
				<td>借款状态</td>
				<td class="detail" colspan="5">
					<select id="borrowStatus" name="borrowStatus">
						<option value="">--请选择-=</option>
						<c:forEach items="${borrowStatusList }" var="bean" varStatus="st">
							<option value="${bean.borrowStatus }">${bean.borrowStatusName }</option>
						</c:forEach>
					</select>
				</td>
				
			</tr>
			<tr>
				<td colspan="6" align="right"><input class="btn btn-inverse"
					onclick="queryForm()" type="button" value="查询" />&nbsp;
					<input class="btn btn-inverse"
					onclick="goUrl('addTBorrowIndex')" type="button" value="录入借款" /> <input
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
			<td class="auto-style1">收款人用户名</td>
			<td class="auto-style1">借款人真实姓名</td>
			<td class="auto-style1">借款总金额</td>
			<td class="auto-style1">借款期限</td>
			<td class="auto-style1">借款年化收益率</td>
			<td class="auto-style1">还款方式</td>
			<td class="auto-style1">开始投标时间</td>
			<td class="auto-style1">满标时间</td>
			<td class="auto-style1">借款状态</td>
			<td class="auto-style1">操作</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageBean}" var="bean" varStatus="st">
			<tr>
				<td><a>${bean.borrowTitle } </a></td>
				<td>${bean.borrowerUserName}</td>
				<td>${bean.borrowerName}</td>
				<td>${bean.borrowAmount}</td>
				<td>${bean.deadline}
				<c:choose>
					<c:when test="${bean.deadlineType==1 }">天</c:when>
					<c:otherwise>个月</c:otherwise>
				</c:choose>
				</td>
				<td>${bean.annualRate} %</td>
				<td><c:choose>
						<c:when test="${bean.repayType==1 }">一次性还款</c:when>
						<c:when test="${bean.repayType==2 }">为按月付息，到期还本</c:when>
						<c:when test="${bean.repayType==3 }">等额本息</c:when>
					
					</c:choose>
				</td>
				<td><fmt:formatDate value="${bean.investStartTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="${bean.fullTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>
					<c:choose>
						<c:when test="${bean.borrowStatus==1 }">申请中</c:when>
						<c:when test="${bean.borrowStatus==2 }">初审通过</c:when>
						<c:when test="${bean.borrowStatus==3 }">招标中</c:when>
						<c:when test="${bean.borrowStatus==4 }">复审中</c:when>
						<c:when test="${bean.borrowStatus==5 }">还款中</c:when>
						<c:when test="${bean.borrowStatus==6 }">已还款</c:when>
						<c:when test="${bean.borrowStatus==7 }">借款失败(初审)</c:when>
						<c:when test="${bean.borrowStatus==8 }">复审失败</c:when>
						<c:when test="${bean.borrowStatus==9 }">流标</c:when>
						<c:when test="${bean.borrowStatus==10 }">复审处理中</c:when>
						<c:when test="${bean.borrowStatus==11 }">流标处理中</c:when>
					</c:choose>
				</td>
				<td>
					<c:choose>
						<c:when test="${bean.borrowStatus==1 }"><input class="btn btn-inverse" type="button" value="初审"
					onclick="goUrl('updateFirstAuditTBorrowIndex?id=${bean.id}');" /></c:when>
						<c:when test="${bean.borrowStatus==4 }"><input class="btn btn-inverse" type="button" value="满标复审"
					onclick="goUrl('updateFullAuditTBorrowIndex?id=${bean.id}');" /></c:when>
						
					</c:choose>
				&nbsp; <input class="btn btn-inverse" type="button" value="查看" onclick="goUrl('queryTBorrowIndex?id=${bean.id}');" />
				&nbsp; <input class="btn btn-inverse" type="button" value="查看投资记录" onclick="openUserFundrecord('${bean.id}')" /></td>
			</tr>
		</c:forEach>


		<tr class="tr_pagenumber">
			<td colspan="100"><my:pager curPage="${page.page }"
					pageSize="${page.limit }" totalCount="${page.totalCount }" /></td>
		</tr>
	</tbody>
</table>
<script type="text/javascript" src="${basePath }/js/plugins/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="${basePath }/js/plugins/jBox/i18n/jquery.jBox-zh-CN.js"></script>
<script type="text/javascript">
<!--
	$(function() {
		$("#borrowStatus").val('${model.borrowStatus}')

	});
//-->

var jbox;
function openUserFundrecord(id){
	jbox = $.jBox.open("iframe:selectInvestListForbid?borrowId="+id, "产品列表", 1024, 600, {
		buttons : {
			'关闭' : true
		}
	});
}
</script>
<!--右边框架结束-->

