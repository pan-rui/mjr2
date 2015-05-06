<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>P2P - 用户注册</title>
<link rel="stylesheet" href="css/common.css" type="text/css" />
<link rel="stylesheet" href="css/page.css" type="text/css" />
<link rel="stylesheet" href="css/validform.css" type="text/css"></link>
<link rel="stylesheet" href="css/demo.css" type="text/css"></link>
<link type="text/css" rel="stylesheet"
	href="js/plugins/jBox/Skins2/Yellow/jbox.css" />
<style type="text/css">
	.Validform_checktip{height: 44px; line-height: 44px;};
</style>
</head>

<body>
	<!--header start-->
	<jsp:include page="/WEB-INF/view/front/comm/header.jsp"></jsp:include>
	<!--header end-->

	<!--reg start-->
	<div class="p_gray">
		<div class="p1000">
			<div class="r_banner">
				<a href="#"><img src="images/member/r_banner.gif" /> </a>
			</div>
			<form action="reg.html" method="post" class="registForm"
				enctype="multipart/form-data">
				<div class="reg_cont">
					<!--步骤 start-->
					<div class="reg_state reg_state1">
						<span class="s_reg s_reg1">填写注册信息</span> <span
							class="s_reg s_reg2">实名认证</span> <span class="s_reg s_reg3">绑定托管账户</span>
						<span class="s_reg s_reg4">成功</span>
					</div>
					<!--步骤 end-->
					<div class="reg_main">

						<div class="reg_box">
							<div class="r_btit">
								<span class="r_red">*</span> 用户名：
							</div>
							<div class="r_binput">
								<input type="text" class="r_text" name="userName" id="userName"
									placeholder="用户名" datatype="userName" nullmsg="用户名不能为空"
									errormsg="用户名的格式为4-16位的字母，数字的组合" ajaxurl="veriUserName.html" />
								<i class="r_ico r_ico1"></i>
							</div>
							<div class="Validform_checktip"></div>
						</div>

						<!--手机号 start-->
						<div class="reg_box">
							<div class="r_btit">
								<span class="r_red">*</span> 手机号码：
							</div>
							<div class="r_binput">
								<input type="text" class="r_text" name="cellPhone"
									id="cellPhone" placeholder="手机号码" datatype="m"
									nullmsg="手机号码不能为空" errormsg="请输入正确的11位手机号码"
									ajaxurl="verificationNewUserPhone.html" /> <i
									class="r_ico r_ico1"></i>
							</div>
							<div class="Validform_checktip"></div>
						</div>
						<!--手机号 start-->
						<!--手机号 start-->
						<div class="reg_box">
							<div class="r_btit">
								<span class="r_red">*</span> 验证码：
							</div>
							<div class="r_binput r_yinput">
								<input type="text" class="r_text r_yzm" name="regCode"
									placeholder="验证码" nullmsg="验证码不能为空" errormsg="请输入正确的6位验证码" datatype="n6-6" /> <i class="r_ico r_ico2"></i>
							</div>
							<!--短信验证码代码写在這-->
							<div class="btns">
								<a id="a_sendVcode" href="javascript:sendVcode();" class="btn"
									hidefocus="true">免费获取验证码</a> <span class="txt-succ"
									style="display:none;">&nbsp;&nbsp;验证码已发送</span>
							</div>
							<div class="Validform_checktip"></div>
						</div>
						<!--手机号 start-->
						<!--密码 start-->
						<div class="reg_box" style="padding-bottom: 20px;">
							<div class="r_btit">
								<span class="r_red">*</span> 密码：
							</div>
							<div class="r_binput">
								<input type="password" class="r_text"
									errormsg="密码至少6个字符,最多16个字符！" nullmsg="请填写密码" datatype="*6-16"
									plugin="passwordStrength" name="password" value="" /> <i
									class="r_ico r_ico3"></i>
								<div class="passwordStrength">
									密码强度： <span class="">弱</span> <span>中</span> <span class="last">强</span>
								</div>
							</div>
							<div class="Validform_checktip"></div>
						</div>
						<!--密码 start-->
						<!--确认密码 start-->
						<div class="reg_box">
							<div class="r_btit">
								<span class="r_red">*</span> 确认密码：
							</div>
							<div class="r_binput">
								<input type="password" class="r_text" name="password2"
									datatype="*6-16" recheck="password" nullmsg="请确认密码！"
									errormsg="2次密码不一致！" /> <i class="r_ico r_ico3"></i>
							</div>
							<div class="Validform_checktip"></div>
						</div>
						<!--确认密码 start-->
						<!--推荐人 start-->
						<div class="reg_box">
							<div class="r_btit">推荐人：</div>
							<div class="r_binput">
								<input type="text" class="r_text" name="refereeUser"
									datatype="m" ignore="ignore" nullmsg="请输入推荐手机号！"
									errormsg="推荐编号只能为手机号" value="${tel}" /> <i
									class="r_ico r_ico5"></i>
							</div>
							<div class="Validform_checktip"></div>
						</div>
						<!--推荐人 start-->
						<!--协议 start-->
						<div class="reg_hetong">
							<div class="ht_inp tran"></div>
							<div class="ht_txt">
								我已阅读并同意 <a href="javascript:void(0);" class="ht_txta">《网站服务协议》</a>
								<input type="hidden" id="reg_Hetong" datatype="s1-5" nullmsg="请勾选网站协议">
							</div>
							<div class="Validform_checktip"></div>
						</div>
						<!--协议 end-->
						<!--but start-->
						<div class="reg_but">
							<input type="button" id="btnSave" value="立即注册" class="reg_submit" />
						</div>
						<!--but end-->
					</div>
				</div>
			</form>
		</div>
	</div>
	<!--reg end-->


	<!--header start-->
	<jsp:include page="/WEB-INF/view/front/comm/footer.jsp"></jsp:include>
	<!--header end-->
	<script type="text/javascript" src="js/page.js"></script>
	<script type="text/javascript" src="js/Validform_v5.3.2_min.js"></script>
	<script type="text/javascript" src="js/Validform-Datatype-lion.js"></script>
	<script type="text/javascript" src="js/passwordStrength-min.js"></script>
	<script type="text/javascript" src="js/layer.min.js"></script>

