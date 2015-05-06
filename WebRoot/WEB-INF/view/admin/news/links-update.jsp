<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!--右边框架开始-->
<div class="alert alert-info">
	当前位置<b class="tip"></b>修改合作伙伴
</div>
<form id="editForm" action="updateCLinks">
	<table class="table table-striped table-bordered table-condensed list">
		<thead>
			<tr>
				<td colspan="4"><b>基本信息</b> <input id="id" name="id"
					value="${cLinksModel.id }" type="hidden" />
				</td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>合作伙伴名称<font color="FF0000">*</font>
				</td>
				<td><input name="linkName" value="${cLinksModel.linkName }"
					type="text" datatype="s2-30" nullmsg="请输入合作伙伴名称"
					errorMsg="合作伙伴名称长度为2-30位" />
				</td>
				<td>合作伙伴简称<font color="FF0000">*</font>
				</td>
				<td><input name="shortName" value="${cLinksModel.shortName }"
					type="text" datatype="s2-20" nullmsg="请输入合作伙伴简称"
					errorMsg="合作伙伴简称长度为2-20位" /></td>

			</tr>
			<tr>
				<td>官网地址<font color="FF0000">*</font>
				</td>
				<td><input name="linkUrl" value="${cLinksModel.linkUrl }"
					type="text" datatype="url" nullmsg="请输入官网地址" errorMsg="官网地址格式错误" />
				</td>
				<td>logo路径<font color="FF0000">*</font>
				</td>
				<td><input id="imagePath" name="imagePath" readonly="readonly" value="${cLinksModel.imagePath }"
					type="text" datatype="*" nullmsg="请选择logo图片" />
				</td>
			</tr>
			<tr>
				<td>合作伙伴描述<font color="FF0000">*</font>
				</td>
				<td colspan="3"><textarea rows="3" name="description"
						cols="100" style="width: 80%" datatype="s2-150" nullmsg="请输入官网地址"
						errorMsg="合作伙伴描述长度为2-150位">${cLinksModel.description }</textarea>
				</td>
			</tr>
			<tr>
				<td>排序<font color="FF0000">*</font>
				</td>
				<td colspan="3"><input name="orderNo"
					value="${cLinksModel.orderNo }" type="text" datatype="n1-3"
					nullmsg="请输入排序" errorMsg="排序的数字格式有误" /></td>
				<td>是否删除<font color="FF0000">*</font>
				</td>
				<td><select name="isDelete">
						<option value="0">否</option>
						<option value="1">是</option>
				</select>
				</td>


			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="4"><input class="btn btn-inverse" id="btnSave"
					type="button" value="修改" /> &nbsp;<input class="btn btn-inverse"
					id="btnBack" type="button" onclick="goUrl('queryCLinksList')"
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

	$(function() {
		$("#imagePath").click(function() {
			openUpload("imagePath", "Images:/links/");
		});
	});
</script>
<!--右边框架结束-->