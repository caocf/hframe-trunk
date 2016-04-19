package com.ucfgroup.client.weixinpay;

import java.util.HashMap;
import com.hframe.common.util.protocol.HttpClient;
import java.text.MessageFormat;
import com.hframe.common.util.message.*;
import com.ucfgroup.client.weixinpay.bean.*;
import com.hframe.common.util.FileUtils;


public class WeixinpayClient   {


	public static UnifiedOrderResponse unifiedorder(UnifiedOrderRequest requestData)  throws Exception{
			String url = MessageFormat.format(WeixinpayConfig.getInstance().getUnifiedorder(),null);
			String result;
			if("true".equals(WeixinpayConfig.getInstance().getTestModel())) {
			   result = FileUtils.readFile(Thread.currentThread().getContextClassLoader().getResource(
			          "third/weixinpay/unifiedorder.response").getPath());
			}else {
			   result = HttpClient.doXmlPost(url,requestData.convert());
			}
			UnifiedOrderResponse responseData = XmlUtils.readValue(result,UnifiedOrderResponse.class);
			return responseData.convert();
	}

	public static OrderQueryResponse orderquery(OrderQueryRequest requestData)  throws Exception{
			String url = MessageFormat.format(WeixinpayConfig.getInstance().getOrderquery(),null);
			String result;
			if("true".equals(WeixinpayConfig.getInstance().getTestModel())) {
			   result = FileUtils.readFile(Thread.currentThread().getContextClassLoader().getResource(
			          "third/weixinpay/orderquery.response").getPath());
			}else {
			   result = HttpClient.doXmlPost(url,requestData.convert());
			}
			OrderQueryResponse responseData = XmlUtils.readValue(result,OrderQueryResponse.class);
			return responseData.convert();
	}

	public static CloseOrderResponse closeorder(CloseOrderRequest requestData)  throws Exception{
			String url = MessageFormat.format(WeixinpayConfig.getInstance().getCloseorder(),null);
			String result;
			if("true".equals(WeixinpayConfig.getInstance().getTestModel())) {
			   result = FileUtils.readFile(Thread.currentThread().getContextClassLoader().getResource(
			          "third/weixinpay/closeorder.response").getPath());
			}else {
			   result = HttpClient.doXmlPost(url,requestData.convert());
			}
			CloseOrderResponse responseData = XmlUtils.readValue(result,CloseOrderResponse.class);
			return responseData.convert();
	}

	public static String downloadbill(DownloadBillRequest requestData)  throws Exception{
			String url = MessageFormat.format(WeixinpayConfig.getInstance().getDownloadbill(),null);
			String result;
			if("true".equals(WeixinpayConfig.getInstance().getTestModel())) {
			   result = FileUtils.readFile(Thread.currentThread().getContextClassLoader().getResource(
			          "third/weixinpay/downloadbill.response").getPath());
			}else {
			   result = HttpClient.doXmlPost(url,requestData.convert());
			}
			return result;
	}

	public static RefundResponse refund(RefundRequest requestData)  throws Exception{
			String url = MessageFormat.format(WeixinpayConfig.getInstance().getRefund(),null);
			String result;
			if("true".equals(WeixinpayConfig.getInstance().getTestModel())) {
			   result = FileUtils.readFile(Thread.currentThread().getContextClassLoader().getResource(
			          "third/weixinpay/refund.response").getPath());
			}else {
			   result = HttpClient.doXmlPost(url,requestData.convert());
			}
			RefundResponse responseData = XmlUtils.readValue(result,RefundResponse.class);
			return responseData.convert();
	}

	public static RefundQueryResponse refundquery(RefundQueryRequest requestData)  throws Exception{
			String url = MessageFormat.format(WeixinpayConfig.getInstance().getRefundquery(),null);
			String result;
			if("true".equals(WeixinpayConfig.getInstance().getTestModel())) {
			   result = FileUtils.readFile(Thread.currentThread().getContextClassLoader().getResource(
			          "third/weixinpay/refundquery.response").getPath());
			}else {
			   result = HttpClient.doXmlPost(url,requestData.convert());
			}
			RefundQueryResponse responseData = XmlUtils.readValue(result,RefundQueryResponse.class);
			return responseData.convert();
	}

  
 
}
