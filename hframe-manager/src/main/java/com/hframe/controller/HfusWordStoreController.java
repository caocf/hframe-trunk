package com.hframe.controller;

import com.hframework.beans.controller.Pagination;
import com.hframework.beans.controller.ResultCode;
import com.hframework.beans.controller.ResultData;
import com.hframework.common.util.ExampleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(HfusWordStoreController.class);

	@Resource
	private IHfusWordStoreSV iHfusWordStoreSV;
  




    /**
     * 查询展示单词库列表
     * @param hfusWordStore
     * @return
     * @throws Throwable
     */
    @RequestMapping(value = "/queryListByAjax.html")
    @ResponseBody
    public ResultData list(@ModelAttribute("hfusWordStore") HfusWordStore hfusWordStore,
                                      @ModelAttribute("example") HfusWordStore_Example example, Pagination pagination){
        logger.debug("request : {},{},{}", hfusWordStore, example, pagination);
        try{
            ExampleUtils.parseExample(hfusWordStore, example);
            //设置分页信息
            example.setLimitStart(pagination.getStartIndex());
            example.setLimitEnd(pagination.getEndIndex());

            final List< HfusWordStore> list = iHfusWordStoreSV.getHfusWordStoreListByExample(example);
            pagination.setTotalCount(iHfusWordStoreSV.getHfusWordStoreCountByExample(example));

            return ResultData.success().add("list",list).add("pagination",pagination);
        }catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
    }

    /**
     * 查询展示单词库明细
     * @param hfusWordStore
     * @return
     * @throws Throwable
     */
    @RequestMapping(value = "/queryOneByAjax.html")
    @ResponseBody
    public ResultData detail(@ModelAttribute("hfusWordStore") HfusWordStore hfusWordStore){
        logger.debug("request : {},{}", hfusWordStore.getHfusWordStoreId(), hfusWordStore);
        try{
            HfusWordStore result = iHfusWordStoreSV.getHfusWordStoreByPK(hfusWordStore.getHfusWordStoreId());
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
    * 创建单词库
    * @param hfusWordStore
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/createByAjax.html")
    @ResponseBody
    public ResultData create(@ModelAttribute("hfusWordStore") HfusWordStore hfusWordStore) {
        logger.debug("request : {}", hfusWordStore);
        try {
            int result = iHfusWordStoreSV.create(hfusWordStore);
            if(result > 0) {
                return ResultData.success(hfusWordStore);
            }
        } catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
        return ResultData.error(ResultCode.UNKNOW);
    }

    /**
    * 更新单词库
    * @param hfusWordStore
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/updateByAjax.html")
    @ResponseBody
    public ResultData update(@ModelAttribute("hfusWordStore") HfusWordStore hfusWordStore) {
        logger.debug("request : {}", hfusWordStore);
        try {
            int result = iHfusWordStoreSV.update(hfusWordStore);
            if(result > 0) {
                return ResultData.success(hfusWordStore);
            }
        } catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
        return ResultData.error(ResultCode.UNKNOW);
    }

    /**
    * 删除单词库
    * @param hfusWordStore
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/deleteByAjax.html")
    @ResponseBody
    public ResultData delete(@ModelAttribute("hfusWordStore") HfusWordStore hfusWordStore) {
        logger.debug("request : {}", hfusWordStore);

        try {
            int result = iHfusWordStoreSV.delete(hfusWordStore);
            if(result > 0) {
                return ResultData.success(hfusWordStore);
            }else {
                return ResultData.error(ResultCode.RECODE_IS_NOT_EXISTS);
            }
        } catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
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
