<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!--右边框架开始-->
<div class="alert alert-info">
	当前位置<b class="tip"></b>充值记录
</div>
<form id="searchForm" action="queryTRechargeList">
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
				<td>审核状态</td>
				<td><select name="result" id="isEnable"
					class="isEnableCls">
						<option value="">--请选择--</option>
						<option value="0">失败</option>
						<option value="1">成功</option>
						
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
			
			<td class="auto-style1">姓名</td>
			<td class="auto-style1">电话</td>
			<td class="auto-style1">充值金额</td>
			<td class="auto-style1">到账金额</td>
			<td class="auto-style1">充值费用</td>
			<td class="auto-style1">充值时间</td>
			<td class="auto-style1">状态</td>
			<td class="auto-style1">操作</td>


		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageBean}" var="bean" varStatus="st">
			<tr>
				<td>${bean.realName }
				</td>
				<td>${bean.cellPhone}</td>
				<td>${bean.rechargeAmount}</td>
				<td>${bean.rechargeRealAmount}</td>
				<td>${bean.feeAmount}</td>
				<td><fmt:formatDate value="${bean.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><c:choose>
						<c:when test="${bean.result==0 }">失败</c:when>
						<c:when test="${bean.result==1 }">成功</c:when>
					</c:choose>
				</td>
				
				<td><input
					class="btn btn-inverse" type="button" value="查看"
					onclick="goUrl('queryTRechargeListDetailed?id=${bean.id}');" />&nbsp;</td> 
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
		$(".isEnableCls").val('${model.result}');
		
	});
//-->
</script>
<!--右边框架结束-->

