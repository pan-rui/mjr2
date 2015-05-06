package com.p2p.web.controller.admin;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dto.InfoMsg;
import com.p2p.model.BScore;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.service.BScoreService;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/admin")
public class BScoreController extends BaseController {
	private static Log log = LogFactory.getLog(BScoreController.class);

	@Autowired
	private BScoreService bScoreService;
 
	@RequestMapping(value = "/queryBScoreList")
	public String queryBScoreList (BScore bScore) {
		Map<String, SearchOperator> options = new HashMap<String, SearchOperator>(); 
		PageList pageList = bScoreService.queryBScorePage(bScore, options,pageBounds);

		request.setAttribute("pageBean", pageList);
		request.setAttribute("page", pageList.getPaginator());
		request.setAttribute("model", bScore);
		return "admin/xx/xx-list";
	}
 
	@RequestMapping(value = "/addBScoreIndex")
	public String addBScoreIndex () {
		return "admin/xx/xx-add";
	}
 
	@RequestMapping(value = "/addBScore")
	public String addBScore (BScore bScore) {
		long result = bScoreService.addBScore(bScore);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
 
	@RequestMapping(value = "/updateBScoreIndex")
	public String updateBScoreIndex  (BScore bScore) {
		BScore bScoreModel = bScoreService.getBScore(bScore);
		request.setAttribute("bScoreModel", bScoreModel);
		return "admin/xx/xx-update";
	}
 
	@RequestMapping(value = "/updateBScore")
	public String updateBScore (BScore bScore) {
		long result = bScoreService.updateBScore(bScore);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
 
	@RequestMapping(value = "/queryBScoreIndex")
	public String queryBScoreIndex  (BScore bScore) {
		BScore bScoreModel = bScoreService.getBScore(bScore);
		request.setAttribute("bScoreModel", bScoreModel);
		return "admin/xx/xx-detail";
	}
 }