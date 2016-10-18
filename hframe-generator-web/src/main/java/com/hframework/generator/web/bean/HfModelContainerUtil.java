package com.hframework.generator.web.bean;

import com.hframework.common.util.CommonUtils;
import com.hframework.common.util.ResourceWrapper;
import com.hframework.common.util.StringUtils;
import com.hframe.domain.model.*;
import com.hframework.generator.enums.HfmdEntityAttr1AttrTypeEnum;
import javafx.util.Pair;

import java.net.URLEncoder;
import java.util.*;

/**
 * Created by zhangqh6 on 2015/10/25.
 */
public class HfModelContainerUtil {


    public static HfModelContainer[] mergerModelContainer(
            HfModelContainer baseModelContainer, HfModelContainer targetModelContainer){
        HfModelContainer[] result = new HfModelContainer[2];
        HfModelContainer  addModelContainer = new HfModelContainer();
        addModelContainer.setContainerType(HfModelContainer.TYPE_ADD);
        result[0] = addModelContainer;
        HfModelContainer  modModelContainer = new HfModelContainer();
        modModelContainer.setContainerType(HfModelContainer.TYPE_MOD);
        result[1] = modModelContainer;

        long oldProgramId = -1;
        long oldModelId = -1;

        HfpmProgram dbModelContainerProgram = baseModelContainer.getProgram();
        HfpmProgram targetModelContainerProgram = targetModelContainer.getProgram();
        if(dbModelContainerProgram == null) {
            addModelContainer.setProgram(targetModelContainerProgram);
        }else {
            oldProgramId = dbModelContainerProgram.getHfpmProgramId();
            if(!targetModelContainerProgram.getHfpmProgramName().equals(dbModelContainerProgram.getHfpmProgramName())){
                dbModelContainerProgram.setHfpmProgramName(targetModelContainerProgram.getHfpmProgramName());
                modModelContainer.setProgram(dbModelContainerProgram);
            }
        }

        Map<String ,HfpmModule> dbModuleMap = new HashMap<String, HfpmModule>();
        for (HfpmModule module : baseModelContainer.getModuleMap().values()) {
            dbModuleMap.put(module.getHfpmModuleCode(),module);
        }

        for (HfpmModule targetModule : targetModelContainer.getModuleMap().values()) {
            HfpmModule dbModule = dbModuleMap.get(targetModule.getHfpmModuleCode());
            if(dbModule == null) {
                addModelContainer.getModuleMap().put(targetModule.getHfpmModuleId(),targetModule);
            }else {
                oldModelId = dbModule.getHfpmModuleId();
                if(!dbModule.getHfpmModuleName().equals(targetModule.getHfpmModuleName())) {
                    dbModule.setHfpmModuleName(targetModule.getHfpmModuleName());
                    //这里需要关系是新的值，还是老的值
                    modModelContainer.getModuleMap().put(targetModule.getHfpmModuleId(),dbModule);
                }
            }
            if(oldProgramId != -1) {
                targetModule.setHfpmProgramId(oldProgramId);
            }

        }

        Map<String, HfmdEntity> dbEntityMap = baseModelContainer.getEntityMap();
        Map<String, HfmdEntity> targetEntityMap = targetModelContainer.getEntityMap();

        Map<String, HfmdEntity> addEntityMap = new HashMap<String, HfmdEntity>();
        addModelContainer.setEntityMap(addEntityMap);
        Map<String, HfmdEntity> modEntityMap = new HashMap<String, HfmdEntity>();
        modModelContainer.setEntityMap(modEntityMap);
        for (String entityCode : targetEntityMap.keySet()) {
            HfmdEntity targetEntity = targetEntityMap.get(entityCode);
            if(dbEntityMap == null) {
                dbEntityMap = new HashMap<String, HfmdEntity>();
//                addModelContainer.getEntityMap().put(entityCode, targetEntity);
//                continue;
            }
            HfmdEntity dbEntity = dbEntityMap.get(entityCode);
            if(dbEntity == null) {
                addModelContainer.getEntityMap().put(entityCode,targetEntity);
            }else if (targetEntity.getHfmdEntityName() != null &&
                    !targetEntity.getHfmdEntityName().equals(dbEntity.getHfmdEntityName())) {
                System.out.println("=> diff: curEntityName : " + dbEntity.getHfmdEntityName() +
                        "; targetEntityName : " + targetEntity.getHfmdEntityName());
                dbEntity.setHfmdEntityName(targetEntity.getHfmdEntityName());
                modModelContainer.getEntityMap().put(entityCode,dbEntity);
            }

            if(oldProgramId != -1) {
                targetEntity.setHfpmProgramId(oldProgramId);
                targetEntity.setHfpmModuleId(oldModelId);

            }
        }

        Map<String,HfmdEntityAttr> dbEntityAttrMap = baseModelContainer.getEntityAttrMap();
        Map<String,HfmdEntityAttr> targetEntityAttrMap = targetModelContainer.getEntityAttrMap();

        Map<String,HfmdEntityAttr> addEntityAttrMap = new HashMap<String, HfmdEntityAttr>();
        addModelContainer.setEntityAttrMap(addEntityAttrMap);
        Map<String,HfmdEntityAttr> modEntityAttrMap = new HashMap<String, HfmdEntityAttr>();
        modModelContainer.setEntityAttrMap(modEntityAttrMap);

        for (String emtityAttrCode : targetEntityAttrMap.keySet()) {
            HfmdEntityAttr targetEntityAttr = targetEntityAttrMap.get(emtityAttrCode);
            if(dbEntityAttrMap == null) {
                dbEntityAttrMap = new HashMap<String, HfmdEntityAttr>();
//                addModelContainer.getEntityAttrMap().put(emtityAttrCode, targetEntityAttr);
//                continue;
            }
            HfmdEntityAttr dbEntityAttr = dbEntityAttrMap.get(emtityAttrCode);
            if(dbEntityAttr == null) {
                dbEntityAttr = dbEntityAttrMap.get(emtityAttrCode.replace(targetEntityAttr.getHfmdEntityAttrCode(),
                        targetEntityAttr.getHfmdEntityAttrCode().toUpperCase()));
            }
            if(dbEntityAttr == null) {
                dbEntityAttr = dbEntityAttrMap.get(emtityAttrCode.replace(targetEntityAttr.getHfmdEntityAttrCode(),
                        targetEntityAttr.getHfmdEntityAttrCode().toLowerCase()));

            }


            if(targetEntityAttr != null && StringUtils.isBlank(targetEntityAttr.getSize())) {
                Integer defaultSize = HfmdEntityAttr1AttrTypeEnum.getDefaultSize(targetEntityAttr.getAttrType());
                if(defaultSize != null && defaultSize > 0) {
                    targetEntityAttr.setSize(String.valueOf(defaultSize));
                }
            }



            if(dbEntityAttr == null) {
                addModelContainer.getEntityAttrMap().put(emtityAttrCode,targetEntityAttr);
            }else if(dbEntityAttr.getAttrType() == null ) {
                System.out.println();
            }else if (dbEntityAttr.getHfmdEntityAttrName() != null
                    &&!( dbEntityAttr.getHfmdEntityAttrName().trim().equals(targetEntityAttr.getHfmdEntityAttrName().trim())
                    || checkEntityName(dbEntityAttr.getHfmdEntityAttrName().trim(), targetEntityAttr.getHfmdEntityAttrName().trim()))) {
                System.out.println("=> diff: entityCode : " + emtityAttrCode + "; curAttrName : " + dbEntityAttr.getHfmdEntityAttrName() +
                        "; targetAttrName : " + targetEntityAttr.getHfmdEntityAttrName());
                dbEntityAttr.setHfmdEntityAttrName(targetEntityAttr.getHfmdEntityAttrName());
                dbEntityAttr.setIspk(targetEntityAttr.getIspk());
                dbEntityAttr.setAttrType(targetEntityAttr.getAttrType());
                dbEntityAttr.setPri(targetEntityAttr.getPri());
                dbEntityAttr.setSize(targetEntityAttr.getSize());
                dbEntityAttr.setNullable(targetEntityAttr.getNullable());
                modModelContainer.getEntityAttrMap().put(emtityAttrCode,dbEntityAttr);
            }else if (!dbEntityAttr.getIspk().equals(targetEntityAttr.getIspk())) {
                System.out.println("=> diff: entityCode : " + emtityAttrCode + "; curIspk : " + dbEntityAttr.getIspk() +
                        "; targetIspk : " + targetEntityAttr.getIspk());
                dbEntityAttr.setHfmdEntityAttrName(targetEntityAttr.getHfmdEntityAttrName());
                dbEntityAttr.setIspk(targetEntityAttr.getIspk());
                dbEntityAttr.setAttrType(targetEntityAttr.getAttrType());
                dbEntityAttr.setPri(targetEntityAttr.getPri());
                dbEntityAttr.setSize(targetEntityAttr.getSize());
                dbEntityAttr.setNullable(targetEntityAttr.getNullable());
                modModelContainer.getEntityAttrMap().put(emtityAttrCode,dbEntityAttr);
            }else if (!dbEntityAttr.getAttrType().equals(targetEntityAttr.getAttrType())) {
                if(dbEntityAttr.getAttrType() == HfmdEntityAttr1AttrTypeEnum.NUMERIC.getIndex()
                        && targetEntityAttr.getAttrType() == HfmdEntityAttr1AttrTypeEnum.DECIMAL.getIndex()) {
                    continue;
                }

                if(targetEntityAttr.getAttrType() == HfmdEntityAttr1AttrTypeEnum.NUMERIC.getIndex()
                        && dbEntityAttr.getAttrType() == HfmdEntityAttr1AttrTypeEnum.DECIMAL.getIndex()) {
                    continue;
                }
                System.out.println("=> diff: entityCode : " + emtityAttrCode + "; curAttrType : " + dbEntityAttr.getAttrType() +
                        "; targetAttrType : " + targetEntityAttr.getAttrType());
                dbEntityAttr.setHfmdEntityAttrName(targetEntityAttr.getHfmdEntityAttrName());
                dbEntityAttr.setIspk(targetEntityAttr.getIspk());
                dbEntityAttr.setAttrType(targetEntityAttr.getAttrType());
                dbEntityAttr.setPri(targetEntityAttr.getPri());
                dbEntityAttr.setSize(targetEntityAttr.getSize());
                dbEntityAttr.setNullable(targetEntityAttr.getNullable());
                modModelContainer.getEntityAttrMap().put(emtityAttrCode,dbEntityAttr);
//            }else if (!(dbEntityAttr.getPri().compareTo(targetEntityAttr.getPri()) == 0)) {
//                System.out.println("=> diff:curPri : " + dbEntityAttr.getPri() +
//                        "; targetPri : " + targetEntityAttr.getPri());
//                dbEntityAttr.setHfmdEntityAttrName(targetEntityAttr.getHfmdEntityAttrName());
//                dbEntityAttr.setIspk(targetEntityAttr.getIspk());
//                dbEntityAttr.setAttrType(targetEntityAttr.getAttrType());
//                dbEntityAttr.setPri(targetEntityAttr.getPri());
//                dbEntityAttr.setSize(targetEntityAttr.getSize());
//                dbEntityAttr.setNullable(targetEntityAttr.getNullable());
//                modModelContainer.getEntityAttrMap().put(emtityAttrCode,dbEntityAttr);
            }else if (!dbEntityAttr.getNullable().equals(targetEntityAttr.getNullable())) {
                System.out.println("=> diff entityCode : " + emtityAttrCode + "; :curNullable : " + dbEntityAttr.getNullable() +
                        "; targetNullable : " + targetEntityAttr.getNullable());
                dbEntityAttr.setHfmdEntityAttrName(targetEntityAttr.getHfmdEntityAttrName());
                dbEntityAttr.setIspk(targetEntityAttr.getIspk());
                dbEntityAttr.setAttrType(targetEntityAttr.getAttrType());
                dbEntityAttr.setPri(targetEntityAttr.getPri());
                dbEntityAttr.setSize(targetEntityAttr.getSize());
                dbEntityAttr.setNullable(targetEntityAttr.getNullable());
                modModelContainer.getEntityAttrMap().put(emtityAttrCode,dbEntityAttr);
            }else if ((dbEntityAttr.getSize() != null && !dbEntityAttr.getSize().equals(targetEntityAttr.getSize()))) {
                System.out.println("=> diff:curSize : " + dbEntityAttr.getSize() +
                        "; targetSize : " + targetEntityAttr.getSize());
                dbEntityAttr.setHfmdEntityAttrName(targetEntityAttr.getHfmdEntityAttrName());
                dbEntityAttr.setIspk(targetEntityAttr.getIspk());
                dbEntityAttr.setAttrType(targetEntityAttr.getAttrType());
                dbEntityAttr.setPri(targetEntityAttr.getPri());
                dbEntityAttr.setSize(targetEntityAttr.getSize());
                dbEntityAttr.setNullable(targetEntityAttr.getNullable());
                modModelContainer.getEntityAttrMap().put(emtityAttrCode,dbEntityAttr);
            }

            HfmdEntity hfmdEntity = dbEntityMap.get(emtityAttrCode.split("\\.")[0].trim());
            if(hfmdEntity == null) {
                hfmdEntity = targetEntityMap.get(emtityAttrCode.split("\\.")[0].trim());
            }


            if(oldProgramId != -1 && hfmdEntity != null) {
                targetEntityAttr.setHfpmProgramId(hfmdEntity.getHfpmProgramId());
                targetEntityAttr.setHfpmModuleId(hfmdEntity.getHfpmModuleId());
                targetEntityAttr.setHfmdEntityId(hfmdEntity.getHfmdEntityId());
            }
            if(StringUtils.isBlank(targetEntityAttr.getHfmdEntityAttrName())) {
                targetEntityAttr.setHfmdEntityAttrName("");
            }
        }
        return result;
    }

