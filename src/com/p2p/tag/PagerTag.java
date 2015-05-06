package com.p2p.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

public class PagerTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long curPage;
	private long totalCount;
	private long pageSize;
	private String funMethod;
	@Override
	public int doStartTag() throws JspException {
		// 是TagSupport类中定义的一个属性，它是javax.servlet.jsp.PageContext的对象
		if(funMethod==null || "".equals(funMethod)){
			funMethod = "turnPage";
		}
		StringBuilder buf = new StringBuilder();
		int middle = 5;
		long pageCount = totalCount/pageSize;
		if(totalCount%pageSize != 0){
			pageCount++;
		}
		long startPage = curPage-5;
		if(startPage<=0){
			startPage=1;
		}
		long endPage = curPage+5;
		if(endPage >pageCount){
			endPage=pageCount;
		}
		buf.append("<div class=\"turn-page\">");
		buf.append("第<strong class=\"curPageColor\">");
		buf.append(curPage);
		buf.append("</strong>页/共");
		buf.append(pageCount);
		buf.append("页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
		if(curPage>1){
			buf.append("<a href=\"javascript:");
			buf.append(funMethod);
			buf.append("(1)\">首页</a><a href=\"javascript:");
			buf.append(funMethod);
			buf.append("(");
			buf.append(curPage-1);
			buf.append(")\">上一页</a>");
		}
		for(long i=startPage;i<=endPage;i++){
			buf.append("<a href=\"javascript:");
			buf.append(funMethod);
			buf.append("(");
			buf.append(i);
			buf.append(")\" ");
			if(i==curPage){
				buf.append(" class=\"current\"");
			}
			buf.append(">");
			buf.append(i);
			buf.append("</a>");
		}
		if(curPage<pageCount){
			buf.append("<a href=\"javascript:");
			buf.append(funMethod);
			buf.append("(");
			buf.append(curPage+1);
			buf.append(")\">下一页</a><a href=\"javascript:");
			buf.append(funMethod);
			buf.append("(");
			buf.append(pageCount);
			buf.append(")\">尾页</a>");
		}
		buf.append("</div>");
		JspWriter out = pageContext.getOut();
		try {
			out.print(buf);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return Tag.SKIP_BODY;
	}

	public long getCurPage() {
		return curPage;
	}

	public void setCurPage(long curPage) {
		this.curPage = curPage;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public long getPageSize() {
		return pageSize;
	}

	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}

	public String getFunMethod() {
		return funMethod;
	}

	public void setFunMethod(String funMethod) {
		this.funMethod = funMethod;
	}
	
	

}
