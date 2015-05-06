<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="${path }">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>P2P - 用户银行卡</title>
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
   <!--用户中心 start-->
<div class="user clearfix">
      <!--侧边 start-->
      <jsp:include page="/WEB-INF/view/front/comm/user-left.jsp"></jsp:include>
      <!--侧边 end-->
      <!--main start-->
      <div class="u_main">
          <!--title start-->
          <div class="u_tit">
             <span class="u_titwz">用户银行卡</span>
             <div class="line1"></div>
          </div>
          <!--title end-->
            <!--账户 start-->
          <div class="u_txt">
              <!--银行卡 start-->
              <div class="bank">
                  <!--提示 start-->
                  <div class="bank_trip">
			             <p class="myb_pre">1、绑定银行用户名必须和P2P实名认证姓名一致。</p>
	                <p class="">2、如填写绑卡信息后，审核员一般在一个工作日内完成审核，节假日顺延。客服热线：400 856 3899</p>
	                <p class="myb_pre2">（例如：2014/9/9 8:00提交绑卡信息，最晚当日17：30完成审核）</p>
			      </div>
                  <!--提示 end-->
                  <!--内容 start-->
                  <div class="bank_cont">
                      <!--添加 start-->
                      <div class="myb_add"><a href="#">+ 添加银行卡</a></div>
                      <!--添加 end-->
                    <div class="space20"></div>
                      <!--列表 start-->
                      <div class="myb_list">
                         <ul>
                         
                         <c:forEach items="${bankcardlist}" var="dto">
                         	<form action="center/deleteBankCard.html" id="editForm">
                         	<li>
                                <b class="myb_bd_card myb_bd_cok tran"></b>
                                <div class="myb_b_name">
                                   <b>${dto.realName}</b>
                                        <a href="javsrcript:;" class="myb_czdel" onclick="aa('${dto.id}')">删除</a>
                             </div>
                                <div class="myb_b_txt">
                                 <p>开户行：${dto.bankName}</p>
                                   <p>账&nbsp;&nbsp;&nbsp;号：<my:replaceStar len="6" content="${dto.bankCardNo}" start="6"/></p>
                                </div>
                           </li>
                           
                           </form>
                         </c:forEach>
                           
                          
                           
                        </ul>
                      </div>
                      <!--列表 end-->
                </div>
                  <!--内容 end-->
              </div>
              <!--银行卡 end-->
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

	<script type="text/javascript" src="js/Validform_v5.3.2_min.js"></script>
	
</body>
<script type="text/javascript">

<!--
$(function() {
	$("#myCenter").addClass("nav_aclick");
	$("#center4").addClass("nav_aclik");
});
//-->
function aa(a){
	if(confirm('确定要删除？'))
	{
		$.ajax({
			type:"post",
			url:"center/deleteBankCard.html",
			data:{id:a},
			success: function(data){
				if (data!=null) {
					window.location.href = "center/selectBankCard.html";
				}else{
					alert("操作失败");
				}
				
			}
		});
	}
	else
	{
	  
	}
	
};
</script>

</html>
