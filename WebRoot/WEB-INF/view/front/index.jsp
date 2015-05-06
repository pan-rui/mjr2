<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>P2P_小微金融 普惠大众_中国互联网金融P2P理财平台</title>
<meta name="keywords" content="P2P，互联网金融，P2P，P2P理财，P2P网贷" />
<meta name="description"
	content="P2P（p2p.com）_为深圳市P2P金融信息服务有限公司线上理财平台！打造专业的小微金融服务体系，让更多的人从小微金融中受益！" />
<link rel="stylesheet" href="css/common.css" type="text/css" />
<link rel="stylesheet" href="css/page.css" type="text/css" />

</head>

<body>
	<!--header start-->
	<jsp:include page="/WEB-INF/view/front/comm/header.jsp"></jsp:include>
	<!--header end-->

	<!--banner start-->
	<div class="banner flexslider">
		<ul class="slides">
			<c:forEach items="${banners }" var="banner" varStatus="st">
				<li style="background:url(${banner.imgPath }) 50% 0 no-repeat;"><a
					href="${banner.url }"></a>
				</li>
			</c:forEach>

		</ul>
	</div>
	<!--banner end-->
	<!--优势公告 start-->
	<div class="surprise_notice">
		<div class="sn_main clearfix">
			<!--优势 start-->
			<div class="surprise">
				<ul>
					<li class="sur_li"><a href="javascript:void(0)"
						class="sur_img sur_img1"></a> <!--隐藏区域 start-->
						<div class="sur_box">
							<p class="space10"></p>
							<b class="ys_ico ys_ico1 tran"></b>
							<p class="ys_des ys_des1">
								100%多重保障<br />资金去向透明化<br />多重风控审核
							</p>
						</div> <!--隐藏区域 start--></li>
					<li class="sur_li"><a href="javascript:void(0)"
						class="sur_img sur_img2"></a> <!--隐藏区域 start-->
						<div class="sur_box">
							<p class="space10"></p>
							<b class="ys_ico ys_ico2 tran"></b>
							<p class="ys_des ys_des2">
								长短期收益产品匹配<br />债券转让灵活机制<br />低门槛高收益
							</p>
						</div> <!--隐藏区域 start--></li>
					<li class="sur_li"><a href="javascript:void(0)"
						class="sur_img sur_img3"></a> <!--隐藏区域 start-->
						<div class="sur_box">
							<p class="space10"></p>
							<b class="ys_ico ys_ico3 tran"></b>
							<p class="ys_des ys_des3">
								放款审核程序严谨<br />投融资操作便捷<br />从业人员经验丰富
							</p>
						</div> <!--隐藏区域 start--></li>
				</ul>
			</div>
			<!--优势 end-->
			<!--平台公告 start-->
			<div class="notice">
				<div class="box_tit">
					<span class="box_twz">平台公告</span> <span class="box_tmore"><a
						href="noticeIndex.html">更多 &gt;</a> </span>
				</div>
				<div class="notice_txt">
					<ul>
						<c:forEach items="${notices }" var="notice" varStatus="st">
							<li><b class="notice_ico tran"></b><a
								href="notices-${notice.id }.html">${notice.noticeTitle }</a>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<!--平台公告 end-->
		</div>
	</div>
	<!--优势公告 end-->

	<!--分割线 start-->
	<div class="ind_line">
		<div class="i_ltxt">
			<span class="i_t i_t1">乐投理财</span> <span class="i_t i_t2">让财富稳健中逐步升值</span>
		</div>
		<div class="i_line"></div>
	</div>
	<!--分割线 end-->
	<c:choose>
		<c:when test="${preSaleBrrows !=null}">
			<c:forEach items="${preSaleBrrows }" var="pBrrow" varStatus="st">
				<!--预售 start-->
				<div class="pro_ys">
					<span class="p_biao p_biao2 tran"></span>
					<h2 class="ys_tit">
						<a href="borrow-${pBrrow.id }.html" class="ys_titwz">${pBrrow.borrowTitle
							}</a> <a id="t_bao" class="tag tag_i tag_bao"
							href="javascript:void(0)" tips="担保公司"></a>
						<!--<i id="t_bao" class="tag tag_bao"></i>
         <i id="t_ya" class="tag tag_ya"></i>-->
					</h2>
					<div class="ys_txt clearfix">
						<!--收益 start-->
						<div class="ys_box ys_box1">
							<div class="ys_icotxt" onmouseover="ysmos(this)"
								onmouseout="ysmoss(this)">
								<span class="yst_ico yst_ico1"></span> <span class="yst_txt">年化收益</span>
							</div>
							<div class="ybox_txt ybox_txt1">
								<div class="b_nhl">
									<span class="bn_number"><fmt:formatNumber
											value='${pBrrow.annualRate }' pattern="0.0" /> </span><b
										class="bn_fh">%</b>
								</div>
								<div class="b_jiang">
									<b class="b_jnew"><a id="t_newer" class="tag"
										href="javascript:void(0)"
										tips="年化收益率<b style='color:red'>+0.4%</b>的新手奖励
