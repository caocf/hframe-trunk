package com.ucfgroup.client.weixinpay;

import java.util.*;

import com.hframework.common.util.message.XmlUtils;
import com.hframework.common.util.protocol.HttpClient;
import com.hframework.common.util.UrlHelper;

import com.ucfgroup.client.weixinpay.bean.*;
import com.hframework.common.util.FileUtils;

/**
 * generated by hframework on 2016-04-22.
 */
public class WeixinpayClient   {

	
	public static UnifiedOrderResponse unifiedorder(UnifiedOrderRequest requestData)  throws Exception{
			Map<String, String> parameterMap = new LinkedHashMap();
			String url = UrlHelper.getFinalUrl(WeixinpayConfig.getInstance().getUnifiedorder(), parameterMap);
			String result;
			if("true".equals(WeixinpayConfig.getInstance().getTestModel())) {
			   result = FileUtils.readFile(Thread.currentThread().getContextClassLoader().getResource(
			          "third/weixinpay/unifiedorder.response").getPath());
			}else {
			   result = HttpClient.doXmlPost(url,requestData.convert());
			}
			UnifiedOrderResponse responseData = XmlUtils.readValue(result, UnifiedOrderResponse.class);
			return responseData.convert();
	}

	
	public static OrderQueryResponse orderquery(OrderQueryRequest requestData)  throws Exception{
			Map<String, String> parameterMap = new LinkedHashMap();
			String url = UrlHelper.getFinalUrl(WeixinpayConfig.getInstance().getOrderquery(), parameterMap);
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
			Map<String, String> parameterMap = new LinkedHashMap();
			String url = UrlHelper.getFinalUrl(WeixinpayConfig.getInstance().getCloseorder(), parameterMap);
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
			Map<String, String> parameterMap = new LinkedHashMap();
			String url = UrlHelper.getFinalUrl(WeixinpayConfig.getInstance().getDownloadbill(), parameterMap);
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
			Map<String, String> parameterMap = new LinkedHashMap();
			String url = UrlHelper.getFinalUrl(WeixinpayConfig.getInstance().getRefund(), parameterMap);
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
			Map<String, String> parameterMap = new LinkedHashMap();
			String url = UrlHelper.getFinalUrl(WeixinpayConfig.getInstance().getRefundquery(), parameterMap);
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
