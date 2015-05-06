<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="${path }">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>P2P - 账户中心</title>
<link rel="stylesheet" href="css/common.css" type="text/css" />
<link rel="stylesheet" href="css/page.css" type="text/css" />
<script type="text/javascript" src="js/tips.js"></script>

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
             <span class="u_titwz">账户充值</span>
             <div class="line1"></div>
          </div>
          <!--title end-->
           <!--账户 start-->
          <div class="u_txt">
              <!--充值 start-->
              <div class="recharge">
                  <div class="cz_ye">
                      <span class="czye_mtit">账户余额</span>
                      <div class="czye_money"><span class="money">${taccount.usableAmount }</span>元</div>
                  </div>
                  
                  <form id="form_Id" action="bfpay/userRecharge.html" method="post" target="_blank">
                  	<div class="chage_box">
                   	  <div class="c_boxtit">充值金额</div>
                  	   <div class="c_boxtxt"><input type="text" class="c_boxinput" name="rechargeAmount" onkeyup="this.value=this.value.replace(/[^\d\.]+/g,'')" onblur="this.value=this.value.replace(/(\.\d{2})\d*$/,'\$1')" /></div>
                 	</div>
                  
                  
                  <div class="chage_box">
                     <div class="c_boxtit">充值方式</div>
                     <div class="c_boxtxt">
                         <div class="cha_type"><b class="cha_tico"></b><span class="baofu_logo"></span></div>
                     </div>
                  </div>
                  <div class="chage_sub"><input type="button" value="充 值" class="tx_submit"/></div>
                  </form>
                  <!--小贴士 start-->
                  <div class="teshi_box">
                      <div class="teshi_tit"><span class="teshi_a teshi_ahov">充值小贴士</span></div>
                      <div class="teshi_txt">
                        1.	为了您的账户安全，请在充值前进行身份认证、手机绑定以及提现密码设置。<br />
						2.	您的账户资金将通过宝付第三方托管支付平台进行充值。宝付会收取0.2%充值手续费，目前由P2P平台为您垫付。<br />
						3.	请注意您的银行卡充值限制，以免造成不便。<br />
						4.	禁止洗钱、信用卡套现、虚假交易等行为，一经发现并确认，将终止该账户的使用。<br />
						5.	如果充值金额没有及时到账，请联系客服，400-888-9999。
                      </div>
                  </div>
                  <!--小贴士 end-->
              </div>
              <!--充值 end-->
          </div>
          <!--账户 end-->
      </div>
      <!--main end-->
   </div>
   <!--用户中心 end-->
</div>
<!--page end-->


	<!--header start-->
	<jsp:include page="/WEB-INF/view/front/comm/footer.jsp"></jsp:include>
	<!--header end-->



	<!--投标提示 start-->
<div class="t_question">
    <div class="t_quetit clearfix">
       <i class="tq_ico"></i>
       <div class="tq_des">充值完成前，请不要关闭此充值验证窗口。<br />
充值完成后，请根据您投标的情况点击下面按钮。</div>
    </div>
    <div class="t_quetxt clearfix">
        <a href="center/selectRcgRcdList.html" class="t_tbok">充值完成</a>
        <a href="helpShow-3.html" class="t_twhat">充值遇到问题</a>
    </div>
</div>
<!--投标提示 end-->
</body>


<script type="text/javascript">
//投标提示
$('.tx_submit').on('click', function(){
	var srje=$(".c_boxinput").val();
	if(srje<99){
		alert("充值金额必须大于100");
		return;
	}
	  var t_issue =	$.layer({
			type: 1,
			title: '充值提示',
			//shadeClose: true, 开启点击遮罩关闭层
			area: ['480px','250px'],
			border : [0, 0.5, '#666'],
			offset: [($(window).height() - 250)/2+'px', ''],
			page: {dom : '.t_question'}
		});
	  //自设关闭
	$('.t_quetxt .t_tbok').on('click', function(){
		layer.close(t_issue);
	});
	$("#form_Id").submit();
});

</script>

<script type="text/javascript">
<!--
	$(function() {
		$("#myCenter").addClass("nav_aclick");
		$("#center1").addClass("nav_aclik");
	});
//-->

<%--
function gorecharge()
{
	var diag = new Dialog();
	diag.Width = 500;
	diag.Height = 500;
	//diag.Title = "是否充值成功？";
	diag.InnerHtml='<div style="line-height:500px; font-size:20px"><a href="center/selectUserIndex.html">充值成功</a><a href="">充值遇到问题</a></div>'
	//diag.OKEvent = function(){diag.close();};//点击确定后调用的方法
	diag.show();

	$("#form_Id").submit();
}
--%></script>
</html>
