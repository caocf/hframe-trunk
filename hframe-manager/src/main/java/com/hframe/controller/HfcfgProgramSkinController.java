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
import com.hframe.domain.model.HfcfgProgramSkin;
import com.hframe.domain.model.HfcfgProgramSkin_Example;
import com.hframe.service.interfaces.IHfcfgProgramSkinSV;

@Controller
@RequestMapping(value = "/hframe/hfcfgProgramSkin")
public class HfcfgProgramSkinController   {
    private static final Logger logger = LoggerFactory.getLogger(HfcfgProgramSkinController.class);

	@Resource
	private IHfcfgProgramSkinSV iHfcfgProgramSkinSV;
  




    /**
     * 查询展示皮肤列表
     * @param hfcfgProgramSkin
     * @return
     * @throws Throwable
     */
    @RequestMapping(value = "/queryListByAjax.html")
    @ResponseBody
    public ResultData list(@ModelAttribute("hfcfgProgramSkin") HfcfgProgramSkin hfcfgProgramSkin,
                                      @ModelAttribute("example") HfcfgProgramSkin_Example example, Pagination pagination){
        logger.debug("request : {},{},{}", hfcfgProgramSkin, example, pagination);
        try{
            ExampleUtils.parseExample(hfcfgProgramSkin, example);
            //设置分页信息
            example.setLimitStart(pagination.getStartIndex());
            example.setLimitEnd(pagination.getEndIndex());

            final List< HfcfgProgramSkin> list = iHfcfgProgramSkinSV.getHfcfgProgramSkinListByExample(example);
            pagination.setTotalCount(iHfcfgProgramSkinSV.getHfcfgProgramSkinCountByExample(example));

            return ResultData.success().add("list",list).add("pagination",pagination);
        }catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
    }

    /**
     * 查询展示皮肤明细
     * @param hfcfgProgramSkin
     * @return
     * @throws Throwable
     */
    @RequestMapping(value = "/queryOneByAjax.html")
    @ResponseBody
    public ResultData detail(@ModelAttribute("hfcfgProgramSkin") HfcfgProgramSkin hfcfgProgramSkin){
        logger.debug("request : {},{}", hfcfgProgramSkin.getHfcfgProgramSkinId(), hfcfgProgramSkin);
        try{
            HfcfgProgramSkin result = iHfcfgProgramSkinSV.getHfcfgProgramSkinByPK(hfcfgProgramSkin.getHfcfgProgramSkinId());
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
    * 创建皮肤
    * @param hfcfgProgramSkin
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/createByAjax.html")
    @ResponseBody
    public ResultData create(@ModelAttribute("hfcfgProgramSkin") HfcfgProgramSkin hfcfgProgramSkin) {
        logger.debug("request : {}", hfcfgProgramSkin);
        try {
            int result = iHfcfgProgramSkinSV.create(hfcfgProgramSkin);
            if(result > 0) {
                return ResultData.success(hfcfgProgramSkin);
            }
        } catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
        return ResultData.error(ResultCode.UNKNOW);
    }

    /**
    * 更新皮肤
    * @param hfcfgProgramSkin
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/updateByAjax.html")
    @ResponseBody
    public ResultData update(@ModelAttribute("hfcfgProgramSkin") HfcfgProgramSkin hfcfgProgramSkin) {
        logger.debug("request : {}", hfcfgProgramSkin);
        try {
            int result = iHfcfgProgramSkinSV.update(hfcfgProgramSkin);
            if(result > 0) {
                return ResultData.success(hfcfgProgramSkin);
            }
        } catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
        return ResultData.error(ResultCode.UNKNOW);
    }

    /**
    * 删除皮肤
    * @param hfcfgProgramSkin
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/deleteByAjax.html")
    @ResponseBody
    public ResultData delete(@ModelAttribute("hfcfgProgramSkin") HfcfgProgramSkin hfcfgProgramSkin) {
        logger.debug("request : {}", hfcfgProgramSkin);

        try {
            int result = iHfcfgProgramSkinSV.delete(hfcfgProgramSkin);
            if(result > 0) {
                return ResultData.success(hfcfgProgramSkin);
            }else {
                return ResultData.error(ResultCode.RECODE_IS_NOT_EXISTS);
            }
        } catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
    }
  	//getter
 	
	public IHfcfgProgramSkinSV getIHfcfgProgramSkinSV(){
		return iHfcfgProgramSkinSV;
	}
	//setter
	public void setIHfcfgProgramSkinSV(IHfcfgProgramSkinSV iHfcfgProgramSkinSV){
    	this.iHfcfgProgramSkinSV = iHfcfgProgramSkinSV;
    }
}
