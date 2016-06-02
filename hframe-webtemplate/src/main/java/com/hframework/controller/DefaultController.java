package com.hframework.controller;

import com.alibaba.fastjson.JSONArray;
import com.hframework.beans.class0.Class;
import com.hframework.beans.controller.ResultData;
import com.hframework.common.frame.cache.PropertyConfigurerUtils;
import com.hframework.common.util.ReflectUtils;
import com.hframework.common.util.StringUtils;
import com.hframework.web.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * User: zhangqh6
 * Date: 2016/5/11 0:16:16
 */
@Controller
public class DefaultController {
    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

    /**
     * 页面跳转
     * @return
     * @throws Throwable
     */
    @RequestMapping(value = "/{module}/{page}.html")
    public ModelAndView gotoPage(@PathVariable("module") String module,@PathVariable("page") String pageCode, HttpServletRequest request, HttpServletResponse response) throws Throwable {
        ModelAndView mav = new ModelAndView();

        PageDescriptor pageInfo = WebContext.get().getPageInfo(module, pageCode);

        Map<String, ElementDescriptor> elements = pageInfo.getElements();
        for (String key : elements.keySet()) {
            mav.addObject(key, elements.get(key).getId());
        }
        Map<String, ContainerDescriptor> containers = pageInfo.getContainers();

        Map<String, ComponentDescriptor> components = pageInfo.getComponents();
        for (ComponentDescriptor componentDescriptor : components.values()) {
            if(componentDescriptor.getDataSetDescriptor() == null) {
                logger.warn("component {} is not set data set",componentDescriptor.getId());
                continue;
            }
            String moduleCode = componentDescriptor.getDataSetDescriptor().getDataSet().getModule();
            String dataSetCode = componentDescriptor.getDataSetDescriptor().getDataSet().getCode();
            String action = componentDescriptor.getComponent().getType();

            com.hframework.beans.class0.Class defPoClass = CreatorUtil.getDefPoClass("",
                    WebContext.get().getProgram().getCode(), moduleCode, dataSetCode);
            com.hframework.beans.class0.Class defControllerClass = CreatorUtil.getDefControllerClass("",
                    WebContext.get().getProgram().getCode(), moduleCode, dataSetCode);

            Object po= java.lang.Class.forName(defPoClass.getClassPath()).newInstance();
            Object controller= java.lang.Class.forName(defControllerClass.getClassPath()).newInstance();
            ResultData resultData = (ResultData) ReflectUtils.invokeMethod(controller,
                    action, new java.lang.Class[]{java.lang.Class.forName(defPoClass.getClassPath())}, new Object[]{po});
            resetResultMessage(resultData, WebContext.get().getProgram().getCode(), moduleCode, dataSetCode, action);
            if(resultData.isSuccess()) {
                String json = componentDescriptor.getJson(resultData);
                mav.addObject(componentDescriptor.getId(), JSONArray.parse(json));
            }
        }

//        mav.addObject("pageTemplate", "default.vm");
        mav.addObject("staticResourcePath", "/static");
        mav.setViewName(pageInfo.getPageTemplate().getId());
        return mav;

    }

    private void resetResultMessage(ResultData resultData, String programCode, String moduleCode, String dataSetCode, String action) {
        String resourceKey = programCode + "." + moduleCode + "." + dataSetCode + "." + action + resultData.getResultCode();
        resourceKey = programCode + "." + moduleCode + "." + dataSetCode + "." + action + resultData.getResultCode();
        resourceKey = action + resultData.getResultCode();
        resourceKey = resultData.getResultCode();
        resultData.setResultMessage("TODO");
    }

    public static class CreatorUtil {

        public static String getJavaClassName(String tableName) {
            String returnName = "";
            tableName = tableName.toLowerCase();
            String[] parts = tableName.split("[_]+");
            for (String part : parts) {
                if (!"".equals(part)) {
                    returnName += part.substring(0, 1).toUpperCase()
                            + part.substring(1);
                }
            }
            return returnName;
        }

        public static com.hframework.beans.class0.Class getDefPoExampleClass(String companyName,
                                                                             String projectName,String moduleName, String tableName) throws Exception {
            if(StringUtils.isBlank(tableName)) {
                throw new Exception("表名称为不能为空！");
            }

            companyName = StringUtils.isBlank(companyName)?"":"."+(companyName);
            projectName = StringUtils.isBlank(projectName)?"":"."+(projectName);
            moduleName = StringUtils.isBlank(moduleName)?"":"."+(moduleName);


            Class class1 = new Class();
            class1.setSrcFilePath(CreatorUtil.getSrcFilePath(companyName, projectName));
            class1.setClassPackage(CreatorUtil.getPoClassPackage(
                    companyName, projectName, moduleName, tableName));
            class1.setClassName(CreatorUtil.getJavaClassName(tableName) + "_Example");
            return class1;
        }
        public static Class getDefServiceClass(String companyName,
                                               String projectName, String moduleName,String tableName) throws Exception {
            if(StringUtils.isBlank(tableName)) {
                throw new Exception("表名称为不能为空！");
            }

            companyName = StringUtils.isBlank(companyName)?"":"."+(companyName);
            projectName = StringUtils.isBlank(projectName)?"":"."+(projectName);
            moduleName = StringUtils.isBlank(moduleName)?"":"."+(moduleName);

            Class class1 = new Class();
            class1.setSrcFilePath(CreatorUtil.getSrcFilePath(companyName, projectName));
            class1.setClassPackage(CreatorUtil.getServiceClassPackage(
                    companyName, projectName, moduleName, tableName));
            class1.setClassName("I" + CreatorUtil.getJavaClassName(tableName) + "SV");
            return class1;
        }

