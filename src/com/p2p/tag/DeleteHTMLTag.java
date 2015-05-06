package com.p2p.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import com.p2p.util.HtmlUtil;

public class DeleteHTMLTag extends TagSupport {
	/**
	 * 过滤HTML标签
	 */
	private static final long serialVersionUID = 1L;
	private String content;
	private int len;

	@Override
	public int doStartTag() throws JspException {
		String str = HtmlUtil.getTextFromHtml(content);
		JspWriter out = pageContext.getOut();
		try {
			out.print(str.substring(0, len));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Tag.SKIP_BODY;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getLen() {
		return len;
	}

	public void setLen(int len) {
		this.len = len;
	}

	
}
