package com.hframe.client.info.baidu;

import com.hframe.client.info.baidu.entity.BaiduMapResult;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * User: zhangqh6
 * Date: 2016/4/10 21:58:58
 */
public class BaiduMapClient {

    private static Map<String, String> urlStore = new HashMap<String, String>(){{
        put("getAddress","http://api.map.baidu.com/geocoder/v2?address={0}&name={2}");
    }};

    public BaiduMapResult getAddress(String address, long mobile) {

        Map params = new LinkedHashMap();
        params.put("address", address);
        params.put("output", BaiduMapConfig.OUTPUT);
        params.put("ak", BaiduMapConfig.AK);
        params.put("sign", md5(address,BaiduMapConfig.OUTPUT,BaiduMapConfig.AK));
        params.put("mobile", aes(mobile));

        //HTTP.doPost doGet..
        String message = null;

        //JsonUtils.parse..
        BaiduMapResult result = null;

        //TODO 定义对应接口并实现
//        result.decoding();

        return result;
    }

    private String aes(long mobile) {
        return null;//TODO
    }

    private String md5(String address, String output, String ak) {
        return null;//TODO
    }

}
