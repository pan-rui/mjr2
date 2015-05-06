
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!--右边框架开始-->
<div class="alert alert-info">
	当前位置<b class="tip"></b>添加借款信息
</div>
<form id="editForm" action="addTBorrow">
	<table class="table table-striped table-bordered table-condensed list">
		<thead>
			<tr>
				<td colspan="6"><b>&nbsp;&nbsp;收款人信息</b></td>
			</tr>
		</thead>
		<tbody>
			
			<tr>
				<td>收款人平台用户名<font color="FF0000">*</font></td>
				<td ><input id="borrowerUserName" name="borrowerUserName" value="" type="text"
					datatype="*" nullmsg="请选择借款人用户名" readonly="readonly" onclick="openBorrowerUser()" />
					<input id="borrowerId" name="borrowerId" type="hidden" />
					</td>
				
				<td>借款人姓名<font color="FF0000"></font>
				</td>
				<td colspan="3"><span id="realName"></span>
					
				</td>
				
			</tr>
			<tr>
				<td colspan="6"><b>&nbsp;&nbsp;借款人个人信息</b></td>
			</tr>
			<tr>
				
				<td>借款人个人信息<font color="FF0000">*</font>
				</td>
				<td ><input id="borrowerName" name="borrowerName" value="" type="text" onclick="openPersonBorrowerUser()"
					datatype="*" nullmsg="请选择借款人信息"  readonly="readonly" />
					<input id="personBorrowerId" name="personBorrowerId" type="hidden" />
				</td>
				<td>借款人身份证号<font color="FF0000">*</font>
				</td>
				<td colspan="3"><input id="borrowerIdCard" name="borrowerIdCard" value="" type="text" onclick="openPersonBorrowerUser()"
					datatype="*" nullmsg="请选择借款人信息"  readonly="readonly" />
					
				</td>
				
			</tr>
			<tr>
				<td colspan="6"><b>&nbsp;&nbsp;项目资料信息</b></td>
			</tr>
			<tr>
				<td colspan="6">
					<table id="attTbl">
						<tr style="display: none;">
							<td id="tdValue"></td>
							
							
						</tr>
						<tr id="tr_1" class="attrCls" lang="1">
							<td>项目资料1<font color="FF0000">*</font></td>
							<td><input id="attrName1" name="attrName" value=""  type="text" /></td>
							<td colspan="4">
							<input  id="attrPath1" name="attrPath" value="" onclick="uploadBorrowFile(this)" type="text" readonly="readonly" />
							&nbsp;<input class="btn btn-inverse" onclick="addAttrTr()" type="button" value="添加" />
							</td>
						</tr>
					
					</table>
				
				</td>
			</tr>
			
			<tr>
				<td colspan="6"><b>&nbsp;&nbsp;借款信息</b></td>
			</tr>
			<tr>
				<td>借款标题<font color="FF0000">*</font></td>
				<td><input name="borrowTitle" value="" type="text"
					datatype="*5-30" nullmsg="请输入借款标题" errorMsg="借款标题长度为5-30位" /></td>
				<td>借款(合同)编号<font color="FF0000">*</font>
				</td>
				<td><input name="borrowNo" value="" type="text"
					datatype="s6-30" nullmsg="请输入借款(合同)编号" errorMsg="借款(合同)编号为6-30位" />
				</td>
				
				<td>借款用途<font color="FF0000">*</font>
				</td>
				<td >
					<input name="borrowPurpose" value="" type="text"
					datatype="*2-15"  nullmsg="请输入借款用途" errorMsg="借款用途长度为2-15" />
				</td>
				
			</tr>
			<tr>
				<td>借款金额<font color="FF0000">*</font></td>
				<td><input name="borrowAmount" value="" type="text"
					datatype="numrange" min="100" max="10000000" nullmsg="请输入借款金额" errorMsg="借款金额范围为100-10,000,000元" />元</td>
				<td>借款利率<font color="FF0000">*</font>
				</td>
				<td><input name="annualRate" value="" type="text"
					datatype="numrange" min="6" max="24" nullmsg="请输入借款利率" errorMsg="借款利率范围为6%-24%" />%
				</td>
				<td>还款方式<font color="FF0000">*</font>
				</td>
				<td><select id="repayType" name="repayType" datatype="*" nullmsg="请选择还款方式">
						<option value="">--请选择-- </option>
						<option value="1">一次性还款 </option>
						<option value="2">按月还息，到期还本 </option>
						<option value="3">等额本息</option>
					</select>
				</td>
				
			</tr>
			<tr>
				<td>期限类型<font color="FF0000">*</font>
				</td>
				<td><select id="deadlineType" name="deadlineType" datatype="*" nullmsg="请选择期限类型">
						<option value="2">月</option>
						<option value="1">天</option>
					</select>
				</td>	
				<td>借款期限<font color="FF0000">*</font></td>
				<td><input name="deadline" value="" type="text"
					datatype="n1-3"  nullmsg="请输入借款期限" errorMsg="借款期限格式不正确" />
					<span id="span_deadlineType">月</span>
					</td>
				<td>标的类型<font color="FF0000">*</font>
				</td>
				<td >
					<select name="borrowType" datatype="*" nullmsg="请选择标的类型">
						<option value="">--请选择-- </option>
						<option value="1">信用标 </option>
						<option value="2">抵押标</option>
						
					</select>
				</td>
				
				
			</tr>
				
				<tr>
				
			<td>最小投标金额<font color="FF0000">*</font>
				</td>
			<td><select name="minInvestAmount" datatype="*" nullmsg="请选择最小投标金额">
						<option value="">--请选择-- </option>
						<c:forEach items="${minInvestAmountList }" var="bean" varStatus="st">
							<option value="${bean.paraKey }"> ${bean.paraValue} </option>
						</c:forEach>
						
					</select>
				</td>
				<td>最大投标金额<font color="FF0000"></font>
				</td>
				<td><select name="maxInvestAmount" >
						<option value="">--请选择-- </option>
						<c:forEach items="${maxInvestAmountList }" var="bean" varStatus="st">
							<option value="${bean.paraKey }"> ${bean.paraValue} </option>
						</c:forEach>
					</select>
				</td>
				<td>募集时间<font color="FF0000">*</font>
				</td>
				<td >
					<select name="raisingPeriod" datatype="*" nullmsg="请选择募集时间">
						<option value="">--请选择-- </option>
						<option value="1"> 1天 </option>
						<option value="2"> 2天 </option>
						<option value="3"> 3天 </option>
						<option value="4"> 4天 </option>
						<option value="5"> 5天 </option>
						<option value="6"> 6天 </option>
						<option value="7"> 7天 </option>
					</select>
				</td>
				
				
			</tr>
			<tr>
				<td>借款详情<font color="FF0000">*</font></td>
				<td colspan="5"><textarea rows="3" id="details" name="details"
						cols="100" style="width: 80%" datatype="*" nullmsg="请输入借款详情"></textarea></td>
				
			</tr>
			<tr>
				<td>风控意见<font color="FF0000">*</font></td>
				<td colspan="5"><textarea rows="3" id="windControlTip" name="windControlTip"
						cols="100" style="width: 80%" datatype="*" nullmsg="请输入风控意见"></textarea></td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="6"><input class="btn btn-inverse" id="btnSave"
					type="button" value="添加" />&nbsp;<input id="btnReset"
					class="btn btn-inverse" type="reset" value="重置" />&nbsp;<input
					class="btn btn-inverse" id="btnBack" type="button"
					onclick="goUrl('queryTBorrowList')" value="返回" />&nbsp;&nbsp;<span
					id="errorMsg" class="Validform_checktip"> </span>
				</td>
			</tr>
		</tfoot>
	</table>
