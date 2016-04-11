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
import com.hframe.domain.model.HfmdEnum;
import com.hframe.domain.model.HfmdEnum_Example;
import com.hframe.service.interfaces.IHfmdEnumSV;

@Controller
@RequestMapping(value = "/hframe/hfmdEnum")
public class HfmdEnumController   {

	@Resource
	private IHfmdEnumSV iHfmdEnumSV;
  


    /**
    * 创建枚举
    * @param hfmdEnum
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/create.html")
    public ModelAndView create(@ModelAttribute("hfmdEnum") HfmdEnum hfmdEnum) throws Throwable {
        ModelAndView mav = new ModelAndView();
        iHfmdEnumSV.create(hfmdEnum);
        mav.addObject("hfmdEnumId", hfmdEnum.getHfmdEnumId());
        mav.setViewName("/hframe/hfmdEnum/hframe_hfmdEnum_create");
        return mav;
    }

    /**
    * 查询展示枚举
    * @param hfmdEnum
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/list.html")
    public ModelAndView list(@ModelAttribute("hfmdEnum") HfmdEnum hfmdEnum) throws Throwable {
        ModelAndView mav = new ModelAndView();
        HfmdEnum_Example example = new HfmdEnum_Example();
        ExampleUtils.parseExample(hfmdEnum,example);

        List< HfmdEnum> hfmdEnumList = iHfmdEnumSV.getHfmdEnumListByExample(example);

        mav.addObject("hfmdEnumList", hfmdEnumList);
        mav.setViewName("/hframe/hfmdEnum/hframe_hfmdEnum_list");
        return mav;
    }


    /**
    * 异步创建枚举
    * @param hfmdEnum
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/createByAjax.html")
    @ResponseBody
    public ResultMessage createByAjax(@ModelAttribute("hfmdEnum") HfmdEnum hfmdEnum) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfmdEnumSV.create(hfmdEnum);
        return message;
    }

    /**
    * 异步更新枚举
    * @param hfmdEnum
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/updateByAjax.html")
    @ResponseBody
    public ResultMessage updateByAjax(@ModelAttribute("hfmdEnum") HfmdEnum hfmdEnum) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfmdEnumSV.update(hfmdEnum);
        return message;
    }

    /**
    * 异步删除枚举
    * @param hfmdEnum
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/deleteByAjax.html")
    @ResponseBody
    public ResultMessage deleteByAjax(@ModelAttribute("hfmdEnum") HfmdEnum hfmdEnum) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfmdEnumSV.delete(hfmdEnum);
        return message;
    }
  	//getter
 	
	public IHfmdEnumSV getIHfmdEnumSV(){
		return iHfmdEnumSV;
	}
	//setter
	public void setIHfmdEnumSV(IHfmdEnumSV iHfmdEnumSV){
    	this.iHfmdEnumSV = iHfmdEnumSV;
    }
}
