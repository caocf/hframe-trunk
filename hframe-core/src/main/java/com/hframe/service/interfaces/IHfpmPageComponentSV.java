package com.hframe.service.interfaces;

import java.util.*;
import com.hframe.domain.model.HfpmPageComponent;
import com.hframe.domain.model.HfpmPageComponent_Example;


public interface IHfpmPageComponentSV   {

  
    /**
    * 创建页面组件
    * @param hfpmPageComponent
    * @return
    * @throws Exception
    */
    public int create(HfpmPageComponent hfpmPageComponent) throws  Exception;

    /**
    * 批量维护页面组件
    * @param hfpmPageComponents
    * @return
    * @throws Exception
    */
    public int batchOperate(HfpmPageComponent[] hfpmPageComponents) throws  Exception;
    /**
    * 更新页面组件
    * @param hfpmPageComponent
    * @return
    * @throws Exception
    */
    public int update(HfpmPageComponent hfpmPageComponent) throws  Exception;

    /**
    * 通过查询对象更新页面组件
    * @param hfpmPageComponent
    * @param example
    * @return
    * @throws Exception
    */
    public int updateByExample(HfpmPageComponent hfpmPageComponent, HfpmPageComponent_Example example) throws  Exception;

    /**
    * 删除页面组件
    * @param hfpmPageComponent
    * @return
    * @throws Exception
    */
    public int delete(HfpmPageComponent hfpmPageComponent) throws  Exception;


    /**
    * 删除页面组件
    * @param hfpmPageComponentId
    * @return
    * @throws Exception
    */
    public int delete(long hfpmPageComponentId) throws  Exception;


    /**
    * 查找所有页面组件
    * @return
    */
    public List<HfpmPageComponent> getHfpmPageComponentAll()  throws  Exception;

    /**
    * 通过页面组件ID查询页面组件
    * @param hfpmPageComponentId
    * @return
    * @throws Exception
    */
    public HfpmPageComponent getHfpmPageComponentByPK(long hfpmPageComponentId)  throws  Exception;

    /**
    * 通过MAP参数查询页面组件
    * @param params
    * @return
    * @throws Exception
    */
    public List<HfpmPageComponent> getHfpmPageComponentListByParam(Map<String, Object> params)  throws  Exception;


    /**
    * 通过查询对象查询页面组件
    * @param example
    * @return
    * @throws Exception
    */
    public List<HfpmPageComponent> getHfpmPageComponentListByExample(HfpmPageComponent_Example example) throws  Exception;

    /**
    * 通过MAP参数查询页面组件数量
    * @param params
    * @return
    * @throws Exception
    */
    public int getHfpmPageComponentCountByParam(Map<String, Object> params)  throws  Exception;


    /**
    * 通过查询对象查询页面组件数量
    * @param example
    * @return
    * @throws Exception
    */
    public int getHfpmPageComponentCountByExample(HfpmPageComponent_Example example) throws  Exception;


 }
