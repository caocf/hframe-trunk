package com.hframe.controller;

import com.hframe.common.util.ExampleUtils;
import com.hframe.controller.bean.ResultMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import java.util.*;
import com.hframe.domain.model.HfusPageEvent;
import com.hframe.domain.model.HfusPageEvent_Example;
import com.hframe.service.interfaces.IHfusPageEventSV;

@Controller
@RequestMapping(value = "/hframe/hfusPageEvent")
public class HfusPageEventController   {

	@Resource
	private IHfusPageEventSV iHfusPageEventSV;
  


    /**
    * 创建常用页面事件
    * @param hfusPageEvent
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/create.html")
    public ModelAndView create(@ModelAttribute("hfusPageEvent") HfusPageEvent hfusPageEvent) throws Throwable {
        ModelAndView mav = new ModelAndView();
        iHfusPageEventSV.create(hfusPageEvent);
        mav.addObject("hfusPageEventId", hfusPageEvent.getHfusPageEventId());
        mav.setViewName("/hframe/hfusPageEvent/hframe_hfusPageEvent_create");
        return mav;
    }

    /**
    * 查询展示常用页面事件
    * @param hfusPageEvent
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/list.html")
    public ModelAndView list(@ModelAttribute("hfusPageEvent") HfusPageEvent hfusPageEvent) throws Throwable {
        ModelAndView mav = new ModelAndView();
        HfusPageEvent_Example example = new HfusPageEvent_Example();
        ExampleUtils.parseExample(hfusPageEvent,example);

        List< HfusPageEvent> hfusPageEventList = iHfusPageEventSV.getHfusPageEventListByExample(example);

        mav.addObject("hfusPageEventList", hfusPageEventList);
        mav.setViewName("/hframe/hfusPageEvent/hframe_hfusPageEvent_list");
        return mav;
    }


    /**
    * 异步创建常用页面事件
    * @param hfusPageEvent
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/createByAjax.html")
    @ResponseBody
    public ResultMessage createByAjax(@ModelAttribute("hfusPageEvent") HfusPageEvent hfusPageEvent) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfusPageEventSV.create(hfusPageEvent);
        return message;
    }

    /**
    * 异步更新常用页面事件
    * @param hfusPageEvent
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/updateByAjax.html")
    @ResponseBody
    public ResultMessage updateByAjax(@ModelAttribute("hfusPageEvent") HfusPageEvent hfusPageEvent) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfusPageEventSV.update(hfusPageEvent);
        return message;
    }

    /**
    * 异步删除常用页面事件
    * @param hfusPageEvent
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/deleteByAjax.html")
    @ResponseBody
    public ResultMessage deleteByAjax(@ModelAttribute("hfusPageEvent") HfusPageEvent hfusPageEvent) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfusPageEventSV.delete(hfusPageEvent);
        return message;
    }
  	//getter
 	
	public IHfusPageEventSV getIHfusPageEventSV(){
		return iHfusPageEventSV;
	}
	//setter
	public void setIHfusPageEventSV(IHfusPageEventSV iHfusPageEventSV){
    	this.iHfusPageEventSV = iHfusPageEventSV;
    }
}
