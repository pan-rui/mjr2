<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/comm/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>P2P互联网金融理财平台！年化16%以上的P2P平台</title>
<meta name="keywords" content="P2P理财，实物抵押，互联网金融，P2P" />
<meta name="description"
	content="严格的风控体系，100%本息保障！富了么？想富就上P2P互联网金融理财平台_小微金融，普惠大众。" />
<link rel="stylesheet" href="css/common.css" type="text/css" />
<link rel="stylesheet" href="css/page.css" type="text/css" />



</head>

<body>
	<!--借款协议（范本） start-->
	<div class="loan_agreement" style="display: block;">
		<h2
			style="text-align:center; color:#3c3c3c; font-size:30px; font-weight:bold;">借款协议</h2>
		<span style="padding-left:624px;">合同编号：</span><br />
		<p>本借款协议（以下简称“本协议”）</p>


		<p><h2 class="loan_ptit">甲方（借出方）：</h2>
P2P用户名：${amdto.userName}<span style="padding-left:220px;">&nbsp;</span>真实姓名：${amdto.realName }<br />
借出金额：${amdto.realAmount }<span style="padding-left:248px;">&nbsp;</span>借款期限：${amdto.deadline }
<c:choose><c:when test="${amdto.deadlineType==1 }">天</c:when><c:when test="${amdto.deadlineType==2 }">月</c:when></c:choose><br />
</p>
<br />
<p>乙方（借入方）<br />
真实姓名：${amdto.borrowerName }<br />
身份证号：${amdto.borrowerIdCard }<br />
</p>
<br />
<p>丙方(担保方)：
<br />

居间服务方：
深圳市P2P金融信息服务有限公司
</p>
<br />
<p>
		<h2 class="loan_ptit">鉴于：</h2>
乙方因经营周转或消费需求，通过居间服务方提供的互联网金融服务平台（http://www.p2p.com）向甲方进行借款，担保方就该借款承担不可撤销的担保责任。现各方依据中华人民共和国相关法律法规，在平等自愿、互利互惠的基础上，经友好协商，达成如下协议，以资共同信守。<br />
</p>
<br />
<p>

		<h2 class="loan_ptit">第一条 总则</h2>
1.1  居间服务方是一家在深圳市合法成立并有效存续的有限责任公司，旗下拥有www.p2p.com网站（以下简称P2P）的经营权，为甲乙双方的交易提供信息服务。<br />
1.2  甲方承诺对本协议涉及的借款具有完全的支付能力，是其自有的闲散资金，为其合法所得;并承诺其提供给居间服务方的信息是完全真实的<br />
1.3  乙方系完全民事行为能力人，并能够独立承担民事责任，具有清偿债务的能力<br />
1.4  丙方承诺并保证：丙方作为上述债权的担保方，将履行《借款（保证）合同》中约定的担保义务，以保证甲方的债权权益不受侵害。<br />
1.5  乙方有借款需求，甲方有意愿借款，双方有意成立借贷关系，甲乙双方均已在第三方托管平台（宝付）开设账户，并将此帐户作为借款资金流向的指定账户使用，第三方托管平台所开设的账户履行代管、代收、代付服务，保证资金流向渠道畅通。
</p>
<br />
<p>
		<h2 class="loan_ptit">第二条 借款信息</h2>
