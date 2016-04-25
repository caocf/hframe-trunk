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
import com.hframe.domain.model.HfpmPageEntityRel;
import com.hframe.domain.model.HfpmPageEntityRel_Example;
import com.hframe.service.interfaces.IHfpmPageEntityRelSV;

@Controller
@RequestMapping(value = "/hframe/hfpmPageEntityRel")
public class HfpmPageEntityRelController   {

	@Resource
	private IHfpmPageEntityRelSV iHfpmPageEntityRelSV;
  


    /**
    * 创建页面关联实体
    * @param hfpmPageEntityRel
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/create.html")
    public ModelAndView create(@ModelAttribute("hfpmPageEntityRel") HfpmPageEntityRel hfpmPageEntityRel) throws Throwable {
        ModelAndView mav = new ModelAndView();
        iHfpmPageEntityRelSV.create(hfpmPageEntityRel);
        mav.addObject("hfpmPageEntityRelId", hfpmPageEntityRel.getHfpmPageEntityRelId());
        mav.setViewName("/hframe/hfpmPageEntityRel/hframe_hfpmPageEntityRel_create");
        return mav;
    }

    /**
    * 查询展示页面关联实体
    * @param hfpmPageEntityRel
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/list.html")
    public ModelAndView list(@ModelAttribute("hfpmPageEntityRel") HfpmPageEntityRel hfpmPageEntityRel) throws Throwable {
        ModelAndView mav = new ModelAndView();
        HfpmPageEntityRel_Example example = new HfpmPageEntityRel_Example();
        ExampleUtils.parseExample(hfpmPageEntityRel,example);

        List< HfpmPageEntityRel> hfpmPageEntityRelList = iHfpmPageEntityRelSV.getHfpmPageEntityRelListByExample(example);

        mav.addObject("hfpmPageEntityRelList", hfpmPageEntityRelList);
        mav.setViewName("/hframe/hfpmPageEntityRel/hframe_hfpmPageEntityRel_list");
        return mav;
    }


    /**
    * 异步创建页面关联实体
    * @param hfpmPageEntityRel
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/createByAjax.html")
    @ResponseBody
    public ResultMessage createByAjax(@ModelAttribute("hfpmPageEntityRel") HfpmPageEntityRel hfpmPageEntityRel) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfpmPageEntityRelSV.create(hfpmPageEntityRel);
        return message;
    }

    /**
    * 异步更新页面关联实体
    * @param hfpmPageEntityRel
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/updateByAjax.html")
    @ResponseBody
    public ResultMessage updateByAjax(@ModelAttribute("hfpmPageEntityRel") HfpmPageEntityRel hfpmPageEntityRel) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfpmPageEntityRelSV.update(hfpmPageEntityRel);
        return message;
    }

    /**
    * 异步删除页面关联实体
    * @param hfpmPageEntityRel
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/deleteByAjax.html")
    @ResponseBody
    public ResultMessage deleteByAjax(@ModelAttribute("hfpmPageEntityRel") HfpmPageEntityRel hfpmPageEntityRel) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfpmPageEntityRelSV.delete(hfpmPageEntityRel);
        return message;
    }
  	//getter
 	
	public IHfpmPageEntityRelSV getIHfpmPageEntityRelSV(){
		return iHfpmPageEntityRelSV;
	}
	//setter
	public void setIHfpmPageEntityRelSV(IHfpmPageEntityRelSV iHfpmPageEntityRelSV){
    	this.iHfpmPageEntityRelSV = iHfpmPageEntityRelSV;
    }
}
