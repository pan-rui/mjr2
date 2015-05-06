package com.p2p.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dao.CUserDao;
import com.p2p.dao.CUserDetailDao;
import com.p2p.dao.CUserLoginRecordDao;
import com.p2p.dao.TPersonDao;
import com.p2p.dto.InfoMsg;
import com.p2p.dto.UserDto;
import com.p2p.exception.ZeroSQLException;
import com.p2p.model.CUser;
import com.p2p.model.CUserDetail;
import com.p2p.model.CUserLoginRecord;
import com.p2p.model.TAccount;
import com.p2p.model.TNoticeSetting;
import com.p2p.model.TPerson;
import com.p2p.model.TRefereeRelation;
import com.p2p.model.TThridAccount;
import com.p2p.util.DateUtil;

@Service
public class CUserService extends BaseService {
	private static Log log = LogFactory.getLog(CUserService.class);
	@Autowired
	private CUserDao cUserDao;
	@Autowired
	private TPersonDao tPersonDao;
	
	@Autowired
	private CUserLoginRecordDao cUserLoginRecordDao;
	
	@Autowired
	private CUserDetailDao cUserDetailDao;
	@Autowired
	private TAccountService tAccountService;
	
	@Autowired
	private TNoticeSettingService tNoticeSettingService;
	
	@Autowired
	private TThridAccountService tThridAccountService;
	@Autowired
	private TRefereeRelationService refereeRelationService;
	/**
	 * 注册
	 * @param cUser
	 * @return
	 */
	public long addCUser(CUser cUser){
		
		long result = cUserDao.insert(cUser);
		if(result<=0){
			InfoMsg msg = new InfoMsg("注册失败", "n");
			throw new ZeroSQLException(msg);
		}
		CUserDetail cUserDetail = new CUserDetail();
		cUserDetail.setUserId(cUser.getId());
		cUserDetail.setScore(0);
		result = cUserDetailDao.insert(cUserDetail);
		if(result<=0){
			InfoMsg msg = new InfoMsg("注册失败", "n");
			throw new ZeroSQLException(msg);
		}
		
		TNoticeSetting tNoticeSetting = new TNoticeSetting();
		tNoticeSetting.setIsEmail(0);
		tNoticeSetting.setIsMessage(1);
		tNoticeSetting.setIsSms(1);
		tNoticeSetting.setUserId(cUser.getId());
		tNoticeSetting.setLastUpdateTime(new Date());
		result = tNoticeSettingService.addTNoticeSetting(tNoticeSetting);
		if(result<=0){
			InfoMsg msg = new InfoMsg("注册失败", "n");
			throw new ZeroSQLException(msg);
		}
		
		//初始化账户
		TAccount tAccount = new TAccount();
		tAccount.setCreateTime(new Date());
		tAccount.setUserId(cUser.getId());
		result = tAccountService.addTAccount(tAccount);
		if(result<=0){
			
			InfoMsg msg = new InfoMsg("注册失败", "n");
			throw new ZeroSQLException(msg);
		}
		
		//初始化第三方托管
		TThridAccount tThridAccount = new TThridAccount();
		tThridAccount.setUserId(cUser.getId());
		result = tThridAccountService.addTThridAccount(tThridAccount);
		if(result<=0){
			InfoMsg msg = new InfoMsg("注册失败", "n");
			throw new ZeroSQLException(msg);
		}
		
		if(StringUtils.isNotBlank(cUser.getReferee())){
			
			Long refereeId=cUserDao.selectallfortel(cUser.getReferee());
			if(refereeId==null){
				InfoMsg msg = new InfoMsg("推荐人不存在", "n");
				throw new ZeroSQLException(msg);
			}
			TRefereeRelation tre=new TRefereeRelation();
			tre.setUserId(cUser.getId());
			tre.setRefereeId(refereeId);
			tre.setCreateTime(new Date());
			result =refereeRelationService.addTRefereeRelation(tre);
			if(result<=0){
				InfoMsg msg = new InfoMsg("注册失败", "n");
				throw new ZeroSQLException(msg);
			}
		}
		
		
		return result;
	}
	
	public long updateCUser(CUser cUser){
		return cUserDao.update(cUser);
	}
	
	public long updateUserAndPerson (CUser cUser,TPerson tPerson){
		long result = cUserDao.update(cUser);
		if(result<0){
			throw new ZeroSQLException();
		}
		result= tPersonDao.update(tPerson);
		if(result<0){
			throw new ZeroSQLException();
		}
		return result;
	}
	
	/**
	 * 根据用户id查询用户详细信息
	 * @param cUser
	 * @return
	 */
	public UserDto getCUser(Long userid){
		return cUserDao.getOneUser(userid);
	}

	public PageList queryCUserPage(UserDto cUser,PageBounds pageBounds) {		
		return (PageList)cUserDao.queryCUserPage(cUser, pageBounds);
	}

	public boolean isExistsUser(CUser cUser){
		int count = cUserDao.isExistsUser(cUser);
		boolean result = false;
		if(count > 0){
			result = true;
		}
		return result;
	}

	public CUser queryLoginUser(CUser cUser) {
		return cUserDao.queryLoginUser(cUser);
	}