</form>

<script type="text/javascript">
	var valiForm = $("#editForm").Validform({
		btnSubmit : "#btnSave",
		tiptype : function(msg, o, cssctl) {
			var objtip = $("#errorMsg");
			cssctl(objtip, o.type);
			objtip.text(msg);
		},
		postonce : true,
		beforeCheck:function(curform){
			//在表单提交执行验证之前执行的函数，curform参数是当前表单对象。
			//这里明确return false的话将不会继续执行验证操作;	
			$("#details").val(detailsEditor.document.getBody().getHtml()); 
			$("#windControlTip").val(windControlTipEditor.document.getBody().getHtml()); 
			$("#tdValue").html("");
			var hiddenContent = "";
			var isReturn = 0;
			var lang = 0;
			$(".attrCls").each(function(index,obj){
				var trId = $(obj).attr("id");
				lang = $(obj).attr("lang");
				var attrName = $("#"+trId+" td input[name='attrName']").val();
				var attrPath = $("#"+trId+" td input[name='attrPath']").val();
				if(attrName == '' ){
					isReturn = 1;
					return;
				}
				if(attrPath == '' ){
					isReturn = 2;
					return;
				}
				hiddenContent += ('<input name="borrowAttrs['+index+'].attrName" type="hidden" value="'+attrName+'" />');
				hiddenContent += ('<input name="borrowAttrs['+index+'].attrPath" type="hidden" value="'+attrPath+'" />');
			});
	
			if(isReturn==1){
				alert("请输入项目资料"+lang+"的名称")
				return false;
			}else if(isReturn ==2){
				alert("请选择项目资料"+lang+"的图片")
				return false;
			}
			$("#tdValue").html(hiddenContent);
		},
		ajaxPost : true,
		callback : function(data) {
			alert(data.info);
			if (data.status == 'y') {
				goUrl('queryTBorrowList');
			}
		}
	});
	$.Tipmsg.r = "";


