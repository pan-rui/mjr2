<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="${path }">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>P2P 红包在此！推荐奖励在此！门槛就是这么低，有钱就是要任性！羊毛党，我不惧！但愿你很资深！想挑战你就来！</title>
<link rel="stylesheet" href="css/common.css" type="text/css" /> 
<link rel="stylesheet" href="css/redaward.css" type="text/css" /> 
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<!--图片延时加载js-->
<script src="js/jquery.lazyload.js"></script> 
<link rel="stylesheet" href="css/page.css" type="text/css" />
<script type="text/javascript">
$(function(){
	$('.lazy').lazyload({
		effect:'fadeIn'
	});
});
</script>
</head>

<body>
<!--header start-->
	<jsp:include page="/WEB-INF/view/front/comm/header.jsp"></jsp:include>
	<!--header end-->
<!--头部-->
<!--banner start-->
<div class="t_banner">
    <div class="t_banmain">
    	<img src="images/yang.png" data-original="images/yang.png" class="center-block lazy opacity0 fadeInLeft animated yang" />
        <img src="images/zi.png" data-original="images/zi.png" class="center-block lazy opacity0 fadeInLeft animated2 zi" />
        <a href="borrowList.html" class="center-block lazy opacity0 fadeInDown animated3 people1" data-original="images/people1.png"><img src="images/people1.png" /></a>
        <a href="borrowList.html" class="center-block lazy opacity0 fadeInDown animated4 people2" data-original="images/people2.png"><img src="images/people2.png" /></a>
    </div>
</div>
<!--banner end-->
<div class="active_page">
    <div class="space40"></div>
    <!--1 start-->
    <div class="bag_box">
        <!--动画 start-->
        <img src="images/b1_img1.png" class="center-block lazy opacity0 fadeInLeft animated b1_img1" data-original="images/b1_img1.png" />
        <img src="images/b1_img2.png" class="b1_img2" />
        <img src="images/b1_img3.png" class="center-block lazy opacity0 fadeInRight animated b1_img3" data-original="images/b1_img3.png" />
        <img src="images/b1_img4.png" class="center-block lazy opacity0 fadeInRight animated b1_img4" data-original="images/b1_img4.png" />
        <img src="images/b1_img5.png" class="center-block lazy opacity0 fadeInRight animated b1_img5" data-original="images/b1_img5.png" />
        <!--动画 end-->
		<div class="b_box">
            <!--tit start-->
            <div class="b_btit b_btit1"></div>
            <!--tit end-->
            <div class="b_txt b_txt1">
                <ul class="b1_txtul">
                   <li>
                      <p class="b1_ta">￥<span class="b1_tamoney">20</span></p>
                      <p class="b1_tb">100元  ≤ 首次投资 < 1000元</p>
                   </li>
                   <li>
                      <p class="b1_ta">￥<span class="b1_tamoney">38</span></p>
                      <p class="b1_tb">1000元 ≤ 首次投资 < 10000 元</p>
                   </li>
                   <li class="b1_ulli3">
                      <p class="b1_ta">￥<span class="b1_tamoney">88</span></p>
                      <p class="b1_tb">首次投资 ≥ 10000元</p>
                   </li>
                </ul>
                <a href="${path }activityFirst.html#bag4_box" class="rule_a">活动规则 ></a>
            </div>
        </div>
    </div>
    <!--1 end-->
    <div class="space40"></div>
    <!--2 start-->
    <div class="bag_box">
        <!--动画 start-->
        <img src="images/b2_img1.png" class="center-block lazy opacity0 fadeInLeft animated b2_img1" data-original="images/b2_img1.png" />
        <!--动画 end-->
  		<div class="b_box">
            <!--tit start-->
            <div class="b_btit b_btit2"></div>
            <!--tit end-->
            <div class="b_txt b_txt2 clearfix">
                <div class="tj_links clearfix">
                   <div class="links">
                      <input type="text" class="links_text" id="abcd" value="http://www.p2p.com/regIndex.html?tjr=${tel}" />
                      <input type="submit" class="links_submit" value="复制链接" id="fz" />
                   </div>
                   <div class="links_img"><p class="links_imgp"><img style="width:102px;" src="http://qr.liantu.com/api.php?text=www.p2p.com/regIndex.html?tjr=${tel}"></p></div>
                </div>
                <div class="links_share">
