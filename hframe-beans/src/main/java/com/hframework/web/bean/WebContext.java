package com.hframework.web.bean;

import com.alibaba.fastjson.JSONObject;
import com.hframework.common.util.EnumUtils;
import com.hframework.common.util.ReflectUtils;
import com.hframework.common.util.StringUtils;
import com.hframework.common.util.message.XmlUtils;
import com.hframework.web.CreatorUtil;
import com.hframework.web.config.bean.*;
import com.hframework.web.config.bean.Component;
import com.hframework.web.config.bean.Module;
import com.hframework.web.config.bean.dataset.Field;
import com.hframework.web.config.bean.mapper.Mapping;
import com.hframework.web.config.bean.module.*;
import com.hframework.web.config.bean.pagetemplates.Element;
import com.hframework.web.config.bean.pagetemplates.Pagetemplate;
import com.hframework.web.enums.ElementType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.Class;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * User: zhangqh6
 * Date: 2016/5/11 0:31:31
 */
public class WebContext {
    private static Logger logger = LoggerFactory.getLogger(WebContext.class);

//    private static final String DATA_SET_DIR = "/data/set";
//    private static final String MODULE_DIR = "/module";
//    private static final String MAPPER_DIR = "/mapper";
//    private static final String PROGRAM_FILE = "/program.xml";
//    private static final String COMPONENT_DIR = "/template/component";
//    private static final String PAGE_DESCRIPTER_FILE = "template/page/pagedescripter.xml";

    private static final String PROGRAM_ROOT_DIR = "program/hframe";
    private static final String DATA_SET_DIR = PROGRAM_ROOT_DIR + "/data/set";
    private static final String MODULE_DIR = PROGRAM_ROOT_DIR + "/module";
    private static final String MAPPER_DIR = PROGRAM_ROOT_DIR + "/mapper";
    private static final String PROGRAM_FILE = PROGRAM_ROOT_DIR + "/program.xml";

    private static final String COMPONENT_DIR = "hframework/template/default/component";
    private static final String COMPONENT_DEFAULT_MAPPER_DIR = "hframework/template/default/compMapper";
    private static final String PAGE_DESCRIPTER_FILE = "hframework/template/default/page/pagedescripter.xml";


    private Map<String, com.hframework.web.config.bean.module.Component> defaultComponentMap
            = new HashMap<String, com.hframework.web.config.bean.module.Component>();
    private Map<String, com.hframework.web.config.bean.module.Element> defaultElementMap
            = new HashMap<String, com.hframework.web.config.bean.module.Element>();


    private static WebContext context = new WebContext();

    public WebContext() {
        try {
            logger.info("web context init ..");
            init();
            logger.info("web context init ok !", JSONObject.toJSONString(context));
        } catch (Exception e) {
            logger.error("web context init error : ", e);
        }
    }

    //项目信息
    private Program program;
    //模块信息
    private Map<String, Module> modules = new HashMap<String, Module>();
    //组件映射信息
    private Map<String, Mapper> mappers = new HashMap<String, Mapper>();

    //页面模板信息
    private Map<String, Pagetemplate> pageTemplates = new HashMap<String, Pagetemplate>();
    //组件信息
    private Map<String, Component> components = new HashMap<String, Component>();


    private Map<String, Map<String, PageDescriptor>> pageSetting = new HashMap<String, Map<String, PageDescriptor>>();

    private Map<String, DataSetDescriptor> dataSets = new HashMap<String, DataSetDescriptor>();

    private Map<Class, DataSetDescriptor> dataSetCache = new HashMap<Class, DataSetDescriptor>();

    public void init() throws Exception {

        //加载项目配置
        loadComponentConfig();

        //加载数据集信息
        loadDataSet();
        //页面架构数据初始化
        pageSettingInitial();

    }

