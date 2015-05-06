<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>帮助中心_${whenHelpType.helpTypeName}</title>
<meta name="keywords" content="P2P网贷，互联网金融，P2P理财，P2P"/>
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
			<i class="p_posico"></i><span class="p_postxt"><a href="${path }">首页</a>
				&gt; <a href="#">帮助中心</a> </span>
		</div>
		<!--当前位置 end-->
		<!--main start-->
		<div class="p_help clearfix">
			<!--left start-->
			<div class="help_left">
				
					<!--box start-->
					<div class="h_sidebox">
						<span class="h_sidebox"><i class="h_sico h_sico1 tran"></i>新手入门</span>
						<div class="h_sbox">
							<ul>
							<c:forEach items="${HTlist}" var="helpType" varStatus="st">
								<c:choose>
									<c:when test="${helpType.helpTypeId ==1 }">
										<li><a href="helpShow-${helpType.id}.html"
											id="helpT_${helpType.id}" >${helpType.helpTypeName}</a>
										</li>
									</c:when>
								</c:choose>
							</c:forEach>
							</ul>
						</div>
					</div>
					<!--box end-->
					<!--box start-->
					<div class="h_sidebox">
						<span  class="h_sidebox"><i class="h_sico h_sico2 tran"></i>账户管理</span>
						<div class="h_sbox">
							<ul>
							<c:forEach items="${HTlist}" var="helpType" varStatus="st">
								<c:choose>
									<c:when test="${helpType.helpTypeId ==2 }">
										<li><a href="helpShow-${helpType.id}.html"
											id="helpT_${helpType.id}" >${helpType.helpTypeName}</a>
										</li>
									</c:when>
								</c:choose>
							</c:forEach>
							</ul>
						</div>
					</div>
					<!--box end-->
					<!--box start-->
					<div class="h_sidebox">
						<span class="h_sidebox"><i
							class="h_sico h_sico3 tran"></i>借款人</span>
						<div class="h_sbox">
							<ul>
							<c:forEach items="${HTlist}" var="helpType" varStatus="st">
								<c:choose>
									<c:when test="${helpType.helpTypeId ==3 }">
										<li><a href="helpShow-${helpType.id}.html" id="helpT_${helpType.id}"
											>${helpType.helpTypeName}</a></li>
									</c:when>
								</c:choose>
							</c:forEach>
							</ul>
						</div>
					</div>
					<!--box end-->
					<!--box start-->
					<div class="h_sidebox">
						<span class="h_sidebox"><i class="h_sico h_sico4 tran"></i>热点问题</span>
						<div class="h_sbox">
							<ul>
							<c:forEach items="${HTlist}" var="helpType" varStatus="st">
								<c:choose>
									<c:when test="${helpType.helpTypeId ==4 }">
										<li><a href="helpShow-${helpType.id}.html"
											id="helpT_${helpType.id}" >${helpType.helpTypeName}</a>
										</li>
									</c:when>
								</c:choose>
							</c:forEach>
							</ul>
						</div>
					</div>
					<!--box end-->
				
				
			</div>
			<!--left end-->
			<!--right start-->
			<div class="help_main">
				<!--标 start-->
				<div class="l_pro clearfix">
					<span class="l_ptitle"><a href="helpInfo.html">帮助中心</a> &gt; <a href="#">${whenHelpType.helpTypeName}</a>
					</span>
					<div class="line1"></div>
				</div>
				<!--标 end-->
				<div class="h_pmain">
					<c:forEach items="${pageBean}" var="bean" varStatus="st">
						<!--box start-->
						<div class="help_box">
							<h2 class="help_tit">
								<a href="#" class="h_tita"><i class="h_titico"></i>${bean.questionName
									}</a>
							</h2>
							<div class="help_txt">
								<b class="h_txtico"></b> ${bean.answer }
							</div>
						</div>
						<!--box end-->
					</c:forEach>

					<!--page start-->
					<my:frontPager curPage="${page.page }" pageSize="${page.limit }"
						totalCount="${page.totalCount }" />
					<!--page end-->
				</div>
			</div>
			<!--right end-->
		</div>
		<!--main end-->
	</div>
	<!--page end-->

	<!--header start-->
	<jsp:include page="/WEB-INF/view/front/comm/footer.jsp"></jsp:include>
	<!--header end-->
</body>
<script type="text/javascript">
<!--
	$(function() {
		
		$("#helpT_"+'${whenHelpType.id}').addClass("h_aclick");
	});
	$(".help_box .help_tit").toggle(function() {
		$(this).find(".h_tita").css({
			"background-color" : "#fefff0",
			"color" : "#f67800"
		});
		$(this).find(".h_titico").addClass("h_titico2");
		$(this).siblings(".help_txt").show(300);
	}, function() {
		$(this).find(".h_tita").css({
			"background-color" : "#ffffff",
			"color" : "#474747"
		});
		$(this).find(".h_titico").removeClass("h_titico2");
		$(this).siblings(".help_txt").hide(300);
	});
//-->
</script>
</html>