<!-- JiaThis Button BEGIN -->
<div class="jiathis_style_32x32">
<a class="jiathis_button_qzone"></a>
<a class="jiathis_button_tsina"></a>
<a class="jiathis_button_tqq"></a>
<a class="jiathis_button_renren"></a>
<a class="jiathis_button_kaixin001"></a>
<a class="jiathis_button_weixin"></a>
<a class="jiathis_button_douban"></a>
<a href="http://www.jiathis.com/share" class="jiathis jiathis_txt jiathis_separator jtico jtico_jiathis" target="_blank"></a>
</div>
<script type="text/javascript" >
var jiathis_config={
	url:"www.p2p.com",
	summary:"恭庆上线，喜迎2015，大红包88元，来者有份！推荐奖励8元起，上不封顶，想挑战你就来。",
	title:"P2P红包送疯啦！注册就有，奖励无上限，还不过来抢？",
	shortUrl:false,
	hideMore:false
}
</script>
<script type="text/javascript" src="http://v3.jiathis.com/code/jia.js" charset="utf-8"></script>
<!-- JiaThis Button END -->

                </div>
            </div>
        </div>
    </div>
    <!--2 end-->
    <div class="space40"></div>
    <!--3 start-->
    <div class="bag_box">
        <!--动画 start-->
        <img src="images/b3_img1.png" class="center-block lazy opacity0 fadeInLeft animated b3_img1" data-original="images/b3_img1.png" />
        <img src="images/b3_img2.png" class="center-block lazy opacity0 fadeInLeft animated b3_img2" data-original="images/b3_img2.png" />
        <!--动画 end-->
        <div class="b_box">
            <!--tit start-->
            <div class="b_btit b_btit3"></div>
            <!--tit end-->
            <!--txt start-->
            <div class="b_txt b_txt3 clearfix">
                <div class="b3_box">
                    <div class="b3_btit">推荐5-10位用户注册并投资100元</div>
                    <div class="b3_btxt">
                        <ul>
                           <li>推荐人数x8元的现金奖励</li>
                           <li>免费享受 <span class="t_red">Vip1</span> 会员收益加成6个月（不限投资金额）</li>
                           <li>获得被推荐人每次投资额年化收益2%奖励</li>
                        </ul>
                    </div>
                </div>
                <div class="b3_box">
                    <div class="b3_btit">推荐10-30位用户并投资100元</div>
                    <div class="b3_btxt">
                        <ul>
                           <li>推荐人数x8元的现金奖励</li>
                           <li>免费享受 <span class="t_red">Vip2</span> 会员收益加成6个月（不限投资金额）</li>
                           <li>获得被推荐人每次投资额年化收益2%奖励</li>
                        </ul>
                    </div>
                </div>
                <div class="b3_box">
                    <div class="b3_btit">推荐30位以上用户并投资100元</div>
                    <div class="b3_btxt">
                        <ul>
                           <li>推荐人数x8元的现金奖励</li>
                           <li>免费享受 <span class="t_red">Vip3</span> 会员收益加成6个月（不限投资金额）</li>
                           <li>获得被推荐人每次投资额年化收益2%奖励</li>
                        </ul>
                    </div>
                </div>
                <div class="b_btit b_btit4"></div>
                <p class="b_btrip">注：年化收益加成，是在用户享有产品收益的基础上，由平台补贴给投资用户。</p>
<div class="b_newvip">
                    <div class="b_newer">
                        <p><img src="images/b_news.gif" /></p>
                        <p class="b_newtxt">年化收益率+0.4%<br />（自第一次投资起30天内均可享有新手奖励）<br />（新手奖励可与Vip奖励叠加）</p>
                    </div>
