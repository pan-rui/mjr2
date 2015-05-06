<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>P2P - 登陆页面</title>
<link rel="stylesheet" href="css/common.css" type="text/css" />
<link rel="stylesheet" href="css/page.css" type="text/css" />
<link rel="stylesheet" href="css/validform.css" type="text/css"></link>
</head>

<body>
	<!--header start-->
	<jsp:include page="/WEB-INF/view/front/comm/header.jsp"></jsp:include>
	<!--header end-->

	<!--center start-->
	<div id="p_login">
		<div class="p1000 pl_page">
			<!--banner start-->
			<div class="log_banner">
				 <img src="images/ad/log_banner.gif" />
			</div>
			<!--banner end-->
			<!--登陆框 start-->
			<div class="log_form">
				<form action="login.html" method="post" id="editForm">
					<div class="log_main">
						<h2 class="log_h2">用户登录</h2>
						<div class="log_box">
							<input type="text" name="userName" id="userName" class="log_text"
								placeholder="用户名或手机号码" datatype="s4-16|m" nullmsg="用户名不能为空"
								errormsg="请输入正确的手机号码或4-16位用户名" /> <i class="log_ico log_ico1"></i>
						</div>
						<div class="log_box">
							<input type="password" name="pwd" id="pwd" class="log_text"
								placeholder="密码" datatype="s6-16" nullmsg="密码不能为空"
								errormsg="请输入正确6-16位密码" /> <i class="log_ico log_ico2"></i>
						</div>
						<div class="log_box">
							<input type="text" name="loginCode" id="loginCode"
								maxlength="4" 
								onkeyup="this.value=this.value.replace(/\D/g,'')"
								class="log_yzm" placeholder="验证码" />
							<i class="log_ico log_ico3"></i>
							<!--验证码代码-->
							<img width="100" id="codeNum" style="cursor:pointer; "
								src="randomImageCode.html?pageId=userlogin" class="verifyImg"
								title="换一张" alt="验证码" onclick="javascript:switchCode()" />
							<div class="yan_ico" id="loginCodeTs"></div>
						</div>
						
						<div class="log_but">
							<input type="button" id="btnSave" class="log_button" value="立即登录" />
						</div>
						<span id="errorMsg"></span>
						<div class="log_part">
							没有P2P账号？ <a href="regIndex.html" class="log_pa1" target="_blank">免费注册</a> <span
								style="padding-left:50px"><a href="forGetPassInfo.html" class="log_pa1"
								target="_blank">忘记密码 ?</a> </span>
						</div>
					</div>
				</form>
			</div>
			<!--登陆框 end-->
		</div>

	</div>
	<!--center end-->


	<!--header start-->
	<jsp:include page="/WEB-INF/view/front/comm/footer.jsp"></jsp:include>
	<!--header end-->

	<script type="text/javascript" src="js/Validform_v5.3.2_min.js"></script>
</body>
<script type="text/javascript">
<!--

	$(function(){
		$("#userName").val("");
		$("#pwd").val("");
	});
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

			if (data.status == 'y') {
				if (data.info != '') {
					window.location.href = data.info;
				} else {
					window.location.href = "center/selectUserIndex.html";
				}
			}
		}
	});
	$.Tipmsg.r = "";

	//刷新验证码
	function switchCode() {

		var timenow = new Date();

		$("#code").val("");

		$("#code").css({
			color : "#999"
		});

		$("#codeNum").attr("src",
				"randomImageCode.html?pageId=userlogin&d=" + timenow);

	}
	/*ajax检验验证码*/
	$("#loginCode").bind("input propertychange", function() {
		var code = $(this).val();
		if(code.length==4){
			$.lionPost("chackLoginCode.html", {param:code}, function(data) {
				if(data.status =='y'){
					$("#loginCodeTs").css({"color":"red","background":"url('images/error.png') no-repeat"});
					$("#loginCodeTs").html("&nbsp;");
				} else {
					$("#loginCodeTs").css({"color":"green","background":"url('images/right.png') no-repeat"});
					$("#loginCodeTs").html("&nbsp;");
				}
			});
		}
	});
	
	//账号删除，清空密码
	$("#userName").bind('input propertychange', function() {
		if($(this).val().length<1){
			$("#pwd").val("");
		};
	});
//-->
</script>
</html>
