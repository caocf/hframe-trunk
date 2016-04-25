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
import com.hframe.domain.model.HfpmEntityBindRule;
import com.hframe.domain.model.HfpmEntityBindRule_Example;
import com.hframe.service.interfaces.IHfpmEntityBindRuleSV;

@Controller
@RequestMapping(value = "/hframe/hfpmEntityBindRule")
public class HfpmEntityBindRuleController   {

	@Resource
	private IHfpmEntityBindRuleSV iHfpmEntityBindRuleSV;
  


    /**
    * 创建实体捆绑规则
    * @param hfpmEntityBindRule
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/create.html")
    public ModelAndView create(@ModelAttribute("hfpmEntityBindRule") HfpmEntityBindRule hfpmEntityBindRule) throws Throwable {
        ModelAndView mav = new ModelAndView();
        iHfpmEntityBindRuleSV.create(hfpmEntityBindRule);
        mav.addObject("hfpmEntityBindRuleId", hfpmEntityBindRule.getHfpmEntityBindRuleId());
        mav.setViewName("/hframe/hfpmEntityBindRule/hframe_hfpmEntityBindRule_create");
        return mav;
    }

    /**
    * 查询展示实体捆绑规则
    * @param hfpmEntityBindRule
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/list.html")
    public ModelAndView list(@ModelAttribute("hfpmEntityBindRule") HfpmEntityBindRule hfpmEntityBindRule) throws Throwable {
        ModelAndView mav = new ModelAndView();
        HfpmEntityBindRule_Example example = new HfpmEntityBindRule_Example();
        ExampleUtils.parseExample(hfpmEntityBindRule,example);

        List< HfpmEntityBindRule> hfpmEntityBindRuleList = iHfpmEntityBindRuleSV.getHfpmEntityBindRuleListByExample(example);

        mav.addObject("hfpmEntityBindRuleList", hfpmEntityBindRuleList);
        mav.setViewName("/hframe/hfpmEntityBindRule/hframe_hfpmEntityBindRule_list");
        return mav;
    }


    /**
    * 异步创建实体捆绑规则
    * @param hfpmEntityBindRule
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/createByAjax.html")
    @ResponseBody
    public ResultMessage createByAjax(@ModelAttribute("hfpmEntityBindRule") HfpmEntityBindRule hfpmEntityBindRule) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfpmEntityBindRuleSV.create(hfpmEntityBindRule);
        return message;
    }

    /**
    * 异步更新实体捆绑规则
    * @param hfpmEntityBindRule
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/updateByAjax.html")
    @ResponseBody
    public ResultMessage updateByAjax(@ModelAttribute("hfpmEntityBindRule") HfpmEntityBindRule hfpmEntityBindRule) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfpmEntityBindRuleSV.update(hfpmEntityBindRule);
        return message;
    }

    /**
    * 异步删除实体捆绑规则
    * @param hfpmEntityBindRule
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/deleteByAjax.html")
    @ResponseBody
    public ResultMessage deleteByAjax(@ModelAttribute("hfpmEntityBindRule") HfpmEntityBindRule hfpmEntityBindRule) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfpmEntityBindRuleSV.delete(hfpmEntityBindRule);
        return message;
    }
  	//getter
 	
	public IHfpmEntityBindRuleSV getIHfpmEntityBindRuleSV(){
		return iHfpmEntityBindRuleSV;
	}
	//setter
	public void setIHfpmEntityBindRuleSV(IHfpmEntityBindRuleSV iHfpmEntityBindRuleSV){
    	this.iHfpmEntityBindRuleSV = iHfpmEntityBindRuleSV;
    }
}
