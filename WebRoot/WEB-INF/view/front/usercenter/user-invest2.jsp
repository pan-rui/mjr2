<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="${path }">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>P2P - 我的投资</title>
<link rel="stylesheet" href="css/common.css" type="text/css" />
<link rel="stylesheet" href="css/page.css" type="text/css" />
<link type="text/css" href="css/jquery-ui-1.8.17.custom.css" rel="stylesheet" />
     <link type="text/css" href="css/jquery-ui-timepicker-addon.css" rel="stylesheet" />
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
             <span class="u_titwz">我的投资</span>
             <div class="line1"></div>
          </div>
          <!--title end-->
          <!--账户 start-->
          <div class="u_txt">
              <!--我的投资 start-->
              <div class="invest">
                  
                  <!--选项卡 start-->
                  <div class="invest_tab">
                      <div class="invest_tit">
                          <a href="center/selectInvestList.html" class="it_tit">投标中的借款</a>
                          <a href="center/selectInvestListforhsz.html" class="it_tit it_ahover">回收中的借款</a>
                          <a href="center/selectInvestListforyhs.html" class="it_tit">已回收的借款</a>
                      </div>
                      
                      <!--回收中的借款 start-->
                      <div class="tz_inv" style="display:block;">
                          <!--搜索 start-->
                          <form id="searchForm" action="center/selectInvestListforhsz.html">
 							<input id="curPage" name="curPage" type="hidden" value="1" />
                          <div class="i_serch">
                              <span class="is_stit">时间：</span>
                             <input class="is_stext" type="text" name="investTimeup" onClick="WdatePicker()">
                              <span class="is_space">到</span>
                              <input type="text" name="investTimeend" class="is_stext" onClick="WdatePicker()"/>
                              <input type="button" value="查询" class="is_submit" onclick="queryForm()" />
                          </div>
                   </form>
                          <!--搜索 end-->
                          <div class="pinv_tit">                                                   
                             <span class="puinv_p puinv_p1">标题</span>
                             <span class="puinv_p puinv_p2">投标金额</span>
                             <span class="puinv_p puinv_p3">年华率</span>
                             <span class="puinv_p puinv_p4">期限</span>
                             <span class="puinv_p puinv_p5">操作</span>
                          </div>
                          <div class="pinv_cont">
                          
                          
                          
                          <c:forEach items="${pageBean}" var="bean" varStatus="st">
                              <!--box start-->
                              <div class="pinv_box">
                                  <div class="pinv_b pinv_b1"><a href="borrow-${bean.borrowId}.html" target="_blank">${bean.borrowTitle}</a></div>
                                  <div class="pinv_b pinv_b2">${bean.realAmount }</div>
                                  <div class="pinv_b pinv_b3">${bean.annualRate }%</div>
                                  <div class="pinv_b pinv_b4">${bean.deadline }
                                  <c:choose>
                                  	<c:when test="${bean.deadlineType ==1 }">天</c:when>	
                                  	<c:when test="${bean.deadlineType ==2 }">个月</c:when>	
                                  </c:choose></div>
                                  <div class="pinv_b pinv_b5"><a href="center/selectInvestDeatil.html?id=${bean.id}" class="b5_descipt">详情</a><a href="javascript:selectxy('${bean.id}')" class="b5_xieyi">协议</a></div>
                              </div>
                              <!--box end-->
                          </c:forEach>  
                            
                              
                              
                          </div>
                          <!--page start-->
                          <div class="p_page"><my:frontPager curPage="${page.page }"
					pageSize="${page.limit }" totalCount="${page.totalCount }" /></div>
                          <!--page end-->
                      </div>
                      <!--回收中的借款 end-->
                     
                  </div>
                  <!--选项卡 end-->
              </div>
              <!--我的投资 start-->
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
<script type="text/javascript" src="${path }js/plugins/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript">
<!--
	$(function() {
		$("#myCenter").addClass("nav_aclick");
		$("#center3").addClass("nav_aclik");
	});
//-->
<%--
$(function () {
        $(".is_stext").datetimepicker({
            //showOn: "button",
            //buttonImage: "./css/images/icon_calendar.gif",
            //buttonImageOnly: true,
            showSecond: true,
            timeFormat: '',
            stepHour: 1,
            stepMinute: 1,
            stepSecond: 1
        });
    });--%>
   function queryForm(){
	   $("#searchForm").submit();
   }
   
 //借款协议（范本）
   function selectxy(id){
    	   loan_box = $.layer({
    			type: 1,
    			title: '借款协议（范本）',
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
