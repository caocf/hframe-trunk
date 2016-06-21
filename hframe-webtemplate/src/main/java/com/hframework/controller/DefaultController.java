package com.hframework.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hframework.base.bean.KVBean;
import com.hframework.base.service.CommonDataService;
import com.hframework.beans.class0.Class;
import com.hframework.beans.controller.Pagination;
import com.hframework.beans.controller.ResultCode;
import com.hframework.beans.controller.ResultData;
import com.hframework.common.frame.ServiceFactory;
import com.hframework.common.frame.cache.PropertyConfigurerUtils;
import com.hframework.common.util.ReflectUtils;
import com.hframework.common.util.StringUtils;
import com.hframework.web.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor;
import org.springframework.web.servlet.mvc.method.annotation.ServletRequestDataBinderFactory;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: zhangqh6
 * Date: 2016/5/11 0:16:16
 */
@Controller
public class DefaultController {
    private static final Logger logger = LoggerFactory.getLogger(DefaultController.class);

    @Resource
    private CommonDataService commonDataService;

    private ModelAttributeSetter modelAttributeSetter = new ModelAttributeSetter();


    /**
     * 字典查询
     * @param dataCode
     * @param dataCondition
     * @return
     */
    @RequestMapping(value = "/dictionary.json")
    @ResponseBody
    public ResultData dictionary(@ModelAttribute("dataCode") String dataCode ,
                                 @ModelAttribute("dataCondition") final String dataCondition){
        logger.debug("request : {}", dataCode, dataCondition);
        try{

            final String[] split = dataCode.split("\\.");
            Map<String, String> dicInfo = new HashMap<String, String>(){{
                put("tableName", split[0]);
                put("keyColumn", split[1]);
                put("valueColumn", split[2]);
//                put("extColumn", dictionary.getExtColumn());
                put("condition", dataCondition);
            }};
            List<KVBean> kvBeans = commonDataService.selectDynamicTableDataList(dicInfo);

            return ResultData.success(kvBeans);
        }catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
    }

    public ResultData invokeMethod(HttpServletRequest request, Object controller, String action, java.lang.Class[] classes, Object[] objects) throws InvocationTargetException {

        Method declaredMethod = ReflectUtils.getDeclaredMethod(controller, action,classes);
        Object o = modelAttributeSetter.resolveArgument(request, new MethodParameter(declaredMethod, 0));
        objects[0] = o;
        ResultData resultData = (ResultData) ReflectUtils.invokeMethod(controller,action,classes,objects);
        return resultData;
    }

