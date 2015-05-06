<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>【关于P2P】_互联网金融理财平台,100%本息保障！</title>
<meta name="keywords" content="P2P，互联网金融，P2P理财，本息保障"/>
<meta name="description" content="P2P为深圳市P2P金融信息服务有限公司线上理财平台，致力于助力小微企业发展的同时普惠大众理财的互联网金融新模式！"/>
<link rel="stylesheet" href="css/common.css" type="text/css" />
<link rel="stylesheet" href="css/page.css" type="text/css" />
<link rel="stylesheet" href="css/validform.css" type="text/css"></link>
<link rel="stylesheet" href="css/picbox.css" type="text/css" media="screen" />


</head>

<body>
	<!--header start-->
	<jsp:include page="/WEB-INF/view/front/comm/header.jsp"></jsp:include>
	<!--header end-->

<!--安全保障 start-->
<div class="safe_banner">
    <div class="safe_bimg"><img src="images/ad/safe_banner.gif" /></div>
</div>
<!--安全保障 end-->
<!--内容 start-->
<div class="safe_contain">
    <div class="pa_nav safe_sidebar">
        <!--侧边导航 start-->
       <jsp:include page="/WEB-INF/view/front/comm/lift_list.jsp"></jsp:include>
        <!--侧边导航 end-->
    </div>
    <!--1 start-->
    <div class="safe_box safe_box1">
        <div class="safe_bmain">
            <div class="s_bmtxt">
              <p class="sbm_tit sbm_tit01"><img src="images/aboutflm/s_bmtit01.png" /></p>
              <p class="sbm_txt">通过调查合作机构的成立时间、管理团队背景、公司资产、项目流程、审核流程、放款流程、贷后管理等全方面审核后，方能确定资深合作机构。高门槛产品审核，以实物抵押类为主，包括房产、车辆、实物等，线下实地审查借款项目，抵押物价值能够覆盖借款金额，项目还款来源明确、借款人具备还款与再融资能力。</p>
            </div>
            <div class="s_bmimg"><img src="images/aboutflm/a_safe01.png" /></div>
        </div>
    </div>
    <!--1 end-->
    <!--2 start-->
    <div class="safe_box safe_box2">
      <div class="safe_bmain">
        <div class="s2_bimg">
          <div class="safe_bmain">
            <div class="s2_bimg"><img src="images/aboutflm/a_safe02.png" /></div>
            <div class="s2_bmtxt">
              <p class="sbm_tit sbm_tit01"><img src="images/aboutflm/s_bmtit02.png" /></p>
              <p class="sbm_txt">P2P接入宝付第三方资金托管，严格分离平台与投资人资金接触，用户充值、提现过程均在宝付第三方资金托管平台完成，杜绝了平台内部形成资金池。</p>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!--2 end-->
    <!--3 start-->
    <div class="safe_box safe_box1">
        <div class="safe_bmain">
            <div class="s_bmtxt">
               <p class="sbm_tit sbm_tit3"><img src="images/aboutflm/s_bmtit03.png" /></p>
               <p class="sbm_txt">P2P产品均由战略合作的担保机构提供100%本息担保，借款人发生逾期问题时，先由合作的担保机构履行担保责任，代为清偿应还款，优先解决投资用户的回款问题。</p>
            </div>
            <div class="s_bmimg s_bmimg3"><img src="images/aboutflm/a_safe03.png" /></div>
        </div>
    </div>
    <!--3 end-->
    <!--4 start-->
    <div class="safe_box safe_box2">
        <div class="safe_bmain">
          <div class="s2_bimg s2_bimg4"><img src="images/aboutflm/a_safe04.png" /></div>
            <div class="s2_bmtxt">
              <p class="sbm_tit sbm_tit4"><img src="images/aboutflm/s_bmtit04.png" /></p>
               <p class="sbm_txt">P2P拥有行业自身的技术资深团队，并采用业界先进的加密技术，对用户注册信息、账户收支信息进行高强度加密处理。同时，公司制定了严格的信息保密及信息隔离政策，确保每一个用户的信息均能得到妥善的保护。对用户隐私信息进行严格保护，未经用户同意，不向任何第三方公司、组织和个人披露用户个人信息、账户信息以及交易信息（法律法规另有规定的除外）。</p>
            </div>
        </div>
    </div>
    <!--4 end-->
    <!--5 start-->
    <div class="safe_box safe_box1">
        <div class="safe_bmain">
            <div class="s_bmtxt">
               <p class="sbm_tit sbm_tit05"><img src="images/aboutflm/s_bmtit05.png" /></p>
               <p class="sbm_txt">引进律师事务所，从服务合规性、政策走向等多方面提供法律意见，保障融资方式、平台和项目的合法性。</p>
            </div>
            <div class="s_bmimg"><img src="images/aboutflm/a_safe05.png" /></div>
        </div>
    </div>
    <!--5 end-->
</div>
<!--内容 end-->

<!--footer start-->
	<jsp:include page="/WEB-INF/view/front/comm/footer.jsp"></jsp:include>
	<!--图片弹出窗-->
<script type="text/javascript" src="js/picbox.js"></script>
<!--图片延时加载js-->
<script src="js/jquery.lazyload.js"></script> 

	<script type="text/javascript">
		$(window).scroll(function() {
			//$("body").css({"background-position":"center "+$(window).scrollTop()+""});
			if($(window).scrollTop()>=425){
				$(".p_sidebar").addClass("fixedNav").css('top', '50px');
			}else{
				$(".p_sidebar").removeClass("fixedNav").css('top', '50px');
			} 
		  });
		
	</script>
</body>
</html>
