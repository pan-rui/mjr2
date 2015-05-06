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
import com.p2p.model.BPara;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.service.BParaService;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/admin")
public class BParaController extends BaseController {
	private static Log log = LogFactory.getLog(BParaController.class);
	@Autowired
	private BParaService bParaService;

	@RequestMapping(value = "/queryBParaList")
	public String queryBParaList(BPara bPara) {
		Map<String, SearchOperator> options = new HashMap<String, SearchOperator>();
		options.put("paraName", SearchOperator.LIKE);
		options.put("paraCode", SearchOperator.LIKE);
		options.put("isDelete", SearchOperator.EQ);
		PageList pageList = bParaService.queryBParaPage(bPara, options,
				pageBounds);

		request.setAttribute("pageBean", pageList);
		request.setAttribute("page", pageList.getPaginator());

		request.setAttribute("model", bPara);
		return "admin/sys/para-list";
	}

	@RequestMapping(value = "/addBParaIndex")
	public String addBParaIndex() {

		return "admin/sys/para-add";
	}

	@RequestMapping(value = "/addBPara")
	public String addBPara(BPara bPara) {

		long result = bParaService.addBPara(bPara);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}

	@RequestMapping(value = "/updateBParaIndex")
	public String updateBParaIndex(BPara bPara) {

		BPara bParaModel = bParaService.getBPara(bPara);
		request.setAttribute("bParaModel", bParaModel);
		return "admin/sys/para-update";
	}

	@RequestMapping(value = "/updateBPara")
	public String updateBPara(BPara bPara) {

		long result = bParaService.updateBPara(bPara);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}

	@RequestMapping(value = "/queryBParaIndex")
	public String queryBParaIndex(BPara bPara) {
		BPara bParaModel = bParaService.getBPara(bPara);
		request.setAttribute("bParaModel", bParaModel);
		return "admin/sys/para-detail";
	}
}
