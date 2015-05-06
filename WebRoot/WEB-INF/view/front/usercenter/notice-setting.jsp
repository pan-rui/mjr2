<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="${path }">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>P2P - 通知设置</title>
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
   
          <!--导航 start-->
         <jsp:include page="/WEB-INF/view/front/comm/user-left.jsp"></jsp:include>
          <!--导航 end-->
    
      <!--侧边 end-->
      <!--main start-->
      <div class="u_main">
          <!--title start-->
          <div class="u_tit">
             <span class="u_titwz">通知设置</span>
             <div class="line1"></div>
          </div>
          <!--title end-->
          <!--账户 start-->
          <div class="u_txt">
              <!--通知设置 start-->
              <form action="center/updateNoticeSetting.html" id="editForm">
              <div class="notice_set">
                   <p class="m_notit">平台将以您勾选的方式通知您。</p>
                   <div class="m_notxt">
                       <div id="isSmsDiv" class="n_tices n_tices1">短信<input  value="0" type="checkbox" class="not_check"></div>
                       <%--<div id="isEmailDiv" class="n_tices">邮件<input value="0" type="checkbox"  class="not_check"></div>
                       --%><div id="isMessageDiv" class="n_tices">站内信<input  value="0" type="checkbox"  class="not_check"></div>
                  		<input id="isSms" name="isSms" value="0" type="hidden" class="not_check">
                  		<input id="isEmail" name="isEmail" value="0" type="hidden" class="not_check">
                  		<input id="isMessage" name="isMessage" value="0" type="hidden" class="not_check">
                   </div>
                   <p class="m_nobut"><input type="submit" value="保存" class="save_submit" /></p>
              </div>
              </form>
              <!--通知设置 end-->
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
	<script type="text/javascript" src="js/page.js"></script>
</body>
<script type="text/javascript">
<!--
	$(function() {
		$("#myCenter").addClass("nav_aclick");
		$("#noticeSetting").addClass("nav_aclik");
		$("#myCenter").addClass("nav_aclick");
		var valiForm = $("#editForm").Validform({
			tiptype : 2,
			postonce : true,
			ajaxPost : true,
			beforeSubmit:function(curform){
				//在验证成功后，表单提交前执行的函数，curform参数是当前表单对象。
				//这里明确return false的话表单将不会提交;	
				if($("#isSmsDiv").hasClass("n_stick")==true){
					$("#isSms").val(1)
				}else{
					$("#isSms").val(0);
				}
				/*
				if($("#isEmailDiv").hasClass("n_stick")==true){
					$("#isEmail").val(1)
				}else{
					$("#isEmail").val(0);
				}
				*/
				if($("#isMessageDiv").hasClass("n_stick")==true){
					$("#isMessage").val(1)
				}else{
					$("#isMessage").val(0);
				}
			},
			callback : function(data) {
				if (data.status == 'y') {
					window.location.href="center/noticeSettingIndex.html";
				}else{
					alert(data.info);
				}
			}
		});

		$.Tipmsg.r = "";
		
		checkSelectBox("isSmsDiv",'${tNoticeSetting.isSms}');
		//checkSelectBox("isEmailDiv",'${tNoticeSetting.isEmail}');
		checkSelectBox("isMessageDiv",'${tNoticeSetting.isMessage}');
		
	});
	
	function checkSelectBox(divId,val){
		if(val=='1'){
			$("#"+divId).addClass("n_stick");
		}
	}
	
//-->
</script>
</html>
