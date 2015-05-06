<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="${path }">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>P2P -我的红包</title>
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
					<span class="u_titwz">我的红包</span>
					<div class="line1"></div>
				</div>
				<!--title end-->
				<!--账户 start-->
				<div class="u_txt">
					<!--我的红包 start-->
					<div class="m_gift">
						<!--tit start-->
						<div class="mg_tit">
							<a href="javascript:;" class="mgt_t mgt_tick" divshow="0">未领取</a>
							<a href="javascript:;" class="mgt_t" divshow="1">已领取</a> <a
								href="javascript:;" class="mgt_t" divshow="2">已过期</a>
						</div>
						<!--tit end-->
						<!--txt start-->
						<div class="mg_txt">
							<div class="bank_trip hong_trip">
								<p class="myb_pre">成功领取红包后，1个工作日打款到您的宝付账户。</p>
							</div>
							<!--未领取 start-->
							<div class="mgtab_txt mgtab_wei" style="display:block;">
								<ul>
									<c:forEach items="${UserRedPaper }" var="userRP" varStatus="st">
										<c:choose>
											<c:when
												test="${userRP.couponType == 1 && userRP.couponStatus == 1}">
												<li>
													<div class="mg_money">
														<h2 class="mg_tmoney">
															￥<span class="money_number">${userRP.couponAmount
																}</span>
														</h2>
														<div class="mg_tdes">
															<span class="mg_td1">${userRP.couponRemarks }</span> <span
																class="mg_td2">红包来源：${userRP.couponName }</span>
														</div>
													</div>
													<div class="mg_time">
														<span class="mgt_date">有效期至：<fmt:formatDate
																value="${userRP.expirationDate}" pattern="yyyy-MM-dd" />
														</span>
														<p class="mgt_but">
															<a href="javascript:;" onclick="userApplyRP(this,'${userRP.id}')" class="mmt_but mmt_butwei">领取红包</a>
														</p>
													</div>
												</li>
											</c:when>
										</c:choose>
									</c:forEach>
								</ul>
							</div>
							<!--未领取 end-->
							<!--已领取 start-->
							<div class="mgtab_txt mgtab_getover">
								<ul>
										<c:forEach items="${UserRedPaper }" var="userRP" varStatus="st">
										<c:choose>
											<c:when
												test="${userRP.couponType == 1 && (userRP.couponStatus == 3 || userRP.couponStatus == 2)}">
												<li>
													<div class="mg_money">
														<h2 class="mg_tmoney">
															￥<span class="money_number">${userRP.couponAmount
																}</span>
														</h2>
														<div class="mg_tdes">
															<span class="mg_td1">${userRP.couponRemarks }</span> <span
																class="mg_td2">红包来源：${userRP.couponName }</span>
														</div>
													</div>
													<div class="mg_time">
														<span class="mgt_date">有效期至：<fmt:formatDate
																value="${userRP.expirationDate}" pattern="yyyy-MM-dd" />
														</span>
														<p class="mgt_but">
															<span class="mmt_but mmt_butgetover">已领取</span>
														</p>
													</div>
												</li>
											</c:when>
										</c:choose>
									</c:forEach>
								</ul>
							</div>
							<!--已领取 end-->
							<!--已过期 start-->
							<div class="mgtab_txt mgtab_gameover">
								<ul>
								<c:forEach items="${UserRedPaper }" var="userRP" varStatus="st">
										<c:choose>
											<c:when
												test="${userRP.couponType==1  } && ${userRP.couponStatus==4  }">
												<li>
													<div class="mg_money">
														<h2 class="mg_tmoney">
															￥<span class="money_number">${userRP.couponAmount
																}</span>
														</h2>
														<div class="mg_tdes">
															<span class="mg_td1">${userRP.couponRemarks }</span> <span
																class="mg_td2">红包来源：${userRP.couponName }</span>
														</div>
													</div>
													<div class="mg_time">
														<span class="mgt_date">有效期至：<fmt:formatDate
																value="${userRP.expirationDate}" pattern="yyyy-MM-dd" />
														</span>
														<p class="mgt_but">
															<span class="mmt_but mmt_butgetover">已过期</span>
														</p>
													</div>
												</li>
											</c:when>
										</c:choose>
									</c:forEach>
								</ul>
							</div>
							<!--已过期 end-->
						</div>
						<!--txt end-->
					</div>
					<!--我的红包 end-->
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

	<script type="text/javascript" src="js/page.js"></script>
</body>
<script type="text/javascript">
<!--
	$(function() {
		$("#myCenter").addClass("nav_aclick");
		$("#center5").addClass("nav_aclik");
	});
	//领奖提示
	function userApplyRP(this_,rpId){
		$.lionPost("center/userApplyRedPaper.html", {redPaperId:rpId}, function(data) {
			if(data.status =='y'){
				$(this_).parent().html("<span class='wei_ok'>申请中</span>");
				layer.msg('恭喜您，已成功申请红包。我们将于一个工作日之内送出，请注意查收。', 3, 1);
			} else {
				layer.msg(data.info, 2, 1);
			}
		});
		
	}
	
//-->
</script>
</html>
