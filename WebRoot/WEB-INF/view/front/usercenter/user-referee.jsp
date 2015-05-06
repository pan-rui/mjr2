<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="${path }">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>P2P - 推荐有礼</title>
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
             <span class="u_titwz">我的推荐</span>
             <div class="line1"></div>
          </div>
          <!--title end-->
           <!--账户 start-->
          <div class="u_txt">
              <!--推荐 start-->
              <div class="recommend">
                  <div class="ms_t" id="ms_tfx" name="ms_tfx">
			               <span class="ms_t_tit">您的邀请链接：</span>
			               <input type="text" class="ms_t_text" id="abcd" value="http://www.p2p.com/regIndex.html?tjr=${tel}">
			               <input type="button" class="ms_t_submit" id="fz" value="复制链接">
			      </div>
                  <div class="stj_img">
			          <div class="stj_img01"></div>
                      <div class="stj_img02"><img style="width:150px;" src="http://qr.liantu.com/api.php?text=www.p2p.com/regIndex.html?tjr=${tel}"></div>
			      </div>
                  <div class="ms_shalinks">
			          <div class="bshare-custom icon-medium"><div class="bsPromo bsPromo2"></div><a title="分享到" href="http://www.bShare.cn/" id="bshare-shareto" class="bshare-more">分享到</a><a title="分享到QQ空间" class="bshare-qzone"></a><a title="分享到新浪微博" class="bshare-sinaminiblog"></a><a title="分享到微信" class="bshare-weixin" href="javascript:void(0);"></a><a title="分享到人人网" class="bshare-renren"></a><a title="分享到腾讯微博" class="bshare-qqmb"></a><a title="分享到豆瓣" class="bshare-douban" href="javascript:void(0);"></a><a title="分享到网易微博" class="bshare-neteasemb"></a><a title="更多平台" class="bshare-more bshare-more-icon more-style-addthis"></a></div><script type="text/javascript" charset="utf-8" src="http://static.bshare.cn/b/buttonLite.js#style=-1&amp;uuid=&amp;pophcol=2&amp;lang=zh"></script><script type="text/javascript" charset="utf-8" src="http://static.bshare.cn/b/bshareC0.js"></script>
			      </div>
                  <!--title start-->
                  <div class="u_tit">
                     <span class="u_titwz">推荐统计</span>
                     <div class="line1"></div>
                  </div>
                  <!--title end-->
                  <!--数据 start--><%--
                  <div class="ms_sha_date">
			                  <ul>
			                     <li class="ms_sha_d1">当月好友投资总额：￥0.00</li>
			                     <li class="ms_sha_d2">当月推荐获益：￥0.00</li>
			                     <li class="ms_sha_d3">当月推荐投资人数：0</li>
			                     <li class="ms_sha_d1">累计好友投资总额：￥0.00</li>
			                     <li class="ms_sha_d2">累计推荐获益：￥0.00</li>
			                     <li class="ms_sha_d3">推荐投资人总数：0</li>
			                  </ul>
			      </div>
                  --%><!--数据 end-->
                  <!--列表 start-->
                  <div class="ms_t_friend">
			               <!--标 start-->
			               <div class="ms_tfri_tit">
			                  <span class="m_fri_spa m_fri_spa1">序号</span>
			                  <span class="m_fri_spa m_fri_spa2">邀请的好友</span>
			                  <span class="m_fri_spa m_fri_spa3">注册时间</span>
			                  <%--<span class="m_fri_spa m_fri_spa4">投资时间</span>
			               --%></div>
			               <!--标 end-->
			               <!--名单 start-->
			               <div class="ms_tfri_txt">
			               <%int i=1; %>
			               <c:forEach items="${pagebean }" var="dto">
			                   <!--循环 start-->
			                   <div class="tfri_box" style="text-align: center;">                             
			                      <span class="tfri_b tfri_b1"><%=i++ %></span>
			                      <span class="tfri_b tfri_b2">${dto.cellPhone }</span>
			                      <span class="tfri_b tfri_b3"><fmt:formatDate value="${dto.createTime }" pattern="yyyy-MM-dd"/></span>
			                      
			                   </div>
			                   <!--循环 end-->
			                  </c:forEach>
			                  </div>
			               <!--名单 end-->
			               <my:frontPager curPage="${page.page }"
					pageSize="${page.limit }" totalCount="${page.totalCount }" />
			           </div>
                  <!--列表 end-->
              </div>
              <!--推荐 end-->
          </div>
          <!--账户 end-->
      </div>
      <!--main end-->
   </div>
   <!--用户中心 end-->
</div>
<!--page end-->
							<form id="searchForm" action="center/selectReferee.html">
 								<input id="curPage" name="curPage" type="hidden" value="1" />
 							</form>

	<!--header start-->
	<jsp:include page="/WEB-INF/view/front/comm/footer.jsp"></jsp:include>
	<!--header end-->

	
</body>
<script type="text/javascript" src="js/jquery.zclip.js"></script>
<script type="text/javascript">
<!--
	$(function() {
		$("#myCenter").addClass("nav_aclick");
		$("#center9").addClass("nav_aclik");
	});
//-->

//复制代码
     $('#fz').zclip({ 
         path: "js/ZeroClipboard.swf", 
         copy: function(){ 
             return $('#abcd').val();
             }
     }); 
</script>
</html>
