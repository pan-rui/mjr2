<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!--右边框架开始-->
<div class="alert alert-info">
	当前位置<b class="tip"></b>添加菜单
</div>
<form id="editForm" action="addBMenu">
	<table class="table table-striped table-bordered table-condensed list">
		<thead>
			<tr>
				<td colspan="4"><b>基本信息</b>
				</td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>父级菜单<font color="FF0000">*</font>
				</td>
				<td><select name="parentId">
						<option value="-1">无</option>
						<c:forEach items="${parentMenuList }" var="bean">
							<option value="${bean.id }">${bean.menuName }</option>
						</c:forEach>

				</select>
				</td>
				<td>菜单名称<font color="FF0000">*</font>
				</td>
				<td><input name="menuName" value="" type="text"
					datatype="*2-20" nullmsg="请输入菜单名称" errorMsg="菜单名称长度为2-20位" /></td>

			</tr>
			<tr>
				<td>菜单URL<font color="FF0000">*</font>
				</td>
				<td><input name="url" value="" type="text" datatype="s5-50"
					nullmsg="菜单URL" ignore="ignore" errorMsg="菜单URL长度为5-50位" /></td>
				<td>菜单层级<font color="FF0000">*</font>
				</td>
				<td><select name="menuLevel">
						<option value="1">1级</option>
						<option value="2">2级</option>
						<option value="3">3级</option>
				</select>
				</td>
			</tr>
			<tr>
				<td>是否拦截<font color="FF0000">*</font>
				</td>
				<td colspan="3"><select name="isIntercept">
						<option value="0">不拦截</option>
						<option value="1">拦截</option>
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
					onclick="goUrl('queryBMenuList')" value="返回" />&nbsp;&nbsp;<span
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
				goUrl('addBMenuIndex');
			}
		}
	});
	$.Tipmsg.r = "";
//-->
</script>
<!--右边框架结束-->