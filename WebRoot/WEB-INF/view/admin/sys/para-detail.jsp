<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!--右边框架开始-->
<div class="alert alert-info">
	当前位置<b class="tip"></b>参数信息
</div>
<div class="xiangqing">
	<table>
		<tr>
			<td width="15%" align="right" valign="top" class="bt_ys"><strong>参数名称：</strong>
			</td>
			<td width="35%" valign="top">${bParaModel.paraName }</td>
			<td width="15%" align="right" valign="top" class="bt_ys"><strong>编码：</strong>
			</td>
			<td width="35%" valign="top">${bParaModel.paraCode }</td>
		</tr>
		<tr>
			<td width="15%" align="right" valign="top" class="bt_ys"><strong>参数键：</strong>
			</td>
			<td width="35%" valign="top">${bParaModel.paraKey }</td>
			<td width="15%" align="right" valign="top" class="bt_ys"><strong>参数值：</strong>
			</td>
			<td width="35%" valign="top">${bParaModel.paraValue }</td>
		</tr>
		<tr>
			<td align="right" valign="top" class="bt_ys"><strong>是否删除：</strong>
			</td>
			<td colspan="3" valign="top"><c:choose>
					<c:when test="${bParaModel.isDelete==0 }">否</c:when>
					<c:otherwise>是</c:otherwise>
				</c:choose>
			</td>

		</tr>
		<tfoot>
			<tr>
				<td colspan="4"><input class="btn btn-inverse" id="btnBack"
					type="button" onclick="goUrl('queryBParaList')" value="返回" /></td>
			</tr>
		</tfoot>
	</table>
</div>
<!--右边框架结束-->
