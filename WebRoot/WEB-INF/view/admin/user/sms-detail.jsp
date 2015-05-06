<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!--右边框架开始-->
<div class="alert alert-info">
	当前位置<b class="tip"></b>短信详情
</div>
<div class="xiangqing">
	<table>
		<tr>
			<td class="bt_ys"><strong>用户名：</strong>
			</td>
			<td >
				${cSmsModel.userName }
			</td>
			<td class="bt_ys"><strong>真实姓名：</strong>
			</td>
			<td >
				${cSmsModel.realName }
			</td>
		</tr>
		<tr>
			<td class="bt_ys"><strong>手机号码：</strong>
			</td>
			<td  >${cSmsModel.cellPhone }</td>
			<td class="bt_ys"><strong>发送时间：</strong>
			</td>
			<td  >
				<fmt:formatDate value="${cSmsModel.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
		</tr>
		<tr>
			<td class="bt_ys"><strong>发送类型：</strong>
			</td>
			<td>
				${cSmsModel.cellPhone }
			</td>
			<td class="bt_ys"><strong>发送状态：</strong>
			</td>
			<td  >
				<c:choose>
					<c:when test="${cSmsModel.sendStatus==1 }">未发送</c:when>
					<c:when test="${cSmsModel.sendStatus==2 }">发送成功</c:when>
					<c:when test="${cSmsModel.sendStatus==2 }">发送失败</c:when>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td class="bt_ys"><strong>短信内容：</strong>
			</td>
			<td colspan="3">
				${cSmsModel.smsContent}
			</td>
		</tr>
		<tfoot>
			<tr>
				<td colspan="4"><input class="btn btn-inverse" id="btnBack"
					type="button" onclick="goUrl('queryCSmsList')" value="返回" /></td>
			</tr>
		</tfoot>
	</table>
</div>
<!--右边框架结束-->