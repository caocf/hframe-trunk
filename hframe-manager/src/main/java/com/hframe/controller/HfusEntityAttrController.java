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
import com.hframe.domain.model.HfusEntityAttr;
import com.hframe.domain.model.HfusEntityAttr_Example;
import com.hframe.service.interfaces.IHfusEntityAttrSV;

@Controller
@RequestMapping(value = "/hframe/hfusEntityAttr")
public class HfusEntityAttrController   {

	@Resource
	private IHfusEntityAttrSV iHfusEntityAttrSV;
  


    /**
    * 创建常用实体属性
    * @param hfusEntityAttr
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/create.html")
    public ModelAndView create(@ModelAttribute("hfusEntityAttr") HfusEntityAttr hfusEntityAttr) throws Throwable {
        ModelAndView mav = new ModelAndView();
        iHfusEntityAttrSV.create(hfusEntityAttr);
        mav.addObject("hfusEntityAttrId", hfusEntityAttr.getHfusEntityAttrId());
        mav.setViewName("/hframe/hfusEntityAttr/hframe_hfusEntityAttr_create");
        return mav;
    }

    /**
    * 查询展示常用实体属性
    * @param hfusEntityAttr
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/list.html")
    public ModelAndView list(@ModelAttribute("hfusEntityAttr") HfusEntityAttr hfusEntityAttr) throws Throwable {
        ModelAndView mav = new ModelAndView();
        HfusEntityAttr_Example example = new HfusEntityAttr_Example();
        ExampleUtils.parseExample(hfusEntityAttr,example);

        List< HfusEntityAttr> hfusEntityAttrList = iHfusEntityAttrSV.getHfusEntityAttrListByExample(example);

        mav.addObject("hfusEntityAttrList", hfusEntityAttrList);
        mav.setViewName("/hframe/hfusEntityAttr/hframe_hfusEntityAttr_list");
        return mav;
    }


    /**
    * 异步创建常用实体属性
    * @param hfusEntityAttr
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/createByAjax.html")
    @ResponseBody
    public ResultMessage createByAjax(@ModelAttribute("hfusEntityAttr") HfusEntityAttr hfusEntityAttr) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfusEntityAttrSV.create(hfusEntityAttr);
        return message;
    }

    /**
    * 异步更新常用实体属性
    * @param hfusEntityAttr
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/updateByAjax.html")
    @ResponseBody
    public ResultMessage updateByAjax(@ModelAttribute("hfusEntityAttr") HfusEntityAttr hfusEntityAttr) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfusEntityAttrSV.update(hfusEntityAttr);
        return message;
    }

    /**
    * 异步删除常用实体属性
    * @param hfusEntityAttr
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/deleteByAjax.html")
    @ResponseBody
    public ResultMessage deleteByAjax(@ModelAttribute("hfusEntityAttr") HfusEntityAttr hfusEntityAttr) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfusEntityAttrSV.delete(hfusEntityAttr);
        return message;
    }
  	//getter
 	
	public IHfusEntityAttrSV getIHfusEntityAttrSV(){
		return iHfusEntityAttrSV;
	}
	//setter
	public void setIHfusEntityAttrSV(IHfusEntityAttrSV iHfusEntityAttrSV){
    	this.iHfusEntityAttrSV = iHfusEntityAttrSV;
    }
}
