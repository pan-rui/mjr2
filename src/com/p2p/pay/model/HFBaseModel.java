package com.p2p.pay.model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public abstract class HFBaseModel {
	public abstract String getMerData();

	protected String getMerData(List<String> fieldNames) {
		Class<?> clz = this.getClass();
		StringBuffer merData = new StringBuffer();

		try {
			for (String fieldName : fieldNames) {

				Method method = clz.getMethod("get" + fieldName);

				String obj = (String) method.invoke(this);
				if (obj != null) {
					merData.append(obj.trim());
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return merData.toString();
	}
}
