package com.hframework.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.hframe.domain.model.HfmdEnum;
import com.hframe.domain.model.HfmdEnumClass;
import com.hframe.domain.model.HfmdEnumClass_Example;
import com.hframe.domain.model.HfmdEnum_Example;
import com.hframe.service.interfaces.IHfmdEnumClassSV;
import com.hframe.service.interfaces.IHfmdEnumSV;
import com.hframework.base.bean.KVBean;
import com.hframework.base.service.CommonDataService;
import com.hframework.beans.class0.Class;
import com.hframework.beans.controller.Pagination;
import com.hframework.beans.controller.ResultCode;
import com.hframework.beans.controller.ResultData;
import com.hframework.common.ext.CollectionUtils;
import com.hframework.common.ext.Grouper;
import com.hframework.common.ext.Mapping;
import com.hframework.common.frame.ServiceFactory;
import com.hframework.common.util.*;
import com.hframework.common.util.BeanUtils;
import com.hframework.web.CreatorUtil;
import com.hframework.web.bean.*;
import com.hframework.web.config.bean.DataSetHelper;
import com.hframework.web.config.bean.Mapper;
import com.hframework.web.config.bean.datasethelper.Mappings;
import com.hframework.web.config.bean.module.Component;
import org.apache.commons.beanutils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpInputMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor;
import org.springframework.web.servlet.mvc.method.annotation.ServletRequestDataBinderFactory;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.*;

/**
 * User: zhangqh6
 * Date: 2016/5/11 0:16:16
 */
@Controller
public class DefaultController {
    private static final Logger logger = LoggerFactory.getLogger(DefaultController.class);

    private static Map<String, HandlerMethod> urlMapping = new HashMap<String, HandlerMethod>();
    @Resource
    private CommonDataService commonDataService;

    private ModelAttributeSetter modelAttributeSetter = new ModelAttributeSetter();

