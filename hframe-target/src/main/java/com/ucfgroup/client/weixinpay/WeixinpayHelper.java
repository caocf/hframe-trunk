package com.ucfgroup.client.weixinpay;


import com.hframe.common.helper.Rules;

public class WeixinpayHelper   {


	public static String md5RandomNumber()  throws Exception{
			return Rules.randomChar32();
	}

	public static String encryptSign(Object object)  throws Exception{
			return Rules.signAllNotEmptyParams(object, "&key=" + WeixinpayConfig.getInstance().getKey());
	}

	public static String decryptSign(Object object)  throws Exception{
			return Rules.checkAllNotEmptyParamsSign(object,"&key="+WeixinpayConfig.getInstance().getKey());
	}

  
 
}