2.1  借款概览<br />
借款用途：${amdto.borrowPurpose }<br />
借款本金数额：${amdto.realAmount }<span style="padding-left:224px;">借款年利率：${amdto.annualRate }%<br />
借款期限：${amdto.deadline } <c:choose><c:when test="${amdto.deadlineType==1 }">天</c:when><c:when test="${amdto.deadlineType==2 }">月</c:when></c:choose>   <span style="padding-left:248px;">还款日：<fmt:formatDate value="${amdto.repayDate }" pattern="yyyy-MM-dd" /><br />
2.2  借款的期限：自   <fmt:formatDate value="${amdto.auditTime }" pattern="yyyy" />年 <fmt:formatDate value="${amdto.auditTime }" pattern="MM" />月 <fmt:formatDate value="${amdto.auditTime }" pattern="dd" /> 日起至    <fmt:formatDate value="${amdto.repayDate }" pattern="yyyy" />年<fmt:formatDate value="${amdto.repayDate }" pattern="MM" />月<fmt:formatDate value="${amdto.repayDate }" pattern="dd" />日止，共计 ${amdto.deadline } 
<c:choose> <c:when test="${amdto.deadlineType==1 }">天</c:when><c:when test="${amdto.deadlineType==2 }">月</c:when>
</c:choose>，以甲方所投标的满标后起始之次日为利息起算日，并不得续展延期。实际借款日与到期日以第三方托管支付平台的转账凭证为准。<br />

2.3  甲方向乙方提供借款，本合同项下利率按以下方法折算：<br />
月利率=年利率÷12，日利率＝年利率÷365。<br />
2.4  本合同有效期内的年利率为${amdto.annualRate }%。<br />
2.5  款项通过第三方托管支付平台进行划拨<br />
</p>
<br />
<p>

				<h2 class="loan_ptit">第三条 还款事宜</h2>
<c:choose><c:when test="${amdto.repayType==1 }">
3.1  利息支付：甲乙双方同意按以下第二种方式执行。<br />
<span class="loan_p3"></span>3.1.1  按月付息方式：本合同项下的借款到账后，每月的 *日为利息还款日。乙方每月应在利息还款日将当月应付利息一次性支<br />
<span class="loan_p4"></span>付至甲方在第三方托管支付平台开设的账户。<br />
<span class="loan_p3"></span>3.1.2  本合同到期一次性付息：本合同项下的借款期限届满，乙方应于<fmt:formatDate value="${amdto.repayDate }" pattern="yyyy" />年<fmt:formatDate value="${amdto.repayDate }" pattern="MM" />月<fmt:formatDate value="${amdto.repayDate }" pattern="dd" />日将全部应付利息一次性支付至甲方在第三方托管支付<br />
<span class="loan_p4"></span>平台开设的账户。<br />
3.2  本金偿还：甲乙双方同意按以下第二种方式执行。<br />
<span class="loan_p3"></span>3.2.1  在本合同项下借款到账后，每月的*日为本金还款日，乙方每月应在本金还款日将当月应还本金一次性支付至甲方在第三<br />
<span class="loan_p4"></span>方支付平台开设的账户。<br />
<span class="loan_p3"></span>3.2.2  在本合同项下借款到账后，采用到期一次性归还本金方式。乙方应于<fmt:formatDate value="${amdto.repayDate }" pattern="yyyy" />年<fmt:formatDate value="${amdto.repayDate }" pattern="MM" />月<fmt:formatDate value="${amdto.repayDate }" pattern="dd" />日将借款本金一次性支付至甲方在第三<br />
<span class="loan_p4"></span>方支付平台开设的账户。<br />

</c:when><c:when test="${amdto.repayType==2 }">
3.1  利息支付：甲乙双方同意按以下第一种方式执行。<br />
<span class="loan_p3"></span>3.1.1  按月付息方式：本合同项下的借款到账后，每月的<fmt:formatDate value="${amdto.avaryMout }" pattern="dd" />日为利息还款日。乙方每月应在利息还款日将当月应付利息一次性支<br />
<span class="loan_p4"></span>付至甲方在第三方托管支付平台开设的账户。<br />
<span class="loan_p3"></span>3.1.2  本合同到期一次性付息：本合同项下的借款期限届满，乙方应于*年*月*日将全部应付利息一次性支付至甲方在第三方托管支付<br />
<span class="loan_p4"></span>平台开设的账户。<br />

