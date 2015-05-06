<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>【${tBorrowModel.borrowTitle }】_P2P互联网金融理财平台！年化16%以上的P2P平台</title>
<meta name="keywords" content="P2P理财，实物抵押，互联网金融，P2P"/>
<meta name="description" content="严格的风控体系，100%本息保障！富了么？想富就上P2P互联网金融理财平台_小微金融，普惠大众。"/>
<link rel="stylesheet" href="css/common.css" type="text/css" />
<link rel="stylesheet" href="css/page.css" type="text/css" />
<link rel="stylesheet" href="css/validform.css" type="text/css"></link>
<link rel="stylesheet" href="css/picbox.css" type="text/css" media="screen" />


</head>

<body>
	<!--header start-->
	<jsp:include page="/WEB-INF/view/front/comm/header.jsp"></jsp:include>
	<!--header end-->

	<!--page start-->
<div class="p_page15">
   <!--当前位置 start-->
   <div class="p1000 p_position">
       <i class="p_posico"></i><span class="p_postxt"><a href="${path }">首页</a> >  <a href="borrowList.html">理财投资</a> > ${tBorrowModel.borrowTitle }</span>
   </div>
   <!--当前位置 end-->
   <!--main start-->
   <div class="p1000 page_box clearfix">
      <!--title start-->
      <div class="pmain_tit clearfix">
         <div class="pm_tit">
             <span class="pm_tith2">${tBorrowModel.borrowTitle }</span>
             <a id="t_bao" class="tag tag_i tag_bao"  href="javascript:void(0)" tips="担保公司"></a>
             <a id="t_ya" class="tag tag_i tag_ya"  href="javascript:void(0)" tips="实物抵押"></a>
         </div>
          <div class="pm_hetong"><a href="#" class="pm_th">借款协议（范本）</a></div>
      </div>
      <!--title end-->
      <!--详细 start-->
      <div class="pmain_cs">
         <!--参数 start-->
         <div class="cs_box">
            <!--up start-->
            <div class="cs_up">
               <div class="cs_money">
                   <p class="cs_bh2">借款金额 （元）</p>
                   <p class="cs_btxt">
                      <span class="cs_b_money"><fmt:formatNumber value="${tBorrowModel.borrowAmount }" pattern="#,###"></fmt:formatNumber></span>
                   </p>
               </div>
               <div class="cs_nhl">
                   <p class="cs_bh2">年化收益率</p>
                   <div class="cs_btxt">
                      <div class="csn_nhsy"><span class="cs_nnumber"><fmt:formatNumber value="${tBorrowModel.annualRate }" pattern="#.0"></fmt:formatNumber></span> <i class="cs_nfu">%</i></div>
                      <div class="cs_njiang">
                         <b class="b_jnew"><a id="t_newer" class="tag"  href="javascript:void(0)" tips="年化收益率<b style='color:red'>+0.4%</b>的新手奖励
<br />（自第一次投资起30天内可享有新手奖励）">+ 新手奖励 </a></b>
                   <b class="b_jvip"><a id="t_vips" class="tag"  href="javascript:void(0)" tips="Vip1: 1万＜待收本金≤5万，  年化收益率<b style='color:red'>+0.40%</b><br />
Vip2: 5万＜待收本金≤10万，年化收益率<b style='color:red'>+0.8%</b><br />
Vip3: 待收本金＞10万，        年化收益率<b style='color:red'>+1.6%</b><br />
(新手奖励可与Vip奖励叠加)">+ VIP奖励</a></b>
                      </div>
                   </div>
               </div>
               <div class="cs_data">
                   <p class="cs_bh2">借款期限</p>
                   <p class="cs_btxt">
                      <span class="cs_ndata">${tBorrowModel.deadline }</span> <i class="cs_nyue"> <c:choose>
                      		<c:when test="${tBorrowModel.deadlineType==2 }"> 个月</c:when>
                      		<c:otherwise>天</c:otherwise>
                      </c:choose></i>
                   </p>
               </div>
            </div>
            <!--up end-->
            <!--down start-->
            <div class="cs_down clearfix">
               <!--进度条 start-->
               <div class="cs_progress">
                  
                  <div id="pbar1" class="pbar1"></div>
               </div>
               <!--进度条 end-->
               <!--其他 start-->
               <div class="cs_other">
                  <ul>
                     <li>还款方式： 
                     <c:choose>
                    <c:when test="${tBorrowModel.repayType==1 }">一次性还款<img src="images/page/what.png" id="t_backtype" class="tag" tips="指借款人到期向理财人偿还全部本金和利息" /></c:when>
						<c:when test="${tBorrowModel.repayType==2 }">按月付息，到期还本<img src="images/page/what.png" id="t_backtype" class="tag" tips="在借款期间内，借款人向理财人每月返还利息，最后一个月返还当月的利息和全部本金。" /></c:when>
						<c:when test="${tBorrowModel.repayType==3 }">等额本息<img src="images/page/what.png" id="t_backtype" class="tag" tips="是指将借款的本金总额与利息总额相加，然后平均分摊到还款期限的每个月中，在借款期内，借款人每月偿还给理财人同等数额的本金和利息。
