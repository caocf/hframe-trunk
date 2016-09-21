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
import com.hframework.web.config.bean.component.Event;
import com.hframework.web.config.bean.dataset.Entity;
import com.hframework.web.config.bean.dataset.Field;
import com.hframework.web.config.bean.dataset.Fields;
import com.hframework.web.config.bean.dataset.Node;
import com.hframework.web.config.bean.module.*;
import com.hframework.web.config.bean.pagetemplates.Element;
import com.hframework.web.config.bean.pagetemplates.Pagetemplate;
import com.hframework.web.enums.ElementType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.Class;
import java.util.*;

/**
 * User: zhangqh6
 * Date: 2016/5/11 0:31:31
 */
public class WebContext {
    private static Logger logger = LoggerFactory.getLogger(WebContext.class);

    private static WebContext context = new WebContext();


    private Map<String, com.hframework.web.config.bean.module.Component> defaultComponentMap
            = new HashMap<String, com.hframework.web.config.bean.module.Component>();
    private Map<String, com.hframework.web.config.bean.module.Element> defaultElementMap
            = new HashMap<String, com.hframework.web.config.bean.module.Element>();

    private WebContextHelper contextHelper;

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

    private Map<String, com.hframework.web.config.bean.component.Event> events = new HashMap<String, Event>();

    private Map<String, Map<String, PageDescriptor>> pageSetting = new HashMap<String, Map<String, PageDescriptor>>();

    private Map<String, DataSetDescriptor> dataSets = new HashMap<String, DataSetDescriptor>();

    private Map<Class, DataSetDescriptor> dataSetCache = new HashMap<Class, DataSetDescriptor>();

    public WebContext() {
        this(null, null, null);
    }

    public WebContext(String companyCode, String programCode, String templateCode) {
        try {
            logger.info("web context init{} ..", companyCode, programCode, templateCode);
            init(companyCode, programCode, templateCode);
            logger.info("web context init ok !", JSONObject.toJSONString(context));
        } catch (Exception e) {
            logger.error("web context init error : ", e);
        }
    }



    public void init(String companyCode, String programCode, String templateCode) throws Exception {
        if(StringUtils.isNotBlank(companyCode) && StringUtils.isNotBlank(programCode)) {
            contextHelper = new WebContextHelper(companyCode,programCode,null,templateCode);
        }else {
            contextHelper = new WebContextHelper();
        }

        //加载项目配置
        loadComponentConfig();

        //加载数据集信息
        loadDataSet();
        //加载数据集信息
        loadDataSetHelper();

        //加载数据集连带规则信息
        loadDataSetRuler();
        //页面架构数据初始化
        pageSettingInitial();

    }

