<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="${path }">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>P2P - 资金流水</title>
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
					<span class="u_titwz">交易记录</span>
					<div class="line1"></div>
				</div>
				<!--title end-->
				<!--账户 start-->
				<div class="u_txt">
					<!--交易记录 start-->
					<div class="flowrecord">
						<!--记录 start-->
						<div class="record_list">
							<!--tit start-->
							<div class="record_tit">
								<span class="rt_t rt_t1">时间</span> <span class="rt_t rt_t2">记录名称</span>
								<span class="rt_t rt_t3">类型</span> <span class="rt_t rt_t4">存入</span>
								<span class="rt_t rt_t5">支出</span> <span class="rt_t rt_t8">可用余额</span>
							</div>
							<!--tit end-->
							<!--txt start-->
							<div class="record_txt">
								<c:forEach items="${pageBean}" var="bean" varStatus="st">
									<!--box start-->
									<div class="rec_box">
										<div class="rxt_x rxt_x1"><fmt:formatDate value="${bean.createTime}"
												pattern="yyyy-MM-dd HH:mm:ss" /> </div>
										<div class="rxt_x rxt_x2">${bean.fundMode }</div>
										
											<c:choose>
												<c:when test="${bean.operType==1 }">
													<div class="rxt_x rxt_x3">收入</div>
													<div class="rxt_x rxt_x4">+${bean.operAmount }</div>
													<div class="rxt_x rxt_x5">-</div>
												</c:when>
												<c:when test="${bean.operType==2 }">
													<div class="rxt_x rxt_x3">冻结</div>
													<div class="rxt_x rxt_x4">-</div>
													<div class="rxt_x rxt_x5">-${bean.operAmount }</div>
												</c:when>
												<c:when test="${bean.operType==3 }">
													<div class="rxt_x rxt_x3">解冻</div>
													<div class="rxt_x rxt_x4">+${bean.operAmount }</div>
													<div class="rxt_x rxt_x5">-</div>
												</c:when>
												<c:when test="${bean.operType==4 }">
													<div class="rxt_x rxt_x3">支出</div>
													<div class="rxt_x rxt_x4">-</div>
													<div class="rxt_x rxt_x5">-${bean.operAmount }</div>
												</c:when>
											</c:choose>
										<div class="rxt_x rxt_x8">${bean.usableAmount }</div>
									</div>
									<!--box end-->
								</c:forEach>
							</div>
							<!--txt end-->
						</div>
						<!--记录 end-->
						<!--page start-->
						<my:frontPager curPage="${page.page }" pageSize="${page.limit }"
							totalCount="${page.totalCount }" />
						<!--page end-->
					</div>
					<!--交易记录 end-->
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
<form id="searchForm" action="center/moneyFlow.html">
 <input id="curPage" name="curPage" type="hidden" value="1" />
</form>
</body>
<script type="text/javascript">
<!--
	$(function() {
		$("#myCenter").addClass("nav_aclick");
		$("#center8").addClass("nav_aclik");
	});
//-->
</script>
</html>
