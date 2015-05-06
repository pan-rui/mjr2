<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>【行业资讯】_P2P互联网金融理财平台_小微金融，普惠大众！</title>
<meta name="keywords" content="P2P理财，实物抵押，互联网金融，P2P" />
<meta name="description"
	content="P2P为深圳市P2P金融信息服务有限公司线上理财平台，致力于助力小微企业发展的同时普惠大众理财的互联网金融新模式！" />
<link rel="stylesheet" href="css/common.css" type="text/css" />
<link rel="stylesheet" href="css/page.css" type="text/css" />
<link rel="stylesheet" href="css/validform.css" type="text/css"></link>
</head>

<body>
	<!--header start-->
	<jsp:include page="/WEB-INF/view/front/comm/header.jsp"></jsp:include>
	<!--header end-->
	<!--banner start-->
	<div class="news_banner">
	   <div class="p1000"><a href="#"><img src="images/ad/news_banner.gif" /></a></div>
	</div>
	<!--banner end-->
	<!--page start-->
<div class="p_page20">
   <!--当前位置 start-->
   <div class="p1000 p_position">
       <i class="p_posico"></i><span class="p_postxt"><a href="#">首页</a> &gt; <a href="#">新闻动态</a></span>
   </div>
   <!--当前位置 end-->
   <!--main start-->
   <div class="news_cont">
   	   <!--左侧导航 start-->
        <div class="pa_nav news_psidebar">
            <!--侧边导航 start-->
            	<jsp:include page="/WEB-INF/view/front/comm/lift_list.jsp"></jsp:include>
            <!--侧边导航 end-->
       </div>
       <!--左侧导航 end-->
			<!--侧边导航 start-->
			<jsp:include page="/WEB-INF/view/front/comm/lift_list.jsp"></jsp:include>
			<!--侧边导航 end-->
			<!--右侧内容 start-->
			<div class="ncont_main">
				<!--标 start-->
				<div class="l_pro clearfix">
					<span class="l_ptitle">新闻动态</span>
					<div class="line1"></div>
				</div>
				<!--标 end-->
				<!--txt start-->
				<div class="nc_main">
					<!--热点资讯list start-->
					<div class="hotnews">
							<c:forEach items="${pageBean}" var="bean" varStatus="st">
								<!--box start-->
								<div class="new_cont">
									<!--标 start-->
									<div class="new_box">
										<b class="notice_ico tran"></b> <span class="new_btit"><a
											href="news-${bean.id}.html">${bean.title}</a>
										</span> <span class="new_bdata"><fmt:formatDate
												value="${bean.createTime}" pattern="yyyy-MM-dd" />
										</span>
									</div>
									<!--标 end-->
									<div class="new_btxt"><my:delHTML len="120" content="${bean.content}"/> </div>
								</div>
								<!--box end-->
							</c:forEach>
						<!--热点资讯list end-->
						<!--page start-->
						<my:frontPager curPage="${page.page }" pageSize="${page.limit }"
							totalCount="${page.totalCount }" />
						<!--page end-->
					</div>
					<!--txt end-->
				</div>
			</div>
			<!--右侧内容 end-->
   </div>
   <!--main end-->
</div>
<!--page end-->


	<!--header start-->
	<jsp:include page="/WEB-INF/view/front/comm/footer.jsp"></jsp:include>
	<!--header end-->

	<script type="text/javascript" src="js/Validform_v5.3.2_min.js"></script>
</body>
<script type="text/javascript">
<!--
$(function(){
	$("#newsix").addClass("p_nlick");
	$(window).scroll(function() {
		if($(window).scrollTop()>=468){
			$(".p_sidebar").addClass("fixedNav").css('top', '10px');
		}else{
			$(".p_sidebar").removeClass("fixedNav").css('top', '10px');
		} 
	  });
});
-->
</script>
</html>
