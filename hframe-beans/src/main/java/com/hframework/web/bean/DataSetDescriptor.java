package com.hframework.web.bean;

import com.hframework.common.util.ReflectUtils;
import com.hframework.web.CreatorUtil;
import com.hframework.web.config.bean.DataSet;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangquanhong on 2016/5/26.
 */
public class DataSetDescriptor {

    private DataSet dataSet;

    private Map<String, String> relFieldKeyMap = new HashMap<String, String>();

    //<hfpm_program/hfpm_program_id,ProgramDescriptor.class>
    private Map<String, DataSetDescriptor> relDataSetMap = new HashMap<String, DataSetDescriptor>();

    public void addRelDataSet(String fieldName, String key, DataSetDescriptor descriptor) {
        relDataSetMap.put(key,descriptor);
        relFieldKeyMap.put(fieldName,key);
    }

    public DataSetDescriptor(DataSet dataSet) {
        this.dataSet = dataSet;
    }

    public DataSet getDataSet() {
        return dataSet;
    }

    public void setDataSet(DataSet dataSet) {
        this.dataSet = dataSet;
    }

    public Map<String, DataSetDescriptor> getRelDataSetMap() {
        return relDataSetMap;
    }

    public Map<String, String> getRelFieldKeyMap() {
        return relFieldKeyMap;
    }

    public Map<String, Object> getRelFieldValueMap(Object object) {
        Map<String, Object> result = new HashMap<String, Object>();
        for (String fieldCode : relFieldKeyMap.keySet()) {
            String propertyName = CreatorUtil.getJavaVarName(fieldCode);
            Object propertyValue = ReflectUtils.getFieldValue(object, propertyName);
            result.put(relFieldKeyMap.get(fieldCode), propertyValue);
        }


        return result;
    }
}
