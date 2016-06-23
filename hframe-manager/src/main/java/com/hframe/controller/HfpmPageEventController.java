package com.hframe.controller;

import com.hframe.domain.model.HfpmPageEvent;
import com.hframe.domain.model.HfpmPageEvent_Example;
import com.hframe.service.interfaces.IHfpmPageEventSV;
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
@RequestMapping(value = "/hframe/hfpmPageEvent")
public class HfpmPageEventController   {
    private static final Logger logger = LoggerFactory.getLogger(HfpmPageEventController.class);

	@Resource
	private IHfpmPageEventSV iHfpmPageEventSV;
  




    @InitBinder
    protected void initBinder(HttpServletRequest request,
        ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CustomDateEditor editor = new CustomDateEditor(df, false);
        binder.registerCustomEditor(Date.class, editor);
    }

    /**
     * 查询展示页面事件列表
     * @param hfpmPageEvent
     * @return
     * @throws Throwable
     */
    @RequestMapping(value = "/queryListByAjax.json")
    @ResponseBody
    public ResultData list(@ModelAttribute("hfpmPageEvent") HfpmPageEvent hfpmPageEvent,
                                      @ModelAttribute("example") HfpmPageEvent_Example example, Pagination pagination){
        logger.debug("request : {},{},{}", hfpmPageEvent, example, pagination);
        try{
            ExampleUtils.parseExample(hfpmPageEvent, example);
            //设置分页信息
            example.setLimitStart(pagination.getStartIndex());
            example.setLimitEnd(pagination.getEndIndex());

            final List< HfpmPageEvent> list = iHfpmPageEventSV.getHfpmPageEventListByExample(example);
            pagination.setTotalCount(iHfpmPageEventSV.getHfpmPageEventCountByExample(example));

            return ResultData.success().add("list",list).add("pagination",pagination);
        }catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
    }

    /**
     * 查询展示页面事件明细
     * @param hfpmPageEvent
     * @return
     * @throws Throwable
     */
    @RequestMapping(value = "/queryOneByAjax.json")
    @ResponseBody
    public ResultData detail(@ModelAttribute("hfpmPageEvent") HfpmPageEvent hfpmPageEvent){
        logger.debug("request : {},{}", hfpmPageEvent.getHfpmPageEventId(), hfpmPageEvent);
        try{
            HfpmPageEvent result = iHfpmPageEventSV.getHfpmPageEventByPK(hfpmPageEvent.getHfpmPageEventId());
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
    * 创建页面事件
    * @param hfpmPageEvent
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/createByAjax.json")
    @ResponseBody
    public ResultData create(@ModelAttribute("hfpmPageEvent") HfpmPageEvent hfpmPageEvent) {
        logger.debug("request : {}", hfpmPageEvent);
        try {
            int result = iHfpmPageEventSV.create(hfpmPageEvent);
            if(result > 0) {
                return ResultData.success(hfpmPageEvent);
            }
        } catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
        return ResultData.error(ResultCode.UNKNOW);
    }

    /**
    * 批量维护页面事件
    * @param hfpmPageEvents
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/createsByAjax.json")
    @ResponseBody
    public ResultData batchCreate(@RequestBody HfpmPageEvent[] hfpmPageEvents) {
        logger.debug("request : {}", hfpmPageEvents);

        ControllerHelper.reorderProperty(hfpmPageEvents);

        try {
            iHfpmPageEventSV.batchOperate(hfpmPageEvents);
        } catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
        return ResultData.error(ResultCode.UNKNOW);
    }

    /**
    * 更新页面事件
    * @param hfpmPageEvent
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/updateByAjax.json")
    @ResponseBody
    public ResultData update(@ModelAttribute("hfpmPageEvent") HfpmPageEvent hfpmPageEvent) {
        logger.debug("request : {}", hfpmPageEvent);
        try {
            int result = iHfpmPageEventSV.update(hfpmPageEvent);
            if(result > 0) {
                return ResultData.success(hfpmPageEvent);
            }
        } catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
        return ResultData.error(ResultCode.UNKNOW);
    }

    /**
    * 删除页面事件
    * @param hfpmPageEvent
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/deleteByAjax.json")
    @ResponseBody
    public ResultData delete(@ModelAttribute("hfpmPageEvent") HfpmPageEvent hfpmPageEvent) {
        logger.debug("request : {}", hfpmPageEvent);

        try {
            int result = iHfpmPageEventSV.delete(hfpmPageEvent);
            if(result > 0) {
                return ResultData.success(hfpmPageEvent);
            }else {
                return ResultData.error(ResultCode.RECODE_IS_NOT_EXISTS);
            }
        } catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
    }
  	//getter
 	
	public IHfpmPageEventSV getIHfpmPageEventSV(){
		return iHfpmPageEventSV;
	}
	//setter
	public void setIHfpmPageEventSV(IHfpmPageEventSV iHfpmPageEventSV){
    	this.iHfpmPageEventSV = iHfpmPageEventSV;
    }
}
