<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!--右边框架开始-->
<div class="alert alert-info">
	当前位置<b class="tip"></b>修改帮助类型
</div>
<form id="editForm" action="updateCHelpType">
	<table class="table table-striped table-bordered table-condensed list">
		<thead>
			<tr>
				<td colspan="4"><b>基本信息</b> <input id="id" name="id"
					value="${cHelpTypeModel.id }" type="hidden" />
				</td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>帮助类型名称<font color="FF0000">*</font>
				</td>
				<td><input name="helpTypeName"
					value="${cHelpTypeModel.helpTypeName }" type="text"
					datatype="s2-20" nullmsg="请输入帮助类型名称" errorMsg="帮助类型名称长度为2-20位" />
				</td>
				<td>排序<font color="FF0000">*</font>
				</td>
				<td><input name="orderNo" value="${cHelpTypeModel.orderNo }"
					type="text" datatype="n1-2" nullmsg="请输入排序" errorMsg="排序的数字格式不正确" />
				</td>

			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="4"><input class="btn btn-inverse" id="btnSave"
					type="button" value="修改" /> &nbsp;<input class="btn btn-inverse"
					id="btnBack" type="button" onclick="goUrl('queryCHelpTypeList')"
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
		$(".isDeleteCls").val('${bParaModel.isDelete}');

	});
//-->
</script>
<!--右边框架结束-->