detailsEditor = CKEDITOR.replace('details', {
	 filebrowserBrowseUrl:'${basePath}/js/plugins/ckfinder/ckfinder.html', 
	 filebrowserImageBrowseUrl:'${basePath}/js/plugins/ckfinder/ckfinder.html?type=Images', 
	 filebrowserFlashBrowseUrl: '${basePath}/js/plugins/ckfinder/ckfinder.html?type=Flash',  
	 filebrowserUploadUrl:'${basePath}/js/plugins/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files', 
	 filebrowserImageUploadUrl:'${basePath}/js/plugins/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images', 
	 filebrowserFlashUploadUrl:'${basePath}/js/plugins/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash'
});


 windControlTipEditor = CKEDITOR.replace('windControlTip', {
	 filebrowserBrowseUrl:'${basePath}/js/plugins/ckfinder/ckfinder.html', 
	 filebrowserImageBrowseUrl:'${basePath}/js/plugins/ckfinder/ckfinder.html?type=Images', 
	 filebrowserFlashBrowseUrl: '${basePath}/js/plugins/ckfinder/ckfinder.html?type=Flash',  
	 filebrowserUploadUrl:'${basePath}/js/plugins/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files', 
	 filebrowserImageUploadUrl:'${basePath}/js/plugins/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images', 
	 filebrowserFlashUploadUrl:'${basePath}/js/plugins/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash'
});


//实例化
CKFinder.setupCKEditor(detailsEditor, "${basePath}/js/plugins/ckfinder/");//相对路径
CKFinder.setupCKEditor(windControlTipEditor, "${basePath}/js/plugins/ckfinder/");//相对路径

var jbox;
function openBorrowerUser(){
	jbox = $.jBox.open("iframe:queryCommCUserList", "用户列表", 1024, 600, {
		buttons : {
			'关闭' : true
		}
	});
}

function openPersonBorrowerUser(){
	jbox = $.jBox.open("iframe:queryCommTPersonBorrowerList", "借款人信息列表", 1024, 600, {
		buttons : {
			'关闭' : true
		}
	});
}

function checkPersonBorrower(id,borrowerName,borrowerIdCard){
	$("#personBorrowerId").val(id);
	$("#borrowerName").val(borrowerName);
	$("#borrowerIdCard").val(borrowerIdCard);
	$.jBox.close(jbox);
}

function checkUser(arrays){
	$("#borrowerUserName").val(arrays["userName"]);
	$("#borrowerId").val(arrays["userId"]);
	$("#realName").html(arrays["realName"]);
	$.jBox.close(jbox);
}

function uploadBorrowFile(obj){
	
	openUpload($(obj).attr("id"),"Images:/borrow/");
}

function addAttrTr(){
	
	var len = $(".attrCls").length;
	var index=len;
	len = len +1 ;
	
	
	var trContent = '<tr id="tr_'+len+'" class="attrCls" lang="'+len+'">'+
	'<td>项目资料'+len+'<font color="FF0000">*</font></td>'+
	'<td><input id="attrName'+len+'" name="attrName" value=""  type="text" /></td>'+
	'<td colspan="4">'+
	'<input  id="attrPath'+len+'" name="attrPath" value="" onclick="uploadBorrowFile(this)" type="text" readonly="readonly" />'+
	'&nbsp;<input class="btn btn-inverse" onclick="delTr(this)" type="button" value="删除" />'+
	'</td>'+'</tr>';
	$("#attTbl").append(trContent);
}

function delTr(td){
	$(td).parent().parent().remove();
	
}

$(function(){
	$("#deadlineType").change(function(){
		var deadlineType = $("#deadlineType").val();
		if(deadlineType==1){
			$("#span_deadlineType").html("天");
			var selectContent =  '<option value="1">一次性还款 </option>';
			$("#repayType").html(selectContent);
		}else{
			var selectContent = '<option value="">--请选择-- </option>';
			selectContent += '<option value="1">一次性还款 </option>';
			selectContent += '<option value="2">按月还息，到期还本 </option>';
			selectContent += '<option value="3">等额本息</option>';
			$("#repayType").html(selectContent);
			$("#span_deadlineType").html("月");
		}
	});
	
	$("#repayType").change(function(){
		var repayType = $("#repayType").val();
		if(repayType==1){
			var deadlineTypeVal = $("#deadlineType").val();
			var selectContent =  '<option value="">--请选择-- </option>';
			selectContent +=  '<option value="2">月</option>';
			selectContent +=  '<option value="1">天</option>';
			$("#deadlineType").html(selectContent);
			$("#deadlineType").val(deadlineTypeVal);
			if(deadlineTypeVal==1){
				$("#span_deadlineType").html("天");
			}else if(deadlineTypeVal==2){
				$("#span_deadlineType").html("月");
			}
			
		}else{
		
			var selectContent = '<option value="2">月</option>';
			
			$("#deadlineType").html(selectContent);
			$("#span_deadlineType").html("月");
		}
	});
	
});
//-->
</script>
<script type="text/javascript" src="${basePath }/js/plugins/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="${basePath }/js/plugins/jBox/i18n/jquery.jBox-zh-CN.js"></script>
<!--右边框架结束-->