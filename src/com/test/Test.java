package com.test;

import java.math.BigDecimal;
import java.util.List;

import com.baofoo.p2p.dto.receive.BankDto;
import com.baofoo.p2p.dto.receive.ResultDto;
import com.baofoo.p2p.util.CommonUtil;
import com.baofoo.p2p.util.Constant;
import com.baofoo.p2p.util.XMLBuild;
import com.p2p.constans.BaofooConstans;
import com.p2p.security.AES;

public class Test {
	public void testRedBao(BigDecimal firstInvest ){
		BigDecimal bonus = null;
		BigDecimal hundred = new BigDecimal("100");
		BigDecimal thousand = new BigDecimal("1000");
		BigDecimal million = new BigDecimal("10000");
		if(firstInvest.compareTo(hundred)>=0 && firstInvest.compareTo(thousand)<0){
			bonus=new BigDecimal("20");
		}else if(firstInvest.compareTo(thousand)>=0 && firstInvest.compareTo(million)<0){
			bonus=new BigDecimal("38");
		}else if(firstInvest.compareTo(million)>=0){
			bonus=new BigDecimal("88");
		}
		System.out.println(bonus);
	}
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		Test ts = new Test();
		ts.testRedBao(new BigDecimal("1001"));
		String info = "23c770d33ad254ffd78263e705057bafb72305412daafcf608f3f39fb5a6713848e2ef750cbbf6f33d9de0b5af86630a435f51013ff1595cbbef8101e32905afee2ead1d558f48a2a9a7edeefa9a1778a79a95fb7abe7773a4a925a9ebb99378b2ea2a5f49d70f5094eecd295ab075f4fd02e35b75119b68b8084071b06944144ce25cd80306c5c9e22e40273b2c801a4ef91021ca39a0d03bd9383d6ac0ec039d9ec33ae19026aeef251d77a4df8d175f8fc50db194202ff1c014f16ff77f881a02e17fee46d5c974f7369a7d93608bdafe6d220d3f97d7694f5fbbae0c5233";
		
//		System.out.println(info);
		String xml = BaofooConstans.REQUEST_SERVICE.serv_BankCardList("980089734");
		System.out.println("获取用户银行卡信息，返回XML："+xml);
		ResultDto dto = XMLBuild.parseXMLToEntity( xml , ResultDto.class);
		String plaintext = XMLBuild.xmlHead + CommonUtil.aesDecryptKey16( dto.getInfo() , Constant.KEY);
		System.out.println("获取用户银行卡信息，返回plaintext："+plaintext);
		List<BankDto> resultDto = BaofooConstans.RECEIVE_SERVICE.serv_BankCardList(xml);
		System.out.println("获取用户银行卡信息，返回plaintext："+resultDto.get(0).getBank_name());
		System.out.println("："+System.getProperty("file.encoding"));; 
		System.out.println("aec plaintext："+AES.decrypt( dto.getInfo(),  Constant.KEY,Constant.KEY,"UTF-8"));
	}
}
