package com.hframe.controller;

import com.hframework.common.util.ExampleUtils;
import com.hframe.controller.bean.ResultMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import java.util.*;
import com.hframe.domain.model.HfpmPageComponent;
import com.hframe.domain.model.HfpmPageComponent_Example;
import com.hframe.service.interfaces.IHfpmPageComponentSV;

@Controller
@RequestMapping(value = "/hframe/hfpmPageComponent")
public class HfpmPageComponentController   {

	@Resource
	private IHfpmPageComponentSV iHfpmPageComponentSV;
  


    /**
    * 创建页面组件
    * @param hfpmPageComponent
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/create.html")
    public ModelAndView create(@ModelAttribute("hfpmPageComponent") HfpmPageComponent hfpmPageComponent) throws Throwable {
        ModelAndView mav = new ModelAndView();
        iHfpmPageComponentSV.create(hfpmPageComponent);
        mav.addObject("hfpmPageComponentId", hfpmPageComponent.getHfpmPageComponentId());
        mav.setViewName("/hframe/hfpmPageComponent/hframe_hfpmPageComponent_create");
        return mav;
    }

    /**
    * 查询展示页面组件
    * @param hfpmPageComponent
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/list.html")
    public ModelAndView list(@ModelAttribute("hfpmPageComponent") HfpmPageComponent hfpmPageComponent) throws Throwable {
        ModelAndView mav = new ModelAndView();
        HfpmPageComponent_Example example = new HfpmPageComponent_Example();
        ExampleUtils.parseExample(hfpmPageComponent,example);

        List< HfpmPageComponent> hfpmPageComponentList = iHfpmPageComponentSV.getHfpmPageComponentListByExample(example);

        mav.addObject("hfpmPageComponentList", hfpmPageComponentList);
        mav.setViewName("/hframe/hfpmPageComponent/hframe_hfpmPageComponent_list");
        return mav;
    }


    /**
    * 异步创建页面组件
    * @param hfpmPageComponent
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/createByAjax.html")
    @ResponseBody
    public ResultMessage createByAjax(@ModelAttribute("hfpmPageComponent") HfpmPageComponent hfpmPageComponent) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfpmPageComponentSV.create(hfpmPageComponent);
        return message;
    }

    /**
    * 异步更新页面组件
    * @param hfpmPageComponent
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/updateByAjax.html")
    @ResponseBody
    public ResultMessage updateByAjax(@ModelAttribute("hfpmPageComponent") HfpmPageComponent hfpmPageComponent) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfpmPageComponentSV.update(hfpmPageComponent);
        return message;
    }

    /**
    * 异步删除页面组件
    * @param hfpmPageComponent
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/deleteByAjax.html")
    @ResponseBody
    public ResultMessage deleteByAjax(@ModelAttribute("hfpmPageComponent") HfpmPageComponent hfpmPageComponent) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfpmPageComponentSV.delete(hfpmPageComponent);
        return message;
    }
  	//getter
 	
	public IHfpmPageComponentSV getIHfpmPageComponentSV(){
		return iHfpmPageComponentSV;
	}
	//setter
	public void setIHfpmPageComponentSV(IHfpmPageComponentSV iHfpmPageComponentSV){
    	this.iHfpmPageComponentSV = iHfpmPageComponentSV;
    }
}
