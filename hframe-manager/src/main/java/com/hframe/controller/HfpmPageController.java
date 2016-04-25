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
import com.hframe.domain.model.HfpmPage;
import com.hframe.domain.model.HfpmPage_Example;
import com.hframe.service.interfaces.IHfpmPageSV;

@Controller
@RequestMapping(value = "/hframe/hfpmPage")
public class HfpmPageController   {

	@Resource
	private IHfpmPageSV iHfpmPageSV;
  


    /**
    * 创建页面
    * @param hfpmPage
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/create.html")
    public ModelAndView create(@ModelAttribute("hfpmPage") HfpmPage hfpmPage) throws Throwable {
        ModelAndView mav = new ModelAndView();
        iHfpmPageSV.create(hfpmPage);
        mav.addObject("hfpmPageId", hfpmPage.getHfpmPageId());
        mav.setViewName("/hframe/hfpmPage/hframe_hfpmPage_create");
        return mav;
    }

    /**
    * 查询展示页面
    * @param hfpmPage
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/list.html")
    public ModelAndView list(@ModelAttribute("hfpmPage") HfpmPage hfpmPage) throws Throwable {
        ModelAndView mav = new ModelAndView();
        HfpmPage_Example example = new HfpmPage_Example();
        ExampleUtils.parseExample(hfpmPage,example);

        List< HfpmPage> hfpmPageList = iHfpmPageSV.getHfpmPageListByExample(example);

        mav.addObject("hfpmPageList", hfpmPageList);
        mav.setViewName("/hframe/hfpmPage/hframe_hfpmPage_list");
        return mav;
    }


    /**
    * 异步创建页面
    * @param hfpmPage
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/createByAjax.html")
    @ResponseBody
    public ResultMessage createByAjax(@ModelAttribute("hfpmPage") HfpmPage hfpmPage) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfpmPageSV.create(hfpmPage);
        return message;
    }

    /**
    * 异步更新页面
    * @param hfpmPage
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/updateByAjax.html")
    @ResponseBody
    public ResultMessage updateByAjax(@ModelAttribute("hfpmPage") HfpmPage hfpmPage) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfpmPageSV.update(hfpmPage);
        return message;
    }

    /**
    * 异步删除页面
    * @param hfpmPage
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/deleteByAjax.html")
    @ResponseBody
    public ResultMessage deleteByAjax(@ModelAttribute("hfpmPage") HfpmPage hfpmPage) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfpmPageSV.delete(hfpmPage);
        return message;
    }
  	//getter
 	
	public IHfpmPageSV getIHfpmPageSV(){
		return iHfpmPageSV;
	}
	//setter
	public void setIHfpmPageSV(IHfpmPageSV iHfpmPageSV){
    	this.iHfpmPageSV = iHfpmPageSV;
    }
}