        public static String getServiceClassPackage(String companyName,
                                                    String projectName,String moduleName, String tableName) throws Exception {

            return PropertyConfigurerUtils.getProperty(
                    "service_class_package",
                    companyName.toLowerCase(),
                    projectName.toLowerCase(),
                    moduleName.toLowerCase());
        }

        public static Class getDefServiceImplClass(String companyName,
                                                   String projectName, String moduleName,String tableName) throws Exception {
            if(StringUtils.isBlank(tableName)) {
                throw new Exception("表名称为不能为空！");
            }

            companyName = StringUtils.isBlank(companyName)?"":"."+(companyName);
            projectName = StringUtils.isBlank(projectName)?"":"."+(projectName);
            moduleName = StringUtils.isBlank(moduleName)?"":"."+(moduleName);

            Class class1 = new Class();
            class1.setSrcFilePath(CreatorUtil.getSrcFilePath(companyName, projectName));
            class1.setClassPackage(CreatorUtil.getServiceImplClassPackage(
                    companyName, projectName, moduleName, tableName));
            class1.setClassName(CreatorUtil.getJavaClassName(tableName) + "SVImpl");
            return class1;
        }
        public static Class getDefControllerClass(String companyName,
                                                  String projectName, String moduleName,String tableName) throws Exception {

            if(StringUtils.isBlank(tableName)) {
                throw new Exception("表名称为不能为空！");
            }

            companyName = StringUtils.isBlank(companyName)?"":"."+(companyName);
            projectName = StringUtils.isBlank(projectName)?"":"."+(projectName);
            moduleName = StringUtils.isBlank(moduleName)?"":"."+(moduleName);

            Class class1 = new Class();
            class1.setSrcFilePath(CreatorUtil.getSrcFilePath(companyName, projectName));
            class1.setClassPackage(CreatorUtil.getActionClassPackage(
                    companyName, projectName, moduleName, tableName));
            class1.setClassName(CreatorUtil.getJavaClassName(tableName) + "Controller");
            return class1;
        }

        public static String getActionClassPackage(String companyName,
                                                   String projectName,String moduleName,String tableName) throws Exception {

            return PropertyConfigurerUtils.getProperty(
                    "action_class_package",
                    companyName.toLowerCase(),
                    projectName.toLowerCase(),
                    moduleName.toLowerCase());
        }
        public static String getServiceImplClassPackage(String companyName,
                                                        String projectName,String moduleName, String tableName) throws Exception {

            return PropertyConfigurerUtils.getProperty(
                    "serviceimpl_class_package",
                    companyName.toLowerCase(),
                    projectName.toLowerCase(),
                    moduleName.toLowerCase());
        }

        public static String getJavaVarName(String tableName) {

            String returnName="";
            tableName=tableName.toLowerCase();
            String[] parts=tableName.split("[_]+");
            for (String part : parts) {
                if(!"".equals(part)){
                    returnName+=part.substring(0,1).toUpperCase()+part.substring(1);
                }
            }
            return returnName.substring(0,1).toLowerCase()+returnName.substring(1);
        }


        /**
         * @param companyName
         * @param projectName
         * @return 获取SQL文件在项目中存放的路径即名称
         * @throws Exception
         */
        public static String getSrcFilePath(String companyName,
                                            String projectName) throws Exception {

            if("".equals(companyName) || companyName == null){
                companyName="zqh";
            }


            if(StringUtils.isBlank(projectName)) {
                throw new Exception("项目名称为不能为空！");
            }

            return PropertyConfigurerUtils.getProperty("project_src_file_path");
        }



        /**
         * @param companyName
         * @param projectName
         * @param tableName
         * @return 获取SQL文件在项目中存放的路径即名称
         * @throws Exception
         */
        public static String getPoClassPackage(String companyName,
                                               String projectName,String moduleName,String tableName) throws Exception {
            return PropertyConfigurerUtils.getProperty(
                    "po_class_package",
                    companyName.toLowerCase(),
                    projectName.toLowerCase(),
                    moduleName.toLowerCase(),
                    getJavaClassName(tableName.toLowerCase()));
        }


        public static Class getDefPoClass(String companyName,
                                          String projectName, String moduleName,String tableName) throws Exception {
            if(StringUtils.isBlank(tableName)) {
                throw new Exception("表名称为不能为空！");
            }

            companyName = StringUtils.isBlank(companyName)?"":"."+(companyName);
            projectName = StringUtils.isBlank(projectName)?"":"."+(projectName);
            moduleName = StringUtils.isBlank(moduleName)?"":"."+(moduleName);


            Class class1 = new Class();
            class1.setSrcFilePath(CreatorUtil.getSrcFilePath(companyName, projectName));
            class1.setClassPackage(CreatorUtil.getPoClassPackage(
                    companyName, projectName, moduleName,tableName));
            class1.setClassName(CreatorUtil.getJavaClassName(tableName) + "");
            return class1;
        }
    }
}
