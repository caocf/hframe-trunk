import com.hframe.domain.model.*;
import com.hframe.service.interfaces.*;
import com.hframework.generator.web.bean.HfModelContainer;
import com.hframework.generator.web.bean.HfModelContainerUtil;
import com.hframework.generator.web.sql.reverse.SQLParseUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangqh6 on 2015/10/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-config.xml")
public class SqlParseUtilTest {

    @Resource
    private IHfpmProgramSV iHfpmProgramSV;
    @Resource
    private IHfpmModuleSV iHfpmModuleSV;
    @Resource
    private IHfmdEntitySV iHfmdEntitySV;
    @Resource
    private IHfmdEntityAttrSV iHfmdEntityAttrSV;
    @Resource
    private IHfpmDataSetSV iHfpmDataSetSV;
    @Resource
    private IHfpmDataFieldSV iHfpmDataFieldSV;

    private HfModelContainer getModelContainerFromDB(String programCode, String programeName, String moduleCode, String moduleName) throws Exception {

        HfModelContainer hfModelContainer = new HfModelContainer();

        HfpmProgram_Example example = new HfpmProgram_Example();
        example.or() .andHfpmProgramCodeEqualTo(programCode);
        List<HfpmProgram> hfpmProgramList = iHfpmProgramSV.getHfpmProgramListByExample(example);

        if(hfpmProgramList != null && hfpmProgramList.size() > 0) {
            HfpmProgram hfpmProgram = hfpmProgramList.get(0);
            Long programId = hfpmProgram.getHfpmProgramId();

            hfModelContainer.setProgram(hfpmProgram);

            //获取模块信息
            HfpmModule_Example moduleExample = new HfpmModule_Example();
            moduleExample.or().andHfpmProgramIdEqualTo(programId);
            List<HfpmModule> hfpmModuleList = iHfpmModuleSV.getHfpmModuleListByExample(moduleExample);

            if(hfpmModuleList != null && hfpmModuleList.size() > 0) {
                for (HfpmModule hfpmModule : hfpmModuleList) {
                    hfModelContainer.getModuleMap().put(hfpmModule.getHfpmModuleId(),hfpmModule);
                }
            }

            //获取实体信息
            HfmdEntity_Example entityExample = new HfmdEntity_Example();
            entityExample.or().andHfpmProgramIdEqualTo(programId);
            List<HfmdEntity> hfmdEntityList = iHfmdEntitySV.getHfmdEntityListByExample(entityExample);
            Map<String,HfmdEntity> entityMap = new HashMap<String, HfmdEntity>();
            Map<Long,HfmdEntity> entityIdEntityMap = new HashMap<Long, HfmdEntity>();
            if(hfmdEntityList != null && hfmdEntityList.size() > 0) {
                //实体信息<entityCode,HfmdEntity>
                for (HfmdEntity entity : hfmdEntityList) {
                    entityMap.put(entity.getHfmdEntityCode(),entity);
                    entityIdEntityMap.put(entity.getHfmdEntityId(),entity);
                }
            }
            hfModelContainer.setEntityMap(entityMap);

            //获取实体属�?�信�?
            HfmdEntityAttr_Example entityAttrExample = new HfmdEntityAttr_Example();
            entityAttrExample.or().andHfpmProgramIdEqualTo(programId);
            List<HfmdEntityAttr> hfmdEntityAttrList = iHfmdEntityAttrSV.getHfmdEntityAttrListByExample(entityAttrExample);
            if(hfmdEntityAttrList != null && hfmdEntityAttrList.size() > 0) {
                //实体属�?�信�?<entityCode.entityAttrCode,HfmdEntityAttr>
                Map<String,HfmdEntityAttr> entityAttrMap = new HashMap<String, HfmdEntityAttr>();
                for (HfmdEntityAttr hfmdEntityAttr : hfmdEntityAttrList) {
                    HfmdEntity entity =  entityIdEntityMap.get(hfmdEntityAttr.getHfmdEntityId());
                    entityAttrMap.put(entity.getHfmdEntityCode() + "." + hfmdEntityAttr.getHfmdEntityAttrCode(),hfmdEntityAttr);
                }
                hfModelContainer.setEntityAttrMap(entityAttrMap);
            }

            //获取数据集信�?
            HfpmDataSet_Example dataSetExample = new HfpmDataSet_Example();
            dataSetExample.or() .andHfpmProgramIdEqualTo(programId);
            List<HfpmDataSet> hfpmDataSets = iHfpmDataSetSV.getHfpmDataSetListByExample(dataSetExample);
            if(hfpmDataSets != null) {
                Map<String, HfpmDataSet> dataSetMap = new HashMap<String, HfpmDataSet>();
                hfModelContainer.setDataSetMap(dataSetMap);
                Map<String, List<HfpmDataField>> dataFieldListMap = new HashMap<String, List<HfpmDataField>>();
                hfModelContainer.setDataFieldListMap(dataFieldListMap);
                for (HfpmDataSet hfpmDataSet : hfpmDataSets) {
                    dataSetMap.put(hfpmDataSet.getHfpmDataSetCode(),hfpmDataSet);
                    //获取数据列信�?
                    HfpmDataField_Example dataFieldExample = new HfpmDataField_Example();
                    dataFieldExample.or().andHfpmDataSetIdEqualTo(hfpmDataSet.getHfpmDataSetId());
                    List<HfpmDataField> hfpmDataFieldList = iHfpmDataFieldSV.getHfpmDataFieldListByExample(dataFieldExample);
                    dataFieldListMap.put(hfpmDataSet.getHfpmDataSetCode(), hfpmDataFieldList);
                }
            }
        }

        return hfModelContainer;
    }

