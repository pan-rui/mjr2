package com.p2p.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dao.CNoticeDao;
import com.p2p.model.CNotice;
import com.p2p.mybatis.annotion.SearchOperator;

@Service
public class CNoticeService extends BaseService {
	private static Log log = LogFactory.getLog(CNoticeService.class);
	@Autowired
	private CNoticeDao cNoticeDao;

	public long addCNotice(CNotice cNotice) {
		return cNoticeDao.insert(cNotice);
	}

	public long updateCNotice(CNotice cNotice) {
		return cNoticeDao.update(cNotice);
	}

	public CNotice getCNotice(CNotice cNotice) {
		return cNoticeDao.get(cNotice);
	}

	public PageList queryCNoticePage(CNotice cNotice,
			Map<String, SearchOperator> options, PageBounds pageBounds) {

		List<CNotice> list = cNoticeDao.getAllBy(cNotice, options, pageBounds);
		PageList pageList = (PageList) list;

		return pageList;
	}
	public PageList queryNoticePage(CNotice cNotice,PageBounds pageBounds) {

		List<CNotice> list =cNoticeDao.getAll(cNotice, pageBounds);
		PageList  pageList  =(PageList )list;
		return pageList;
	}

	public List<Long> getAllId(){
		return cNoticeDao.getAllId();
	}
	
	public List<CNotice> getIndexNotice(){
		return cNoticeDao.getIndexNotice();
	}
}
