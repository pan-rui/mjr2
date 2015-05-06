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
	.xubox_msgico{top:68px !important;}
</style>
</head>

<body>
	<!--header start-->
	<jsp:include page="/WEB-INF/view/front/comm/header.jsp"></jsp:include>
	<!--header end-->

	<!--page start-->
	<div class="p_page20">
		<!--当前位置 start-->
		<div class="p1000 p_position">
			<i class="p_posico"></i><span class="p_postxt"><a href="#">首页</a>
				&gt; <a href="#">登陆</a> &gt; <a href="#">忘记密码</a> </span>
		</div>
		<!--当前位置 end-->
		<!--main start-->
		<div class="fw_page clearfix">
			<!--标 start-->
			<div class="l_pro clearfix">
				<span class="l_ptitle">忘记密码</span>
				<div class="line1"></div>
			</div>
			<!--标 end-->
			<!--内容 start-->
			<div class="fget_pass">
				<!--第一步骤 start-->
				<div class="fg_pcont fg_pcont1" id="saveTel" style="display:block;" >
					<p class="bz_step bz_step1"></p>
					<form action="reFormforGetPassCode.html" class="registerform" method="post">
						<div class="fg_pc_txt">
							<ul>
								<li>
									<div class="fpas_t_tit">手机号码：</div>
									<div class="fpas_t_txt">
										<input id="uphone" type="text" maxlength="11"
											class="fpas_text" placeholder="注册手机号码" datatype="m"
											nullmsg="手机号码不能为空" errormsg="请输入绑定手机号码"
											ajaxurl="forGetPassPhone.html">
									</div>
									<div class="fpas_t_trip" id="telTs"></div></li>
								<li>
									<div class="fpas_t_tit">验证码：</div>
									<div class="fpas_t_txt">
										<input id="uphone" type="text" name="telCode" maxlength="6"
											placeholder="验证码" datatype="n6-6" class="fpas_text"
											style="width:90px">
									</div>
									<div class="fpas_btns">
										<div class="btns">
											<a hidefocus="true" class="btn"
												href="javascript:sendVcode();" id="a_sendVcode">免费获取验证码</a>
										</div>
									</div>
								</li>
								<li>
									<div class="fpas_t_tit"></div>
									<div class="fpas_t_txt">
										<input type="button" id="TelSM" class="fpas_snext" value="下一步">
									</div></li>
							</ul>
						</div>
					</form>
				</div>
				<!--第一步骤 end-->
				<!--第三步骤 start-->
				<div class="fg_pcont fg_pcont2" id="newPass" >
					<p class="bz_step bz_step2"></p>
					<form action="updateforGetPass.html" class="registerform" method="post">
						<div class="fg_pc_txt">
							<ul>
								<li>
									<div class="fpas_t_tit">新密码：</div>
									<div class="fpas_t_txt">
										<input type="hidden" id="userId" name="userId">
										<input type="password" errormsg="密码至少6个字符,最多16个字符！"
											nullmsg="请填写密码" datatype="*6-16" plugin="passwordStrength"
											name="forGetpassword" class="fpas_text" >
									</div>
									<div class="fpas_t_trip"  id="newPssTs"></div>
								</li>
								<li>
									<div class="fpas_t_tit">确认新密码：</div>
									<div class="fpas_t_txt">
										<input type="password" name="password2" datatype="*6-16"
											recheck="forGetpassword" nullmsg="请确认密码！" errormsg="2次密码不一致！"
											class="fpas_text">
									</div>
								</li>
								<li>
									<div class="fpas_t_tit"></div>
									<div class="fpas_t_txt">
										<!--确认密码完成通过后，button加一个class:fpas_finish,点击它触发弹出窗修改完成ok-->
										<input type="button" id="newPassSM"
											class="fpas_snext fpas_finish" value="完成">
									</div></li>
							</ul>
						</div>
					</form>
				</div>
				<!--第三步骤 end-->
			</div>
			<!--内容 end-->
		</div>
		<!--main end-->
	</div>
	<!--page end-->

	<!--header start-->
	<jsp:include page="/WEB-INF/view/front/comm/footer.jsp"></jsp:include>
	<!--header end-->

	<script type="text/javascript" src="js/Validform_v5.3.2_min.js"></script>
	<script type="text/javascript" src="js/passwordStrength-min.js"></script>
	<script type="text/javascript" src="js/layer.min.js"></script>

</body>
<script type="text/javascript">
<!--
	var ivr_phone = /^1[3|4|5|8][0-9]\d{8}$/;

	var valiForm = $(".registerform:eq(0)").Validform({
		btnSubmit : "#TelSM",
		tiptype : function(msg, o, cssctl) {
			var objtip = $("#telTs");
			cssctl(objtip, o.type);
			objtip.text(msg);
		},
		postonce : true,
		ajaxPost : true,
		callback : function(data) {
			if (data.status == 'y') {
				$("#saveTel").hide();
				$("#newPass").show();
				
			} else {
				alert(data.info);
			}
		}
	});
	var valiForm2 = $(".registerform:eq(1)").Validform({
		btnSubmit : "#newPassSM",
		tiptype : function(msg, o, cssctl) {
			var objtip = $("#newPssTs");
			cssctl(objtip, o.type);
			objtip.html(msg);
		},
		postonce : true,
		ajaxPost : true,
		callback : function(data) {
			if (data.status == 'y') {
				var passSuccess = $.layer({
					//shade: [0],
					area : [ '350', '150' ],
					border : [ 0, 0.5, '#666' ],
					dialog : {
						msg : '重置密码成功',
						btns : 2,
						type : 1,
						time : 3,
						btn : [ '我要登陆', '关闭' ],
						yes : function() {
							window.location.href = "loginIndex.html";
						},
						no : function() {
							layer.close(passSuccess);
						}
					}
				});
			} else {
				layer.msg(data.info);
			}
		}
	});

	$.Tipmsg.r = "";

	//发送手机验证码
	function sendVcode() {
		$("#uphone").blur();
		var phone = $("#uphone").val(); //验证手机号码
		if (ivr_phone.test(phone)) {
			if ($("#a_sendVcode").html() == "免费获取验证码"
					|| $("#a_sendVcode").html() == "重新获取") {
				$("#a_sendVcode").html("发送中...");
				$.post("forGetPassCode.html", {
					"cellPhone" : phone
				}, function(data) {
					$("#phoneTs").html(data.info);
					if (data.status == 'y') {
						timers = 120;
						tipId = window.setInterval(timer, 1000);
					} else if (data.value != null) {
						$("#a_sendVcode").html(data.value + "秒后重新获取");
						timers = data.value;
						tipId = window.setInterval(timer, 1000);
					} else if (data.status == 'n' && data.value == null) {
						alert(data.info);
						$("#a_sendVcode").html("无法获取");
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
//-->
</script>
</html>
