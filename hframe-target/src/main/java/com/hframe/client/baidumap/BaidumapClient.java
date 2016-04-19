package com.hframe.client.baidumap;

import java.util.HashMap;
import com.hframe.common.util.protocol.HttpClient;
import java.text.MessageFormat;
import com.hframe.common.util.message.*;
import com.hframe.client.baidumap.bean.*;
import com.hframe.common.util.FileUtils;


public class BaidumapClient   {


	public static ResponseData getAddress(String address, long mobile, RequestData requestData)  throws Exception{
			String url = MessageFormat.format(BaidumapConfig.getInstance().getGetAddress(),address,mobile,BaidumapHelper.md5(String.valueOf(address),String.valueOf(mobile)));
			String result;
			if("true".equals(BaidumapConfig.getInstance().getTestModel())) {
			   result = FileUtils.readFile(Thread.currentThread().getContextClassLoader().getResource(
			          "third/baidumap/getAddress.response").getPath());
			}else {
			   result = HttpClient.doJsonPost(url,requestData.convert());
			}
			ResponseData responseData = JsonUtils.readValue(result,ResponseData.class);
			return responseData.convert();
	}

	public static String getAddress2()  throws Exception{
			String url = MessageFormat.format(BaidumapConfig.getInstance().getGetAddress2(),null);
			String result;
			if("true".equals(BaidumapConfig.getInstance().getTestModel())) {
			   result = FileUtils.readFile(Thread.currentThread().getContextClassLoader().getResource(
			          "third/baidumap/getAddress2.response").getPath());
			}else {
			   result = HttpClient.doPost(url,new HashMap());
			}
			return result;
	}

  
 
}
