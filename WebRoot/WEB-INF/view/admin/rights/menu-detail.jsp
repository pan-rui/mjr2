<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!--右边框架开始-->
<div class="alert alert-info">
	当前位置<b class="tip"></b>菜单信息
</div>
<div class="xiangqing">
	<table>
		<tr>
			<td width="15%" align="right" valign="top" class="bt_ys"><strong>父级菜单：</strong>
			</td>
			<td width="35%" valign="top"><select name="parentId"
				class="parentIdCls" disabled="disabled">
					<option value="-1">无</option>
					<c:forEach items="${parentMenuList }" var="bean">
						<option value="${bean.id }">${bean.menuName }</option>
					</c:forEach>

			</select>
			</td>
			<td width="15%" align="right" valign="top" class="bt_ys"><strong>菜单名称：</strong>
			</td>
			<td width="35%" valign="top">${bMenuModel.menuName }</td>
		</tr>
		<tr>
			<td width="15%" align="right" valign="top" class="bt_ys"><strong>菜单URL：</strong>
			</td>
			<td width="35%" valign="top">${bMenuModel.url }</td>
			<td width="15%" align="right" valign="top" class="bt_ys"><strong>菜单层级：</strong>
			</td>
			<td width="35%" valign="top"><c:choose>
					<c:when test="${bMenuModel.menuLevel==1 }">1级</c:when>
					<c:when test="${bMenuModel.menuLevel==2 }">2级</c:when>
					<c:when test="${bMenuModel.menuLevel==3 }">3级</c:when>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td align="right" valign="top" class="bt_ys"><strong>是否拦截：</strong>
			</td>
			<td colspan="3" valign="top"><c:choose>
					<c:when test="${bMenuModel.isIntercept==0 }">不拦截</c:when>
					<c:when test="${bMenuModel.isIntercept==1 }">拦截</c:when>
				</c:choose>
			</td>

		</tr>
		<tfoot>
			<tr>
				<td colspan="4"><input class="btn btn-inverse" id="btnBack"
					type="button" onclick="goUrl('queryBMenuList')" value="返回" /></td>
			</tr>
		</tfoot>
	</table>
</div>
<script type="text/javascript">
<!--
	$(function() {
		$(".parentIdCls").val('${bMenuModel.parentId}');

	});
//-->
</script>
<!--右边框架结束-->
