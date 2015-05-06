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
			
				${tbankcardmsg.userName }
			</td>
			<td class="bt_ys"><strong>姓名：</strong>
			</td>
			<td  >${tbankcardmsg.realName }</td>
		</tr>
		<tr>
			<td class="bt_ys"><strong>手机号码：</strong>
			</td>
			<td  >${tbankcardmsg.cellPhone }</td>
			<td class="bt_ys"><strong>银行名称：</strong>
			</td>
			<td  >
				${tbankcardmsg.bankName }
			</td>
		</tr>
		<tr>
			<td class="bt_ys"><strong>支行名称：</strong>
			</td>
			<td  >${tbankcardmsg.subBankName }
			</td>
			<td class="bt_ys"><strong>银行编号：</strong>
			</td>
			<td  >
				${tbankcardmsg.bankCode}
			</td>
		</tr>
		<tr>
			<td class="bt_ys"><strong>银行卡号：</strong>
			</td>
			<td>
				${tbankcardmsg.bankCardNo}
			</td>
			<td class="bt_ys"><strong>卡状态：</strong>
			</td>
			<td  >
				<c:choose>
						<c:when test="${tbankcardmsg.cardStatus ==1}">审核中</c:when>
						<c:when test="${tbankcardmsg.cardStatus ==2}">审核通过</c:when>
						<c:when test="${tbankcardmsg.cardStatus ==3}">审核不通过</c:when>
					</c:choose>
			</td>
		</tr>
		
		<tr>
			<td class="bt_ys"><strong>创建时间：</strong>
			</td>
			<td>
				<fmt:formatDate value="${tbankcardmsg.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
			
		</tr>
		
		<tfoot>
			<tr>
				<td colspan="4"><input class="btn btn-inverse" id="btnBack"
					type="button" onclick="goUrl('queryTBankCardList')" value="返回" /></td>
			</tr>
		</tfoot>
	</table>
</div>
<!--右边框架结束-->
