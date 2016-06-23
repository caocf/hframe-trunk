package com.hframe.service.impl;

import java.util.*;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.hframe.domain.model.HfpmPage;
import com.hframe.domain.model.HfpmPage_Example;
import com.hframe.dao.HfpmPageMapper;
import com.hframe.service.interfaces.IHfpmPageSV;

@Service("iHfpmPageSV")
public class HfpmPageSVImpl  implements IHfpmPageSV {

	@Resource
	private HfpmPageMapper hfpmPageMapper;
  
    /**
    * 创建页面
    * @param hfpmPage
    * @return
    * @throws Exception
    */
    public int create(HfpmPage hfpmPage) throws Exception {
        return hfpmPageMapper.insertSelective(hfpmPage);
    }

    /**
    * 批量维护页面
    * @param hfpmPages
    * @return
    * @throws Exception
    */
    public int batchOperate(HfpmPage[] hfpmPages) throws  Exception{
        int result = 0;
        if(hfpmPages != null) {
            for (HfpmPage hfpmPage : hfpmPages) {
                if(hfpmPage.getHfpmPageId() == null) {
                    result += hfpmPageMapper.insertSelective(hfpmPage);
                }else {
                    result += hfpmPageMapper.updateByPrimaryKey(hfpmPage);
                }
            }
        }
        return result;
    }

    /**
    * 更新页面
    * @param hfpmPage
    * @return
    * @throws Exception
    */
    public int update(HfpmPage hfpmPage) throws  Exception {
        return hfpmPageMapper.updateByPrimaryKeySelective(hfpmPage);
    }

    /**
    * 通过查询对象更新页面
    * @param hfpmPage
    * @param example
    * @return
    * @throws Exception
    */
    public int updateByExample(HfpmPage hfpmPage, HfpmPage_Example example) throws  Exception {
        return hfpmPageMapper.updateByExampleSelective(hfpmPage, example);
    }

    /**
    * 删除页面
    * @param hfpmPage
    * @return
    * @throws Exception
    */
    public int delete(HfpmPage hfpmPage) throws  Exception {
        return hfpmPageMapper.deleteByPrimaryKey(hfpmPage.getHfpmPageId());
    }

    /**
    * 删除页面
    * @param hfpmPageId
    * @return
    * @throws Exception
    */
    public int delete(long hfpmPageId) throws  Exception {
        return hfpmPageMapper.deleteByPrimaryKey(hfpmPageId);
    }

    /**
    * 查找所有页面
    * @return
    */
    public List<HfpmPage> getHfpmPageAll()  throws  Exception {
        return hfpmPageMapper.selectByExample(new HfpmPage_Example());
    }

    /**
    * 通过页面ID查询页面
    * @param hfpmPageId
    * @return
    * @throws Exception
    */
    public HfpmPage getHfpmPageByPK(long hfpmPageId)  throws  Exception {
        return hfpmPageMapper.selectByPrimaryKey(hfpmPageId);
    }

    /**
    * 通过MAP参数查询页面
    * @param params
    * @return
    * @throws Exception
    */
    public List<HfpmPage> getHfpmPageListByParam(Map<String, Object> params)  throws  Exception {
        return null;
    }



    /**
    * 通过查询对象查询页面
    * @param example
    * @return
    * @throws Exception
    */
    public List<HfpmPage> getHfpmPageListByExample(HfpmPage_Example example) throws  Exception {
        return hfpmPageMapper.selectByExample(example);
    }

    /**
    * 通过MAP参数查询页面数量
    * @param params
    * @return
    * @throws Exception
    */
    public int getHfpmPageCountByParam(Map<String, Object> params)  throws  Exception {
        return 0;
    }

    /**
    * 通过查询对象查询页面数量
    * @param example
    * @return
    * @throws Exception
    */
    public int getHfpmPageCountByExample(HfpmPage_Example example) throws  Exception {
        return hfpmPageMapper.countByExample(example);
    }


  	//getter
 	
	public HfpmPageMapper getHfpmPageMapper(){
		return hfpmPageMapper;
	}
	//setter
	public void setHfpmPageMapper(HfpmPageMapper hfpmPageMapper){
    	this.hfpmPageMapper = hfpmPageMapper;
    }
}
