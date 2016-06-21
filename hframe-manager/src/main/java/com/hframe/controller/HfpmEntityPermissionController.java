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
import com.hframe.domain.model.HfpmEntityPermission;
import com.hframe.domain.model.HfpmEntityPermission_Example;
import com.hframe.service.interfaces.IHfpmEntityPermissionSV;

@Controller
@RequestMapping(value = "/hframe/hfpmEntityPermission")
public class HfpmEntityPermissionController   {
    private static final Logger logger = LoggerFactory.getLogger(HfpmEntityPermissionController.class);

	@Resource
	private IHfpmEntityPermissionSV iHfpmEntityPermissionSV;
  




    /**
     * 查询展示实体权限列表
     * @param hfpmEntityPermission
     * @return
     * @throws Throwable
     */
    @RequestMapping(value = "/queryListByAjax.json")
    @ResponseBody
    public ResultData list(@ModelAttribute("hfpmEntityPermission") HfpmEntityPermission hfpmEntityPermission,
                                      @ModelAttribute("example") HfpmEntityPermission_Example example, Pagination pagination){
        logger.debug("request : {},{},{}", hfpmEntityPermission, example, pagination);
        try{
            ExampleUtils.parseExample(hfpmEntityPermission, example);
            //设置分页信息
            example.setLimitStart(pagination.getStartIndex());
            example.setLimitEnd(pagination.getEndIndex());

            final List< HfpmEntityPermission> list = iHfpmEntityPermissionSV.getHfpmEntityPermissionListByExample(example);
            pagination.setTotalCount(iHfpmEntityPermissionSV.getHfpmEntityPermissionCountByExample(example));

            return ResultData.success().add("list",list).add("pagination",pagination);
        }catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
    }

    /**
     * 查询展示实体权限明细
     * @param hfpmEntityPermission
     * @return
     * @throws Throwable
     */
    @RequestMapping(value = "/queryOneByAjax.json")
    @ResponseBody
    public ResultData detail(@ModelAttribute("hfpmEntityPermission") HfpmEntityPermission hfpmEntityPermission){
        logger.debug("request : {},{}", hfpmEntityPermission.getHfpmEntityPermissionId(), hfpmEntityPermission);
        try{
            HfpmEntityPermission result = iHfpmEntityPermissionSV.getHfpmEntityPermissionByPK(hfpmEntityPermission.getHfpmEntityPermissionId());
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
    * 创建实体权限
    * @param hfpmEntityPermission
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/createByAjax.json")
    @ResponseBody
    public ResultData create(@ModelAttribute("hfpmEntityPermission") HfpmEntityPermission hfpmEntityPermission) {
        logger.debug("request : {}", hfpmEntityPermission);
        try {
            int result = iHfpmEntityPermissionSV.create(hfpmEntityPermission);
            if(result > 0) {
                return ResultData.success(hfpmEntityPermission);
            }
        } catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
        return ResultData.error(ResultCode.UNKNOW);
    }

    /**
    * 更新实体权限
    * @param hfpmEntityPermission
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/updateByAjax.json")
    @ResponseBody
    public ResultData update(@ModelAttribute("hfpmEntityPermission") HfpmEntityPermission hfpmEntityPermission) {
        logger.debug("request : {}", hfpmEntityPermission);
        try {
            int result = iHfpmEntityPermissionSV.update(hfpmEntityPermission);
            if(result > 0) {
                return ResultData.success(hfpmEntityPermission);
            }
        } catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
        return ResultData.error(ResultCode.UNKNOW);
    }

    /**
    * 删除实体权限
    * @param hfpmEntityPermission
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/deleteByAjax.json")
    @ResponseBody
    public ResultData delete(@ModelAttribute("hfpmEntityPermission") HfpmEntityPermission hfpmEntityPermission) {
        logger.debug("request : {}", hfpmEntityPermission);

        try {
            int result = iHfpmEntityPermissionSV.delete(hfpmEntityPermission);
            if(result > 0) {
                return ResultData.success(hfpmEntityPermission);
            }else {
                return ResultData.error(ResultCode.RECODE_IS_NOT_EXISTS);
            }
        } catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
    }
  	//getter
 	
	public IHfpmEntityPermissionSV getIHfpmEntityPermissionSV(){
		return iHfpmEntityPermissionSV;
	}
	//setter
	public void setIHfpmEntityPermissionSV(IHfpmEntityPermissionSV iHfpmEntityPermissionSV){
    	this.iHfpmEntityPermissionSV = iHfpmEntityPermissionSV;
    }
}
