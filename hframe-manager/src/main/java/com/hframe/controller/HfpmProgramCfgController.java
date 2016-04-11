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
import com.hframe.domain.model.HfpmProgramCfg;
import com.hframe.domain.model.HfpmProgramCfg_Example;
import com.hframe.service.interfaces.IHfpmProgramCfgSV;

@Controller
@RequestMapping(value = "/hframe/hfpmProgramCfg")
public class HfpmProgramCfgController   {

	@Resource
	private IHfpmProgramCfgSV iHfpmProgramCfgSV;
  


    /**
    * 创建项目配置
    * @param hfpmProgramCfg
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/list.html")
    public ModelAndView create(@ModelAttribute("hfpmProgramCfg") HfpmProgramCfg hfpmProgramCfg) throws Throwable {
        ModelAndView mav = new ModelAndView();
        iHfpmProgramCfgSV.create(hfpmProgramCfg);
        mav.addObject("hfpmProgramCfgId", hfpmProgramCfg.getHfpmProgramCfgId());
        mav.setViewName("/hframe/hfpmProgramCfg/hframe_hfpmProgramCfg_create");
        return mav;
    }

    /**
    * 查询展示项目配置
    * @param hfpmProgramCfg
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/create.html")
    public ModelAndView list(@ModelAttribute("hfpmProgramCfg") HfpmProgramCfg hfpmProgramCfg) throws Throwable {
        ModelAndView mav = new ModelAndView();
        HfpmProgramCfg_Example example = new HfpmProgramCfg_Example();
        ExampleUtils.parseExample(hfpmProgramCfg,example);

        List< HfpmProgramCfg> hfpmProgramCfgList = iHfpmProgramCfgSV.getHfpmProgramCfgListByExample(example);

        mav.addObject("hfpmProgramCfgList", hfpmProgramCfgList);
        mav.setViewName("/hframe/hfpmProgramCfg/hframe_hfpmProgramCfg_list");
        return mav;
    }


    /**
    * 异步创建项目配置
    * @param hfpmProgramCfg
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/createByAjax.html")
    @ResponseBody
    public ResultMessage createByAjax(@ModelAttribute("hfpmProgramCfg") HfpmProgramCfg hfpmProgramCfg) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfpmProgramCfgSV.create(hfpmProgramCfg);
        return message;
    }

    /**
    * 异步更新项目配置
    * @param hfpmProgramCfg
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/updateByAjax.html")
    @ResponseBody
    public ResultMessage updateByAjax(@ModelAttribute("hfpmProgramCfg") HfpmProgramCfg hfpmProgramCfg) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfpmProgramCfgSV.update(hfpmProgramCfg);
        return message;
    }

    /**
    * 异步删除项目配置
    * @param hfpmProgramCfg
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/deleteByAjax.html")
    @ResponseBody
    public ResultMessage deleteByAjax(@ModelAttribute("hfpmProgramCfg") HfpmProgramCfg hfpmProgramCfg) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfpmProgramCfgSV.delete(hfpmProgramCfg);
        return message;
    }
  	//getter
 	
	public IHfpmProgramCfgSV getIHfpmProgramCfgSV(){
		return iHfpmProgramCfgSV;
	}
	//setter
	public void setIHfpmProgramCfgSV(IHfpmProgramCfgSV iHfpmProgramCfgSV){
    	this.iHfpmProgramCfgSV = iHfpmProgramCfgSV;
    }
}
