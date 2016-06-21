package com.hframe.controller;

import com.hframe.domain.model.HfpmPage;
import com.hframe.domain.model.HfpmPage_Example;
import com.hframe.service.interfaces.IHfpmPageSV;
import com.hframework.beans.controller.Pagination;
import com.hframework.beans.controller.ResultCode;
import com.hframework.beans.controller.ResultData;
import com.hframework.common.util.ExampleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/hframe/hfpmPage")
public class HfpmPageController   {
    private static final Logger logger = LoggerFactory.getLogger(HfpmPageController.class);

	@Resource
	private IHfpmPageSV iHfpmPageSV;
  




    @InitBinder
    protected void initBinder(HttpServletRequest request,
        ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CustomDateEditor editor = new CustomDateEditor(df, false);
        binder.registerCustomEditor(Date.class, editor);
    }

    /**
     * 查询展示页面列表
     * @param hfpmPage
     * @return
     * @throws Throwable
     */
    @RequestMapping(value = "/queryListByAjax.json")
    @ResponseBody
    public ResultData list(@ModelAttribute("hfpmPage") HfpmPage hfpmPage,
                                      @ModelAttribute("example") HfpmPage_Example example, Pagination pagination){
        logger.debug("request : {},{},{}", hfpmPage, example, pagination);
        try{
            ExampleUtils.parseExample(hfpmPage, example);
            //设置分页信息
            example.setLimitStart(pagination.getStartIndex());
            example.setLimitEnd(pagination.getEndIndex());

            final List< HfpmPage> list = iHfpmPageSV.getHfpmPageListByExample(example);
            pagination.setTotalCount(iHfpmPageSV.getHfpmPageCountByExample(example));

            return ResultData.success().add("list",list).add("pagination",pagination);
        }catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
    }

    /**
     * 查询展示页面明细
     * @param hfpmPage
     * @return
     * @throws Throwable
     */
    @RequestMapping(value = "/queryOneByAjax.json")
    @ResponseBody
    public ResultData detail(@ModelAttribute("hfpmPage") HfpmPage hfpmPage){
        logger.debug("request : {},{}", hfpmPage.getHfpmPageId(), hfpmPage);
        try{
            HfpmPage result = iHfpmPageSV.getHfpmPageByPK(hfpmPage.getHfpmPageId());
            if(result != null) {
                return ResultData.success(result);
            }else {
                return ResultData.error(ResultCode.RECODE_IS_NOT_EXISTS);
            }
        }catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
    }

    /**
    * 创建页面
    * @param hfpmPage
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/createByAjax.json")
    @ResponseBody
    public ResultData create(@ModelAttribute("hfpmPage") HfpmPage hfpmPage) {
        logger.debug("request : {}", hfpmPage);
        try {
            int result = iHfpmPageSV.create(hfpmPage);
            if(result > 0) {
                return ResultData.success(hfpmPage);
            }
        } catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
        return ResultData.error(ResultCode.UNKNOW);
    }

    /**
    * 更新页面
    * @param hfpmPage
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/updateByAjax.json")
    @ResponseBody
    public ResultData update(@ModelAttribute("hfpmPage") HfpmPage hfpmPage) {
        logger.debug("request : {}", hfpmPage);
        try {
            int result = iHfpmPageSV.update(hfpmPage);
            if(result > 0) {
                return ResultData.success(hfpmPage);
            }
        } catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
        return ResultData.error(ResultCode.UNKNOW);
    }

    /**
    * 删除页面
    * @param hfpmPage
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/deleteByAjax.json")
    @ResponseBody
    public ResultData delete(@ModelAttribute("hfpmPage") HfpmPage hfpmPage) {
        logger.debug("request : {}", hfpmPage);

        try {
            int result = iHfpmPageSV.delete(hfpmPage);
            if(result > 0) {
                return ResultData.success(hfpmPage);
            }else {
                return ResultData.error(ResultCode.RECODE_IS_NOT_EXISTS);
            }
        } catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
    }
  	//getter
 	
	public IHfpmPageSV getIHfpmPageSV(){
		return iHfpmPageSV;
	}
	//setter
	public void setIHfpmPageSV(IHfpmPageSV iHfpmPageSV){
    	this.iHfpmPageSV = iHfpmPageSV;
    }
}
