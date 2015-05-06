<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!--右边框架开始-->
<div class="alert alert-info">
	当前位置<b class="tip"></b>用户详细信息
</div>
<div class="xiangqing">
	<table>
		<tr>
			<td colspan="4" style="text-align: center;"><strong>基本信息</strong>
			</td>
		</tr>
		<tr>
			<td class="bt_ys"><strong>用户名：</strong>
			</td>
			<td  >
				${cUserModel.userName }
			</td>
			<td class="bt_ys"><strong>手机号码：</strong>
			</td>
			<td  >${cUserModel.cellPhone }</td>
		</tr>
		<tr>
			<td class="bt_ys"><strong>邮箱地址：</strong>
			</td>
			<td colspan="3" >${cUserModel.email }</td>
		</tr>
		<tr>
			<td class="bt_ys"><strong>用户来源：</strong></td>
			<td><c:choose>
					<c:when test="${cUserModel.source==0 }">普通</c:when>
					<c:when test="${cUserModel.source==1 }">CPA</c:when>
					<c:when test="${cUserModel.source==2 }">CPS</c:when>
					<c:when test="${cUserModel.source==3 }">百度</c:when>
				</c:choose>
			</td>
			<td class="bt_ys"><strong>是否禁用：</strong></td>
			<td><c:choose>
					<c:when test="${cUserModel.isEnable==0 }">不禁用</c:when>
					<c:when test="${cUserModel.isEnable==1 }">禁用</c:when>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td class="bt_ys"><strong>创建时间：</strong></td>
			<td  >
				<fmt:formatDate value="${cUserModel.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
			<td class="bt_ys"><strong>注册IP：</strong>
			</td>
			<td >
				${cUserModel.regIp}
			</td>
		</tr>
		<tr>
			<td class="bt_ys"><strong>最后登录IP：</strong>
			</td>
			<td>
				${cUserModel.lastLoginIp}
			</td>
			<td class="bt_ys"><strong>最后登录时间：</strong>
			</td>
			<td  >
				<fmt:formatDate value="${cUserModel.lastLoginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
		</tr>
		<tr>
			<td class="bt_ys" colspan="4" style="text-align: center;"><strong>实名信息</strong>
			</td>
		</tr>
		<c:choose>
			<c:when test="${cUserModel.isAuth == 1 }">
				<tr>
					<td class="bt_ys" ><strong>真实姓名：</strong></td>
					<td  >
						${cUserModel.realName }
					</td>
					<td class="bt_ys"><strong>性别：</strong></td>
					<td >
						<c:choose>
							<c:when test="${cUserModel.sex==1 }">男</c:when>
							<c:when test="${cUserModel.sex==2 }">女</c:when>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td class="bt_ys"><strong>实名时间：</strong></td>
					<td >
						<fmt:formatDate value="${cUserModel.pcreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td >
					<td class="bt_ys"><strong>身份证号：</strong></td>
					<td >
						${cUserModel.cardNo}
					</td>
				</tr>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="4" style="text-align: center; ">没有实名</td>
				</tr>
			</c:otherwise>
		</c:choose>
		<tfoot>
			<tr>
				<td colspan="4"><input class="btn btn-inverse" id="btnBack"
					type="button" onclick="goUrl('queryCUserList')" value="返回" /></td>
			</tr>
		</tfoot>
	</table>
</div>
<!--右边框架结束-->
