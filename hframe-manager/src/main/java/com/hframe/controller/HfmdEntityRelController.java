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
import com.hframe.domain.model.HfmdEntityRel;
import com.hframe.domain.model.HfmdEntityRel_Example;
import com.hframe.service.interfaces.IHfmdEntityRelSV;

@Controller
@RequestMapping(value = "/hframe/hfmdEntityRel")
public class HfmdEntityRelController   {
    private static final Logger logger = LoggerFactory.getLogger(HfmdEntityRelController.class);

	@Resource
	private IHfmdEntityRelSV iHfmdEntityRelSV;
  




    /**
     * 查询展示实体关系列表
     * @param hfmdEntityRel
     * @return
     * @throws Throwable
     */
    @RequestMapping(value = "/queryListByAjax.json")
    @ResponseBody
    public ResultData list(@ModelAttribute("hfmdEntityRel") HfmdEntityRel hfmdEntityRel,
                                      @ModelAttribute("example") HfmdEntityRel_Example example, Pagination pagination){
        logger.debug("request : {},{},{}", hfmdEntityRel, example, pagination);
        try{
            ExampleUtils.parseExample(hfmdEntityRel, example);
            //设置分页信息
            example.setLimitStart(pagination.getStartIndex());
            example.setLimitEnd(pagination.getEndIndex());

            final List< HfmdEntityRel> list = iHfmdEntityRelSV.getHfmdEntityRelListByExample(example);
            pagination.setTotalCount(iHfmdEntityRelSV.getHfmdEntityRelCountByExample(example));

            return ResultData.success().add("list",list).add("pagination",pagination);
        }catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
    }

    /**
     * 查询展示实体关系明细
     * @param hfmdEntityRel
     * @return
     * @throws Throwable
     */
    @RequestMapping(value = "/queryOneByAjax.json")
    @ResponseBody
    public ResultData detail(@ModelAttribute("hfmdEntityRel") HfmdEntityRel hfmdEntityRel){
        logger.debug("request : {},{}", hfmdEntityRel.getHfmdEntityRelId(), hfmdEntityRel);
        try{
            HfmdEntityRel result = iHfmdEntityRelSV.getHfmdEntityRelByPK(hfmdEntityRel.getHfmdEntityRelId());
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
    * 创建实体关系
    * @param hfmdEntityRel
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/createByAjax.json")
    @ResponseBody
    public ResultData create(@ModelAttribute("hfmdEntityRel") HfmdEntityRel hfmdEntityRel) {
        logger.debug("request : {}", hfmdEntityRel);
        try {
            int result = iHfmdEntityRelSV.create(hfmdEntityRel);
            if(result > 0) {
                return ResultData.success(hfmdEntityRel);
            }
        } catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
        return ResultData.error(ResultCode.UNKNOW);
    }

    /**
    * 更新实体关系
    * @param hfmdEntityRel
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/updateByAjax.json")
    @ResponseBody
    public ResultData update(@ModelAttribute("hfmdEntityRel") HfmdEntityRel hfmdEntityRel) {
        logger.debug("request : {}", hfmdEntityRel);
        try {
            int result = iHfmdEntityRelSV.update(hfmdEntityRel);
            if(result > 0) {
                return ResultData.success(hfmdEntityRel);
            }
        } catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
        return ResultData.error(ResultCode.UNKNOW);
    }

    /**
    * 删除实体关系
    * @param hfmdEntityRel
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/deleteByAjax.json")
    @ResponseBody
    public ResultData delete(@ModelAttribute("hfmdEntityRel") HfmdEntityRel hfmdEntityRel) {
        logger.debug("request : {}", hfmdEntityRel);

        try {
            int result = iHfmdEntityRelSV.delete(hfmdEntityRel);
            if(result > 0) {
                return ResultData.success(hfmdEntityRel);
            }else {
                return ResultData.error(ResultCode.RECODE_IS_NOT_EXISTS);
            }
        } catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
    }
  	//getter
 	
	public IHfmdEntityRelSV getIHfmdEntityRelSV(){
		return iHfmdEntityRelSV;
	}
	//setter
	public void setIHfmdEntityRelSV(IHfmdEntityRelSV iHfmdEntityRelSV){
    	this.iHfmdEntityRelSV = iHfmdEntityRelSV;
    }
}
