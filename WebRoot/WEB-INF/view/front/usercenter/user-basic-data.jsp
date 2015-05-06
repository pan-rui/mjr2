<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="${path }">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>P2P - 个人资料</title>
<link rel="stylesheet" href="css/common.css" type="text/css" />
<link rel="stylesheet" href="css/page.css" type="text/css" />
<link rel="stylesheet" href="css/validform.css" type="text/css"></link>
<link rel="stylesheet" href="css/demo.css" type="text/css"></link>
<style type="text/css">
	.xubox_msgico{top:68px !important;}
</style>
</head>

<body>
	<!--header start-->
	<jsp:include page="/WEB-INF/view/front/comm/header.jsp"></jsp:include>
	<!--header end-->

	<!--page start-->
	<div class="p_page20">
		<!--用户中心 start-->
		<div class="user clearfix">
			<!--侧边 start-->
			<jsp:include page="/WEB-INF/view/front/comm/user-left.jsp"></jsp:include>
			<!--侧边 end-->
			<!--main start-->
			<div class="u_main">
				<!--title start-->
				<div class="u_tit">
					<span class="u_titwz">基本信息</span>
					<div class="line1"></div>
				</div>
				<!--title end-->
				<!--账户 start-->
				<div class="u_txt">
					<!--基本信息 start-->
					<div class="information">
						<div class="inf_box">
							<span class="inf_btit">手机号码</span>
							<div class="inf_btxt">
								<input type="text" class="i_btxt" disabled="disabled"
									value="<my:replaceStar len="4"  content="${cuser.cellPhone }"  start='4'/>" />
							</div>
							<span class="inf_modify"><a href="javascript:void(0);"
								class="tel_modify">修改</a> </span>
						</div>
						<div class="inf_box">
							<span class="inf_btit">登录密码</span>
							<div class="inf_btxt">
								<input type="text" class="i_btxt" disabled="disabled"
									value="******" />
							</div>
							<span class="inf_modify"><a href="javascript:void(0);"
								class="pass_modify">修改</a> </span>
						</div>
						<%--<div class="inf_box">
							<span class="inf_btit">邮箱</span>
							<div class="inf_btxt">
								<input type="text" class="i_btxt" disabled="disabled"
									value="${cuser.email }" />
							</div>
							<span class="inf_modify"><a href="javascript:void(0);"
								class="email_modify">修改</a> </span>
						</div>
					--%></div>
					<!--基本信息 end-->
					<!--title start-->
					<div class="u_tit">
						<span class="u_titwz">实名认证</span>
						<div class="line1"></div>
					</div>
					<!--title end-->
					<div class="shiming">
						<c:choose>
							<c:when test="${cuserp !=null}">
								<div class="inf_box">
									<span class="inf_btit">真实姓名</span>
									<div class="inf_btxt">
										<input type="text" disabled="disabled"
											value="<my:replaceStar len="2"  content="${cuserp.realName }"  start='2'/>"
											class="i_btxt" />
									</div>
								</div>
								<div class="inf_box">
									<span class="inf_btit">身份证</span>
									<div class="inf_btxt">
										<input type="text" disabled="disabled"
											value="<my:replaceStar len="10"  content="${cuserp.cardNo }"  start='5'/>"
											class="i_btxt" />
									</div>
								</div>
							</c:when>
							<c:otherwise>
								<div class="inf_box">
									<span class="inf_btit">真实姓名</span>
									<div class="inf_btxt">
										<input type="text" disabled="disabled" value="未实名，请验证"
											class="i_btxt" />
									</div>
								</div>
								<div class="inf_box">
									<span class="inf_btit">身份证</span>
									<div class="inf_btxt">
										<input type="text" disabled="disabled" value="" class="i_btxt" />
									</div>
									<a href="regPersonInfo.html" class="inf_yz">去验证</a>
								</div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<!--账户 end-->
			</div>
		</div>
		<!--用户中心 end-->
	</div>
	<!--page end-->


	<!--header start-->
	<jsp:include page="/WEB-INF/view/front/comm/footer.jsp"></jsp:include>
	<!--header end-->
	<!--修改手机号码 start-->
	<div class="u_telcont">
		<!--第一步start-->
		<div class="mod_tel mod_tel1" id="updateTel1" style="display:block;">
			<p class="mt_state mt_state1"></p>
			<form action="center/updateUserPhone2.html" method="post"
				id="oldTelregistForm">
				<div class="mt_scont">
					<div class="mt_sbox">
						<div class="mt_btit">原手机号码</div>
						<div class="mt_btxt">
							<input type="text" id="oldTel" class="mt_binput"
								placeholder="手机号码" datatype="m" nullmsg="手机号码不能为空"
								errormsg="请输入绑定手机号码" ajaxurl="center/updateUserPhone1.html" />
						</div>
						<div class="Validform_checktip"></div>
					</div>
					<div class="mt_sbox">
						<div class="mt_btit">验证码</div>
						<div class="mt_btxt">
							<input type="text" id="telCode" name="telCode" placeholder="验证码"
								datatype="n6-6" class="mt_binput mt_byzm" />
						</div>
						<div class="btns">
							<a id="a_sendVcode" href="javascript:sendVcode();" class="btn"
								hidefocus="true">获取验证码</a> <span class="txt-succ"
								style="display:none;">&nbsp;&nbsp;验证码已发送</span>
						</div>
					</div>
					<div class="mt_submit">
						<input type="button" id="smtTel1" value="下一步" class="tel_sub" />
					</div>
				</div>
			</form>
		</div>
		<!--第一步end-->
		<!--第二步start-->
		<div class="mod_tel mod_tel2" id="updateTel2">
			<p class="mt_state mt_state2"></p>
			<form action="center/updateUserPhone3.html" method="post"
				id="newTelregistForm">
				<div class="mt_scont">
					<div class="mt_sbox">
						<div class="mt_btit">新手机号码</div>
						<div class="mt_btxt">
							<input type="text" id="newTel" maxlength="11"
								onkeyup="this.value=this.value.replace(/\D/g,'')"
								placeholder="手机号码" class="mt_binput" />
						</div>
						<div class="newPhoneTs"></div>
					</div>
					<div class="mt_sbox">
						<div class="mt_btit">验证码</div>
						<div class="mt_btxt">
							<input type="text" class="mt_binput mt_byzm" id="newTelCode"
								maxlength="6" onkeyup="this.value=this.value.replace(/\D/g,'')" />
						</div>
						<div class="btns">
							<a id="newTelVcode" href="javascript:sendVcodeNewTel();"
								class="btn" hidefocus="true">获取验证码</a>
						</div>
					</div>
					<div class="mt_submit">
						<input type="button" id="smtTel2" value="下一步" class="tel_sub" />
					</div>
				</div>
			</form>
		</div>
		<!--第二步end-->
		<!--第三步start-->
		<div class="mod_tel mod_tel3" id="updateTel3">
			<p class="mt_state mt_state3"></p>
			<div class="mt_scont">
				<p class="mt_stxt">
					<b class="uok_ico"></b>恭喜您，您已经成功绑定新的手机： <span id="successNewTel"
						class="uok_txt"></span>
				</p>
				<div class="uok_but">
					<input type="button" value="关 闭" class="uok_close">
				</div>
			</div>
		</div>
		<!--第三步end-->
	</div>
	<!--修改手机号码 end-->
	<!--修改登录密码 start-->
	<div class="u_passcont">
		<div class="pas_box pas_box1">
			<div class="pas_btit">原密码</div>
			<div class="pas_btxt">
				<input type="password" id="oldPass" class="pas_binput" /> <span
					id="oldPassTs"></span>
			</div>
		</div>
		<div class="pas_box">
			<div class="pas_btit">新密码</div>
			<div class="pas_btxt">
				<input type="password" id="newPass" class="pas_binput" /> <span
					id="newPassTs"></span>
			</div>
		</div>
		<div class="pas_box">
			<div class="pas_btit">确定新密码</div>
			<div class="pas_btxt">
				<input type="password" id="reNewPass" class="pas_binput" /> <span
					id="reNewPassTs"></span>
			</div>
		</div>
		<div class="pas_submit">
			<input type="button" value="立即修改" id="updatePassSM" class="uok_mod"><input
				type="button" value="关 闭" class="uok_close">
		</div>
	</div>
	<!--修改登录密码 end-->
	<!--修改邮箱 start-->
	<div class="u_emailcont">
		<c:choose>
			<c:when test="${cuser.email !=null}">
				<!--修改 start-->
				<div class="email_mod">
					<div class="pas_box pas_box1">
						<div class="pas_btit">旧邮箱</div>
						<div class="pas_btxt">
							<input type="text" disabled="disabled" value="${cuser.email}" class="pas_binput" />
						</div>
					</div>
					<div class="pas_box">
						<div class="pas_btit">新邮箱</div>
						<div class="pas_btxt">
							<input type="text" id="newEmil1" onblur="chackEmil(this,1)" class="pas_binput" />
							<span id="emilTs1" ></span>
						</div>
					</div>
					<div class="pas_submit">
						<input type="button" value="立即修改" onclick="smEmil(1)" class="uok_mod" /><input
							type="button" value="关 闭" class="uok_close" />
					</div>
				</div>
				<!--修改 end-->
			</c:when>
			<c:otherwise>
				<!--添加 start-->
				<div class="email_add">
					<div class="pas_box pas_box1">
						<div class="pas_btit">邮箱</div>
						<div class="pas_btxt">
							<input type="text" id="newEmil2" onblur="chackEmil(this,2)" class="pas_binput" /><span id="emilTs2" ></span>
						</div>
					</div>
					<div class="pas_submit">
						<input type="button" onclick="smEmil(2)" value="去验证" class="uok_mod" />
					</div>
				</div>
				<!--增加 end-->
			</c:otherwise>
		</c:choose>

	</div>
	<!--修改邮箱 end-->
	<script type="text/javascript" src="js/Validform_v5.3.2_min.js"></script>
	<script type="text/javascript" src="js/passwordStrength-min.js"></script>
