<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="${path }">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>P2P - 站内消息</title>
<link rel="stylesheet" href="css/common.css" type="text/css" />
<link rel="stylesheet" href="css/page.css" type="text/css" />

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
					<span class="u_titwz">站内消息</span>
					<div class="line1"></div>
				</div>
				<!--title end-->
				<!--主体 start-->
				<div class="u_txt">
					<!--站内消息 start-->
					<div class="message">
						<!--tit start-->
						<div class="message_tit">
							<span class="mest_tit">时间</span> <span class="mest_txt">内容</span>
						</div>
						<!--tit end-->
						<!--txt start-->
						<div class="message_main">
							<c:forEach items="${pageBean}" var="bean" varStatus="st">
								<!--box start-->
								<div class="mess_box">
									<!--上 start-->
									<div class="mess_btit">
										<span class="mbt_tit"><i class="mtit_ico mtit_up"></i>
											<fmt:formatDate value="${bean.createTime}"
												pattern="yyyy-MM-dd HH:mm:ss" /> </span>
										<div class="mbt_txt">${bean.messageTitle}</div>
									</div>
									<!--上 end-->
									<!--下 start-->
									<div class="mess_btxt">
										<i class="mb_line"></i> ${bean.messageContent}
									</div>
									<!--下 end-->
								</div>
							</c:forEach>
							<!--box end-->
							<!--page start-->
							<my:frontPager curPage="${page.page }" pageSize="${page.limit }"
								totalCount="${page.totalCount }" />
							<!--page end-->
						</div>
						<!--txt end-->
					</div>
					<!--站内消息 end-->
				</div>
				<!--主体 end-->
			</div>
			<!--main end-->
		</div>
		<!--用户中心 end-->
	</div>
	<!--page end-->


	<!--header start-->
	<jsp:include page="/WEB-INF/view/front/comm/footer.jsp"></jsp:include>
	<!--header end-->

<form id="searchForm" action="center/letterStation.html">
	<input id="curPage" name="curPage" type="hidden" value="1" />
</form>
</body>
<script type="text/javascript" src="js/page.js"></script>
<script type="text/javascript">
<!--
	$(function() {
		$("#myCenter").addClass("nav_aclick");
		$("#center7").addClass("nav_aclik");
	});
//-->
</script>
</html>