    private static boolean checkEntityName(String s1, String s2) {
        s1 = s1.replace(":", " ").replace("\\n"," ");
        s2 = s2.replace(":", " ").replace("\\n"," ");
        if(s1.contains(" ")) {
            s1 = s1.substring(0, s1.indexOf(" "));
        }

        if(s2.contains(" ")) {
            s2 = s2.substring(0, s2.indexOf(" "));
        }
//        System.out.println(s1 +"|" + s2);
        return s1.equals(s2);
    }

    public static HfModelContainer[] mergerEntityToDataSet(HfModelContainer[] resultModelContainers, HfModelContainer dbModelContainer) {

        if(resultModelContainers == null) {
            return null;
        }

        HfModelContainer addModelContainer = resultModelContainers[0];
        resetReallyDBEntity(addModelContainer, dbModelContainer);
        mergerModelContainerSelf(addModelContainer, dbModelContainer);

        HfModelContainer modModelContainer = resultModelContainers[1];
        resetReallyDBEntity(modModelContainer,dbModelContainer);
        mergerModelContainerSelf(modModelContainer, dbModelContainer);


        return resultModelContainers;
    }

    private static void resetReallyDBEntity(HfModelContainer addModelContainer, HfModelContainer dbModelContainer) {
        Map<String, HfmdEntity> entityMap = addModelContainer.getEntityMap();
        for (String entityCode : entityMap.keySet()) {
            if(dbModelContainer.getEntity(entityCode) != null) {
                entityMap.put(entityCode, dbModelContainer.getEntity(entityCode) );
            }
        }

        Map<String, HfmdEntityAttr> entityAttrMap = addModelContainer.getEntityAttrMap();
        for (String entityAttrCode : entityAttrMap.keySet()) {
            if(dbModelContainer.getEntityAttrMap().get(entityAttrCode) != null) {
                entityAttrMap.put(entityAttrCode, dbModelContainer.getEntityAttrMap().get(entityAttrCode));
            }
        }
    }

