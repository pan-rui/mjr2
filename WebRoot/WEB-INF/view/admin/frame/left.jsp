<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!--左边菜单开始-->
<div class="left_c">
	<h1>后台系统操作菜单</h1>
	<div class="acc">
		
			<c:forEach items="${menuList }" var="parent">
				<c:if test="${ parent.parentId==-1}">
				<div>
					<a class="one">${parent.menuName}</a>
					<ul class="kid">
						<c:forEach items="${menuList }" var="bean">
							<c:if test="${parent.id==bean.parentId}">
								<li><b class="tip"></b><a href="javascript:void(0);"
									onclick="goUrl('${bean.url}')">${bean.menuName}</a>
								</li>
							</c:if>
						</c:forEach>
					</ul>
				</div>
				</c:if>

			</c:forEach>

			
		
	</div>

</div>
<!--左边菜单结束-->
<div class="right_c">
	<div class="nav-tip" onclick="javascript:void(0)">&nbsp;</div>
</div>