3.2  本金偿还：甲乙双方同意按以下第一种方式执行。<br />
<span class="loan_p3"></span>3.2.1  在本合同项下借款到账后，每月的<fmt:formatDate value="${amdto.avaryMout }" pattern="dd" />日为本金还款日，乙方每月应在本金还款日将当月应还本金一次性支付至甲方在第三<br />
<span class="loan_p4"></span>方支付平台开设的账户。<br />
<span class="loan_p3"></span>3.2.2  在本合同项下借款到账后，采用到期一次性归还本金方式。乙方应于*年*月*日将借款本金一次性支付至甲方在第三<br />
<span class="loan_p4"></span>方支付平台开设的账户。<br />
</c:when> </c:choose>

3.3  提前还款<br />
<span class="loan_p3"></span>3.3.1  乙方如需要提前部分或全部还款，应在本合同项下规定的本金还款日的15日前书面通知居间服务方，经居间服务方协调其他当事人同意后，乙方须一次性将拟还本息结清。<br />
<span class="loan_p3"></span>3.3.2  乙方提前还款，已支付的利息和相关费用不予退还。未提前归还的款项，乙方偿还本息后仍依本合同履行相关义务。<br />
</p>
<br />
<p>

				<h2 class="loan_ptit">第四条  各方权利与义务</h2>
4.1  甲方的权利与义务<br />

<span class="loan_p3"></span>4.1.1  甲方保证期所出借的资金来源的合法性，甲方是该资金的合法所有人。<br />
<span class="loan_p3"></span>4.1.2  甲方享有其所出借款项所带来的利息收益，并应主动缴纳由利息所带来的可能的税费。<br />
<span class="loan_p3"></span>4.1.3  如乙方违约，甲方有权要求丙方提供其已获得的乙方信息。<br />
<span class="loan_p3"></span>4.1.4  就甲方在本协议项下对乙方的部分或者全部债权，甲方有权根据自己的意愿对外转让，并授权P2P可以视情况将债权转让事项通知乙方。<br />

4.2  乙方的权利与义务<br />

<span class="loan_p3"></span>4.2.1  乙方必须按期足额向甲方归还应还本金和利息，并支付相关借款管理费和服务费，若发生逾期，还要支付因逾期产生的一切费用。<br />
<span class="loan_p3"></span>4.2.2  乙方承诺所借款项用途为真实借款用途，不得用于任何违法用途。<br />
<span class="loan_p3"></span>4.2.3  乙方确认甲方可以根据自己的意愿对其根据本协议形成的对乙方的部分或全部债权进行转让，并特此同意甲方届时转让债权无需另行通知乙方；同时乙方承诺若届时需要，将采取必要措施配合债权的一次或多次转让或届时债权人债权的实现。在债权转让后，甲方在本协议项下的权利和义务自动转移到债权受让人名下，乙方应按照本协议的约定向债权受让人支付每期应还借款本息，不得以未接到债权转让通知为由拒绝履行还款义务。<br />
<span class="loan_p3"></span>4.2.4  乙方应确保其提供的信息和资料的真实性，不得提供虚假信息或隐瞒重要事实。若乙方违反本协议约定，P2P有权根据本协议及有关条款对乙方的信息和资料予以合理的披露。<br />
<span class="loan_p3"></span>4.2.5  乙方不得将本协议项下的任何权利义务转让给任何其他地方。<br />

4.3  丙方权利与义务<br />

