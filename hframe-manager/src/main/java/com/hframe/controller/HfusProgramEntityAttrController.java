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
import com.hframe.domain.model.HfusProgramEntityAttr;
import com.hframe.domain.model.HfusProgramEntityAttr_Example;
import com.hframe.service.interfaces.IHfusProgramEntityAttrSV;

@Controller
@RequestMapping(value = "/hframe/hfusProgramEntityAttr")
public class HfusProgramEntityAttrController   {

	@Resource
	private IHfusProgramEntityAttrSV iHfusProgramEntityAttrSV;
  


    /**
    * 创建常用项目实体属性
    * @param hfusProgramEntityAttr
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/create.html")
    public ModelAndView create(@ModelAttribute("hfusProgramEntityAttr") HfusProgramEntityAttr hfusProgramEntityAttr) throws Throwable {
        ModelAndView mav = new ModelAndView();
        iHfusProgramEntityAttrSV.create(hfusProgramEntityAttr);
        mav.addObject("hfusProgramEntityAttrId", hfusProgramEntityAttr.getHfusProgramEntityAttrId());
        mav.setViewName("/hframe/hfusProgramEntityAttr/hframe_hfusProgramEntityAttr_create");
        return mav;
    }

    /**
    * 查询展示常用项目实体属性
    * @param hfusProgramEntityAttr
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/list.html")
    public ModelAndView list(@ModelAttribute("hfusProgramEntityAttr") HfusProgramEntityAttr hfusProgramEntityAttr) throws Throwable {
        ModelAndView mav = new ModelAndView();
        HfusProgramEntityAttr_Example example = new HfusProgramEntityAttr_Example();
        ExampleUtils.parseExample(hfusProgramEntityAttr,example);

        List< HfusProgramEntityAttr> hfusProgramEntityAttrList = iHfusProgramEntityAttrSV.getHfusProgramEntityAttrListByExample(example);

        mav.addObject("hfusProgramEntityAttrList", hfusProgramEntityAttrList);
        mav.setViewName("/hframe/hfusProgramEntityAttr/hframe_hfusProgramEntityAttr_list");
        return mav;
    }


    /**
    * 异步创建常用项目实体属性
    * @param hfusProgramEntityAttr
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/createByAjax.html")
    @ResponseBody
    public ResultMessage createByAjax(@ModelAttribute("hfusProgramEntityAttr") HfusProgramEntityAttr hfusProgramEntityAttr) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfusProgramEntityAttrSV.create(hfusProgramEntityAttr);
        return message;
    }

    /**
    * 异步更新常用项目实体属性
    * @param hfusProgramEntityAttr
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/updateByAjax.html")
    @ResponseBody
    public ResultMessage updateByAjax(@ModelAttribute("hfusProgramEntityAttr") HfusProgramEntityAttr hfusProgramEntityAttr) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfusProgramEntityAttrSV.update(hfusProgramEntityAttr);
        return message;
    }

    /**
    * 异步删除常用项目实体属性
    * @param hfusProgramEntityAttr
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/deleteByAjax.html")
    @ResponseBody
    public ResultMessage deleteByAjax(@ModelAttribute("hfusProgramEntityAttr") HfusProgramEntityAttr hfusProgramEntityAttr) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfusProgramEntityAttrSV.delete(hfusProgramEntityAttr);
        return message;
    }
  	//getter
 	
	public IHfusProgramEntityAttrSV getIHfusProgramEntityAttrSV(){
		return iHfusProgramEntityAttrSV;
	}
	//setter
	public void setIHfusProgramEntityAttrSV(IHfusProgramEntityAttrSV iHfusProgramEntityAttrSV){
    	this.iHfusProgramEntityAttrSV = iHfusProgramEntityAttrSV;
    }
}