<br />（自第一次投资起30天内可享有新手奖励）">+
											新手奖励</a> </b> <b class="b_jvip"><a id="t_vips" class="tag"
										href="javascript:void(0)"
										tips="Vip1: 1万＜待收本金≤5万，  年化收益率<b style='color:red'>+0.40%</b><br />
Vip2: 5万＜待收本金≤10万，年化收益率<b style='color:red'>+0.8%</b><br />
Vip3: 待收本金＞10万，        年化收益率<b style='color:red'>+1.6%</b><br />
(新手奖励可与Vip奖励叠加)">+
											VIP奖励</a> </b>
								</div>
							</div>
						</div>
						<!--收益 end-->
						<!--收益 start-->
						<div class="ys_box ys_box2">
							<div class="ys_icotxt" onmouseover="ys2mos(this)"
								onmouseout="ys2moss(this)">
								<span class="yst_ico yst_ico2"></span> <span class="yst_txt">投资额度</span>
							</div>
							<div class="ybox_txt ybox_txt2">
								<p class="ybox_money">
									<span class="y_totalmoney"><fmt:formatNumber
											value='${pBrrow.borrowAmount }' pattern="#,###" /> </span>元
								</p>
							</div>
						</div>
						<!--收益 end-->
						<!--收益 start-->
						<div class="ys_box ys_box3">
							<div class="ys_icotxt" onmouseover="ys3mos(this)"
								onmouseout="ys3moss(this)">
								<span class="yst_ico yst_ico3"></span> <span class="yst_txt">投资期限</span>
							</div>
							<div class="ybox_txt ybox_txt3">
								<p class="ybox_qx">
									<span class="ybox_month">${pBrrow.deadline }</span>
									<c:choose>
										<c:when test="${pBrrow.deadlineType==1 }">天</c:when>
										<c:when test="${pBrrow.deadlineType==2 }">月</c:when>
									</c:choose>
								</p>
							</div>
						</div>
						<!--收益 end-->
						<!--but start-->
						<div class="ys_but">
							<a href="borrow-${pBrrow.id }.html" class="ys_butxt">查看详情</a>
						</div>
						<!--but end-->
					</div>
				</div>
				<!--预售 end-->

			</c:forEach>
		</c:when>
	</c:choose>

	<c:choose>
		<c:when test="${IngBrrows !=null}">
			<c:forEach items="${IngBrrows }" var="IBrrow" varStatus="st">
				<!--热售 start-->
				<div class="pro_sell">
					<!--左侧 start-->
					<div class="psell_cs">
						<h2 class="psell_tit">
							<a href="borrow-${IBrrow.id }.html" class="psell_twz">${IBrrow.borrowTitle
								}</a><i id="t_bao" class="tag tag_i tag_bao" tips="担保公司"></i>
						</h2>
						<p class="psell_distit">保障方式：100%保本保息</p>
						<!--参数 start-->
						<div class="sell_cs clearfix">
							<!--年化率 start-->
							<div class="cs_box cs_box1">
								<p class="cs_btit">年化收益率</p>
								<div class="cs_btxt clearfix">
									<div class="hcs_nhl">
										<span class="nhl_bumber"><fmt:formatNumber
												value="${IBrrow.annualRate }" pattern="0.0" /> </span>%
									</div>
									<div class="hcs_jiang">
										<span class="j_news"><a id="t_newer" class="tag"
											href="javascript:void(0)"
											tips="年化收益率<b style='color:red'>+0.4%</b>的新手奖励
<br />(自第一次投资起30天内可享有新手奖励)">+
												新手奖励</a> </span> <span class="j_vip"><a id="t_vips" class="tag"
											href="javascript:void(0)"
											tips="Vip1: 1万＜待收本金≤5万，  年化收益率<b style='color:red'>+0.40%</b><br />
