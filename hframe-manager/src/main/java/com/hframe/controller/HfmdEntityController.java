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
import com.hframe.domain.model.HfmdEntity;
import com.hframe.domain.model.HfmdEntity_Example;
import com.hframe.service.interfaces.IHfmdEntitySV;

@Controller
@RequestMapping(value = "/hframe/hfmdEntity")
public class HfmdEntityController   {

	@Resource
	private IHfmdEntitySV iHfmdEntitySV;
  


    /**
    * 创建实体
    * @param hfmdEntity
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/create.html")
    public ModelAndView create(@ModelAttribute("hfmdEntity") HfmdEntity hfmdEntity) throws Throwable {
        ModelAndView mav = new ModelAndView();
        iHfmdEntitySV.create(hfmdEntity);
        mav.addObject("hfmdEntityId", hfmdEntity.getHfmdEntityId());
        mav.setViewName("/hframe/hfmdEntity/hframe_hfmdEntity_create");
        return mav;
    }

    /**
    * 查询展示实体
    * @param hfmdEntity
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/list.html")
    public ModelAndView list(@ModelAttribute("hfmdEntity") HfmdEntity hfmdEntity) throws Throwable {
        ModelAndView mav = new ModelAndView();
        HfmdEntity_Example example = new HfmdEntity_Example();
        ExampleUtils.parseExample(hfmdEntity,example);

        List< HfmdEntity> hfmdEntityList = iHfmdEntitySV.getHfmdEntityListByExample(example);

        mav.addObject("hfmdEntityList", hfmdEntityList);
        mav.setViewName("/hframe/hfmdEntity/hframe_hfmdEntity_list");
        return mav;
    }


    /**
    * 异步创建实体
    * @param hfmdEntity
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/createByAjax.html")
    @ResponseBody
    public ResultMessage createByAjax(@ModelAttribute("hfmdEntity") HfmdEntity hfmdEntity) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfmdEntitySV.create(hfmdEntity);
        return message;
    }

    /**
    * 异步更新实体
    * @param hfmdEntity
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/updateByAjax.html")
    @ResponseBody
    public ResultMessage updateByAjax(@ModelAttribute("hfmdEntity") HfmdEntity hfmdEntity) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfmdEntitySV.update(hfmdEntity);
        return message;
    }

    /**
    * 异步删除实体
    * @param hfmdEntity
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/deleteByAjax.html")
    @ResponseBody
    public ResultMessage deleteByAjax(@ModelAttribute("hfmdEntity") HfmdEntity hfmdEntity) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfmdEntitySV.delete(hfmdEntity);
        return message;
    }
  	//getter
 	
	public IHfmdEntitySV getIHfmdEntitySV(){
		return iHfmdEntitySV;
	}
	//setter
	public void setIHfmdEntitySV(IHfmdEntitySV iHfmdEntitySV){
    	this.iHfmdEntitySV = iHfmdEntitySV;
    }
}
