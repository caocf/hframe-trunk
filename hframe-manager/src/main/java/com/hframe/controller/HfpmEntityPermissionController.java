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
import com.hframe.domain.model.HfpmEntityPermission;
import com.hframe.domain.model.HfpmEntityPermission_Example;
import com.hframe.service.interfaces.IHfpmEntityPermissionSV;

@Controller
@RequestMapping(value = "/hframe/hfpmEntityPermission")
public class HfpmEntityPermissionController   {

	@Resource
	private IHfpmEntityPermissionSV iHfpmEntityPermissionSV;
  


    /**
    * 创建实体权限
    * @param hfpmEntityPermission
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/create.html")
    public ModelAndView create(@ModelAttribute("hfpmEntityPermission") HfpmEntityPermission hfpmEntityPermission) throws Throwable {
        ModelAndView mav = new ModelAndView();
        iHfpmEntityPermissionSV.create(hfpmEntityPermission);
        mav.addObject("hfpmEntityPermissionId", hfpmEntityPermission.getHfpmEntityPermissionId());
        mav.setViewName("/hframe/hfpmEntityPermission/hframe_hfpmEntityPermission_create");
        return mav;
    }

    /**
    * 查询展示实体权限
    * @param hfpmEntityPermission
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/list.html")
    public ModelAndView list(@ModelAttribute("hfpmEntityPermission") HfpmEntityPermission hfpmEntityPermission) throws Throwable {
        ModelAndView mav = new ModelAndView();
        HfpmEntityPermission_Example example = new HfpmEntityPermission_Example();
        ExampleUtils.parseExample(hfpmEntityPermission,example);

        List< HfpmEntityPermission> hfpmEntityPermissionList = iHfpmEntityPermissionSV.getHfpmEntityPermissionListByExample(example);

        mav.addObject("hfpmEntityPermissionList", hfpmEntityPermissionList);
        mav.setViewName("/hframe/hfpmEntityPermission/hframe_hfpmEntityPermission_list");
        return mav;
    }


    /**
    * 异步创建实体权限
    * @param hfpmEntityPermission
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/createByAjax.html")
    @ResponseBody
    public ResultMessage createByAjax(@ModelAttribute("hfpmEntityPermission") HfpmEntityPermission hfpmEntityPermission) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfpmEntityPermissionSV.create(hfpmEntityPermission);
        return message;
    }

    /**
    * 异步更新实体权限
    * @param hfpmEntityPermission
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/updateByAjax.html")
    @ResponseBody
    public ResultMessage updateByAjax(@ModelAttribute("hfpmEntityPermission") HfpmEntityPermission hfpmEntityPermission) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfpmEntityPermissionSV.update(hfpmEntityPermission);
        return message;
    }

    /**
    * 异步删除实体权限
    * @param hfpmEntityPermission
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/deleteByAjax.html")
    @ResponseBody
    public ResultMessage deleteByAjax(@ModelAttribute("hfpmEntityPermission") HfpmEntityPermission hfpmEntityPermission) throws Throwable {
        ResultMessage message = new ResultMessage();
        iHfpmEntityPermissionSV.delete(hfpmEntityPermission);
        return message;
    }
  	//getter
 	
	public IHfpmEntityPermissionSV getIHfpmEntityPermissionSV(){
		return iHfpmEntityPermissionSV;
	}
	//setter
	public void setIHfpmEntityPermissionSV(IHfpmEntityPermissionSV iHfpmEntityPermissionSV){
    	this.iHfpmEntityPermissionSV = iHfpmEntityPermissionSV;
    }
}