" /></c:when> 
                     </c:choose> </li>
                     <li>
       				<c:choose>
       					<c:when test="${tBorrowModel.borrowStatus==3 }">剩余时间：<span id="remainTime"></span></c:when>
       					<c:when test="${tBorrowModel.borrowStatus==2}">距开始投标倒计时：<span id="remainTime"></span></c:when>
       					<c:otherwise>
       						剩余时间：已结束
       					</c:otherwise>
       				</c:choose>              
                   </li>
                  </ul>
               </div>
               <!--其他 end-->
            </div>
            <!--down end-->
         </div>
         <!--参数 end-->
         <!--表单 start-->
         <div class="pmain_form">
         	<c:choose>
         		<c:when test="${tBorrowModel.borrowStatus==2 }">
	         		 <!--预售 start-->
	           		 <div class="pbuy pbuy2" style="display:block">
	                <p class="pbuy2_tit">千万别走开，精彩马上开始...</p>
	                <div class="p_biao p_biao2 tran"></div>
	                <p class="pbbut pbbut2">预售中..</p>
	            </div>
	            <!--预售 end-->
         		
         		</c:when>
         		<c:when test="${tBorrowModel.borrowStatus==3}">
         		 <!--招标中 start-->
		            <div class="pbuy pbuy3" style="display:block">
		            <form action="bfpay/investAjaxBorrow.html" id="editForm" target="_blank">
		                 <ul class="form_money">
			              <li>可投金额：<span class="f_lastmoney"><fmt:formatNumber value="${remainAmount}" pattern="#,###"></fmt:formatNumber></span> 元</li>
			              <li>账户余额：
			              <c:choose>
			              	<c:when test="${user==null }"><span class="f_loginico"></span><a href="loginIndex.html" class="form_login" target="_blank">登陆</a> 后显示</c:when>
			              	<c:otherwise><span class="f_lastmoney">${tAccountModel.usableAmount }</span> 元  &nbsp;&nbsp;<a href="bfpay/readyRecharge.html">充值</a></c:otherwise>
			              </c:choose>
			            
			              </li>
			            </ul>
			             <div class="f_write">
			               <span class="f_wmoney tran"></span>
			               <input id="investAmount" type="text" name="investAmount" class="f_writxt" onpropertychange="calculateInterest()" oninput="calculateInterest()" datatype="investAmount" nullmsg="投资金额不能为空" errormsg="投资金额格式不正确"/>
			           		<input type="hidden" name="borrowId" value="${tBorrowModel.id }" class="f_writxt"/>
			            </div>
		              
		                <p class="f_shouyi">可获得收益：￥ <span class="f_symoney" id="interest">0.00</span> 元</p>
		                <div class="f_submit">
		                  <input type="submit" value="立即投标"  id="btnSave" class="f_buy" />
		                </div>
		                 <p class="f_shouyi" id="errorMsg"></p>
		                </form>
		            </div>
		            <!--招标中 end-->
         		</c:when>
         		<c:when test="${tBorrowModel.borrowStatus==4 || tBorrowModel.borrowStatus==10}">
         			 <!--满标复审 start-->
		            <div class="pbuy pbuy4" style="display:block">
		                <p class="pbuy2_tit">已满标，正在复审中，请耐心等待...</p>
		                <div class="p_biao p_biao4 tran"></div>
		                <p class="pbbut pbbut4">等待复审..</p>
		            </div>
		            <!--满标复审 end-->
         		</c:when>
         		<c:when test="${tBorrowModel.borrowStatus==5}">
         			 <!--还款中 start-->
			        <div class="pbuy pbuy5" style="display:block">
			                <p class="pbuy2_tit">正在还款中，请耐心等待...</p>
			                <div class="p_biao p_biao5 tran"></div>
			                <p class="pbbut pbbut5">还款中..</p>
			            </div>
			            <!--还款中 end-->
         		</c:when>
         		<c:when test="${tBorrowModel.borrowStatus==6}">
         			  <!--已还清 start-->
				        <div class="pbuy pbuy6" style="display:block">
				                <p class="pbuy2_tit">此标已还清，查看自己的<a href="#" class="mem_jinku">小金库</a></p>
				                <div class="p_biao p_biao6 tran"></div>
				                <p class="pbbut pbbut6">已还清</p>
				            </div>
				            <!--已还清 end-->
         		</c:when>
         		<c:when test="${tBorrowModel.borrowStatus==9}">
         			   <!--流标 start-->
            <div class="pbuy pbuy9" style="display:block">
                <p class="pbuy2_tit">此标已流标，请查看<a href="#" class="other_pro">其他理财项目</a></p>
                <div class="p_biao p_biao9 tran"></div>
                <p class="pbbut pbbut9">流标</p>
            </div>
            <!--流标 end-->
         		</c:when>
         	</c:choose>
          
        </div>
         <!--表单 end-->
      </div>
      <!--详细 end-->
      <!--选项卡 start-->
      <div class="p_txt">
         <div class="p_tabtit">
            <a class="tab_tit tab_click" href="javascript:void(0)" divshow="0">借款明细</a>
            <a class="tab_tit" href="javascript:void(0)" divshow="1">投资记录<i id="investCount" class="people_number"></i></a>
         </div>
         <!--借款明细 start-->
         <div class="p_tabmain" style="display:block;">
            <!--用户资料 start-->
            <div class="m_pbox m_pbox1">
               <!--tit start-->
               <div class="m_pbtit">
                  <i class="m_pbico m_pbico1 tran"></i>
                  <span class="m_pbtou">用户资料</span>
               </div>
               <!--tit end-->
               <!--txt start-->
               <div class="m_pbtxt">
                  <div class="mzl_box clearfix">
                      <ul class="mzl_ua">
                         <li>姓   名：<my:replaceStar content="${tPersonBorrowerModel.borrowerName }" start="2" len="1" /></li>
                         <li>性   别：<c:choose>
                         	<c:when test="${tPersonBorrowerModel.sex==1 }">男</c:when>
                         	<c:otherwise>女</c:otherwise>
                         </c:choose></li>
                         <li>年   龄：${tPersonBorrowerModel.age }</li>
                         <li>婚  否：<c:choose>
                         	<c:when test="${tPersonBorrowerModel.maritalStatus==0 }">否</c:when>
                         	<c:when test="${tPersonBorrowerModel.maritalStatus==1 }">是</c:when>
                         	<c:otherwise></c:otherwise>
                         </c:choose></li>
                          <li>是否有房：<c:choose><c:when test="${tPersonBorrowerModel.houseStatus==0 }">没有</c:when>
                         	<c:when test="${tPersonBorrowerModel.houseStatus==1 }">有</c:when>
                         	<c:otherwise></c:otherwise></c:choose></li>
                      </ul>
                       <ul class="mzl_ub">
                        
                         <li>是否有车：<c:choose><c:when test="${tPersonBorrowerModel.carStatus==0 }">没有</c:when>
                         	<c:when test="${tPersonBorrowerModel.carStatus==1 }">有</c:when>
                         	<c:otherwise></c:otherwise></c:choose></li>
                        
                         <li>公司行业：${tJobBorrowerModel.companyIndustry }</li>
                         <li>公司性质：${tJobBorrowerModel.companyNature }</li>
                         <li>岗   位：${tJobBorrowerModel.position }</li>
                         <li>月收入水平：${tPersonBorrowerModel.monthlyIncomeLevel }</li>
                      </ul>
                      <span class="shenhe_over tran"></span>
                  </div>
               </div>
               <!--txt end-->
            </div>
            <!--用户资料 end-->
            <!--资质资料 start-->
            <div class="m_pbox m_pbox2">
               <!--tit start-->
               <div class="m_pbtit">
                  <i class="m_pbico m_pbico2 tran"></i>
                  <span class="m_pbtou">资质资料</span>
               </div>
               <!--tit end-->
               <!--txt start-->
               <div class="m_pbtxt">
                    <div class="show">
                        <a href="javascript:void(0);" class="prev"><</a>
                        <a href="javascript:void(0);" class="next">></a>
                        <div class="itemshow">
                            <ul class="items">
                            	<c:forEach items="${borrowAttrList }" var="bean" varStatus="st">
	                            	 <li>
	                                
	                            		<a href="${imgPath }${bean.attrPath }" rel="lightbox-group" title="${bean.attrName }">
	                                        <img src="${imgPath }${bean.attrPath }" alt="${bean.attrName }">
	                                        <span>${bean.attrName }</span>
	                                    </a>
	                                </li>
                            	</c:forEach>
                               
                           
                            </ul>
                        </div>
                    </div>
             </div>
               <!--txt start-->
            </div>
            <!--资质资料 end-->
            <!--项目审核 start-->
            <div class="m_pbox m_pbox3">
               <!--tit start-->
               <div class="m_pbtit">
                  <i class="m_pbico m_pbico3 tran"></i>
                  <span class="m_pbtou">项目审核</span>
               </div>
               <!--tit end-->
               <!--txt start-->
               <div class="m_pbtxt">
                    ${tBorrowModel.details }
               </div>
               <!--txt start-->
            </div>
            <!--项目审核 end-->
            <!--风控意见 start-->
            <div class="m_pbox m_pbox4">
               <!--tit start-->
               <div class="m_pbtit">
                  <i class="m_pbico m_pbico4 tran"></i>
                  <span class="m_pbtou">风控意见</span>
               </div>
               <!--tit end-->
               <!--txt start-->
               <div class="m_pbtxt">
                   ${tBorrowModel.windControlTip }
               </div>
               <!--txt start-->
            </div>
            <!--风控意见 end-->
         </div>
         <!--借款明细 end-->
         <!--投标记录 start-->
 <div id="investList" class="p_tabmain ptab_record">
            
           
              
           
           
   </div>
         <!--投标记录 end-->
     </div>
      <!--选项卡 end-->
   </div>
   <!--main end-->
