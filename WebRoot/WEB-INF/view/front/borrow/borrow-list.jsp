<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>【理财投资】_P2P互联网金融理财平台_P2P理财</title>
<meta name="keywords" content="互联网金融，P2P，房产抵押，车产抵押，本息担保，P2P"/>
<meta name="description" content="P2P（p2p.com）_为深圳市P2P金融信息服务有限公司线上理财平台！打造专业的小微金融服务体系，让更多的人从小微金融中受益！"/>
<link rel="stylesheet" href="css/common.css" type="text/css" />
<link rel="stylesheet" href="css/page.css" type="text/css" />
</head>

<body>
	<!--header start-->
	<jsp:include page="/WEB-INF/view/front/comm/header.jsp"></jsp:include>
	<!--header end-->

	<!--page-->
<div class="p_page15">
   <!--当前位置 start-->
   <div class="p1000 p_position">
       <a href="${path }">首页</a> > 理财投资
   </div>
   <!--当前位置 end-->
   <!--筛选 start-->
   <div class="p1000 pbox clearfix">
   	  <form action="borrowList.html" id="searchForm">
   	  	  <input id="curPage" name="curPage" type="hidden" value="1"  />
   	  	  <input id="repayType" name="repayType" type="hidden" value="${borrowDto.repayType }" />
   	  	  <input id="borrowStatus" name="borrowStatus" type="hidden" value="${borrowDto.borrowStatus }" />
   	  	  <input id="deadline" name="deadline" type="hidden" value="${borrowDto.deadline }" />
	      <!--条件 start-->
	      <div class="pl_sai">
	         <h2 class="pl_sh2">筛选投资项目</h2>
	         <!--1 start-->
	      <div class="pl_sbox">
	            <span class="sbox_tit">借款期限：</span>
	            <div class="sbox_txt">
	               <a href="javascript:void(0);" lang=""  class="deadlineCls sb_tb">全部</a>
	               <a href="javascript:void(0);" lang="1" class="deadlineCls sb_tb">1个月</a>
	               <a href="javascript:void(0);" lang="2" class="deadlineCls sb_tb">3个月</a>
	               <a href="javascript:void(0);" lang="3" class="deadlineCls sb_tb">6个月</a>
	               <a href="javascript:void(0);" lang="4" class="deadlineCls sb_tb">12个月及以上</a>
	            </div>
	         </div>
	         <!--1 end-->
	         <!--2 start-->
	         <div class="pl_sbox">
	            <span class="sbox_tit">还款方式：</span>
	            <div class="sbox_txt">             
	              <a href="javascript:void(0);" lang="" class="repayTypeCls sb_tb">全部</a>
	               <a href="javascript:void(0);" lang="1"  class="repayTypeCls sb_tb">一次还清</a>
	               <a href="javascript:void(0);" lang="2" class="repayTypeCls sb_tb">按月还息，到期还本</a>
	               <a href="javascript:void(0);" lang="3" class="repayTypeCls sb_tb">等额本息</a>
	            </div>
	         </div>
	         <!--2 end-->
	        <!--3 start-->
         <div class="pl_sbox">                        
            <span class="sbox_tit">项目状态：</span>
            <div class="sbox_txt">
               <a href="javascript:void(0);" lang="" class="borrowStatusCls sb_tb">全部</a>
               <a href="javascript:void(0);" lang="2" class="borrowStatusCls sb_tb">即将发布</a>
               <a href="javascript:void(0);" lang="3" class="borrowStatusCls sb_tb">投标中</a>
               <a href="javascript:void(0);" lang="4" class="borrowStatusCls sb_tb">已满标</a>
               <a href="javascript:void(0);" lang="5" class="borrowStatusCls sb_tb">还款中</a>
               <a href="javascript:void(0);" lang="6" class="borrowStatusCls sb_tb">已结束</a>
            </div>
         </div>
         <!--3 end-->
	        
	      </div>
	      <!--条件 end-->
      </form>
     
   </div>
   <!--筛选 end-->
   <div class="space20"></div>
   <!--列表 start-->
   <div class="p1000 pbox clearfix">
      <!--title start-->
      <div class="l_pro clearfix">
          <span class="l_ptitle">理财投资</span>
          <div class="line1"></div>
      </div>
      <!--title end-->
      <!--tit start-->
      <div class="l_ptit">
         <span class="l_pt_t l_pt_t1">投资产品</span>                                                                         
         <span class="l_pt_t l_pt_t2">年化利率</span>
         <span class="l_pt_t l_pt_t3">金额(元)</span>
         <span class="l_pt_t l_pt_t4">期限</span>
         <span class="l_pt_t l_pt_t5">进度</span>
         <span class="l_pt_t l_pt_t6">状态</span>
     </div>
      <!--tit end-->
      <!--list start-->
      <div class="l_plist">
      
      	 <c:forEach items="${pageBean }" var="bean" varStatus="st">
      	  <!--box start-->
         <div class="pi_box">
            <div class="pi_bn pi_bn1">
               <div class="p_tit">
                   <span class="title"><a href="borrow-${bean.id }.html">${bean.borrowTitle }</a></span>
               	   <i id="t_bao" class="tag tag_i tag_bao" tips="担保公司"></i>
               </div>
            </div>
            <div class="pi_bn pi_bn2">
                <div class="pip_nhl">
                    <span class="p_nhl"><i class="p_nhnumber"><fmt:formatNumber value="${bean.annualRate }" pattern="0.0" /></i><i class="p_nhfu">%</i></span>
                </div>
                <div class="pb_jiang">
                     <span class="j_news"><a id="t_newer" class="tag"  href="javascript:void(0)" tips="年化收益率<b style='color:red'>+0.4%</b>的新手奖励