    @Resource
    private IHfmdEnumClassSV iHfmdEnumClassSV;
    @Resource
    private IHfmdEnumSV iHfmdEnumSV;
    @Resource
    private ObjectMapper mvcObjectMapper;



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
            if(split.length < 3) {
                HfmdEnumClass_Example hfmdEnumClass_example = new HfmdEnumClass_Example();
                hfmdEnumClass_example.createCriteria().andHfmdEnumClassCodeEqualTo(dataCode);
                List<HfmdEnumClass> hfmdEnumClassListByExample = iHfmdEnumClassSV.getHfmdEnumClassListByExample(hfmdEnumClass_example);
                if(hfmdEnumClassListByExample != null && hfmdEnumClassListByExample.size() > 0) {
                    Long hfmdEnumClassId = hfmdEnumClassListByExample.get(0).getHfmdEnumClassId();
                    HfmdEnum_Example hfmdEnum_example = new HfmdEnum_Example();
                    hfmdEnum_example.createCriteria().andHfmdEnumClassIdEqualTo(String.valueOf(hfmdEnumClassId));
                    List<HfmdEnum> hfmdEnumList = iHfmdEnumSV.getHfmdEnumListByExample(hfmdEnum_example);
                    List<KVBean> kvBeans = CollectionUtils.from(hfmdEnumList, new Mapping<HfmdEnum, KVBean>() {
                        public KVBean from(HfmdEnum hfmdEnum) {
                            KVBean kvBean = new KVBean();
                            kvBean.setValue(hfmdEnum.getHfmdEnumValue());
                            kvBean.setText(hfmdEnum.getHfmdEnumText());
                            return kvBean;
                        }
                    });
                    return ResultData.success(kvBeans);
                }
                return ResultData.error(ResultCode.RECODE_IS_NOT_EXISTS);
            }else {
                Map<String, String> dicInfo = new HashMap<String, String>(){{
                    put("tableName", split[0]);
                    put("keyColumn", split[1]);
                    put("valueColumn", split[2]);
                    if(split.length > 3) {
                        put("extColumn", split[3]);

                    }
                    put("condition", dataCondition);
                }};
                List<KVBean> kvBeans = commonDataService.selectDynamicTableDataList(dicInfo);

                return ResultData.success(kvBeans);
            }
        }catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
    }

    /**
     * 获取某对象详情
     * @param dataCodes
     * @param dataValue
     * @return
     */
    @RequestMapping(value = "/queryOne.json")
    @ResponseBody
    public ResultData queryOne(@ModelAttribute("dataCode") String dataCodes ,final @ModelAttribute("dataValue") String dataValue){
        logger.debug("request : {}", dataCodes, dataValue);

        String dataCode = dataCodes;
        if(dataCodes.contains(";")) {
            dataCode = dataCodes.substring(dataCodes.lastIndexOf(";") + 1);
        }
        final String[] split = dataCode.split("\\.");



        try{
            Map<String, String> dicInfo = new HashMap<String, String>(){{
                put("tableName", split[0]);
                put("condition", split[1] + " = " + dataValue);
            }};
            Map<String, Object> stringObjectMap = commonDataService.selectDynamicTableDataOne(dicInfo);

            logger.debug("result json : {}", stringObjectMap);
            return ResultData.success(stringObjectMap);
        }catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
    }

    /**
     * 字典查询
     * @param dataCodes
     * @param dataCondition
     * @return
     */
    @RequestMapping(value = "/treeData.json")
    @ResponseBody
    public ResultData treeData(@ModelAttribute("dataCode") String dataCodes ,
                                 @ModelAttribute("dataCondition") final String dataCondition, @ModelAttribute("dataValue") String dataValue){
        logger.debug("request : {}", dataCodes, dataCondition);
        JSONObject treeData = new JSONObject();
        String dataDisplayValue = null;
        try{
            final String[] dataCodeArray = dataCodes.split(";");
            Map<String, KVBean> cache = new HashMap<String, KVBean>();
            for (String dataCode : dataCodeArray) {
                ResultData dictionary = dictionary(dataCode, dataCondition);
                List<KVBean> kvBeans = (List<KVBean>) dictionary.getData();
                cache.putAll(CollectionUtils.convert(kvBeans, new com.hframework.common.ext.Mapper<String, KVBean>() {
                    public <K> K getKey(KVBean kvBean) {
                        return (K) kvBean.getValue();
                    }
                }));
                if(treeData.isEmpty()) {
                    JSONArray jsonArray = new JSONArray();
                    if(kvBeans != null) {
                        for (KVBean kvBean : kvBeans) {
                            JSONObject recode = new JSONObject();
                            recode.put("code",kvBean.getValue());
                            recode.put("address",kvBean.getText());
                            jsonArray.add(recode);
                        }
                    }

                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("", jsonArray);
                    treeData.put("86", jsonObject);
                }else {
                    Map<String, List<KVBean>> group = CollectionUtils.group(kvBeans, new Grouper<String, KVBean>() {
                        public <K> K groupKey(KVBean kvBean) {
                            return (K) kvBean.getExtInfo();
                        }
                    });
                    for (Map.Entry<String, List<KVBean>> entry : group.entrySet()) {
                        Map<String, String> recode = new HashMap<String, String>();
                        if(StringUtils.isBlank(entry.getKey())) {//存在垃圾数据时
                            continue;
                        }
                        for (KVBean kvBean : entry.getValue()) {
                            if(kvBean.getValue().equals(dataValue)) {
                                dataDisplayValue = kvBean.getText();
                                String parentId = kvBean.getExtInfo();
                                while (cache.containsKey(parentId)) {
                                    dataDisplayValue = cache.get(parentId).getText() + "/" + dataDisplayValue;
                                    parentId = cache.get(parentId).getExtInfo();
                                }
                            }
                            recode.put(kvBean.getValue(), kvBean.getText());
                        }
                        treeData.put(entry.getKey(), recode);
                    }
                }
            }
            logger.debug("result json : {}", treeData.toJSONString());
            return ResultData.success().add("data", treeData).add("disValue", dataDisplayValue);
        }catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
    }
    /**
     * 数据保存
     * @return
     */
    @RequestMapping(value = "/logout.html")
    @ResponseBody
    public ModelAndView logout(HttpServletRequest request,
                            HttpServletResponse response){
        ModelAndView mav = new ModelAndView();
        request.getSession().setAttribute("context", null);
        mav.addObject("staticResourcePath", "/static");
        mav.setViewName("/login");
        return mav;
    }
    /**
     * 数据保存
     * @return
     */
    @RequestMapping(value = "/login.json")
    @ResponseBody
    public ResultData login(HttpServletRequest request,
                               HttpServletResponse response){
        WebContext.clear();
        Component login = WebContext.get().getDefaultComponentMap().get("login");
        Mapper mapper = WebContext.get().getMapper(login.getDataSet(), login.getId());
        HashMap<String , String> inputs = new HashMap<String, String>();
        if(mapper != null) {
            List<com.hframework.web.config.bean.mapper.Mapping> mappingList = mapper.getBaseMapper().getMappingList();
            for (com.hframework.web.config.bean.mapper.Mapping mapping : mappingList) {
                String value = request.getParameter(mapping.getId());
                inputs.put(mapping.getValue(), value);
            }
        }
        if(!inputs.isEmpty()) {
            String moduleCode = mapper.getDataSet().substring(0, mapper.getDataSet().indexOf("/"));
            String dataSetCode = mapper.getDataSet().substring(mapper.getDataSet().indexOf("/") + 1);
            try {
                Class defPoClass = CreatorUtil.getDefPoClass("",
                        WebContext.get().getProgram().getCode(), moduleCode, dataSetCode);
                Class defControllerClass = CreatorUtil.getDefControllerClass("",
                        WebContext.get().getProgram().getCode(), moduleCode, dataSetCode);
                Object controller= ServiceFactory.getService(defControllerClass.getClassName().substring(0, 1).toLowerCase()
                        + defControllerClass.getClassName().substring(1));
                Object po = java.lang.Class.forName(defPoClass.getClassPath()).newInstance();
                for (String propertyName : inputs.keySet()) {
                    org.apache.commons.beanutils.BeanUtils.setProperty(po,propertyName,inputs.get(propertyName));
                }
                ResultData resultData = invokeMethod(controller,"search",
                        new java.lang.Class[]{java.lang.Class.forName(defPoClass.getClassPath())},
                        new Object[]{po});

                if(resultData.isSuccess()) {
                    Object data = resultData.getData();
                    request.getSession().setAttribute(java.lang.Class.forName(defPoClass.getClassPath()).getName(),data);
                }
                return resultData;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ResultData.error(ResultCode.UNKNOW);
    };

    /**
     * 数据保存
     * @return
     */
    @RequestMapping(value = "/ajaxSubmits.json")
    @ResponseBody
    public ResultData saveData(HttpServletRequest request,
                               HttpServletResponse response){
        WebContext.clear();
        String refererUrl = request.getHeader("referer");
        String[] refererUrlInfo = Arrays.copyOfRange(refererUrl.split("[/]+"), 2, refererUrl.split("[/]+").length);
        String module = refererUrlInfo[0];
        String pageCode = refererUrlInfo[1].substring(0, refererUrlInfo[1].indexOf(".html"));
        logger.debug("request referer : {},{},{}", refererUrl, module, pageCode);
        try{

            PageDescriptor pageInfo = WebContext.get().getPageInfo(module, pageCode);
            Map<String, ComponentDescriptor> components = pageInfo.getComponents();


            String dataJson = getRequestPostStr(request);
            logger.debug("request : {}", dataJson);

            JSONObject jsonObject = JSONObject.parseObject(dataJson, Feature.OrderedField);
            Set<String> componentIds = jsonObject.keySet();
            Object parentObject = null;
            for (String componentId : componentIds) {
                ComponentDescriptor componentDescriptor = components.get(componentId);
                if(componentDescriptor.getDataSetDescriptor() == null) {
                    logger.warn("component {} is not set data set",componentDescriptor.getId());
                    continue;
                }
                String moduleCode = componentDescriptor.getDataSetDescriptor().getDataSet().getModule();
                String dataSetCode = componentDescriptor.getDataSetDescriptor().getDataSet().getCode();
                String type = componentDescriptor.getComponent().getType();

                String componentJsonData = null;

                Class defPoClass = CreatorUtil.getDefPoClass("",
                        WebContext.get().getProgram().getCode(), moduleCode, dataSetCode);
                Class defControllerClass = CreatorUtil.getDefControllerClass("",
                        WebContext.get().getProgram().getCode(), moduleCode, dataSetCode);
                Object controller= ServiceFactory.getService(defControllerClass.getClassName().substring(0, 1).toLowerCase() + defControllerClass.getClassName().substring(1));
                Object objects = null;
                componentJsonData = jsonObject.getString(componentId);
                logger.debug("class: {}; json: {}", defPoClass.getClassName(), componentJsonData);
                if("treeChart".equals(componentId)) {
                    Map<String, String> result = new LinkedHashMap<String, String>();
                    JSONObject jsonObject1 = JSONObject.parseObject(componentJsonData);
                    parseRelatMap(jsonObject1, result);
                    String[] relPropertyNames = componentDescriptor.getDataSetDescriptor().getRelPropertyNames();
                    if(relPropertyNames != null && relPropertyNames.length == 2) {
                        List tempList = new ArrayList();
                        for (Map.Entry<String, String> newRel : result.entrySet()) {
                            String keyValue = newRel.getKey();
                            String parentKeyValue = newRel.getValue();
                            Object po = java.lang.Class.forName(defPoClass.getClassPath()).newInstance();
                            ReflectUtils.setFieldValue(po,relPropertyNames[0],keyValue);
                            ReflectUtils.setFieldValue(po, relPropertyNames[1], parentKeyValue);
                            tempList.add(po);
                        }
                        objects = tempList.toArray((Object[]) Array.newInstance(java.lang.Class.forName(defPoClass.getClassPath()), 0));
                    }
                }else {
                    objects = readObjectsFromJson(componentJsonData, java.lang.Class.forName(defPoClass.getClassPath()));
                    if(parentObject != null) {

                    }
                }

                ResultData resultData = (ResultData) ReflectUtils.invokeMethod(controller,"batchCreate",new java.lang.Class[]{
                        java.lang.reflect.Array.newInstance(java.lang.Class.forName(defPoClass.getClassPath()), 1).getClass()}, new Object[]{objects});
                logger.debug("result: {}", JSON.toJSONString(resultData));
                if(type.endsWith("Form")) {
                    parentObject = ((Object[])objects)[0];
                    WebContext.add(parentObject);
//                    WebContext.put(type, componentDescriptor);
//                    Object object = readObjectFromJson(componentJsonData, java.lang.Class.forName(defPoClass.getClassPath()));
                }
            }

            return ResultData.success(parentObject);

        }catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }

//        return ResultData.error(ResultCode.UNKNOW);
    }

    private void parseRelatMap(JSONObject jsonObject1, Map<String, String> result) {

        String id = jsonObject1.getString("id");
        JSONArray children = jsonObject1.getJSONArray("children");
        if(children == null) {
            return ;
        }
        Iterator<Object> iterator = children.iterator();
        while (iterator.hasNext()) {
            JSONObject next = (JSONObject) iterator.next();
            String subId = next.getString("id");
            result.put(subId,id);
            parseRelatMap(next, result);
        }
    }

    private Object readObjectFromJson(String jsonString, java.lang.Class<?> poClass) {
        MockHttpInputMessage inputMessage = null;
        try {
            inputMessage = new MockHttpInputMessage(jsonString.getBytes("UTF-8"));
            inputMessage.getHeaders().setContentType(new MediaType("application", "json"));
            MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
            converter.setObjectMapper(this.mvcObjectMapper);
            Object object = converter.read(poClass, inputMessage);
            return object;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Object[] readObjectsFromJson(String jsonString, final java.lang.Class<?> poClass) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter() {
            @Override
            protected JavaType getJavaType(Type type, java.lang.Class<?> contextClass) {
                if (type instanceof java.lang.Class && List.class.isAssignableFrom((java.lang.Class<?>)type)) {
                    return mvcObjectMapper.getTypeFactory().constructCollectionType(ArrayList.class, poClass);
                }
                else {
                    return super.getJavaType(type, contextClass);
                }
            }
        };
        converter.setObjectMapper(this.mvcObjectMapper);
        MockHttpInputMessage inputMessage = null;
        try {
            inputMessage = new MockHttpInputMessage(jsonString.getBytes("UTF-8"));
            inputMessage.getHeaders().setContentType(new MediaType("application", "json"));
            List results = (List) converter.read(List.class, inputMessage);

            Object[] o = (Object[]) Array.newInstance(poClass, results.size());
            for (int i = 0; i < results.size(); i++) {
                o[i] = results.get(i);
            }
            return o;
//            try {
//                return ReflectUtils.invokeMethod(results, "toArray", new java.lang.Class[]{o.getClass()}, new Object[]{o});
//            } catch (InvocationTargetException e) {
//                e.printStackTrace();
//            }
//            return results.toArray(new HfmdEnum[0]);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /***
     * Get request query string, form method : post
     *
     * @param request
     * @return byte[]
     * @throws IOException
     */
    public static byte[] getRequestPostBytes(HttpServletRequest request)
            throws IOException {
        int contentLength = request.getContentLength();
        /*当无请求参数时，request.getContentLength()返回-1 */
        if(contentLength<0){
            return null;
        }
        byte buffer[] = new byte[contentLength];
        for (int i = 0; i < contentLength;) {

            int readlen = request.getInputStream().read(buffer, i,
                    contentLength - i);
            if (readlen == -1) {
                break;
            }
            i += readlen;
        }
        return buffer;
    }

    /***
     * Get request query string, form method : post
     *
     * @param request
     * @return
     * @throws IOException
     */
    public static String getRequestPostStr(HttpServletRequest request)
            throws IOException {
        byte buffer[] = getRequestPostBytes(request);
        String charEncoding = request.getCharacterEncoding();
        if (charEncoding == null) {
            charEncoding = "UTF-8";
        }
        return new String(buffer, charEncoding);
    }

    public ResultData invokeMethod(Object controller, String action, java.lang.Class[] classes, Object[] objects) throws InvocationTargetException {
        ResultData resultData = (ResultData) ReflectUtils.invokeMethod(controller,action,classes,objects);
        return resultData;
    }



    /**
     * 页面跳转
     * @return
     * @throws Throwable
     */
    @RequestMapping(value = "/{page}.html")
    public ModelAndView gotoPage(@PathVariable("page") String pageCode,
                                 @ModelAttribute("component")  String componentId,
                                 @ModelAttribute("pagination")  Pagination pagination,
                                 @ModelAttribute("isPop")  String isPop,
                                 HttpServletRequest request, HttpServletResponse response) throws Throwable {
        return gotoPage("",pageCode,componentId,pagination,isPop,request,response);
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
        WebContext.clear();
        System.out.println("==> " + request.getQueryString());
        ModelAndView mav = new ModelAndView();

        PageDescriptor pageInfo = WebContext.get().getPageInfo(module, pageCode);

        Map<String, Object> extendData = getExtendData("/extend/" + pageCode + ".json", request);

        Map<String, ElementDescriptor> elements = pageInfo.getElements();
        for (String key : elements.keySet()) {
            ElementDescriptor elementDescriptor = elements.get(key);
            if (elementDescriptor instanceof StringDescriptor) {
                StringDescriptor descriptor = (StringDescriptor) elementDescriptor;
                if(StringUtils.isNotBlank(descriptor.getValue())) {
                    mav.addObject(key, descriptor.getValue());
                }else {
                    mav.addObject(key, WebContext.get().getElementValue(key));
                }
            }

        }
        Map<String, ContainerDescriptor> containers = pageInfo.getContainers();

        Map<String, Object> result = new LinkedHashMap<String, Object>();
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
                }else if("eList".equals(type)) {
                     action = "list";
                 }else if("cList".equals(type)) {
                     action = null;
                 }else if(!"cForm".equals(type) && !"qForm".equals(type)) {
                     action = type;
                 }

                JSONObject jsonObject = null;
                String componentQueryString = null;
                if("pageflow".equals(componentDescriptor.getMapper().getDataAuth())) {
                    Map<String, String>  pageContextParams = getPageContextParams(request);
                    WebContext.get().add(getPageContextRealyParams(pageContextParams));
//                        jsonObject.putAll(pageContextParams);
                    jsonObject = componentDescriptor.getJson();
                    jsonObject.put("data", pageContextParams);
                }else if(StringUtils.isNotBlank(action)) {

                    ResultData resultData = null;

                    if (extendData != null && extendData.containsKey(componentDescriptor.getDataId())) {
                        resultData = ResultData.success(extendData.get(componentDescriptor.getDataId()));
                    }else {

                        Class defPoClass = CreatorUtil.getDefPoClass("",
                                WebContext.get().getProgram().getCode(), moduleCode, dataSetCode);
                        Class defPoExampleClass = CreatorUtil.getDefPoExampleClass("",
                                WebContext.get().getProgram().getCode(), moduleCode, dataSetCode);
                        Class defControllerClass = CreatorUtil.getDefControllerClass("",
                                WebContext.get().getProgram().getCode(), moduleCode, dataSetCode);


                        System.out.println("dataId = " + componentDescriptor.getDataId());
                        Object poExample = java.lang.Class.forName(defPoExampleClass.getClassPath()).newInstance();
                        PropertyDescriptor priPropertyDescriptor = org.springframework.beans.BeanUtils.getPropertyDescriptor(java.lang.Class.forName(defPoClass.getClassPath()), "pri");

                        if (priPropertyDescriptor != null) {
                            ReflectUtils.invokeMethod(poExample, "setOrderByClause", new java.lang.Class[]{String.class}, new Object[]{" pri asc"});

                        }
                        Object controller = ServiceFactory.getService(defControllerClass.getClassName().substring(0, 1).toLowerCase() + defControllerClass.getClassName().substring(1));
                        Object po = null;

                        if ("session".equals(componentDescriptor.getMapper().getDataAuth())) {
                            Object data = request.getSession().getAttribute(java.lang.Class.forName(defPoClass.getClassPath()).getName());
                            System.out.println("session data " + data);
                            if (data == null) {
                                mav.addObject("staticResourcePath", "/static");
                                mav.setViewName("/login");
                                return mav;
                            }
                            resultData = ResultData.success(data);
                        } else if ("detail".equals(action)) {
                            po = getPoInstance(request, controller, action, new java.lang.Class[]{java.lang.Class.forName(defPoClass.getClassPath())});
                            resultData = invokeMethod(controller, action,
                                    new java.lang.Class[]{java.lang.Class.forName(defPoClass.getClassPath())},
                                    new Object[]{po});
                            //这里将查询的单个对象存入线程中，别的组件在需要时可以获取想要的值，如数据集数据列智能提醒需要依赖数据集的主实体ID
                            WebContext.add(resultData.getData());
                        } else if ("tree".equals(action)) {
                            po = getPoInstance(request, controller, action, new java.lang.Class[]{java.lang.Class.forName(defPoClass.getClassPath()),
                                    java.lang.Class.forName(defPoExampleClass.getClassPath())});
                            resultData = invokeMethod(controller, action,
                                    new java.lang.Class[]{java.lang.Class.forName(defPoClass.getClassPath()),
                                            java.lang.Class.forName(defPoExampleClass.getClassPath())},
                                    new Object[]{po, poExample});
                        } else {
                            if (pagination.getPageNo() == 0) pagination.setPageNo(1);
                            if (pagination.getPageSize() == 0) pagination.setPageSize(5);
                            if ("eList".equals(type)) pagination.setPageSize(50);
                            po = getPoInstance(request, controller, action, new java.lang.Class[]{java.lang.Class.forName(defPoClass.getClassPath()),
                                    java.lang.Class.forName(defPoExampleClass.getClassPath()), Pagination.class});
                            Map<String, String> pageFlowParams = WebContext.get(HashMap.class.getName());
                            ReflectUtils.setFieldValue(po, pageFlowParams);

                            Map<String, String> params = BeanUtils.convertMap(po, false);
                            componentQueryString = UrlHelper.getUrlQueryString(params);
                            System.out.println("=======> " + componentQueryString);
                            resultData = invokeMethod(controller, action,
                                    new java.lang.Class[]{java.lang.Class.forName(defPoClass.getClassPath()),
                                            java.lang.Class.forName(defPoExampleClass.getClassPath()), Pagination.class},
                                    new Object[]{po, poExample, pagination});
    //                        resultData = (ResultData) ReflectUtils.invokeMethod(controller,action,
    //                                new java.lang.Class[]{java.lang.Class.forName(defPoClass.getClassPath()),
    //                                        java.lang.Class.forName(defPoExampleClass.getClassPath()), Pagination.class},
    //                                new Object[]{po,poExample, pagination});

                            if (resultData.getData() instanceof Map) {
                                List helperData = getHelperData(componentDescriptor.getDataSetDescriptor(), action, defPoClass, request);
                                ((Map) resultData.getData()).put("helperData", helperData);
                            }
                        }
                    }

                    resetResultMessage(resultData, WebContext.get().getProgram().getCode(), moduleCode, dataSetCode, action);
                    if(resultData.isSuccess()) {
                        jsonObject = componentDescriptor.getJson(resultData);

                    }


                }else {
                    if("cList".equals(type)){
                        Class defPoClass = CreatorUtil.getDefPoClass("",
                                WebContext.get().getProgram().getCode(), moduleCode, dataSetCode);
                        List helperData = getHelperData(componentDescriptor.getDataSetDescriptor(), "list", defPoClass,request);
                        ResultData resultData = ResultData.success().add("helperData",helperData);
                        jsonObject = componentDescriptor.getJson(resultData);
                    }else {
                        jsonObject = componentDescriptor.getJson();
                    }

                    if(!(jsonObject.get("data") instanceof JSONArray)) {
                        jsonObject.put("data",JSONObject.toJSON(WebContext.get(HashMap.class.getName())));
                    }
                }
                if("list".equals(type) || "cList".equals(type) || "eList".equals(type)) {
                    if(((JSONArray) jsonObject.get("data")).size() == 0) {
                        String[] defaultNullData = new String[((JSONArray) jsonObject.get("columns")).size()];
                        Arrays.fill(defaultNullData,"");
                        ((JSONArray) jsonObject.get("data")).add(defaultNullData);
                    }

//                    Collections.addAll(((JSONArray) jsonObject.get("data")), defaultNullData);
                }

                if("${icon}".equals(jsonObject.get("icon"))) {
                    if("list".equals(type)) {
                        jsonObject.put("icon","icon-align-justify");
                    }else if("qForm".equals(type)) {
                        jsonObject.put("icon","icon-search");
                    }else {
                        jsonObject.put("icon","icon-edit");
                    }

                }

                if(StringUtils.isNotBlank(componentDescriptor.getTitle())) {
                    jsonObject.put("title",componentDescriptor.getTitle());
                }

                jsonObject.put("icon","icon-edit");
                jsonObject.put("ruler",componentDescriptor.getDataSetDescriptor().getDataSetRulerJsonObject().toJSONString());
                jsonObject.put("helper",componentDescriptor.getDataSetDescriptor().getDynamicHelper());

                jsonObject.put("module",module);
                jsonObject.put("page",pageCode);
                jsonObject.put("param", componentQueryString);
                jsonObject.put("component", componentDescriptor.getId());

                if("treeChart".equals(componentDescriptor.getId())) {
                    jsonObject.put("id", "-1");
                    jsonObject.put("name","根节点");
                }

                String key = componentDescriptor.getId();
                if(result.containsKey(key)) {
                    key = componentDescriptor.getId() + "|" + componentDescriptor.getDataSetDescriptor().getDataSet().getCode() + "|" + componentDescriptor.getDataId();
                }
                System.out.println("=====>" + key + " : " + jsonObject.toJSONString());
                result.put(key, jsonObject);
            }
        }

        mav.addObject("ExtMap",extendData);
        mav.addObject("elements", result);
        mav.addAllObjects(result);
        mav.addObject("isPop","true".equals(isPop)? true : false);
        mav.addObject("staticResourcePath", "/static");
        if(StringUtils.isNotBlank(componentId) && "qList".equals(componentId)) {
            mav.addObject("list",mav.getModelMap().get("qList"));
            mav.setViewName("/component/queryList");

        }else if(StringUtils.isNotBlank(componentId) && "eList".equals(componentId)) {
            mav.addObject("list",mav.getModelMap().get("eList"));
            mav.setViewName("/component/editList");

        }else {
            mav.setViewName(pageInfo.getPageTemplate().getPath().substring(0,pageInfo.getPageTemplate().getPath().indexOf(".vm")));
        }

        return mav;

    }

    private List getHelperData(DataSetDescriptor dataSetDescriptor, String action, Class targetPoClass, HttpServletRequest request) {
        List helperPoList = new ArrayList();
        List<DataSetHelper> dataSetHelpers = dataSetDescriptor.getDataSetHelpers();
        nextHelper : for (DataSetHelper dataSetHelper : dataSetHelpers) {
            String helpModule = dataSetHelper.getHelpModule();
            String helpDataset = dataSetHelper.getHelpDataset();
            String helpDatascore = dataSetHelper.getHelpDatascore();
            try {
                Class defPoClass = CreatorUtil.getDefPoClass("",
                        WebContext.get().getProgram().getCode(), helpModule, helpDataset);
                Class defPoExampleClass = CreatorUtil.getDefPoExampleClass("",
                        WebContext.get().getProgram().getCode(), helpModule, helpDataset);
                Class defControllerClass = CreatorUtil.getDefControllerClass("",
                        WebContext.get().getProgram().getCode(), helpModule, helpDataset);
                Object po = java.lang.Class.forName(defPoClass.getClassPath()).newInstance();
                Object poExample= java.lang.Class.forName(defPoExampleClass.getClassPath()).newInstance();
                Pagination pagination = new Pagination();
                Object controller= ServiceFactory.getService(defControllerClass.getClassName().substring(0, 1).toLowerCase() + defControllerClass.getClassName().substring(1));
                Map<String, String> pageFlowParams = WebContext.get(HashMap.class.getName());
                ReflectUtils.setFieldValue(po, pageFlowParams);

                if(StringUtils.isNotBlank(helpDatascore)) {
                    Map<String, String> condition = DataSetDescriptor.getConditionMap( helpDatascore);
                    for (String propertyName : condition.keySet()) {
                        String propertyValue = condition.get(propertyName);
                        if(!propertyValue.startsWith("{") && !propertyValue.endsWith("}") ) {
                            ReflectUtils.setFieldValue(po,propertyName.trim(),propertyValue);
                            continue ;
                        }
                        propertyValue = propertyValue.substring(1, propertyValue.length() - 1);

                        //组件连带刷新时需要传入所选择的值
                        String parameterValue = request.getParameter(propertyValue);
                        if(parameterValue != null) {
                            if(StringUtils.isNotBlank(parameterValue)) {
                                ReflectUtils.setFieldValue(po,propertyName.trim(),parameterValue);
                            }
                            continue;
                        }

                        String referDataSetCode = propertyValue.substring(0, propertyValue.indexOf("/"));
                        String referDataFiledCode = propertyValue.substring(propertyValue.indexOf("/") + 1);
                        com.hframework.beans.class0.Class referClass =
                                CreatorUtil.getDefPoClass("",
                                        WebContext.get().getProgram().getCode(), "hframe", referDataSetCode);
                        Object relPo = WebContext.get(java.lang.Class.forName(referClass.getClassPath()).getName());
                        if(relPo != null) {
                            Object fieldValue = ReflectUtils.getFieldValue(relPo, CreatorUtil.getJavaVarName(referDataFiledCode));
                            ReflectUtils.setFieldValue(po,propertyName.trim(),fieldValue);
                        }else {
                            continue nextHelper;
                        }
                    }
                }



                Map<String, String> params = BeanUtils.convertMap(po, false);
                String componentQueryString = UrlHelper.getUrlQueryString(params);
                System.out.println("=======> " + componentQueryString);
                PropertyDescriptor priPropertyDescriptor = org.springframework.beans.BeanUtils.getPropertyDescriptor(java.lang.Class.forName(defPoClass.getClassPath()), "pri");

                if(priPropertyDescriptor != null) {
                    ReflectUtils.invokeMethod(poExample, "setOrderByClause", new java.lang.Class[]{String.class}, new Object[]{" pri asc"});
                }
                    ResultData resultData = invokeMethod(controller, action,
                        new java.lang.Class[]{java.lang.Class.forName(defPoClass.getClassPath()),
                                java.lang.Class.forName(defPoExampleClass.getClassPath()), Pagination.class},
                        new Object[]{po, poExample, pagination});
                List list = (List) ((Map) resultData.getData()).get("list");
                for (Object helpPo : list) {
                    Object targetPo = java.lang.Class.forName(targetPoClass.getClassPath()).newInstance();
                    Mappings mappings = dataSetHelper.getMappings();
                    for (com.hframework.web.config.bean.datasethelper.Mapping mapping : mappings.getMappingList()) {
                        String helpDatasetField = mapping.getHelpDatasetField();
                        String effectDatasetField = mapping.getEffectDatasetField();
                        String express = mapping.getExpress();

                        String propertyValue = org.apache.commons.beanutils.BeanUtils.getProperty(helpPo, ResourceWrapper.JavaUtil.getJavaVarName(helpDatasetField));
                        if(StringUtils.isNotBlank(express)) {
                            if(express.startsWith("*.replace(")) {
                                String originChars = express.substring("*.replace(".length(), express.indexOf(","));
                                String targetChars = express.substring(express.indexOf(",") + 1, express.indexOf(")"));
                                propertyValue = propertyValue.replace(originChars, targetChars);
                            }else {
                                propertyValue = express.replace("*",propertyValue);
                            }
                        }
                        org.apache.commons.beanutils.BeanUtils.setProperty(targetPo, ResourceWrapper.JavaUtil.getJavaVarName(effectDatasetField), propertyValue);
                    }
                    helperPoList.add(targetPo);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return helperPoList;
    }



    private Map<String, String> getPageContextRealyParams(Map<String, String> map) {
        Map<String, String> result = new HashMap<String, String>();
        for (String key : map.keySet()) {
            result.put(key.substring(0,key.length()-4), map.get(key));
        }
        return result;
    }

    private Map<String, String> getPageContextParams(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        Enumeration parameterNames = request.getParameterNames();
       while (parameterNames.hasMoreElements()) {
           String paramName = (String)parameterNames.nextElement();
           if(paramName.endsWith("PCXT")) {
               map.put(paramName, request.getParameter(paramName));
           }
       }
        logger.debug("page context : {}", map);
        return map;
    }

    private Object getPoInstance(HttpServletRequest request, Object controller, String action, java.lang.Class[] defPoClass) throws ClassNotFoundException {
        //                    Object po= java.lang.Class.forName(defPoClass.getClassPath()).newInstance();

        Method declaredMethod = ReflectUtils.getDeclaredMethod(controller, action, defPoClass);
        if(declaredMethod == null) {
            logger.warn("{}", controller,action,defPoClass);
        }
        Object po = modelAttributeSetter.resolveArgument(request, new MethodParameter(declaredMethod, 0));
        return po;
    }

    private void resetResultMessage(ResultData resultData, String programCode, String moduleCode, String dataSetCode, String action) {
        String resourceKey = programCode + "." + moduleCode + "." + dataSetCode + "." + action + resultData.getResultCode();
        resourceKey = programCode + "." + moduleCode + "." + dataSetCode + "." + action + resultData.getResultCode();
        resourceKey = action + resultData.getResultCode();
        resourceKey = resultData.getResultCode();
        resultData.setResultMessage("TODO");
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
                        methodParameter,  new ModelAndViewContainer(), webRequest, this.binderFactory);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @RequestMapping(value = "/all_urls.json")
    @ResponseBody
    public void list(HttpServletRequest request, HttpServletResponse response) {
        StringBuilder sb = new StringBuilder();
        sb.append("URL").append("--").append("Class").append("--").append("Function").append('\n');

        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();

        for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
            RequestMappingInfo info = m.getKey();
            HandlerMethod method = m.getValue();
            sb.append(info.getPatternsCondition()).append("--");
            sb.append(method.getMethod().getDeclaringClass()).append("--");
            sb.append(method.getMethod().getName()).append('\n');
        }

        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.print(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }

    }

    private Map<String, Object> getExtendData(String url , HttpServletRequest request) {
        HandlerExecutionChain handler = null;
        if(urlMapping.size() == 0) {
            synchronized (DefaultController.class){
                Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
                for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
                    RequestMappingInfo info = m.getKey();
                    HandlerMethod method = m.getValue();
                    urlMapping.put(info.getPatternsCondition().getPatterns().iterator().next(), method);
                }
            }
        }

        HandlerMethod handlerMethod = urlMapping.get(url);
        if(handlerMethod == null) {
            return null;
        }

        try {
            Object bean = ServiceFactory.getService(String.valueOf(handlerMethod.getBean()));
            Method method = handlerMethod.getMethod();
            Object result = method.invoke(bean, request);
            return (Map<String, Object>)((ResultData)result).getData();
//            String lookupPath = requestMappingHandlerMapping.getUrlPathHelper().getLookupPathForRequest(request);
//            HandlerMethod handlerMethod = requestMappingHandlerMapping.lookupHandlerMethod(lookupPath, request);
//            handler = requestMappingHandlerMapping.getHandler(request);
//            if(handler.getHandler() != null && handler.getHandler() instanceof HandlerMethod) {
//                HandlerMethod handlerMethod = (HandlerMethod) handler.getHandler();
//                Object bean = handlerMethod.getBean();
//                Method method = handlerMethod.getMethod();
//                Object result = method.invoke(bean, request);
//                return result;
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
