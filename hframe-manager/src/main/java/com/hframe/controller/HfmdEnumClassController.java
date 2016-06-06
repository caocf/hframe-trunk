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
import com.hframe.domain.model.HfmdEnumClass;
import com.hframe.domain.model.HfmdEnumClass_Example;
import com.hframe.service.interfaces.IHfmdEnumClassSV;

@Controller
@RequestMapping(value = "/hframe/hfmdEnumClass")
public class HfmdEnumClassController   {
    private static final Logger logger = LoggerFactory.getLogger(HfmdEnumClassController.class);

	@Resource
	private IHfmdEnumClassSV iHfmdEnumClassSV;
  




    /**
     * 查询展示枚举组列表
     * @param hfmdEnumClass
     * @return
     * @throws Throwable
     */
    @RequestMapping(value = "/queryListByAjax.html")
    @ResponseBody
    public ResultData list(@ModelAttribute("hfmdEnumClass") HfmdEnumClass hfmdEnumClass,
                                      @ModelAttribute("example") HfmdEnumClass_Example example, Pagination pagination){
        logger.debug("request : {},{},{}", hfmdEnumClass, example, pagination);
        try{
            ExampleUtils.parseExample(hfmdEnumClass, example);
            //设置分页信息
            example.setLimitStart(pagination.getStartIndex());
            example.setLimitEnd(pagination.getEndIndex());

            final List< HfmdEnumClass> list = iHfmdEnumClassSV.getHfmdEnumClassListByExample(example);
            pagination.setTotalCount(iHfmdEnumClassSV.getHfmdEnumClassCountByExample(example));

            return ResultData.success().add("list",list).add("pagination",pagination);
        }catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
    }

    /**
     * 查询展示枚举组明细
     * @param hfmdEnumClass
     * @return
     * @throws Throwable
     */
    @RequestMapping(value = "/queryOneByAjax.html")
    @ResponseBody
    public ResultData detail(@ModelAttribute("hfmdEnumClass") HfmdEnumClass hfmdEnumClass){
        logger.debug("request : {},{}", hfmdEnumClass.getHfmdEnumClassId(), hfmdEnumClass);
        try{
            HfmdEnumClass result = iHfmdEnumClassSV.getHfmdEnumClassByPK(hfmdEnumClass.getHfmdEnumClassId());
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
    * 创建枚举组
    * @param hfmdEnumClass
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/createByAjax.html")
    @ResponseBody
    public ResultData create(@ModelAttribute("hfmdEnumClass") HfmdEnumClass hfmdEnumClass) {
        logger.debug("request : {}", hfmdEnumClass);
        try {
            int result = iHfmdEnumClassSV.create(hfmdEnumClass);
            if(result > 0) {
                return ResultData.success(hfmdEnumClass);
            }
        } catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
        return ResultData.error(ResultCode.UNKNOW);
    }

    /**
    * 更新枚举组
    * @param hfmdEnumClass
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/updateByAjax.html")
    @ResponseBody
    public ResultData update(@ModelAttribute("hfmdEnumClass") HfmdEnumClass hfmdEnumClass) {
        logger.debug("request : {}", hfmdEnumClass);
        try {
            int result = iHfmdEnumClassSV.update(hfmdEnumClass);
            if(result > 0) {
                return ResultData.success(hfmdEnumClass);
            }
        } catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
        return ResultData.error(ResultCode.UNKNOW);
    }

    /**
    * 删除枚举组
    * @param hfmdEnumClass
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/deleteByAjax.html")
    @ResponseBody
    public ResultData delete(@ModelAttribute("hfmdEnumClass") HfmdEnumClass hfmdEnumClass) {
        logger.debug("request : {}", hfmdEnumClass);

        try {
            int result = iHfmdEnumClassSV.delete(hfmdEnumClass);
            if(result > 0) {
                return ResultData.success(hfmdEnumClass);
            }else {
                return ResultData.error(ResultCode.RECODE_IS_NOT_EXISTS);
            }
        } catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
    }
  	//getter
 	
	public IHfmdEnumClassSV getIHfmdEnumClassSV(){
		return iHfmdEnumClassSV;
	}
	//setter
	public void setIHfmdEnumClassSV(IHfmdEnumClassSV iHfmdEnumClassSV){
    	this.iHfmdEnumClassSV = iHfmdEnumClassSV;
    }
}
