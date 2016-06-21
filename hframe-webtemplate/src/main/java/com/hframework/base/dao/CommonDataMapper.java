package com.hframework.base.dao;

import com.hframework.base.bean.KVBean;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CommonDataMapper {

    /**
     * 动态查询表数据
     * @param entity
     * @return
     * @throws Exception
     */
    public List<KVBean> selectDynamicTableDataList(Map entity) throws Exception;
}