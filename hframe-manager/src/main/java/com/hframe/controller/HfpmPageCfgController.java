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
import com.hframe.domain.model.HfpmPageCfg;
import com.hframe.domain.model.HfpmPageCfg_Example;
import com.hframe.service.interfaces.IHfpmPageCfgSV;

@Controller
@RequestMapping(value = "/hframe/hfpmPageCfg")
public class HfpmPageCfgController   {

	@Resource
	private IHfpmPageCfgSV iHfpmPageCfgSV;
  


    /**
    * 创建页面配置
    * @param hfpmPageCfg
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/create.html")
    public ModelAndView create(@ModelAttribute("hfpmPageCfg") HfpmPageCfg hfpmPageCfg) throws Throwable {
        ModelAndView mav = new ModelAndView();
        iHfpmPageCfgSV.create(hfpmPageCfg);
        mav.addObject("hfpmPageCfgId", hfpmPageCfg.getHfpmPageCfgId());
        mav.setViewName("/hframe/hfpmPageCfg/hframe_hfpmPageCfg_create");
        return mav;
    }

    /**
    * 查询展示页面配置
    * @param hfpmPageCfg
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/list.html")
    public ModelAndView list(@ModelAttribute("hfpmPageCfg") HfpmPageCfg hfpmPageCfg) throws Throwable {
        ModelAndView mav = new ModelAndView();
        HfpmPageCfg_Example example = new HfpmPageCfg_Example();
        ExampleUtils.parseExample(hfpmPageCfg,example);

        List< HfpmPageCfg> hfpmPageCfgList = iHfpmPageCfgSV.getHfpmPageCfgListByExample(example);

        mav.addObject("hfpmPageCfgList", hfpmPageCfgList);
        mav.setViewName("/hframe/hfpmPageCfg/hframe_hfpmPageCfg_list");
        return mav;
    }


    /**
    * 异步创建页面配置
    * @param hfpmPageCfg
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/createByAjax.html")
    @ResponseBody
    public ResultMessage createByAjax(@ModelAttribute("hfpmPageCfg") HfpmPageCfg hfpmPageCfg) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfpmPageCfgSV.create(hfpmPageCfg);
        return message;
    }

    /**
    * 异步更新页面配置
    * @param hfpmPageCfg
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/updateByAjax.html")
    @ResponseBody
    public ResultMessage updateByAjax(@ModelAttribute("hfpmPageCfg") HfpmPageCfg hfpmPageCfg) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfpmPageCfgSV.update(hfpmPageCfg);
        return message;
    }

    /**
    * 异步删除页面配置
    * @param hfpmPageCfg
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/deleteByAjax.html")
    @ResponseBody
    public ResultMessage deleteByAjax(@ModelAttribute("hfpmPageCfg") HfpmPageCfg hfpmPageCfg) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfpmPageCfgSV.delete(hfpmPageCfg);
        return message;
    }
  	//getter
 	
	public IHfpmPageCfgSV getIHfpmPageCfgSV(){
		return iHfpmPageCfgSV;
	}
	//setter
	public void setIHfpmPageCfgSV(IHfpmPageCfgSV iHfpmPageCfgSV){
    	this.iHfpmPageCfgSV = iHfpmPageCfgSV;
    }
}
