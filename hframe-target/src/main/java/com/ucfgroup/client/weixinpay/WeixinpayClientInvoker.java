package com.ucfgroup.client.weixinpay;

import com.hframe.common.util.FileUtils;
import com.ucfgroup.client.weixinpay.bean.OrderQueryRequest;
import com.ucfgroup.client.weixinpay.bean.OrderQueryResponse;

/**
 * Created by zhangquanhong on 2016/4/19.
 */
public class WeixinpayClientInvoker {

    public static void main(String[] args) throws Exception {
        OrderQueryRequest requestData = new OrderQueryRequest();
        requestData.setTransactionId("234432432");
        OrderQueryResponse orderquery = WeixinpayClient.orderquery(requestData);
        orderquery.convert();
//        System.out.println(FileUtils.readFile(Thread.currentThread().getContextClassLoader().getResource("third/weixinpay/orderquery.response").getContent()));

    }
}
