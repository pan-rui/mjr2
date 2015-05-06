package com.p2p.web.controller.front;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.p2p.constans.SysConstans;
import com.p2p.dto.InfoMsg;
import com.p2p.model.CUser;
import com.p2p.model.TApplyBorrow;
import com.p2p.service.TApplyBorrowService;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/")
public class QuickBorrowController extends BaseController {
	private static Log log = LogFactory.getLog(QuickBorrowController.class);

	@Autowired
	private TApplyBorrowService tapplyBorrowService;
	
	/**
	 * 借款中心企业贷款页面
	 * @return
	 */
	@RequestMapping(value="/quickBorrow")
	public String QuickBorrow(){
		return "front/quickborrow/quick-borrow";
	}
	
	/**
	 * 借款中心个人贷款页面
	 * @return
	 */
	@RequestMapping(value="/quickBorrowpersonal")
	public String QuickBorrowpersonal(){
		return "front/quickborrow/quick-borrowpersonal";
	}

	/**
	 * 插入个人借款信息
	 * @param tPersonBorrower
	 * @return
	 */
	@RequestMapping(value="/inserttPersonBorrower")
	public String inserttPersonBorrower(TApplyBorrow tApplyBorrow){
		
		String sqbCode = (String)session.getAttribute(SysConstans.QUICK_BORROW_CODE); 
		String hqbCode = tApplyBorrow.getQbCode();
		if(sqbCode==null||hqbCode==null){
			InfoMsg msg=new InfoMsg("验证码验证错误", "n");
			this.writeJson(msg);
			return null;
		}
		if(!sqbCode.equals(hqbCode)){
			InfoMsg msg=new InfoMsg("验证码验证错误", "n");
			this.writeJson(msg);
			return null;
		}
		CUser cUser = this.getCUser();
		if(cUser!=null)
			tApplyBorrow.setUserId(cUser.getId());
		
		tApplyBorrow.setCreateTime(new Date());
		tApplyBorrow.setBorrowerType(1);
		tApplyBorrow.setHouseProvince("广东省");
		tApplyBorrow.setHouseCity("深圳市"+tApplyBorrow.getHouseCity());
		try{
			Long rest  =tapplyBorrowService.addTApplyBorrow(tApplyBorrow);
			if(rest<=0){
				InfoMsg msg=new InfoMsg("借款信息错误", "n");
				this.writeJson(msg);
				return null;
			}
		} catch (Exception e) {
			log.error("快速借款添加异常", e);
		}
		this.writeJson(new InfoMsg("提交成功", "y"));
		return null;
	}

	/**
	 * 插入企业借款信息
	 * @param tPersonBorrower
	 * @return
	 */
	@RequestMapping(value="insertQyBorrower")
	public String insertQyBorrower(TApplyBorrow tApplyBorrow){

		this.writeJson(new InfoMsg("提交成功", "y"));
		return null;
	}
}
