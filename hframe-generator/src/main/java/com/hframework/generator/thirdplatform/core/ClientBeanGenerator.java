package com.hframework.generator.thirdplatform.core;

import com.hframe.common.util.*;
import com.hframe.common.util.message.VelocityUtil;
import com.hframe.common.util.message.XmlUtils;
import com.hframe.generator.base.BeanGeneratorUtil;
import com.hframe.generator.bean.*;
import com.hframe.generator.bean.Class;
import com.hframe.generator.util.CreatorUtil;
import com.hframework.generator.thirdplatform.bean.Descriptor;
import com.hframework.generator.thirdplatform.bean.GeneratorConfig;
import com.hframework.generator.thirdplatform.bean.InterfaceExample;
import com.hframework.generator.thirdplatform.bean.descriptor.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zqh on 2016/4/16.
 */
public class ClientBeanGenerator extends AbstractGenerator implements Generator<GeneratorConfig,Descriptor>{

    private RequestConfig requestConfig;
    private ResponseConfig responseConfig;


    @Override
    protected boolean generateInternal(GeneratorConfig generatorConfig, Descriptor descriptor) {
        requestConfig = descriptor.getGlobal().getRequestConfig();
        responseConfig = descriptor.getGlobal().getResponseConfig();

        com.hframe.generator.bean.Class beanClass = new com.hframe.generator.bean.Class();
        beanClass.setSrcFilePath(javaRootPath);
        beanClass.setClassPackage(javaPackage);
        beanClass.setClassName(CreatorUtil.getJavaClassName(platformName) + "Client");


        beanClass.addImportClass("java.util.HashMap");
        beanClass.addImportClass("com.hframe.common.util.protocol.HttpClient");
        beanClass.addImportClass("java.text.MessageFormat");
        beanClass.addImportClass("com.hframe.common.util.message.*");
        beanClass.addImportClass(javaPackage + ".bean.*");
        beanClass.addImportClass("com.hframe.common.util.FileUtils");


        List<Interface> interface1List = descriptor.getInterfaces().getInterface1List();
        for (Interface anInterface : interface1List) {

            Method method = new Method();
            method.setName(anInterface.getName());
            method.setModifier("public static");
            method.setExceptionStr(" throws Exception");

            method.addCodeLn(getFormatUrl(method, anInterface));

            String requestBeanName = StringUtils.isNotBlank(anInterface.getRequest().getBeanName()) ? anInterface.getRequest().getBeanName() : "RequestData";
            String responseBeanName = StringUtils.isNotBlank(anInterface.getResponse().getBeanName()) ? anInterface.getResponse().getBeanName() : "ResponseData";
            responseBeanName = (!"xml".equals(anInterface.getResponse().getMessage()) && !"json".equals(anInterface.getResponse().getMessage()) ) ? "String" : responseBeanName;
            method.setReturnType(responseBeanName);
            //生成request请求对象
            if(StringUtils.isNotBlank(anInterface.getRequest().getMessage())) {
                method.addParameter(new Field(requestBeanName, "requestData"));
                createRequestBean(anInterface, requestBeanName);
            }

            //生成response请求对象
            if(StringUtils.isNotBlank(anInterface.getResponse().getMessage())) {
                createResponseBean(anInterface,responseBeanName);
            }

            method.addCodeLn("String result;");
            method.addCodeLn("if(\"true\".equals(" + CreatorUtil.getJavaClassName(platformName) + "Config.getInstance().getTestModel())) {");
            method.addCodeLn("   result = FileUtils.readFile(Thread.currentThread().getContextClassLoader().getResource(");
            method.addCodeLn("          \"" + resourceFolder + "/" + platformName + "/" + anInterface.getName() + ".response" + "\").getPath());");
            method.addCodeLn("}else {");


            String httpCode = "   result = ";
            if("json".equals(anInterface.getRequest().getMessage())) {
                httpCode += "HttpClient.doJsonPost(url,requestData.convert());";
            }else if("xml".equals(anInterface.getRequest().getMessage())) {
                httpCode += "HttpClient.doXmlPost(url,requestData.convert());";
            }else {
                httpCode += "HttpClient.doPost(url,new HashMap());";
            }
            method.addCodeLn(httpCode);
            method.addCodeLn("}");

            if("json".equals(anInterface.getResponse().getMessage())) {
                method.addCodeLn(responseBeanName + " responseData = JsonUtils.readValue(result," + responseBeanName + ".class);");
            }else if("xml".equals(anInterface.getResponse().getMessage())) {
                method.addCodeLn(responseBeanName + " responseData = XmlUtils.readValue(result," + responseBeanName + ".class);");
            }

            if("json".equals(anInterface.getResponse().getMessage()) || "xml".equals(anInterface.getResponse().getMessage())) {
                method.addCodeLn("return responseData.convert();");
            }else {
                method.addCodeLn("return result;");
            }

            beanClass.addMethod(method);
        }

        Map map = new HashMap();
        map.put("CLASS", beanClass);
        String content = VelocityUtil.produceTemplateContent("com/hframe/generator/vm/bean.vm", map);
        System.out.println(content);
        FileUtils.writeFile(beanClass.getFilePath(), content);

        return false;
    }

