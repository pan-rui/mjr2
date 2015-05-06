<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!--右边框架开始-->
<div class="alert alert-info">
	当前位置<b class="tip"></b>添加借款人信息
</div>
<form id="editForm" action="addTPersonBorrower">
	<table class="table table-striped table-bordered table-condensed list">
		<thead>
			<tr>
				<td colspan="6"><b>个人信息</b>
				</td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>借款人姓名<font color="FF0000">*</font>
				</td>
				<td><input name="borrowerName" value="" type="text"
					datatype="s2-20" nullmsg="请输入借款人姓名" errorMsg="借款人姓名长度为2-20位" />
				</td>
				<td>借款人身份证<font color="FF0000">*</font>
				</td>
				<td><input id="borrowerIdCard" name="borrowerIdCard"  value="" type="text"
					datatype="idcard" nullmsg="请填写借款人身份证" errorMsg="借款人身份证格式不正确"/></td>
				<td>借款人手机号<font color="FF0000">*</font>
				</td>
				<td><input id="borrowerCellPhone" name="borrowerCellPhone"  value="" type="text"
					datatype="m" nullmsg="请填写借款人手机号" errorMsg="借款人手机号不正确"/></td>
			</tr>
			<tr>
				<td>户籍<font color="FF0000">*</font>
				</td>
				<td><select id="houseRegisteProvince" name="houseRegisteProvince" datatype="*" nullmsg="请选择户籍省份">
						<option value="">--请选择--</option>
						<c:forEach items="${bAreaList }" var="bean" varStatus="st">
							<option value="${bean.areaName }" lang="${bean.id }">${bean.areaName }</option>
						</c:forEach>
				</select>
				<select id="houseRegisteCity" name="houseRegisteCity" datatype="*" nullmsg="请选择户籍城市" >
						<option value="">--请选择--</option>
				</select>
				</td>
				<td>性别<font color="FF0000">*</font>
				</td>
				<td><input name="sex" type="radio" value="1"/>男&nbsp;<input name="sex" type="radio" value="2"/>女
				</td>
				<td>是否结婚<font color="FF0000">*</font>
				</td>
				<td><input name="maritalStatus" type="radio" value="2" checked="checked"/>未知&nbsp;<input name="maritalStatus" type="radio" value="0"/>否&nbsp;<input name="maritalStatus" type="radio" value="1"/>是</td>
				
			</tr>
			<tr>
				<td>是否有孩子<font color="FF0000">*</font>
				</td>
				<td><input name="childrenStatus" type="radio" value="2" checked="checked"/>未知&nbsp;<input name="childrenStatus" type="radio" value="0"/>否&nbsp;<input name="childrenStatus" type="radio" value="1"/>是</td>
				<td>是否有房<font color="FF0000"></font>
				</td>
				<td><input name="houseStatus" type="radio" value="2" checked="checked"/>未知&nbsp;<input name="houseStatus" type="radio" value="0"/>否&nbsp;<input name="houseStatus" type="radio" value="1"/>是</td>
				<td>是否有车<font color="FF0000"></font>
				</td>
				<td><input name="carStatus" type="radio" value="2" checked="checked" />未知&nbsp;<input name="carStatus" type="radio" value="0"/>否&nbsp;<input name="carStatus" type="radio" value="1"/>是</td>
			</tr>
			<tr>
				<td>月收入水平<font color="FF0000"></font>
				</td>
				<td colspan="5">
				<select id="monthlyIncomeLevel" name="monthlyIncomeLevel"  datatype="*"  ignore="ignore" nullmsg="请选择月收入水平">
						<option value="">--请选择--</option>
						<c:forEach items="${monthlyIncomeLevelList }" var="bean" varStatus="st">
							<option value="${bean.paraKey }" lang="${bean.id }">${bean.paraValue }</option>
						</c:forEach>
				</select></td>
			</tr>
			
			<tr>
				<td colspan="6"><b>工作信息</b>
				</td>
			</tr>
			<tr>
				<td>工作城市<font color="FF0000">*</font>
				</td>
				<td><select id="jobProvince" name="jobProvince"  datatype="*" nullmsg="请选择工作省份">
						<option value="">--请选择--</option>
						<c:forEach items="${bAreaList }" var="bean" varStatus="st">
							<option value="${bean.areaName }" lang="${bean.id }">${bean.areaName }</option>
						</c:forEach>
				</select>
				<select id="jobCity" name="jobCity"  datatype="*" nullmsg="请选择工作城市">
						<option value="">--请选择--</option>
				</select>
				</td>
				<td>工作年限<font color="FF0000">*</font>
				</td>
				<td>
					<select id="jobYear" name="jobYear"  datatype="*" nullmsg="请选择工作年限">
						<option value="">--请选择--</option>
						<c:forEach items="${jobYearList }" var="bean" varStatus="st">
							<option value="${bean.paraKey }" lang="${bean.id }">${bean.paraValue }</option>
						</c:forEach>
					</select>
				</td>
				<td>公司行业<font color="FF0000"></font>
				</td>
				<td>
				<select id="companyIndustry" name="companyIndustry" datatype="*"  ignore="ignore" nullmsg="请选择公司行业">
						<option value="">--请选择--</option>
						<c:forEach items="${companyIndustryList }" var="bean" varStatus="st">
							<option value="${bean.paraKey }" lang="${bean.id }">${bean.paraValue }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>公司性质<font color="FF0000"></font>
				</td>
				<td><select id="companyNature" name="companyNature" datatype="*"  ignore="ignore" nullmsg="请选择公司性质">
						<option value="">--请选择--</option>
						<c:forEach items="${companyNatureList }" var="bean" varStatus="st">
							<option value="${bean.paraKey }" lang="${bean.id }">${bean.paraValue }</option>
						</c:forEach>
					</select>
				</td>
				<td>岗位或职位<font color="FF0000"></font>
				</td>
				<td colspan="3">
				<input id="position" name="position"  value="" type="text" /></td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="4"><input class="btn btn-inverse" id="btnSave"
					type="button" value="添加" />&nbsp;<input id="btnReset"
					class="btn btn-inverse" type="reset" value="重置" />&nbsp;
					<c:choose>
						<c:when test="${isWindow==1 }">
						<input
					class="btn btn-inverse" id="btnBack" type="button"
					onclick="goUrl('queryCommTPersonBorrowerList')" value="返回" />
						</c:when>
						<c:otherwise>
						<input
					class="btn btn-inverse" id="btnBack" type="button"
					onclick="goUrl('queryTPersonBorrowerList')" value="返回" />
						</c:otherwise>
					</c:choose>
					&nbsp;&nbsp;<span
					id="errorMsg" class="Validform_checktip"> </span></td>
			</tr>
		</tfoot>
	</table>
</form>

<script type="text/javascript" src="${basePath }/js/Validform_Datatype.js"></script>

<script type="text/javascript">
<!--
	var valiForm = $("#editForm").Validform({
		btnSubmit : "#btnSave",
		tiptype : function(msg, o, cssctl) {
			var objtip = $("#errorMsg");
			cssctl(objtip, o.type);
			objtip.text(msg);
		},
		postonce : true,
		ajaxPost : true,
		callback : function(data) {
			alert(data.info);
			if (data.status == 'y') {
				$("#btnBack").click();		
			}
		}
	});
	$.Tipmsg.r = "";
//-->

	
	$(function() {

		$("#houseRegisteProvince").change(function(){
			var id = $("#houseRegisteProvince option:selected").attr("lang");
			queryCity("houseRegisteCity",id);
		});
		
		$("#jobProvince").change(function(){
			var id = $("#jobProvince option:selected").attr("lang");
			queryCity("jobCity",id);
		});
	});
	

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
</script>
<!--右边框架结束-->