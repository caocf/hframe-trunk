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
import com.hframe.domain.model.HfcfgLoginPage;
import com.hframe.domain.model.HfcfgLoginPage_Example;
import com.hframe.service.interfaces.IHfcfgLoginPageSV;

@Controller
@RequestMapping(value = "/hframe/hfcfgLoginPage")
public class HfcfgLoginPageController   {

	@Resource
	private IHfcfgLoginPageSV iHfcfgLoginPageSV;
  


    /**
    * 创建登陆页
    * @param hfcfgLoginPage
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/create.html")
    public ModelAndView create(@ModelAttribute("hfcfgLoginPage") HfcfgLoginPage hfcfgLoginPage) throws Throwable {
        ModelAndView mav = new ModelAndView();
        iHfcfgLoginPageSV.create(hfcfgLoginPage);
        mav.addObject("hfcfgLoginPageId", hfcfgLoginPage.getHfcfgLoginPageId());
        mav.setViewName("/hframe/hfcfgLoginPage/hframe_hfcfgLoginPage_create");
        return mav;
    }

    /**
    * 查询展示登陆页
    * @param hfcfgLoginPage
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/list.html")
    public ModelAndView list(@ModelAttribute("hfcfgLoginPage") HfcfgLoginPage hfcfgLoginPage) throws Throwable {
        ModelAndView mav = new ModelAndView();
        HfcfgLoginPage_Example example = new HfcfgLoginPage_Example();
        ExampleUtils.parseExample(hfcfgLoginPage,example);

        List< HfcfgLoginPage> hfcfgLoginPageList = iHfcfgLoginPageSV.getHfcfgLoginPageListByExample(example);

        mav.addObject("hfcfgLoginPageList", hfcfgLoginPageList);
        mav.setViewName("/hframe/hfcfgLoginPage/hframe_hfcfgLoginPage_list");
        return mav;
    }


    /**
    * 异步创建登陆页
    * @param hfcfgLoginPage
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/createByAjax.html")
    @ResponseBody
    public ResultMessage createByAjax(@ModelAttribute("hfcfgLoginPage") HfcfgLoginPage hfcfgLoginPage) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfcfgLoginPageSV.create(hfcfgLoginPage);
        return message;
    }

    /**
    * 异步更新登陆页
    * @param hfcfgLoginPage
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/updateByAjax.html")
    @ResponseBody
    public ResultMessage updateByAjax(@ModelAttribute("hfcfgLoginPage") HfcfgLoginPage hfcfgLoginPage) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfcfgLoginPageSV.update(hfcfgLoginPage);
        return message;
    }

    /**
    * 异步删除登陆页
    * @param hfcfgLoginPage
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/deleteByAjax.html")
    @ResponseBody
    public ResultMessage deleteByAjax(@ModelAttribute("hfcfgLoginPage") HfcfgLoginPage hfcfgLoginPage) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfcfgLoginPageSV.delete(hfcfgLoginPage);
        return message;
    }
  	//getter
 	
	public IHfcfgLoginPageSV getIHfcfgLoginPageSV(){
		return iHfcfgLoginPageSV;
	}
	//setter
	public void setIHfcfgLoginPageSV(IHfcfgLoginPageSV iHfcfgLoginPageSV){
    	this.iHfcfgLoginPageSV = iHfcfgLoginPageSV;
    }
}
