<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>

             <!--biao start-->
             <div class="tblist clearfix">                                                                                  	 	   
                <span class="tbtit tbtit1">投资人</span>
                <span class="tbtit tbtit2">投标时间</span>
                <span class="tbtit tbtit3">投标金额</span>
                <span class="tbtit tbtit4">投标类型</span>
             </div>
             <!--biao end-->
           
             <!--txt start-->
             <div class="tblist_txt">
             	<c:forEach items="${pageBean }" var="bean" varStatus="st">
	                <!--box start-->
	                <div class="tblist_box clearfix">
	                    <div class="list_b list_b1"><my:replaceStar content="${bean.userName }" start="4" len="6" /></div>
	                    <div class="list_b list_b2"><fmt:formatDate value="${bean.investTime }" pattern="yyyy-MM-dd HH:mm:ss" /></div>
	                    <div class="list_b list_b3">￥${bean.realAmount }</div>
	                    <div class="list_b list_b4">成功</div>
	                </div>
	                <!--box end-->
               </c:forEach>
             </div>
             <!--txt end-->
             <!--page start-->
            <my:frontPager  curPage="${page.page }" 
					pageSize="${page.limit }" totalCount="${page.totalCount }" funMethod="turnInvestPage" />
             <!--page end-->
<script type="text/javascript">
<!--
	$(function(){
		$("#investCount").text('${page.totalCount }');
	});
//-->
</script>
