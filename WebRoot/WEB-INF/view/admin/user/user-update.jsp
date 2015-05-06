<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!--右边框架开始-->
<div class="alert alert-info">
	当前位置<b class="tip"></b>修改菜单
</div>
<form id="editForm" action="updateCUser">
	<div class="xiangqing">
		<table>
			<thead>
				<tr>
					<td colspan="4"><b>基本信息</b> <input id="id" name="id"
						value="${cUserModel.id }" type="hidden" />
					</td>
				</tr>
			</thead>
			<tr>
				<td class="bt_ys"><strong>用户名：</strong></td>
				<td>${cUserModel.userName }</td>
				<td class="bt_ys"><strong>手机号码：</strong></td>
				<td>${cUserModel.cellPhone }</td>
			</tr>
			<tr>
				<td class="bt_ys"><strong>邮箱地址：</strong></td>
				<td colspan="3">${cUserModel.email }</td>
			</tr>
			<tr>
				<td class="bt_ys"><strong>用户来源：</strong>
				</td>
				<td><select name="source" id="source" class="whereSource">
						<option value="0">普通</option>
						<option value="1">CPA</option>
						<option value="2">CPS</option>
						<option value="3">百度</option>
				</select></td>
				<td class="bt_ys"><strong>是否禁用：</strong>
				</td>
				<td><select name="isEnable" id="isEnable" class="isEnableCls">
						<option value="0">不禁用</option>
						<option value="1">禁用</option>
				</select></td>
			</tr>
			<tr>
				<td class="bt_ys"><strong>创建时间：</strong>
				</td>
				<td><fmt:formatDate value="${cUserModel.createTime}"
						pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td class="bt_ys"><strong>注册IP：</strong></td>
				<td>${cUserModel.regIp}</td>
			</tr>
			<tr>
				<td class="bt_ys"><strong>最后登录IP：</strong></td>
				<td>${cUserModel.lastLoginIp}</td>
				<td class="bt_ys"><strong>最后登录时间：</strong></td>
				<td><fmt:formatDate value="${cUserModel.lastLoginTime}"
						pattern="yyyy-MM-dd HH:mm:ss" /></td>
			</tr>
			<tr>
				<td class="bt_ys" colspan="4" style="text-align: center;"><strong>实名信息</strong>
				</td>
			</tr>
			<c:choose>
				<c:when test="${cUserModel.realName == null }">
					<tr>
						<td colspan="4" style="text-align: center;">没有实名</td>
					</tr>
				</c:when>
				<c:when test="${cUserModel.realName == '' }">
					<tr>
						<td colspan="4" style="text-align: center;">没有实名</td>
					</tr>
				</c:when>
				<c:otherwise>
					<tr>
						<td class="bt_ys"><strong>真实姓名：</strong>
						</td>
						<td>${cUserModel.realName }</td>
						<td class="bt_ys"><strong>性别：</strong>
						</td>
						<td><select name="sex" id="sex" class="whysex">
								<option value="1">男</option>
								<option value="2">女</option>
						</select></td>
					</tr>
					<tr>
						<td class="bt_ys"><strong>实名时间：</strong>
						</td>
						<td><fmt:formatDate value="${cUserModel.pcreateTime}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td class="bt_ys"><strong>身份证号：</strong>
						</td>
						<td>${cUserModel.cardNo}</td>
					</tr>
				</c:otherwise>
			</c:choose>
			<tfoot>
				<tr>
					<td colspan="4"><input class="btn btn-inverse" id="btnSave"
						type="button" value="修改" /> &nbsp;<input class="btn btn-inverse"
						id="btnBack" type="button" onclick="goUrl('queryCUserList')"
						value="返回" />&nbsp;&nbsp;<span id="errorMsg"
						class="Validform_checktip"> &nbsp; </span>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
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
		$(".isEnableCls").val('${cUserModel.isEnable}');
		$(".whereSource").val('${cUserModel.source}');
		$(".whysex").val('$(cUserModel.sex)');
	});
//-->
</script>
<!--右边框架结束-->