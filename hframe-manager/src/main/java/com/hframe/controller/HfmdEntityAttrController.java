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
import com.hframe.domain.model.HfmdEntityAttr;
import com.hframe.domain.model.HfmdEntityAttr_Example;
import com.hframe.service.interfaces.IHfmdEntityAttrSV;

@Controller
@RequestMapping(value = "/hframe/hfmdEntityAttr")
public class HfmdEntityAttrController   {

	@Resource
	private IHfmdEntityAttrSV iHfmdEntityAttrSV;
  


    /**
    * 创建实体属性
    * @param hfmdEntityAttr
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/create.html")
    public ModelAndView create(@ModelAttribute("hfmdEntityAttr") HfmdEntityAttr hfmdEntityAttr) throws Throwable {
        ModelAndView mav = new ModelAndView();
        iHfmdEntityAttrSV.create(hfmdEntityAttr);
        mav.addObject("hfmdEntityAttrId", hfmdEntityAttr.getHfmdEntityAttrId());
        mav.setViewName("/hframe/hfmdEntityAttr/hframe_hfmdEntityAttr_create");
        return mav;
    }

    /**
    * 查询展示实体属性
    * @param hfmdEntityAttr
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/list.html")
    public ModelAndView list(@ModelAttribute("hfmdEntityAttr") HfmdEntityAttr hfmdEntityAttr) throws Throwable {
        ModelAndView mav = new ModelAndView();
        HfmdEntityAttr_Example example = new HfmdEntityAttr_Example();
        ExampleUtils.parseExample(hfmdEntityAttr,example);

        List< HfmdEntityAttr> hfmdEntityAttrList = iHfmdEntityAttrSV.getHfmdEntityAttrListByExample(example);

        mav.addObject("hfmdEntityAttrList", hfmdEntityAttrList);
        mav.setViewName("/hframe/hfmdEntityAttr/hframe_hfmdEntityAttr_list");
        return mav;
    }


    /**
    * 异步创建实体属性
    * @param hfmdEntityAttr
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/createByAjax.html")
    @ResponseBody
    public ResultMessage createByAjax(@ModelAttribute("hfmdEntityAttr") HfmdEntityAttr hfmdEntityAttr) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfmdEntityAttrSV.create(hfmdEntityAttr);
        return message;
    }

    /**
    * 异步更新实体属性
    * @param hfmdEntityAttr
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/updateByAjax.html")
    @ResponseBody
    public ResultMessage updateByAjax(@ModelAttribute("hfmdEntityAttr") HfmdEntityAttr hfmdEntityAttr) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfmdEntityAttrSV.update(hfmdEntityAttr);
        return message;
    }

    /**
    * 异步删除实体属性
    * @param hfmdEntityAttr
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/deleteByAjax.html")
    @ResponseBody
    public ResultMessage deleteByAjax(@ModelAttribute("hfmdEntityAttr") HfmdEntityAttr hfmdEntityAttr) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfmdEntityAttrSV.delete(hfmdEntityAttr);
        return message;
    }
  	//getter
 	
	public IHfmdEntityAttrSV getIHfmdEntityAttrSV(){
		return iHfmdEntityAttrSV;
	}
	//setter
	public void setIHfmdEntityAttrSV(IHfmdEntityAttrSV iHfmdEntityAttrSV){
    	this.iHfmdEntityAttrSV = iHfmdEntityAttrSV;
    }
}
