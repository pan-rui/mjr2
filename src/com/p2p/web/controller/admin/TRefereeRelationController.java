package com.p2p.web.controller.admin;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dto.InfoMsg;
import com.p2p.dto.TRefereeRelationDto;
import com.p2p.model.TRefereeRelation;
import com.p2p.service.TRefereeRelationService;
import com.p2p.web.controller.BaseController;
/**
 * 推荐关系管理
 * @author GUYEU
 *
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/admin")
public class TRefereeRelationController extends BaseController {

	private static Log log = LogFactory.getLog(TRefereeRelationController.class);
	@Autowired
	private TRefereeRelationService tRefereeRelationService;
 
	@RequestMapping(value = "/queryTRefereeRelationList")
	public String queryTRefereeRelationList (TRefereeRelationDto tRefereeRelationDto) {
		PageList pageList = tRefereeRelationService.queryRefereeUserAndTUser(tRefereeRelationDto, pageBounds);
		request.setAttribute("pageBean", pageList);
		request.setAttribute("page", pageList.getPaginator());
		request.setAttribute("model", tRefereeRelationDto);
		return "admin/user/referee-list";
	}
 
	@RequestMapping(value = "/addTRefereeRelationIndex")
	public String addTRefereeRelationIndex () {
		return "admin/user/referee-add";
	}
 
	@RequestMapping(value = "/addTRefereeRelation")
	public String addTRefereeRelation (TRefereeRelation tRefereeRelation) {
		tRefereeRelation.setCreateTime(new Date());
		long result = tRefereeRelationService.addTRefereeRelation(tRefereeRelation);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
 }