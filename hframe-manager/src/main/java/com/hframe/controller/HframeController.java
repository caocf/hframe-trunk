package com.hframe.controller;

import com.hframework.common.util.DateUtils;
import com.hframe.controller.bean.ResultMessage;
import com.hframe.domain.model.HfmdEntity;
import com.hframe.domain.model.HfpmPage;
import com.hframe.domain.model.HfpmProgramCfg;
import com.hframe.service.interfaces.IHfmdEntitySV;
import com.hframe.service.interfaces.IHfpmPageSV;
import com.hframe.service.interfaces.IHfpmProgramCfgSV;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/hframe")
public class HframeController {

	@Resource
	private IHfpmPageSV iHfpmPageSV;

    @Resource
    IHfpmProgramCfgSV iHfpmProgramCfgSV;

    @Resource
    private IHfmdEntitySV iHfmdEntitySV;

    /**
    * 默认页面生成
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/create_page_default.html")
    @ResponseBody
    public ResultMessage createPageDefault() throws Throwable {
        ResultMessage message = new ResultMessage();
        List<HfmdEntity> hfmdEntityAll = iHfmdEntitySV.getHfmdEntityAll();
        if(hfmdEntityAll != null) {
            for (HfmdEntity entity : hfmdEntityAll) {
                if(entity.getHfmdEntityType() == 0) {
                    HfpmPage hfpmPage = new HfpmPage();
                    hfpmPage.setHfpmPageId(null);
                    hfpmPage.setHfpmPageCode(entity.getHfmdEntityCode() + "_index");
                    hfpmPage.setHfpmPageName(entity.getHfmdEntityName());
                    hfpmPage.setHfpmPageType(1);
                    hfpmPage.setHfpmPageDesc(entity.getHfmdEntityDesc());
                    hfpmPage.setParentHfpmPageId(null);
                    hfpmPage.setHfpmProgramId(entity.getHfpmProgramId());
                    hfpmPage.setHfpmModuleId(entity.getHfpmModuleId());
                    hfpmPage.setPri(null);
                    hfpmPage.setOpId(999L);
                    hfpmPage.setCreateTime(DateUtils.getCurrentDate());
                    hfpmPage.setModifyOpId(999L);
                    hfpmPage.setModifyTime(DateUtils.getCurrentDate());
                    hfpmPage.setDelFlag(0);
                    iHfpmPageSV.create(hfpmPage);
                }
            }
        }
        return message;
    }


    /**
     * 首页
     * @return
     * @throws Throwable
     */
    @RequestMapping(value = "/index.html")
    public ModelAndView index() throws Throwable {
        ModelAndView mav = new ModelAndView();

        HfpmProgramCfg hfpmProgramCfg = iHfpmProgramCfgSV.getHfpmProgramCfgByPK(1);
        mav.addObject("hfpmProgramCfg", hfpmProgramCfg);
        mav.setViewName("/template");
        return mav;
    }

}