    private void createRequestBean(Interface anInterface, String requestDataName) {
        List<Node> ruleNodeList = requestConfig.getPublicNodes().getNodeList();
        try {
            if(StringUtils.isNotBlank(anInterface.getTemplate())) {
                InterfaceExample interfaceExample =
                        XmlUtils.readValueFromFile("/thirdplatform/" + anInterface.getTemplate(), InterfaceExample.class);
                String requestMessage = interfaceExample.getRequestMessage();
                if(StringUtils.isNotBlank(requestMessage)) {
                    if("json".equals(anInterface.getRequest().getMessage())) {
                        BeanGeneratorUtil.generateByJson(javaPackage + ".bean", requestDataName, requestMessage,ruleNodeList);
                    }else if("xml".equals(anInterface.getRequest().getMessage())) {
                        ClientBeanGenerateDescriptor descriptor = new ClientBeanGenerateDescriptor();
                        descriptor.setJavaRootPath(javaRootPath);
                        descriptor.setJavaPackage(javaPackage + ".bean");
                        descriptor.setRuleNodeList(ruleNodeList);
                        BeanGeneratorUtil.generateByXml(descriptor, requestMessage, requestDataName);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createResponseBean(Interface anInterface, String responseBeanName) {

        List<Node> ruleNodeList = responseConfig.getPublicNodes().getNodeList();
        try {
            if(StringUtils.isNotBlank(anInterface.getTemplate())) {
                InterfaceExample interfaceExample =
                        XmlUtils.readValueFromFile("/thirdplatform/" + anInterface.getTemplate(), InterfaceExample.class);
                String responseMessage = interfaceExample.getResponseMessage();
                if(StringUtils.isNotBlank(responseMessage)) {
                    if("json".equals(anInterface.getResponse().getMessage())) {
                        BeanGeneratorUtil.generateByJson(javaPackage + ".bean", responseBeanName, responseMessage, ruleNodeList);
                    }else if("xml".equals(anInterface.getResponse().getMessage())) {
                        ClientBeanGenerateDescriptor descriptor = new ClientBeanGenerateDescriptor();
                        descriptor.setJavaRootPath(javaRootPath);
                        descriptor.setJavaPackage(javaPackage + ".bean");
                        descriptor.setRuleNodeList(ruleNodeList);
                        descriptor.setRequestBean(false);
                        BeanGeneratorUtil.generateByXml(descriptor, responseMessage, responseBeanName);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String getFormatUrl(Method method, Interface anInterface) {
        String url = "String url = MessageFormat.format(" +
                CreatorUtil.getJavaClassName(platformName) + "Config.getInstance().get"
                + ResourceWrapper.JavaUtil.getJavaClassName(method.getName()) + "()";
        List<Parameter> parameterList = anInterface.getRequest().getParameters().getParameterList();
        for (Parameter parameter : parameterList) {
            if(!"false".equals(parameter.getVisiable())) {
                method.addParameter(new Field(parameter.getType(), parameter.getName()));
                url += "," + parameter.getName();
            }else {
                for (Method method1 : helperClass.getMethodList()) {
                    if(method1.getName().equals(parameter.getRuleId())) {
                        url += "," + getMethodInvokeCode(method1);
                    }
                }
            }
        }

        if(parameterList.size() == 0) {
            url += ",null";
        }

        url += ");";
        return url;
    }

    private String getMethodInvokeCode(Method method1) {

        String code = CreatorUtil.getJavaClassName(platformName) + "Helper."
                + method1.getName() + "(" ;
        for (Field field : method1.getParameterList()) {
            if(!"String".equals(field.getType())) {
            }else {
                code += "String.valueOf("+field.getName() + "),";
            }

        }
        code = code.substring(0, code.length()-1) +  ")";

        return code;
    }

    public class ClientBeanGenerateDescriptor extends AbstractGenerateDescriptor implements GenerateDescriptor {
        private List<Node> ruleNodeList = null;
        private boolean requestBean = true;

        public void execute(com.hframe.generator.bean.Class beanClass, XmlNode xmlNode) {
            Field field = new Field("boolean", "converted");
            field.setSetGetMethod(false);
            field.addFieldAnno("@XStreamOmitField");
            beanClass.addImportClass("com.thoughtworks.xstream.annotations.XStreamOmitField");
            beanClass.addField(field);
            beanClass.addImportClass("com.hframe.common.util.message.*");

            Method method = new Method();
            method.setName("convert");
            method.setExceptionStr(" throws Exception");

            if(ruleNodeList != null && ruleNodeList.size() > 0) {
                method.addCodeLn("if(!converted) {");
                method.addCodeLn("   String beforeInfo = XmlUtils.writeValueAsString(this);");
                method.addCodeLn("   System.out.println(beforeInfo);");

                method.addCodeLn("   converted = true;");
                method.setReturnType(beanClass.getClassName());

                List<XmlNode> childrenXmlNode = xmlNode.getChildrenXmlNode();
                if(childrenXmlNode != null) {
                    for (XmlNode childXmlNode : childrenXmlNode) {
                        if (childXmlNode.getChildrenXmlNode().size() == 0 &&
                                (childXmlNode.getAttrMap() == null || childXmlNode.getAttrMap().size() == 0)) {
                            if (childXmlNode.isSingleton()) {
                                Node node = matchNode(childXmlNode, ruleNodeList);
                                if (node != null) {
                                    String value = node.getValue();
                                    if(StringUtils.isNotBlank(value)) {
                                        String[] strings = RegexUtils.find(value, "[\\#\\$]\\{[ a-zA-Z:0-9_]+\\}");
                                        if(strings != null && strings.length > 0) {
                                            String paramName = com.hframe.generator.util.CreatorUtil.getJavaVarName(strings[0].substring(2, strings[0].length() - 1));
                                            if(strings[0].startsWith("#")) {
                                                method.addCodeLn("   " + CreatorUtil.getJavaVarName(childXmlNode.getNodeName()) +
                                                        "=" + com.hframe.generator.util.CreatorUtil.getJavaClassName(platformName) + "Config.getInstance().get"
                                                        + ResourceWrapper.JavaUtil.getJavaClassName(paramName) + "();");

                                            }
                                        }
                                    }
                                    String ruleId = node.getRuleId();
                                    if(StringUtils.isNotBlank(ruleId)) {
                                        method.addCodeLn("   " + CreatorUtil.getJavaVarName(childXmlNode.getNodeName()) +
                                                "=" + com.hframe.generator.util.CreatorUtil.getJavaClassName(platformName) + "Helper."
                                                + ruleId + "(" + whetherContainThis(ruleId) + ");");
                                    }
                                    modifyMethod(beanClass, CreatorUtil.getJavaVarName(childXmlNode.getNodeName()));
                                }
                            }
                        }
                    }
                }

                method.addCodeLn("   String afterInfo = XmlUtils.writeValueAsString(this);");
                method.addCodeLn("   System.out.println(afterInfo);");
                beanClass.addImportClass(beanClass.getClassPackage().substring(0, beanClass.getClassPackage().lastIndexOf(".")) + ".*");
            }

            method.addCodeLn("}");
            method.addCodeLn("return this;");
            beanClass.addMethod(method);
        }

        private void modifyMethod(Class beanClass, String javaVarName) {
            List<Field> fieldList = beanClass.getFieldList();
            for (Field field : fieldList) {
                if(javaVarName.equals(field.getName())) {
                    field.setSetGetMethod(false);
                    Method getMethod = MethodHelper.getGetMethod(field);
                    Method setMethod = MethodHelper.getSetMethod(field);
                    beanClass.addMethod(getMethod);
                    beanClass.addMethod(setMethod);
                    if(requestBean) {
                        setMethod.setModifier("private");
                    }else {
                        getMethod.setModifier("private");
                    }


                }
            }
        }

        private String whetherContainThis(String ruleId) {
            for (Method method1 : helperClass.getMethodList()) {
                if(method1.getName().equals(ruleId)) {
                    for (Field field : method1.getParameterList()) {
                        if("Object".equals(field.getType())) {
                            return "this";
                        }
                    }
                }
            }
            return "";
        }

        private  Node matchNode(XmlNode childXmlNode, List<Node> ruleNodeList) {
            for (Node node : ruleNodeList) {
//                System.out.println(node.getPath() + "--" + childXmlNode.getNodeCode());
                if(PathMatcherUtils.matches(node.getPath(), childXmlNode.getNodeCode())) {
                    return node;
                }
            }
            return null;
        }

        public List<Node> getRuleNodeList() {
            return ruleNodeList;
        }

        public void setRuleNodeList(List<Node> ruleNodeList) {
            this.ruleNodeList = ruleNodeList;
        }

        public boolean isRequestBean() {
            return requestBean;
        }

        public void setRequestBean(boolean requestBean) {
            this.requestBean = requestBean;
        }
    }



}
