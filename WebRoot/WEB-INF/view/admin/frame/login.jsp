<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>

<!DOCTYPE html>
<html>
<head>
<title>后台管理系统</title>
<meta charset="utf-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="keywords" content="keyword1,keyword2,keyword3">
<meta name="description" content="This is my page">



<script type="text/javascript"
	src="${basePath}/js/admin/jquery-1.7.2.js"></script>
<script type="text/javascript"
	src="${basePath}/js/Validform_v5.3.2_min.js"></script>

<LINK href="${basePath}/css/Default.css" type=text/css rel=stylesheet>
<LINK href="${basePath}/css/xtree.css" type=text/css rel=stylesheet>
<LINK href="${basePath}/css/User_Login.css" type=text/css rel=stylesheet>
</head>

<BODY id=userlogin_body>





<form action="adminLogin" id="editForm">
<DIV id=user_login>
<DL>
  <DD id=user_top>
  <UL>
    <LI class=user_top_l></LI>
    <LI class=user_top_c></LI>
    <LI class=user_top_r></LI></UL>
  <DD id=user_main>
  <UL>
    <LI class=user_main_l></LI>
    <LI class=user_main_c>
    
    <DIV class=user_main_box>
    <UL>
      <LI class=user_main_text>用户名： </LI>
      <LI class=user_main_input><INPUT class=TxtUserNameCssClass id=TxtUserName 
      maxLength=20 name=adminName> </LI></UL>
    <UL>
      <LI class=user_main_text>密 码： </LI>
      <LI class=user_main_input><INPUT class=TxtPasswordCssClass id=TxtPassword 
      type=password name=pwd> </LI>
      <li><span id="errorMsg" style="color: red"></span></li>
      </UL>
    </DIV></LI>
	
    <LI class=user_main_r><INPUT class=IbtnEnterCssClass id=btnLogin 
    style="BORDER-TOP-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-RIGHT-WIDTH: 0px" 
    type=image src="../images/user_botton.gif" name=IbtnEnter> </LI>

</UL>


  </DL></DIV><SPAN id=ValrUserName 
style="DISPLAY: none; COLOR: red"></SPAN><SPAN id=ValrPassword 
style="DISPLAY: none; COLOR: red"></SPAN><SPAN id=ValrValidateCode 
style="DISPLAY: none; COLOR: red"></SPAN>
<DIV id=ValidationSummary1 style="DISPLAY: none; COLOR: red"></DIV>
</form>
<DIV></DIV>
</BODY>
<script type="text/javascript">

	var valiForm = $("#editForm").Validform({
		btnSubmit : "#btnLogin",
		tiptype : function(msg, o, cssctl) {
			var objtip = $("#errorMsg");
			cssctl(objtip, o.type);
			objtip.text(msg);
		},
		postonce : true,
		ajaxPost : true,
		callback : function(data) {
			if (data.status == 'y') {
				window.location.href="toMain";
			}
		}
	});
	$.Tipmsg.r = "";

</script>
</html>
