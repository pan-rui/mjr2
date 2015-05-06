<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!--右边框架开始-->
<div class="alert alert-info">
	当前位置<b class="tip"></b>银行卡详细
</div>
<div class="xiangqing">
	<table>
		<tr>
			<td class="bt_ys"><strong>用户名：</strong>
			</td>
			<td  >
			
				${twmsg.userName }
			</td>
			<td class="bt_ys"><strong>姓名：</strong>
			</td>
			<td  >${twmsg.realName }</td>
		</tr>
		<tr>
			<td class="bt_ys"><strong>手机号码：</strong>
			</td>
			<td  >${twmsg.cellPhone }</td>
			<td class="bt_ys"><strong>订单号：</strong>
			</td>
			<td  >
				${twmsg.ordId }
			</td>
		</tr>
		<tr>
			<td class="bt_ys"><strong>订单时间：</strong>
			</td>
			<td  ><fmt:formatDate value="${twmsg.ordDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
			<td class="bt_ys"><strong>提现金额：</strong>
			</td>
			<td  >
				${twmsg.withdrawAmount}
			</td>
		</tr>
		<tr>
			<td class="bt_ys"><strong>到账金额：</strong>
			</td>
			<td>
				${twmsg.accountAmount}
			</td>
			<td class="bt_ys"><strong>手续费：</strong>
			</td>
			<td>
				${twmsg.feeAmount }
			</td>
		</tr>
		<tr>
			<td class="bt_ys"><strong>创建时间：</strong>
			</td>
			<td  ><fmt:formatDate value="${twmsg.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
			<td class="bt_ys"><strong>提现状态：</strong>
			</td>
			<td  >
				<c:choose>
						<c:when test="${twmsg.withdrawStatus ==1}">审核中</c:when>
						<c:when test="${twmsg.withdrawStatus ==2}">审核通过</c:when>
						<c:when test="${twmsg.withdrawStatus ==3}">审核不通过</c:when>
					</c:choose>
			</td>
		</tr>
		<tr>
			<td class="bt_ys"><strong>银行卡号：</strong>
			</td>
			<td>
				${twmsg.bankCardId }
			</td>
			<td class="bt_ys"><strong>处理人ID：</strong>
			</td>
			<td>
				${twmsg.adminId }
			</td>
		</tr>
		
		<tfoot>
			<tr>
				<td colspan="4"><input class="btn btn-inverse" id="btnBack"
					type="button" onclick="goUrl('queryTWithdrawCashList')" value="返回" /></td>
			</tr>
		</tfoot>
	</table>
</div>
<!--右边框架结束-->
