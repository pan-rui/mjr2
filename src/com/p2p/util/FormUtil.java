package com.p2p.util;

import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/* *
 *支付表单
 */

public class FormUtil {
	private static Log log = LogFactory.getLog(FormUtil.class);
   

    /**
     * 构造提交表单HTML数据
     * @param sParaTemp 请求参数数组
     * @param gateway 网关地址
     * @param strMethod 提交方式。两个值可选：post、get
     * @param strButtonName 确认按钮显示文字
     * @return 提交表单HTML文本
     */
    public static String buildForm(Map<String, String> sParaTemp, String gateway, String strMethod,
                                   String strButtonName) {
        //待请求参数数组
        Set<String> keys = sParaTemp.keySet();

        StringBuffer sbHtml = new StringBuffer();

        sbHtml.append("<form id=\"editForm\" name=\"editForm\" action=\"");
        sbHtml.append(gateway);
        sbHtml.append("\" method=\""); 
        sbHtml.append(strMethod);
        sbHtml.append( "\">");
        log.info("=============发送参数===start========");
        for (String name : keys) {
            String value =  sParaTemp.get(name);
            sbHtml.append("<input type=\"hidden\" name=\"");
            sbHtml.append(name);
            sbHtml.append("\" value=\""); 
            sbHtml.append(value);
            sbHtml.append("\"/>");
            log.info(name+"============="+value);
        }
        log.info("=============发送参数===end========");
        //submit按钮控件请不要含有name属性
        sbHtml.append("<input type=\"submit\" value=\"");
        sbHtml.append(strButtonName );
        sbHtml.append("\" style=\"display:none;\"></form>");
        sbHtml.append("<script>document.forms['editForm'].submit();</script>");

        return sbHtml.toString();
    }
    
    public static String buildHtmlForm(Map<String, String> sParaTemp, String gateway, String method) {
    	StringBuffer htmlBuf = new StringBuffer();
    	htmlBuf.append("<html>");
		htmlBuf.append(" <head><title>paysender</title></head>");
		htmlBuf.append(" <body>");
		htmlBuf.append(buildForm(sParaTemp,gateway,method,"提交"));
		htmlBuf.append(" </body>");
		htmlBuf.append("</html>");
    	return htmlBuf.toString();
    }

    

   
}
