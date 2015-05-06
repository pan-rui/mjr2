<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!--右边框架开始-->
<div class="alert alert-info">
	当前位置<b class="tip"></b>添加推荐关系
</div>
<form id="editForm" action="addTRefereeRelation">
	<table class="table table-striped table-bordered table-condensed list">
		<thead>
			<tr>
				<td colspan="4"><b></b>
				</td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>被推荐人<font color="FF0000">*</font></td>
				<td>
				<input id="userName" name="userName" value="" type="text"
					datatype="*" nullmsg="请选择被推荐人" readonly="readonly" onclick="opentUser(1)" />
					<input id="userId" name="userId" type="hidden" />
				</td>
				<td>推荐人<font color="FF0000">*</font></td>
				<td>
					<input id="tUserName" name="tUserName" value="" type="text"
					datatype="*" nullmsg="请选择推荐人" readonly="readonly" onclick="opentUser(2)" />
					<input id="refereeId" name="refereeId" type="hidden" />
				</td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="4"><input class="btn btn-inverse" id="btnSave"
					type="button" value="添加" />&nbsp;<input id="btnReset"
					class="btn btn-inverse" type="reset" value="重置" />&nbsp;<input
					class="btn btn-inverse" id="btnBack" type="button"
					onclick="goUrl('queryTRefereeRelationList')" value="返回" />&nbsp;&nbsp;<span
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
				goUrl('addTRefereeRelationIndex');
			}
		}
	});
	$.Tipmsg.r = "";
	
	var jbox;
	var isbt;
	function opentUser(isb){
		isbt=isb;
		jbox = $.jBox.open("iframe:queryCommCUserList", "用户列表", 1024, 600, {
			buttons : {
				'关闭' : true
			}
		});
	}

	function checkUser(arrays){
		if(isbt==1){
			$("#userName").val(arrays["userName"]);
			$("#userId").val(arrays["userId"]);
		}else{
			$("#tUserName").val(arrays["userName"]);
			$("#refereeId").val(arrays["userId"]);
		}
		$.jBox.close(jbox);
	}
//-->
</script>
<script type="text/javascript" src="${basePath }/js/plugins/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="${basePath }/js/plugins/jBox/i18n/jquery.jBox-zh-CN.js"></script>
<!--右边框架结束-->