</div>
<!--page end-->


<!--投标提示 start-->
<div class="t_question">
    <div class="t_quetit clearfix">
       <i class="tq_ico"></i>
       <div class="tq_des" id="showMsg"><br />
投标完成后，请根据您投标的情况点击下面按钮。</div>
    </div>
    <div class="t_quetxt clearfix">
        <a href="javascript:window.location.reload();" class="t_tbok">投标完成</a>
        <a href="helpShow-4.html" class="t_twhat">投标遇到问题</a>
    </div>
</div>
<!--投标提示 end-->
<!--footer start-->
	<jsp:include page="/WEB-INF/view/front/comm/footer.jsp"></jsp:include>
<!--footer end-->
<script type="text/javascript" src="js/page.js"></script>
<!--进度条-->
<script type="text/javascript" src="js/raphael.2.1.0.min.js"></script>
<script type="text/javascript" src="js/justgage.1.0.1.min.js"></script>
<script type="text/javascript" src="layer/layer.min.js"></script>
<!--图片弹出窗-->
<script type="text/javascript" src="js/picbox.js"></script>
<!--tips-->
<script type="text/javascript" src="js/tips.js"></script>
<script type="text/javascript" src="js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript">
   window.onload = function(){
	 var pbar1 = new JustGage({
		  id: "pbar1", 
		  value: '${tBorrowModel.progress}', 
		  min: 0,
		  max: 100,
		  title: " ",
		  showMinMax: false,
		  label: "投资比例"
	   });
                };
