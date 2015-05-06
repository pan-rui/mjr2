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



</head>

<body>
	<!--header start-->
	<jsp:include page="/WEB-INF/view/front/comm/header.jsp"></jsp:include>
	<!--header end-->

<!--page start-->
<div class="p_page20">
   <!--当前位置 start-->
   <div class="p1000 p_position">
       <i class="p_posico"></i><span class="p_postxt"><a href="${path }">首页</a> > 平台原理</span>
   </div>
   <!--当前位置 end-->
   <!--main start-->
   <div class="fw_page clearfix">
       <!--标 start-->
       <div class="l_pro clearfix">
           <span class="fwt_tit fwt_tit1 fwt_tick" dirshow="0">平台原理</span>
           <span class="fwt_tit fwt_tit2" dirshow="1">资金托管</span>
         <div class="line1"></div>
       </div>
       <!--标 end-->
       <!--内容 start-->
       <div class="fwp_main">
           <!--平台原理 start-->
           <div class="m_fwtab f_ptyl" style="display:block;">
               <img src="images/aboutflm/ptyl.jpg" />
           </div>
           <!--平台原理 start-->
           <!--资金托管 start-->
           <div class="m_fwtab f_ptyl">
               <img src="images/aboutflm/zjtg.jpg" />
           </div>
           <!--资金托管 start-->
       </div>
       <!--内容 end-->
   </div>
   <!--main end-->
</div>
<!--page end-->

<!--footer start-->
	<jsp:include page="/WEB-INF/view/front/comm/footer.jsp"></jsp:include>
	<!--图片弹出窗-->
<script type="text/javascript" src="js/page.js"></script>

</body>
</html>