    @Test
    public void testBetweenSQL() throws Exception {
        String rootClassPath = Thread.currentThread().getContextClassLoader ().getResource("").getPath();
        System.out.println(rootClassPath);
//        String filePath = rootClassPath + "\\reversesql\\sql.sql";
        String filePath ="/E:/myworkspace/hframe-trunk/hframe-generator/target/classes/reversesql/basicdata.sql";

        String programCode = "hframe";
        String programeName = "框架";
        String moduleCode = "hframe";
        String moduleName = "框架";
        HfModelContainer sqlFileModelContainer = SQLParseUtil.parseModelContainerFromSQLFile(
                filePath, programCode, programeName, moduleCode, moduleName);

        filePath ="/E:/myworkspace/hframe-trunk/hframe-generator/target/classes/reversesql/xfb.sql";

        HfModelContainer dbModelContainer = SQLParseUtil.parseModelContainerFromSQLFile(
                filePath, programCode, programeName, moduleCode, moduleName);

        HfModelContainer[] resultModelContainers =
                HfModelContainerUtil.mergerModelContainer(dbModelContainer, sqlFileModelContainer);

        SQLParseUtil.getSqlFromHfModelContainer(resultModelContainers[0]);
        SQLParseUtil.getSqlFromHfModelContainer(resultModelContainers[1]);


        resultModelContainers =
                HfModelContainerUtil.mergerEntityToDataSet(resultModelContainers, dbModelContainer);
        System.out.println(resultModelContainers);
    }


    @Test
    public void test() throws Exception {
        String rootClassPath = Thread.currentThread().getContextClassLoader ().getResource("").getPath();
        System.out.println(rootClassPath);
//        String filePath = rootClassPath + "\\reversesql\\sql.sql";
        String filePath ="/E:/myworkspace/hframe-trunk/hframe-generator/target/classes/reversesql/sql.sql";

        String programCode = "hframe";
        String programeName = "框架";
        String moduleCode = "hframe";
        String moduleName = "框架";
        HfModelContainer sqlFileModelContainer = SQLParseUtil.parseModelContainerFromSQLFile(
                filePath, programCode, programeName, moduleCode, moduleName);


        HfModelContainer dbModelContainer = getModelContainerFromDB(programCode, programeName, moduleCode, moduleName);

        HfModelContainer[] resultModelContainers =
                HfModelContainerUtil.mergerModelContainer(dbModelContainer, sqlFileModelContainer);

        resultModelContainers =
                HfModelContainerUtil.mergerEntityToDataSet(resultModelContainers,dbModelContainer);
        System.out.println(resultModelContainers);

        executeModelInsert(resultModelContainers[0]);
        executeModelUpdate(resultModelContainers[1]);

    }

    private void executeModel(HfModelContainer hfModelContainer,int operType) throws Exception {
        //项目信息
        HfpmProgram hfpmProgram = hfModelContainer.getProgram();
        if(hfpmProgram != null) {
            if(operType == 1) {
                iHfpmProgramSV.create(hfpmProgram);
            }else {
                iHfpmProgramSV.update(hfpmProgram);
            }
        }

        //模块信息
        for (HfpmModule targetModule : hfModelContainer.getModuleMap().values()) {
            if(operType == 1) {
                iHfpmModuleSV.create(targetModule);
            }else {
                iHfpmModuleSV.update(targetModule);
            }
        }
        //实体信息
        Map<String, HfmdEntity> targetEntityMap = hfModelContainer.getEntityMap();
        if(targetEntityMap != null) {
            for (String entityCode : targetEntityMap.keySet()) {
                HfmdEntity targetEntity = targetEntityMap.get(entityCode);
                if(operType == 1) {
                    iHfmdEntitySV.create(targetEntity);
                }else {
                    iHfmdEntitySV.update(targetEntity);
                }
            }
        }

        //实体属�?�信�?
        Map<String,HfmdEntityAttr> targetEntityAttrMap = hfModelContainer.getEntityAttrMap();
        if(targetEntityAttrMap != null) {
            for (String emtityAttrCode : targetEntityAttrMap.keySet()) {
                HfmdEntityAttr targetEntityAttr = targetEntityAttrMap.get(emtityAttrCode);
                if(operType == 1) {
                    iHfmdEntityAttrSV.create(targetEntityAttr);
                }else {
                    iHfmdEntityAttrSV.update(targetEntityAttr);
                }
            }
        }
        Map<String, HfpmDataSet> dataSetMap = hfModelContainer.getDataSetMap();
        if (dataSetMap != null) {
            for (HfpmDataSet hfpmDataSet : dataSetMap.values()) {
                if(operType == 1) {
                    iHfpmDataSetSV.create(hfpmDataSet);
                }else {
                    iHfpmDataSetSV.update(hfpmDataSet);
                }
            }
        }
        Map<String, List<HfpmDataField>> dataFieldListMap = hfModelContainer.getDataFieldListMap();
        if(dataFieldListMap != null) {
            for (List<HfpmDataField> hfpmDataFieldList : dataFieldListMap.values()) {
                if(hfpmDataFieldList != null) {
                    for (HfpmDataField hfpmDataField : hfpmDataFieldList) {
                        if(operType == 1) {
                            iHfpmDataFieldSV.create(hfpmDataField);
                        }else {
                            iHfpmDataFieldSV.update(hfpmDataField);
                        }
                    }
                }
            }
        }

    }

    private void executeModelUpdate(HfModelContainer hfModelContainer) throws Exception {
        executeModel(hfModelContainer,2);
    }

    private void executeModelInsert(HfModelContainer hfModelContainer) throws Exception {
        executeModel(hfModelContainer,1);
    }


}
