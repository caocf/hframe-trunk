package com.hframe.controller;

import com.hframe.controller.bean.ResultMessage;
import com.hframe.domain.model.HfmdEntity;
import com.hframe.domain.model.HfmdEntity_Example;
import com.hframe.service.interfaces.IHfmdEntitySV;
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
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/hframe/hfmdEntity")
public class HfmdEntityController_NEW {
    private static final Logger logger = LoggerFactory.getLogger(HfmdEntityController_NEW.class);

	@Resource
	private IHfmdEntitySV iHfmdEntitySV;

    /**
     * 查询展示实体列表
     * @param hfmdEntity
     * @return
     * @throws Throwable
     */
    @RequestMapping(value = "/queryListByAjax.html")
    public ResultData list(@ModelAttribute("hfmdEntity") HfmdEntity hfmdEntity,
                                      @ModelAttribute("example") HfmdEntity_Example example, Pagination pagination){
        logger.debug("request : {},{},{}", hfmdEntity, example, pagination);
        try{
            ExampleUtils.parseExample(hfmdEntity, example);

            example.setLimitStart(pagination.getStartIndex());
            example.setLimitEnd(pagination.getEndIndex());

            final List< HfmdEntity> list = iHfmdEntitySV.getHfmdEntityListByExample(example);
            pagination.setTotalCount(iHfmdEntitySV.getHfmdEntityCountByExample(example));

            return ResultData.success().add("list",list).add("pagination",pagination);
        }catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }

//        ExampleUtils.parseExample(hfmdEntity, HfmdEntity_Example.class);
//        example.getOredCriteria().get(0).andCreateTimeBetween(new Date(), new Date());
    }

    /**
     * 查询展示实体列表
     * @param hfmdEntity
     * @return
     * @throws Throwable
     */
    @RequestMapping(value = "/queryOneByAjax.html")
    public ResultData detail(@ModelAttribute("hfmdEntity") HfmdEntity hfmdEntity){
        logger.debug("request : {}", hfmdEntity);
        try{
            HfmdEntity result = iHfmdEntitySV.getHfmdEntityByPK(hfmdEntity.getHfmdEntityId());
            if(result != null) {
                return ResultData.success(result);
            }else {
                return ResultData.error("1001");
            }
        }catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
    }

    /**
    * 异步创建实体
    * @param hfmdEntity
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/createByAjax.html")
    @ResponseBody
    public ResultData create(@ModelAttribute("hfmdEntity") HfmdEntity hfmdEntity) {
        logger.debug("request : {}", hfmdEntity);
        try {
            int result = iHfmdEntitySV.create(hfmdEntity);
            if(result > 0) {
                return ResultData.success(hfmdEntity);
            }
        } catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
        return ResultData.error(ResultCode.UNKNOW);
    }

    /**
    * 异步更新实体
    * @param hfmdEntity
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/updateByAjax.html")
    @ResponseBody
    public ResultData update(@ModelAttribute("hfmdEntity") HfmdEntity hfmdEntity) {
        logger.debug("request : {}", hfmdEntity);
        try {
            int result = iHfmdEntitySV.update(hfmdEntity);
            if(result > 0) {
                return ResultData.success(hfmdEntity);
            }
        } catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
        return ResultData.error(ResultCode.UNKNOW);
    }

    /**
    * 异步删除实体
    * @param hfmdEntity
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/deleteByAjax.html")
    @ResponseBody
    public ResultData delete(@ModelAttribute("hfmdEntity") HfmdEntity hfmdEntity) {
        logger.debug("request : {}", hfmdEntity);

        try {
            int result = iHfmdEntitySV.delete(hfmdEntity);
            if(result > 0) {
                return ResultData.success(hfmdEntity);
            }
        } catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
        return ResultData.error(ResultCode.UNKNOW);
    }

}
