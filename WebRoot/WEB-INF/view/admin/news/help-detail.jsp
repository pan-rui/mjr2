<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!--右边框架开始-->
<div class="alert alert-info">
	当前位置<b class="tip"></b>帮助信息
</div>
<div class="xiangqing">
	<table>
		<tr>
			<td width="15%" align="right" valign="top" class="bt_ys"><strong>问题：</strong>
			</td>
			<td width="35%" valign="top">${cHelpModel.questionName }</td>
			<td width="15%" align="right" valign="top" class="bt_ys"><strong>帮助类型：</strong>
			</td>
			<td width="35%" valign="top"><select name="helpTypeId" disabled="disabled" class="helpTypeIdCls">
						<c:forEach items="${helpTypeList }" var="bean">
							<option value="${bean.id }">${bean.helpTypeName }</option>
						</c:forEach>
				</select></td>
		</tr>
		
		<tr>
			<td align="right" valign="top" class="bt_ys"><strong>回答：</strong>
			</td>
			<td colspan="3" valign="top">${cHelpModel.answer}
			</td>

		</tr>
		<tfoot>
			<tr>
				<td colspan="4"><input class="btn btn-inverse" id="btnBack"
					type="button" onclick="goUrl('queryCHelpList')" value="返回" /></td>
			</tr>
		</tfoot>
	</table>
</div>
<script type="text/javascript">
<!--
	$(function() {
		$(".helpTypeIdCls").val('${cHelpModel.helpTypeId}');

	});
//-->
</script>
<!--右边框架结束-->
