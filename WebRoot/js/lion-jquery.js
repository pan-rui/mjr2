$.lionPost = function(url,param,callBack){
	if(url.indexOf('?') == -1){
		url = url+"?now="+new Date().getTime();
	}else{
		url = url+"&now="+new Date().getTime();
	}
	
	$.post(url,param,function(data){
		if(data == "noLogin"){
			window.location.href = "loginIndex";
			return;
		}
		callBack(data);
	});
}

function turnFrontPage(page){
	$("#curPage").val(page);
	$("#searchForm").submit();
}
