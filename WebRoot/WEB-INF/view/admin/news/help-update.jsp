<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!--右边框架开始-->
<div class="alert alert-info">
	当前位置<b class="tip"></b>修改帮助
</div>
<form id="editForm" action="updateCHelp">
	<table class="table table-striped table-bordered table-condensed list">
		<thead>
			<tr>
				<td colspan="4"><b>基本信息</b> <input id="id" name="id"
					value="${cHelpModel.id }" type="hidden" />
				</td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>问题<font color="FF0000">*</font>
				</td>
				<td><input name="questionName" value="${cHelpModel.questionName }" type="text"
					datatype="s2-80" nullmsg="请输入问题" errorMsg="问题长度为2-80位" />
				</td>
				<td>帮助类型<font color="FF0000">*</font>
				</td>
				<td><select name="helpTypeId" class="helpTypeIdCls">
						<c:forEach items="${helpTypeList }" var="bean">
							<option value="${bean.id }">${bean.helpTypeName }</option>
						</c:forEach>
				</select></td>

			</tr>
			<tr>
				<td>回答<font color="FF0000">*</font>
				</td>
				<td colspan="3"><textarea rows="3" id="answer" name="answer"
						cols="100" style="width: 80%" datatype="*" nullmsg="请输入回答">${cHelpModel.answer }</textarea>

				</td>
			</tr>

		</tbody>
		<tfoot>
			<tr>
				<td colspan="4"><input class="btn btn-inverse" id="btnSave"
					type="button" value="修改" /> &nbsp;<input class="btn btn-inverse"
					id="btnBack" type="button" onclick="goUrl('queryCHelpList')"
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
			$("#answer").val(editor.document.getBody().getHtml()); 		
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
		$(".helpTypeIdCls").val('${cHelpModel.helpTypeId}');

	});
	
	editor = CKEDITOR.replace('answer', {
		 filebrowserBrowseUrl:'${basePath}/js/plugins/ckfinder/ckfinder.html', 
		 filebrowserImageBrowseUrl:'${basePath}/js/plugins/ckfinder/ckfinder.html?type=Images', 
		 filebrowserFlashBrowseUrl: '${basePath}/js/plugins/ckfinder/ckfinder.html?type=Flash',  
		 filebrowserUploadUrl:'${basePath}/js/plugins/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files', 
		 filebrowserImageUploadUrl:'${basePath}/js/plugins/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images', 
		 filebrowserFlashUploadUrl:'${basePath}/js/plugins/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash'
	});

	//实例化
	CKFinder.setupCKEditor(editor, "${basePath}/js/plugins/ckfinder/");//相对路径
//-->
</script>

<!--右边框架结束-->