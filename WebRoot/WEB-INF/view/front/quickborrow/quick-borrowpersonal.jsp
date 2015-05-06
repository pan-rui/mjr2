<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>【快速借款】_P2P互联网金融理财平台！</title>
<meta name="keywords" content="P2P，深圳贷款，房产抵押贷款，公司贷款，互联网金融"/>
<meta name="description" content="富了吗？想富就上P2P互联网金融理财平台，100%本息保障。打造专业的小微金融服务体系，让更多的人收益！"/>
<link rel="stylesheet" href="css/common.css" type="text/css" />
<link rel="stylesheet" href="css/page.css" type="text/css" />
<link rel="stylesheet" href="css/validform.css" type="text/css"></link>
<style type="text/css">
a:HOVER{color:#fff;}
.qyzm{
	background: none repeat scroll 0 0 #f89c44;
    border-radius: 3px;
    color: #fff;
    display: block;
    float: left;
    font-family: "microsoft yahei";
    height: 36px;
    line-height: 36px;
    margin-left: 15px;
    padding: 0 12px;
    margin-top:2px;
    text-align: center;
    }
</style>
</head>

<body>
	<!--header start-->
	<jsp:include page="/WEB-INF/view/front/comm/header.jsp"></jsp:include>
	<!--header end-->


	<!--借款banner start-->
	<div class="loan_banner">
		<div class="loan_bimg"><img src="images/ad/loan_banner.gif" /></div>
	</div>
	<!--借款banner end-->
	<!--内容 start-->
	<div class="loan_cont">
		<div class="loan_tips">
			提示： 1、借款所在区域目前<b>仅限深圳</b>地区，后续将增加其他地区业务<br /> <span
				style="padding-left:45px;"></span>2、请仔细填写您的真实信息，我们将在1个工作日内与您取得联系。
		</div>
		<!--txt start-->
		<form action="inserttPersonBorrower.html" method="post" class="registForm">
			<div class="loan_main" style="display:block;">
				<div class="l_fbox">
					<div class="l_fortit">* 借款人姓名</div>
					<div class="l_fortxt">
						<input type="text" class="loan_input" id="borrowerName" name="borrowerName" placeholder="借款人真实姓名" datatype="s2-10" nullmsg="姓名不能为空"
								errormsg="请输入正确的真实姓名"/>
					</div>
					<div class="Validform_checktip"></div>
				</div>
				<div class="l_fbox">
					<div class="l_fortit">* 联系电话</div>
					<div class="l_fortxt">
						<input type="text" class="loan_input" id="cellPhone" name="borrowerCellPhone" placeholder="手机号码" datatype="m"
									nullmsg="手机号码不能为空" errormsg="请输入正确的11位手机号码" />
					</div>
					<div class="Validform_checktip"></div>
				</div>
				<div class="l_fbox">
		             <div class="l_fortit">* 手机验证</div>
		             <div class="l_fortxt"><input type="text" class="loan_input" name="qbCode" placeholder="验证码" errormsg="请输入正确的6位验证码" datatype="n6-6" style="width:100px;" /></div>
		             <div class="l_foryzm"><a href="javascript:sendVcode();" id="a_sendVcode" class="qyzm">获取验证码</a></div>
		             <div class="Validform_checktip"></div>
         		</div>
				<div class="l_fbox">
					<div class="l_fortit">* 借款金额</div>
					<div class="l_fortxt">
						<input type="text" onkeyup="this.value=this.value.replace(/\D/g,'')" datatype="n3-7" placeholder="借款金额" nullmsg="借款金额不能为空" errormsg="借款金额需为千至千万" class="loan_input" name="borrowAmount" />元
					</div>
					<div class="Validform_checktip"></div>
				</div>
				<div class="l_fbox">
					<div class="l_fortit">* 借款期限</div>
					<div class="l_fortxt">
						<select class="l_qxselect" name="borrowDealine" datatype="*" nullmsg="请选择借款期限">
							<option value="">--请选择--</option>
							<option value="1">1个月</option>
							<option value="2">2个月</option>
							<option value="3">3个月</option>
							<option value="4">4个月</option>
							<option value="5">5个月</option>
							<option value="6">6个月</option>
							<option value="7">7个月</option>
							<option value="8">8个月</option>
							<option value="9">9个月</option>
							<option value="10">10个月</option>
							<option value="11">11个月</option>
							<option value="12">12个月</option>
							<option value="13">1年以上</option>
						</select>
					</div>
					<div class="Validform_checktip"></div>
				</div>
				<div class="l_fbox">
					<div class="l_fortit">* 借款用途</div>
					<div class="l_fortxt">
						<input type="text" class="loan_input" name="borrowUse" placeholder="借款用途" datatype="s2-50" nullmsg="用途不能为空"
								errormsg="2-50位字符" />
					</div>
					<div class="Validform_checktip"></div>
				</div>
				<div class="l_fbox">
					<div class="l_fortit">* 所在区域</div>
					<div class="l_fortxt">
						深圳市 <select name="houseCity" class="l_addselect" datatype="*" nullmsg="请选择所在区域">
							<option value="">--请选择--</option>
							<option value="罗湖区">罗湖区</option>
							<option value="福田区">福田区</option>
							<option value="南山区">南山区</option>
							<option value="盐田区">盐田区</option>
							<option value="宝安区">宝安区</option>
							<option value="龙岗区">龙岗区</option>
							<option value="布吉">布吉</option>
							<option value="坪山新区">坪山新区</option>
							<option value="光明新区">光明新区</option>
							<option value="龙华新区">龙华新区</option>
							<option value="大鹏新区">大鹏新区</option>
							<option value="深圳周边">深圳周边</option>
						</select>
					</div>
					<div class="Validform_checktip"></div>
				</div>
				<div class="l_fbox">
					<div class="l_fortit">* 借款方式</div>
					<div class="l_fortxt">
						<div class="l_select">
							<span class="ls_sta tran" lang="1">房产</span> <span class="ls_sta" lang="2">车</span>
							<span class="ls_sta" lang ="3">信用</span> <input type="hidden"
								name="borrowType" id="houseStatus" value="" datatype="n1-10" nullmsg="请选择借款方式">
						</div>
					</div>
					<div class="Validform_checktip"></div>
				</div>
				<div class="loan_sub">
					<input type="submit" class="loan_submit" id="borrowSM" value="提交申请" />
				</div>
			</div>
		</form>
		<!--txt end-->

	</div>
	<!--内容 end-->
	<!--优势 start-->
	<div class="loan_newer">
		<div class="l_newcont clearfix">
			<!--产品特点 start-->
			<div class="l_ys l_ys1">
				<h2>产品特点</h2>
				<ul>
					<li><a href="#">* 无任何贷前费用，收费透明</a>
					</li>
					<li><a href="#">* 快速审批，最快当天可以获得资金支持</a>
					</li>
					<li><a href="#">* 还款便捷，服务渠道广泛</a>
					</li>
				</ul>
			</div>
			<!--产品特点 start-->
			<!--申请条件 start-->
			<div class="l_ys l_ys2">
				<h2>申请条件</h2>
				<ul>
					<li><a href="#">* 持有中国二代身份证及稳定收入证明</a>
					</li>
					<li><a href="#">* 无不良信贷记录</a>
					</li>
					<li><a href="#">* 有车、房等抵押物最佳</a>
					</li>
				</ul>
			</div>
			<!--申请条件 start-->
		</div>
	</div>
	<!--优势 end-->
	<!--foot start-->
	<jsp:include page="/WEB-INF/view/front/comm/footer.jsp"></jsp:include>
	<!--foot end-->
	<script type="text/javascript" src="js/Validform_v5.3.2_min.js"></script>
</body>
<script type="text/javascript">
<!--
	var ivr_phone = /^1[3|4|5|8][0-9]\d{8}$/;
	$(function(){
		$("#quickBoorw").addClass("nav_aclick");
	});
	$(".l_select .ls_sta").click(function() {
		$(this).addClass("ls_click").siblings(".ls_sta").removeClass("ls_click");
		$("#houseStatus").val($(this).attr("lang"));
	});
	var valiForm = $(".registForm").Validform({
		btnSubmit : "#borrowSM",
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
				layer.msg('您的借款申请已成功提交，我们相关工作人员会尽快于您取得联系。', 3, 1);
				window.location.href="index.html";
			} else {
				layer.msg(data.info, 3, 1);
			}
		}
	});
	$.Tipmsg.r = "";
	$.Tipmsg.tit="";
	
	//发送手机验证码
	function sendVcode() {
		$("#cellPhone").blur();
		var phone = $("#cellPhone").val(); //验证手机号码
		if (ivr_phone.test(phone)) {
			if ($("#a_sendVcode").html() == "获取验证码"
					|| $("#a_sendVcode").html() == "重新获取") {
				$("#a_sendVcode").html("发送中...");
				$.post("getQuickBorrowCode.html", {
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
//-->
</script>
</html>
