package com.p2p.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dao.BParaDao;
import com.p2p.model.BPara;
import com.p2p.mybatis.annotion.SearchOperator;

@Service
public class BParaService extends BaseService {
	private static Log log = LogFactory.getLog(BParaService.class);
	@Autowired
	private BParaDao bParaDao;
	
	public long addBPara(BPara bPara){
		return bParaDao.insert(bPara);
	}
	
	public long updateBPara(BPara bPara){
		return bParaDao.update(bPara);
	}
	
	public BPara getBPara(BPara bPara){
		return bParaDao.get(bPara);
	}

	public PageList queryBParaPage(BPara bPara,
			Map<String, SearchOperator> options, PageBounds pageBounds) {
		
		List<BPara> list =bParaDao.getAllBy(bPara, options, pageBounds);
		PageList  pageList  =(PageList )list;
		
		return pageList;
	}

	public List<BPara> queryBparaByCode(String paraCode) {
		
		return bParaDao.queryBparaByCode(paraCode);
	}

	
	
	
}
