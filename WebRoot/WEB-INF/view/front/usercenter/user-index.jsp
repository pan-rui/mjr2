<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="${path }">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>P2P - 账户中心</title>
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
             <span class="u_titwz">账户总览</span>
             <div class="line1"></div>
          </div>
          <!--title end--> 
          
           <!--账户 start-->
          <div class="u_txt">
              <!--总览 start-->
              <div class="u_zhanhu">
              	<p class="u_ztit">HI，<span class="time"></span>，工作的时候记得多喝几杯茶水哦 ^^！</p>
                <div class="u_boxcont clearfix">
                   <!--1 start-->
                   <div class="u_box u_box1">
                      <div class="u_bmain u_bmain1">
                         <div class="space20"></div>
                         
                         <!--上 start-->
                         <div class="u_infor">
                            <div class="u_timg"><img src="images/ad/tou.jpg" /></div>
                            <div class="u_tdes">
                               <div class="title">昵称：<span class="name">${userdto.userName}</span><span class="jiac">
                               <c:if test="${isnew==1 }">
                               <a  class="tag jiac" id="v_new" href="javascript:;" tips="<b style='color:red'>+0.4%</b>的新手奖励<br/>	(自第一次投资起30天内可享有新手奖励)<br />★ 可与VIP奖励叠加">+ 新手奖励</a>
                               </c:if>
                               </span>
                               <c:if test="${uild.awardLevel=='V1' }">
								<a class="tag vipc" id="v_vip" href="javascript:;" tips="Vip1：1万＜投资额≤5万  <b style='color:red'>+0.40%</b><br />还差${uild.dueinAmount }元达到vip2，奖励可达<b style='color:red'>+0.80%</b>">VIP 1</a>
								</c:if>
								<c:if test="${uild.awardLevel=='V2' }">
								<a class="tag vipc" id="v_vip" href="javascript:;" tips="Vip2：5万＜投资额≤10万  <b style='color:red'>+0.80%</b><br />还差${uild.dueinAmount }元达到vip3，奖励可达<b style='color:red'>+1.60%</b>">VIP 2</a>
								</c:if>
								<c:if test="${uild.awardLevel=='V3' }">
								<a class="tag vipc" id="v_vip" href="javascript:;" tips="Vip3：10万＜投资额  <b style='color:red'>+1.60%</b><br />">VIP 3</a>
								</c:if>
								</div>
                               <div class="u_picocont">
                                  <a href="javascript:;" class="u_pico ico_tel ico_telok tran"></a>
                                  <c:if test="${tp.cardNo!=null}"><a href="javascript:;" class="u_pico ico_id ico_idok tran"></a></c:if>
                                  <c:if test="${tp.cardNo==null}"><a href="javascript:;" class="u_pico ico_id tran"></a></c:if>
                                  <%--<a href="javascript:;" class="u_pico u_picoemail u_picoemailok tran"></a>
                               --%></div>
                               <!--<div class="tel">
                                  <b class="tel_ico"></b>
                                  <span class="tel_number">15085879685</span>
                                  <a href="#" class="tel_xiu"></a>
                               </div>-->
                            </div>
                         </div>
                         <!--上 end-->
                         <!--money start-->
                         <div class="u_lastmoney">
                             可用余额：<span class="l_money">${taccount.usableAmount}</span> 元
                         </div>
                         <!--money end-->
                         <!--button start-->
                         <div class="u_button">
                             <a href="bfpay/readyRecharge.html" class="u_b_botton u_b_cz">充值</a>
                            <a href="center/userWithdraw.html" class="u_b_botton u_b_tx">提现</a>
                         </div>
                         <!--button end-->
                      </div>
                   </div>
                   <!--1 end-->
                   <!--宝付平台 start-->
                   <div class="tuoguan">
                       <div class="tg_main">
                           <div class="space15"></div>
                           <h2 class="tg_mh2"><i class="tg_mh2ico"></i>第三方托管平台</h2>
                           <!--未开通 start-->
                           <c:if test="${tp2.isOpen==0}">
                           <div class="tg_mwei">
                               <p class="tg_mwtxt">P2P使用第三方资金托管服务保障您的资金安全，投资前需要开通资金托管账户。<a href="helpShow-6.html">了解更多>></a></p>
                           	   <p class="tg_mwgo"><a href="regPersonInfo.html">去开通</a></p>
                           </div>
                           </c:if>
                           <!--未开通 end-->
                           <!--已开通 start-->
                           <c:if test="${tp2.isOpen==1}">
                           <div class="tg_mok">
                               <ul>
                                  <%--<li>宝付账号：<my:replaceStar len="4"  content="${userdto.cellPhone}"  start='4'/></li>
                                  --%><li>真实姓名：<my:replaceStar len="2"  content="${tp.realName }"  start='2'/></li>
                                  <li>身份证号：<my:replaceStar len="10"  content="${tp.cardNo }"  start='5'/></li>
                               </ul>
                               <div class="tg_mokgo"><a href="bfpay/loginBaofooUrl.html" target="_blank" class="tg_go">登陆第三方托管支付平台</a></div>
                           </div>
                           </c:if>
                           <!--已开通 end-->
                       </div>
                   </div>
                   <!--宝付平台 end-->
                   <!--2 start-->
                   <div class="u_box u_box2">
                      <div class="u_bmain u_bmain2">
                         <div class="space10"></div>
                         <p class="u_shouyi"><c:if test="${zsy==null}">0</c:if><c:if test="${zsy!=null}">${zsy}</c:if></p>
                         <p class="u_sytit">总收益</p>
                         <div class="money_main">
                             <div class="money_box">
                                <span class="m_num"><c:if test="${ljtz==null}">0</c:if><c:if test="${ljtz!=null}">${ljtz}</c:if></span>
                                <span class="m_ntit">累计投资</span>
                             </div>
                             <div class="money_box money_box2">
                                <span class="m_num"><c:if test="${dslx==null}">0</c:if><c:if test="${dslx!=null}">${dslx}</c:if></span>
                                <span class="m_ntit">待收收益</span>
                             </div>
                             <div class="money_box money_box3">
                                <span class="m_num"><c:if test="${dsbj==null}">0</c:if><c:if test="${dsbj!=null}">${dsbj}</c:if></span>
                                <span class="m_ntit">待收本金</span>
                             </div>
                         </div>
                      </div>
                   </div>
                   <!--2 end-->
                   <!--3 start-->
                   <div class="u_box u_box3">
                      <div class="u_bmain3">
                          <div class="space20"></div>
                          <!--统计 start-->
                          <div class="z_tj">
                             <!--1 start-->
                             <div class="ztj_box">
                                 <div class="ztj_left">
                                     <span class="ztj_img ztj_img1"></span>
                                     <span class="ztj_txt">已投资</span>
                                 </div>
                                 <div class="ztj_data"><span class="data_number">${tzbs}</span> 笔</div>
                             </div>
                             <!--1 end-->
                             <!--1 start-->
                             <div class="ztj_box">
                                 <div class="ztj_left">
                                     <span class="ztj_img ztj_img2"></span>
                                     <span class="ztj_txt">已回款</span>
                                 </div>
                                 <div class="ztj_data"><span class="data_number">${yhktz}</span> 笔</div>
                             </div>
                             <!--1 end-->
                             <!--1 start-->
                             <div class="ztj_box ztj_box3">
                                 <div class="ztj_left">
                                     <span class="ztj_img ztj_img3"></span>
                                     <span class="ztj_txt">待回款</span>
                                 </div>
                                 <div class="ztj_data"><span class="data_number">${nhktz}</span> 笔</div>
                             </div>
                             <!--1 end-->
                          </div>
                          <!--统计 end-->
                          <div class="ztj_total"><a href="center/selectInvestList.html" class="t_total">查看投资汇总<i class="t_totico"></i></a></div>
                      </div>
                   </div>
                   <!--3 end-->
                </div>
              </div>
              <!--总览 start-->
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
<script type="text/javascript" src="js/tips.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	//tips提示框
	$("#v_new").manhua_hoverTips({position : "t"});//改变了显示的位置参数
	$("#v_vip").manhua_hoverTips({position : "t"});//改变了显示的位置参数
	$("#p_tel").manhua_hoverTips({position : "t"});//改变了显示的位置参数
	$("#p_id").manhua_hoverTips({position : "t"});//改变了显示的位置参数
	$("#p_email").manhua_hoverTips({position : "t"});//改变了显示的位置参数
});
</script>
<script type="text/javascript">
<!--
	$(function() {
		$("#myCenter").addClass("nav_aclick");
		$("#center1").addClass("nav_aclik");
	});
//-->
</script>

<script>
	function getDiv(){
		var _div = document.getElementsByTagName('div');
		for(var i in _div){
			if(_div[i].className=='time'){
				return _div[i];
			}
		}
	}
	function getText(){
		var _date = new Date();
		var _time = _date.getHours();
		var _text = '';
		if(_time>=6&&_time<9)
			_text = '早上好';
		else if(_time>=9&&_time<11)
			_text = '上午好';
		else if(_time>=11&&_time<13)
			_text = '中午好';
		else if(_time>=13&&_time<17)
			_text = '下午好';
		else
			_text = '晚上好';
		return _text;
	}
</script>

<script>
if(getDiv())
	getDiv().innerText = getText();
</script>
<script>
//jQuery的写法
jQuery('.time').html(getText());
</script>
</html>
