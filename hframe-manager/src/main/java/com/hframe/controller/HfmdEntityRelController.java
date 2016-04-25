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
import com.hframe.domain.model.HfmdEntityRel;
import com.hframe.domain.model.HfmdEntityRel_Example;
import com.hframe.service.interfaces.IHfmdEntityRelSV;

@Controller
@RequestMapping(value = "/hframe/hfmdEntityRel")
public class HfmdEntityRelController   {

	@Resource
	private IHfmdEntityRelSV iHfmdEntityRelSV;
  


    /**
    * 创建实体关系
    * @param hfmdEntityRel
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/create.html")
    public ModelAndView create(@ModelAttribute("hfmdEntityRel") HfmdEntityRel hfmdEntityRel) throws Throwable {
        ModelAndView mav = new ModelAndView();
        iHfmdEntityRelSV.create(hfmdEntityRel);
        mav.addObject("hfmdEntityRelId", hfmdEntityRel.getHfmdEntityRelId());
        mav.setViewName("/hframe/hfmdEntityRel/hframe_hfmdEntityRel_create");
        return mav;
    }

    /**
    * 查询展示实体关系
    * @param hfmdEntityRel
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/list.html")
    public ModelAndView list(@ModelAttribute("hfmdEntityRel") HfmdEntityRel hfmdEntityRel) throws Throwable {
        ModelAndView mav = new ModelAndView();
        HfmdEntityRel_Example example = new HfmdEntityRel_Example();
        ExampleUtils.parseExample(hfmdEntityRel,example);

        List< HfmdEntityRel> hfmdEntityRelList = iHfmdEntityRelSV.getHfmdEntityRelListByExample(example);

        mav.addObject("hfmdEntityRelList", hfmdEntityRelList);
        mav.setViewName("/hframe/hfmdEntityRel/hframe_hfmdEntityRel_list");
        return mav;
    }


    /**
    * 异步创建实体关系
    * @param hfmdEntityRel
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/createByAjax.html")
    @ResponseBody
    public ResultMessage createByAjax(@ModelAttribute("hfmdEntityRel") HfmdEntityRel hfmdEntityRel) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfmdEntityRelSV.create(hfmdEntityRel);
        return message;
    }

    /**
    * 异步更新实体关系
    * @param hfmdEntityRel
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/updateByAjax.html")
    @ResponseBody
    public ResultMessage updateByAjax(@ModelAttribute("hfmdEntityRel") HfmdEntityRel hfmdEntityRel) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfmdEntityRelSV.update(hfmdEntityRel);
        return message;
    }

    /**
    * 异步删除实体关系
    * @param hfmdEntityRel
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/deleteByAjax.html")
    @ResponseBody
    public ResultMessage deleteByAjax(@ModelAttribute("hfmdEntityRel") HfmdEntityRel hfmdEntityRel) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfmdEntityRelSV.delete(hfmdEntityRel);
        return message;
    }
  	//getter
 	
	public IHfmdEntityRelSV getIHfmdEntityRelSV(){
		return iHfmdEntityRelSV;
	}
	//setter
	public void setIHfmdEntityRelSV(IHfmdEntityRelSV iHfmdEntityRelSV){
    	this.iHfmdEntityRelSV = iHfmdEntityRelSV;
    }
}
