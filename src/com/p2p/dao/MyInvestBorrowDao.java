package com.p2p.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.p2p.dto.MyInvestBorrowDto;
@Repository
public interface MyInvestBorrowDao extends BaseDao<MyInvestBorrowDto>{
	public List<MyInvestBorrowDto> selectMyInvest(MyInvestBorrowDto myInvestBorrowDto,PageBounds pageBounds);
}
