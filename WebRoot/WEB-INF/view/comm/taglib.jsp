<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/taglib/my.tld" prefix="my" %>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();

session.setAttribute("imgPath", basePath+"/");
basePath += path;
session.setAttribute("basePath", basePath);
session.setAttribute("path", basePath+"/");
response.setHeader("pragma","no-cache");

response.setHeader("cache-control","no-cache");

response.setDateHeader("expires", 0);
%>
