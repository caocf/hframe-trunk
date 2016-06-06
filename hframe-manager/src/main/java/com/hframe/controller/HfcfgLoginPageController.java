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
import com.hframe.domain.model.HfcfgLoginPage;
import com.hframe.domain.model.HfcfgLoginPage_Example;
import com.hframe.service.interfaces.IHfcfgLoginPageSV;

@Controller
@RequestMapping(value = "/hframe/hfcfgLoginPage")
public class HfcfgLoginPageController   {
    private static final Logger logger = LoggerFactory.getLogger(HfcfgLoginPageController.class);

	@Resource
	private IHfcfgLoginPageSV iHfcfgLoginPageSV;
  




    /**
     * 查询展示登陆页列表
     * @param hfcfgLoginPage
     * @return
     * @throws Throwable
     */
    @RequestMapping(value = "/queryListByAjax.html")
    @ResponseBody
    public ResultData list(@ModelAttribute("hfcfgLoginPage") HfcfgLoginPage hfcfgLoginPage,
                                      @ModelAttribute("example") HfcfgLoginPage_Example example, Pagination pagination){
        logger.debug("request : {},{},{}", hfcfgLoginPage, example, pagination);
        try{
            ExampleUtils.parseExample(hfcfgLoginPage, example);
            //设置分页信息
            example.setLimitStart(pagination.getStartIndex());
            example.setLimitEnd(pagination.getEndIndex());

            final List< HfcfgLoginPage> list = iHfcfgLoginPageSV.getHfcfgLoginPageListByExample(example);
            pagination.setTotalCount(iHfcfgLoginPageSV.getHfcfgLoginPageCountByExample(example));

            return ResultData.success().add("list",list).add("pagination",pagination);
        }catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
    }

    /**
     * 查询展示登陆页明细
     * @param hfcfgLoginPage
     * @return
     * @throws Throwable
     */
    @RequestMapping(value = "/queryOneByAjax.html")
    @ResponseBody
    public ResultData detail(@ModelAttribute("hfcfgLoginPage") HfcfgLoginPage hfcfgLoginPage){
        logger.debug("request : {},{}", hfcfgLoginPage.getHfcfgLoginPageId(), hfcfgLoginPage);
        try{
            HfcfgLoginPage result = iHfcfgLoginPageSV.getHfcfgLoginPageByPK(hfcfgLoginPage.getHfcfgLoginPageId());
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
    * 创建登陆页
    * @param hfcfgLoginPage
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/createByAjax.html")
    @ResponseBody
    public ResultData create(@ModelAttribute("hfcfgLoginPage") HfcfgLoginPage hfcfgLoginPage) {
        logger.debug("request : {}", hfcfgLoginPage);
        try {
            int result = iHfcfgLoginPageSV.create(hfcfgLoginPage);
            if(result > 0) {
                return ResultData.success(hfcfgLoginPage);
            }
        } catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
        return ResultData.error(ResultCode.UNKNOW);
    }

    /**
    * 更新登陆页
    * @param hfcfgLoginPage
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/updateByAjax.html")
    @ResponseBody
    public ResultData update(@ModelAttribute("hfcfgLoginPage") HfcfgLoginPage hfcfgLoginPage) {
        logger.debug("request : {}", hfcfgLoginPage);
        try {
            int result = iHfcfgLoginPageSV.update(hfcfgLoginPage);
            if(result > 0) {
                return ResultData.success(hfcfgLoginPage);
            }
        } catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
        return ResultData.error(ResultCode.UNKNOW);
    }

    /**
    * 删除登陆页
    * @param hfcfgLoginPage
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/deleteByAjax.html")
    @ResponseBody
    public ResultData delete(@ModelAttribute("hfcfgLoginPage") HfcfgLoginPage hfcfgLoginPage) {
        logger.debug("request : {}", hfcfgLoginPage);

        try {
            int result = iHfcfgLoginPageSV.delete(hfcfgLoginPage);
            if(result > 0) {
                return ResultData.success(hfcfgLoginPage);
            }else {
                return ResultData.error(ResultCode.RECODE_IS_NOT_EXISTS);
            }
        } catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
    }
  	//getter
 	
	public IHfcfgLoginPageSV getIHfcfgLoginPageSV(){
		return iHfcfgLoginPageSV;
	}
	//setter
	public void setIHfcfgLoginPageSV(IHfcfgLoginPageSV iHfcfgLoginPageSV){
    	this.iHfcfgLoginPageSV = iHfcfgLoginPageSV;
    }
}
