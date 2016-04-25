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
import com.hframe.domain.model.HfcfgProgramSkin;
import com.hframe.domain.model.HfcfgProgramSkin_Example;
import com.hframe.service.interfaces.IHfcfgProgramSkinSV;

@Controller
@RequestMapping(value = "/hframe/hfcfgProgramSkin")
public class HfcfgProgramSkinController   {

	@Resource
	private IHfcfgProgramSkinSV iHfcfgProgramSkinSV;
  


    /**
    * 创建皮肤
    * @param hfcfgProgramSkin
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/create.html")
    public ModelAndView create(@ModelAttribute("hfcfgProgramSkin") HfcfgProgramSkin hfcfgProgramSkin) throws Throwable {
        ModelAndView mav = new ModelAndView();
        iHfcfgProgramSkinSV.create(hfcfgProgramSkin);
        mav.addObject("hfcfgProgramSkinId", hfcfgProgramSkin.getHfcfgProgramSkinId());
        mav.setViewName("/hframe/hfcfgProgramSkin/hframe_hfcfgProgramSkin_create");
        return mav;
    }

    /**
    * 查询展示皮肤
    * @param hfcfgProgramSkin
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/list.html")
    public ModelAndView list(@ModelAttribute("hfcfgProgramSkin") HfcfgProgramSkin hfcfgProgramSkin) throws Throwable {
        ModelAndView mav = new ModelAndView();
        HfcfgProgramSkin_Example example = new HfcfgProgramSkin_Example();
        ExampleUtils.parseExample(hfcfgProgramSkin,example);

        List< HfcfgProgramSkin> hfcfgProgramSkinList = iHfcfgProgramSkinSV.getHfcfgProgramSkinListByExample(example);

        mav.addObject("hfcfgProgramSkinList", hfcfgProgramSkinList);
        mav.setViewName("/hframe/hfcfgProgramSkin/hframe_hfcfgProgramSkin_list");
        return mav;
    }


    /**
    * 异步创建皮肤
    * @param hfcfgProgramSkin
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/createByAjax.html")
    @ResponseBody
    public ResultMessage createByAjax(@ModelAttribute("hfcfgProgramSkin") HfcfgProgramSkin hfcfgProgramSkin) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfcfgProgramSkinSV.create(hfcfgProgramSkin);
        return message;
    }

    /**
    * 异步更新皮肤
    * @param hfcfgProgramSkin
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/updateByAjax.html")
    @ResponseBody
    public ResultMessage updateByAjax(@ModelAttribute("hfcfgProgramSkin") HfcfgProgramSkin hfcfgProgramSkin) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfcfgProgramSkinSV.update(hfcfgProgramSkin);
        return message;
    }

    /**
    * 异步删除皮肤
    * @param hfcfgProgramSkin
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/deleteByAjax.html")
    @ResponseBody
    public ResultMessage deleteByAjax(@ModelAttribute("hfcfgProgramSkin") HfcfgProgramSkin hfcfgProgramSkin) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfcfgProgramSkinSV.delete(hfcfgProgramSkin);
        return message;
    }
  	//getter
 	
	public IHfcfgProgramSkinSV getIHfcfgProgramSkinSV(){
		return iHfcfgProgramSkinSV;
	}
	//setter
	public void setIHfcfgProgramSkinSV(IHfcfgProgramSkinSV iHfcfgProgramSkinSV){
    	this.iHfcfgProgramSkinSV = iHfcfgProgramSkinSV;
    }
}
