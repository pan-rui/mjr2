<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>【关于P2P】_互联网金融理财平台,100%本息保障！</title>
<meta name="keywords" content="P2P，互联网金融，P2P理财，本息保障"/>
<meta name="description" content="P2P为深圳市P2P金融信息服务有限公司线上理财平台，致力于助力小微企业发展的同时普惠大众理财的互联网金融新模式！"/>
<link rel="stylesheet" href="css/common.css" type="text/css" />
<link rel="stylesheet" href="css/page.css" type="text/css" />
<link rel="stylesheet" href="css/validform.css" type="text/css"></link>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.5&ak=5b171be193e9362978a383b1cce00460"></script>

</head>

<body>
	<!--header start-->
	<jsp:include page="/WEB-INF/view/front/comm/header.jsp"></jsp:include>
	<!--header end-->

	<!--page start-->
<div class="a_contactus">
    <!--address start-->
    <div class="a_contmain">
        <div class="acm_main">
            <div class="add_cont">
                <div class="addbiao tran"></div>
                <div class="addbg tran"></div>
                <div class="addcont addcont1">
                    <h2 class="conpany_tit">深圳市P2P金融信息服务有限公司</h2>
                    <div class="conpany_add">
                        <i class="c_addico tran"></i>广东省 深圳市 宝安区 新安街道  宝安创业二路与前进一路交汇处深信泰丰大厦4楼402
                    </div>
                </div>
                <div class="addcont addcont2">
                    <h2 class="conpany_tit">在线客服</h2>
                    <div class="a_QQtxt">
                        <ul>
                            <li><span class="asever_tit">Q&nbsp;&nbsp;Q&nbsp;&nbsp;群： </span>9999999</li>
                        </ul>
                    </div>
                </div>
                <div class="addcont addcont3"><h2 class="conpany_tit">服务热线：<span class="tel">400 - 876 - 9008</span></h2></div>
            </div>
            <div id="map" class="addmap">
                
            </div>
        </div>
    </div>
    <!--address end-->
    <!--2 start-->
    <div class="a_about a_about2">
        <div class="a_a2cont">
            <!--tit start-->
			<div class="atop_tit atop_tit2 tran">
                <div class="atit_t">
                   <span class="atit_tit">联系我们</span>
                   <span class="atit_des">Contact   us</span>
                </div>
            </div>
            <!--tit end-->
            <!--txt start-->
			<div class="cont_content">
                <div class="cont_box">
                    <div class="cont_bimg"><img src="images/aboutflm/contact_01.png" /></div>
                    <div class="cont_btxt">
                        <h2 class="cb_h2">服务热线</h2>
                        <p class="cb_des">400 - 876 - 9008</p>
                    </div>
                </div>
                <div class="cont_box cont_box2">
                    <div class="cont_bimg"><img src="images/aboutflm/contact_02.png" /></div>
                    <div class="cont_btxt">
                        <h2 class="cb_h2">QQ官方群</h2>
                        <p class="cb_des">9999999</p>
                    </div>
                </div>
                <div class="cont_box cont_box3">
                    <div class="cont_bimg"><img src="images/aboutflm/contact_03.png" /></div>
                    <div class="cont_btxt">
                        <h2 class="cb_h2">企业邮箱</h2>
                        <p class="cb_des">service@p2p.com</p>
                    </div>
                </div>
            </div>
            <!--txt end-->
        </div>
    </div>
    <!--2 end-->
    <!--客服热线 start-->
    <div class="a_about a_about3">
        <div class="a_a2cont">
            <!--tit start-->
			<div class="atop_tit atop_tit3 tran">
                <div class="atit_t">
                   <span class="atit_tit">客服</span>
                   <span class="atit_des">Customer Service</span>
                </div>
            </div>
            <!--tit end-->
            <!--ico start-->
          <div class="aabout_img"><img src="images/aboutflm/contact_04.png" /></div>
            <!--ico end-->
            <p class="service_time"><i class="s_timeico tran"></i>上午 09:00-12:00<span style="padding-left:50px;">&nbsp;</span>下午 14:00-18:00</p>
            <!--txt start-->
			<div class="aabout_txt">
                <p class="ab_txt2">尊进的用户您好！感谢您对P2P的支持，如有任何问题，请点击咨询我们的在线客服MM。<br />
欢迎来电：400-888-9999，我们竭诚为您服务！</p>
          </div>
            <!--txt end-->
        </div>
    </div>
    <!--客服热线 end-->
</div>
<!--page end-->

<!--footer start-->
	<jsp:include page="/WEB-INF/view/front/comm/footer.jsp"></jsp:include>
	<script type="text/javascript">
		$(function(){
			$("#aboutUs").addClass("nav_aclick");
		});
		var map = new BMap.Map("map");
		var point = new BMap.Point(113.911953,22.574479);
		map.centerAndZoom(point,18);
		//启用托转功能
		map.enableDragging();
		//启动滚轮功能
		map.enableScrollWheelZoom();
		var marker1 = new BMap.Marker(new BMap.Point(113.911953,22.574479));
		map.addOverlay(marker1); 
		//给标点添加事件
		
		openwd();
		marker1.addEventListener("mouseover",openwd);
		function openwd(){
			point = new BMap.Point(113.911933,22.574529);
			var opts = {
					  width : 250,     // 信息窗口宽度
					  height: 65,     // 信息窗口高度
					  title : "深圳市P2P金融信息服务有限公司" , // 信息窗口标题
					  enableMessage:false,//设置允许信息窗发送短息
					  message:"欢迎来访"
					};
			var infoWindow = new BMap.InfoWindow("&nbsp;&nbsp;&nbsp;&nbsp;深圳市宝安区新安街道前进一路深信泰丰大厦402", opts);  // 创建信息窗口对象
			map.openInfoWindow(infoWindow,point); //开启信息窗口
		}
	</script>
</body>
</html>
