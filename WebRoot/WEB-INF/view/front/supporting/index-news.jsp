<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>

<ul>
	<c:forEach items="${indexNews }" var="Ixnew" varStatus="st">
		<li><b class="notice_ico tran"></b><a href="news-${Ixnew.id }.html">${Ixnew.title }</a>
	</li>
	</c:forEach>
</ul>
