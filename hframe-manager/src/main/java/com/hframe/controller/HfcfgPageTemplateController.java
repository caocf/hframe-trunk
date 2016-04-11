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
import com.hframe.domain.model.HfcfgPageTemplate;
import com.hframe.domain.model.HfcfgPageTemplate_Example;
import com.hframe.service.interfaces.IHfcfgPageTemplateSV;

@Controller
@RequestMapping(value = "/hframe/hfcfgPageTemplate")
public class HfcfgPageTemplateController   {

	@Resource
	private IHfcfgPageTemplateSV iHfcfgPageTemplateSV;
  


    /**
    * 创建页面模板
    * @param hfcfgPageTemplate
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/create.html")
    public ModelAndView create(@ModelAttribute("hfcfgPageTemplate") HfcfgPageTemplate hfcfgPageTemplate) throws Throwable {
        ModelAndView mav = new ModelAndView();
        iHfcfgPageTemplateSV.create(hfcfgPageTemplate);
        mav.addObject("hfcfgPageTemplateId", hfcfgPageTemplate.getHfcfgPageTemplateId());
        mav.setViewName("/hframe/hfcfgPageTemplate/hframe_hfcfgPageTemplate_create");
        return mav;
    }

    /**
    * 查询展示页面模板
    * @param hfcfgPageTemplate
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/list.html")
    public ModelAndView list(@ModelAttribute("hfcfgPageTemplate") HfcfgPageTemplate hfcfgPageTemplate) throws Throwable {
        ModelAndView mav = new ModelAndView();
        HfcfgPageTemplate_Example example = new HfcfgPageTemplate_Example();
        ExampleUtils.parseExample(hfcfgPageTemplate,example);

        List< HfcfgPageTemplate> hfcfgPageTemplateList = iHfcfgPageTemplateSV.getHfcfgPageTemplateListByExample(example);

        mav.addObject("hfcfgPageTemplateList", hfcfgPageTemplateList);
        mav.setViewName("/hframe/hfcfgPageTemplate/hframe_hfcfgPageTemplate_list");
        return mav;
    }


    /**
    * 异步创建页面模板
    * @param hfcfgPageTemplate
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/createByAjax.html")
    @ResponseBody
    public ResultMessage createByAjax(@ModelAttribute("hfcfgPageTemplate") HfcfgPageTemplate hfcfgPageTemplate) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfcfgPageTemplateSV.create(hfcfgPageTemplate);
        return message;
    }

    /**
    * 异步更新页面模板
    * @param hfcfgPageTemplate
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/updateByAjax.html")
    @ResponseBody
    public ResultMessage updateByAjax(@ModelAttribute("hfcfgPageTemplate") HfcfgPageTemplate hfcfgPageTemplate) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfcfgPageTemplateSV.update(hfcfgPageTemplate);
        return message;
    }

    /**
    * 异步删除页面模板
    * @param hfcfgPageTemplate
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/deleteByAjax.html")
    @ResponseBody
    public ResultMessage deleteByAjax(@ModelAttribute("hfcfgPageTemplate") HfcfgPageTemplate hfcfgPageTemplate) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfcfgPageTemplateSV.delete(hfcfgPageTemplate);
        return message;
    }
  	//getter
 	
	public IHfcfgPageTemplateSV getIHfcfgPageTemplateSV(){
		return iHfcfgPageTemplateSV;
	}
	//setter
	public void setIHfcfgPageTemplateSV(IHfcfgPageTemplateSV iHfcfgPageTemplateSV){
    	this.iHfcfgPageTemplateSV = iHfcfgPageTemplateSV;
    }
}