</script>
<script type="text/javascript">
//投标提示
function showTips(showMsg){
	$("#showMsg").html("投标处理中，请耐性等待...");
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
}

//借款协议（范本）
$('.pm_hetong .pm_th').on('click', function(){
	   loan_box = $.layer({
			type: 1,
			title: '借款协议（范本）',
			shadeClose: true, //开启点击遮罩关闭层
			area: ['880px','650px'],
			border : [0, 0.5, '#666'],
			offset: [($(window).height() - 650)/2+'px', ''],
			page: {url : 'showAgreement.html'}
		});
	//自设关闭
	
});

function closeLoan(){
	layer.close(loan_box);
	
}
</script>
<script type="text/javascript">
			$(document).ready(function(){
				//tips提示框
				$("#t_bao").manhua_hoverTips({position : "t"});//改变了显示的位置参数
				$("#t_ya").manhua_hoverTips({position : "t"});//改变了显示的位置参数
				$("#t_newer").manhua_hoverTips({position : "t"});//改变了显示的位置参数
				$("#t_vips").manhua_hoverTips({position : "t"});//改变了显示的位置参数
				$("#t_backtype").manhua_hoverTips({position : "t"});//改变了显示的位置参数
			});
</script>
<script type="text/javascript">
$(function(){
	$("#borrowList").addClass("nav_aclick");
	
	var valiForm = $("#editForm").Validform({
		btnSubmit : "#btnSave",
		tiptype : function(msg, o, cssctl) {
			var objtip = $("#errorMsg");
			cssctl(objtip, o.type);
			objtip.text(msg);
		},
		beforeSubmit:function(curform){
			showTips();
		},
		datatype:{
		    "investAmount":function(gets,obj,curform,regxp){
		        //参数gets是获取到的表单元素值，
		        //obj为当前表单元素，
		        //curform为当前验证的表单，
		        //regxp为内置的一些正则表达式的引用。
		    	if('${user}'==''){
		    		return "请先登录后再投标";
			    }
		        var reg=/^\d+$/;
		        if(reg.test(gets)==false){
		        	return "投标金额格式不正确";
		        }
		        var remainAmount = parseFloat('${remainAmount}');
		        var usableAmount = parseFloat('${tAccountModel.usableAmount }');
		        if(gets > usableAmount){
		        	return "余额不足";
		        }
		        var minAmount = parseFloat('${tBorrowModel.minInvestAmount}');
		        if(remainAmount < minAmount && gets !=remainAmount){
		        	return "投资金额只能为"+remainAmount+"元";
		        }else if(remainAmount > minAmount && gets<minAmount){
		        	return "投标金额小于最小投标金额"+minAmount+"元";
		        }
		        
		        if(gets > remainAmount){
		        	return "投标金额大于剩余可投标金额"+remainAmount+"元";
		        }
		        
		        return true;
		        //return false表示验证出错，没有return或者return true表示验证通过。
		    }
		},
		postonce : true,
		ajaxPost : true,
		callback : function(data) {
			$("#showMsg").html(data.info);
			if (data.status == 'y') {
				
			}
		}
	});
	$.Tipmsg.r = "";
	//加载投资记录
	turnInvestPage(1);
	
});

