<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!--右边框架开始-->
<div class="alert alert-info">
	当前位置<b class="tip"></b>借款信息
</div>

<div class="xiangqing">
	<table >
		<tr align="left">
			<td  align="left" valign="top"  colspan="6"><b>收款账户信息</b>
			</td>
		</tr>
		<tr>
			<td width="10%" align="right" valign="top" class="bt_ys"><strong>平台用户名：</strong>
			</td>
			<td width="20%" valign="top">${userDto.userName }</td>
			<td width="10%" align="right" valign="top" class="bt_ys"><strong>真实姓名：</strong>
			</td>
			<td  width="20%" valign="top">${userDto.realName }</td>
			<td width="10%" align="right" valign="top" class="bt_ys"><strong>手机号：</strong>
			</td>
			<td  width="20%" valign="top">${userDto.cellPhone }</td>
		</tr>
		<tr align="left">
			<td  align="left" valign="top"  colspan="6"><b>借款人个人信息</b>
			</td>
		</tr>
		<tr>
			<td width="10%" align="right" valign="top" class="bt_ys"><strong>借款人姓名：</strong>
			</td>
			<td width="20%" valign="top">${tPersonBorrowerModel.borrowerName }</td>
			<td width="10%" align="right" valign="top" class="bt_ys"><strong>借款人身份证：</strong>
			</td>
			<td  width="20%" valign="top">${tPersonBorrowerModel.borrowerIdCard }</td>
			<td width="10%" align="right" valign="top" class="bt_ys"><strong>借款人手机号：</strong>
			</td>
			<td  width="20%" valign="top">${tPersonBorrowerModel.borrowerCellPhone }</td>
		</tr>
		<tr>
			<td align="right" valign="top" class="bt_ys"><strong>户籍：</strong>
			</td>
			<td  valign="top"><select id="houseRegisteProvince" name="houseRegisteProvince" >
						<option value="">--请选择--</option>
						<c:forEach items="${bAreaList }" var="bean" varStatus="st">
							<option value="${bean.areaName }" lang="${bean.id }">${bean.areaName }</option>
						</c:forEach>
				</select>
				<select id="houseRegisteCity" name="houseRegisteCity" >
						
				</select></td>
			<td  align="right" valign="top" class="bt_ys"><strong>性别：</strong>
			</td>
			<td  valign="top"><input name="sex" type="radio" value="1"/>男&nbsp;<input name="sex" type="radio" value="2"/>女</td>
			<td  align="right" valign="top" class="bt_ys"><strong>是否结婚：</strong>
			</td>
			<td  valign="top"><input name="maritalStatus" type="radio" value="2" checked="checked"/>未知&nbsp;<input name="maritalStatus" type="radio" value="0"/>否&nbsp;<input name="maritalStatus" type="radio" value="1"/>是</td>
		</tr>
		<tr>
			<td  align="right" valign="top" class="bt_ys"><strong>是否有孩子：</strong>
			</td>
			<td  valign="top"><input name="childrenStatus" type="radio" value="2" checked="checked"/>未知&nbsp;<input name="childrenStatus" type="radio" value="0"/>否&nbsp;<input name="childrenStatus" type="radio" value="1"/>是</td>
			<td  align="right" valign="top" class="bt_ys"><strong>是否有房：</strong>
			</td>
			<td  valign="top"><input name="houseStatus" type="radio" value="2" checked="checked"/>未知&nbsp;<input name="houseStatus" type="radio" value="0"/>否&nbsp;<input name="houseStatus" type="radio" value="1"/>是</td>
			<td  align="right" valign="top" class="bt_ys"><strong>是否有车：</strong>
			</td>
			<td  valign="top"><input name="houseStatus" type="radio" value="2" checked="checked" />未知&nbsp;<input name="carStatus" type="radio" value="0"/>否&nbsp;<input name="carStatus" type="radio" value="1"/>是</td>
		</tr>
		<tr>
			<td  align="right" valign="top" class="bt_ys"><strong>月收入水平：</strong>
			</td>
			<td  valign="top" colspan="5"><select id="monthlyIncomeLevel" name="monthlyIncomeLevel"  >
						<c:forEach items="${monthlyIncomeLevelList }" var="bean" varStatus="st">
							<option value="${bean.paraKey }" lang="${bean.id }">${bean.paraValue }</option>
						</c:forEach>
				</select></td>
			
		</tr>
		<tr>
			<td align="left" valign="top"  colspan="6"><strong>借款人工作信息</strong>
			</td>
		</tr>
			<tr>
			<td  align="right" valign="top" class="bt_ys"><strong>工作城市：</strong>
			</td>
			<td  valign="top"><select id="jobProvince" name="jobProvince"  >
						<option value="">--请选择--</option>
						<c:forEach items="${bAreaList }" var="bean" varStatus="st">
							<option value="${bean.areaName }" lang="${bean.id }">${bean.areaName }</option>
						</c:forEach>
				</select>
				<select id="jobCity" name="jobCity"  datatype="*" nullmsg="请选择工作城市">
						<option value="">--请选择--</option>
				</select></td>
			<td  align="right" valign="top" class="bt_ys"><strong>工作年限：</strong>
			</td>
			<td  valign="top"><select id="jobYear" name="jobYear" >
						<option value="">--请选择--</option>
						<c:forEach items="${jobYearList }" var="bean" varStatus="st">
							<option value="${bean.paraKey }" lang="${bean.id }">${bean.paraValue }</option>
						</c:forEach>
					</select></td>
			<td  align="right" valign="top" class="bt_ys"><strong>公司行业：</strong>
			</td>
			<td  valign="top"><select id="companyIndustry" name="companyIndustry" >
						<option value="">--请选择--</option>
						<c:forEach items="${companyIndustryList }" var="bean" varStatus="st">
							<option value="${bean.paraKey }" lang="${bean.id }">${bean.paraValue }</option>
						</c:forEach>
					</select></td>
		</tr>
			<tr>
			<td  align="right" valign="top" class="bt_ys"><strong>公司性质：</strong>
			</td>
			<td  valign="top"><select id="companyNature" name="companyNature" >
						<option value="">--请选择--</option>
						<c:forEach items="${companyNatureList }" var="bean" varStatus="st">
							<option value="${bean.paraKey }" lang="${bean.id }">${bean.paraValue }</option>
						</c:forEach>
					</select></td>
			<td  align="right" valign="top" class="bt_ys"><strong>岗位或职位：</strong>
			</td>
			<td  valign="top" colspan="3">${tJobBorrowerModel.position }</td>
			
		</tr>
		<tr>
			<td align="left" valign="top"  colspan="6"><strong>借款信息</strong>
			</td>
		</tr>
			<tr>
			<td  align="right" valign="top" class="bt_ys"><strong>借款标题：</strong>
			</td>
			<td  valign="top">${tBorrowModel.borrowTitle }</td>
			<td  align="right" valign="top" class="bt_ys"><strong>借款(合同)编号：</strong>
			</td>
			<td  valign="top">${tBorrowModel.borrowNo }</td>
			<td  align="right" valign="top" class="bt_ys"><strong>借款用途：</strong>
			</td>
			<td  valign="top">${tBorrowModel.borrowPurpose }</td>
			</tr>
			
			<tr>
			<td  align="right" valign="top" class="bt_ys"><strong>借款金额：</strong>
			</td>
			<td  valign="top">${tBorrowModel.borrowAmount }</td>
			<td  align="right" valign="top" class="bt_ys"><strong>借款利率：</strong>
			</td>
			<td  valign="top">${tBorrowModel.annualRate } %</td>
			<td  align="right" valign="top" class="bt_ys"><strong>还款方式：</strong>
			</td>
			<td  valign="top"><select id="repayType" name="repayType" >
						<option value="">--请选择-- </option>
						<option value="1">一次性还款 </option>
						<option value="2">按月还息，到期还本 </option>
						<option value="3">等额本息</option>
					</select></td>
			
			</tr>
			<tr>
			<td  align="right" valign="top" class="bt_ys"><strong>借款期限：</strong>
			</td>
			<td  valign="top">${tBorrowModel.deadline } 月</td>
			<td  align="right" valign="top" class="bt_ys"><strong>最小投标金额：</strong>
			</td>
			<td  valign="top">${tBorrowModel.minInvestAmount }</td>
			<td  align="right" valign="top" class="bt_ys"><strong>最大投标金额：</strong>
			</td>
			<td  valign="top">${tBorrowModel.maxInvestAmount }</td>
			
			</tr>
			<tr>
				<td align="right" valign="top" class="bt_ys">标的类型
				</td>
				<td >
					<select name="borrowType" id="borrowType" >
						<option value="1">信用标 </option>
						<option value="2">抵押标</option>
						
					</select>
					
				</td>
				<td  align="right" valign="top" class="bt_ys"><strong>募集时间：</strong>
			</td>
			<td  valign="top" colspan="3">${tBorrowModel.raisingPeriod }天</td>
			</tr>
			<tr>
			<td  align="right" valign="top" class="bt_ys"><strong>借款详情：</strong>
			</td>
			<td  valign="top" colspan="5">${tBorrowModel.details }</td>
			
			</tr>
			<tr>
			<td  align="right" valign="top" class="bt_ys"><strong>风控意见：</strong>
			</td>
			<td  valign="top" colspan="5">${tBorrowModel.windControlTip }</td>
			
			</tr>
			<tr>
			<td  align="right" valign="top" class="bt_ys"><strong>借款状态：</strong>
			</td>
			<td  valign="top">
				<c:choose>
						<c:when test="${tBorrowModel.borrowStatus==1 }">申请中</c:when>
						<c:when test="${tBorrowModel.borrowStatus==2 }">初审通过</c:when>
						<c:when test="${tBorrowModel.borrowStatus==3 }">招标中</c:when>
						<c:when test="${tBorrowModel.borrowStatus==4 }">复审中</c:when>
						<c:when test="${tBorrowModel.borrowStatus==5 }">还款中</c:when>
						<c:when test="${tBorrowModel.borrowStatus==6 }">已还款</c:when>
						<c:when test="${tBorrowModel.borrowStatus==7 }">借款失败(初审)</c:when>
						<c:when test="${tBorrowModel.borrowStatus==8 }">复审失败</c:when>
						<c:when test="${tBorrowModel.borrowStatus==9 }">流标</c:when>
						<c:when test="${tBorrowModel.borrowStatus==10 }">满标处理中</c:when>
				</c:choose>
			</td>
			<td  align="right" valign="top" class="bt_ys"><strong>开始投标时间：</strong>
			</td>
			<td  valign="top"><fmt:formatDate value="${tBorrowModel.investStartTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td  align="right" valign="top" class="bt_ys"><strong>满标时间：</strong>
			</td>
			<td  valign="top"><fmt:formatDate value="${tBorrowModel.fullTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
			<tr>
			
				<td align="left" valign="top"  colspan="6"><strong>借款项目资料信息</strong> &nbsp;&nbsp;&nbsp;<input class="btn btn-inverse" id="showyesorno" type="button" value="添加" />
				<div style="display: none" id="addtbattr">
				<form action="insertTBorrowAttr" id="editForm2">
				添加项目资料信息:<input id="attrName1" name="attrName" value=""  type="text" />
				<input  id="attrPath1" name="attrPath" value="" onclick="uploadBorrowFile(this)" type="text" readonly="readonly" />
				<input class="btn btn-inverse" id="btnSave2" type="button" value="添加" />
				<input type="hidden" name="borrowId" value="${tBorrowModel.id }">
				</form>
				</div>
				</td>
			</tr>
			<c:forEach items="${borrowAttrList }" var="bean" varStatus="st">
				<tr>
					<td  align="right" valign="top" class="bt_ys"><strong>${bean.attrName }</strong>
					</td>
				<td  valign="top" colspan="5"><img src="${imgPath}${bean.attrPath }" style="height: 50px;width: 100px;" /> <input type="button" onclick="aa('${bean.id}')" value="删除"></td>
			
					
				</tr>
			</c:forEach>
			<c:if test="${auditStatus==1 }">
					
					<tr>
					<td align="left" valign="top"  colspan="6"><strong>审核信息</strong>
					</td>
				</tr>
				<tr>
					<td align="left" valign="top"  colspan="6">
						<form action="updateFirstAuditTBorrow" id="editForm" target="_blank">
							<table>
								<tr>
									<td  align="right" valign="top" class="bt_ys"><strong>初审是否通过：</strong>
										
									</td>
									<td  valign="top"><input name="borrowStatus" type="radio" value="1" datatype="*" errormsg="请选择初审是否通过"/>通过&nbsp;<input name="borrowStatus" type="radio" value="0"/>不通过</td>
									<td  align="right" valign="top" class="bt_ys"><strong>发标时间：</strong>
									</td>
									<td  valign="top">
										<input name="investStartTime" type="text" value="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate:'%y-%M-%d %H:%m'})" readonly="readonly" datatype="*" nullmsg="请选择开始投标时间" />
										<input type="hidden" name="id" value="${tBorrowModel.id }">
									</td>
									
								</tr>
								<tr>
									<td  align="center" valign="top" colspan="4">
									<input class="btn btn-inverse" id="btnSave"
										type="button"  value="审核" /> &nbsp;&nbsp;<span
											id="errorMsg" class="Validform_checktip"> </span>
									</td>
									
									
								</tr>
								
								</table>
								</form>
							
							</td>
						</tr>
					
			</c:if>
			<c:if test="${auditStatus==2 }">
					
					<tr>
					<td align="left" valign="top"  colspan="6"><strong>审核信息</strong>
					</td>
				</tr>
				<tr>
					<td align="left" valign="top"  colspan="6">
						<form action="auditBorrow" id="editForm">
							<table>
								<tr>
									<td  align="right" valign="top" class="bt_ys"><strong>复审是否通过：</strong>
										
									</td>
									<td  valign="top"><input name="borrowStatus" type="radio" value="1" datatype="*" errormsg="请选择复审是否通过"/>通过&nbsp;<input name="borrowStatus" type="radio" value="0"/>不通过</td>
									
									
								</tr>
								<tr>
									<td  align="center" valign="top" colspan="2">
									<input class="btn btn-inverse" id="btnSave"
										type="button"  value="审核" /> &nbsp;&nbsp;<span
											id="errorMsg" class="Validform_checktip"> </span>
											<input type="hidden" name="id" value="${tBorrowModel.id }">
									</td>
									
									
								</tr>
								
								</table>
								</form>
							
							</td>
						</tr>
					
			</c:if>
		<tfoot>
			<tr>
				<td colspan="6">				
				<input class="btn btn-inverse" id="btnBack"
					type="button" onclick="goUrl('queryTBorrowList')" value="返回" /></td>
			</tr>
		</tfoot>
	</table>
