package com.p2p.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dto.BorrowDto;
import com.p2p.model.TBorrow;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.mybatis.util.SQLProvider;


/** 借款表**/
@Repository
public interface TBorrowDao extends BaseDao<TBorrow>{

	@SelectProvider(type = SQLProvider.class,method = "getAll")
	public List<TBorrow> getAll(TBorrow tBorrow,PageBounds row);

	@SelectProvider(type = SQLProvider.class,method = "getAllBy")
	public List<TBorrow> getAllBy(TBorrow tBorrow, Map<String,SearchOperator> options, PageBounds row);


	@SelectProvider(type = SQLProvider.class,method = "get")
	public TBorrow get(TBorrow tBorrow);
	
	public int selectidfortitle(String borrowTitle);

	public long updateHasBorrowAmount(TBorrow tBorrow);

	public long updateFullBorrow(TBorrow tBorrow);

	public long updateBorrowAuditTwo(TBorrow auditBorrow);

	public long updateRepayAllBorrow(Long borrowId);

	public PageList<BorrowDto> queryBorrowDtoPage(BorrowDto borrowDto, PageBounds pageBounds);

	public long updateBorrowFirstAudit(TBorrow auditBorrow);

	public long updateBorrowStartInvest(Long borrowId);

	/**
	 * 查询初审通过的标的
	 * @return
	 */
	public List<TBorrow> queryTBorrowListByInvestDate();

	public PageList<TBorrow> queryTBorrowListPage(BorrowDto borrowDto,
			PageBounds pageBounds);
	
	public List<TBorrow> queryIndexTBorrowListPage(BorrowDto borrowDto);

	public long updateBorrowFullAuditBack(TBorrow auditBorrow);

	public List<TBorrow> queryTBorrowList(TBorrow tBorrow);

	public long updateBorrowFlowAuditBack(TBorrow auditBorrow);

	public TBorrow getTBorrowByOrdId(String ordId);

	public long updateBorrowResetBackFullStatus(Long id);

	public long updateClockAmount(@Param("borrowId")Long borrowId, @Param("clockAmount")BigDecimal clockAmount);
}