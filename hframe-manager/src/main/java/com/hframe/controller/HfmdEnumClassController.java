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
import com.hframe.domain.model.HfmdEnumClass;
import com.hframe.domain.model.HfmdEnumClass_Example;
import com.hframe.service.interfaces.IHfmdEnumClassSV;

@Controller
@RequestMapping(value = "/hframe/hfmdEnumClass")
public class HfmdEnumClassController   {

	@Resource
	private IHfmdEnumClassSV iHfmdEnumClassSV;
  


    /**
    * 创建枚举组
    * @param hfmdEnumClass
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/create.html")
    public ModelAndView create(@ModelAttribute("hfmdEnumClass") HfmdEnumClass hfmdEnumClass) throws Throwable {
        ModelAndView mav = new ModelAndView();
        iHfmdEnumClassSV.create(hfmdEnumClass);
        mav.addObject("hfmdEnumClassId", hfmdEnumClass.getHfmdEnumClassId());
        mav.setViewName("/hframe/hfmdEnumClass/hframe_hfmdEnumClass_create");
        return mav;
    }

    /**
    * 查询展示枚举组
    * @param hfmdEnumClass
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/list.html")
    public ModelAndView list(@ModelAttribute("hfmdEnumClass") HfmdEnumClass hfmdEnumClass) throws Throwable {
        ModelAndView mav = new ModelAndView();
        HfmdEnumClass_Example example = new HfmdEnumClass_Example();
        ExampleUtils.parseExample(hfmdEnumClass,example);

        List< HfmdEnumClass> hfmdEnumClassList = iHfmdEnumClassSV.getHfmdEnumClassListByExample(example);

        mav.addObject("hfmdEnumClassList", hfmdEnumClassList);
        mav.setViewName("/hframe/hfmdEnumClass/hframe_hfmdEnumClass_list");
        return mav;
    }


    /**
    * 异步创建枚举组
    * @param hfmdEnumClass
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/createByAjax.html")
    @ResponseBody
    public ResultMessage createByAjax(@ModelAttribute("hfmdEnumClass") HfmdEnumClass hfmdEnumClass) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfmdEnumClassSV.create(hfmdEnumClass);
        return message;
    }

    /**
    * 异步更新枚举组
    * @param hfmdEnumClass
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/updateByAjax.html")
    @ResponseBody
    public ResultMessage updateByAjax(@ModelAttribute("hfmdEnumClass") HfmdEnumClass hfmdEnumClass) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfmdEnumClassSV.update(hfmdEnumClass);
        return message;
    }

    /**
    * 异步删除枚举组
    * @param hfmdEnumClass
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/deleteByAjax.html")
    @ResponseBody
    public ResultMessage deleteByAjax(@ModelAttribute("hfmdEnumClass") HfmdEnumClass hfmdEnumClass) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfmdEnumClassSV.delete(hfmdEnumClass);
        return message;
    }
  	//getter
 	
	public IHfmdEnumClassSV getIHfmdEnumClassSV(){
		return iHfmdEnumClassSV;
	}
	//setter
	public void setIHfmdEnumClassSV(IHfmdEnumClassSV iHfmdEnumClassSV){
    	this.iHfmdEnumClassSV = iHfmdEnumClassSV;
    }
}
