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
import com.hframe.domain.model.HfpmPageEventAttr;
import com.hframe.domain.model.HfpmPageEventAttr_Example;
import com.hframe.service.interfaces.IHfpmPageEventAttrSV;

@Controller
@RequestMapping(value = "/hframe/hfpmPageEventAttr")
public class HfpmPageEventAttrController   {

	@Resource
	private IHfpmPageEventAttrSV iHfpmPageEventAttrSV;
  


    /**
    * 创建事件属性
    * @param hfpmPageEventAttr
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/create.html")
    public ModelAndView create(@ModelAttribute("hfpmPageEventAttr") HfpmPageEventAttr hfpmPageEventAttr) throws Throwable {
        ModelAndView mav = new ModelAndView();
        iHfpmPageEventAttrSV.create(hfpmPageEventAttr);
        mav.addObject("hfpmPageEventAttrId", hfpmPageEventAttr.getHfpmPageEventAttrId());
        mav.setViewName("/hframe/hfpmPageEventAttr/hframe_hfpmPageEventAttr_create");
        return mav;
    }

    /**
    * 查询展示事件属性
    * @param hfpmPageEventAttr
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/list.html")
    public ModelAndView list(@ModelAttribute("hfpmPageEventAttr") HfpmPageEventAttr hfpmPageEventAttr) throws Throwable {
        ModelAndView mav = new ModelAndView();
        HfpmPageEventAttr_Example example = new HfpmPageEventAttr_Example();
        ExampleUtils.parseExample(hfpmPageEventAttr,example);

        List< HfpmPageEventAttr> hfpmPageEventAttrList = iHfpmPageEventAttrSV.getHfpmPageEventAttrListByExample(example);

        mav.addObject("hfpmPageEventAttrList", hfpmPageEventAttrList);
        mav.setViewName("/hframe/hfpmPageEventAttr/hframe_hfpmPageEventAttr_list");
        return mav;
    }


    /**
    * 异步创建事件属性
    * @param hfpmPageEventAttr
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/createByAjax.html")
    @ResponseBody
    public ResultMessage createByAjax(@ModelAttribute("hfpmPageEventAttr") HfpmPageEventAttr hfpmPageEventAttr) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfpmPageEventAttrSV.create(hfpmPageEventAttr);
        return message;
    }

    /**
    * 异步更新事件属性
    * @param hfpmPageEventAttr
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/updateByAjax.html")
    @ResponseBody
    public ResultMessage updateByAjax(@ModelAttribute("hfpmPageEventAttr") HfpmPageEventAttr hfpmPageEventAttr) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfpmPageEventAttrSV.update(hfpmPageEventAttr);
        return message;
    }

    /**
    * 异步删除事件属性
    * @param hfpmPageEventAttr
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/deleteByAjax.html")
    @ResponseBody
    public ResultMessage deleteByAjax(@ModelAttribute("hfpmPageEventAttr") HfpmPageEventAttr hfpmPageEventAttr) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfpmPageEventAttrSV.delete(hfpmPageEventAttr);
        return message;
    }
  	//getter
 	
	public IHfpmPageEventAttrSV getIHfpmPageEventAttrSV(){
		return iHfpmPageEventAttrSV;
	}
	//setter
	public void setIHfpmPageEventAttrSV(IHfpmPageEventAttrSV iHfpmPageEventAttrSV){
    	this.iHfpmPageEventAttrSV = iHfpmPageEventAttrSV;
    }
}
