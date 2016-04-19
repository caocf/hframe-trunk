package com.hframe.client.bean;

import com.hframe.common.util.message.XmlUtils;
import com.sun.org.apache.xpath.internal.operations.Mod;

import java.io.*;

/**
 * Created by zhangquanhong on 2016/4/15.
 */
public class Demo {

    public static void main(String[] args) throws IOException {
        String rootClassPath = Thread.currentThread().getContextClassLoader ().getResource("").getPath();
        String xmlString = readFile(rootClassPath + "test.xml");
        System.out.println(xmlString);
        Module descriptor = XmlUtils.readValue(xmlString, Module.class);
        System.out.println(XmlUtils.writeValueAsString(descriptor));
    }

    private static String readFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName))));
        String retStr = "";
        String str = br.readLine();
        while(str != null) {
            retStr += str;
            str = br.readLine();
        }
        return retStr;
    }
}
