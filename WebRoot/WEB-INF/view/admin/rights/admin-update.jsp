<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!--右边框架开始-->
<div class="alert alert-info">
	当前位置<b class="tip"></b>修改管理员
</div>
<form id="editForm" action="updateBAdmin">
	<table class="table table-striped table-bordered table-condensed list">
		<thead>
			<tr>
				<td colspan="4"><b>基本信息</b> <input id="id" name="id"
					value="${bAdminModel.id }" type="hidden" /></td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>管理员用户名<font color="FF0000">*</font></td>
				<td><input name="adminName" value="${bAdminModel.adminName }"
					type="text" datatype="s5-20" ignore="ignore" nullmsg="请输入管理员用户名"
					errorMsg="管理员用户名长度为5-20位" />
				</td>
				<td>是否可用<font color="FF0000">*</font></td>
				<td><select name="isEnable" class="isEnableCls">
						<option value="1">是</option>
						<option value="0">否</option>
				</select></td>
			</tr>
			<tr>
				<td>密 码<font color="FF0000">*</font></td>
				<td><input name="pwd" value="" type="password" datatype="*6-18"
					ignore="ignore" nullmsg="请输入密码" errorMsg="密码长度为6-18位" /></td>
				<td>确认密码<font color="FF0000">*</font></td>
				<td><input name="confirmPwd" value="" type="password"
					datatype="*" ignore="ignore" recheck="pwd" nullmsg="请输入确认密码"
					errorMsg="两次输入的密码不一致" /></td>
			</tr>

		</tbody>
		<tfoot>
			<tr>
				<td colspan="4"><input class="btn btn-inverse" id="btnSave"
					type="button" value="修改" /> &nbsp;<input class="btn btn-inverse"
					id="btnBack" type="button" onclick="goUrl('queryBAdminList')"
					value="返回" />&nbsp;&nbsp;<span id="errorMsg"
					class="Validform_checktip"> &nbsp; </span></td>
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
		$(".isEnableCls").val('${bAdminModel.isEnable}');
	});
//-->
</script>
<!--右边框架结束-->