<link href="/favicon.ico" type="image/x-icon" rel=icon />
<link href="/favicon.ico" type="image/x-icon" rel="shortcut icon" />
<link rel="Shortcut Icon" href="images/favicon.ico" />
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>

<!--header start-->
<div id="head">
   <!--top start-->
   <div class="top_cont">
      <div class="h_tmain clearfix">
         <!--left start-->
         <div class="top_left">
            <i class="top_tico tran"></i>
            <span class="top_txt">客服电话：400-888-9999</span>
         </div>
         <!--left end-->
         <!--right start-->
         <div class="top_right">
         	<c:choose><c:when test="${null==sessionScope.user}"><a href="loginIndex.html" class="top_rlogin">登录</a><a href="regIndex.html" class="top_rreg">注册</a></c:when> <c:otherwise>
         		 <a href="center/selectUserIndex.html" class="top_user tran">${sessionScope.user.cellPhone}</a><a href="bfpay/readyRecharge.html" >充值</a>
         		 <a href="loginOut.html">退出登陆</a>
         		</c:otherwise> </c:choose> <a href="helpInfo.html">帮助中心</a>
         </div>
         <!--right end-->
      </div>
   </div>
   <!--top end-->
   <!--logo and nav start-->
   <div class="nav_cont">
      <div class="nav_main">
          <!--logo start-->
          <div class="h_logo"><a href="${path }"><img alt="P2P_互联网金融理财平台" src="images/logo.jpg" style="width: 380px;" /></a></div>
          <!--logo end-->
          <!--口号 start-->
          <div class="h_kouhao"></div>
          <!--口号 end-->
          <!--nav start-->
          <div class="h_nav">
             <ul>                              
                <li><a href="${path }" id="aIndex" class="nav_a nav_a1">首&nbsp;&nbsp;&nbsp;&nbsp;页</a></li>
                <li><a href="borrowList.html" id="borrowList" class="nav_a nav_a1">理财投资</a></li>
                <li><a href="quickBorrowpersonal.html" id="quickBoorw" class="nav_a nav_a1">快速借款</a></li>
                <li><a href="center/selectUserIndex.html" id="myCenter" class="nav_a nav_a1">我的账户</a></li>
                <li><a href="aboutus.html" id="aboutUs" class="nav_a nav_a1">关于我们</a></li>
             </ul>
          </div>
          <!--nav end-->
      </div>
   </div>
   <!--logo and nav end-->
</div>
<!--header end-->
