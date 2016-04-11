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
import com.hframe.domain.model.HfpmPageEvent;
import com.hframe.domain.model.HfpmPageEvent_Example;
import com.hframe.service.interfaces.IHfpmPageEventSV;

@Controller
@RequestMapping(value = "/hframe/hfpmPageEvent")
public class HfpmPageEventController   {

	@Resource
	private IHfpmPageEventSV iHfpmPageEventSV;
  


    /**
    * 创建页面事件
    * @param hfpmPageEvent
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/create.html")
    public ModelAndView create(@ModelAttribute("hfpmPageEvent") HfpmPageEvent hfpmPageEvent) throws Throwable {
        ModelAndView mav = new ModelAndView();
        iHfpmPageEventSV.create(hfpmPageEvent);
        mav.addObject("hfpmPageEventId", hfpmPageEvent.getHfpmPageEventId());
        mav.setViewName("/hframe/hfpmPageEvent/hframe_hfpmPageEvent_create");
        return mav;
    }

    /**
    * 查询展示页面事件
    * @param hfpmPageEvent
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/list.html")
    public ModelAndView list(@ModelAttribute("hfpmPageEvent") HfpmPageEvent hfpmPageEvent) throws Throwable {
        ModelAndView mav = new ModelAndView();
        HfpmPageEvent_Example example = new HfpmPageEvent_Example();
        ExampleUtils.parseExample(hfpmPageEvent,example);

        List< HfpmPageEvent> hfpmPageEventList = iHfpmPageEventSV.getHfpmPageEventListByExample(example);

        mav.addObject("hfpmPageEventList", hfpmPageEventList);
        mav.setViewName("/hframe/hfpmPageEvent/hframe_hfpmPageEvent_list");
        return mav;
    }


    /**
    * 异步创建页面事件
    * @param hfpmPageEvent
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/createByAjax.html")
    @ResponseBody
    public ResultMessage createByAjax(@ModelAttribute("hfpmPageEvent") HfpmPageEvent hfpmPageEvent) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfpmPageEventSV.create(hfpmPageEvent);
        return message;
    }

    /**
    * 异步更新页面事件
    * @param hfpmPageEvent
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/updateByAjax.html")
    @ResponseBody
    public ResultMessage updateByAjax(@ModelAttribute("hfpmPageEvent") HfpmPageEvent hfpmPageEvent) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfpmPageEventSV.update(hfpmPageEvent);
        return message;
    }

    /**
    * 异步删除页面事件
    * @param hfpmPageEvent
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/deleteByAjax.html")
    @ResponseBody
    public ResultMessage deleteByAjax(@ModelAttribute("hfpmPageEvent") HfpmPageEvent hfpmPageEvent) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfpmPageEventSV.delete(hfpmPageEvent);
        return message;
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
