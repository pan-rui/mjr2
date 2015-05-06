<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="${path }">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>P2P - 用户提现</title>
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
             <span class="u_titwz">用户提现</span>
             <div class="line1"></div>
          </div>
          <!--title end-->
           <!--账户 start-->
          <div class="u_txt">
              <!--提现 start-->
              <div class="drawcash">
                  <!--表格 start-->
                  <div class="drawcash_table">
                  <form action="bfpay/userWithdraw.html" method="post" id="editForm" target="_blank">
                      <table width="800" border="0" cellpadding="0" cellspacing="0" class="tx_table">
  <tr>
    <td width="129">持卡人</td>
    <td width="655"><my:replaceStar len="1" content="${userdto.realName}" start="2"/></td>
  </tr>
  <tr>
    <td>电话号码</td>
    <td><my:replaceStar len="4" content="${userdto.cellPhone}" start="4"/></td>
  </tr><%--
  <tr>
    <td>提现至银行卡</td>
    <td>
       <div class="mb_bcard_txt">
       <c:forEach items="${tbankcard}" var="dto">
       		<c:if test="${dto.cardStatus==2}"><div class="mb_bcard"><span class="mb_bc_bank">${dto.bankName}</span><span class="mb_bc_non" style="display: none;">${dto.id}</span> <span class="mb_bc_card">卡号 <my:replaceStar len="6" content="${dto.bankCardNo}" start="6"/></span></div></c:if>
       </c:forEach>
       <input  id="yhkhsfwk"   name="bankCardId"  type="hidden"/>
			<div class="mb_bcard mb_bclick"><span class="mb_bc_bank">中国工商银行</span> <span class="mb_bc_card">卡号 622202****9042</span></div>
			<div class="mb_bcard"><span class="mb_bc_bank">招商银行</span> <span class="mb_bc_card">卡号 622588****8533</span></div>
			<div class="mb_newbank"><a class="addband" href="#">+ 添加提现银行卡</a></div>
	   </div>
    </td>
  </tr>
  --%><tr>
    <td>可提现金额</td>
    <td><span class="txm_yes">￥${taccount.usableAmount}</span>
    	<input type="hidden" value="${taccount.usableAmount}" id="ktxje" />
    </td>
    
  </tr>
  <tr>
    <td>提现金额</td>
    <td><div class="txje"><i class="dm_tico tran"></i><input type="text" class="dm_money" name="withdrawAmount" id="srtxje" onkeypress="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}"/></div></td>
  </tr>
  <tr>
    <td>手续费</td>
    <td>￥<span id="sxf"></span></td>
  </tr>
  <tr>
    <td>实际到账金额</td>
    <td>￥<span id="truemoney"></span></td>
  </tr>
  
  <tr>
    <td colspan="2">
      <div class="tx_butcont">
        <p class="txb_trip">提现过程中，请耐心等候，请勿多次点击或者关闭浏览窗口，否则可能造成提现失败</p>
        <div class="txb_but">
          <input type="button" value="提 现" class="tx_submit" id="btnSave"/>
          <span class="question"><a href="helpShow-5.html" target="_blank"><i>提现不了，请点击</i></a></span>
          </div>
        <p class="txb_warning">警告：禁止恶意套现</p>
      </div>    </td>
    </tr>
</table>
</form>
      </div>
                  <!--表格 end-->
                  <!--小贴士 start-->
                  <div class="teshi_box">
                      <div class="teshi_tit"><span class="teshi_a teshi_ahov">提现小贴士</span></div>
                      <div class="teshi_txt">
                        1.	请正确输入要提现金额，高于可提现金额不能提现成功。<br />
						2.	提现过程是在第三方托管平台完成，平台不参与资金划拨。<br />
						3. 	如果您填写的提现信息不正确可能会导致提现失败，由此产生的提现费用将不予返还<br />
						4. 	当日20:00之前提现，实时到账； 当日20:00之后申请的订单，将于下日到账。<br />
						5. 	平台禁止洗钱、信用卡套现、虚假交易等行为，一经发现并确认，将终止该账户的使用
                      </div>
                  </div>
                  <!--小贴士 end-->
              </div>
              <!--提现 end-->
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
	
	<!--投标提示 start-->
