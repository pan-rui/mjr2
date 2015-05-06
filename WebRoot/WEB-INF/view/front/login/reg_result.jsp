<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>P2P - 实名成功</title>
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
			<div class="reg_cont">
				<!--步骤 start-->
				<div class="reg_state reg_state4">
					<span class="s_reg s_reg1">填写注册信息</span> <span class="s_reg s_reg2">实名认证</span>
					<span class="s_reg s_reg3">绑定托管账户</span> <span class="s_reg s_reg4">成功</span>
				</div>
				<!--步骤 end-->
				<!--步骤 start-->
				<div class="reg_success">
					<b class="reg_sucico"></b>
					<div class="reg_suctxt">
						<h2 class="r_success">恭喜您，注册成功！</h2>
						<p class="r_suctxt"> 
							<span class="r_time" id="times">5</span> 秒后立即跳回 <span class="r_member">会员中心</span>，如未跳转，<a
								href="center/selectUserIndex.html" class="r_memclick">请点击</a>
						</p>
					</div>
				</div>
				<!--步骤 end-->
			</div>
		</div>
	</div>
	<!--reg end-->
	<!--header start-->
	<jsp:include page="/WEB-INF/view/front/comm/footer.jsp"></jsp:include>
	<!--header end-->

</body>
  <script type="text/javascript">
			var times = 6;
			var t;
			$(function(){
				t=setInterval(function(){
					if(times<=0){
						clearInterval(t);
						window.location.href="center/selectUserIndex.html";
					}else{
						times--;
						$("#times").html(times);
					}
				},1000);
			});
			
			
		</script>
</html>
