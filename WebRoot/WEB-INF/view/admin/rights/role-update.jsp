<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!--右边框架开始-->
<div class="alert alert-info">
	当前位置<b class="tip"></b>修改角色
</div>
<form id="editForm" action="updateBRole">
	<table class="table table-striped table-bordered table-condensed list">
		<thead>
			<tr>
				<td colspan="4"><b>基本信息</b> <input id="id" name="id"
					value="${bRoleModel.id }" type="hidden" /></td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>角色名称<font color="FF0000">*</font>
				</td>
				<td colspan="3"><input name="roleName"
					value="${bRoleModel.roleName }" type="text" datatype="s2-10"
					nullmsg="请输入角色名称" errorMsg="角色名称长度为2-10位" />
				</td>


			</tr>
			<tr>

				<td>角色描述<font color="FF0000">*</font>
				</td>
				<td colspan="3"><textarea name="description" style="width: 95%"
						rows="4" cols="5" datatype="s2-50" nullmsg="请输入角色描述"
						errorMsg="角色描述长度为2-50位">${bRoleModel.description }</textarea></td>

			</tr>
			<tr>

				<td>菜单权限<font color="FF0000">*</font>
				</td>
				<td colspan="3"><c:forEach items="${menuList }" var="parent">
						<c:if test="${parent.parentId==-1 }">
							<input class="top" type="checkbox" id="top_${parent.id}"
								name="menuId" value="${parent.id }" />${parent.menuName }<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<c:forEach items="${menuList }" var="bean">
								<c:if test="${parent.id ==bean.parentId}">
									<input class="top_${parent.id} parent" name="menuId"
										id="top_${bean.id}" parentId="${bean.parentId }"
										type="checkbox" value="${bean.id }" />${bean.menuName }
								</c:if>
							</c:forEach>
							<br />
						</c:if>

					</c:forEach></td>

			</tr>

		</tbody>
		<tfoot>
			<tr>
				<td colspan="4"><input class="btn btn-inverse" id="btnSave"
					type="button" value="修改" /> &nbsp;<input class="btn btn-inverse"
					id="btnBack" type="button" onclick="goUrl('queryBRoleList')"
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

		$(".top").click(function() {
			var id = $(this).val();
			if ($(this).attr("checked") == "checked") {
				$(".top_" + id).attr("checked", true);
			} else {
				$(".top_" + id).attr("checked", false);
			}

		});

		$(".parent").click(function() {
			var id = $(this).val();
			$(".parent_" + id).attr("checked", $(this).attr("checked"));
			if (this.checked) {
				var topId = $(this).attr("parentId");
				$("#top_" + topId).attr("checked", true);
			}
		});

		$.lionPost('queryBRoleMenuById', {
			'roleId' : '${bRoleModel.id}'
		}, function(data) {
			for ( var i = 0; i < data.length; i++) {

				$("#top_" + data[i].menuId).attr("checked", "checked");
			}

		});
	});
//-->
</script>
<!--右边框架结束-->