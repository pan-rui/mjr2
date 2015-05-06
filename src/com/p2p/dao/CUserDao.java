package com.p2p.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.p2p.dto.UserDto;
import com.p2p.model.CUser;
import com.p2p.model.CUserDetail;
import com.p2p.model.TAccount;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.mybatis.util.SQLProvider;

/** 用户 **/
@Repository
public interface CUserDao extends BaseDao<CUser> {

	@SelectProvider(type = SQLProvider.class, method = "getAll")
	public List<CUser> getAll(CUser cUser, PageBounds row);

	@SelectProvider(type = SQLProvider.class, method = "getAllBy")
	public List<CUser> getAllBy(CUser cUser,
			Map<String, SearchOperator> options, PageBounds row);

	@SelectProvider(type = SQLProvider.class, method = "get")
	public CUser get(CUser cUser);

	/**
	 * 
	 * @param cUser
	 * @param options
	 * @param row
	 * @return
	 */
	public List<CUser> queryCUserPage(UserDto cUser, PageBounds row);

	/**
	 * 根据用户id查询用户详细信息
	 * 
	 * @param cUser
	 * @return
	 */
	public UserDto getOneUser(Long userid);

	public int isExistsUser(CUser cUser);

	public CUser queryLoginUser(CUser cUser);

	public CUser verificationNewUserPhone(CUser cUser);

	/**
	 * 修改密码
	 * 
	 * @param cuser
	 * @return
	 */
	public int updatepwd(CUser cuser);

	public String selectPwd(CUser cuser);

	// 查询账户余额
	public TAccount selectUserMoney(Long userId);

	// 查询待收本金
	public BigDecimal selectDueinCorpus(Long investId);

	// 查询待收利息
	public BigDecimal selectDueinInterest(Long investId);

	// 查询已收利息
	public BigDecimal selectFinishInterest(Long investId);

	// 查询用户累计投资金额
	public BigDecimal selectAllInvestMoney(Long investId);

	// 查询投资笔数
	public int selectInvestSum(Long investId);

	// 查询已回款的投资数
	public int selectInvestSumReceived(Long investId);

	// 查询待回款的投资数
	public int selectInvestSumNoReceived(Long investId);

	public Integer isExistUserName(String userName);

	
	
	public Long selectallfortel(String cellPhone);
	
	public Long selectconutinvestforid(Long userId);
	
	public String selectvipforid(Long userId);
	
	public int updatevipzt(@Param("awardTimeLevel")String awardTimeLevel,@Param("userId")Long userId,@Param("awardExpirationDate")Date awardExpirationDate);
	
	public List<CUserDetail> selectvipzt();
	
	public int updatevipzt2(Long userId);
	
	public List<CUserDetail> selectisNew(Date investTime);
	
	public int updateisNew(Long userId);
	
	public Long selectisNewforuserid(Long userId);
	
	public List<Long> queryAlluserid();
}