<div class="b_vip">
                        <p><img src="images/b_vip.gif" /></p>
                        <p class="b_newtxt">Vip1 : 1万＜待收本金≤5万<span style="padding-left:31px;">&nbsp;</span>年化收益率+0.4%<br />Vip2 : 5万＜待收本金≤10万<span style="padding-left:23px;">&nbsp;</span>年化收益率+0.8%<br />Vip3 : 待收本金＞10万<span style="padding-left:56px;">&nbsp;</span>年化收益率+1.6%</p>
                    </div>
                </div>
                <p class="b3_but"><a href="borrowList.html" class="b3_button">&nbsp;</a></p>
            </div>
            <!--txt end-->
      </div>
  </div>
    <!--3 end-->
    <div class="space40"></div>
    <!--4 start-->
    <div class="bag_box" id="bag4_box" style="margin-top:-10px;">
        <!--动画 start-->
        <img src="images/b5_img1.png" class="center-block lazy opacity0 fadeInLeft animated b5_img1" data-original="images/b5_img1.png" />
        <img src="images/b5_img2.png" class="center-block lazy opacity0 fadeInDown animated b5_img2" data-original="images/b5_img2.png" />
        <!--动画 end-->
        <div class="b_box">
            <!--tit start-->
          <div class="b_btit b_btit5"></div>
            <!--tit end-->
            <!--txt start-->
            <div class="b_txt b_txt4 clearfix">
                <div class="act_des">
                    <span class="act_ico act_ico1"></span>
                    如何领取投资红包及有效期?<br />
      用户注册投资后，在 [ 个人账户 ] 中领取，点击领取，平台会在两个工作日内将金额打到用户账户上。活动期内均可以任意时间领取
                </div>
                <div class="act_des">
                    <span class="act_ico act_ico2"></span>
                    如何领取推荐现金奖励?<br />
     用户可根据符合规则的推荐人数领取推荐奖励，活动期内有效
                </div>
                <div class="act_des">
                    <span class="act_ico act_ico3"></span>
                    如何理解活动期的Vip收益奖励?<br />
                    <p class="p_des2">用户通过推荐奖励获得的Vip收益奖励，是根据每个用户活动期内推荐总人数来确定的，活动结束后第二天起开始享有相应Vip收益奖励，6个月有效期。</p>
                    <p class="p_des2">活动期间获得的Vip收益奖励，目的是奖励实际投资总额达不到该Vip收益奖励的用户，体验6个月的收益Vip奖励。</p>
                    <p class="p_des2">活动期间，用户若获得Vip1收益奖励，但用户实际投资额度符合Vip2或更高的收益奖励，根据用户权益最大<br />化来界定，最终用户享有的是Vip2或更高的收益奖励，Vip收益奖励之间不可叠加。</p>
                    <p class="p_des2">Vip收益奖励是由P2P额外补贴给投资用户。</p>
              </div>
                <div class="act_des">
                    <span class="act_ico act_ico4"></span>
                    新手收益奖励及有效期?<br />
                    <p class="p_des2">以用户第一次在P2P投资日期起算，一个月内视为新手，可获得年化收益+0.4%的奖励。</p>
                    <p class="p_des2">新手收益奖励是由P2P补贴给投资用户。</p>
              </div>
                <div class="act_des"><span class="act_ico act_ico5"></span>Vip奖励与新手收益奖励可叠加获得</div>
                <p class="act_tel">本活动最终解释权归P2P所有<span style="padding-left:60px;">&nbsp;</span>欢迎拨打热线：<em>400-888-9999</em></p>
          </div>
            <!--txt end-->
      </div>
  </div>
    <!--4 end-->
    <div class="space40"></div>
</div>
<!--底部-->

<!--header start-->
	<jsp:include page="/WEB-INF/view/front/comm/footer.jsp"></jsp:include>
	<!--header end-->
</body>
<script type="text/javascript" src="js/jquery.zclip.js"></script>
<script type="text/javascript">

//复制代码
     $('#fz').zclip({ 
         path: "js/ZeroClipboard.swf", 
         copy: function(){ 
             return $('#abcd').val();
             }
     }); 
</script>
</html>
