<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>【行业资讯】_P2P互联网金融理财平台_小微金融，普惠大众！</title>
<meta name="keywords" content="P2P理财，实物抵押，互联网金融，P2P"/>
<meta name="description" content="${metaContent }"/>
<link rel="stylesheet" href="css/common.css" type="text/css" />
<link rel="stylesheet" href="css/page.css" type="text/css" />
<link rel="stylesheet" href="css/validform.css" type="text/css"></link>
</head>

<body>
	<!--header start-->
	<jsp:include page="/WEB-INF/view/front/comm/header.jsp"></jsp:include>
	<!--header end-->
	<!--banner start-->
	<div class="not_banner">
	   <div class="p1000"><a href="#"><img src="images/ad/not_banner.gif" /></a></div>
	</div>
	<!--banner end-->
	<!--page start-->
	<div class="page">
		<div class="notice_page">
			<!--侧边导航 start-->
			<jsp:include page="/WEB-INF/view/front/comm/lift_list.jsp"></jsp:include>
			<!--侧边导航 end-->
			<!--title start-->
			<div class="p_tit">
				<div class="p_tcont">
					<span class="not_tit">平台公告</span> <span class="not_des">HOTSPOT INFORMATION</span>
				</div>
			</div>
			<!--title end-->
			<!--新闻内容 start-->
			<div class="news_main">
				<h2 class="news_mh2">${CNoticeModel.noticeTitle }</h2>
				<div class="news_des">
					<div class="news_d_data">
						<i class="news_dico news_dico1"></i>
						<fmt:formatDate value="${CNoticeModel.createTime}"
							pattern="yyyy-MM-dd HH:mm:ss" />
					</div>
					<div class="news_d_come">
						<i class="news_dico news_dico2"></i>来源：无
					</div>
					<div class="news_d_num">
						<i class="news_dico news_dico3"></i>浏览量：无
					</div>
				</div>
				<!--txt start-->
				<div class="news_mcont">
					${CNoticeModel.noticeContent}
				</div>
				<!--txt end-->
				<!--keyword start-->
				<div class="news_mkeyword">
					<div class="news_gobank">
						<a href="noticeIndex.html">返回列表 >></a>
					</div>
					<div class="news_mky">
						关键字：无
					</div>
				</div>
				<!--keyword end-->
				<!--翻页 start-->
				<div class="news_fy">
					<ul>
						<li>上一页：
						<c:choose>
							<c:when test="${minNoticeModel == null}">没有了</c:when>
							<c:otherwise>
								<a href="notices-${minNoticeModel.id}.html">${minNoticeModel.noticeTitle}</a>
							</c:otherwise>
						</c:choose>
						</li>
						<li>下一页：
						<c:choose>
							<c:when test="${maxNoticeModel == null}">没有了</c:when>
							<c:otherwise>
								<a href="notices-${maxNoticeModel.id}.html">${maxNoticeModel.noticeTitle}</a>
							</c:otherwise>
						</c:choose>
						</li>
					</ul>
				</div>
				<!--翻页 end-->
			</div>
			<!--新闻内容 end-->
		</div>
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
	$("#noticeix").addClass("p_nlick");
});
//-->
</script>
</html>