function turnInvestPage(curPage){
	$.lionPost("borrowInvestList.html",{"borrowId":'${tBorrowModel.id}',"curPage":curPage},function(data){
		$("#investList").html(data);
	});
}

sysSecond = parseInt('${remainTime}');
InterValObj = window.setInterval(setRemainTime, 1000);
function setRemainTime() {
    if (sysSecond > 0) {
    	sysSecond = sysSecond - 1;
        var second = Math.floor(sysSecond % 60);             // 计算秒    
        var minite = Math.floor((sysSecond / 60) % 60);      //计算分
        var hour = Math.floor((sysSecond / 3600) % 24);      //计算小时
        var day = Math.floor((sysSecond / 3600) / 24);        //计算天
        if (day > 0) {
            if (day == 1) {
                $("#remainTime").html(day + " 天 " + hour + " 时 " + minite + " 分 " + second + " 秒");
            } else {
                $("#remainTime").html(day + " 天 " + hour + " 时 " + minite + " 分 " + second + " 秒");
            }
        } else {
            $("#remainTime").html(hour + " 时 " + minite + " 分 " + second + " 秒");
        }
    } else {//剩余时间小于或等于0的时候，就停止间隔函数
    	if('${tBorrowModel.borrowStatus}'=='2'){
    		window.clearInterval(InterValObj);
    		window.location.reload();
    		 
    	}else{
    		$("#remainTime").html("已结束");
            window.clearInterval(InterValObj);
    	}
    	
        //这里可以添加倒计时时间为0后需要执行的事件
    }
}




function calculateInterest(){

	var amount = $("#investAmount").val();
	var regex = /^[1-9]+[0-9]*]*$/  ;


	if(!regex.test(amount)){
		return;
	}
	if(amount<50){
		$("#interest").html("0.00");
		return;
	}
	$.lionPost("calculateInterest.html",
			{"amount":amount,"repayType":'${tBorrowModel.repayType}',"annualRate":'${tBorrowModel.annualRate}',"month":'${tBorrowModel.deadline}',"dayType":'${tBorrowModel.deadlineType}'},function(data){
		if(data.code != -1){
			$("#interest").html(data.interestAmount);
		}
	});
}
</script>
</html>