</div>
<!--右边框架结束-->
<script type="text/javascript">
<!--

	$(function() {
		$("#houseRegisteProvince").val('${tPersonBorrowerModel.houseRegisteProvince}');
		var id = $("#houseRegisteProvince option:selected").attr("lang");
		selectCityValue("houseRegisteCity",'${tPersonBorrowerModel.houseRegisteCity}',id);
		
		$("#jobProvince").val('${tJobBorrowerModel.jobProvince}');
		var cityProviceId = $("#jobProvince option:selected").attr("lang");
		selectCityValue("jobCity",'${tJobBorrowerModel.jobCity}',cityProviceId);
		
		$("#jobYear").val('${tJobBorrowerModel.jobYear}');
		
		$("#companyIndustry").val('${tJobBorrowerModel.companyIndustry}');
		
		$("#companyNature").val('${tJobBorrowerModel.companyNature}');
		$("#monthlyIncomeLevel").val('${tPersonBorrowerModel.monthlyIncomeLevel}');
		
		$("input:radio[name='sex'][value='${tPersonBorrowerModel.sex}']").attr('checked','true');
		$("input:radio[name='maritalStatus'][value='${tPersonBorrowerModel.maritalStatus}']").attr('checked','true');
		$("input:radio[name='childrenStatus'][value='${tPersonBorrowerModel.childrenStatus}']").attr('checked','true');
		$("input:radio[name='houseStatus'][value='${tPersonBorrowerModel.houseStatus}']").attr('checked','true');
		$("input:radio[name='carStatus'][value='${tPersonBorrowerModel.carStatus}']").attr('checked','true');
		
		//借款信息
		
		$("#repayType").val('${tBorrowModel.repayType}');
		$("#borrowType").val('${tBorrowModel.borrowType}');
		$("input:radio").attr('disabled','true');
		$("select").attr('disabled','true');
		$("input[name='borrowStatus']").attr('disabled',false);
		
	});