    private void loadDataSet() throws Exception {

        //加载数据源信息
        List<DataSet> dataSetList = XmlUtils.readValuesFromDirectory(contextHelper.programConfigRootDir,contextHelper.programConfigDataSetDir, DataSet.class, ".xml");
        for (DataSet dataSet : dataSetList) {
            DataSetDescriptor dataSetDescriptor = new DataSetDescriptor(dataSet);

            if(dataSet.getDescriptor() != null) {
                Map<String, DataSetDescriptor> dataSetCodeMap = new HashMap<String, DataSetDescriptor>();
                if(dataSet.getDescriptor().getFieldsList() != null) {
                    for (Fields fields :  dataSet.getDescriptor().getFieldsList()) {
                        DataSet tmpDataSet = new DataSet();
                        tmpDataSet.setCode(dataSet.getCode() + "#" + fields.getCode());
                        tmpDataSet.setName(fields.getName());
                        tmpDataSet.setModule(dataSet.getModule());
                        tmpDataSet.setSource(dataSet.getSource());
                        tmpDataSet.setFields(fields);
                        DataSetDescriptor tempDataSetDescriptor = new DataSetDescriptor(tmpDataSet);
                        dataSets.put(dataSet.getModule() + "/" + dataSet.getCode() + "#" + fields.getCode(), tempDataSetDescriptor);
                        dataSetCodeMap.put(fields.getCode(), tempDataSetDescriptor);
                    }
                }
               if(dataSet.getDescriptor().getNode() != null) {
                   Node rootNode = dataSet.getDescriptor().getNode();
                   rootNode.calcPath();
                   IDataSet iDataSet = calculateNodeGrid(rootNode, dataSetCodeMap);
                   dataSetDescriptor.setDateSetStruct(iDataSet);
//                   System.out.println(iDataSet);
               }
            }

            dataSets.put(dataSet.getModule() + "/" + dataSet.getCode(), dataSetDescriptor);
            com.hframework.beans.class0.Class defPoClass = CreatorUtil.getDefPoClass("",
                    program.getCode(), dataSet.getModule(), dataSet.getCode());
            if(dataSet.getCode().equals(dataSet.getEventObjectCode())) {
                try{
                    Class<?> aClass = Class.forName(defPoClass.getClassPath());
                    dataSetCache.put(aClass, dataSetDescriptor);
                }catch (Exception e) {
//                    e.printStackTrace();
                }

            }else {
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

    public static boolean isList(Node node) {
        if(node.getName().endsWith("[]")) {
            return true;
        }
        return false;
    }

    public static String getNodeName(Node node) {
        if(node.getName().endsWith("[]")) {
            return node.getName().substring(0,node.getName().length()-2);
        }
        return node.getName();
    }

    private IDataSet calculateNodeGrid(Node node, Map<String, DataSetDescriptor> dataSetCodes) {

        DataSetInstance curDataSetInstance = null;
        List<DataSetInstance> subDataSetInstances = new ArrayList<DataSetInstance>();
        List<DataSetInstance> dataSetInstances = new ArrayList<DataSetInstance>();
        List<DataSetContainer> dataSetContainers = new ArrayList<DataSetContainer>();

        List<IDataSet> sortedDataSetObjects = new ArrayList<IDataSet>();

        if(dataSetCodes.containsKey(node.getPath())) {
            curDataSetInstance = DataSetInstance.valueOf(node);
            dataSetInstances.add(curDataSetInstance);
            sortedDataSetObjects.add(curDataSetInstance);
        }

        List<Node> nodeList = node.getNodeList();

        if(nodeList != null && nodeList.size() > 0) {
            for (Node subNode : nodeList) {
                IDataSet result = calculateNodeGrid(subNode, dataSetCodes);
                sortedDataSetObjects.add(result);
                if (result instanceof DataSetInstance) {
                    subDataSetInstances.add((DataSetInstance) result);
                    dataSetInstances.add((DataSetInstance) result);
                }else {
                    dataSetContainers.add((DataSetContainer) result);
                }
            }
        }


        if(dataSetContainers.size() == 0) {
            if(dataSetInstances.size() == 0) {//该场景原则上不会出现
                return null;
            }else if(dataSetInstances.size() == 1) {//该场景原则上出现为叶子节点
                return dataSetInstances.get(0);
            }else {//该场景为：① 1个父节点+>= 1的子节点；② >= 2的子节点;涉及多个数据集，原则上是需要合并，所以先合并
                DataSetContainer dataSetContainer = DataSetContainer.valueOf(node, dataSetInstances);
                dataSetContainer.setElementList(sortedDataSetObjects);
                return dataSetContainer;
//                boolean islist = false;
//                for (DataSetInstance subDataSetInstance : subDataSetInstances) {
//                    islist  = isList(subDataSetInstance.getNode()) || islist;
//                }
//
//                if(curDataSetInstance != null) {//当前节点有数据，叶子节点也有数据 ==> 合
//                    return DataSetContainer.valueOf(dataSetInstances);
//                }else {//同级别多个叶子节点 ==> 合
//                    return DataSetContainer.valueOf(dataSetInstances);
//                }

            }
        }else {
            boolean isMany = false;
            for (DataSetContainer subDataSetContainer : dataSetContainers) {//判断是否存在多个元素子容器
                isMany = isMany || subDataSetContainer.isMany();
            }

            if(isMany && dataSetContainers.size() + dataSetInstances.size() > 1) {//如果存在子容器多元素且存在两个及以上元素，需要成立新的容器
                DataSetContainer dataSetContainer = DataSetContainer.valueOf(node, dataSetInstances, dataSetContainers);
                dataSetContainer.setElementList(sortedDataSetObjects);
                return dataSetContainer;
            }else if(dataSetContainers.size() + dataSetInstances.size() == 1) {//如果只存在一个子容器且只有一个元素，重新赋值子容器元素
                if(!isMany) {
                    dataSetContainers.get(0).setNode(node);
                }
                return dataSetContainers.get(0);
            }else {//存在两个容器以上且不是都是单元素容器，合并容器为一个容器

                DataSetContainer dataSetContainer = DataSetContainer.valueOf(node, dataSetInstances);

                for (DataSetContainer subDataSetContainer : dataSetContainers) {
                    dataSetContainer.addDataAll(subDataSetContainer.getDatas());
                    dataSetContainer.addContainerAll(subDataSetContainer.getSubDataSetContainers());
                }

                for (IDataSet result : sortedDataSetObjects) {
                    if (result instanceof DataSetInstance) {
                        dataSetContainer.getElementList().add(result);
                    }else {
                        dataSetContainer.getElementList().addAll(((DataSetContainer) result).getElementList());
                    }
                }
                return dataSetContainer;
            }

        }

//        if(dataSetContainers.size() == 1) {
//            dataSetContainers.get(0).addDataAll(subDataSetInstances);
//            return dataSetContainers.get(0);
//        }
//
//        DataSetContainer dataSetContainer = new DataSetContainer();
//        dataSetContainer.setSubDataSetContainers(dataSetContainers);
//        dataSetContainer.setDatas(subDataSetInstances);
//        return dataSetContainer;

    }

    private DataSetInstance mergeDataSet(List result1) {
        List<Map<String, String>> data = new ArrayList<Map<String, String>>();
        Iterator iterator = result1.iterator();
        while (iterator.hasNext()) {
            Object o = iterator.next();
            if (o instanceof DataSetInstance) {
                DataSetInstance dataSetInst = (DataSetInstance) o;
                if(dataSetInst.isOne()) {
                    data.add(dataSetInst.getOne());
                    iterator.remove();
                }
            }
        }

        return data.size() > 0 ? DataSetInstance.valueOf(data) : null;
    }







    private void loadDataSetHelper() throws Exception {

        List<DataSetHelper> dataSetHelperList = XmlUtils.readValuesFromDirectory(contextHelper.programConfigRootDir,contextHelper.programConfigDataSetHelperDir, DataSetHelper.class, ".xml");
        for (DataSetHelper dataSetHelper : dataSetHelperList) {
            String effectModuleCode = dataSetHelper.getEffectModule();
            String effectDatasetCode = dataSetHelper.getEffectDataset();
            DataSetDescriptor dataSetDescriptor = dataSets.get(effectModuleCode + "/" + effectDatasetCode);
            dataSetDescriptor.addDataSetHelper(dataSetHelper);
        }
    }

    private void loadDataSetRuler() throws Exception {

        List<DataSetRuler> dataSetRulers = XmlUtils.readValuesFromDirectory(contextHelper.programConfigRootDir,contextHelper.programConfigDataSetRulerDir, DataSetRuler.class, ".xml");
        for (DataSetRuler dataSetRuler : dataSetRulers) {
            String module = dataSetRuler.getModule();
            String entity = dataSetRuler.getEntity();
            DataSetDescriptor dataSetDescriptor = dataSets.get(module + "/" + entity);
            dataSetDescriptor.addDataSetRuler(dataSetRuler);
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

        //获取组件级初始化信息。
        List<com.hframework.web.config.bean.module.Component> componentList = page.getComponentList();
        for (com.hframework.web.config.bean.module.Component component : componentList) {
            ComponentDescriptor componentDescriptor = pageDescriptor.getComponentDescriptor(component.getId());
            if(componentDescriptor == null) {//针对于dynamic动态模板进行处理
                Element element = new Element();
                element.setId(component.getId());
                element.setType(ElementType.component.getName());
                element.setEventExtend("false");
                pageDescriptor.addComponentDescriptor(component.getId() + "|" + component.getDataSet()+ "|" + component.getDataid(), parseComponentDescriptor(element));
            }
        }
        //获取页面级初始化信息。
        if(StringUtils.isBlank(page.getDataSet())) {
            logger.warn("no mapper exists for page level !");
        }else {
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
                if(dataSets.get(page.getDataSet()) == null) {
                    logger.warn("no dataset {} exists !", page.getDataSet() + "_" + componentDescriptor.getId());
                    continue;
                }
                componentDescriptor.setDataSetDescriptor(dataSets.get(page.getDataSet()));
                componentDescriptor.initComponentDataContainer(events);
            }
        }



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
            if("container".equals(component.getId())) {
                System.out.println(1);
            }
            if(componentDescriptor == null) {
                componentDescriptor = pageDescriptor.getComponentDescriptor(component.getId()  + "|" + component.getDataSet() + "|" + component.getDataid());
            }
            if(componentDescriptor != null) {
                componentDescriptor.setEventList(component.getEventList());
                componentDescriptor.setDataId(component.getDataid());
                componentDescriptor.setTitle(component.getTitle());
                componentDescriptor.setEventExtend(component.getEventExtend());
                componentDescriptor.setMapper(mapper);
                componentDescriptor.setDataSetDescriptor(dataSets.get(component.getDataSet()));
                if(dataSets.get(component.getDataSet()) == null) {
                    System.out.println("==>error : data set [" +  component.getDataSet() +"] is not exists !");
                }
                componentDescriptor.initComponentDataContainer(events);
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
        try{
            program = XmlUtils.readValueFromFile(contextHelper.programConfigRootDir,contextHelper.programConfigProgramFile, Program.class);
        }catch (Exception e) {
            program = XmlUtils.readValueFromFile(contextHelper.programConfigRootDir.replace("hframe-webtemplate","hframe-web"),contextHelper.programConfigProgramFile, Program.class);
        }
        //加载模块信息
        List<Module> moduleList = XmlUtils.readValuesFromDirectory(contextHelper.programConfigRootDir, contextHelper.programConfigModuleDir, Module.class, ".xml");
        for (Module module : moduleList) {
            List<Page> pageList = module.getPageList();
            if(pageList != null) {
                for (Page page : pageList) {
                    page.setModule(module);
                }
            }

            if(this.modules.containsKey(module.getCode())) {
                this.modules.get(module.getCode()).getPageList().addAll(module.getPageList());
            }else {
                this.modules.put(module.getCode(),module);
            }

        }

        List<com.hframework.web.config.bean.program.Module> moduleList1 = program.getModules().getModuleList();
        for (com.hframework.web.config.bean.program.Module module : moduleList1) {
            if(!this.modules.containsKey(module.getCode())) {
                Module module1 = new Module();
                module1.setCode(module.getCode());
                module1.setPageList(new ArrayList<Page>());
                this.modules.put(module.getCode(),module1);
            }
        }


        //加载数据映射信息
        List<Mapper> mapperList = XmlUtils.readValuesFromDirectory(contextHelper.programConfigRootDir, contextHelper.programConfigMapperDir, Mapper.class, ".mapper");
        for (Mapper mapper : mapperList) {
            mappers.put(mapper.getDataSet() + "_" + mapper.getComponentId(), mapper);
        }

        //加载页面模板信息
        PageTemplates pageTemplates = XmlUtils.readValueFromFile(contextHelper.programConfigRootDir, contextHelper.templateResourcePageDescriptorFile, PageTemplates.class);
        for (Pagetemplate pagetemplate : pageTemplates.getPagetemplateList()) {
            this.pageTemplates.put(pagetemplate.getId(),pagetemplate);
        }

        //加载事件信息
        List<EventStore> eventStores = XmlUtils.readValuesFromDirectory(contextHelper.programConfigRootDir,contextHelper.templateResourceEventStoreDir, EventStore.class,".xml");
        for (EventStore eventStore : eventStores) {
            String group = eventStore.getGroup();
            List<Event> eventList = eventStore.getEventList();
            for (Event event : eventList) {
                events.put("#" + group + "." + event.getName(),event);
            }
        }

        //加载组件模板信息
        List<Component> componentList = XmlUtils.readValuesFromDirectory(contextHelper.programConfigRootDir,contextHelper.templateResourceComponentDir, Component.class,".xml");
        for (Component component : componentList) {
            if(StringUtils.isBlank(component.getId())) {
                continue;
            }
            components.put(component.getId(),component);
        }

        //加载默认数据映射信息
        List<Mapper> defaultMappers = XmlUtils.readValuesFromDirectory(contextHelper.programConfigRootDir,contextHelper.templateResourceComponentMapperDir, Mapper.class,".mapper");
        for (Mapper mapper : defaultMappers) {
            mappers.put(mapper.getComponentId(), mapper);
        }
    }


    public static WebContext get(){
        return context;
    }

    public static WebContext get(String companyCode, String programCode, String templateCode){
        WebContext newContext = new WebContext(companyCode, programCode, templateCode);
        return newContext;
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
        if(data == null) {
            return;
        }
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

    public static <T> void putSession(String key, T data) {
        Context.put("SESSION:" + key, data);
    }

    public static <T> T getSession(String key) {
        return Context.get("SESSION:" + key);
    }

    public static <T> T get(String key) {
        return Context.get(key);
    }

    public static Map<String, String> putContext(String key, String value) {
        Map<String, String> map = get(HashMap.class.getName());
        if(map == null) {
            put(HashMap.class.getName(), new HashMap<String, String>());
        }
        map = get(HashMap.class.getName());
        map.put(key, value);
        return map;
    }

    public static Map<String, String> putContext(Map<String, String> objectMap) {
        Map<String, String> map = get(HashMap.class.getName());
        if(map == null) {
            put(HashMap.class.getName(), new HashMap<String, String>());
        }
        map = get(HashMap.class.getName());
        map.putAll(objectMap);
        return map;
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


    public Map<Module,List<List<Entity>>> getEntityRelats() {
        Map<String, Module> modules = this.modules;
        Map<Module,List<List<Entity>>> result = new HashMap<Module, List<List<Entity>>>();
        for (String moduleCode : modules.keySet()) {
            if(StringUtils.isBlank(moduleCode)) {
                continue;
            }
            Module module = modules.get(moduleCode);
            result.put(module, new ArrayList<List<Entity>>());
            List<List<Entity>> moduleEntityList = result.get(module);
            List<Page> pageList = module.getPageList();
            for (Page page : pageList) {
                if("true".equals(page.getModule().getIsExtend())) {
                    continue;
                }
                Set<Entity> allEntitys = new LinkedHashSet<Entity>();
                DataSetDescriptor dataSetDescriptor = null;
                if(StringUtils.isNotBlank(page.getDataSet())) {
                    dataSetDescriptor = this.dataSets.get(page.getDataSet());
                    allEntitys.addAll(dataSetDescriptor.getDataSet().getEntityList());
                }

                List<com.hframework.web.config.bean.module.Component> componentList = page.getComponentList();
                for (com.hframework.web.config.bean.module.Component component : componentList) {
                    if(this.defaultComponentMap.values().contains(component)) {
                        continue;
                    }
                    String dataSet = component.getDataSet();
                    if(StringUtils.isBlank(dataSet)) {
                        continue;
                    }

                    dataSetDescriptor = this.dataSets.get(dataSet);
                    allEntitys.addAll(dataSetDescriptor.getDataSet().getEntityList());
                }

                Entity rootEntity = null;
                for (final Entity entity : allEntitys) {
                    if(rootEntity == null) rootEntity = entity;
                    List<Entity> relEntityList = getListFromModuleEntityList(moduleEntityList, rootEntity);
                    if(relEntityList == null) {
                        moduleEntityList.add(new LinkedList<Entity>(){{add(entity);}});
                    }else {
                        boolean flag = false;
                        for (Entity entity1 : relEntityList) {
                            if(entity1.getText().equals(entity.getText())) {
                                flag = true;
                                break;
                            }
                        }
                        if(!flag) {
                            relEntityList.add(entity);
                        }
                    }
                }
            }
        }

        return result;
    }

    private List<Entity> getListFromModuleEntityList(List<List<Entity>> moduleEntityList, Entity entity) {

        for (List<Entity> entities : moduleEntityList) {
            if(entities.contains(entity)) {
                return entities;
            }
        }

        return null;
    }

    public WebContextHelper getContextHelper() {
        return contextHelper;
    }

    public void setContextHelper(WebContextHelper contextHelper) {
        this.contextHelper = contextHelper;
    }

    public Map<String, Event> getEvents() {
        return events;
    }

    public void setEvents(Map<String, Event> events) {
        this.events = events;
    }
}
