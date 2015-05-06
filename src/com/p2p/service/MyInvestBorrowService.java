package com.p2p.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dao.MyInvestBorrowDao;
import com.p2p.dto.MyInvestBorrowDto;

@Service
public class MyInvestBorrowService extends BaseService{
	@Autowired
	private MyInvestBorrowDao myInvestBorrowDao;
	
	public PageList selectMyInvest(MyInvestBorrowDto myInvestBorrowDto,PageBounds pageBounds){
		return (PageList)myInvestBorrowDao.selectMyInvest(myInvestBorrowDto, pageBounds);
	}
}
