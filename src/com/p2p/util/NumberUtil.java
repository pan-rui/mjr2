package com.p2p.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class NumberUtil {
	private static DecimalFormat df = new DecimalFormat("0.00");
	private static DecimalFormat df_show = new DecimalFormat("#,##0.00");
	public static String fomatAmount(BigDecimal amount){
		return df.format(amount);
	}
	
	public static String fomatShowAmount(BigDecimal amount){
		return df_show.format(amount);
	}
	
}
