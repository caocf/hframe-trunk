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
import com.hframe.domain.model.HfpmDataField;
import com.hframe.domain.model.HfpmDataField_Example;
import com.hframe.service.interfaces.IHfpmDataFieldSV;

@Controller
@RequestMapping(value = "/hframe/hfpmDataField")
public class HfpmDataFieldController   {

	@Resource
	private IHfpmDataFieldSV iHfpmDataFieldSV;
  


    /**
    * 创建数据列
    * @param hfpmDataField
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/create.html")
    public ModelAndView create(@ModelAttribute("hfpmDataField") HfpmDataField hfpmDataField) throws Throwable {
        ModelAndView mav = new ModelAndView();
        iHfpmDataFieldSV.create(hfpmDataField);
        mav.addObject("hfpmDataFieldId", hfpmDataField.getHfpmDataFieldId());
        mav.setViewName("/hframe/hfpmDataField/hframe_hfpmDataField_create");
        return mav;
    }

    /**
    * 查询展示数据列
    * @param hfpmDataField
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/list.html")
    public ModelAndView list(@ModelAttribute("hfpmDataField") HfpmDataField hfpmDataField) throws Throwable {
        ModelAndView mav = new ModelAndView();
        HfpmDataField_Example example = new HfpmDataField_Example();
        ExampleUtils.parseExample(hfpmDataField,example);

        List< HfpmDataField> hfpmDataFieldList = iHfpmDataFieldSV.getHfpmDataFieldListByExample(example);

        mav.addObject("hfpmDataFieldList", hfpmDataFieldList);
        mav.setViewName("/hframe/hfpmDataField/hframe_hfpmDataField_list");
        return mav;
    }


    /**
    * 异步创建数据列
    * @param hfpmDataField
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/createByAjax.html")
    @ResponseBody
    public ResultMessage createByAjax(@ModelAttribute("hfpmDataField") HfpmDataField hfpmDataField) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfpmDataFieldSV.create(hfpmDataField);
        return message;
    }

    /**
    * 异步更新数据列
    * @param hfpmDataField
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/updateByAjax.html")
    @ResponseBody
    public ResultMessage updateByAjax(@ModelAttribute("hfpmDataField") HfpmDataField hfpmDataField) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfpmDataFieldSV.update(hfpmDataField);
        return message;
    }

    /**
    * 异步删除数据列
    * @param hfpmDataField
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/deleteByAjax.html")
    @ResponseBody
    public ResultMessage deleteByAjax(@ModelAttribute("hfpmDataField") HfpmDataField hfpmDataField) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfpmDataFieldSV.delete(hfpmDataField);
        return message;
    }
  	//getter
 	
	public IHfpmDataFieldSV getIHfpmDataFieldSV(){
		return iHfpmDataFieldSV;
	}
	//setter
	public void setIHfpmDataFieldSV(IHfpmDataFieldSV iHfpmDataFieldSV){
    	this.iHfpmDataFieldSV = iHfpmDataFieldSV;
    }
}
