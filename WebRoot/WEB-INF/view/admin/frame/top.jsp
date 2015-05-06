<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!--头部开始-->
<div class="top_c">
	<div style="font-size: 35px;font-family:'Microsoft YaHei';height: 97px; color: white; line-height: 97px;padding-left: 20px;">
	P2P后台管理系统
	</div>
	<div class="top-menu">
		<ul class="top-menu-nav">
			<%--<li><a href="/">首页</a>
			</li>
			<li><a href="#">查询界面</a></li>
			<li><a href="#">辅助信息</a></li>
		--%></ul>
	</div>
	<div class="top-nav">
		上午好，欢迎您，${admin.adminName }！&nbsp;&nbsp;<a href="javascript:goUrl('updateBAdminPwdIndex')">修改密码</a> | <a href="loginOut">安全退出</a>
	</div>
</div>
<!--头部结束-->
