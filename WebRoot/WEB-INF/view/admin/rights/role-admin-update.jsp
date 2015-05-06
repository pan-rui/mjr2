<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!--右边框架开始-->
<div class="alert alert-info">
	当前位置<b class="tip"></b>为${bAdminModel.adminName }分配权限
</div>
<form id="editForm" action="updateBRoleAdmin">
	<table class="table table-striped table-bordered table-condensed list">
		<thead>
			<tr>
				<td colspan="4"><b>基本信息</b> <input id="id" name="adminId"
					value="${bAdminModel.id }" type="hidden" />
				</td>
			</tr>
		</thead>
		<tbody>


			<tr>

				<td>角色权限<font color="FF0000">*</font></td>
				<td colspan="3"><c:forEach items="${roleList }" var="bean">

						<input class="top" type="checkbox" id="top_${bean.id}"
							name="roleIds" value="${bean.id }" />
						<span title="${bean.description }">${bean.roleName }</span>&nbsp;&nbsp;
							
						</c:forEach>
				</td>

			</tr>

		</tbody>
		<tfoot>
			<tr>
				<td colspan="4"><input class="btn btn-inverse" id="btnSave"
					type="button" value="修改" /> &nbsp;<input class="btn btn-inverse"
					id="btnBack" type="button" onclick="goUrl('queryBAdminList')"
					value="返回" />&nbsp;&nbsp;<span id="errorMsg"
					class="Validform_checktip"> &nbsp; </span>
				</td>
			</tr>
		</tfoot>
	</table>
</form>

<script type="text/javascript">
<!--
	var valiForm = $("#editForm").Validform({
		btnSubmit : "#btnSave",
		tiptype : function(msg, o, cssctl) {
			var objtip = $("#errorMsg");
			cssctl(objtip, o.type);
			objtip.text(msg);
		},
		postonce : true,
		ajaxPost : true,
		callback : function(data) {
			alert(data.info);
			if (data.status == 'y') {
				$("#btnBack").click();
			}
		}
	});
	$.Tipmsg.r = "";

	$(function() {

		$.lionPost('queryBRoleAdminByAdminId', {
			'adminId' : '${bAdminModel.id}'
		}, function(data) {
			for ( var i = 0; i < data.length; i++) {

				$("#top_" + data[i].roleId).attr("checked", "checked");
			}

		});
	});
//-->
</script>
<!--右边框架结束-->