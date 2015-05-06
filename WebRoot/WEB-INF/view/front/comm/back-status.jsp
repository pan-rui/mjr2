<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="${path }">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>P2P </title>
<link rel="stylesheet" href="css/common.css" type="text/css" />
<link rel="stylesheet" href="css/page.css" type="text/css" />
<link rel="stylesheet" href="css/validform.css" type="text/css"></link>
</head>

<body>
	<!--header start-->
	<jsp:include page="/WEB-INF/view/front/comm/header.jsp"></jsp:include>
	<!--header end-->

	<!--page start-->
<div class="p_page20">
    <!--当前位置 start-->
   <div class="p1000 p_position">
       <i class="p_posico"></i><span class="p_postxt"><a href="${path}">首页</a> > ${tipTitle }</span>
   </div>
   <!--当前位置 end-->
   <!--成功 start-->
   <div class="success">
       <div class="s_sbox">
       	
       		<c:choose>
       			<c:when test="${msg.status=='y' }">
       				  <!--成功 start-->
		           <div class="s_box s_bsuccess" >
		               <i class="ico ico_success"></i>
		               <div class="s_btxt s_btxt_ok">${msg.info }</div>
		           </div>
		           <!--成功 end-->
       			
       			</c:when>
       			<c:otherwise>
       			 <!--失败 start-->
		           <div class="s_box s_bfail" >
		               <i class="ico ico_fail"></i>
		               <div class="s_btxt s_btxt_fail">${msg.info }</div>
		           </div>
		           <!--失败 end-->
       			</c:otherwise>
       		</c:choose>
         
          
           <div class="s_toher">
              <a href="center/selectUserIndex.html" class="s_omember">会员中心</a>
              <!--<a href="#" class="s_omember">立即充值</a>-->
              <a href="${path }">返回首页</a>
           </div>
       </div>
       
   </div>
   <!--成功 end-->
</div>
<!--page end-->


	<!--header start-->
	<jsp:include page="/WEB-INF/view/front/comm/footer.jsp"></jsp:include>
	<!--header end-->

	
</body>

</html>
