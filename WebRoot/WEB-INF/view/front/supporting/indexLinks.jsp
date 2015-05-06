<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<ul>
	<c:forEach items="${indexLinks }" var="link" varStatus="st">
		<li><a href="${link.linkUrl}" target="_blank"><img
				src="${link.imagePath }" /> </a>
		</li>
	</c:forEach>
		
</ul>
