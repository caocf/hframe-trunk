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
import com.hframe.domain.model.HfpmDataSet;
import com.hframe.domain.model.HfpmDataSet_Example;
import com.hframe.service.interfaces.IHfpmDataSetSV;

@Controller
@RequestMapping(value = "/hframe/hfpmDataSet")
public class HfpmDataSetController   {

	@Resource
	private IHfpmDataSetSV iHfpmDataSetSV;
  


    /**
    * 创建数据集
    * @param hfpmDataSet
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/create.html")
    public ModelAndView create(@ModelAttribute("hfpmDataSet") HfpmDataSet hfpmDataSet) throws Throwable {
        ModelAndView mav = new ModelAndView();
        iHfpmDataSetSV.create(hfpmDataSet);
        mav.addObject("hfpmDataSetId", hfpmDataSet.getHfpmDataSetId());
        mav.setViewName("/hframe/hfpmDataSet/hframe_hfpmDataSet_create");
        return mav;
    }

    /**
    * 查询展示数据集
    * @param hfpmDataSet
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/list.html")
    public ModelAndView list(@ModelAttribute("hfpmDataSet") HfpmDataSet hfpmDataSet) throws Throwable {
        ModelAndView mav = new ModelAndView();
        HfpmDataSet_Example example = new HfpmDataSet_Example();
        ExampleUtils.parseExample(hfpmDataSet,example);

        List< HfpmDataSet> hfpmDataSetList = iHfpmDataSetSV.getHfpmDataSetListByExample(example);

        mav.addObject("hfpmDataSetList", hfpmDataSetList);
        mav.setViewName("/hframe/hfpmDataSet/hframe_hfpmDataSet_list");
        return mav;
    }


    /**
    * 异步创建数据集
    * @param hfpmDataSet
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/createByAjax.html")
    @ResponseBody
    public ResultMessage createByAjax(@ModelAttribute("hfpmDataSet") HfpmDataSet hfpmDataSet) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfpmDataSetSV.create(hfpmDataSet);
        return message;
    }

    /**
    * 异步更新数据集
    * @param hfpmDataSet
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/updateByAjax.html")
    @ResponseBody
    public ResultMessage updateByAjax(@ModelAttribute("hfpmDataSet") HfpmDataSet hfpmDataSet) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfpmDataSetSV.update(hfpmDataSet);
        return message;
    }

    /**
    * 异步删除数据集
    * @param hfpmDataSet
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/deleteByAjax.html")
    @ResponseBody
    public ResultMessage deleteByAjax(@ModelAttribute("hfpmDataSet") HfpmDataSet hfpmDataSet) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfpmDataSetSV.delete(hfpmDataSet);
        return message;
    }
  	//getter
 	
	public IHfpmDataSetSV getIHfpmDataSetSV(){
		return iHfpmDataSetSV;
	}
	//setter
	public void setIHfpmDataSetSV(IHfpmDataSetSV iHfpmDataSetSV){
    	this.iHfpmDataSetSV = iHfpmDataSetSV;
    }
}
