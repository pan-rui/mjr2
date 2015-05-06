<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>P2P -实名认证</title>
<meta name="keywords" content="P2P网贷，互联网金融，P2P理财，P2P"/>
<meta name="description" content="P2P为深圳市P2P金融信息服务有限公司线上理财平台，致力于助力小微企业发展的同时普惠大众理财的互联网金融新模式！"/>
<link rel="stylesheet" href="css/common.css" type="text/css" />
<link rel="stylesheet" href="css/page.css" type="text/css" />
<link rel="stylesheet" href="css/validform.css" type="text/css"></link>
<link rel="stylesheet" href="css/demo.css" type="text/css"></link>
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
			<form action="regPerson.html" method="post" class="registForm" id="registForm"
				enctype="multipart/form-data">
				<div class="reg_cont">
					<!--步骤 start-->
					<div class="reg_state reg_state2">
						<span class="s_reg s_reg1">填写注册信息</span> <span
							class="s_reg s_reg2">实名认证</span> <span class="s_reg s_reg3">绑定托管账户</span>
						<span class="s_reg s_reg4">成功</span>
					</div>
					<!--步骤 end-->
					<div class="reg2_des">
						<b class="reg2_dico"></b>
						<p class="reg2_dtxt">P2P是正规的P2P平台，是真正的第三方独立托管，为了保证资金安全和投资人利益，需要进行第三方独立托管宝付支付账户的注册</p>
					</div>
					<p class="reg2_line"></p>
					<!--实名 start-->
					<div class="reg2_rz">
						<div class="r_rzbox">
							<span class="r_rztit">真实姓名</span><span>${msg.info }</span>
							<div class="r_rztxt">
								<input type="text" name="realName"nullmsg="姓名不能为空"
								errormsg="请输入真实姓名" value="${isPerson.realName }" datatype="s2-12" class="r_rzinput" />
							</div>
							<div class="Validform_checktip"></div>
						</div>
						<div class="r_rzbox">
							<span class="r_rztit">身份证号</span>
							<div class="r_rztxt">
								<input type="text" name="cardNo" nullmsg="身份证号码不嫩为空"
								errormsg="请输入正确18位2代身份证号码" value="${isPerson.cardNo }" datatype="/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/" class="r_rzinput" />
							</div>
							<div class="Validform_checktip"></div>
						</div>
						<p class="r_rzwarning">
							<b class="r_warnico"></b>提现只能提取现金至账号实名相同的银行卡！
						</p>
						<div class="r_rzsub">
							<input type="submit" id="btnSave" value="下一步" class="rz_submit" />
							
						</div>
					</div>
					<!--实名 end-->
				</div>
			</form>
		</div>
	</div>
	<!--reg end-->


	<!--header start-->
	<jsp:include page="/WEB-INF/view/front/comm/footer.jsp"></jsp:include>
	<!--header end-->

	<script type="text/javascript" src="js/Validform_v5.3.2_min.js"></script>
	<script type="text/javascript" src="js/passwordStrength-min.js"></script>
</body>
<script type="text/javascript"><%--
<!--
	
	var valiForm = $(".registForm").Validform({
		btnSubmit : "#btnSave",
		tiptype : 2,
		postonce : true,
		ajaxPost : false,
		callback : function(data) {
			if (data.status == 'y') {
				window.location.href="loginIndex.html";
			}else{
				alert(data.info);
			}
		}
	});
//-->
--%></script>
</html>
