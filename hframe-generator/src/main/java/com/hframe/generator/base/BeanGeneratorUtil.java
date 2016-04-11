package com.hframe.generator.base;

import com.fasterxml.jackson.databind.JsonNode;
import com.hframe.common.util.FileUtils;
import com.hframe.common.util.message.Dom4jUtils;
import com.hframe.common.util.message.JacksonObjectMapper;
import com.hframe.common.util.message.VelocityUtil;
import com.hframe.generator.bean.Field;
import com.hframe.generator.util.CreatorUtil;
import org.dom4j.Document;
import org.dom4j.Element;

import java.io.IOException;
import java.util.*;

/**
 * User: zhangqh6
 * Date: 2016/1/20 16:33:33
 */
public class BeanGeneratorUtil {


    /**
     * 通过Json数据生成Bean对象
     * @param packagePath
     * @param rootClassName
     * @param jsonString
     */
    public static void generateByJson(String packagePath, String rootClassName, String jsonString) throws IOException {
        JsonNode jsonNode = JacksonObjectMapper.getInstance().readTree(jsonString);
        Map<String, Object> mergeMap = new HashMap<String, Object>();
        mergeMap.put(rootClassName, parseJsonNode(jsonNode));
        generateClassByJson(packagePath, rootClassName, mergeMap.get(rootClassName), true);
    }



    /**
     * 通过Xml数据生成Bean对象
     * @param packagePath
     * @param rootClassName
     * @param xmlString
     */
    public static void generateByXml(String packagePath, String rootClassName,String rootXmlName, String xmlString) throws IOException {
        Document document = Dom4jUtils.getDocumentByContent(xmlString);
        Element root = document.getRootElement();
        Map<String, Object> mergeMap = new HashMap<String, Object>();
        mergeMap.put(rootClassName, parseXmlNode(root));
        generateClassByXml(packagePath, rootClassName, rootXmlName, mergeMap.get(rootClassName), true);

    }


    /**
     * 生成类文件
     * @param packagePath
     * @param rootClassName
     * @param rootXmlName
     * @param data
     * @param isRoot
     */
    private static void generateClassByXml(String packagePath, String rootClassName,String rootXmlName, Object data, boolean isRoot) {
        com.hframe.generator.bean.Class beanClass = new com.hframe.generator.bean.Class();
        beanClass.setSrcFilePath("E:\\xfb_workspace\\boomshare\\bs-xfb-wx\\src\\main\\java\\");
        beanClass.setClassPackage(packagePath);
        beanClass.setClassName(rootClassName);
        beanClass.addConstructor();
        beanClass.addAnnotation("@XStreamAlias(\"" + rootXmlName + "\")");

        Map<String, Object> dataMap = new LinkedHashMap<String, Object>();
        if(data instanceof Map) {
            dataMap = (Map<String, Object>) data;
        }else if(data instanceof List){
            dataMap = (Map<String, Object>) ((List) data).get(0);
        }else {
            return ;
        }
        beanClass.addImportClass("com.thoughtworks.xstream.annotations.XStreamAlias");
        beanClass.addImportClass("com.thoughtworks.xstream.annotations.XStreamAsAttribute");

        for (String fieldName : dataMap.keySet()) {
            String subElementName = getSubElementName(dataMap.get(fieldName));
            Field field = getField(fieldName, dataMap.get(fieldName), subElementName);
            field.addFieldAnno("@XStreamAlias(\"" + fieldName + "\")");

            beanClass.addField(field);
            if(!"String".equals(field.getType())) {
                if(field.getType().startsWith("List<") && !beanClass.getImportClassList().contains("java.util.List")) {
                    beanClass.addImportClass("java.util.List");
                }
                if(isRoot) {
                    beanClass.addImportClass(packagePath + "." + CreatorUtil.getJavaClassName(rootClassName).toLowerCase() + ".*");
                }

                if(subElementName != null) {
                    generateClassByXml(packagePath + (isRoot ? ("." + CreatorUtil.getJavaClassName(rootClassName).toLowerCase()) : ""),
                            CreatorUtil.getJavaClassName(subElementName), subElementName, dataMap.get(fieldName), false);
                }else {
                    generateClassByXml(packagePath + (isRoot ? ("." + CreatorUtil.getJavaClassName(rootClassName).toLowerCase()) : ""),
                            CreatorUtil.getJavaClassName(fieldName), fieldName, dataMap.get(fieldName), false);
                }

            }
        }

        Map map = new HashMap();
        map.put("CLASS", beanClass);
        String content = VelocityUtil.produceTemplateContent("com/hframe/generator/vm/poByTemplate.vm", map);
        System.out.println(content);
        FileUtils.writeFile(beanClass.getFilePath(), content);
    }

    /**
     * 获取子元素
     * @param data
     * @return
     */
    private static String getSubElementName(Object data) {
        if(data instanceof List) {
            return (String) ((List) data).get(1);
        }

        return null;
    }


