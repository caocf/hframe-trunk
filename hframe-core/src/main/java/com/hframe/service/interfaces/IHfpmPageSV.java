package com.hframe.service.interfaces;

import java.util.*;
import com.hframe.domain.model.HfpmPage;
import com.hframe.domain.model.HfpmPage_Example;


public interface IHfpmPageSV   {

  
    /**
    * 创建页面
    * @param hfpmPage
    * @return
    * @throws Exception
    */
    public int create(HfpmPage hfpmPage) throws  Exception;


    /**
    * 更新页面
    * @param hfpmPage
    * @return
    * @throws Exception
    */
    public int update(HfpmPage hfpmPage) throws  Exception;

    /**
    * 通过查询对象更新页面
    * @param hfpmPage
    * @param example
    * @return
    * @throws Exception
    */
    public int updateByExample(HfpmPage hfpmPage, HfpmPage_Example example) throws  Exception;

    /**
    * 删除页面
    * @param hfpmPage
    * @return
    * @throws Exception
    */
    public int delete(HfpmPage hfpmPage) throws  Exception;


    /**
    * 删除页面
    * @param hfpmPageId
    * @return
    * @throws Exception
    */
    public int delete(long hfpmPageId) throws  Exception;


    /**
    * 查找所有页面
    * @return
    */
    public List<HfpmPage> getHfpmPageAll()  throws  Exception;

    /**
    * 通过页面ID查询页面
    * @param hfpmPageId
    * @return
    * @throws Exception
    */
    public HfpmPage getHfpmPageByPK(long hfpmPageId)  throws  Exception;

    /**
    * 通过MAP参数查询页面
    * @param params
    * @return
    * @throws Exception
    */
    public List<HfpmPage> getHfpmPageListByParam(Map<String, Object> params)  throws  Exception;


    /**
    * 通过查询对象查询页面
    * @param example
    * @return
    * @throws Exception
    */
    public List<HfpmPage> getHfpmPageListByExample(HfpmPage_Example example) throws  Exception;

    /**
    * 通过MAP参数查询页面数量
    * @param params
    * @return
    * @throws Exception
    */
    public int getHfpmPageCountByParam(Map<String, Object> params)  throws  Exception;


    /**
    * 通过查询对象查询页面数量
    * @param example
    * @return
    * @throws Exception
    */
    public int getHfpmPageCountByExample(HfpmPage_Example example) throws  Exception;


 }