</body>
<script type="text/javascript">
<!--
	$(function (){
		$("#reg_Hetong").val("");
	});
	var ivr_phone = /^1[3|4|5|8][0-9]\d{8}$/;
	
	var valiForm = $(".registForm").Validform({
		btnSubmit : "#btnSave",
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
		usePlugin:{
			passwordstrength:{
			minLen:6,
			maxLen:18
			}
		},	
		datatype:{
		    "userName":function(gets,obj,curform,regxp){
		        //参数gets是获取到的表单元素值，
		        //obj为当前表单元素，
		        //curform为当前验证的表单，
		        //regxp为内置的一些正则表达式的引用。
		    	
		        var reg=/^[a-zA-Z0-9_]{4,16}$/;
		        if(reg.test(gets)==false){
		        	return "用户名的格式为4-16位的字母，数字的组合";
		        }
		      
		        return true;
		        //return false表示验证出错，没有return或者return true表示验证通过。
		    }
		},
		postonce : true,
		ajaxPost : true,
		callback : function(data) {
			if (data.status == 'y') {
				window.location.href="regPersonInfo.html";
			}else{
				alert(data.info);
			}
		}
	});

	$.Tipmsg.r = "";
	//发送手机验证码
	function sendVcode() {
		$("#cellPhone").blur();
		var phone = $("#cellPhone").val(); //验证手机号码
		if (ivr_phone.test(phone)) {
			if ($("#a_sendVcode").html() == "免费获取验证码"
					|| $("#a_sendVcode").html() == "重新获取") {
				$("#a_sendVcode").html("发送中...");
				$.post("getPhoneCode.html", {
					"cellPhone" : phone
				}, function(data) {
					$("#phoneTs").html(data.info);
					if (data.status == 'y') {
						timers = 120;
						tipId = window.setInterval(timer, 1000);
					} else if(data.value != null) {
						$("#a_sendVcode").html(data.value+"秒后重新获取");
						timers = data.value;
						tipId = window.setInterval(timer, 1000);
					}else if(data.status =='n' && data.value==null){
						alert(data.info);
						$("#a_sendVcode").html("重新获取");
					}
				});
			} else {
				return;
			}
		} else {
			return;
		}
	}

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
	 //注册服务协议 复选框
	   $(".reg_hetong .ht_inp").toggle( 
			function () { 
				$(this).addClass("ht_tick"); 
				//$(this).children("input").removeAttr("checked"); 
				$("#reg_Hetong").val("ok");
			} ,
			function () { 
				$(this).removeClass("ht_tick");
				//$(this).children("input").attr("checked", "checked");
				$("#reg_Hetong").val("");
			}
	   ); 
	//网站服务协议
	$('.ht_txt .ht_txta').on('click', function(){
		  xy_box = $.layer({
				type: 1,
				title: '网站服务协议',
				//shadeClose: true, 开启点击遮罩关闭层
				area: ['880px','650px'],
				border : [0, 0.5, '#666'],
				offset: [($(window).height() - 650)/2+'px', ''],
				page: {url : 'showWebService.html'}
			});
		
	});
	
	function closeWebService(){
		layer.close(xy_box);
	}
//-->
</script>
</html>
