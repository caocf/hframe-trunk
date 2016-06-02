package com.hframework.common.util.message;

import com.hframework.common.util.FileUtils;
import com.hframework.common.util.StringUtils;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * User: zhangqh6
 * Date: 2016/1/21 13:28:28
 */
public class XmlUtils {
    private static final Logger logger = LoggerFactory.getLogger(XmlUtils.class);

    public static <T> T readValue(String content, Class<T> valueType) {
        XStream xstream = new XStream(new DomDriver());
        xstream.processAnnotations(valueType);
        return (T) xstream.fromXML(content);
    }

    public static <T> T readValueFromFile(String filePath, Class<T> valueType) throws IOException {
        logger.debug("入参：{}|{}",filePath,valueType);
        String rootClassPath = Thread.currentThread().getContextClassLoader ().getResource("").getPath();
        String xmlString = FileUtils.readFile(rootClassPath + "/"+ filePath);
        logger.debug("报文：{}", xmlString);
        T t = readValue(xmlString, valueType);
        logger.debug("对象：{}", t);
        return t;
    }

    public static <T> List<T> readValuesFromDirectory(String directory, Class<T> valueType, String format) throws IOException {
        List<T> result = new ArrayList<T>();
        logger.debug("入参：{}|{}",directory,valueType);
        String rootClassPath = Thread.currentThread().getContextClassLoader ().getResource("").getPath();
        File[] fileList = FileUtils.getFileList(new File(rootClassPath + "/" + directory));
        for (File file : fileList) {
            if(StringUtils.isBlank(format) || file.getName().endsWith(format)) {
                String xmlString = FileUtils.readFile(file.getAbsolutePath());
                logger.debug("报文：{}", xmlString);
                T t = readValue(xmlString, valueType);
                result.add(t);
                logger.debug("对象：{}", t);
            }
        }

        return result;
    }

    public static <T> List<T> readValuesFromDirectory(String directory, Class<T> valueType) throws IOException {
        List<T> result = new ArrayList<T>();
        logger.debug("入参：{}|{}",directory,valueType);
        String rootClassPath = Thread.currentThread().getContextClassLoader ().getResource("").getPath();
        File[] fileList = FileUtils.getFileList(new File(rootClassPath + "/" + directory));
        for (File file : fileList) {
            String xmlString = FileUtils.readFile(file.getAbsolutePath());
            logger.debug("报文：{}", xmlString);
            T t = readValue(xmlString, valueType);
            result.add(t);
            logger.debug("对象：{}", t);
        }

        return result;
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
