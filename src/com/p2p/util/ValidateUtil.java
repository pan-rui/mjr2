package com.p2p.util;

public class ValidateUtil {
	public static boolean validateCellPhone(String cellPhone){
		if(cellPhone == null){
			return false;
		}
		return cellPhone.matches("^13[0-9]{9}$|14[0-9]{9}|15[0-9]{9}$|18[0-9]{9}$");
	}
}
