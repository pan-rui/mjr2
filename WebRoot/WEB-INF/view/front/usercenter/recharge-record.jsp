<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="${path }">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>P2P - 充值提现</title>
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
          <!--导航 start-->
          <jsp:include page="/WEB-INF/view/front/comm/user-left.jsp"></jsp:include>
          <!--导航 end-->
  
      <!--侧边 end-->
      <!--main start-->
      <div class="u_main">
          <!--title start-->
          <div class="u_tit">
             <span class="u_titwz">充值提现</span>
             <div class="line1"></div>
          </div>
          <!--title end-->
          <!--账户 start-->
          <div class="u_txt">
              <!--充值提现 start-->
              <div class="m_user">
                  <div class="ct_up clearfix">
                      <div class="up_last"><p class="up_lastit">可用余额</p></div>
                      <div class="up_lastmoney"><span class="lastm_number">${taccount.usableAmount}</span>元</div>
                      <div class="up_button">
                          <a href="bfpay/readyRecharge.html" class="up_bcox up_bcz"><i class="up_czico tran"></i>充值</a>
                          <a href="center/userWithdraw.html" class="up_bcox up_btx"><i class="up_txico tran"></i>提现</a>
                      </div>
                  </div>
                  <!--tab start-->
                  <div class="tab_cont">
                      <div class="tabtit">
                         <a href="center/selectRcgRcdList.html" class="ctab_a ctab_ahover">充值记录</a>
                         <a href="center/selectWithdraw.html" class="ctab_a">提现记录</a>
                      </div>
                      <!--充值记录-->
                      <div class="tabtxt" style="display:block;">
                      <form id="searchForm" action="center/selectRcgRcdList.html">
 							<input id="curPage" name="curPage" type="hidden" value="1" />
 							<input id="aChack" name ="achacked" type="hidden" value="0" />
							<input id="jll" name="days" type="hidden" value="0">
                          <div class="tab_search">
                             <a class="tab_sbox tran" href="javascript:aa(0,0)">全部</a>
                             <a class="tab_sbox tran" href="javascript:aa(1,1)">今天</a>
                             <a class="tab_sbox tran" href="javascript:aa(7,2)" >本周</a>
                             <a class="tab_sbox tran" href="javascript:aa(30,3)">最近一个月</a>
                             <a class="tab_sbox tran" href="javascript:aa(90,4)">最近三个月</a>
                          </div>
                      </form>
                          <div class="tabm_tit">                                                                      
                              <span class="tabm_t tabm_t1">序号</span>
                              <span class="tabm_t tabm_t2">充值时间</span>
                              
                              <span class="tabm_t tabm_t4">充值金额（元）</span>
                              <span class="tabm_t tabm_t5">状态</span>
                          </div>
                          <!--内容 start-->
                          <div class="tabm_txt">
                              <!--box start--><%int i=1; %>
                              <c:forEach items="${pageBean}" var="bean" varStatus="st">
                              <div class="tabm_box">
                                  <div class="m_btit m_btit1"><%=i++	 %></div>
                                  <div class="m_btit m_btit2"><fmt:formatDate value="${bean.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></div>
                                  
                                  <div class="m_btit m_btit4">${bean.rechargeAmount }</div>
                                  <div class="m_btit m_btit5"><c:choose>
						<c:when test="${bean.result ==0}">失败</c:when>
						<c:when test="${bean.result ==1}"><span class="m_state m_sok">成功</span></c:when>
						
					</c:choose></div>
                              </div>
                              </c:forEach>
                              <!--box end-->
                              
                              <!--page start-->
                              <div class="p_page"> <my:frontPager curPage="${page.page }"
					pageSize="${page.limit }" totalCount="${page.totalCount }" /></div>
                              <!--page end-->
                          </div>
                          <!--内容 start-->
                      </div>
                      <!--充值记录-->
                      
                  </div>
                  <!--tab end-->
              </div>
              <!--充值提现 end-->
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

	
</body>
<script type="text/javascript">
	$(function() {
		$("#myCenter").addClass("nav_aclick");
		$("#center2").addClass("nav_aclik");
		if('${aChacked}'==""){
			$(".tab_search a:eq(0)").addClass("tab_ahover");
		}else{
			$(".tab_search a:eq("+'${aChacked}'+")").addClass("tab_ahover");
		}
	});
	function aa(jl,sxIndex){//css : tab_ahover
		$("#aChack").val(sxIndex);
		$("#jll").val(jl);
		$("#searchForm").submit();
	}
	
</script>
</html>
