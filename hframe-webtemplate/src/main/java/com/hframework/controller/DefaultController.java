package com.hframework.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import com.hframework.common.ext.Mapping;
import com.hframework.common.frame.ServiceFactory;
import com.hframework.common.frame.cache.PropertyConfigurerUtils;
import com.hframework.common.util.ReflectUtils;
import com.hframework.common.util.StringUtils;
import com.hframework.web.CreatorUtil;
import com.hframework.web.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpInputMessage;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
import java.io.IOException;
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
            if(split.length != 3) {
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
//                put("extColumn", dictionary.getExtColumn());
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
        logger.debug("request referer : {},{},{}",refererUrl, module, pageCode);
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
                Object objects ;
                componentJsonData = jsonObject.getString(componentId);
                logger.debug("class: {}; json: {}", defPoClass.getClassName(), componentJsonData);
                objects = readObjectsFromJson(componentJsonData, java.lang.Class.forName(defPoClass.getClassPath()));
                if(parentObject != null) {

                }
                ResultData resultData = (ResultData) ReflectUtils.invokeMethod(controller,"batchCreate",new java.lang.Class[]{
                        java.lang.reflect.Array.newInstance(java.lang.Class.forName(defPoClass.getClassPath()), 1).getClass()}, new Object[]{objects});
                logger.debug("result: {}", JSON.toJSONString(resultData));
                if(type.endsWith("Form")) {
                    parentObject = ((Object[])objects)[0];
                    WebContext.add(parentObject);
//                    Object object = readObjectFromJson(componentJsonData, java.lang.Class.forName(defPoClass.getClassPath()));
                }
            }

        }catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }

        return ResultData.error(ResultCode.UNKNOW);
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

        Map<String, ElementDescriptor> elements = pageInfo.getElements();
        for (String key : elements.keySet()) {
            mav.addObject(key, elements.get(key).getId());
        }
        Map<String, ContainerDescriptor> containers = pageInfo.getContainers();

        Map<String, Object> result = new LinkedHashMap<String, Object>();
//        result.put("cFrom","fdsafdsafsdafds");
//        result.put("eList","fdsa");
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
                    Object poExample= java.lang.Class.forName(defPoExampleClass.getClassPath()).newInstance();
                    Object controller= ServiceFactory.getService(defControllerClass.getClassName().substring(0, 1).toLowerCase() + defControllerClass.getClassName().substring(1));
                    Object po =  null;

                    ResultData resultData = null;
                    if("detail".equals(action)) {
                        po = getPoInstance(request, controller, action, new java.lang.Class[]{java.lang.Class.forName(defPoClass.getClassPath())});
                        resultData = invokeMethod(controller,action,
                                new java.lang.Class[]{java.lang.Class.forName(defPoClass.getClassPath())},
                                new Object[]{po});
                    }else {
                        po = getPoInstance(request, controller, action, new java.lang.Class[]{java.lang.Class.forName(defPoClass.getClassPath()),
                                java.lang.Class.forName(defPoExampleClass.getClassPath()), Pagination.class});
                        resultData = invokeMethod(controller,action,
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
                        jsonObject = componentDescriptor.getJson(resultData);

                    }
                }else {
                    jsonObject = componentDescriptor.getJson();
                }
                if("cList".equals(type) || "eList".equals(type)) {
                    if(((JSONArray) jsonObject.get("data")).size() == 0) {
                        String[] defaultNullData = new String[((JSONArray) jsonObject.get("columns")).size()];
                        Arrays.fill(defaultNullData,"");
                        ((JSONArray) jsonObject.get("data")).add(defaultNullData);
                    }

//                    Collections.addAll(((JSONArray) jsonObject.get("data")), defaultNullData);
                }

                jsonObject.put("module",module);
                jsonObject.put("page",pageCode);
                jsonObject.put("param","");
                jsonObject.put("component",componentDescriptor.getId());
                System.out.println(jsonObject.toJSONString());
                result.put(componentDescriptor.getId(),jsonObject);
            }
        }

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

    private Object getPoInstance(HttpServletRequest request, Object controller, String action, java.lang.Class[] defPoClass) throws ClassNotFoundException {
        //                    Object po= java.lang.Class.forName(defPoClass.getClassPath()).newInstance();

        Method declaredMethod = ReflectUtils.getDeclaredMethod(controller, action,  defPoClass);
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
}
