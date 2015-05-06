package com.p2p.web.controller.front;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.model.CMediaReport;
import com.p2p.model.CNotice;
import com.p2p.service.CMediaReportService;
import com.p2p.service.CNoticeService;
import com.p2p.util.HtmlUtil;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/")
public class MediaController extends BaseController {
	private static Log log = LogFactory.getLog(MediaController.class);
	@Autowired
	private CMediaReportService cMediaReportService;
	@Autowired
	private CNoticeService cNoticeService;
	
	@RequestMapping(value = "/newsIndex")
	public String newsIndex(CMediaReport cMediaReport) {
		PageList pageList = cMediaReportService.queryMediaReportPage(cMediaReport,pageBounds);
		for (Object object : pageList) {
			
		}
		request.setAttribute("pageBean", pageList);
		request.setAttribute("page", pageList.getPaginator());
		return "front/media/news-list";
	}
	
	@RequestMapping(value = "/showOneNews")
	public String showOneNews(CMediaReport cMediaReport){
		Long newsid = Long.parseLong(request.getParameter("newId"));
		cMediaReport.setId(1l);//防止一下自认为专业的人士吧
		if(newsid!=null){
			cMediaReport.setId(newsid);
		}
		CMediaReport cMediaReportModel = cMediaReportService.getCMediaReport(cMediaReport);
		request.setAttribute("cMediaReportModel", cMediaReportModel);
		request.setAttribute("metaContent", HtmlUtil.getTextFromHtml(cMediaReportModel.getContent()));
		//更新访问量
		int newsShow = (cMediaReportModel.getViewCount()+1);
		cMediaReportModel.setViewCount(newsShow);
		cMediaReportService.updateCMediaReport(cMediaReportModel);
		//获取上下条
		Long minid=0L;
		Long maxid=100000L;
		List<Long> maxquestion = new ArrayList<Long>() ,minquestion = new ArrayList<Long>();
		List<Long> allid = cMediaReportService.getAllId();
		for (Long long1 : allid) {
			if(long1<newsid){
				minquestion.add(long1);
			}else if(long1>newsid){
				maxquestion.add(long1);
			}
		}
		/**
		 * 获取上一条问题的id
		 */
		if(minquestion!=null && minquestion.size()>0){
			for (Long long1 : minquestion) {
				if (long1>minid) {
					minid=long1;
				}
			}
		}
		/**
		 * 获取下一条问题的id
		 */
		if(maxquestion!=null && maxquestion.size()>0){
			for (Long long1 : maxquestion) {
				if (long1<maxid) {
					maxid=long1; 
				}
			}
		}
		cMediaReport.setId(minid);
		request.setAttribute("minMediaModel", cMediaReportService.getCMediaReport(cMediaReport));
		cMediaReport.setId(maxid);
		request.setAttribute("maxMediaModel", cMediaReportService.getCMediaReport(cMediaReport));
		return "front/media/news-detail";
	}
	/**
	 * 平台公告首页
	 * @param cNotice
	 * @return
	 */
	@RequestMapping(value = "/noticeIndex")
	public String noticeIndex(CNotice cNotice){
	
		PageList pageList = cNoticeService.queryNoticePage(cNotice, pageBounds);
		request.setAttribute("pageBean", pageList);
		request.setAttribute("page", pageList.getPaginator());
		return "front/media/notice-list";
	}
	
	@RequestMapping(value = "/showOneNotice")
	public String showOneNotice(CNotice cNotice){
		Long noticeid = Long.parseLong(request.getParameter("noticeId"));
		cNotice.setId(1l);
		if(noticeid!=null){
			cNotice.setId(noticeid);
		}
		CNotice notice = cNoticeService.getCNotice(cNotice);
		request.setAttribute("CNoticeModel", notice);
		request.setAttribute("metaContent", HtmlUtil.getTextFromHtml(notice.getNoticeContent()));
		//获取上下条
		Long minid=0L;
		Long maxid=100000L;
		List<Long> maxquestion = new ArrayList<Long>() ,minquestion = new ArrayList<Long>();
		List<Long> allid = cNoticeService.getAllId();
		for (Long long1 : allid) {
			if(long1<noticeid){
				minquestion.add(long1);
			}else if(long1>noticeid){
				maxquestion.add(long1);
			}
		}
		/**
		 * 获取上一条问题的id
		 */
		if(minquestion!=null && minquestion.size()>0){
			for (Long long1 : minquestion) {
				if (long1>minid) {
					minid=long1;
				}
			}
		}
		/**
		 * 获取下一条问题的id
		 */
		if(maxquestion!=null && maxquestion.size()>0){
			for (Long long1 : maxquestion) {
				if (long1<maxid) {
					maxid=long1; 
				}
			}
		}
		cNotice.setId(minid);
		request.setAttribute("minNoticeModel", cNoticeService.getCNotice(cNotice));
		cNotice.setId(maxid);
		request.setAttribute("maxNoticeModel", cNoticeService.getCNotice(cNotice));
		return "front/media/notice-detail";
	}
}