    private void loadDataSet() throws Exception {

        //加载数据源信息
        List<DataSet> dataSetList = XmlUtils.readValuesFromDirectory(DATA_SET_DIR, DataSet.class);
        for (DataSet dataSet : dataSetList) {
            DataSetDescriptor dataSetDescriptor = new DataSetDescriptor(dataSet);
            dataSets.put(dataSet.getModule() + "/" + dataSet.getCode(), dataSetDescriptor);
            com.hframework.beans.class0.Class defPoClass = CreatorUtil.getDefPoClass("",
                    program.getCode(), dataSet.getModule(), dataSet.getCode());
            try {
                Class<?> aClass = Class.forName(defPoClass.getClassPath());
                dataSetCache.put(aClass, dataSetDescriptor);
            }catch (Exception e) {
                //针对于查询类的dateset无需缓存
            }
        }

        for (DataSetDescriptor dataSetDescriptor : dataSetCache.values()) {
            List<Field> fieldList = dataSetDescriptor.getDataSet().getFields().getFieldList();
            for (Field field : fieldList) {
                if(field.getRel() != null && field.getRel().getEntityCode() != null) {
                    String entityCode = field.getRel().getEntityCode();
                    String dataSetCode = entityCode.substring(0, entityCode.indexOf("/"));
                    String relFieldCode = entityCode.substring(entityCode.indexOf("/") + 1, entityCode.lastIndexOf("/"));
                    com.hframework.beans.class0.Class relPoClass =
                            CreatorUtil.getDefPoClass("",
                                    program.getCode(), "hframe", dataSetCode);
                    DataSetDescriptor relDataSetDescriptor = dataSetCache.get(Class.forName(relPoClass.getClassPath()));
                    dataSetDescriptor.addRelDataSet(field.getCode(), dataSetCode + "/" + relFieldCode, relDataSetDescriptor);
                }
            }
        }
    }

    private void pageSettingInitial() throws Exception {
        for (String moduleCode : modules.keySet()) {
            pageSetting.put(moduleCode, new HashMap<String, PageDescriptor>());
            Module module = modules.get(moduleCode);
            List<Page> pageList = module.getPageList();
            if(pageList == null) {
                continue;
            }
            for (Page page : pageList) {
                if(StringUtils.isBlank(page.getId()) && StringUtils.isBlank(moduleCode)) {
                    List<com.hframework.web.config.bean.module.Component> pubComponentList = page.getComponentList();
                    for (com.hframework.web.config.bean.module.Component component : pubComponentList) {
                        defaultComponentMap.put(component.getId(), component);
                    }
                    List<com.hframework.web.config.bean.module.Element> elementList = page.getElementList();
                    for (com.hframework.web.config.bean.module.Element element : elementList) {
                        defaultElementMap.put(element.getId(),element);
                    }

                    continue;
                }
                pageSetting.get(moduleCode).put(page.getId(), parsePageDescriptor(page, moduleCode));
            }
        }
    }

