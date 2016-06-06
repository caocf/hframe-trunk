package com.hframe.controller;

import com.hframework.beans.controller.Pagination;
import com.hframework.beans.controller.ResultCode;
import com.hframework.beans.controller.ResultData;
import com.hframework.common.util.ExampleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import java.util.*;
import com.hframe.domain.model.HfcfgPageTemplate;
import com.hframe.domain.model.HfcfgPageTemplate_Example;
import com.hframe.service.interfaces.IHfcfgPageTemplateSV;

@Controller
@RequestMapping(value = "/hframe/hfcfgPageTemplate")
public class HfcfgPageTemplateController   {
    private static final Logger logger = LoggerFactory.getLogger(HfcfgPageTemplateController.class);

	@Resource
	private IHfcfgPageTemplateSV iHfcfgPageTemplateSV;
  




    /**
     * 查询展示页面模板列表
     * @param hfcfgPageTemplate
     * @return
     * @throws Throwable
     */
    @RequestMapping(value = "/queryListByAjax.html")
    @ResponseBody
    public ResultData list(@ModelAttribute("hfcfgPageTemplate") HfcfgPageTemplate hfcfgPageTemplate,
                                      @ModelAttribute("example") HfcfgPageTemplate_Example example, Pagination pagination){
        logger.debug("request : {},{},{}", hfcfgPageTemplate, example, pagination);
        try{
            ExampleUtils.parseExample(hfcfgPageTemplate, example);
            //设置分页信息
            example.setLimitStart(pagination.getStartIndex());
            example.setLimitEnd(pagination.getEndIndex());

            final List< HfcfgPageTemplate> list = iHfcfgPageTemplateSV.getHfcfgPageTemplateListByExample(example);
            pagination.setTotalCount(iHfcfgPageTemplateSV.getHfcfgPageTemplateCountByExample(example));

            return ResultData.success().add("list",list).add("pagination",pagination);
        }catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
    }

    /**
     * 查询展示页面模板明细
     * @param hfcfgPageTemplate
     * @return
     * @throws Throwable
     */
    @RequestMapping(value = "/queryOneByAjax.html")
    @ResponseBody
    public ResultData detail(@ModelAttribute("hfcfgPageTemplate") HfcfgPageTemplate hfcfgPageTemplate){
        logger.debug("request : {},{}", hfcfgPageTemplate.getHfcfgPageTemplateId(), hfcfgPageTemplate);
        try{
            HfcfgPageTemplate result = iHfcfgPageTemplateSV.getHfcfgPageTemplateByPK(hfcfgPageTemplate.getHfcfgPageTemplateId());
            if(result != null) {
                return ResultData.success(result);
            }else {
                return ResultData.error(ResultCode.RECODE_IS_NOT_EXISTS);
            }
        }catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
    }

    /**
    * 创建页面模板
    * @param hfcfgPageTemplate
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/createByAjax.html")
    @ResponseBody
    public ResultData create(@ModelAttribute("hfcfgPageTemplate") HfcfgPageTemplate hfcfgPageTemplate) {
        logger.debug("request : {}", hfcfgPageTemplate);
        try {
            int result = iHfcfgPageTemplateSV.create(hfcfgPageTemplate);
            if(result > 0) {
                return ResultData.success(hfcfgPageTemplate);
            }
        } catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
        return ResultData.error(ResultCode.UNKNOW);
    }

    /**
    * 更新页面模板
    * @param hfcfgPageTemplate
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/updateByAjax.html")
    @ResponseBody
    public ResultData update(@ModelAttribute("hfcfgPageTemplate") HfcfgPageTemplate hfcfgPageTemplate) {
        logger.debug("request : {}", hfcfgPageTemplate);
        try {
            int result = iHfcfgPageTemplateSV.update(hfcfgPageTemplate);
            if(result > 0) {
                return ResultData.success(hfcfgPageTemplate);
            }
        } catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
        return ResultData.error(ResultCode.UNKNOW);
    }

    /**
    * 删除页面模板
    * @param hfcfgPageTemplate
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/deleteByAjax.html")
    @ResponseBody
    public ResultData delete(@ModelAttribute("hfcfgPageTemplate") HfcfgPageTemplate hfcfgPageTemplate) {
        logger.debug("request : {}", hfcfgPageTemplate);

        try {
            int result = iHfcfgPageTemplateSV.delete(hfcfgPageTemplate);
            if(result > 0) {
                return ResultData.success(hfcfgPageTemplate);
            }else {
                return ResultData.error(ResultCode.RECODE_IS_NOT_EXISTS);
            }
        } catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
    }
  	//getter
 	
	public IHfcfgPageTemplateSV getIHfcfgPageTemplateSV(){
		return iHfcfgPageTemplateSV;
	}
	//setter
	public void setIHfcfgPageTemplateSV(IHfcfgPageTemplateSV iHfcfgPageTemplateSV){
    	this.iHfcfgPageTemplateSV = iHfcfgPageTemplateSV;
    }
}
