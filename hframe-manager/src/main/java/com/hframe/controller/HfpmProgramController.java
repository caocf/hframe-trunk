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
import com.hframe.domain.model.HfpmProgram;
import com.hframe.domain.model.HfpmProgram_Example;
import com.hframe.service.interfaces.IHfpmProgramSV;

@Controller
@RequestMapping(value = "/hframe/hfpmProgram")
public class HfpmProgramController   {

	@Resource
	private IHfpmProgramSV iHfpmProgramSV;
  


    /**
    * 创建项目
    * @param hfpmProgram
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/create.html")
    public ModelAndView create(@ModelAttribute("hfpmProgram") HfpmProgram hfpmProgram) throws Throwable {
        ModelAndView mav = new ModelAndView();
        iHfpmProgramSV.create(hfpmProgram);
        mav.addObject("hfpmProgramId", hfpmProgram.getHfpmProgramId());
        mav.setViewName("/hframe/hfpmProgram/hframe_hfpmProgram_create");
        return mav;
    }

    /**
    * 查询展示项目
    * @param hfpmProgram
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/list.html")
    public ModelAndView list(@ModelAttribute("hfpmProgram") HfpmProgram hfpmProgram) throws Throwable {
        ModelAndView mav = new ModelAndView();
        HfpmProgram_Example example = new HfpmProgram_Example();
        ExampleUtils.parseExample(hfpmProgram,example);

        List< HfpmProgram> hfpmProgramList = iHfpmProgramSV.getHfpmProgramListByExample(example);

        mav.addObject("hfpmProgramList", hfpmProgramList);
        mav.setViewName("/hframe/hfpmProgram/hframe_hfpmProgram_list");
        return mav;
    }


    /**
    * 异步创建项目
    * @param hfpmProgram
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/createByAjax.html")
    @ResponseBody
    public ResultMessage createByAjax(@ModelAttribute("hfpmProgram") HfpmProgram hfpmProgram) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfpmProgramSV.create(hfpmProgram);
        return message;
    }

    /**
    * 异步更新项目
    * @param hfpmProgram
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/updateByAjax.html")
    @ResponseBody
    public ResultMessage updateByAjax(@ModelAttribute("hfpmProgram") HfpmProgram hfpmProgram) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfpmProgramSV.update(hfpmProgram);
        return message;
    }

    /**
    * 异步删除项目
    * @param hfpmProgram
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/deleteByAjax.html")
    @ResponseBody
    public ResultMessage deleteByAjax(@ModelAttribute("hfpmProgram") HfpmProgram hfpmProgram) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfpmProgramSV.delete(hfpmProgram);
        return message;
    }
  	//getter
 	
	public IHfpmProgramSV getIHfpmProgramSV(){
		return iHfpmProgramSV;
	}
	//setter
	public void setIHfpmProgramSV(IHfpmProgramSV iHfpmProgramSV){
    	this.iHfpmProgramSV = iHfpmProgramSV;
    }
}
