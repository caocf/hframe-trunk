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
import com.hframe.domain.model.HfcfgProgramTemplate;
import com.hframe.domain.model.HfcfgProgramTemplate_Example;
import com.hframe.service.interfaces.IHfcfgProgramTemplateSV;

@Controller
@RequestMapping(value = "/hframe/hfcfgProgramTemplate")
public class HfcfgProgramTemplateController   {

	@Resource
	private IHfcfgProgramTemplateSV iHfcfgProgramTemplateSV;
  


    /**
    * 创建项目模板
    * @param hfcfgProgramTemplate
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/create.html")
    public ModelAndView create(@ModelAttribute("hfcfgProgramTemplate") HfcfgProgramTemplate hfcfgProgramTemplate) throws Throwable {
        ModelAndView mav = new ModelAndView();
        iHfcfgProgramTemplateSV.create(hfcfgProgramTemplate);
        mav.addObject("hfcfgProgramTemplateId", hfcfgProgramTemplate.getHfcfgProgramTemplateId());
        mav.setViewName("/hframe/hfcfgProgramTemplate/hframe_hfcfgProgramTemplate_create");
        return mav;
    }

    /**
    * 查询展示项目模板
    * @param hfcfgProgramTemplate
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/list.html")
    public ModelAndView list(@ModelAttribute("hfcfgProgramTemplate") HfcfgProgramTemplate hfcfgProgramTemplate) throws Throwable {
        ModelAndView mav = new ModelAndView();
        HfcfgProgramTemplate_Example example = new HfcfgProgramTemplate_Example();
        ExampleUtils.parseExample(hfcfgProgramTemplate,example);

        List< HfcfgProgramTemplate> hfcfgProgramTemplateList = iHfcfgProgramTemplateSV.getHfcfgProgramTemplateListByExample(example);

        mav.addObject("hfcfgProgramTemplateList", hfcfgProgramTemplateList);
        mav.setViewName("/hframe/hfcfgProgramTemplate/hframe_hfcfgProgramTemplate_list");
        return mav;
    }


    /**
    * 异步创建项目模板
    * @param hfcfgProgramTemplate
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/createByAjax.html")
    @ResponseBody
    public ResultMessage createByAjax(@ModelAttribute("hfcfgProgramTemplate") HfcfgProgramTemplate hfcfgProgramTemplate) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfcfgProgramTemplateSV.create(hfcfgProgramTemplate);
        return message;
    }

    /**
    * 异步更新项目模板
    * @param hfcfgProgramTemplate
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/updateByAjax.html")
    @ResponseBody
    public ResultMessage updateByAjax(@ModelAttribute("hfcfgProgramTemplate") HfcfgProgramTemplate hfcfgProgramTemplate) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfcfgProgramTemplateSV.update(hfcfgProgramTemplate);
        return message;
    }

    /**
    * 异步删除项目模板
    * @param hfcfgProgramTemplate
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/deleteByAjax.html")
    @ResponseBody
    public ResultMessage deleteByAjax(@ModelAttribute("hfcfgProgramTemplate") HfcfgProgramTemplate hfcfgProgramTemplate) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfcfgProgramTemplateSV.delete(hfcfgProgramTemplate);
        return message;
    }
  	//getter
 	
	public IHfcfgProgramTemplateSV getIHfcfgProgramTemplateSV(){
		return iHfcfgProgramTemplateSV;
	}
	//setter
	public void setIHfcfgProgramTemplateSV(IHfcfgProgramTemplateSV iHfcfgProgramTemplateSV){
    	this.iHfcfgProgramTemplateSV = iHfcfgProgramTemplateSV;
    }
}
