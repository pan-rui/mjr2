<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!--右边框架开始-->
<div class="alert alert-info">
	当前位置<b class="tip"></b>修改参数
</div>
<form id="editForm" action="updateBPara">
	<table class="table table-striped table-bordered table-condensed list">
		<thead>
			<tr>
				<td colspan="4"><b>基本信息</b> <input id="id" name="id"
					value="${bParaModel.id }" type="hidden" />
				</td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>参数名称<font color="FF0000">*</font></td>
				<td><input name="paraName" value="${bParaModel.paraName }"
					type="text" datatype="s2-20" nullmsg="请输入参数名称"
					errorMsg="参数名称长度为2-20位" /></td>
				<td>参数编码<font color="FF0000">*</font></td>
				<td><input name="paraCode" value="${bParaModel.paraCode }"
					type="text" datatype="s2-20" nullmsg="请输入参数编码"
					errorMsg="参数编码长度为2-20位" />
				</td>

			</tr>
			<tr>
				<td>参数键<font color="FF0000">*</font></td>
				<td><input name="paraKey" value="${bParaModel.paraKey }"
					type="text" datatype="*1-20" nullmsg="请输入参数键"
					errorMsg="参数键长度为1-20位" />
				</td>
				<td>参数值<font color="FF0000">*</font></td>
				<td><input name="paraValue" value="${bParaModel.paraValue }"
					type="text" datatype="*1-30" nullmsg="请输入参数值"
					errorMsg="参数值长度为1-30位" /></td>
			</tr>
			<tr>
				<td>是否删除<font color="FF0000">*</font></td>
				<td colspan="3"><select name="isDelete" class="isDeleteCls">
						<option value="0">否</option>
						<option value="1">是</option>
				</select></td>


			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="4"><input class="btn btn-inverse" id="btnSave"
					type="button" value="修改" /> &nbsp;<input class="btn btn-inverse"
					id="btnBack" type="button" onclick="goUrl('queryBParaList')"
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