    /**
     * 页面跳转
     * @return
     * @throws Throwable
     */
    @RequestMapping(value = "/{module}/{page}.html")
    public ModelAndView gotoPage(@PathVariable("module") String module,@PathVariable("page") String pageCode,
                                 @ModelAttribute("component")  String componentId,
                                 @ModelAttribute("pagination")  Pagination pagination,
                                 @ModelAttribute("isPop")  String isPop,
                                 HttpServletRequest request, HttpServletResponse response) throws Throwable {
        System.out.println("==> " + request.getQueryString());
        ModelAndView mav = new ModelAndView();

        PageDescriptor pageInfo = WebContext.get().getPageInfo(module, pageCode);

        Map<String, ElementDescriptor> elements = pageInfo.getElements();
        for (String key : elements.keySet()) {
            mav.addObject(key, elements.get(key).getId());
        }
        Map<String, ContainerDescriptor> containers = pageInfo.getContainers();

        Map<String, ComponentDescriptor> components = pageInfo.getComponents();
        for (ComponentDescriptor componentDescriptor : components.values()) {
            if(StringUtils.isBlank(componentId) || componentId.equals(componentDescriptor.getId())) {
                if(componentDescriptor.getDataSetDescriptor() == null) {
                    logger.warn("component {} is not set data set",componentDescriptor.getId());
                    continue;
                }
                String moduleCode = componentDescriptor.getDataSetDescriptor().getDataSet().getModule();
                String dataSetCode = componentDescriptor.getDataSetDescriptor().getDataSet().getCode();
                String type = componentDescriptor.getComponent().getType();
                String action = null;
                 if("eForm".equals(type) || "dForm".equals(type)) {
                    action = "detail";
                }else if(!"cForm".equals(type) && !"qForm".equals(type)) {
                     action = type;
                 }

                if(StringUtils.isNotBlank(action)) {
                    Class defPoClass = CreatorUtil.getDefPoClass("",
                            WebContext.get().getProgram().getCode(), moduleCode, dataSetCode);
                    Class defPoExampleClass = CreatorUtil.getDefPoExampleClass("",
                            WebContext.get().getProgram().getCode(), moduleCode, dataSetCode);
                    Class defControllerClass = CreatorUtil.getDefControllerClass("",
                            WebContext.get().getProgram().getCode(), moduleCode, dataSetCode);

                    if(pagination.getPageNo() == 0) {
                        pagination.setPageNo(1);
                    }
                    if(pagination.getPageSize() == 0) {
                        pagination.setPageSize(5);
                    }
                    Object po= java.lang.Class.forName(defPoClass.getClassPath()).newInstance();
                    Object poExample= java.lang.Class.forName(defPoExampleClass.getClassPath()).newInstance();
                    Object controller= ServiceFactory.getService(defControllerClass.getClassName().substring(0, 1).toLowerCase() + defControllerClass.getClassName().substring(1));
                    ResultData resultData = null;
                    if("detail".equals(action)) {
                        resultData = invokeMethod(request,controller,action,
                                new java.lang.Class[]{java.lang.Class.forName(defPoClass.getClassPath())},
                                new Object[]{po});
                    }else {
                        resultData = invokeMethod(request,controller,action,
                                new java.lang.Class[]{java.lang.Class.forName(defPoClass.getClassPath()),
                                        java.lang.Class.forName(defPoExampleClass.getClassPath()), Pagination.class},
                                new Object[]{po,poExample, pagination});
//                        resultData = (ResultData) ReflectUtils.invokeMethod(controller,action,
//                                new java.lang.Class[]{java.lang.Class.forName(defPoClass.getClassPath()),
//                                        java.lang.Class.forName(defPoExampleClass.getClassPath()), Pagination.class},
//                                new Object[]{po,poExample, pagination});
                    }

                    resetResultMessage(resultData, WebContext.get().getProgram().getCode(), moduleCode, dataSetCode, action);
                    if(resultData.isSuccess()) {
                        JSONObject jsonObject = componentDescriptor.getJson(resultData);
                        jsonObject.put("module",module);
                        jsonObject.put("page",pageCode);
                        jsonObject.put("param","");
                        jsonObject.put("component",componentDescriptor.getId());
                        System.out.println(jsonObject.toJSONString());
                        mav.addObject(componentDescriptor.getId(), jsonObject);
                    }
                }else {
                    JSONObject jsonObject = componentDescriptor.getJson();
                    jsonObject.put("module",module);
                    jsonObject.put("page",pageCode);
                    jsonObject.put("param","");
                    jsonObject.put("component",componentDescriptor.getId());
                    System.out.println(jsonObject.toJSONString());
                    mav.addObject(componentDescriptor.getId(), jsonObject);
                }
            }
        }

        mav.addObject("isPop","true".equals(isPop)? true : false);
        mav.addObject("staticResourcePath", "/static");
        if(StringUtils.isNotBlank(componentId)) {
            mav.setViewName("/component/queryList");

        }else {
            mav.setViewName(pageInfo.getPageTemplate().getId());
        }

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

        public static Class getDefPoExampleClass(String companyName,
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

    public  class ModelAttributeSetter{
        private ServletModelAttributeMethodProcessor processor;

        private ModelAndViewContainer mavContainer;

        private WebDataBinderFactory binderFactory;

        public ModelAttributeSetter() {
            init();
        }

        public void init() {
            this.processor = new ServletModelAttributeMethodProcessor(false);

            ConfigurableWebBindingInitializer initializer = new ConfigurableWebBindingInitializer();
            initializer.setConversionService(new DefaultConversionService());

            this.binderFactory = new ServletRequestDataBinderFactory(null, initializer);
            this.mavContainer = new ModelAndViewContainer();
        }

        public Object resolveArgument(HttpServletRequest request, MethodParameter methodParameter) {
            NativeWebRequest webRequest = new ServletWebRequest(request);

            try {
                return this.processor.resolveArgument(
                        methodParameter, this.mavContainer, webRequest, this.binderFactory);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
