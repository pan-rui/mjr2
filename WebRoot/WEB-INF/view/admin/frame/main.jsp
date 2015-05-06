<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>

<!DOCTYPE html>
<html>
<head>

<title>后台管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="keyword1,keyword2,keyword3">
<meta name="description" content="This is my page">

<link rel="stylesheet" type="text/css" href="${basePath}/css/admin/admin-all.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/admin/base.css" />
<link rel="stylesheet" type="text/css"
	href="${basePath}/css/admin/bootstrap-responsive.min.css" />
	<link rel="stylesheet" type="text/css"
	href="${basePath}/css/admin/bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="${basePath}/css/validform.css" />	
<link type="text/css" rel="stylesheet" href="${basePath }/js/plugins/jBox/Skins/Blue/jbox.css"/>
	

<script type="text/javascript" src="${basePath}/js/admin/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${basePath}/js/lion-jquery.js"></script>
<script type="text/javascript"
	src="${basePath}/js/admin/jquery-ui-1.8.22.custom.min.js"></script>
<script type="text/javascript" src="${basePath}/js/admin/index.js"></script>
<script type="text/javascript" src="${basePath}/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="${basePath}/js/Validform_Datatype.js"></script>
<script type="text/javascript" src="${basePath}/js/plugins/ckeditor/ckeditor.js"></script></head>
<script type="text/javascript" src="${basePath}/js/plugins/ckfinder/ckfinder.js"></script></head>
<script type="text/javascript" src="${basePath}/js/plugins/My97DatePicker/WdatePicker.js"></script>

<body>
	<div class="warp">
		<!--头部开始-->
		<jsp:include page="/WEB-INF/view/admin/frame/top.jsp"></jsp:include>
		<!--头部结束-->

		<!--左边菜单开始-->
		<jsp:include page="/WEB-INF/view/admin/frame/left.jsp"></jsp:include>
		<!--左边菜单结束-->

		<!--右边框架开始-->
		<div id="mainContent" class="Conframe">
			<img alt="" src="${basePath}/images/admin/welcome.png" style="width: 100%;height: 100%;">
		</div>
		<!--右边框架结束-->

		<!--底部开始-->
		<jsp:include page="/WEB-INF/view/admin/frame/footer.jsp"></jsp:include>
		<!--底部结束-->
	</div>
<script type="text/javascript">
	function goUrl(url){
		$.lionPost(url,{},function(data){
			$("#mainContent").html(data);
		});
	}

	function queryForm(){
		queryFormById("searchForm");
	}
	
	function queryFormById(formId){
		var url = $("#"+formId).attr("action");
		var arrays = $("#"+formId).serializeArray();
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
	
	function openUpload(inputId,startupPath){
		var finder = new CKFinder();
		finder.basePath = '${basePath}/js/plugins/ckfinder/';
		finder.selectActionFunction = SetFileField; // 回写选中的图片URL
		finder.selectActionData = inputId; //接收地址的input ID
		finder.startupFolderExpanded = true;
		finder.startupPath = startupPath;// 限定文件夹
		finder.skin="v1";
		//finder.selectThumbnailActionFunction = ShowThumbnails;// 展示缩略图
		finder.width = 960;
		finder.heigth = 700;
		finder.popup();
	}
	
	//文件选中时执行
	function SetFileField(fileUrl,data) {
		$("#" + data["selectActionData"]).val(fileUrl.substring(4));
	}
	
	function queryCity(inputId,parentId) {
		$.lionPost("queryCityList",{"parentId":parentId},function(data){
			$("#"+inputId).empty();

			var options = "<option value=''>--请选择--</option>";
			  
			 $.each(data, function (index, value){
				 options +=("<option value='"+data[index].areaName+"'>"+data[index].areaName+"</option>");
			 });
			
			$("#"+inputId).html(options);
		});
	}
	
	function selectCityValue(inputId,value,parentId){
		
		$.lionPost("queryCityList",{"parentId":parentId},function(data){
			$("#"+inputId).empty();

			var options = "<option value=''>--请选择--</option>";
			  
			 $.each(data, function (index, value){
				 options +=("<option value='"+data[index].areaName+"'>"+data[index].areaName+"</option>");
			 });
			
			$("#"+inputId).html(options);
			$("#"+inputId+" option[value='"+value+"']").attr("selected",true);
		});
		
	}
</script>
</body>
</html>
