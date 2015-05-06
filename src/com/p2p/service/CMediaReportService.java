package com.p2p.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dao.CMediaReportDao;
import com.p2p.model.CMediaReport;
import com.p2p.mybatis.annotion.SearchOperator;

@Service
public class CMediaReportService extends BaseService {
	private static Log log = LogFactory.getLog(CMediaReportService.class);
	@Autowired
	private CMediaReportDao cMediaReportDao;
	
	public long addCMediaReport(CMediaReport cMediaReport){
		return cMediaReportDao.insert(cMediaReport);
	}
	
	public long updateCMediaReport(CMediaReport cMediaReport){
		return cMediaReportDao.update(cMediaReport);
	}
	
	public CMediaReport getCMediaReport(CMediaReport cMediaReport){
		return cMediaReportDao.get(cMediaReport);
	}

	public PageList queryCMediaReportPage(CMediaReport cMediaReport,
			Map<String, SearchOperator> options, PageBounds pageBounds) {
		
		List<CMediaReport> list =cMediaReportDao.getAllBy(cMediaReport, options, pageBounds);
		PageList  pageList  =(PageList )list;
		
		return pageList;
	}
	public PageList queryMediaReportPage(CMediaReport cMediaReport,PageBounds pageBounds) {
		
		List<CMediaReport> list =cMediaReportDao.getAllMedia(cMediaReport, pageBounds);
		PageList  pageList  =(PageList )list;
		
		return pageList;
	}
	
	public List<Long> getAllId(){
		return cMediaReportDao.getAllId();
	}
	
	public List<CMediaReport> queryIndexNews(){
		return cMediaReportDao.queryIndexNews();
	}
}
