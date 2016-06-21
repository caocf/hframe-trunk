package com.hframework.base.service;

import com.hframework.base.bean.KVBean;
import com.hframework.base.dao.CommonDataMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * User: zhangqh6
 * Date: 2016/6/6 21:39:39
 */
@Service("commonDataService")
public class CommonDataService {

    @Resource
    private CommonDataMapper commonDataMapper;

    /**
     * 动态查询表数据
     * @param entity
     * @return
     * @throws Exception
     */
    public List<KVBean> selectDynamicTableDataList(Map entity) throws Exception {
        return commonDataMapper.selectDynamicTableDataList(entity);
    }
}
