package com.p2p.pay.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import chinapnr.SecureLink;

import com.alibaba.fastjson.JSONObject;
import com.dib.dataservice.constant.HFConfig;
import com.dib.dataservice.util.ReflectUtil;
import com.dib.dataservice.util.http.HttpClientUtil;
import com.p2p.pay.model.HFBaseModel;
import com.p2p.pay.model.HFModel;
import com.p2p.pay.model.HFModelBack;

public class HFUtil {
	private static final Log log = LogFactory.getLog(HFUtil.class);

	public static Map<String, String> hfModelToMap(HFBaseModel hfBaseModel) {
		Map<String, String> map = new HashMap<String, String>();
		if (hfBaseModel != null) {
			List<Field> fieldList = ReflectUtil.getAllFields(hfBaseModel);
			try {
				for (Field field : fieldList) {
					Object value = ReflectUtil
							.getFieldValue(hfBaseModel, field);
					if (value != null) {
						map.put(field.getName(), ((String) value).trim());
					}
				}
			} catch (IllegalArgumentException e) {
				log.error(e);
			}
		}
		return map;
	}

	public static HFModelBack mapToHFModelBack(Map<String, String> map) {
		log.info("汇付参数：" + map);
		HFModelBack hfModelBack = null;
		if (HFConfig.SUCCESS_RESPCODE.equals(map.get("RespCode"))) {
			String cmdId = map.get("CmdId");
			hfModelBack = ReflectUtil
					.newnewInstance("com.dib.dataservice.pay.model.back."
							+ cmdId + "Back");
			List<Field> fieldList = ReflectUtil.getAllFields(hfModelBack);
			for (Field field : fieldList) {
				ReflectUtil.setFieldValue(hfModelBack, field,
						map.get(field.getName()));
			}
			if (hfModelBack != null) {
				int ret = veriSignMsg(hfModelBack.getMerData(),
						hfModelBack.getChkValue());
				if (ret != 0) {
					hfModelBack = null;
					log.error("加密异常：" + map);
				}
			}

		} else {
			log.error("返回参数失败：" + map.get("RespCode") + ",描述："
					+ map.get("RespDesc"));
		}

		return hfModelBack;
	}

	public static HFModelBack jsonToHFModelBack(String json) {
		JSONObject jsonObject = JSONObject.parseObject(json);
		Set<String> keySet = jsonObject.keySet();
		Map<String, String> map = new HashMap<String, String>();
		for (String key : keySet) {
			map.put(key, jsonObject.getString(key));
		}
		return mapToHFModelBack(map);
	}

	/**
	 * 
	 * @param MerData
	 *            ：自定义拼装字符串
	 * @param ChkValue
	 *            :汇付返回的chkValue
	 * @return 回调签名验证
	 */
	public static int veriSignMsg(String MerData, String ChkValue) {
		return new SecureLink().VeriSignMsg(HFConfig.PgKeyFile, MerData,
				ChkValue);
	}

	public static HFModelBack sendHF(HFModel hfModel) {
		SecureLink sl = new SecureLink();
		int ret = sl.SignMsg(HFConfig.MerId, HFConfig.MerKeyFile,
				hfModel.getMerData());// 商户号、商户密匙文件、签名内容
		log.info("签名结果：" + ret);

		hfModel.setChkValue(sl.getChkValue());
		Map<String, String> map = HFUtil.hfModelToMap(hfModel);
		String json = null;
		try {
			json = HttpClientUtil.doPostHttps(HFConfig.HFReqURL, map, "UTF-8");
			log.info("汇付返回参数:" + json);
		} catch (IOException e) {
			log.error(e);
			e.printStackTrace();
		}
		HFModelBack hfModelBack = jsonToHFModelBack(json);
		return hfModelBack;
	}

}
