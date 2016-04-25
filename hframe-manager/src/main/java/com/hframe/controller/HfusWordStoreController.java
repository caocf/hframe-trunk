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
import com.hframe.domain.model.HfusWordStore;
import com.hframe.domain.model.HfusWordStore_Example;
import com.hframe.service.interfaces.IHfusWordStoreSV;

@Controller
@RequestMapping(value = "/hframe/hfusWordStore")
public class HfusWordStoreController   {

	@Resource
	private IHfusWordStoreSV iHfusWordStoreSV;
  


    /**
    * 创建单词库
    * @param hfusWordStore
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/create.html")
    public ModelAndView create(@ModelAttribute("hfusWordStore") HfusWordStore hfusWordStore) throws Throwable {
        ModelAndView mav = new ModelAndView();
        iHfusWordStoreSV.create(hfusWordStore);
        mav.addObject("hfusWordStoreId", hfusWordStore.getHfusWordStoreId());
        mav.setViewName("/hframe/hfusWordStore/hframe_hfusWordStore_create");
        return mav;
    }

    /**
    * 查询展示单词库
    * @param hfusWordStore
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/list.html")
    public ModelAndView list(@ModelAttribute("hfusWordStore") HfusWordStore hfusWordStore) throws Throwable {
        ModelAndView mav = new ModelAndView();
        HfusWordStore_Example example = new HfusWordStore_Example();
        ExampleUtils.parseExample(hfusWordStore,example);

        List< HfusWordStore> hfusWordStoreList = iHfusWordStoreSV.getHfusWordStoreListByExample(example);

        mav.addObject("hfusWordStoreList", hfusWordStoreList);
        mav.setViewName("/hframe/hfusWordStore/hframe_hfusWordStore_list");
        return mav;
    }


    /**
    * 异步创建单词库
    * @param hfusWordStore
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/createByAjax.html")
    @ResponseBody
    public ResultMessage createByAjax(@ModelAttribute("hfusWordStore") HfusWordStore hfusWordStore) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfusWordStoreSV.create(hfusWordStore);
        return message;
    }

    /**
    * 异步更新单词库
    * @param hfusWordStore
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/updateByAjax.html")
    @ResponseBody
    public ResultMessage updateByAjax(@ModelAttribute("hfusWordStore") HfusWordStore hfusWordStore) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfusWordStoreSV.update(hfusWordStore);
        return message;
    }

    /**
    * 异步删除单词库
    * @param hfusWordStore
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/deleteByAjax.html")
    @ResponseBody
    public ResultMessage deleteByAjax(@ModelAttribute("hfusWordStore") HfusWordStore hfusWordStore) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfusWordStoreSV.delete(hfusWordStore);
        return message;
    }
  	//getter
 	
	public IHfusWordStoreSV getIHfusWordStoreSV(){
		return iHfusWordStoreSV;
	}
	//setter
	public void setIHfusWordStoreSV(IHfusWordStoreSV iHfusWordStoreSV){
    	this.iHfusWordStoreSV = iHfusWordStoreSV;
    }
}