Vip2: 5万＜待收本金≤10万，年化收益率<b style='color:red'>+0.8%</b><br />
Vip3: 待收本金＞10万，        年化收益率<b style='color:red'>+1.6%</b><br />
(新手奖励可与Vip奖励叠加)">+
												VIP奖励</a> </span>
									</div>
								</div>
							</div>
							<!--年化率 end-->
							<!--总额度 start-->
							<div class="cs_box cs_box2">
								<p class="cs_btit">总额度</p>
								<div class="cs_btxt clearfix">
									<p class="total_money">
										<span class="money_p"><fmt:formatNumber
												value='${IBrrow.borrowAmount }' pattern="#,###" /> </span>元
									</p>
								</div>
							</div>
							<!--总额度 end-->
							<!--投资期限 start-->
							<div class="cs_box cs_box3">
								<p class="cs_btit">投资期限</p>
								<div class="cs_btxt clearfix">
									<p class="total_time">
										<span class="t_timenum">${IBrrow.deadline }</span>
										<c:choose>
											<c:when test="${IBrrow.deadlineType==1 }">天</c:when>
											<c:when test="${IBrrow.deadlineType==2 }">月</c:when>
										</c:choose>
									</p>
								</div>
							</div>
							<!--投资期限 end-->
							<!--进度条 start-->
							<div class="pcb_progress">

								<div id="pbar${IBrrow.id }"
									lang="<fmt:formatNumber value='${IBrrow.progress*100 }'  pattern="#0.0" />"
									class="pbar1"></div>
							</div>
							<!--进度条 end-->
						</div>
						<!--参数 end-->
					</div>
					<!--左侧 end-->
					<!--右侧 start-->
					<div class="psell_other">
						<div class="ps_bg">
							<div class="ps_main">
								<c:choose>
									<c:when test="${IBrrow.borrowStatus==3 }">
										<!--显示 start-->
										<div class="psm_show">
											<div class="buys_box">
												<span class="bs_btit">还款方式：</span>
												<div class="bs_btxt">
													<p class="bs_bmoney">
														<c:choose>
															<c:when test="${IBrrow.repayType==1 }">一次性还款</c:when>
															<c:when test="${IBrrow.repayType==2 }">按月付息，到期还本</c:when>
															<c:when test="${IBrrow.repayType==3 }">等额本息</c:when>
														</c:choose>
													</p>
												</div>
											</div>
											<div class="buys_box">
												<span class="bs_btit">可投金额：</span>
												<div class="bs_btxt">
													<p class="bs_bmoney">
														<em class="money" id="mayCast${IBrrow.id }">${IBrrow.mayCast
															}</em> 元
													</p>
												</div>
											</div>
											<div class="buys_box">
												<span class="bs_btit">账户余额：</span>
												<div class="bs_btxt">
													<!--未登录 start-->
													<c:choose>
														<c:when test="${null==sessionScope.user}">
															<!--未登录 end-->
															<p class="nologin_money">
																<a href="loginIndex.html" class="money_login">登录</a>后查看余额
															</p>
														</c:when>
														<c:otherwise>
															<!--已经登录 start-->
															<p class="login_money">
																<em class="lo_money">${userAccount.usableAmount}</em> 元
															</p>
														</c:otherwise>
													</c:choose>

												</div>
											</div>
											<div class="buys_box">
												<form action="bfpay/investBorrow.html"
													id="invetForm${IBrrow.id }" method="post">
													<span class="bs_btit">投资金额：</span>
													<div class="bs_btxt">
														<input type="text" id="userInvt${IBrrow.id }"
															name="investAmount"
															onkeyup="this.value=this.value.replace(/\D/g,'')"
															onblur="chackInvestmentOK(this,'${IBrrow.id }','${userAccount.usableAmount}','${IBrrow.minInvestAmount}','${IBrrow.mayCast}')"
															class="btz_input"
															onpropertychange="calculateInterest('${IBrrow.id }','${IBrrow.repayType }','${IBrrow.annualRate }','${IBrrow.deadline}','${IBrrow.deadlineType }')"
															oninput="calculateInterest('${IBrrow.id }','${IBrrow.repayType }','${IBrrow.annualRate }','${IBrrow.deadline}','${IBrrow.deadlineType }')" />元
														<input type="hidden" name="borrowId" value="${IBrrow.id }"
															class="f_writxt" />
													</div>
												</form>
											</div>
											<div class="buys_box">
												<span class="bs_btit">投资收益：</span>
												<div class="bs_btxt">
													<p class="bsy_money">
														<em class="bsy_mnumber" id="interest${IBrrow.id }">0.00</em>
														元
													</p>
												</div>
											</div>
											<div class="buys_but">
												<input type="button"
													onclick="sumbForm('${IBrrow.id }','${userAccount.usableAmount}','${IBrrow.minInvestAmount}','${IBrrow.mayCast}')"
													class="buys_button" value="立即投标" />
											</div>
										</div>
										<!--显示 end-->
									</c:when>
									<c:when test="${IBrrow.borrowStatus==4 }">
										<!--满标复审 start-->
										<div class="buy_manbiao">
											<span class="bm_ico bm_ico4 tran"></span>
											<div class="bmb_cont">
												<ul>
													<li>还款方式：
														<c:choose>
															<c:when test="${IBrrow.repayType==1 }">一次性还款</c:when>
															<c:when test="${IBrrow.repayType==2 }">按月付息，到期还本</c:when>
															<c:when test="${IBrrow.repayType==3 }">等额本息</c:when>
														</c:choose>
													</li>
													<li>可投金额：￥0.00 元</li>
													<li>满标时间：<fmt:formatDate value="${IBrrow.fullTime}" pattern="yyyy-MM-dd HH:mm:ss" /></li>
													<li><div class="bico_biao bico_mbfs">
															<a href="borrow-${IBrrow.id }.html">满标复审</a>
														</div>
													</li>
												</ul>
											</div>
										</div>
										<!--满标复审 end-->
									</c:when>
									<c:when test="${IBrrow.borrowStatus==5 }">
										<!--还款中 start-->
										<div class="buy_manbiao">
											<span class="bm_ico bm_ico3 tran"></span>
											<div class="bmb_cont">
												<ul>
													<li>还款方式：
														<c:choose>
															<c:when test="${IBrrow.repayType==1 }">一次性还款</c:when>
															<c:when test="${IBrrow.repayType==2 }">按月付息，到期还本</c:when>
															<c:when test="${IBrrow.repayType==3 }">等额本息</c:when>
														</c:choose>
