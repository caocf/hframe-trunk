package com.hframe.common.util.message;

import com.hframe.common.util.FileUtils;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.*;

/**
 * User: zhangqh6
 * Date: 2016/1/21 13:28:28
 */
public class XmlUtils {

    public static <T> T readValue(String content, Class<T> valueType) {
        XStream xstream = new XStream(new DomDriver());
        xstream.processAnnotations(valueType);
        return (T) xstream.fromXML(content);
    }

    public static <T> T readValueFromFile(String filePath, Class<T> valueType) throws IOException {
        String rootClassPath = Thread.currentThread().getContextClassLoader ().getResource("").getPath();
        String xmlString = FileUtils.readFile(rootClassPath + "/"+ filePath);
        System.out.println(xmlString);
        return readValue(xmlString,valueType);
    }





    public static  <T> String writeValueAsString(T t) throws IOException {
        XStream xstream = new XStream(new DomDriver("utf8"));
        xstream.processAnnotations(t.getClass());// 识别obj类中的注解
        /*
         // 以压缩的方式输出XML
         StringWriter sw = new StringWriter();
         xstream.marshal(obj, new CompactWriter(sw));
         return sw.toString();
         */
        // 以格式化的方式输出XML
        return xstream.toXML(t);
    }

    public static void main(String[] args) throws IOException {
        String rootClassPath = Thread.currentThread().getContextClassLoader ().getResource("").getPath();
        String xmlString = readFile(rootClassPath + "test.xml");
        System.out.println(xmlString);
//        Menu menu = readValue(jsonString, Menu.class);
//        System.out.println(writeValueAsString(menu));
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
