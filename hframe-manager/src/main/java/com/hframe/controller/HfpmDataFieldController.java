package com.hframe.controller;

import com.hframe.domain.model.HfpmDataField;
import com.hframe.domain.model.HfpmDataField_Example;
import com.hframe.service.interfaces.IHfpmDataFieldSV;
import com.hframework.beans.controller.Pagination;
import com.hframework.beans.controller.ResultCode;
import com.hframework.beans.controller.ResultData;
import com.hframework.common.helper.ControllerHelper;
import com.hframework.common.util.ExampleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/hframe/hfpmDataField")
public class HfpmDataFieldController   {
    private static final Logger logger = LoggerFactory.getLogger(HfpmDataFieldController.class);

	@Resource
	private IHfpmDataFieldSV iHfpmDataFieldSV;
  




    @InitBinder
    protected void initBinder(HttpServletRequest request,
        ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CustomDateEditor editor = new CustomDateEditor(df, false);
        binder.registerCustomEditor(Date.class, editor);
    }

    /**
     * 查询展示数据列列表
     * @param hfpmDataField
     * @return
     * @throws Throwable
     */
    @RequestMapping(value = "/queryListByAjax.json")
    @ResponseBody
    public ResultData list(@ModelAttribute("hfpmDataField") HfpmDataField hfpmDataField,
                                      @ModelAttribute("example") HfpmDataField_Example example, Pagination pagination){
        logger.debug("request : {},{},{}", hfpmDataField, example, pagination);
        try{
            ExampleUtils.parseExample(hfpmDataField, example);
            //设置分页信息
            example.setLimitStart(pagination.getStartIndex());
            example.setLimitEnd(pagination.getEndIndex());

            final List< HfpmDataField> list = iHfpmDataFieldSV.getHfpmDataFieldListByExample(example);
            pagination.setTotalCount(iHfpmDataFieldSV.getHfpmDataFieldCountByExample(example));

            return ResultData.success().add("list",list).add("pagination",pagination);
        }catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
    }

    /**
     * 查询展示数据列明细
     * @param hfpmDataField
     * @return
     * @throws Throwable
     */
    @RequestMapping(value = "/queryOneByAjax.json")
    @ResponseBody
    public ResultData detail(@ModelAttribute("hfpmDataField") HfpmDataField hfpmDataField){
        logger.debug("request : {},{}", hfpmDataField.getHfpmDataFieldId(), hfpmDataField);
        try{
            HfpmDataField result = iHfpmDataFieldSV.getHfpmDataFieldByPK(hfpmDataField.getHfpmDataFieldId());
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
    * 创建数据列
    * @param hfpmDataField
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/createByAjax.json")
    @ResponseBody
    public ResultData create(@ModelAttribute("hfpmDataField") HfpmDataField hfpmDataField) {
        logger.debug("request : {}", hfpmDataField);
        try {
            int result = iHfpmDataFieldSV.create(hfpmDataField);
            if(result > 0) {
                return ResultData.success(hfpmDataField);
            }
        } catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
        return ResultData.error(ResultCode.UNKNOW);
    }

    /**
    * 批量维护数据列
    * @param hfpmDataFields
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/createsByAjax.json")
    @ResponseBody
    public ResultData batchCreate(@RequestBody HfpmDataField[] hfpmDataFields) {
        logger.debug("request : {}", hfpmDataFields);

        ControllerHelper.reorderProperty(hfpmDataFields);

        try {
            iHfpmDataFieldSV.batchOperate(hfpmDataFields);
        } catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
        return ResultData.error(ResultCode.UNKNOW);
    }

    /**
    * 更新数据列
    * @param hfpmDataField
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/updateByAjax.json")
    @ResponseBody
    public ResultData update(@ModelAttribute("hfpmDataField") HfpmDataField hfpmDataField) {
        logger.debug("request : {}", hfpmDataField);
        try {
            int result = iHfpmDataFieldSV.update(hfpmDataField);
            if(result > 0) {
                return ResultData.success(hfpmDataField);
            }
        } catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
        return ResultData.error(ResultCode.UNKNOW);
    }

    /**
    * 删除数据列
    * @param hfpmDataField
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/deleteByAjax.json")
    @ResponseBody
    public ResultData delete(@ModelAttribute("hfpmDataField") HfpmDataField hfpmDataField) {
        logger.debug("request : {}", hfpmDataField);

        try {
            int result = iHfpmDataFieldSV.delete(hfpmDataField);
            if(result > 0) {
                return ResultData.success(hfpmDataField);
            }else {
                return ResultData.error(ResultCode.RECODE_IS_NOT_EXISTS);
            }
        } catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
    }
  	//getter
 	
	public IHfpmDataFieldSV getIHfpmDataFieldSV(){
		return iHfpmDataFieldSV;
	}
	//setter
	public void setIHfpmDataFieldSV(IHfpmDataFieldSV iHfpmDataFieldSV){
    	this.iHfpmDataFieldSV = iHfpmDataFieldSV;
    }
}
