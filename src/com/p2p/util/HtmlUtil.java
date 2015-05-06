package com.p2p.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlUtil {
	   private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式  
	    private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式  
	    private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式  
	    private static final String regEx_space = "\\s*|\t|\r|\n";//定义空格回车换行符  
	      
	    /** 
	     * @param htmlStr 
	     * @return 
	     *  删除Html标签 
	     */  
	    public static String delHTMLTag(String htmlStr) {  
	        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);  
	        Matcher m_script = p_script.matcher(htmlStr);  
	        htmlStr = m_script.replaceAll(""); // 过滤script标签  
	  
	        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);  
	        Matcher m_style = p_style.matcher(htmlStr);  
	        htmlStr = m_style.replaceAll(""); // 过滤style标签  
	  
	        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);  
	        Matcher m_html = p_html.matcher(htmlStr);  
	        htmlStr = m_html.replaceAll(""); // 过滤html标签  
	  
	        Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);  
	        Matcher m_space = p_space.matcher(htmlStr);  
	        htmlStr = m_space.replaceAll(""); // 过滤空格回车标签  
	        return htmlStr.trim(); // 返回文本字符串  
	    }  
	      
	    public static String getTextFromHtml(String htmlStr){  
	        htmlStr = delHTMLTag(htmlStr);  
	        htmlStr = htmlStr.replaceAll("&nbsp;", "");  
//	        htmlStr = htmlStr.substring(0, htmlStr.indexOf("。")+1);  
	        return htmlStr;  
	    }  
	      
	    public static void main(String[] args) {  
	    	System.out.println("==========start====");  
	        String str = "&nbsp; &nbsp; &nbsp;&nbsp;继多家银行涉足P2P业务后，另一大传统金融业机构—证券公司也瞄准了这个日益崛起的互联网金融平台行业。<br />"
	        		
	        		+"&nbsp; &nbsp; &nbsp;&nbsp;近日，深圳的P2P借贷平台投哪儿网、北京的互联网金融服务平台91金融先后宣布获得来自广发信德、海通开元的投资。值得注意的是，这两家投资公司分别为广发证券和海通证券的直投子公司。<br />"
	        		+"<br />"
+"<br />去年以来，各路资本开始“扎堆”涌入以P2P借贷、线上理财、垂直搜索等为代表的互联网金融平台。然而，随着互联网金融行业的发展和竞争的加剧，这些创业平台已经告别了单纯对资金的需求，转向考量双方资源整合的空间与前景。<br />"
+"<br />"
+"<br />　91金融CEO许泽玮、投哪儿网CEO吴显勇在接受《第一财经日报》采访时均表示，选择券商系的直投公司，主要看中的是“嫁接”券商资源后，在提升品牌效应、扩展业务空间等方面的积极作用。<br />"
+"<br />"
+"<br />"
+"　　券商资源的深度整合<br />";  
	        System.out.println(getTextFromHtml(str));  
	        System.out.println("==========start====");  
	    }  
}