</li>
													<li>可投金额：￥0.00 元</li>
													<li>满标时间：<fmt:formatDate value="${IBrrow.fullTime}" pattern="yyyy-MM-dd HH:mm:ss" /></li>
													<li><div class="bico_biao bico_mbfs">
															<a href="borrow-${IBrrow.id }.html">还款中</a>
														</div>
													</li>
												</ul>
											</div>
										</div>
										<!--还款中 end-->
									</c:when>
								</c:choose>
							</div>
						</div>
					</div>
					<!--右侧 end-->
				</div>
				<!--热售 end-->
			</c:forEach>
		</c:when>
	</c:choose>

	<!--more start-->
	<div class="pro_more">
		<a href="borrowList.html">更多 &gt;</a>
	</div>
	<!--more end-->

	<!--战略合作，新闻 start-->
	<div class="coop_news">
		<!--战略合作 start-->
		<div class="c_coop">
			<div class="box_tit">
				<span class="box_twz">战略合作</span>
			</div>
			<div class="c_cooptxt" id="links"></div>
		</div>
		<!--战略合作 end-->
		<!--新闻 start-->
		<div class="c_news">
			<div class="box_tit">
				<span class="box_twz">新闻动态</span> <span class="box_tmore"><a
					href="newsIndex.html">更多 &gt;</a> </span>
			</div>
			<div class="notice_txt notice_txt2" id="indexNew"></div>
		</div>
		<!--新闻 end-->
	</div>
	<!--战略合作，新闻合作 end-->
	<div class="space30"></div>
	<!--投标提示 start-->
	<div class="t_question">
		<div class="t_quetit clearfix">
			<i class="tq_ico"></i>
			<div class="tq_des" id="showMsg"></div>
		</div>
		<div class="t_quetxt clearfix">
			<a href="javascript:window.location.reload();" class="t_tbok">投标完成</a>
			<a href="helpShow-4.html" class="t_twhat">投标遇到问题</a>
		</div>
	</div>
	<!--投标提示 end-->

	<!--foot start-->
	<jsp:include page="/WEB-INF/view/front/comm/footer.jsp"></jsp:include>
	<!--foot end-->
	<script type="text/javascript" src="js/page.js"></script>
	<!--进度条-->
	<script type="text/javascript" src="js/raphael.2.1.0.min.js"></script>
	<script type="text/javascript" src="js/justgage.1.0.1.min.js"></script>
	<!--tips-->
	<script type="text/javascript" src="js/tips.js"></script>
	<script type="text/javascript" src="js/slideshow.js"></script>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		//幻灯片
		$('.flexslider').flexslider({
			directionNav : true,
			pauseOnAction : false
		});
		//tips提示框
		$("#t_bao").manhua_hoverTips({
			position : "t"
		});//改变了显示的位置参数
		$("#t_ya").manhua_hoverTips({
			position : "t"
		});//改变了显示的位置参数
		$("#t_newer").manhua_hoverTips({
			position : "t"
		});//改变了显示的位置参数
		$("#t_vips").manhua_hoverTips({
			position : "t"
		});//改变了显示的位置参数
	});
	/**
	 * 投标进度条
	 */
	$(".pbar1").each(function() {
		var bid = $(this).attr("id");
		var jd = $(this).attr("lang");
		new JustGage({
			id : bid,
			value : jd,
			min : 0,
			max : 100,
			title : " ",
			showMinMax : false,
			label : "投资比例"
		});
	});

	/*异步加载合作伙伴*/
	$.lionPost("indexLinks.html", {}, function(data) {
		$("#links").html(data);
	});
	/*异步加载新闻动态*/
	$.lionPost("indexNews.html", {}, function(data) {
		$("#indexNew").html(data);
	});
	//   
	/**
	 * 验证投资金额
	 */
	function chackInvestmentOK(this_, brrowId, Amount, minAmount, brrowAmount) {
		var investAm = $(this_).val();
		var reg = /^\d+$/;
		if (investAm == '') {
			return false;
		}
		if ('${user}' == '') {
			alert("请先登录后再投标");
			return false;
		}
		if (reg.test(investAm) == false) {
			alert("投标金额格式不正确");
			return false;
		}
		var userInvestAm = parseFloat(investAm);
		var userHasAmount = parseFloat(Amount);
		if (userInvestAm > userHasAmount) {
			alert("可用余额不足");
			return false;
		}
		var minInvestAmount = parseFloat(minAmount);
		if (userInvestAm < minInvestAmount) {
			alert("投标金额小于最小投标金额" + minAmount + "元");
			return false;
		}
		var brrowHasAmount = parseFloat(brrowAmount);
		if (userInvestAm > brrowHasAmount) {
			alert("投标金额大于剩余可投标金额" + brrowAmount + "元");
			return false;
		}
	};
	/**
	 * 快速投资
	 */
	function sumbForm(brrowId, Amount, minAmount, brrowAmount) {
		var investValue = $("#userInvt" + brrowId).val();
		var reg = /^\d+$/;
		if ('${user}' == '') {
			alert("请先登录后再投标");
			return false;
		}
		if (reg.test(investValue) == false) {
			alert("投标金额格式不正确");
			return false;
		}
		var userInvestAm = parseFloat(investValue);
		var userHasAmount = parseFloat(Amount);
		if (userInvestAm > userHasAmount) {
			alert("余额不足");
			return false;
		}
		var minInvestAmount = parseFloat(minAmount);
		if (userInvestAm < minInvestAmount) {
			alert("投标金额小于最小投标金额" + minAmount + "元");
			return false;
		}
		var brrowHasAmount = parseFloat(brrowAmount);
		if (userInvestAm > brrowHasAmount) {
			alert("投标金额大于剩余可投标金额" + brrowAmount + "元");
			return false;
		}
		/*投资*/
		showTips("投标处理中，请耐性等待...");
		$.lionPost("bfpay/investAjaxBorrow.html", {
			"investAmount" : investValue,
			"borrowId" : brrowId
		}, function(data) {
			$("#showMsg").html(data.info);
		});

	};

	function showTips(showMsg) {
		$("#showMsg").html(showMsg);
		var t_issue = $.layer({
			type : 1,
			title : '投标提示',
			//shadeClose: true, 开启点击遮罩关闭层
			area : [ '480px', '250px' ],
			border : [ 0, 0.5, '#666' ],
			offset : [ ($(window).height() - 250) / 2 + 'px', '' ],
			page : {
				dom : '.t_question'
			}
		});
		//自设关闭
		$('.t_quetxt .t_tbok').on('click', function() {
			layer.close(t_issue);
		});
	}

	function calculateInterest(borrowId, repayType, annualRate, deadline,
			deadlineType) {

		var amount = $("#userInvt" + borrowId).val();
		var regex = /^[1-9]+[0-9]*]*$/;

		if (!regex.test(amount)) {
			return;
		}
		if (amount < 50) {
			$("#interest").html("0.00");
			return;
		}
		$.lionPost("calculateInterest.html", {
			"amount" : amount,
			"repayType" : repayType,
			"annualRate" : annualRate,
			"month" : deadline,
			"dayType" : deadlineType
		}, function(data) {
			if (data.code != -1) {
				$("#interest" + borrowId).html(data.interestAmount);
			}
		});
	}

	$(function() {
		$("#aIndex").addClass("nav_aclick");
		$("input[name='investAmount']").val("");
	});
</script>
</html>
