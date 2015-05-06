package com.p2p.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dto.MyInvestBorrowDto;
import com.p2p.service.MyInvestBorrowService;
import com.p2p.web.controller.BaseController;
import com.sun.org.apache.commons.logging.Log;
import com.sun.org.apache.commons.logging.LogFactory;

@Controller
@Scope("prototype")
@RequestMapping(value = "/")
public class MyInvestBorrowController extends BaseController{
	private static Log log=LogFactory.getLog(MyInvestBorrowController.class);
	@Autowired
	private MyInvestBorrowService mibs;
	
	/**
	 * 我的投资招标中的借款
	 * @param myInvestBorrowDto
	 * @return
	 */
	@RequestMapping(value="center/selectMyInvest")
	public String selectMyInvest(MyInvestBorrowDto myInvestBorrowDto){
		PageList pagelist=mibs.selectMyInvest(myInvestBorrowDto, pageBounds);
		request.setAttribute("bean", pagelist);
		request.setAttribute("page", pagelist.getPaginator());
		request.setAttribute("model", myInvestBorrowDto);
		return "";
	}
}
