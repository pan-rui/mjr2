<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="${path }">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>P2P - 投资详细</title>
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
             <span class="u_titwz">投资详细</span>
             <div class="line1"></div>
          </div>
          <!--title end-->
          <!--账户 start-->
          <div class="u_txt">
              <div class="tzhz_cont">
                  <!--已购产品 start-->
                  <div class="tc_up">
                      <h2 class="tc_uptit">产品详细</h2>
                      <div class="tc_stxt">
                          <h2 class="tc_sh2">产品名称：${investdto.borrowTitle}</h2>
                          <ul class="tc_stmain">
                             <li>投资金额：￥${investdto.investAmount }元</li>
                             <li>投资时间：<fmt:formatDate value="${investdto.investTime}" pattern="yyyy-MM-dd"/></li>
                             <li class="tc_stli">投资期限：${investdto.deadline }天</li>
                             <li><c:if test="${investdto.repayStatus ==1 }">应收本金</c:if><c:if test="${investdto.repayStatus ==2 }">已收本金</c:if>：￥${investdto.capitalAmount}元</li>
                             <li>年化收益率：${investdto.annualRate}%</li>
                             <li class="tc_stli">总收益：￥${investdto.profitAmount}元</li>
                             
                             <li>分配方式：
                             <c:choose>
                             	<c:when test="${investdto.repayType==1}">一次性还款</c:when>
                             	<c:when test="${investdto.repayType==2}">按月付息，到期还本</c:when>
                             	<c:when test="${investdto.repayType==3}">等额本息</c:when>	
                             </c:choose>
                             </li>
                             <li class="tc_stli"><a href="javascript:selectxy('${investdto.id}')" class="tc_shetong">查看合同</a></li>
                          </ul>
                      </div>
                  </div>
                  <!--已购产品 end-->
                  <!--我的投资单条详细 start-->
                  <div class="invmain_cont">
                      <div class="inv_tit">                                                    
                                 <span class="uinv_p uinv_p1">期数</span>
                                 <span class="uinv_p uinv_p2">还款时间</span>
                                 <span class="uinv_p uinv_p3">应收本金</span>
                                 <span class="uinv_p uinv_p4">应收收益</span>
                                 <span class="uinv_p uinv_p5">管理费</span>
                                 <span class="uinv_p uinv_p6">是否逾期</span>
                                 <span class="uinv_p uinv_p7">逾期罚息</span>
                                 <span class="uinv_p uinv_p8">收益</span>
                                 <span class="uinv_p uinv_p9">还款状态</span>
                    </div>
                              <div class="inv_cont">
                              
                              
                              <c:forEach items="${pageBean}" var="dto">
                              	 <!--box start-->
                                  <div class="inv_box">
                                      <div class="inv_b inv_b1">第${dto.numOfPeriods }期</div>
                                      <div class="inv_b inv_b2"><span class=""><c:if test="${dto.repayStatus==2 }"><fmt:formatDate value="${dto.realRepayTime }" pattern="yyyy-MM-dd"/></c:if><c:if test="${dto.repayStatus==1 }"><fmt:formatDate value="${dto.repayDate }" pattern="yyyy-MM-dd"/></c:if></span></div>
                                      <div class="inv_b inv_b3">${dto.capitalAmount }</div>
                                      <div class="inv_b inv_b4">+${dto.profitAmount }</div>
                                      <div class="inv_b inv_b5">-${dto.feeAmount }</div>
                                      <div class="inv_b inv_b6">否</div>
                                      <div class="inv_b inv_b7">-</div>
                                      <div class="inv_b inv_b8">${dto.profitAmount - dto.feeAmount}</div>
                                      <div class="inv_b inv_b9">
                                      <c:choose>
                                      	<c:when test="${dto.repayStatus == 1 }"><span class="inv_swait">待还款</span></c:when>
                                      	<c:when test="${dto.repayStatus == 2 }"><span class="inv_salready">已还款</span></c:when>
                                      </c:choose>
                                         
                                      </div>
                                  </div>
                                  <!--box end-->
                              </c:forEach>
                                 
                                  
                                  
                              </div>
                  </div>
                  <!--我的投资单挑详细 end-->
              </div>
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
<!--
	$(function() {
		$("#myCenter").addClass("nav_aclick");
		$("#center3").addClass("nav_aclik");
	});
//-->

//借款协议（范本）
   function selectxy(id){
    	   loan_box = $.layer({
    			type: 1,
    			title: '借款协议',
    			shadeClose: true, //开启点击遮罩关闭层
    			area: ['880px','650px'],
    			border : [0, 0.5, '#666'],
    			offset: [($(window).height() - 650)/2+'px', ''],
    			page: {url : 'center/selectAgreementMsg.html?id='+id}
    		});
    	//自设关闭
    	
    };
    function closeLoan(){
 		layer.close(loan_box);
 		
 	}
</script>
</html>
