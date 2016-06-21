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
import com.hframe.domain.model.HfpmPageEntityRel;
import com.hframe.domain.model.HfpmPageEntityRel_Example;
import com.hframe.service.interfaces.IHfpmPageEntityRelSV;

@Controller
@RequestMapping(value = "/hframe/hfpmPageEntityRel")
public class HfpmPageEntityRelController   {
    private static final Logger logger = LoggerFactory.getLogger(HfpmPageEntityRelController.class);

	@Resource
	private IHfpmPageEntityRelSV iHfpmPageEntityRelSV;
  




    /**
     * 查询展示页面关联实体列表
     * @param hfpmPageEntityRel
     * @return
     * @throws Throwable
     */
    @RequestMapping(value = "/queryListByAjax.json")
    @ResponseBody
    public ResultData list(@ModelAttribute("hfpmPageEntityRel") HfpmPageEntityRel hfpmPageEntityRel,
                                      @ModelAttribute("example") HfpmPageEntityRel_Example example, Pagination pagination){
        logger.debug("request : {},{},{}", hfpmPageEntityRel, example, pagination);
        try{
            ExampleUtils.parseExample(hfpmPageEntityRel, example);
            //设置分页信息
            example.setLimitStart(pagination.getStartIndex());
            example.setLimitEnd(pagination.getEndIndex());

            final List< HfpmPageEntityRel> list = iHfpmPageEntityRelSV.getHfpmPageEntityRelListByExample(example);
            pagination.setTotalCount(iHfpmPageEntityRelSV.getHfpmPageEntityRelCountByExample(example));

            return ResultData.success().add("list",list).add("pagination",pagination);
        }catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
    }

    /**
     * 查询展示页面关联实体明细
     * @param hfpmPageEntityRel
     * @return
     * @throws Throwable
     */
    @RequestMapping(value = "/queryOneByAjax.json")
    @ResponseBody
    public ResultData detail(@ModelAttribute("hfpmPageEntityRel") HfpmPageEntityRel hfpmPageEntityRel){
        logger.debug("request : {},{}", hfpmPageEntityRel.getHfpmPageEntityRelId(), hfpmPageEntityRel);
        try{
            HfpmPageEntityRel result = iHfpmPageEntityRelSV.getHfpmPageEntityRelByPK(hfpmPageEntityRel.getHfpmPageEntityRelId());
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
    * 创建页面关联实体
    * @param hfpmPageEntityRel
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/createByAjax.json")
    @ResponseBody
    public ResultData create(@ModelAttribute("hfpmPageEntityRel") HfpmPageEntityRel hfpmPageEntityRel) {
        logger.debug("request : {}", hfpmPageEntityRel);
        try {
            int result = iHfpmPageEntityRelSV.create(hfpmPageEntityRel);
            if(result > 0) {
                return ResultData.success(hfpmPageEntityRel);
            }
        } catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
        return ResultData.error(ResultCode.UNKNOW);
    }

    /**
    * 更新页面关联实体
    * @param hfpmPageEntityRel
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/updateByAjax.json")
    @ResponseBody
    public ResultData update(@ModelAttribute("hfpmPageEntityRel") HfpmPageEntityRel hfpmPageEntityRel) {
        logger.debug("request : {}", hfpmPageEntityRel);
        try {
            int result = iHfpmPageEntityRelSV.update(hfpmPageEntityRel);
            if(result > 0) {
                return ResultData.success(hfpmPageEntityRel);
            }
        } catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
        return ResultData.error(ResultCode.UNKNOW);
    }

    /**
    * 删除页面关联实体
    * @param hfpmPageEntityRel
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/deleteByAjax.json")
    @ResponseBody
    public ResultData delete(@ModelAttribute("hfpmPageEntityRel") HfpmPageEntityRel hfpmPageEntityRel) {
        logger.debug("request : {}", hfpmPageEntityRel);

        try {
            int result = iHfpmPageEntityRelSV.delete(hfpmPageEntityRel);
            if(result > 0) {
                return ResultData.success(hfpmPageEntityRel);
            }else {
                return ResultData.error(ResultCode.RECODE_IS_NOT_EXISTS);
            }
        } catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
    }
  	//getter
 	
	public IHfpmPageEntityRelSV getIHfpmPageEntityRelSV(){
		return iHfpmPageEntityRelSV;
	}
	//setter
	public void setIHfpmPageEntityRelSV(IHfpmPageEntityRelSV iHfpmPageEntityRelSV){
    	this.iHfpmPageEntityRelSV = iHfpmPageEntityRelSV;
    }
}
