package com.p2p.web.controller.admin;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dto.InfoMsg;
import com.p2p.model.TCoupon;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.service.TCouponService;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/admin")
public class TCouponController extends BaseController {


	@Autowired
	private TCouponService tCouponService;
 
	@RequestMapping(value = "/queryTCouponList")
	public String queryTCouponList (TCoupon tCoupon) {
		Map<String, SearchOperator> options = new HashMap<String, SearchOperator>(); 
		PageList pageList = tCouponService.queryTCouponPage(tCoupon,pageBounds);
		
		request.setAttribute("pageBean", pageList);
		request.setAttribute("page", pageList.getPaginator());
		request.setAttribute("model", tCoupon);
		return "admin/finance/Coupon-list";
	}
 
	@RequestMapping(value = "/addTCouponIndex")
	public String addTCouponIndex () {
		return "admin/finance/Coupon-add";
	}
 
	@RequestMapping(value = "/addTCoupon")
	public String addTCoupon (TCoupon tCoupon) {
		long result = tCouponService.addTCoupon(tCoupon);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
 
	@RequestMapping(value = "/updateTCouponIndex")
	public String updateTCouponIndex  (TCoupon tCoupon) {
		TCoupon tCouponModel = tCouponService.getTCoupon(tCoupon);
		request.setAttribute("tCouponModel", tCouponModel);
		return "admin/finance/Coupon-update";
	}
 
	@RequestMapping(value = "/updateTCoupon")
	public String updateTCoupon (TCoupon tCoupon) {
		long result = tCouponService.updateTCoupon(tCoupon);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
 
	@RequestMapping(value = "/queryTCouponIndex")
	public String queryTCouponIndex  (TCoupon tCoupon) {
		TCoupon tCouponModel = tCouponService.getTCoupon(tCoupon);
		request.setAttribute("tCouponModel", tCouponModel);
		return "admin/finance/Coupon-detail";
	}
 }