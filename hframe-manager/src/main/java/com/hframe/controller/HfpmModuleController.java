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
import com.hframe.domain.model.HfpmModule;
import com.hframe.domain.model.HfpmModule_Example;
import com.hframe.service.interfaces.IHfpmModuleSV;

@Controller
@RequestMapping(value = "/hframe/hfpmModule")
public class HfpmModuleController   {

	@Resource
	private IHfpmModuleSV iHfpmModuleSV;
  


    /**
    * 创建模块
    * @param hfpmModule
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/create.html")
    public ModelAndView create(@ModelAttribute("hfpmModule") HfpmModule hfpmModule) throws Throwable {
        ModelAndView mav = new ModelAndView();
        iHfpmModuleSV.create(hfpmModule);
        mav.addObject("hfpmModuleId", hfpmModule.getHfpmModuleId());
        mav.setViewName("/hframe/hfpmModule/hframe_hfpmModule_create");
        return mav;
    }

    /**
    * 查询展示模块
    * @param hfpmModule
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/list.html")
    public ModelAndView list(@ModelAttribute("hfpmModule") HfpmModule hfpmModule) throws Throwable {
        ModelAndView mav = new ModelAndView();
        HfpmModule_Example example = new HfpmModule_Example();
        ExampleUtils.parseExample(hfpmModule,example);

        List< HfpmModule> hfpmModuleList = iHfpmModuleSV.getHfpmModuleListByExample(example);

        mav.addObject("hfpmModuleList", hfpmModuleList);
        mav.setViewName("/hframe/hfpmModule/hframe_hfpmModule_list");
        return mav;
    }


    /**
    * 异步创建模块
    * @param hfpmModule
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/createByAjax.html")
    @ResponseBody
    public ResultMessage createByAjax(@ModelAttribute("hfpmModule") HfpmModule hfpmModule) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfpmModuleSV.create(hfpmModule);
        return message;
    }

    /**
    * 异步更新模块
    * @param hfpmModule
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/updateByAjax.html")
    @ResponseBody
    public ResultMessage updateByAjax(@ModelAttribute("hfpmModule") HfpmModule hfpmModule) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfpmModuleSV.update(hfpmModule);
        return message;
    }

    /**
    * 异步删除模块
    * @param hfpmModule
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/deleteByAjax.html")
    @ResponseBody
    public ResultMessage deleteByAjax(@ModelAttribute("hfpmModule") HfpmModule hfpmModule) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfpmModuleSV.delete(hfpmModule);
        return message;
    }
  	//getter
 	
	public IHfpmModuleSV getIHfpmModuleSV(){
		return iHfpmModuleSV;
	}
	//setter
	public void setIHfpmModuleSV(IHfpmModuleSV iHfpmModuleSV){
    	this.iHfpmModuleSV = iHfpmModuleSV;
    }
}
