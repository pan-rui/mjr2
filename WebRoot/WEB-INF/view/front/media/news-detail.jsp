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
	<div class="news_banner">
	   <div class="p1000"><a href="#"><img src="images/ad/news_banner.gif" /></a></div>
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
					<span class="not_tit">新闻动态</span> <span class="not_des">NEWS TREND</span>
				</div>
			</div>
			<!--title end-->
			<!--新闻内容 start-->
			<div class="news_main">
				<h2 class="news_mh2">${cMediaReportModel.title }</h2>
				<div class="news_des">
					<div class="news_d_data">
						<i class="news_dico news_dico1"></i>
						<fmt:formatDate value="${cMediaReportModel.createTime}"
							pattern="yyyy-MM-dd HH:mm:ss" />
					</div>
					<div class="news_d_come">
						<i class="news_dico news_dico2"></i>来源：${cMediaReportModel.srcUrl
						}
					</div>
					<div class="news_d_num">
						<i class="news_dico news_dico3"></i>浏览量：${cMediaReportModel.viewCount
						}
					</div>
				</div>
				<!--txt start-->
				<div class="news_mcont">
					<%--<img src="images/ad/newsad.gif"
						style="display:block; margin:0px auto;" />
				--%>
					${cMediaReportModel.content}
				</div>
				<!--txt end-->
				<!--keyword start-->
				<div class="news_mkeyword">
					<div class="news_gobank">
						<a href="newsIndex.html">返回列表 >></a>
					</div>
					<div class="news_mky">
						关键字：
						<c:forTokens var="kw" items="${cMediaReportModel.keywords}" delims="，">
							<a href="#" target="_blank">${kw }</a>
						</c:forTokens>
					</div>
				</div>
				<!--keyword end-->
				<!--翻页 start-->
				<div class="news_fy">
					<ul>
						<li>上一页：
						<c:choose>
							<c:when test="${minMediaModel == null}">没有了</c:when>
							<c:otherwise>
								<a href="news-${minMediaModel.id}.html">${minMediaModel.title}</a>
							</c:otherwise>
						</c:choose>
						</li>
						<li>下一页：
						<c:choose>
							<c:when test="${maxMediaModel == null}">没有了</c:when>
							<c:otherwise>
								<a href="news-${maxMediaModel.id}.html">${maxMediaModel.title}</a>
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
	$("#newsix").addClass("p_nlick");
});
//-->
</script>
</html>
