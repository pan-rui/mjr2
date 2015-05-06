<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!--右边框架开始-->
<div class="alert alert-info">
	当前位置<b class="tip"></b>添加Banner
</div>
<form id="editForm" action="addCBanner">
	<table class="table table-striped table-bordered table-condensed list">
		<thead>
			<tr>
				<td colspan="4"><b>基本信息</b>
				</td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>Banner名称<font color="FF0000">*</font>
				</td>
				<td><input name="bannerName" value="" type="text"
					datatype="s2-20" nullmsg="请输入参数名称" errorMsg="参数名称长度为2-20位" />
				</td>
				<td>图片路径<font color="FF0000">*</font>
				</td>
				<td><input id="imgPath" name="imgPath" readonly="readonly" value="" type="text"
					datatype="*" nullmsg="请选择图片" /></td>

			</tr>
			<tr>
				<td>网址<font color="FF0000">*</font>
				</td>
				<td><input name="url" value="" type="text" datatype="url|/[a-zA-Z0-9./-:]+/"
					nullmsg="请输入网址" errorMsg="请输入正确网址" /></td>
				<td>排序<font color="FF0000">*</font>
				</td>
				<td><input name="orderNo" value="" type="text" datatype="n1-2"
					nullmsg="请输入排序" errorMsg="排序为0-100的数字" />
				</td>
			</tr>
			<tr>
				<td>是否删除<font color="FF0000">*</font>
				</td>
				<td colspan="3"><select name="isDelete">
						<option value="0">否</option>
						<option value="1">是</option>
				</select>
				</td>


			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="4"><input class="btn btn-inverse" id="btnSave"
					type="button" value="添加" />&nbsp;<input id="btnReset"
					class="btn btn-inverse" type="reset" value="重置" />&nbsp;<input
					class="btn btn-inverse" id="btnBack" type="button"
					onclick="goUrl('queryCBannerList')" value="返回" />&nbsp;&nbsp;<span
					id="errorMsg" class="Validform_checktip"> </span></td>
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
				goUrl('addCBannerIndex');
			}
		}
	});
	$.Tipmsg.r = "";
//-->

	
	$(function() {
		$("#imgPath").click(function() {
			openUpload("imgPath", "Images:/banner/");
		});
	});
</script>
<!--右边框架结束-->