    private PageDescriptor parsePageDescriptor(Page page, String moduleCode) throws Exception {
        PageDescriptor pageDescriptor = new PageDescriptor();
        pageDescriptor.setModule(moduleCode);
        pageDescriptor.setCode(page.getId());
        pageDescriptor.setName(page.getName());
        pageDescriptor.setPageTemplate(pageTemplates.get(page.getPageTemplate()));

        Stack<Pagetemplate> pageTemplateStack =  getPageTemplateStack(page.getPageTemplate(), new Stack<Pagetemplate>());
        for (Pagetemplate pageTemplate : pageTemplateStack) {
            List<Element> elementList = pageTemplate.getElementList();
            for (Element element : elementList) {
                if(EnumUtils.compareIfNullTrue(ElementType.component, element.getType())) {
                    pageDescriptor.addComponentDescriptor(element.getId(), parseComponentDescriptor(element));
                }else if(EnumUtils.compare(ElementType.string, element.getType())) {
                    pageDescriptor.addElementDescriptor(element.getId(), parseStringDescriptor(element));
                }else if(EnumUtils.compare(ElementType.container, element.getType())) {
                    pageDescriptor.addElementDescriptor(element.getId(), parseContainerDescriptor(element));
                }
            }
        }

        //获取页面级初始化信息。
        for (ComponentDescriptor componentDescriptor : pageDescriptor.getComponents().values()) {
            Mapper mapper = null;
            if(mappers.get(page.getDataSet() + "_" + componentDescriptor.getId()) != null) {
                mapper = mappers.get(page.getDataSet() + "_" + componentDescriptor.getId());
            }

            if(mappers.get(componentDescriptor.getId()) != null) {
                mapper = mappers.get(componentDescriptor.getId());
            }

            if(mapper == null) {
                logger.warn("no mapper {} exists !", page.getDataSet() + "_" + componentDescriptor.getId());
                continue;
            }
            componentDescriptor.setMapper(mapper);
            componentDescriptor.setDataSetDescriptor(dataSets.get(page.getDataSet()));
            componentDescriptor.initComponentDataContainer();
        }

        //获取组件级初始化信息。
        List<com.hframework.web.config.bean.module.Component> componentList = page.getComponentList();
        //将默认的组件配置添加到每一个page，如果page中没有该组件，需要兼容处理
        componentList.addAll(defaultComponentMap.values());
        for (com.hframework.web.config.bean.module.Component component : componentList) {
            if(StringUtils.isBlank(component.getDataSet())) {
                component.setDataSet(page.getDataSet());
            }
            Mapper mapper = null;
            if(mappers.get(component.getDataSet() + "_" + component.getId()) != null) {
                mapper = mappers.get(component.getDataSet() + "_" + component.getId());
            }

            if(mappers.get(component.getId()) != null) {
                mapper = mappers.get(component.getId());
            }

            if(mapper == null) {
                logger.warn("no mapper {} exists !", page.getDataSet() + "_" + component.getId());
                continue;
            }
            ComponentDescriptor componentDescriptor = pageDescriptor.getComponentDescriptor(component.getId());
            if(componentDescriptor != null) {
                componentDescriptor.setMapper(mapper);
                componentDescriptor.setDataSetDescriptor(dataSets.get(component.getDataSet()));
                componentDescriptor.initComponentDataContainer();
            }
//            Map<String, ElementDescriptor> elements = pageDescriptor.getElements();
//            for (String key : elements.keySet()) {
//                ElementDescriptor elementDescriptor = elements.get(key);
//                if (elementDescriptor instanceof StringDescriptor) {
//                    StringDescriptor descriptor = (StringDescriptor) elementDescriptor;
//                    List<Mapping> mappingList = mapper.getBaseMapper().getMappingList();
//                    for (Mapping mapping : mappingList) {
//                        if(key.equals(mapping.getId())) {
//                            descriptor.setValue(mapping.getValue());
//                        }
//                    }
//                }
//            }

        }


        return pageDescriptor;
    }

    public Mapper getMapper(String dataSet, String componentId) {
        if(mappers.get(dataSet + "_" + componentId) != null) {
            return mappers.get(dataSet + "_" + componentId);
        }
        return mappers.get(componentId);
    }

    public String getElementValue(String elementId) {
        return defaultElementMap.get(elementId) != null ? defaultElementMap.get(elementId).getValue() : null;
    }

    private ElementDescriptor parseContainerDescriptor(Element element) {
        ContainerDescriptor descriptor = new ContainerDescriptor(element);
        return descriptor;
    }

    private ElementDescriptor parseStringDescriptor(Element element) {
        StringDescriptor descriptor = new StringDescriptor(element);

        return descriptor;
    }

    private ComponentDescriptor parseComponentDescriptor(Element element) throws Exception {
        ComponentDescriptor componentDescriptor = new ComponentDescriptor(element);
        Component component = components.get(element.getId());
        if(component == null) {
            throw  new Exception("没有找到对应的组件" + element.getId());
        }
        componentDescriptor.setComponent(component);

        return componentDescriptor;
    }

    private Stack<Pagetemplate>  getPageTemplateStack(String pageTemplateId, Stack<Pagetemplate> pageTemplateStack) {
        Pagetemplate pageTemplate = pageTemplates.get(pageTemplateId);
        pageTemplateStack.add(0,pageTemplate);
//        pageTemplateStack.add(pageTemplate);
        if(StringUtils.isNotBlank(pageTemplate.getParentId())) {
            getPageTemplateStack(pageTemplate.getParentId(), pageTemplateStack);
        }

        return pageTemplateStack;
    }


