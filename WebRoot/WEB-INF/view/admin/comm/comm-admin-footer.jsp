<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>


		
	</div>
<script type="text/javascript">

	
	function goUrl(url){
		$.lionPost(url,{},function(data){
			$("#mainContent").html(data);
		});
	}
	
	
	function queryForm(){
		var url = $("#searchForm").attr("action");
		var arrays = $("#searchForm").serializeArray();
		$.lionPost(url,arrays,function(data){
			$("#mainContent").html(data);
		});
	}
	
	function resetForm(){
		var url = $("#searchForm").attr("action");
		var arrays = null;
		$.lionPost(url,arrays,function(data){
			$("#mainContent").html(data);
		});
	}
	
	function turnPage(curPage){
		$("#curPage").val(curPage);
		queryForm();
	}
	

	
</script>

</body>
</html>