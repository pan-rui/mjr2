<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!--右边框架开始-->
<div class="alert alert-info">
	当前位置<b class="tip"></b>角色信息
</div>
<div class="xiangqing">
	<table>
		<tr>
			<td width="15%" align="right" valign="top" class="bt_ys"><strong>角色名称：</strong>
			</td>
			<td width="35%" valign="top">${bRoleModel.roleName }</td>
			<td width="15%" align="right" valign="top" class="bt_ys"><strong>描述：</strong>
			</td>
			<td width="35%" valign="top">${bRoleModel.description }</td>
		</tr>

		<tfoot>
			<tr>
				<td colspan="4"><input class="btn btn-inverse" id="btnBack"
					type="button" onclick="goUrl('queryBRoleList')" value="返回" />
				</td>
			</tr>
		</tfoot>
	</table>
</div>
<!--右边框架结束-->
