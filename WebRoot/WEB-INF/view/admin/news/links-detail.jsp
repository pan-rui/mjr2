<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!--右边框架开始-->
<div class="alert alert-info">
	当前位置<b class="tip"></b>合作伙伴信息
</div>
<div class="xiangqing">
	<table>
		<tr>
			<td width="15%" align="right" valign="top" class="bt_ys"><strong>合作伙伴名称：</strong>
			</td>
			<td width="35%" valign="top">${cLinksModel.linkName }</td>
			<td width="15%" align="right" valign="top" class="bt_ys"><strong>合作伙伴简称：</strong>
			</td>
			<td width="35%" valign="top">${cLinksModel.shortName }</td>
		</tr>
		<tr>
			<td align="right" valign="top" class="bt_ys"><strong>官网地址：</strong>
			</td>
			<td valign="top">${cLinksModel.linkUrl }</td>
			<td align="right" valign="top" class="bt_ys"><strong>logo路径：</strong>
			</td>
			<td valign="top">${cLinksModel.imagePath }</td>
		</tr>
		<tr>
			<td align="right" valign="top" class="bt_ys"><strong>合作伙伴描述：</strong>
			</td>
			<td colspan="3" valign="top">${cLinksModel.description }</td>

		</tr>
		<tr>
			<td align="right" valign="top" class="bt_ys"><strong>排序：</strong>
			</td>
			<td valign="top">${cLinksModel.orderNo}</td>
			<td align="right" valign="top" class="bt_ys"><strong>是否删除：</strong>
			</td>
			<td valign="top"><c:choose>
					<c:when test="${cLinksModel.isDelete==0 }">否</c:when>
					<c:otherwise>是</c:otherwise>
				</c:choose>
			</td>

		</tr>
		<tfoot>
			<tr>
				<td colspan="4"><input class="btn btn-inverse" id="btnBack"
					type="button" onclick="goUrl('queryCLinksList')" value="返回" /></td>
			</tr>
		</tfoot>
	</table>
</div>
<!--右边框架结束-->
