package com.p2p.web.controller.front;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.p2p.dto.InfoMsg;
import com.p2p.model.CMessage;
import com.p2p.model.CUser;
import com.p2p.model.TCoupon;
import com.p2p.service.TCouponService;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/")
public class CenterRedenvelopesController extends BaseController{
	private static Log log = LogFactory.getLog(BorrowController.class);
	
	@Autowired
	private TCouponService tCouponService;
	/**
	 * 初始化用户红包页面
	 * @param cMessage
	 * @return
	 */
	@RequestMapping(value="center/selectUserRedenvelopes")
	public String selectUserRedenvelopes(CMessage cMessage){
		try {
			CUser cUser = this.getCUser();
			List<TCoupon> tclist = tCouponService.queryUserRedenvelopes(cUser.getId());
			request.setAttribute("UserRedPaper", tclist);
		} catch (Exception e) {
			log.error("进入用户红包异常：", e);
		}
		return "front/usercenter/user-gift";
	}
	/**
	 * 用户点击领取红包
	 * @return
	 */
	@RequestMapping(value="center/userApplyRedPaper")
	public String userApplyRedPaper(){
		String rpID = request.getParameter("redPaperId");
		TCoupon tCoupon = new TCoupon();
		if(StringUtils.isBlank(rpID)){
			this.writeJson(new InfoMsg("服务器忙碌请稍后重试", "n"));
			return null;
		}
		tCoupon.setId(Long.parseLong(rpID));
		tCoupon = tCouponService.getTCoupon(tCoupon);
		if(tCoupon==null){
			this.writeJson(new InfoMsg("服务器忙碌请稍后重试", "n"));
			return null;
		}
		tCoupon.setCouponStatus(2);
		Long rest = tCouponService.updateTCoupon(tCoupon);
		if(rest<=0){
			this.writeJson(new InfoMsg("服务器忙碌请稍后重试", "n"));
			return null;
		}
		this.writeJson(new InfoMsg("ok", "y"));
		return null;
	}
	
}
