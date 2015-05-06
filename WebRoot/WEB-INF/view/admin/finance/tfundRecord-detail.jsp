<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!--右边框架开始-->
<div class="alert alert-info">
	当前位置<b class="tip"></b>资金流水
</div>
<div class="xiangqing">
	<table>
		<tr>
			<td class="bt_ys"><strong>姓名：</strong>
			</td>
			<td  >
			
				${dtomsg.realName }
			</td>
			<td class="bt_ys"><strong>手机号码：</strong>
			</td>
			<td  >${dtomsg.cellPhone }</td>
		</tr>
		<tr>
			<td class="bt_ys"><strong>操作金额：</strong>
			</td>
			<td  >${dtomsg.operAmount }</td>
			<td class="bt_ys"><strong>可用余额增加金额：</strong>
			</td>
			<td  >
				${dtomsg.inAmount }
			</td>
		</tr>
		<tr>
			<td class="bt_ys"><strong>可用余额减少金额：</strong>
			</td>
			<td  >${dtomsg.outAmount }
			</td>
			<td class="bt_ys"><strong>创建时间：</strong>
			</td>
			<td  >
				<fmt:formatDate value="${dtomsg.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
		</tr>
		<tr>
			<td class="bt_ys"><strong>项目名称：</strong>
			</td>
			<td>
				${dtomsg.fundMode}
			</td>
			<td class="bt_ys"><strong>项目类型：</strong>
			</td>
			<td  >
				${dtomsg.fundType}
			</td>
		</tr>
		
		<tr>
			<td class="bt_ys"><strong>备注：</strong>
			</td>
			<td>
				${dtomsg.remarks}
			</td>
			<td class="bt_ys"><strong>可用余额：</strong>
			</td>
			<td  >
				${dtomsg.usableAmount}
			</td>
		</tr>
		<tr>
			<td class="bt_ys"><strong>冻结金额：</strong>
			</td>
			<td>
				${dtomsg.frozenAmount}
			</td>
			<td class="bt_ys"><strong>操作类型：</strong>
			</td>
			<td  >
				<c:choose>
						<c:when test="${dtomsg.operType==1 }">添加可用余额</c:when>
						<c:when test="${dtomsg.operType==2 }">冻结可用余额</c:when>
						<c:when test="${dtomsg.operType==3 }">解冻可用余额</c:when>
						<c:when test="${dtomsg.operType==4 }">减少可用余额</c:when>
					</c:choose>
			</td>
		</tr>
		<%-- <tfoot>
			<tr>
				<td colspan="4"><input class="btn btn-inverse" id="btnBack"
					type="button" onclick="goUrl('selectTFundRecordList?userId=${dtomsg.userId}')" value="返回" /></td>
			</tr>
		</tfoot> --%>
	</table>
</div>
<!--右边框架结束-->
