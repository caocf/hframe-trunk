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
import com.hframe.domain.model.HfusEntityTypeRelatEntityAttr;
import com.hframe.domain.model.HfusEntityTypeRelatEntityAttr_Example;
import com.hframe.service.interfaces.IHfusEntityTypeRelatEntityAttrSV;

@Controller
@RequestMapping(value = "/hframe/hfusEntityTypeRelatEntityAttr")
public class HfusEntityTypeRelatEntityAttrController   {

	@Resource
	private IHfusEntityTypeRelatEntityAttrSV iHfusEntityTypeRelatEntityAttrSV;
  


    /**
    * 创建常用实体类型关联属性
    * @param hfusEntityTypeRelatEntityAttr
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/create.html")
    public ModelAndView create(@ModelAttribute("hfusEntityTypeRelatEntityAttr") HfusEntityTypeRelatEntityAttr hfusEntityTypeRelatEntityAttr) throws Throwable {
        ModelAndView mav = new ModelAndView();
        iHfusEntityTypeRelatEntityAttrSV.create(hfusEntityTypeRelatEntityAttr);
        mav.addObject("hfusEntityTypeRelatEntityAttrId", hfusEntityTypeRelatEntityAttr.getHfusEntityTypeRelatEntityAttrId());
        mav.setViewName("/hframe/hfusEntityTypeRelatEntityAttr/hframe_hfusEntityTypeRelatEntityAttr_create");
        return mav;
    }

    /**
    * 查询展示常用实体类型关联属性
    * @param hfusEntityTypeRelatEntityAttr
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/list.html")
    public ModelAndView list(@ModelAttribute("hfusEntityTypeRelatEntityAttr") HfusEntityTypeRelatEntityAttr hfusEntityTypeRelatEntityAttr) throws Throwable {
        ModelAndView mav = new ModelAndView();
        HfusEntityTypeRelatEntityAttr_Example example = new HfusEntityTypeRelatEntityAttr_Example();
        ExampleUtils.parseExample(hfusEntityTypeRelatEntityAttr,example);

        List< HfusEntityTypeRelatEntityAttr> hfusEntityTypeRelatEntityAttrList = iHfusEntityTypeRelatEntityAttrSV.getHfusEntityTypeRelatEntityAttrListByExample(example);

        mav.addObject("hfusEntityTypeRelatEntityAttrList", hfusEntityTypeRelatEntityAttrList);
        mav.setViewName("/hframe/hfusEntityTypeRelatEntityAttr/hframe_hfusEntityTypeRelatEntityAttr_list");
        return mav;
    }


    /**
    * 异步创建常用实体类型关联属性
    * @param hfusEntityTypeRelatEntityAttr
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/createByAjax.html")
    @ResponseBody
    public ResultMessage createByAjax(@ModelAttribute("hfusEntityTypeRelatEntityAttr") HfusEntityTypeRelatEntityAttr hfusEntityTypeRelatEntityAttr) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfusEntityTypeRelatEntityAttrSV.create(hfusEntityTypeRelatEntityAttr);
        return message;
    }

    /**
    * 异步更新常用实体类型关联属性
    * @param hfusEntityTypeRelatEntityAttr
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/updateByAjax.html")
    @ResponseBody
    public ResultMessage updateByAjax(@ModelAttribute("hfusEntityTypeRelatEntityAttr") HfusEntityTypeRelatEntityAttr hfusEntityTypeRelatEntityAttr) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfusEntityTypeRelatEntityAttrSV.update(hfusEntityTypeRelatEntityAttr);
        return message;
    }

    /**
    * 异步删除常用实体类型关联属性
    * @param hfusEntityTypeRelatEntityAttr
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/deleteByAjax.html")
    @ResponseBody
    public ResultMessage deleteByAjax(@ModelAttribute("hfusEntityTypeRelatEntityAttr") HfusEntityTypeRelatEntityAttr hfusEntityTypeRelatEntityAttr) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfusEntityTypeRelatEntityAttrSV.delete(hfusEntityTypeRelatEntityAttr);
        return message;
    }
  	//getter
 	
	public IHfusEntityTypeRelatEntityAttrSV getIHfusEntityTypeRelatEntityAttrSV(){
		return iHfusEntityTypeRelatEntityAttrSV;
	}
	//setter
	public void setIHfusEntityTypeRelatEntityAttrSV(IHfusEntityTypeRelatEntityAttrSV iHfusEntityTypeRelatEntityAttrSV){
    	this.iHfusEntityTypeRelatEntityAttrSV = iHfusEntityTypeRelatEntityAttrSV;
    }
}
