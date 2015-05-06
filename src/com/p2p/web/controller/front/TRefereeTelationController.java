package com.p2p.web.controller.front;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dto.TRefereeRelationDto;
import com.p2p.service.TRefereeRelationService;
import com.p2p.web.controller.BaseController;
@Controller
@Scope("prototype")
@RequestMapping(value = "/")
public class TRefereeTelationController extends BaseController{
	private static Log log = LogFactory.getLog(RechargeController.class);
	@Autowired
	private TRefereeRelationService refereeRelationService;
	
	/**
	 * 查询客户推荐人
	 * @return
	 */
	@RequestMapping(value="center/selectReferee")
	public String selectReferee(TRefereeRelationDto trr){
		trr.setRefereeId(this.getCUser().getId());
		PageList pagelist=refereeRelationService.queryRefereeUserAndTUser2(trr, pageBounds);
		
		
		request.setAttribute("tel", this.getCUser().getCellPhone());
		request.setAttribute("pagebean", pagelist);
		request.setAttribute("page", pagelist.getPaginator());
		request.setAttribute("model", trr);
		return "front/usercenter/user-referee";
	}
	
	/**
	 * 进入首次活动页面
	 * @return
	 */
	@RequestMapping(value="/activityFirst")
	public String activityFirst(){
		String tel="";
		if(this.getCUser()!=null){
			tel=this.getCUser().getCellPhone();
		}
		request.setAttribute("tel", tel);
		return "activity/redaward";
	}
}