<div class="t_question">
    <div class="t_quetit clearfix">
       <i class="tq_ico"></i>
       <div class="tq_des">提现完成前，请不要关闭此充值验证窗口。<br />
提现完成后，请根据您投标的情况点击下面按钮。</div>
    </div>
    <div class="t_quetxt clearfix">
        <a href="center/selectWithdraw.html" class="t_tbok">提现完成</a>
        <a href="helpShow-5.html" class="t_twhat">提现遇到问题</a>
    </div>
</div>
<!--投标提示 end-->
</body>

<script type="text/javascript">
$(function(){
	$("#myCenter").addClass("nav_aclick");
});
//投标提示
$('#btnSave').on('click', function(){
	var yhsrje=$("#srtxje").val();
	var ktxje=$("#ktxje").val();
	var userInvestAm = parseFloat(ktxje);
	var userInvestAm2 = parseFloat(yhsrje);
	
	if(yhsrje==""){
		alert("请输入正确的提现金额");
		return;
	}
	if(userInvestAm2<0){
		alert("请输入正确的提现金额");
		return;
	}
	if(userInvestAm2 > userInvestAm){
		alert("可用余额不足,请输入正确的提现金额");
		return;
	}
	  var t_issue =	$.layer({
			type: 1,
			title: '投标提示',
			//shadeClose: true, 开启点击遮罩关闭层
			area: ['480px','250px'],
			border : [0, 0.5, '#666'],
			offset: [($(window).height() - 250)/2+'px', ''],
			page: {dom : '.t_question'}
		});
	  //自设关闭
	$('.t_quetxt .t_tbok').on('click', function(){
		layer.close(t_issue);
	});
	$("#editForm").submit();
});

</script>

<script type="text/javascript"><%--
var valiForm = $("#editForm").Validform({
	btnSubmit : "#btnSave",
	tiptype : function(msg, o, cssctl) {
		var objtip = $("#errorMsg");
		cssctl(objtip, o.type);
		//alert(msg);
		
	},
	postonce : true,
	ajaxPost : false,
	callback : function(data) {
		
		if (data.status == 'y') {
			window.location.href = "index.jsp";
		}
	}
});
$.Tipmsg.r = "";
--%>

var setLabel = function(svalue){
		var jiji=$("#srtxje").val();
		if(jiji.length<1){
			$("#sxf").html("0.00");
			$("#truemoney").html("0.00");
			$("#errorxx").html("");
		}else{
			$("#errorxx").html("");
			$.post(
		      "countpoundage.html",
		      {
		    	  tzje:$("#srtxje").val()
		        		
		      },
		      function (data) //回传函数
		      {
		      
		        $("#sxf").html(data);
		        if((jiji-data)<0){
		        	$("#truemoney").html("0.00");
		        }else{
			   	 	$("#truemoney").html((jiji-data).toFixed(2));
		        }
			    //Subtr(jiji,data)
		      }
		     
		    );
	};
	};

	var easychange = function(foochange){
	    if(!foochange||foochange.constructor!=Function){return;}
	    try{this.watch("value",function(id,oldval,newval){foochange(newval);return newval;});}catch(e){}
	    this.setAttribute('oninput','('+foochange.toString()+')(this.value)');
	    this.onpropertychange = function(){foochange(this.value);};
	    this.onmouseover = function(){foochange(this.value);};
	    this.onkeyup = function(){foochange(this.value);};
	};

	var dx = document.getElementById('srtxje');
	easychange.call(dx,setLabel);
	
	
	$(".mb_bcard_txt .mb_bcard").click(function(){
          $(this).addClass("mb_bclick").siblings(".mb_bcard").removeClass("mb_bclick");
          $("#yhkhsfwk").val($(this).find(".mb_bc_non").text());
    });
	

	

</script>
</html>
