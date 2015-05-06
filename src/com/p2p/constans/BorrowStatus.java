package com.p2p.constans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 借款状态（1，申请中，2，初审通过，3，招标中，4，复审中，5，还款中，6，已还款，7，借款失败(初审)，8复审失败）
 * @author Administrator
 *
 */
public class BorrowStatus {
	/**
	 * 1，申请中
	 */
	public static final int APPLY = 1;
	/**
	 * 2，初审通过
	 */
	public static final int AUDIT_ONE_SUCCESS = 2;
	/**
	 * 3，招标中
	 */
	public static final int TENDERING = 3;
	/**
	 * 4，复审中
	 */
	public static final int AUDITING_TWO = 4;
	/**
	 * ，5，还款中
	 */
	public static final int REPAYING =5;
	/**
	 * 6，已还款
	 */
	public static final int REPAY_SUCCESS = 6;
	/**
	 * 7，借款失败(初审)
	 */
	public static final int AUDIT_ONE_FAIL = 7;
	/**
	 * 8复审失败
	 */
	public static final int AUDIT_TWO_FAIL = 8;
	
	/**
	 * 9流标
	 */
	public static final int FLOW = 9;
	
	/**
	 * 10 复审处理中
	 */
	public static final int AUDIT_TWO_DEALING = 10;
	
	/**
	 * 11 流标或复审不通过处理中
	 */
	public static final int FLOW_DEALING = 11;
	

	
	public static final List<Map<String,Object>> BORROW_STATUS_LIST = new ArrayList<Map<String,Object>>();
	
	static{
		Map<String,Object> map = new HashMap<>();
		map.put("borrowStatusName", "申请中");
		map.put("borrowStatus", APPLY);
		BORROW_STATUS_LIST.add(map);
		
		map = new HashMap<>();
		map.put("borrowStatusName", "初审通过");
		map.put("borrowStatus", AUDIT_ONE_SUCCESS);
		BORROW_STATUS_LIST.add(map);
		
		map = new HashMap<>();
		map.put("borrowStatusName", "招标中");
		map.put("borrowStatus", TENDERING);
		BORROW_STATUS_LIST.add(map);
		
		map = new HashMap<>();
		map.put("borrowStatusName", "复审中");
		map.put("borrowStatus", AUDITING_TWO);
		BORROW_STATUS_LIST.add(map);
		
		map = new HashMap<>();
		map.put("borrowStatusName", "还款中");
		map.put("borrowStatus", REPAYING);
		BORROW_STATUS_LIST.add(map);
		
		map = new HashMap<>();
		map.put("borrowStatusName", "已还款");
		map.put("borrowStatus", REPAY_SUCCESS);
		BORROW_STATUS_LIST.add(map);
		
		map = new HashMap<>();
		map.put("borrowStatusName", "借款失败(初审)");
		map.put("borrowStatus", AUDIT_ONE_FAIL);
		BORROW_STATUS_LIST.add(map);
		
		map = new HashMap<>();
		map.put("borrowStatusName", "复审失败");
		map.put("borrowStatus", AUDIT_TWO_FAIL);
		BORROW_STATUS_LIST.add(map);
		
		map = new HashMap<>();
		map.put("borrowStatusName", "流标");
		map.put("borrowStatus", FLOW);
		BORROW_STATUS_LIST.add(map);
		
		map = new HashMap<>();
		map.put("borrowStatusName", "复审处理中");
		map.put("borrowStatus", AUDIT_TWO_DEALING);
		BORROW_STATUS_LIST.add(map);
		
		map = new HashMap<>();
		map.put("borrowStatusName", "流标处理中");
		map.put("borrowStatus", FLOW_DEALING);
		BORROW_STATUS_LIST.add(map);
	}

	
}
