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
import com.hframe.domain.model.HfpmFieldShowType;
import com.hframe.domain.model.HfpmFieldShowType_Example;
import com.hframe.service.interfaces.IHfpmFieldShowTypeSV;

@Controller
@RequestMapping(value = "/hframe/hfpmFieldShowType")
public class HfpmFieldShowTypeController   {

	@Resource
	private IHfpmFieldShowTypeSV iHfpmFieldShowTypeSV;
  


    /**
    * 创建列展示类型
    * @param hfpmFieldShowType
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/create.html")
    public ModelAndView create(@ModelAttribute("hfpmFieldShowType") HfpmFieldShowType hfpmFieldShowType) throws Throwable {
        ModelAndView mav = new ModelAndView();
        iHfpmFieldShowTypeSV.create(hfpmFieldShowType);
        mav.addObject("hfpmFieldShowTypeId", hfpmFieldShowType.getHfpmFieldShowTypeId());
        mav.setViewName("/hframe/hfpmFieldShowType/hframe_hfpmFieldShowType_create");
        return mav;
    }

    /**
    * 查询展示列展示类型
    * @param hfpmFieldShowType
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/list.html")
    public ModelAndView list(@ModelAttribute("hfpmFieldShowType") HfpmFieldShowType hfpmFieldShowType) throws Throwable {
        ModelAndView mav = new ModelAndView();
        HfpmFieldShowType_Example example = new HfpmFieldShowType_Example();
        ExampleUtils.parseExample(hfpmFieldShowType,example);

        List< HfpmFieldShowType> hfpmFieldShowTypeList = iHfpmFieldShowTypeSV.getHfpmFieldShowTypeListByExample(example);

        mav.addObject("hfpmFieldShowTypeList", hfpmFieldShowTypeList);
        mav.setViewName("/hframe/hfpmFieldShowType/hframe_hfpmFieldShowType_list");
        return mav;
    }


    /**
    * 异步创建列展示类型
    * @param hfpmFieldShowType
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/createByAjax.html")
    @ResponseBody
    public ResultMessage createByAjax(@ModelAttribute("hfpmFieldShowType") HfpmFieldShowType hfpmFieldShowType) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfpmFieldShowTypeSV.create(hfpmFieldShowType);
        return message;
    }

    /**
    * 异步更新列展示类型
    * @param hfpmFieldShowType
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/updateByAjax.html")
    @ResponseBody
    public ResultMessage updateByAjax(@ModelAttribute("hfpmFieldShowType") HfpmFieldShowType hfpmFieldShowType) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfpmFieldShowTypeSV.update(hfpmFieldShowType);
        return message;
    }

    /**
    * 异步删除列展示类型
    * @param hfpmFieldShowType
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/deleteByAjax.html")
    @ResponseBody
    public ResultMessage deleteByAjax(@ModelAttribute("hfpmFieldShowType") HfpmFieldShowType hfpmFieldShowType) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfpmFieldShowTypeSV.delete(hfpmFieldShowType);
        return message;
    }
  	//getter
 	
	public IHfpmFieldShowTypeSV getIHfpmFieldShowTypeSV(){
		return iHfpmFieldShowTypeSV;
	}
	//setter
	public void setIHfpmFieldShowTypeSV(IHfpmFieldShowTypeSV iHfpmFieldShowTypeSV){
    	this.iHfpmFieldShowTypeSV = iHfpmFieldShowTypeSV;
    }
}
