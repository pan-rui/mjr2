<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<c:choose>
	<c:when test="${bean.borrowStatus==1 }">申请中</c:when>
	<c:when test="${bean.borrowStatus==2 }">初审通过</c:when>
	<c:when test="${bean.borrowStatus==3 }">招标中</c:when>
	<c:when test="${bean.borrowStatus==4 }">复审中</c:when>
	<c:when test="${bean.borrowStatus==5 }">还款中</c:when>
	<c:when test="${bean.borrowStatus==6 }">已还款</c:when>
	<c:when test="${bean.borrowStatus==7 }">借款失败(初审)</c:when>
	<c:when test="${bean.borrowStatus==8 }">复审失败</c:when>
	<c:when test="${bean.borrowStatus==9 }">流标</c:when>
</c:choose>
