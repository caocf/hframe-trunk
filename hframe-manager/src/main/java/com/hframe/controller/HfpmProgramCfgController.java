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
import com.hframe.domain.model.HfpmProgramCfg;
import com.hframe.domain.model.HfpmProgramCfg_Example;
import com.hframe.service.interfaces.IHfpmProgramCfgSV;

@Controller
@RequestMapping(value = "/hframe/hfpmProgramCfg")
public class HfpmProgramCfgController   {
    private static final Logger logger = LoggerFactory.getLogger(HfpmProgramCfgController.class);

	@Resource
	private IHfpmProgramCfgSV iHfpmProgramCfgSV;
  




    /**
     * 查询展示项目配置列表
     * @param hfpmProgramCfg
     * @return
     * @throws Throwable
     */
    @RequestMapping(value = "/queryListByAjax.html")
    @ResponseBody
    public ResultData list(@ModelAttribute("hfpmProgramCfg") HfpmProgramCfg hfpmProgramCfg,
                                      @ModelAttribute("example") HfpmProgramCfg_Example example, Pagination pagination){
        logger.debug("request : {},{},{}", hfpmProgramCfg, example, pagination);
        try{
            ExampleUtils.parseExample(hfpmProgramCfg, example);
            //设置分页信息
            example.setLimitStart(pagination.getStartIndex());
            example.setLimitEnd(pagination.getEndIndex());

            final List< HfpmProgramCfg> list = iHfpmProgramCfgSV.getHfpmProgramCfgListByExample(example);
            pagination.setTotalCount(iHfpmProgramCfgSV.getHfpmProgramCfgCountByExample(example));

            return ResultData.success().add("list",list).add("pagination",pagination);
        }catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
    }

    /**
     * 查询展示项目配置明细
     * @param hfpmProgramCfg
     * @return
     * @throws Throwable
     */
    @RequestMapping(value = "/queryOneByAjax.html")
    @ResponseBody
    public ResultData detail(@ModelAttribute("hfpmProgramCfg") HfpmProgramCfg hfpmProgramCfg){
        logger.debug("request : {},{}", hfpmProgramCfg.getHfpmProgramCfgId(), hfpmProgramCfg);
        try{
            HfpmProgramCfg result = iHfpmProgramCfgSV.getHfpmProgramCfgByPK(hfpmProgramCfg.getHfpmProgramCfgId());
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
    * 创建项目配置
    * @param hfpmProgramCfg
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/createByAjax.html")
    @ResponseBody
    public ResultData create(@ModelAttribute("hfpmProgramCfg") HfpmProgramCfg hfpmProgramCfg) {
        logger.debug("request : {}", hfpmProgramCfg);
        try {
            int result = iHfpmProgramCfgSV.create(hfpmProgramCfg);
            if(result > 0) {
                return ResultData.success(hfpmProgramCfg);
            }
        } catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
        return ResultData.error(ResultCode.UNKNOW);
    }

    /**
    * 更新项目配置
    * @param hfpmProgramCfg
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/updateByAjax.html")
    @ResponseBody
    public ResultData update(@ModelAttribute("hfpmProgramCfg") HfpmProgramCfg hfpmProgramCfg) {
        logger.debug("request : {}", hfpmProgramCfg);
        try {
            int result = iHfpmProgramCfgSV.update(hfpmProgramCfg);
            if(result > 0) {
                return ResultData.success(hfpmProgramCfg);
            }
        } catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
        return ResultData.error(ResultCode.UNKNOW);
    }

    /**
    * 删除项目配置
    * @param hfpmProgramCfg
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/deleteByAjax.html")
    @ResponseBody
    public ResultData delete(@ModelAttribute("hfpmProgramCfg") HfpmProgramCfg hfpmProgramCfg) {
        logger.debug("request : {}", hfpmProgramCfg);

        try {
            int result = iHfpmProgramCfgSV.delete(hfpmProgramCfg);
            if(result > 0) {
                return ResultData.success(hfpmProgramCfg);
            }else {
                return ResultData.error(ResultCode.RECODE_IS_NOT_EXISTS);
            }
        } catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
    }
  	//getter
 	
	public IHfpmProgramCfgSV getIHfpmProgramCfgSV(){
		return iHfpmProgramCfgSV;
	}
	//setter
	public void setIHfpmProgramCfgSV(IHfpmProgramCfgSV iHfpmProgramCfgSV){
    	this.iHfpmProgramCfgSV = iHfpmProgramCfgSV;
    }
}
