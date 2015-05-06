<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!--右边框架开始-->
<div class="alert alert-info">
	当前位置<b class="tip"></b>借款人个人信息
</div>
<div class="xiangqing">
	<table >
		<tr align="left">
			<td  align="left" valign="top"  colspan="6"><b>个人信息</b>
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
			<td align="left" valign="top"  colspan="6"><strong>工作信息</strong>
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
		<tfoot>
			<tr>
				<td colspan="6"><input class="btn btn-inverse" id="btnBack"
					type="button" onclick="goUrl('queryTPersonBorrowerList')" value="返回" /></td>
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
		
		
		$("input:radio").attr('disabled','true');
		$("select").attr('disabled','true');
		
	});
//-->

</script>