	/**
	 * 登录调用
	 * @param cLoginUser
	 * @return
	 */
	public long updateLoginCUser(CUser cLoginUser) {
		long result = -1;
		CUser cUser = new CUser();
		cUser.setId(cLoginUser.getId());
		String ip = cLoginUser.getLastLoginIp();
		cUser.setLastLoginIp(ip);
		Date nowDate = cLoginUser.getLastLoginTime();
		cUser.setLastLoginTime(nowDate);
		result = cUserDao.update(cUser);
		if(result<=0){
			throw new ZeroSQLException();
		}
		CUserLoginRecord cUserLoginRecord = new CUserLoginRecord();
		cUserLoginRecord.setCreateTime(nowDate);
		cUserLoginRecord.setLoginIp(ip);
		cUserLoginRecord.setUserId(cLoginUser.getId());
		result = cUserLoginRecordDao.insert(cUserLoginRecord);
		if(result<=0){
			throw new ZeroSQLException();
		}
		return result;
		
	}

	/*注册方法*/
	/**
	 * 验证新用户注册手机号码是否存在
	 * @param cUser
	 * @return
	 */
	public CUser verificationNewUserPhone(CUser cUser){
		return cUserDao.verificationNewUserPhone(cUser);
	}
	
	/**
	 * 修改密码
	 * @param cuser
	 * @return
	 */
	public int updatepwd(CUser cuser){
		return cUserDao.updatepwd(cuser);
	}
	public String selectPwd(CUser cuser){
		return cUserDao.selectPwd(cuser);
	}
	
	//查询用户资金表
	public TAccount selectUserMoney(Long userId){
		return cUserDao.selectUserMoney(userId);
	}
	
	//查询待收本金
	public BigDecimal selectDueinCorpus(Long investorId){
		return cUserDao.selectDueinCorpus(investorId);
	}
	//查询待收利息
		public BigDecimal selectDueinInterest(Long investorId){
			return cUserDao.selectDueinInterest(investorId);
		}
	
		//查询已收利息
		public BigDecimal selectFinishInterest(Long investorId){
			return cUserDao.selectFinishInterest(investorId);
		}
		
	//查询用户累计投资金额
		public BigDecimal selectAllInvestMoney(Long investorId){
			return cUserDao.selectAllInvestMoney(investorId);
		}
		//查询投资笔数
		public int selectInvestSum(Long investorId){
			return cUserDao.selectInvestSum(investorId);
		}
		//查询已回款的投资数
		public int selectInvestSumReceived(Long investorId){
			return cUserDao.selectInvestSumReceived(investorId);
		}
		//查询待回款的投资数
		public int selectInvestSumNoReceived(Long investorId){
			return cUserDao.selectInvestSumNoReceived(investorId);
		}

		public boolean isExistUserName(String userName) {
			return cUserDao.isExistUserName(userName) >0;
		}
		
		public long selectallfortel(String cellPhone){
			return cUserDao.selectallfortel(cellPhone);
		}
		
		/**
		 * 修改vip等级
		 * @param userId
		 * @return
		 */
		public InfoMsg updateAwardTimeLevel(Long userId){
			try{
			//得到推荐人的id
				
			Long refereeId=refereeRelationService.selectrefereeIdforuserId(userId);
			if(refereeId==null||refereeId<0){
				InfoMsg msg=new InfoMsg("此用户没有推荐人", "y");
				return msg;
			}
			//得到用户当前vip等级
			String vip=cUserDao.selectvipforid(refereeId);
			
			//根据用户id去查询所有推荐人数的投资数
			Long countsum=cUserDao.selectconutinvestforid(refereeId);
			String szvip="V0";
			int dy=0;
			if(countsum>=5&&countsum<10&&vip.equals("V0")){
				//更改vip状态为V1
				szvip="V1";
				dy=1;
			}else if(countsum>=10&&countsum<30&&vip.equals("V1")){
				//更改vip状态为V2
				szvip="V2";
				dy=1;
			}else if(countsum>30&&vip.equals("V2")){
				//更改vip状态为V3
				szvip="V3";
				dy=1;
			}
			
			if(dy==1){
				//执行修改操作
				int i=cUserDao.updatevipzt(szvip, refereeId,DateUtil.dateAddMonth(new Date(), 6));
				if(i<0){
					InfoMsg msg=new InfoMsg("修改vip失败", "n");
					return msg;
				}
			}
			}catch (Exception e) {
				// TODO: handle exception
				log.error("修改vip状态异常", e);
				throw e;
			}
			InfoMsg msg=new InfoMsg("修改vip完成", "y");
			return msg;
		}
		
		/**
		 * 定时移除过期的vip
		 */
		public void updatevipdown(){
			List<CUserDetail> list=cUserDao.selectvipzt();
			try{
			if(list!=null){
				for (CUserDetail cUserDetail : list) {
					cUserDao.updatevipzt2(cUserDetail.getUserId());
					
				}
			}
			}catch (Exception e) {
				log.error("移除过期vip出问题",e);
				throw e;
			}
			
		}
		
		/**
		 * 定时移除过期新手奖励
		 */
		public void deleteNovicesReward(){
			try{
			List<CUserDetail> list=cUserDao.selectisNew(DateUtil.dateAddMonth(new Date(), 1));
			if(list!=null){
				for (CUserDetail cUserDetail : list) {
					cUserDao.updateisNew(cUserDetail.getUserId());
				}
			}
			}catch (Exception e) {
				log.error("移除过期新手奖励异常", e);
				throw e;
			}
		}
		
		public Long selectisNewforuserid(Long userId){
			return cUserDao.selectisNewforuserid(userId);
		}
		/**
		 * 获取全部用户id
		 * @return
		 */
		public List<Long> queryAlluserid(){
			return cUserDao.queryAlluserid();
		};
}