    /**
     * 生成类文件
     * @param packagePath
     * @param rootClassName
     * @param data
     * @param isRoot
     */
    private static void generateClassByJson(String packagePath, String rootClassName, Object data, boolean isRoot) {
        com.hframe.generator.bean.Class beanClass = new com.hframe.generator.bean.Class();
        beanClass.setSrcFilePath("E:\\xfb_workspace\\boomshare\\bs-xfb-wx\\src\\main\\java\\");
        beanClass.setClassPackage(packagePath);
        beanClass.setClassName(rootClassName);
        beanClass.addConstructor();

        Map<String, Object> dataMap = new LinkedHashMap<String, Object>();
        if(data instanceof Map) {
            dataMap = (Map<String, Object>) data;
        }else if(data instanceof List){
            dataMap = (Map<String, Object>) ((List) data).get(0);
        }else {
            return ;
        }
        beanClass.addImportClass("com.fasterxml.jackson.annotation.JsonProperty");
        for (String fieldName : dataMap.keySet()) {
            Field field = getField(fieldName, dataMap.get(fieldName));
            field.addFieldAnno("@JsonProperty(\"" + fieldName + "\")");

            beanClass.addField(field);
            if(!"String".equals(field.getType())) {
                if(field.getType().startsWith("List<") && !beanClass.getImportClassList().contains("java.util.List")) {
                       beanClass.addImportClass("java.util.List");
                }
                if(isRoot) {
                    beanClass.addImportClass(packagePath + "." + CreatorUtil.getJavaClassName(rootClassName).toLowerCase() + ".*");
                }

                generateClassByJson(packagePath + (isRoot ? ("." + CreatorUtil.getJavaClassName(rootClassName).toLowerCase()) : ""),
                        CreatorUtil.getJavaClassName(fieldName), dataMap.get(fieldName), false);
            }
        }

        Map map = new HashMap();
        map.put("CLASS", beanClass);
        String content = VelocityUtil.produceTemplateContent("com/hframe/generator/vm/poByTemplate.vm", map);
        System.out.println(content);
        FileUtils.writeFile(beanClass.getFilePath(), content);
    }

    /**
     * 获取字段定义
     * @param fieldName
     * @param data
     * @param subElementName
     * @return
     */
    private static Field getField(String fieldName, Object data, String subElementName) {
        if(data instanceof Map) {
            return new Field(CreatorUtil.getJavaClassName(fieldName),CreatorUtil.getJavaVarName(fieldName));
        }else if(data instanceof List){
            if(subElementName != null) {
                return new Field("List<" + CreatorUtil.getJavaClassName(subElementName) + ">", CreatorUtil.getJavaVarName(subElementName) + "List");
            }else {
                return new Field("List<" + CreatorUtil.getJavaClassName(fieldName) + ">", CreatorUtil.getJavaVarName(fieldName) + "List");
            }

        }else {
            return new Field("String",CreatorUtil.getJavaVarName(fieldName));
        }
    }

    /**
     * 获取字段定义
     * @param fieldName
     * @param data
     * @return
     */
    private static Field getField(String fieldName, Object data) {
        if(data instanceof Map) {
            return new Field(CreatorUtil.getJavaClassName(fieldName),CreatorUtil.getJavaVarName(fieldName));
        }else if(data instanceof List){
            return new Field("List<" + CreatorUtil.getJavaClassName(fieldName) + ">", CreatorUtil.getJavaVarName(fieldName) + "List");
        }else {
            return new Field("String",CreatorUtil.getJavaVarName(fieldName));
        }
    }

    private static void generateClass(String packagePath, Map<String, Object> mergeMap) {


    }

    private static boolean checkElementIsArray(Element element) {
        List elements = element.elements();
        if(elements.size() > 1) {
            Element element1 = (Element) elements.get(0);
            Element element2 = (Element) elements.get(1);
            if(element1.getName().equals(element2.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 解析XML节点信息
     * @param element
     * @return
     */
    private static Object parseXmlNode(Element element) {
        if(checkElementIsArray(element)) {
            List result = new ArrayList();
            String xmlElementName = null;
            for (Object o : element.elements()) {
                Element subElement = (Element) o;
                xmlElementName = subElement.getName();//子元素名称
                Map<String, Object> fieldMap = (Map<String, Object>)parseXmlNode(subElement);
                mergeField(result, fieldMap);
            }
            result.add(xmlElementName);
            return result;
        }

        Map<String, Object> fieldMap = new LinkedHashMap<String, Object>();
        for (Object o : element.elements()) {
            Element subElement = (Element) o;
            fieldMap.put(subElement.getName(), parseXmlNode(subElement));
        }

        if(fieldMap.size() == 0) {
            return element.getTextTrim();
        }

        return fieldMap;
    }


    /**
     * 解析Json节点信息
     * @param jsonNode
     * @return
     */
    public static Object parseJsonNode(JsonNode jsonNode) {

        if(jsonNode.isArray()) {
            List result = new ArrayList();
            for (JsonNode subNode : jsonNode) {
                Map<String, Object> fieldMap = (Map<String, Object>)parseJsonNode(subNode);
                mergeField(result, fieldMap);
            }
            return result;
        }

        Map<String, Object> fieldMap = new LinkedHashMap<String, Object>();
        Iterator<Map.Entry<String, JsonNode>> fields = jsonNode.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> field = fields.next();
            fieldMap.put(field.getKey(), parseJsonNode(field.getValue()));
        }

        if(fieldMap.size() == 0) {
            return jsonNode.asText();
        }
        return fieldMap;
    }

    /**
     * 属性合并
     * @param result
     * @param fieldMap
     */
    private static void mergeField(List result, Map<String, Object> fieldMap) {
        if(result == null || result.size() == 0) {
            result.add(fieldMap);
        }else {
            ((Map<String, Object>) result.get(0)).putAll(fieldMap);
        }
    }


    public static void main(String[] args) throws IOException {
        String rootClassPath = Thread.currentThread().getContextClassLoader ().getResource("").getPath();
//        String jsonString = FileUtils.readFile(rootClassPath + "test.json");
//        generateByJson("com.wechat.bean.request","Menu",jsonString);

        String xmlString = FileUtils.readFile(rootClassPath + "test.xml");
        generateByXml("com.wechat.bean.request","Persons","persons",xmlString);

    }
}