    public static HfModelContainer[] mergerEntityToDataSetReturnOnly(HfModelContainer[] resultModelContainers, HfModelContainer dbModelContainer) {

        if(resultModelContainers == null) {
            return null;
        }

        HfModelContainer addModelContainer = resultModelContainers[0];
        resetReallyDBEntity(addModelContainer, dbModelContainer);

        mergerModelContainerSelf(addModelContainer, dbModelContainer);
        //避免重复执行
        Map<String, HfpmDataSet> dataSetMap = addModelContainer.getDataSetMap();
        Iterator<Map.Entry<String, HfpmDataSet>> iterator = dataSetMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, HfpmDataSet> next = iterator.next();
            String dataSetCode = next.getKey();
            if(dbModelContainer.getDataSetMap().containsKey(dataSetCode)) {
                iterator.remove();
                addModelContainer.getDataFieldListMap().remove(dataSetCode);
            }
        }


        HfModelContainer modModelContainer = resultModelContainers[1];
        resetReallyDBEntity(modModelContainer, dbModelContainer);
        mergerModelContainerSelf(modModelContainer, dbModelContainer);


        return new HfModelContainer[]{getDataSetOnly(addModelContainer), getDataSetOnly(modModelContainer)};
    }

    private static HfModelContainer getDataSetOnly(HfModelContainer originContainer) {
        HfModelContainer result = new HfModelContainer();
        result.setDataSetMap(originContainer.getDataSetMap());
        result.setDataFieldListMap(originContainer.getDataFieldListMap());
        return result;
    }

    private static void mergerModelContainerSelf(HfModelContainer modelContainer, HfModelContainer dbModelContainer) {
        if (modelContainer == null) {
            return;
        }

        Map<String, HfmdEntity> entityMap = modelContainer.getEntityMap();
        Map<String, HfmdEntityAttr> entityAttrMap = modelContainer.getEntityAttrMap();

        Map<String, HfpmDataSet> dataSetMap = new HashMap<String, HfpmDataSet>();
        modelContainer.setDataSetMap(dataSetMap);
        if (entityMap != null) {
            for (String entityCode : entityMap.keySet()) {
                HfmdEntity hfmdEntity = entityMap.get(entityCode);
                dataSetMap.put(entityCode,getDataSetFromEntity(hfmdEntity));
                dataSetMap.put(entityCode + "_DS4Q", getQryDataSetFromEntity(hfmdEntity));


            }
        }

        Map<String, List<HfpmDataField>> dataFieldListMap = new HashMap<String, List<HfpmDataField>>();
        modelContainer.setDataFieldListMap(dataFieldListMap);
        if (entityAttrMap != null) {
            for (String entityAttrEntityCode : entityAttrMap.keySet()) {
                String entityCode = entityAttrEntityCode.substring(0, entityAttrEntityCode.indexOf("."));
                HfmdEntityAttr hfmdEntityAttr = entityAttrMap.get(entityAttrEntityCode);
                if(!dataFieldListMap.containsKey(entityCode)) {
                    dataFieldListMap.put(entityCode,new ArrayList<HfpmDataField>());
                }

                HfpmDataSet dataSet;
                if(dataSetMap.containsKey(entityCode)) {
                    dataSet = dataSetMap.get(entityCode);
                }else {
                    dataSet =  dbModelContainer.getDataSetMap().get(entityCode);
                }

                //脏数据
                if(dataSet == null) {
                    continue;
                }

                dataFieldListMap.get(entityCode).add(getDataFieldFromEntityAttr(hfmdEntityAttr,dataSet));

                if(!dataFieldListMap.containsKey(entityCode + "_DS4Q")) {
                    dataFieldListMap.put(entityCode+"_DS4Q",new ArrayList<HfpmDataField>());
                }

                if(dataSetMap.containsKey(entityCode+"_DS4Q")) {
                    dataSet = dataSetMap.get(entityCode+"_DS4Q");
                }else {
                    dataSet =  dbModelContainer.getDataSetMap().get(entityCode+"_DS4Q");
                }

                dataFieldListMap.get(entityCode+"_DS4Q").addAll(getDS4QryDataFieldFromEntityAttr(hfmdEntityAttr, dataSet));
            }
        }
    }

    public static HfpmDataSet getDataSetFromEntity(HfmdEntity hfmdEntity) {
        HfpmDataSet hfpmDataSet = new HfpmDataSet( CommonUtils.uuidL(),
                hfmdEntity.getHfmdEntityName()+"【默认】",
                hfmdEntity.getHfmdEntityCode(),
                hfmdEntity.getHfmdEntityId(),
                hfmdEntity.getHfpmProgramId(),
                hfmdEntity.getOpId(),
                hfmdEntity.getCreateTime(),
                hfmdEntity.getModifyOpId(),
                hfmdEntity.getModifyTime(),
                hfmdEntity.getDelFlag());
        return hfpmDataSet;
    }

    public static HfpmDataSet getQryDataSetFromEntity(HfmdEntity hfmdEntity) {
        HfpmDataSet hfpmDataSet = new HfpmDataSet( CommonUtils.uuidL(),
                hfmdEntity.getHfmdEntityName()+"【查询】",
                hfmdEntity.getHfmdEntityCode()+"_DS4Q",
                hfmdEntity.getHfmdEntityId(),
                hfmdEntity.getHfpmProgramId(),
                hfmdEntity.getOpId(),
                hfmdEntity.getCreateTime(),
                hfmdEntity.getModifyOpId(),
                hfmdEntity.getModifyTime(),
                hfmdEntity.getDelFlag());
        return hfpmDataSet;
    }

    public static HfpmDataField getDataFieldFromEntityAttr(HfmdEntityAttr hfmdEntityAttr, HfpmDataSet dataSet) {
        HfpmDataField hfpmDataField= new HfpmDataField();
        hfpmDataField.setHfpmDataFieldId(CommonUtils.uuidL());
        hfpmDataField.setHfpmDataFieldCode(hfmdEntityAttr.getHfmdEntityAttrCode());
        hfpmDataField.setHfpmFieldShowTypeId(getFieldShowTypeIdByEntityAttr(hfmdEntityAttr));
        hfpmDataField.setFieldShowCode(getFieldShowCodeByEntityAttr(hfmdEntityAttr));
        hfpmDataField.setHfmdEntityId(hfmdEntityAttr.getHfmdEntityId());
        hfpmDataField.setHfmdEntityAttrId(hfmdEntityAttr.getHfmdEntityAttrId());
        hfpmDataField.setDataGetMethod(0);
        hfpmDataField.setHfpmDataFieldName(hfmdEntityAttr.getHfmdEntityAttrName());
        hfpmDataField.setHfpmDataSetId(dataSet.getHfpmDataSetId());
        hfpmDataField.setPri(hfmdEntityAttr.getPri());
        hfpmDataField.setOpId(hfmdEntityAttr.getOpId());
        hfpmDataField.setCreateTime(hfmdEntityAttr.getCreateTime());
        hfpmDataField.setModifyOpId(hfmdEntityAttr.getModifyOpId());
        hfpmDataField.setModifyTime(hfmdEntityAttr.getModifyTime());
        hfpmDataField.setDelFlag(hfmdEntityAttr.getDelFlag());

        return hfpmDataField;
    }

    public static List<HfpmDataField> getDS4QryDataFieldFromEntityAttr(HfmdEntityAttr hfmdEntityAttr, HfpmDataSet dataSet) {

        List<HfpmDataField> result = new ArrayList<HfpmDataField>();

        HfpmDataField dataFieldFromEntityAttr = null;
        if("pri".equals(hfmdEntityAttr.getHfmdEntityAttrCode().toLowerCase())) {
            return result;
        }else if("create_time".equals(hfmdEntityAttr.getHfmdEntityAttrCode().toLowerCase())
                || "modify_time".equals(hfmdEntityAttr.getHfmdEntityAttrCode().toLowerCase())) {
            dataFieldFromEntityAttr = getDataFieldFromEntityAttr(hfmdEntityAttr, dataSet);
            dataFieldFromEntityAttr.setHfpmDataFieldName(
                    dataFieldFromEntityAttr.getHfpmDataFieldName().replaceAll("时间","开始时间"));
            dataFieldFromEntityAttr.setHfpmDataFieldCode(
                    dataFieldFromEntityAttr.getHfpmDataFieldCode() + "_GEQ");
            dataFieldFromEntityAttr.setFieldShowCode("222");
            result.add(dataFieldFromEntityAttr);
            dataFieldFromEntityAttr = getDataFieldFromEntityAttr(hfmdEntityAttr, dataSet);
            dataFieldFromEntityAttr.setHfpmDataFieldName(
                    dataFieldFromEntityAttr.getHfpmDataFieldName().replaceAll("时间","结束时间"));
            dataFieldFromEntityAttr.setHfpmDataFieldCode(
                    dataFieldFromEntityAttr.getHfpmDataFieldCode() + "_LEQ");
            dataFieldFromEntityAttr.setFieldShowCode("222");
            result.add(dataFieldFromEntityAttr);
        }else {
            dataFieldFromEntityAttr = getDataFieldFromEntityAttr(hfmdEntityAttr, dataSet);
            dataFieldFromEntityAttr.setFieldShowCode("222");
            result.add(dataFieldFromEntityAttr);
        }

        return result;
    }

    /**
     *  新建/修改：固定枚举值个数大于2个为下拉框,
        新建/修改：固定枚举值个数不大于2个为单选框
        新建/修改：关联配置表下拉框选择
        新建/修改：关联业务表弹出框选择
        新建/修改：默认都为input框
     * @param hfmdEntityAttr
     * @return
     */
    public static String getFieldShowTypeIdByEntityAttr(HfmdEntityAttr hfmdEntityAttr) {

        if(hfmdEntityAttr.getHfmdEnumClassId()!= null && hfmdEntityAttr.getHfmdEnumClassId() > 0) {//枚举值对象
            return "2";//TODO
        }else if(hfmdEntityAttr.getRelHfmdEntityAttrId()!= null && hfmdEntityAttr.getRelHfmdEntityAttrId() > 0){ //外键对象
            return "2";//TODO
        }else if(hfmdEntityAttr.getAttrType() == HfmdEntityAttr1AttrTypeEnum.DATETIME.getIndex() || hfmdEntityAttr.getAttrType() == HfmdEntityAttr1AttrTypeEnum.DATE.getIndex()) {
            return "3";//TODO
        } else {
            return "1";//TODO
        }
    }

    public static String getFieldShowCodeByEntityAttr(HfmdEntityAttr hfmdEntityAttr) {

        if(hfmdEntityAttr.getHfmdEntityAttrCode().toLowerCase().endsWith("_id") && hfmdEntityAttr.getIspk() != null && hfmdEntityAttr.getIspk() == 1) {
            return "011";
        }else if("create_time".equals(hfmdEntityAttr.getHfmdEntityAttrCode().toLowerCase())
                || "op_id".equals(hfmdEntityAttr.getHfmdEntityAttrCode().toLowerCase())
                || "creator_id".equals(hfmdEntityAttr.getHfmdEntityAttrCode().toLowerCase())) {
            return "011";
        }else if("modify_op_id".equals(hfmdEntityAttr.getHfmdEntityAttrCode().toLowerCase())
                || "modify_time".equals(hfmdEntityAttr.getHfmdEntityAttrCode().toLowerCase())
                || "modifier_id".equals(hfmdEntityAttr.getHfmdEntityAttrCode().toLowerCase())) {
            return "001";
        }else if("del_flag".equals(hfmdEntityAttr.getHfmdEntityAttrCode().toLowerCase())) {
            return "021";
        }else if("pri".equals(hfmdEntityAttr.getHfmdEntityAttrCode().toLowerCase())) {
            return "000";
        }else {
            return "221";
        }
    }

    public static List<String> getSql(List<Map<String, Object>> result, String tableName) {
        if(result == null || result.size() == 0) return null;

        Map<String, List<String>> tmpMap = new LinkedHashMap<String, List<String>>();

        for (Map<String, Object> stringStringMap : result) {
            String[] keyValuePair = getKeyValuePair(stringStringMap);
            if(!tmpMap.containsKey(keyValuePair[0])) {
                tmpMap.put(keyValuePair[0], new ArrayList<String>());
            }
            tmpMap.get(keyValuePair[0]).add(keyValuePair[1]);
        }
        List<String> sqls = new ArrayList<String>();
        for (Map.Entry<String, List<String>> entry : tmpMap.entrySet()) {
            StringBuffer sql = new StringBuffer().append("insert into " +  tableName + entry.getKey() + " values ");
            for (String values : entry.getValue()) {
                sql.append(values).append(",");
            }
            sqls.add(sql.substring(0, sql.length() - 1));
        }

        return sqls;
    }

    private static String[] getKeyValuePair(Map<String, Object> stringStringMap) {
        List<Map.Entry<String, Object>> infoIds = new ArrayList<Map.Entry<String, Object>>(stringStringMap.entrySet());
        Collections.sort(infoIds,
                new Comparator<Map.Entry<String, Object>>() {
                    public int compare(Map.Entry<String, Object> o1,
                                       Map.Entry<String, Object> o2) {
                        return (o1.getKey()).toString().compareTo(
                                o2.getKey());
                    }
                });
        String key = "";
        String value = "";
        for (int i = 0; i < infoIds.size(); i++) {
            Map.Entry<String, Object> item = infoIds.get(i);
            key += (item.getKey() + ",");
            if (item.getValue() instanceof Long || item.getValue() instanceof Integer || item.getValue() instanceof Short
                    || item.getValue() instanceof Double || item.getValue() instanceof Float) {
                value += (item.getValue() + ",");
            }else {
                value += ("'" + item.getValue() + "',");
            }

        }

        key = "(" +  key.substring(0,key.length()-1)+ ")";
        value ="(" +  value.substring(0,value.length()-1) + ")";

        return new String[]{key, value};
    }

    public static List<String> getSql(HfModelContainer addContainer, HfModelContainer modifyContainer) {

        List<String> result = new ArrayList<String>();

        Map<HfmdEntity, List<HfmdEntityAttr>> newTableMap = new HashMap<HfmdEntity, List<HfmdEntityAttr>>();

        Map<String, HfmdEntityAttr> entityAttrMap = addContainer.getEntityAttrMap();
        for (String key : entityAttrMap.keySet()) {

            HfmdEntityAttr entityAttr = entityAttrMap.get(key);
            String entityName = key.substring(0, key.indexOf("."));

            if(addContainer.getEntityMap().containsKey(entityName)) {
                HfmdEntity hfmdEntity = addContainer.getEntityMap().get(entityName);
                if(!newTableMap.containsKey(hfmdEntity)) {
                    newTableMap.put(hfmdEntity, new ArrayList<HfmdEntityAttr>());
                }
                newTableMap.get(hfmdEntity).add(entityAttr);
            }else{
                StringBuffer sql = new StringBuffer();
                sql.append("alter table " + entityName + " add column " + getColumnInfo(entityAttr) + ";");
                result.add(sql.toString());
            }
        }

        for (HfmdEntity hfmdEntity : newTableMap.keySet()) {
            StringBuffer sql = new StringBuffer();
            sql.append("create table " + hfmdEntity.getHfmdEntityCode() + "(").append("\n");
            List<HfmdEntityAttr> hfmdEntityAttrs = newTableMap.get(hfmdEntity);
            Collections.sort(hfmdEntityAttrs, new Comparator<HfmdEntityAttr>() {
                public int compare(HfmdEntityAttr o1, HfmdEntityAttr o2) {
                    return o1.getPri().compareTo(o2.getPri());
                }
            });
            for (HfmdEntityAttr hfmdEntityAttr : hfmdEntityAttrs) {
                System.out.println("->" + hfmdEntity.getHfmdEntityCode());
                sql.append("   " + getColumnInfo(hfmdEntityAttr)).append(",").append("\n");
            }
            sql.delete(sql.length() - 2, sql.length()).append(")");
            if(StringUtils.isNotBlank(hfmdEntity.getHfmdEntityName())) {
                sql.append(" comment '" + hfmdEntity.getHfmdEntityName() + "'");
            }
            sql.append(";");
            result.add(sql.toString());
        }

        for (HfmdEntity hfmdEntity : modifyContainer.getEntityMap().values()) {
            StringBuffer sql = new StringBuffer();
            sql.append("alter table " + hfmdEntity.getHfmdEntityCode() + " comment " + hfmdEntity.getHfmdEntityName() + ";");
            result.add(sql.toString());
        }

        entityAttrMap = modifyContainer.getEntityAttrMap();
        for (String key : entityAttrMap.keySet()) {
            HfmdEntityAttr entityAttr = entityAttrMap.get(key);
            String entityName = key.substring(0, key.indexOf("."));
            StringBuffer sql = new StringBuffer();
            sql.append("alter table " + entityName + " modify column " + getColumnInfo(entityAttr) + ";");
            result.add(sql.toString());
        }

        return result;
    }


    private static String getColumnInfo(HfmdEntityAttr entityAttr) {
        if(entityAttr !=null) {
            System.out.println("=>" + entityAttr.getHfmdEntityAttrCode() + "|" + entityAttr.getAttrType() + "|" + entityAttr.getSize() + "|" + entityAttr.getIspk() + "|" + entityAttr.getNullable());
            return entityAttr.getHfmdEntityAttrCode() + " " + HfmdEntityAttr1AttrTypeEnum.getName(entityAttr.getAttrType())
                    + (StringUtils.isNotBlank(entityAttr.getSize()) ? ("(" + entityAttr.getSize() + ")" ): "")
                    + (entityAttr.getIspk() != null && entityAttr.getIspk() == 1? " primary key auto_increment" : "")
                    + (entityAttr.getNullable() != null && entityAttr.getNullable() == 1? "" : " not null")
                    + (StringUtils.isNotBlank(entityAttr.getHfmdEntityAttrName()) ? " comment '" + entityAttr.getHfmdEntityAttrName() + "'" : "");
        }
        return null;

    }

    public static HfModelContainer getInstance() {
        return new HfModelContainer();
    }
}