</body>
<script type="text/javascript">
var ivr_phone = /^1[3|4|5|8][0-9]\d{8}$/;//验证手机号码
var updatePhone;
var updatePass ;
	$(function() {
		$("#myCenter").addClass("nav_aclick");
		$("#center6").addClass("nav_aclik");
	});
	
	var valiForm = $("#oldTelregistForm").Validform({
		btnSubmit : "#smtTel1",
		tiptype : function(msg,o,cssctl){
			//msg：提示信息;
			//o:{obj:*,type:*,curform:*}, obj指向的是当前验证的表单元素（或表单对象），type指示提示的状态，值为1、2、3、4， 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, curform为当前form对象;
			//cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和 当前提示的状态（既形参o中的type）;
			if(!o.obj.is("form")){//验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
				var objtip=o.obj.parent().siblings(".Validform_checktip");
				cssctl(objtip,o.type);
				objtip.text(msg);
			}
		},
		postonce : true,
		ajaxPost : true,
		callback : function(data) {
			if (data.status == 'y') {
				$(".mod_tel").hide();
				$("#updateTel2").show();
			} else {
				alert(data.info);
			}
		}
	});

	$.Tipmsg.r = "";

	//发送手机验证码-解绑原手机
	function sendVcode() {
		$("#oldTel").blur();
		var phone = $("#oldTel").val(); //验证手机号码
		if ($("#a_sendVcode").html() == "获取验证码"
				|| $("#a_sendVcode").html() == "重新获取") {
			$("#a_sendVcode").html("发送中...");
			$.post("updateTelGetCode.html", {
				"cellPhone" : phone
			}, function(data) {
				if (data.status == 'y') {
					timers = 120;
					tipId = window.setInterval(timer, 1000);
				} else if (data.value != null) {
					$("#a_sendVcode").html(data.value + "秒后重新获取");
					timers = data.value;
					tipId = window.setInterval(timer, 1000);
				} else if (data.status == 'n' && data.value == null) {
					alert(data.info);
					$("#a_sendVcode").html("重新获取");
				}
			});
		} else {
			return;
		}
	}
	//发送手机验证码-绑定新手机
	function sendVcodeNewTel() {
		$("#newTel").blur();
		var phone = $("#newTel").val(); //验证手机号码
		if ($("#newTelVcode").html() == "获取验证码"
				|| $("#newTelVcode").html() == "重新获取") {
			$("#newTelVcode").html("发送中...");
			$.post("updateTelNewCode.html", {
				"cellPhone" : phone
			}, function(data) {
				if (data.status == 'y') {
					timers2 = 120;
					tipNewId = window.setInterval(timer2, 1000);
				} else if (data.value != null) {
					$("#newTelVcode").html(data.value + "秒后重新获取");
					timers2 = data.value;
					tipNewId = window.setInterval(timer2, 1000);
				} else if (data.status == 'n' && data.value == null) {
					alert(data.info);
					$("#newTelVcode").html("重新获取");
				}
			});
		} else {
			return;
		}
	}
	
	$("#newTel").blur(function () {
	    phone = this.value;
	    RegCellPhone = /^(1)([0-9]{10})?$/;
	    falg=phone.search(RegCellPhone);
	    if (falg==-1){
	    	$("#newPhoneTs").html("手机号码输入有误");
	    	this.focus();
	    }
	});
	
	$("#smtTel2").click(function(){
		var ntel = $("#newTel").val();
		var code = $("#newTelCode").val();
		$.lionPost("center/updateUserPhone3.html", {newPhone:ntel,newCode:code}, function(data) {
			if(data.status =='y'){
				$(".mod_tel").hide();
				$("#successNewTel").html(ntel);
				$("#updateTel3").show();
			} else {
				alert(data.info);
			}
		});
	});
	 //自设关闭
	$('.uok_close').on('click', function(){
		window.location.href = "center/userData.html";
		layer.close(updatePhone);
	});
	function timer() {
		if (timers >= 0) {
			$("#a_sendVcode").attr("disabled", true);
			$("#a_sendVcode").html("重新获取：" + timers + "秒");
			timers--;
		} else {
			window.clearInterval(tipId);
			$("#a_sendVcode").html("重新获取");
			$("#a_sendVcode").removeAttr("disabled");
		}
	}
	
	function timer2() {
		if (timers2 >= 0) {
			$("#newTelVcode").attr("disabled", true);
			$("#newTelVcode").html("重新获取：" + timers2 + "秒");
			timers2--;
		} else {
			window.clearInterval(tipNewId);
			$("#newTelVcode").html("重新获取");
			$("#newTelVcode").removeAttr("disabled");
		}
	}
	
	var isOldPass = false;
	var isNewPass = false;
	var isReNewPass = false;
	//修改密码 ：;错误提示
	$("#oldPass").blur(function(){
		var newpass = $(this).val();
		if(newpass.length<6 ||newpass.length>16){
			$("#oldPassTs").css({"color":"red","background":"url('images/error.png') no-repeat scroll left center rgba(0, 0, 0, 0)"});
			$("#oldPassTs").html("&nbsp;&nbsp;&nbsp;&nbsp;新密码输入有误");
			isOldPass = false;
			return false;
		}else{
			$("#oldPassTs").css({"color":"green","background":"url('images/right.png') no-repeat scroll left center rgba(0, 0, 0, 0)"});
			$("#oldPassTs").html("&nbsp;&nbsp;&nbsp;&nbsp;ok");
			isOldPass = true;
			return true;
		}
	});
	$("#newPass").blur(function(){
		var newpass = $(this).val();
		var renewpass = $("#reNewPass").val();
		if(newpass.length<6 ||newpass.length>16){
			$("#newPassTs").css({"color":"red","background":"url('images/error.png') no-repeat scroll left center rgba(0, 0, 0, 0)"});
			$("#newPassTs").html("&nbsp;&nbsp;&nbsp;&nbsp;密码输入有误");
			isNewPass = false;
			return false;
		}
		if(renewpass!=""){
			if(newpass!=renewpass){
				$("#reNewPassTs").css({"color":"red","background":"url('images/error.png') no-repeat scroll left center rgba(0, 0, 0, 0)"});
				$("#reNewPassTs").html("&nbsp;&nbsp;&nbsp;&nbsp;重复密码输入有误");
				var isReNewPass = false;
			}
		}
		$("#newPassTs").css({"color":"green","background":"url('images/right.png') no-repeat scroll left center rgba(0, 0, 0, 0)"});
		$("#newPassTs").html("&nbsp;&nbsp;&nbsp;&nbsp;ok");
		isNewPass = true;
		return true;
	});
	$("#reNewPass").blur(function(){
		var newpass = $("#newPass").val();
		var renewpass = $(this).val();
		if(renewpass.length<6 ||renewpass.length>16){
			$("#reNewPassTs").css({"color":"red","background":"url('images/error.png') no-repeat scroll left center rgba(0, 0, 0, 0)"});
			$("#reNewPassTs").html("&nbsp;&nbsp;&nbsp;&nbsp;重复密码输入有误");
			isReNewPass = false;
			return false;
		}
		if(newpass!=renewpass){
			$("#reNewPassTs").css({"color":"red","background":"url('images/error.png') no-repeat scroll left center rgba(0, 0, 0, 0)"});
			$("#reNewPassTs").html("&nbsp;&nbsp;&nbsp;&nbsp;重复密码输入有误");
			isReNewPass = false;
			return false;
		}
		$("#reNewPassTs").css({"color":"green","background":"url('images/right.png') no-repeat scroll left center rgba(0, 0, 0, 0)"});
		$("#reNewPassTs").html("&nbsp;&nbsp;&nbsp;&nbsp;ok");
		isReNewPass = true;
		return true;
	});
	
	function chackPassTrue(){
		if(isOldPass==true && isNewPass==true && isReNewPass==true ){
			return true;
		}else{
			return false;
		}
	}
	/*修改密码*/
	$("#updatePassSM").click(function(){
		$("#oldPass").blur();
		$("#newPass").blur();
		$("#reNewPass").blur();
		var oldpass = $("#oldPass").val();
		var newPass = $("#newPass").val();
		if(chackPassTrue()){
			$.lionPost("center/updateUserPass.html", {OldPass:oldpass,NewPass:newPass}, function(data) {
				if(data.status =='y'){
					 var passSuccess = $.layer({
						    //shade: [0],
						    area: ['350','150'],
						    border : [ 0, 0.5, '#666' ],
						    dialog: {
						        msg: '修改密码成功',
						        btns: 2,                    
						        type: 1,
						        time:3,
						        btn: ['重新登录','关闭'],
						        yes: function(){
						            window.location.href="loginOut.html";
						        }, no: function(){
						        	layer.close(passSuccess);
						        	layer.close(updatePass);
						        }
						    }
						});
				} else {
					alert(data.info);
				}
			});
		}
	});
	
	
	//验证
	var isemail=/^\w+([-\.]\w+)*@\w+([\.-]\w+)*\.\w{2,4}$/;
	var isNewEmil = false;
	function chackEmil(this_,nindex){
		var a=$(this_).val();
		var ts = $(("#emilTs"+nindex));
  		if(!isemail.test(a)){
  			ts.css({"color":"red","background":"url('images/error.png') no-repeat"});
  			ts.html("&nbsp;&nbsp;&nbsp;&nbsp;您输入的邮箱格式不对");
			return false;
  		}else if(a.length>25){
  			ts.css({"color":"red","background":"url('images/error.png') no-repeat scroll left center rgba(0, 0, 0, 0)"});
  			ts.html("&nbsp;&nbsp;&nbsp;&nbsp;您输入的邮箱格式不对");
			return false;
  		}else if(a==""){
  			ts.css({"color":"red","background":"url('images/error.png') no-repeat scroll left center rgba(0, 0, 0, 0)"});
  			ts.html("&nbsp;&nbsp;&nbsp;&nbsp;请输入您的邮箱");
			return false;
  		}
  		ts.css({"color":"green","background":"url('images/right.png') no-repeat scroll left center rgba(0, 0, 0, 0)"});
  		ts.html("&nbsp;&nbsp;&nbsp;&nbsp;ok");
  		isNewEmil = true;
		return true;
	}
	
	function smEmil(smindex){
		var emil = $("#newEmil"+smindex);
		emil.blur();
		if(isNewEmil){
			$.lionPost("center/updateUserEmil.html", {newEmil:emil.val()}, function(data) {
				if(data.status =='y'){
					alert("已成功发送验证邮件到您的邮箱，请及时验证。");
				} else {
					alert(data.info);
				}
			});
		}
	}
	
	//修改手机号码
	$('.tel_modify').on('click', function() {
		updatePhone = $.layer({
			type : 1,
			title : '修改手机号码',
			shadeClose : true, //开启点击遮罩关闭层
			area : [ '600px', '390px' ],
			border : [ 0, 0.5, '#666' ],
			offset : [ ($(window).height() - 390) / 2 + 'px', '' ],
			page : {
				dom : '.u_telcont'
			}
		});
	});
	//修改登录密码
	$('.pass_modify').on('click', function() {
		updatePass = $.layer({
			type : 1,
			title : '修改登录密码',
			shadeClose : true, //开启点击遮罩关闭层
			area : [ '600px', '390px' ],
			border : [ 0, 0.5, '#666' ],
			offset : [ ($(window).height() - 390) / 2 + 'px', '' ],
			page : {
				dom : '.u_passcont'
			}
		});
	});
	//修改邮箱
	$('.email_modify').on('click', function() {
		$.layer({
			type : 1,
			title : '修改邮箱',
			shadeClose : true, //开启点击遮罩关闭层
			area : [ '560px', '310px' ],
			border : [ 0, 0.5, '#666' ],
			offset : [ ($(window).height() - 310) / 2 + 'px', '' ],
			page : {
				dom : '.u_emailcont'
			}
		});
	});
	//询问框：
</script>
</html>
