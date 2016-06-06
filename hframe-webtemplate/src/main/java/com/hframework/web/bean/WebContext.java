package com.hframework.web.bean;

import com.alibaba.fastjson.JSONObject;
import com.hframework.common.util.EnumUtils;
import com.hframework.common.util.StringUtils;
import com.hframework.common.util.message.XmlUtils;
import com.hframework.web.config.bean.*;
import com.hframework.web.config.bean.Component;
import com.hframework.web.config.bean.Module;
import com.hframework.web.config.bean.module.*;
import com.hframework.web.config.bean.pagetemplates.Element;
import com.hframework.web.config.bean.pagetemplates.Pagetemplate;
import com.hframework.web.enums.ElementType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
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
    private static final String PAGE_DESCRIPTER_FILE = "hframework/template/default/page/pagedescripter.xml";

    private static WebContext context = new WebContext();
    static {
        try {
            logger.info("web context init ..");
            context.init();
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


    public void init() throws Exception {

        //加载项目配置
        loadComponentConfig();

        //加载数据集信息
        loadDataSet();
        //页面架构数据初始化
        pageSettingInitial();

    }

    private void loadDataSet() throws IOException {

        //加载数据源信息
        List<DataSet> dataSetList = XmlUtils.readValuesFromDirectory(DATA_SET_DIR, DataSet.class);
        for (DataSet dataSet : dataSetList) {
            dataSets.put(dataSet.getModule() + "/" + dataSet.getCode(), new DataSetDescriptor(dataSet));
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
            if(mappers.get(page.getDataSet() + "_" + componentDescriptor.getId()) == null) {
                logger.warn("no mapper {} exists !", page.getDataSet() + "_" + componentDescriptor.getId());
                continue;
            }
            componentDescriptor.setMapper(mappers.get(page.getDataSet() + "_" + componentDescriptor.getId()));
            componentDescriptor.setDataSetDescriptor(dataSets.get(page.getDataSet()));
            componentDescriptor.initComponentDataContainer();
        }

        //获取组件级初始化信息。
        List<com.hframework.web.config.bean.module.Component> componentList = page.getComponentList();
        for (com.hframework.web.config.bean.module.Component component : componentList) {
            if(StringUtils.isBlank(component.getDataSet())) {
                component.setDataSet(page.getDataSet());
            }
            ComponentDescriptor componentDescriptor = pageDescriptor.getComponentDescriptor(component.getId());
            componentDescriptor.setMapper(mappers.get(component.getDataSet() + "_" + component.getId()));
            componentDescriptor.setDataSetDescriptor(dataSets.get(component.getDataSet()));
            componentDescriptor.initComponentDataContainer();
        }


        return pageDescriptor;
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
        pageTemplateStack.add(pageTemplate);
        if(StringUtils.isNotBlank(pageTemplate.getParentId())) {
            getPageTemplateStack(pageTemplate.getParentId(), pageTemplateStack);
        }

        return pageTemplateStack;
    }


    private void loadComponentConfig() throws IOException {
        //加载项目信息
        program = XmlUtils.readValueFromFile(PROGRAM_FILE, Program.class);
        //加载模块信息
        List<Module> moduleList = XmlUtils.readValuesFromDirectory(MODULE_DIR, Module.class);
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
    }


    public static WebContext get(){
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
}
