package com.hframework.web.bean;

import com.alibaba.fastjson.JSONObject;
import com.hframework.beans.controller.ResultData;
import com.hframework.web.config.bean.Component;
import com.hframework.web.config.bean.Mapper;
import com.hframework.web.config.bean.mapper.Mapping;
import com.hframework.web.config.bean.pagetemplates.Element;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangquanhong on 2016/5/26.
 */
public class ComponentDescriptor extends ElementDescriptor{

    private Component component;

    private DataSetDescriptor dataSetDescriptor;

    private Mapper mapper;

    private ComponentDataContainer dataContainer;

    public void initComponentDataContainer() {
        dataContainer = new ComponentDataContainer(component,getElement());
        List<Mapping> mappingList = mapper.getBaseMapper().getMappingList();
        for (Mapping mapping : mappingList) {
            dataContainer.addMappingAndDataSetDescriptor(mapping, dataSetDescriptor, true);
        }

        List<Mapping> mappingList1 = mapper.getEventMapper().getMappingList();
        for (Mapping mapping : mappingList1) {
            dataContainer.addMappingAndDataSetDescriptor(mapping, dataSetDescriptor, false);
        }
    }

    public JSONObject getJson(ResultData resultData){
        ComponentDataContainer dataInstance;
        if(resultData.getData() != null) {
            dataInstance = this.dataContainer.getDataInstance(resultData.getData());
            return dataInstance.getJson();
        }else {

        }
        return null;
    }

    public JSONObject getJson(){
        return this.dataContainer.getJson();
    }

    public ComponentDescriptor(Element element) {
        super(element);
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;

    }

    public DataSetDescriptor getDataSetDescriptor() {
        return dataSetDescriptor;
    }

    public void setDataSetDescriptor(DataSetDescriptor dataSetDescriptor) {
        this.dataSetDescriptor = dataSetDescriptor;
    }

    public Mapper getMapper() {
        return mapper;
    }

    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }


}