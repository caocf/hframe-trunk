package com.hframe.client.info.baidu;

import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * 百度地图工具类
 * Created by atom on 2015/6/9.
 */
public class BaiduMapUtil {

    private static String base_url = "http://api.map.baidu.com/geocoder/v2";
    private static String ak = "V8aRPU6kXiB3Gf3TtSaYWbQ0";

    /**
     * 根据地址获取坐标信息
     *
     * @param address 地址
     * @return
     * @author atom
     */
    public static Map<String, Double> getCoordinateByAddress(String address) {
        System.out.println(address);
        Map<String, Double> map = new HashMap<String, Double>();
        String url = base_url + "/?address=" + address + "&output=json&ak=" + ak;
        //System.out.println(url);
        String json = loadJSON(url);
        JSONObject obj = JSONObject.fromObject(json);
        if (obj.get("status").toString().equals("0")) {
            double lng = obj.getJSONObject("result").getJSONObject("location").getDouble("lng");
            double lat = obj.getJSONObject("result").getJSONObject("location").getDouble("lat");
            map.put("lng", lng);
            map.put("lat", lat);
            //System.out.println("经度：" + lng + "---纬度：" + lat);
        } else {
            System.out.println("未找到相匹配的经纬度！");
        }
        return map;
    }

    public static String loadJSON(String url) {
        StringBuilder json = new StringBuilder();
        try {
            URL oracle = new URL(url);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream()));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        return json.toString();
    }


    /**
     * 根据经纬度计算距离
     *
     * @param sLat 起点纬度
     * @param sLon 起点经度
     * @param eLat 终点纬度
     * @param eLon 终点经度
     * @param isKm 返回的距离单位
     * @return 距离
     */
    public static double getDistance(double sLat, double sLon, double eLat, double eLon, boolean isKm) {
        double distance = 0;
        if (sLat > 0 && sLon > 0 && eLat > 0 && eLon > 0)
            if (isKm)
                distance = Math.sqrt(Math.abs(sLat - eLat) * Math.abs(sLat - eLat) + Math.abs(sLon - eLon) * Math.abs(sLon - eLon)) * 100; //公里
            else
                distance = Math.sqrt(Math.abs(sLat - eLat) * Math.abs(sLat - eLat) + Math.abs(sLon - eLon) * Math.abs(sLon - eLon)) * 100000; //米
        return distance;
    }





}
