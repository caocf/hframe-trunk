
package com.hframe.service.handler;

import com.hframe.domain.model.*;
import com.hframe.service.interfaces.IHfcfgDbConnectSV;
import com.hframe.service.interfaces.IHfpmProgramSV;
import com.hframe.service.interfaces.IHfsecUserSV;
import com.hframework.base.bean.AbstractBusinessHandler;
import com.hframework.common.annotation.extension.AfterCreateHandler;
import com.hframework.common.annotation.extension.AfterUpdateHandler;
import com.hframework.common.util.FileUtils;
import com.hframework.common.util.message.VelocityUtil;
import com.hframework.common.util.message.XmlUtils;
import com.hframework.ext.datasoucce.DataSourceContextHolder;
import com.hframework.generator.util.CreatorUtil;
import com.hframework.generator.web.BaseGeneratorUtil;
import com.hframework.web.bean.WebContextHelper;
import com.hframework.web.config.bean.Program;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangquanhong on 2016/10/14.
 */
@Service
public class HfpmProgramCfgHandler extends AbstractBusinessHandler<HfpmProgramCfg> {

    @Resource
    private IHfcfgDbConnectSV iHfcfgDbConnectSV;
    @Resource
    private IHfsecUserSV hfsecUserSV;
    @Resource
    private IHfpmProgramSV hfpmProgramSV;

    @AfterCreateHandler
    @AfterUpdateHandler(attr = "hfcfgDbConnectId")
    public boolean dataSetSetting(HfpmProgramCfg hfpmProgramCfg) throws Exception {
        if(hfpmProgramCfg.getHfcfgDbConnectId() != null && hfpmProgramCfg.getHfcfgDbConnectId() > 0L){
            String companyCode = "hframe";
            HfpmProgram hfpmProgram = hfpmProgramSV.getHfpmProgramByPK(hfpmProgramCfg.getHfpmProgramId());
            String programCode = hfpmProgram.getHfpmProgramCode();
//            String programName = hfpmProgram.getHfpmProgramCode();
            String projectBasePath = CreatorUtil.getTargetProjectBasePath(companyCode,
                    "hframe".equals(programCode) ? "trunk" : programCode, null);
            HfcfgDbConnect dataSourceInfo = iHfcfgDbConnectSV.getHfcfgDbConnectByPK(hfpmProgramCfg.getHfcfgDbConnectId());
            if(dataSourceInfo == null) return true;

            //重定义项目的dataSource.properties文件
            Map map = new HashMap();
            map.put("Jdbc", new BaseGeneratorUtil.Jdbc(dataSourceInfo.getUrl().replaceAll("&", "&amp;"), dataSourceInfo.getUser(), dataSourceInfo.getPassword()));
            String content = VelocityUtil.produceTemplateContent("com/hframework/generator/vm/jdbcProperties.vm", map);
            System.out.println(content);
            FileUtils.writeFile(projectBasePath + "/hframe-core/src/main/resources/properties/dataSource.properties", content);

            //管理员入库 -db变更前需要先执行ddl语句，因此走db变更功能，不用做后台连带
//            Program program = XmlUtils.readValueFromAbsoluteFilePath(projectBasePath + "/hframe-web/src/main/resources/program/program.xml", Program.class);
//            DataSourceContextHolder.setDbInfo(dataSourceInfo.getUrl(), dataSourceInfo.getUser(), dataSourceInfo.getPassword());


//            HfsecUser_Example example = new HfsecUser_Example();
//            example.createCriteria().andAccountEqualTo(program.getSuperManager().getCode());
//            if(hfsecUserSV.getHfsecUserCountByExample(example) ==  0) {
//                HfsecUser hfsecUser = new HfsecUser();
//                hfsecUser.setHfsecUserName(program.getSuperManager().getName());
//                hfsecUser.setAccount(program.getSuperManager().getCode());
//                hfsecUser.setPassword(program.getSuperManager().getPassword());
//                hfsecUser.setCreateTime(hfpmProgramCfg.getCreateTime());
//                hfsecUser.setStatus(1);
//                hfsecUser.setAvatar("http://pic.hanhande.com/files/141127/1283574_094432_8946.jpg");
//                hfsecUserSV.create(hfsecUser);
//            }

//            DataSourceContextHolder.clear();
        }
        return true;
    }
}