    private void loadComponentConfig() throws IOException {
        //加载项目信息
        program = XmlUtils.readValueFromFile(PROGRAM_FILE, Program.class);
        //加载模块信息
        List<Module> moduleList = XmlUtils.readValuesFromDirectory(MODULE_DIR, Module.class, ".xml");
        for (Module module : moduleList) {
            this.modules.put(module.getCode(),module);
        }
        //加载数据映射信息
        List<Mapper> mapperList = XmlUtils.readValuesFromDirectory(MAPPER_DIR, Mapper.class);
        for (Mapper mapper : mapperList) {
            mappers.put(mapper.getDataSet() + "_" + mapper.getComponentId(), mapper);
        }

        //加载页面模板信息
        PageTemplates pageTemplates = XmlUtils.readValueFromFile(PAGE_DESCRIPTER_FILE, PageTemplates.class);
        for (Pagetemplate pagetemplate : pageTemplates.getPagetemplateList()) {
            this.pageTemplates.put(pagetemplate.getId(),pagetemplate);
        }

        //加载组件模板信息
        List<Component> componentList = XmlUtils.readValuesFromDirectory(COMPONENT_DIR, Component.class,".xml");
        for (Component component : componentList) {
            if(StringUtils.isBlank(component.getId())) {
                continue;
            }
            components.put(component.getId(),component);
        }

        //加载默认数据映射信息
        List<Mapper> defaultMappers = XmlUtils.readValuesFromDirectory(COMPONENT_DEFAULT_MAPPER_DIR, Mapper.class);
        for (Mapper mapper : defaultMappers) {
            mappers.put(mapper.getComponentId(), mapper);
        }
    }


    public static WebContext get(){
        return context;
    }

    public synchronized static WebContext reload(){
        WebContext newContext = new WebContext();
        context = newContext;
        return context;
    }



    public PageDescriptor getPageInfo(String module, String pageCode) {
//        try {
//            context.init();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        PageDescriptor pageDescriptor = pageSetting.get(module).get(pageCode);
        return pageDescriptor;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public Map<Class, DataSetDescriptor> getDataSetCache() {
        return dataSetCache;
    }

    public DataSetDescriptor getDataSet(Class clazz) {
        return dataSetCache.get(clazz);
    }

    public void setDataSetCache(Map<Class, DataSetDescriptor> dataSetCache) {
        this.dataSetCache = dataSetCache;
    }

    public Map<String, com.hframework.web.config.bean.module.Component> getDefaultComponentMap() {
        return defaultComponentMap;
    }

    public void setDefaultComponentMap(Map<String, com.hframework.web.config.bean.module.Component> defaultComponentMap) {
        this.defaultComponentMap = defaultComponentMap;
    }

    public static <T> void add(T data) {
        Class<?> aClass = data.getClass();
        String simpleName = aClass.getName();
        Context.put(simpleName, data);
    }

    public static void clear() {
        Context.clear();
    }

    public static <T> void put(String key, T data) {
        Context.put(key, data);
    }

    public static <T> T get(String key) {
        return Context.get(key);
    }

    public static <T> boolean fillProperty(String key, T t, String propertyName, String relPropertyName) {
        Object cacheObject = get(key);
        if(cacheObject != null) {
            Object propertyValue;
            if(cacheObject instanceof Map) {
                propertyValue  = ((Map)cacheObject).get(relPropertyName);
            }else {
                propertyValue = ReflectUtils.getFieldValue(cacheObject, relPropertyName);
            }
            if(propertyValue != null) {
                ReflectUtils.setFieldValue(t,propertyName, propertyValue);
                return true;
            }

        }

        return false;
    }

    public static class Context{
        private static ThreadLocal<Map<String, Item>> itemsTL = new ThreadLocal<Map<String, Item>>();

        public static <T> void put(String key, T data) {
            if(itemsTL.get() == null) {
                itemsTL.set(new HashMap<String, Item>());
            }
            itemsTL.get().put(key, new Item(data));
        }

        public static void clear() {
            itemsTL.remove();
        }

        public static <T> T get(String key) {
            if(itemsTL.get() != null && itemsTL.get().containsKey(key)) {
                return (T) itemsTL.get().get(key).getT();
            }
            return null;
        }
    }



    public static class Item<T>{

        private T t ;

        public Item(T t) {
            this.t = t;
        }

        public T getT() {
            return t;
        }

        public void setT(T t) {
            this.t = t;
        }
    }

}
