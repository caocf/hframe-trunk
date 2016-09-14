package com.hframework.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.hframe.domain.model.*;
import com.hframe.service.interfaces.*;
import com.hframework.base.service.CommonDataService;
import com.hframework.base.service.DataSetLoaderService;
import com.hframework.base.service.ModelLoaderService;
import com.hframework.beans.controller.ResultCode;
import com.hframework.beans.controller.ResultData;
import com.hframework.common.ext.CollectionUtils;
import com.hframework.common.ext.Mapper;
import com.hframework.common.util.*;
import com.hframework.common.util.message.VelocityUtil;
import com.hframework.common.util.message.XmlUtils;
import com.hframework.ext.datasoucce.DataSourceContextHolder;
import com.hframework.generator.util.CreatorUtil;
import com.hframework.generator.web.BaseGeneratorUtil;
import com.hframework.generator.web.bean.HfClassContainer;
import com.hframework.generator.web.bean.HfClassContainerUtil;
import com.hframework.generator.web.bean.HfModelContainer;
import com.hframework.generator.web.bean.HfModelContainerUtil;
import com.hframework.generator.web.mybatis.MyBatisGeneratorUtil;
import com.hframework.generator.web.sql.HfModelService;
import com.hframework.generator.web.sql.SqlGeneratorUtil;
import com.hframework.generator.web.sql.reverse.SQLParseUtil;
import com.hframework.web.bean.WebContext;
import com.hframework.web.bean.WebContextHelper;
import com.hframework.web.config.bean.Module;
import com.hframework.web.config.bean.dataset.Entity;
import com.hframework.web.config.bean.module.Component;
import com.hframework.web.config.bean.module.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;

/**
 * User: zhangqh6
 * Date: 2016/5/11 0:16:16
 */
@Controller
@RequestMapping(value = "/after")
public class AfterInvokeController {
    private static final Logger logger = LoggerFactory.getLogger(AfterInvokeController.class);

    @Resource
    private DataSetLoaderService dataSetLoaderService;


    /**
     * 数据保存
     * @return
     */
    @RequestMapping(value = "/hframe/hfpmModule/createsByAjax.json")
    @ResponseBody
    public ResultData saveData(HttpServletRequest request,
                               HttpServletResponse response){

        try {
            Map<String, String>  pageContextParams = DefaultController.getPageContextParams(request);
            WebContext.putContext(DefaultController.getPageContextRealyParams(pageContextParams));
            Map<String, String> pageFlowParams = WebContext.get(HashMap.class.getName());
            ResultData resultData = new DefaultController().saveData(request, response);
            return resultData;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DataSourceContextHolder.clear();
        }

        return ResultData.error(ResultCode.UNKNOW);
    }


}