<span class="loan_p3"></span>4.3.1  担保范围：本合同项下的借款本金及应收利息。<br />
<span class="loan_p3"></span>4.3.2  担保方式：本合同约定的履行期限届满，乙方未履行或未完全履行还本付息义务，丙方在借款期限届满之日起五个工作日内无条件的向甲方支付甲方应付款项，丙方承担连带保证责任。<br />
<span class="loan_p3"></span>4.3.3  担保期间：自本合同生效之日起直至借款合同到期。<br />
<span class="loan_p3"></span>4.3.4  丙方履行担保责任代为清偿甲方的应还款项后，即取得了对乙方的追索权。<br />
<span class="loan_p3"></span>4.3.5  合同有效期内，丙方按合同履行担保责任为乙方代偿一期或数期债务，不影响丙方对剩余债务继续承担连带保证责任。<br />
<span class="loan_p3"></span>4.3.6发生以下情形之一，担保方不承担本合同约定的担保责任：<br />
<span class="loan_p4"></span>（1） 除本合同另有约定外，未经担保方书面同意，甲乙双方变更本合同内容的；<br />
<span class="loan_p4"></span>（2）未经担保方书面同意，乙方同意甲方转让本合同的；<br />
<span class="loan_p4"></span>（3）其他甲乙双方侵害担保方权益的行为。<br />

4.4  居间方（P2P）权利与义务<br />

<span class="loan_p3"></span>4.4.1  甲方同意向乙方出借相应款项时，已委托丙方在本协议成立后将该笔借款直接划付至乙方账户。甲方授权并委托P2P平台代表其收取本协议所约定的出借人每月应收本息，代收后按照甲方的要求进行处置，乙方对此表示认可。<br />
<span class="loan_p3"></span>4.4.2  P2P平台有权就为本协议借款所提供的服务向甲方收取平台手续费，向乙方收取借款管理费和/或服务费。<br />
<span class="loan_p3"></span>4.4.3  P2P平台接受甲乙双方的委托行为所产生的法律后果由相应委托方承担。如因乙方或甲方或其他方（包括但不限于技术问题）造成的延误或错误，P2P平台不承担任何责任。<br />
<span class="loan_p3"></span>4.4.4  P2P平台应对甲方和乙方的信息及本协议内容保密;如任何一方违约，或因相关权利部门要求（包括但不限于法院、仲裁机构、金融监管机构等），P2P平台有权披露。<br />
</p>
<br />
<p>
				<h2 class="loan_ptit">第五条  借款服务费等费用</h2>
5.1  甲方通过P2P平台获得收益，P2P平台会收取甲方收益的10%作为居间手续费。<br />
5.2  乙方通过P2P平台获得借款，P2P平台会收取乙方借款金额0%的作为借款管理费。<br />
5.3  如果乙方与P2P平台协商一致调整借款管理费和居间服务费时，无需经甲方同意。<br />
</p>
<br />
<p>

				<h2 class="loan_ptit">第六条  违约责任</h2>
6.1  借款期限届满，若乙方未能按本合同约定的期限及还款方式归还借款本息的，甲方有权向乙方催收，或要求担保方承担代偿责任。<br />
6.2  各方同意，任一方违反、不履行、不遵守其在本合同中约定的义务，致使其他方遭受或发生损害、损失、索赔、处罚、诉讼、仲裁，均应承担相应的赔偿责任。<br />
</p>
<br />
<p>

				<h2 class="loan_ptit">第七条  合同生效及其他条款</h2>
7.1  本合同自甲方投资款项划转至乙方账户或乙方指定的其他收款账户时生效。<br />
7.2  本协议的签订地及履行地均为广东省深圳，本协议的签订、履行、终止、解释均适用中华人民共和国法律。<br />
7.3  在本合同履行过程中发生的纠纷，应协商解决；协商未果，任何一方均有权向广东省深圳市宝安区人民法院提起诉讼。<br />
7.4  本合同未尽事宜，以居间服务方的互联网金融服务平台公布的规则和通知为准。<br />
7.5  本合同经甲方通过居间服务方的互联网金融服务平台以在线点击确认后即订立生效，正常操作自动生成电子合同，均为各方真实的意思表示，对各方均有法律约束力。各方签署纸质合同的，电子合同与纸质合同具有同等的法律效力。<br />
</p>
<p class="loan_close">
					<a href="javascript:closeLoan();" class="l_close">关闭</a>
				</p>

	</div>
<!--借款协议（范本） start-->
</html>
