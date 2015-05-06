<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!--右边框架开始-->
<div class="alert alert-info">
	当前位置<b class="tip"></b>修改媒体报道
</div>
<form id="editForm" action="updateCMediaReport">
	<table class="table table-striped table-bordered table-condensed list">
		<thead>
			<tr>
				<td colspan="4"><b>基本信息</b> <input id="id" name="id"
					value="${cMediaReportModel.id }" type="hidden" />
				</td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>标题<font color="FF0000">*</font>
				</td>
				<td><input name="title" value="${cMediaReportModel.title }" type="text"
					datatype="*2-38" nullmsg="请输入标题" errorMsg="标题长度为2-38位" />
				</td>
				<td>排序<font color="FF0000">*</font>
				</td>
				<td><input name="orderNo" value="${cMediaReportModel.orderNo }" type="text"
					datatype="n1-3" nullmsg="请输入排序" errorMsg="排序数字格式不正确" /></td>

			</tr>
			<tr>
				<td>来源名称<font color="FF0000">*</font>
				</td>
				<td><input name="src" value="${cMediaReportModel.src }" type="text" datatype="s1-20"
					nullmsg="请输入来源名称" errorMsg="来源名称长度为1-20位" /></td>
				<td>来源URL<font color="FF0000">*</font>
				</td>
				<td><input name="srcUrl" value="${cMediaReportModel.srcUrl }" type="text"
					datatype="url" nullmsg="请输入来源URL" errorMsg="来源URL格式不正确" />
				</td>
			</tr>
			<tr>
				<td>关键字<font color="FF0000">*</font>
				</td>
				<td colspan="3">
					<input name="keywords" value="${cMediaReportModel.keywords }" type="text"
					datatype="*" nullmsg="请输入关键字" errorMsg="关键字输入有误" />
					<span style="color: red;">两个关键字之间，请使用中文“，”隔开。例如：网袋，投资</span>
				</td>
			
			</tr>
			<tr>
				<td>内容<font color="FF0000">*</font>
				</td>
				<td colspan="3"><textarea rows="3" id="content" name="content"
						cols="100" style="width: 80%" datatype="*" nullmsg="请输入内容">${cMediaReportModel.content }</textarea>

				</td>
			
			</tr>
			<tr>
				<td>图片路径<font color="FF0000">*</font>
				</td>
				<td><input name="srcImgPath" value="${cMediaReportModel.srcImgPath }" type="text"
					datatype="s1-50" nullmsg="请输入图片路径" errorMsg="图片路径不正确" />
				</td>
			
				<td>是否删除<font color="FF0000">*</font>
				</td>
				<td colspan="3"><select name="isDelete" class="isDeleteCls">
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
					id="btnBack" type="button" onclick="goUrl('queryCMediaReportList')"
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
		beforeCheck:function(curform){
			//在表单提交执行验证之前执行的函数，curform参数是当前表单对象。
			//这里明确return false的话将不会继续执行验证操作;	
			$("#content").val(contentEditor.document.getBody().getHtml()); 
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
	
	contentEditor = CKEDITOR.replace('content', {
		 filebrowserBrowseUrl:'${basePath}/js/plugins/ckfinder/ckfinder.html', 
		 filebrowserImageBrowseUrl:'${basePath}/js/plugins/ckfinder/ckfinder.html?type=Images', 
		 filebrowserFlashBrowseUrl: '${basePath}/js/plugins/ckfinder/ckfinder.html?type=Flash',  
		 filebrowserUploadUrl:'${basePath}/js/plugins/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files', 
		 filebrowserImageUploadUrl:'${basePath}/js/plugins/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images', 
		 filebrowserFlashUploadUrl:'${basePath}/js/plugins/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash'
	});
	//实例化
	CKFinder.setupCKEditor(contentEditor, "${basePath}/js/plugins/ckfinder/");//相对路径
	
	$(function() {
		$(".isDeleteCls").val('${cMediaReportModel.isDelete}');

	});
//-->
</script>
<!--右边框架结束-->