<br />（自第一次投资起30天内可享有新手奖励）">+ 新手奖励</a></span>
                        <span class="j_vip"><a id="t_vips" class="tag"  href="javascript:void(0)" tips="Vip1: 1万＜待收本金≤5万，  年化收益率<b style='color:red'>+0.40%</b><br />
Vip2: 5万＜待收本金≤10万，年化收益率<b style='color:red'>+0.8%</b><br />
Vip3: 待收本金＞10万，        年化收益率<b style='color:red'>+1.6%</b><br />
(新手奖励可与Vip奖励叠加)">+ VIP奖励</a></span>
                </div>
            </div>
            <div class="pi_bn pi_bn3">
               <span class="p_money"><fmt:formatNumber pattern="#,###" value="${bean.borrowAmount }"></fmt:formatNumber> 元</span>
            </div>
            <div class="pi_bn pi_bn4">
                <span class="p_month"><i class="p_mon">${bean.deadline }</i><i class="p_yue"><c:choose>
                	<c:when test="${bean.deadlineType==1 }">天</c:when>
                	<c:otherwise>个月</c:otherwise>
                </c:choose></i></span>
            </div>
            <div class="pi_bn pi_bn5">
                <div class="progress">
                   <div class="progress_bg"><div class="pro_width" style="width:${bean.progress*100 }%"></div></div>
                   <div class="pro_percent"><fmt:formatNumber pattern="0.00" value="${bean.progress*100  }" />%</div>
              </div>
            </div>
            <div class="pi_bn pi_bn6">
                  <c:choose>
                	<c:when test="${bean.borrowStatus==2 }"><div class="pl_but pl_but${bean.borrowStatus}"><a href="borrow-${bean.id }.html">即将发布</a></div></c:when>
                	<c:when test="${bean.borrowStatus==3 }"><div class="pl_but pl_but${bean.borrowStatus}"><a href="borrow-${bean.id }.html">立即投标</a></div></c:when>
                	<c:when test="${bean.borrowStatus==4 }"><p class="pl_but pl_but${bean.borrowStatus}"><a href="borrow-${bean.id }.html">已满标</a></p></c:when>
                	<c:when test="${bean.borrowStatus==5 }"><div class="pl_but pl_but${bean.borrowStatus}"><a href="borrow-${bean.id }.html">还款中</a></div></c:when>
                	<c:when test="${bean.borrowStatus==6 }"><div class="pl_but pl_but${bean.borrowStatus}"><a href="borrow-${bean.id }.html">已还清</a></div></c:when>
                </c:choose>
            </div>
         </div>
         <!--box end-->
         
         </c:forEach>
        
      </div>
      <!--list end-->
     <my:frontPager  curPage="${page.page }"
					pageSize="${page.limit }" totalCount="${page.totalCount }" />
  </div>
   <!--列表 end-->
</div>
<!--page-->


	<!--header start-->
	<jsp:include page="/WEB-INF/view/front/comm/footer.jsp"></jsp:include>
	<!--header end-->
	
</body>
<script type="text/javascript">
$(function(){
	$("#borrowList").addClass("nav_aclick");
	
	$(".deadlineCls").click(function(){
		$(".deadlineCls").removeClass("sb_tb_cilik");
		$(this).addClass("sb_tb_cilik");
		$("#deadline").val($(this).attr("lang"));
		$("#searchForm").submit();
	});
	
	$(".repayTypeCls").click(function(){
		$(".repayTypeCls").removeClass("sb_tb_cilik");
		$(this).addClass("sb_tb_cilik");
		$("#repayType").val($(this).attr("lang"));
		$("#searchForm").submit();
	});
	
	$(".borrowStatusCls").click(function(){
		$(".borrowStatusCls").removeClass("sb_tb_cilik");
		$(this).addClass("sb_tb_cilik");
		$("#borrowStatus").val($(this).attr("lang"));
		$("#searchForm").submit();
	});
	
	$(".repayTypeCls[lang='${borrowDto.repayType}']").addClass("sb_tb_cilik");
	$(".deadlineCls[lang='${borrowDto.deadline}']").addClass("sb_tb_cilik");
	$(".borrowStatusCls[lang='${borrowDto.borrowStatus}']").addClass("sb_tb_cilik");

});
</script>
<!--tips-->
<script type="text/javascript" src="js/tips.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	//tips提示框
	$("#t_bao").manhua_hoverTips({position : "t"});//改变了显示的位置参数
	$("#t_ya").manhua_hoverTips({position : "t"});//改变了显示的位置参数
	$("#t_newer").manhua_hoverTips({position : "t"});//改变了显示的位置参数
	$("#t_vips").manhua_hoverTips({position : "t"});//改变了显示的位置参数
});
</script>
</html>
