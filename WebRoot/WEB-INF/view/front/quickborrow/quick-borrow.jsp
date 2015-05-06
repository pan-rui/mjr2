<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>P2P - 首页</title>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<link rel="stylesheet" href="css/common.css" type="text/css" />
<link rel="stylesheet" href="css/page.css" type="text/css" />
<script type="text/javascript" src="js/page.js"></script>
</head>

<body>
<!--header start-->
	<jsp:include page="/WEB-INF/view/front/comm/header.jsp"></jsp:include>
	<!--header end-->
	
	
<!--借款banner start-->
<div class="loan_banner">
    
</div>
<!--借款banner end-->
<!--内容 start-->
<div class="loan_cont">
     <!--tit start-->
     <div class="loan_tit">
         <div class="l_ttit">
         	<a href="QuickBorrowpersonal.html" class="l_tta l_tta1" divshow="0">个人贷款</a>
         	<a href="QuickBorrow.html" class="l_tta l_tclick" divshow="1">企业贷款</a>
         </div>
     </div>
     <!--tit end-->
     <div class="loan_tips">
         提示： 1、借款所在区域目前<b>仅限深圳</b>地区，后续将增加其他地区业务<br />
         <span style="padding-left:45px;"></span>2、请仔细填写您的真实信息，我们将在1个工作日内与您取得联系。
     </div>
     <!--txt start-->


     <!--txt end-->
     <!--企业贷款 start-->
     <div class="loan_main" style="display: block;">
         <div class="l_fbox">
             <div class="l_fortit">* 联系人</div>
             <div class="l_fortxt"><input type="text" class="loan_input" /></div>
         </div>
         <div class="l_fbox">
             <div class="l_fortit">* 联系电话</div>
             <div class="l_fortxt"><input type="text" class="loan_input" /></div>
         </div>
         <div class="l_fbox">
             <div class="l_fortit">* 意向融资金额</div>
             <div class="l_fortxt"><input type="text" class="loan_input" /></div>
         </div>
         <div class="l_fbox">
             <div class="l_fortit">* 融资期限</div>
             <div class="l_fortxt">
                <select name="" class="l_qxselect">
                  <option>1个月</option>
                  <option>2个月</option>
                  <option>3个月</option>
                  <option>4个月</option>
                  <option>5个月</option>
                  <option>6个月</option>
                  <option>7个月</option>
                  <option>8个月</option>
                  <option>9个月</option>
                  <option>10个月</option>
                  <option>11个月</option>
                  <option>12个月</option>
                  <option>1年以上</option>
                </select>
             </div>
         </div>
         <div class="l_fbox">
             <div class="l_fortit">* 所在区域</div>
             <div class="l_fortxt">
                深圳市
                <select name="" class="l_addselect">
                  <option>罗湖区</option>
                  <option>福田区</option>
                  <option>南山区</option>
                  <option>盐田区</option>
                  <option>宝安区</option>
                  <option>龙岗区</option>
                  <option>布吉</option>
                  <option>坪山新区</option>
                  <option>光明新区</option>
                  <option>龙华新区</option>
                  <option>大鹏新区</option>
                  <option>深圳周边</option>
                </select>
                </div>
         </div>
         <div class="l_fbox">
             <div class="l_fortit">* 企业名称</div>
             <div class="l_fortxt">
               <input type="text" class="loan_input" />
             </div>
         </div>
         <div class="l_fbox l_fbox2">
             <div class="l_fortit">* 借款用途</div>
             <div class="l_fortxt">
                 <textarea name="" cols="" rows="" class="l_textarea"></textarea>
             </div>
         </div>
         <div class="l_fbox">
             <div class="l_fortit">* 验证码</div>
             <div class="l_fortxt"><input type="text" class="loan_input loan_yzm" /></div>
         </div>
         <div class="loan_sub">
            <input type="submit" class="loan_submit" value="提交申请" />
         </div>
     </div>
     <!--企业贷款 start-->
    
</div>
<!--内容 end-->
<!--优势 start-->
<div class="loan_newer">
    <div class="l_newcont clearfix">
        <!--产品特点 start-->
        <div class="l_ys l_ys1">
            <h2>产品特点</h2>
            <ul>
               <li><a href="#">* 无任何贷前费用，收费透明</a></li>
               <li><a href="#">* 快速审批，最快当天可以获得资金支持</a></li>
               <li><a href="#">* 还款便捷，服务渠道广泛</a></li>
            </ul>
        </div>
        <!--产品特点 start-->
        <!--申请条件 start-->
        <div class="l_ys l_ys2">
            <h2>申请条件</h2>
            <ul>
               <li><a href="#">* 持有中国二代身份证及稳定收入证明</a></li>
               <li><a href="#">* 无不良信贷记录</a></li>
               <li><a href="#">* 有车、房等抵押物最佳</a></li>
            </ul>
        </div>
        <!--申请条件 start-->
    </div>
</div>
<!--优势 end-->
<!--foot start-->
	<jsp:include page="/WEB-INF/view/front/comm/footer.jsp"></jsp:include>
	<!--foot end-->
</body>

</html>
