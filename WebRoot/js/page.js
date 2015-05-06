// JavaScript Document
$(function() {
    //优势第一中方案
	$(".sur_li .sur_img").mouseover(function(){
	  //$(this).hide();						 
	  $(this).siblings(".sur_box").fadeIn();
	});
	$(".sur_li .sur_box").mouseleave(function(){
	  $(this).hide();
	  $(this).siblings(".sur_img").fadeIn();
	});
	
	//预售 鼠标经过
	$(".ys_box").mouseover(function(){
		$(this).find(".yst_ico").addClass("yst_icohover");
		$(this).find(".bn_number").css("color","#f67800");
		$(this).find(".bn_fh").css("color","#f67800");
		$(this).find(".y_totalmoney").css("color","#f67800");
		$(this).find(".ybox_month").css("color","#f67800");
	});
	$(".ys_box").mouseleave(function(){
		$(this).find(".yst_ico").removeClass("yst_icohover");
		$(this).find(".bn_number").css("color","#514f4f");
		$(this).find(".bn_fh").css("color","#514f4f");
		$(this).find(".y_totalmoney").css("color","#514f4f");
		$(this).find(".ybox_month").css("color","#514f4f");
	});
	
	//产品切换tab效果
    $(".p_tabtit .tab_tit").click(function(){
	    var j = $(this).attr("divshow");
		$(".p_txt .p_tabmain").eq(j).fadeIn().siblings(".p_tabmain").hide();
		$(this).addClass("tab_click").siblings(".tab_tit").removeClass("tab_click");
	 });
	
	//产品鼠标经过
	$(".m_pbox1").mouseover(function(){
		$(".m_pbico1").addClass("m_pbico1hover");
	});
	$(".m_pbox1").mouseleave(function(){
		$(".m_pbico1").removeClass("m_pbico1hover");
	});
	$(".m_pbox2").mouseover(function(){
		$(".m_pbico2").addClass("m_pbico2hover");
	});
	$(".m_pbox2").mouseleave(function(){
		$(".m_pbico2").removeClass("m_pbico2hover");
	});
	$(".m_pbox3").mouseover(function(){
		$(".m_pbico3").addClass("m_pbico3hover");
	});
	$(".m_pbox3").mouseleave(function(){
		$(".m_pbico3").removeClass("m_pbico3hover");
	});
	$(".m_pbox4").mouseover(function(){
		$(".m_pbico4").addClass("m_pbico4hover");
	});
	$(".m_pbox4").mouseleave(function(){
		$(".m_pbico4").removeClass("m_pbico4hover");
	});
	
	//会员中心的站内消息
   $(".mess_box .mess_btit").click(
		function() {
			$(this).find(".mtit_ico").addClass("mtit_up").parents(".mess_btit").siblings(".mess_btxt").slideToggle().siblings(".mess_btxt").hide();
		});
    //会员中心问候提示语
	var date = new Date();
	var hours = date.getHours();
	if (hours >= 6 && hours <= 11) {
		$("#mem_trip").html("HI，早上好啊，勤起早啊，和我一起做早操 O(∩_∩)O");
	}
	if (hours > 11 && hours <= 13) {
		$("#mem_trip").html("HI，中午好，吃完午餐睡个美容觉，工作生活会更好  ~@^_^@~");
	}
	if (hours > 13 && hours <= 18) {
		$("#mem_trip").html("HI，下午好，工作的时候记得多喝几杯茶水哦 &( ^___^ )&");
	}
	if (hours > 18 && hours <= 22) {
		$("#mem_trip").html("HI，傍晚好，记得晚餐吃好点哦 ~@^_^@~");
	}
	if (hours > 22 && hours <= 24) {
		$("#mem_trip").html("HI，晚上好，休息时间到了，记得喝杯牛奶在睡觉哦，晚安 o(^_ ^)o");
	}
	if (hours < 6) {
		$("#mem_trip").html("HI，夜已深了，请注意身体，早点休息 π_π");
	}

});

//产品图片滚动代码
$(function(){
					var tLen = 0, 
							vNum = 4, 
							mNum = 1, 
							mTime = 500, 
							iShow = $(".show .itemshow ul"),
							iItems = $(".show .itemshow ul li"),
							mLen = iItems.eq(0).width() * mNum, 
							cLen = (iItems.length - vNum) * iItems.eq(0).width(); 

					iShow.css({width:iItems.length*iItems.eq(0).width()+'px'});
					//下一张
					$(".show .next").on({
						click:function(){
								if(tLen < cLen){
									if((cLen - tLen) > mLen){
										iShow.animate({left:"-=" + mLen + "px"}, mTime);
										tLen += mLen;
									}else{
										iShow.animate({left:"-=" + (cLen - tLen) + "px"}, mTime);
										tLen += (cLen - tLen);
									}
								}
						}
					});
					//上一张
					$(".show .prev").on({
						click:function () {
							if(tLen > 0){
								if(tLen > mLen){
									iShow.animate({left: "+=" + mLen + "px"}, mTime);
									tLen -= mLen;
								}else{
									iShow.animate({left: "+=" + tLen + "px"}, mTime);
									tLen = 0;
								}
							}
						}
					})
				});
//快速借款 复选框
$(function(){
		   $(".l_select .ls_sta").click(
				function() {
					$(this).addClass("ls_click").siblings(".ls_sta").removeClass("ls_click");
			});
		   //通知设置 复选框
		   $(".m_notxt .n_tices").toggle( 
				function () { 
				$(this).removeClass("n_stick"); 
				$(this).children("input").removeAttr("checked"); 
				} , 
				function () { 
				$(this).addClass("n_stick"); 
				$(this).children("input").attr("checked", "checked"); 
				}
		   ); 
		   
		   $('.s_pbut .s_pbutton').on('click', function(){
				$(".reg_hetong .ht_inp").addClass("ht_tick");
		   });
		   //产品切换tab效果
			$(".loan_cont .l_tta").click(function(){
				var j = $(this).attr("divshow");
				$(".loan_cont .loan_main").eq(j).fadeIn().siblings(".loan_main").hide();
				$(this).addClass("l_tclick").siblings(".l_tta").removeClass("l_tclick");
			 });
			//服务资费切换
			$(".l_pro .fwt_tit").click(function(){
				var k = $(this).attr("dirshow");
				$(".fwp_main .m_fwtab").eq(k).fadeIn().siblings(".m_fwtab").hide();
				$(this).addClass("fwt_tick").siblings(".fwt_tit").removeClass("fwt_tick");
			 });
			//服务资费切换
			$(".mg_tit .mgt_t").click(function(){
				var l = $(this).attr("divshow");
				$(".mg_txt .mgtab_txt").eq(l).fadeIn().siblings(".mgtab_txt").hide();
				$(this).addClass("mgt_tick").siblings(".mgt_t").removeClass("mgt_tick");
			 });
			//侧边导航
			
		   })