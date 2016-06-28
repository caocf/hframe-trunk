package com.hframework.web.bean;

import com.hframework.web.config.bean.DataSet;

/**
 * Created by zhangquanhong on 2016/5/26.
 */
public class DataSetDescriptor {

    private DataSet dataSet;
    public DataSetDescriptor(DataSet dataSet) {
        this.dataSet = dataSet;
    }

    public DataSet getDataSet() {
        return dataSet;
    }

    public void setDataSet(DataSet dataSet) {
        this.dataSet = dataSet;
    }
}