//-->

var valiForm = $("#editForm").Validform({
		btnSubmit : "#btnSave",
		tiptype : function(msg, o, cssctl) {
			var objtip = $("#errorMsg");
			cssctl(objtip, o.type);
			objtip.text(msg);
		},
		postonce : true,
		beforeSubmit:function(curform){
			var status = $("input:radio[name='borrowStatus']:checked").val();
			if(status==0){
				if(confirm("确定审核不通过嘛？")){
					return true;
				}else{
					return false;
				}
			}else{
				if(confirm("确定审核通过嘛？")){
					return true;
				}else{
					return false;
				}
			}
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

	
	var valiForm = $("#editForm2").Validform({
		btnSubmit : "#btnSave2",
		tiptype : function(msg, o, cssctl) {
			var objtip = $("#errorMsg");
			cssctl(objtip, o.type);
			objtip.text(msg);
		},
		postonce : true,
		beforeSubmit:function(curform){
			var attrName1 = $("#attrName1").val();
			var attrPath1= $("#attrPath1").val();
			if(attrName1==""){
				alert("请输入资料名称");
				return false;
			}else if(attrPath1==""){
				alert("请选择资料图片");
				return false;
			}
		
		},
		ajaxPost : true,
		callback : function(data) {
			alert(data.info);
			if (data.status == 'y') {
				goUrl('queryTBorrowIndex?id=${tBorrowModel.id }');
			}
		}
	});
	$.Tipmsg.r = "";
	
	function uploadBorrowFile(obj){
		
		openUpload($(obj).attr("id"),"Images:/borrow/");
	}
	
	function aa(a){
		if(confirm('确定要删除？'))
		{
			$.ajax({
				type:"post",
				url:"deleteTBorrowAttr",
				data:{id:a},
				success: function(data){
					if (data!=null) {
						alert("操作成功");
						goUrl('queryTBorrowIndex?id=${tBorrowModel.id }');
					}else{
						alert("操作失败");
					}
					
				}
			});
			
		}else{}	
	};
	
	$("#showyesorno").click(function(){
		$("#addtbattr").show();
	});
</script>
