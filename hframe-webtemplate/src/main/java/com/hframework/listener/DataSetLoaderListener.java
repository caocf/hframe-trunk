package com.hframework.listener;

import com.hframe.domain.model.*;
import com.hframe.service.interfaces.*;
import com.hframe.tag.bean.*;
import com.hframe.tag.bean.Field;
import com.hframe.tag.util.ClassDeclaredUtils;
import com.hframework.beans.class0.*;
import com.hframework.beans.class0.Class;
import com.hframework.beans.class0.Column;
import com.hframework.common.frame.cache.CacheFactory;
import com.hframework.common.frame.cache.CacheKeyEnum;
import com.hframework.common.frame.cache.PropertyConfigurerUtils;
import com.hframework.common.util.FileUtils;
import com.hframework.common.util.StringUtils;
import com.hframework.common.util.message.XmlUtils;
import com.hframework.web.config.bean.DataSet;
import com.hframework.web.config.bean.dataset.Entity;
import com.hframework.web.config.bean.dataset.EnumClass;
import com.hframework.web.config.bean.dataset.Fields;
import com.hframework.web.config.bean.dataset.Rel;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.util.*;

/**
 * Created by zhangquanhong on 2016/6/13.
 */
public class DataSetLoaderListener  implements ServletContextListener {


    private static String DATA_SET_ROOT_PATH = null;

    private static final String TITLE = "Title";
    private static final String VIEW_OBJECT = "ViewObject";
    private static final String PO_PATH = "PoPath";
    private static final String COLUMNS_LIST = "columnsList";
    private static final String PRIMARY_KEY = "PrimaryKey";
    private static final String FIELDS_LIST = "fieldsList";
    private static final String REL_ENTITYS = "CoreSetTables";
    private static final String FIELDS = "fields";

    private Map<Long, HfpmProgram> hfpmProgramMap = null;
    private Map<Long, HfpmModule> hfpmModuleMap = null;

    private Map<Long, List<HfmdEntityAttr>> hfmdEntityAttrMap = null;

    private Map<Long, HfmdEntity> hfmdEntityIdEntityMap = null;

    private Map<Long, HfmdEntityAttr> hfmdEntityAttrIdEntityAttrMap = null;

    private Map<Long, HfpmFieldShowType> hfpmFieldShowTypeMap = null;

    private Map<Long, HfmdEnumClass> hfmdEnumClassMap = null;
    private Map<Long, List<HfmdEnum>> hfmdEnumMap = null;

    public DataSetLoaderListener() {
    }

    public void contextInitialized(ServletContextEvent event) {
// 1、获取spring对应的service层bean对象
        WebApplicationContext webappcontext = WebApplicationContextUtils
                .getRequiredWebApplicationContext(event.getServletContext());
        DATA_SET_ROOT_PATH = event.getServletContext().getRealPath("\\");

        IHfpmProgramSV iHfpmProgramSV = (IHfpmProgramSV) webappcontext.getBean("iHfpmProgramSV");
        IHfpmModuleSV iHfpmModuleSV = (IHfpmModuleSV) webappcontext.getBean("iHfpmModuleSV");

        IHfmdEntitySV iHfmdEntitySV = (IHfmdEntitySV) webappcontext.getBean("iHfmdEntitySV");
        IHfmdEntityAttrSV iHfmdEntityAttrSV = (IHfmdEntityAttrSV) webappcontext.getBean("iHfmdEntityAttrSV");

        IHfpmDataSetSV iHfpmDataSetSV = (IHfpmDataSetSV) webappcontext.getBean("iHfpmDataSetSV");
        IHfpmDataFieldSV iHfpmDataFieldSV = (IHfpmDataFieldSV) webappcontext.getBean("iHfpmDataFieldSV");

        IHfpmFieldShowTypeSV iHfpmFieldShowTypeSV = (IHfpmFieldShowTypeSV) webappcontext.getBean("iHfpmFieldShowTypeSV");

        IHfmdEnumClassSV iHfmdEnumClassSV = (IHfmdEnumClassSV) webappcontext.getBean("iHfmdEnumClassSV");
        IHfmdEnumSV iHfmdEnumSV = (IHfmdEnumSV) webappcontext.getBean("iHfmdEnumSV");

        try {
            //获取项目信息
            List<HfpmProgram> hfpmProgramAll = iHfpmProgramSV.getHfpmProgramAll();
            hfpmProgramMap = getHfpmProgramMap(hfpmProgramAll);

            //获取项目模块信息
            List<HfpmModule> hfpmModuleAll = iHfpmModuleSV.getHfpmModuleAll();
            hfpmModuleMap = getHfpmModuleMap(hfpmModuleAll);

            //获取实体信息
            List<HfmdEntity> hfmdEntityAll = iHfmdEntitySV.getHfmdEntityAll();
            hfmdEntityIdEntityMap = getHfmdEntityIdEntityMap(hfmdEntityAll);

            //获取实体属性信息
            List<HfmdEntityAttr> hfmdEntityAttrAll = iHfmdEntityAttrSV.getHfmdEntityAttrAll();
            hfmdEntityAttrMap = getHfmdEntityAttrMap(hfmdEntityAttrAll);
            hfmdEntityAttrIdEntityAttrMap = getHfmdEntityAttrIdEntityAttrMap(hfmdEntityAttrAll);

            //获取数据集信息
            List<HfpmDataSet> hfpmDataSetAll = iHfpmDataSetSV.getHfpmDataSetAll();

            //获取数据字段信息<HfpmDataFieldId, List<HfpmDataField>>
            List<HfpmDataField> hfpmDataFieldAll = iHfpmDataFieldSV.getHfpmDataFieldAll();
            Map<Long, List<HfpmDataField>> hfpmDataFieldMap = getHfpmDataFieldMap(hfpmDataFieldAll);

            //获取展示方式信息
            List<HfpmFieldShowType> hfpmFieldShowTypeAll = iHfpmFieldShowTypeSV.getHfpmFieldShowTypeAll();
            hfpmFieldShowTypeMap = getHfpmFieldShowTypeMap(hfpmFieldShowTypeAll);

            //获取枚举类信息
            List<HfmdEnumClass> hfmdEnumClassAll = iHfmdEnumClassSV.getHfmdEnumClassAll();
            hfmdEnumClassMap = getHfmdEnumClassMap(hfmdEnumClassAll);

            //获取枚举值信息
            List<HfmdEnum> hfmdEnumAll = iHfmdEnumSV.getHfmdEnumAll();
            hfmdEnumMap = getHfmdEnumMap(hfmdEnumAll);

            loadEntity(hfmdEntityAll, hfmdEntityAttrMap);

            loadDataSet(hfpmDataSetAll, hfpmDataFieldMap);


            createDataSetDescriptorXml(hfpmDataSetAll, hfpmDataFieldMap);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createDataSetDescriptorXml(List<HfpmDataSet> hfpmDataSetAll, Map<Long, List<HfpmDataField>> hfpmDataFieldMap) throws IOException {
        if (hfpmDataSetAll != null) {
            for (HfpmDataSet hfpmDataSet : hfpmDataSetAll) {

                DataSet dataSet = new DataSet();
                dataSet.setModule("hframe");
                dataSet.setCode(hfpmDataSet.getHfpmDataSetCode());
                dataSet.setName(hfpmDataSet.getHfpmDataSetName());
                Fields fields = new Fields();
                ArrayList<com.hframework.web.config.bean.dataset.Field> fieldList
                        = new ArrayList<com.hframework.web.config.bean.dataset.Field>();
                fields.setFieldList(fieldList);
                dataSet.setFields(fields);
                List<HfpmDataField> hfpmDataFieldList = hfpmDataFieldMap.get(hfpmDataSet.getHfpmDataSetId());
                for (HfpmDataField hfpmDataField : hfpmDataFieldList) {
                    HfmdEntityAttr hfmdEntityAttr = hfmdEntityAttrIdEntityAttrMap.get(hfpmDataField.getHfmdEntityAttrId());

                    com.hframework.web.config.bean.dataset.Field field = new com.hframework.web.config.bean.dataset.Field();
                    fieldList.add(field);
                    com.hframe.tag.bean.Column column = getColumnFromHfmdEntityAttr(hfmdEntityAttr, hfpmDataField.getHfpmFieldShowTypeId());
                    String editType = column.getShowType().getType();
                    field.setName(hfpmDataField.getHfpmDataFieldName());
                    field.setCode(hfpmDataField.getHfpmDataFieldCode());
                    field.setEditType(editType);
                    field.setCreateEditType("0".equals(String.valueOf(hfpmDataField.getFieldShowCode().charAt(0))) ? "hidden"
                            : "1".equals(String.valueOf(hfpmDataField.getFieldShowCode().charAt(0))) ? "text" : null);
                    field.setUpdateEditType("0".equals(String.valueOf(hfpmDataField.getFieldShowCode().charAt(1))) ? "hidden"
                            : "1".equals(String.valueOf(hfpmDataField.getFieldShowCode().charAt(1))) ? "text" : null);
//                    field.setAction();

                    Long hfmdEnumClassId = hfmdEntityAttr.getHfmdEnumClassId();
                    if(hfmdEnumClassId != null && hfmdEnumClassId > 0) {
                        HfmdEnumClass hfmdEnumClass = hfmdEnumClassMap.get(hfmdEnumClassId);
                        EnumClass enumClass = new EnumClass();
                        enumClass.setCode(hfmdEnumClass.getHfmdEnumClassCode());
                        field.setEnumClass(enumClass);

                    }
                    if(hfmdEntityAttr.getRelHfmdEntityAttrId() != null && hfmdEntityAttr.getRelHfmdEntityAttrId() > 0) {
                        HfmdEntityAttr relEntityAttr = hfmdEntityAttrIdEntityAttrMap.get(hfmdEntityAttr.getRelHfmdEntityAttrId());
                        Rel rel = new Rel();
                        Long hfpmModuleId = relEntityAttr.getHfpmModuleId();
                        HfpmModule hfpmModule = hfpmModuleMap.get(hfpmModuleId);
                        Long hfmdEntityId = relEntityAttr.getHfmdEntityId();
                        HfmdEntity hfmdEntity = hfmdEntityIdEntityMap.get(hfmdEntityId);
                        List<HfmdEntityAttr> hfmdEntityAttrs = hfmdEntityAttrMap.get(hfmdEntityId);
                        String name = null;
                        for (HfmdEntityAttr entityAttr : hfmdEntityAttrs) {
                            if(entityAttr.getHfmdEntityAttrCode().endsWith("ame")) {
                                name = entityAttr.getHfmdEntityAttrCode();
                                break;
                            }
                        }
//                        if(name == null) {
//                            name = relEntityAttr.getHfmdEntityAttrCode().substring(0,)
//                        }
                        rel.setEntityCode( hfmdEntity.getHfmdEntityCode() + "/" + relEntityAttr.getHfmdEntityAttrCode() + "/" + name);
                        field.setRel(rel);
                    }
                }

                List<Entity> entitys = new ArrayList<Entity>();
                dataSet.setEntityList(entitys);
                Set<HfmdEntity> entityList = getEntityFromDataFieldList(hfpmDataFieldList);
                for (HfmdEntity entity : entityList) {
                    Entity entity1 = new Entity();
                    entity1.setText(entity.getHfmdEntityCode());
                    entitys.add(entity1);
                }

                String dataSetXml = XmlUtils.writeValueAsString(dataSet);
//                dataSetXml = dataSetXml.replaceAll("/name", "//name");
                //获取属性文件路径
                String dataSetFilePath =  PropertyConfigurerUtils.getProperty(CreatorConst.PROJECT_BASE_FILE_PATH) +
                        "/hframe-webtemplate/src/main/resources/program/hframe/data/set/" + dataSet.getCode() + ".xml";
                System.out.println(dataSetFilePath);
                System.out.println(dataSetXml);
                FileUtils.writeFile(dataSetFilePath, dataSetXml);

//                //如果是树形数据集，生成一个默认的树形缓存对象
//                fieldList = getTreeFieldListFromDataFieldList(hfpmDataFieldList);
//                if(fieldList != null) {
//                    map = new HashMap();
//                    map.put(FIELDS_LIST, fieldList);
//                    map.put(TITLE, hfpmDataSet.getHfpmDataSetName());
//                    map.put(REL_ENTITYS, entityCodes);
////                SetCacheFactory.put(hfpmDataSet.getHfpmDataSetCode(), map, "table");
//                    CacheFactory.put(CacheKeyEnum.DS_TREE_CACHE.getName(),hfpmDataSet.getHfpmDataSetCode(),map);
//                }


//                map = getDataSetMap(hfpmDataSet,entityCodes,hfpmDataFieldList,0);
//                CacheFactory.put(CacheKeyEnum.DS_CREATE_CACHE.getName(),hfpmDataSet.getHfpmDataSetCode(),map);
//
//                map = getDataSetMap(hfpmDataSet,entityCodes,hfpmDataFieldList,1);
//                CacheFactory.put(CacheKeyEnum.DS_MODIFY_CACHE.name(),hfpmDataSet.getHfpmDataSetCode(),map);
//
//                map = getDataSetMap(hfpmDataSet,entityCodes,hfpmDataFieldList,2);
//                CacheFactory.put(CacheKeyEnum.DS_SHOW_CACHE.name(),hfpmDataSet.getHfpmDataSetCode(),map);




            }
        }


    }

    private Map<Long, HfmdEntityAttr> getHfmdEntityAttrIdEntityAttrMap(List<HfmdEntityAttr> hfmdEntityAttrAll) {

        Map<Long, HfmdEntityAttr> map = new HashMap<Long, HfmdEntityAttr>();
        for (HfmdEntityAttr hfmdEntityAttr : hfmdEntityAttrAll) {
            map.put(hfmdEntityAttr.getHfmdEntityAttrId(), hfmdEntityAttr);
        }

        return map;
    }

    private Map<Long, HfmdEntity> getHfmdEntityIdEntityMap(List<HfmdEntity> hfmdEntityAll) {

        Map<Long, HfmdEntity> map = new HashMap<Long, HfmdEntity>();
        for (HfmdEntity hfmdEntity : hfmdEntityAll) {
            map.put(hfmdEntity.getHfmdEntityId(), hfmdEntity);
        }
        return map;
    }

    private void loadDataSet(List<HfpmDataSet> hfpmDataSetAll, Map<Long, List<HfpmDataField>> hfpmDataFieldMap) throws Exception {
        if (hfpmDataSetAll != null) {
            for (HfpmDataSet hfpmDataSet : hfpmDataSetAll) {

                //if ("fields".equals(coreSet.getCoreSetType()))
                List<HfpmDataField> hfpmDataFieldList = hfpmDataFieldMap.get(hfpmDataSet.getHfpmDataSetId());
                List<Field> fieldList = getFieldListFromDataFieldList(hfpmDataFieldList);
                Set<HfmdEntity> entityList = getEntityFromDataFieldList(hfpmDataFieldList);
                String entityCodes = "";
                for (HfmdEntity entity : entityList) {
                    if (!"".equals(entityCodes)) {
                        entityCodes += ",";
                    }
                    entityCodes += entity.getHfmdEntityCode();
                }
                Map map = new HashMap();
                map.put(FIELDS_LIST, fieldList);
                map.put(TITLE, hfpmDataSet.getHfpmDataSetName());
                map.put(REL_ENTITYS, entityCodes);
//                SetCacheFactory.put(hfpmDataSet.getHfpmDataSetCode(), map, FIELDS);
                CacheFactory.put(CacheKeyEnum.DS_LIST_CACHE.name(),hfpmDataSet.getHfpmDataSetCode(),map);


                //如果是树形数据集，生成一个默认的树形缓存对象
                fieldList = getTreeFieldListFromDataFieldList(hfpmDataFieldList);
                if(fieldList != null) {
                    map = new HashMap();
                    map.put(FIELDS_LIST, fieldList);
                    map.put(TITLE, hfpmDataSet.getHfpmDataSetName());
                    map.put(REL_ENTITYS, entityCodes);
//                SetCacheFactory.put(hfpmDataSet.getHfpmDataSetCode(), map, "table");
                    CacheFactory.put(CacheKeyEnum.DS_TREE_CACHE.getName(),hfpmDataSet.getHfpmDataSetCode(),map);
                }



//				if ("columns".equals(coreSet.getCoreSetType()))

                map = getDataSetMap(hfpmDataSet,entityCodes,hfpmDataFieldList,0);
//                SetCacheFactory.put(hfpmDataSet.getHfpmDataSetCode(), map, "table");
                CacheFactory.put(CacheKeyEnum.DS_CREATE_CACHE.getName(),hfpmDataSet.getHfpmDataSetCode(),map);

                map = getDataSetMap(hfpmDataSet,entityCodes,hfpmDataFieldList,1);
                CacheFactory.put(CacheKeyEnum.DS_MODIFY_CACHE.name(),hfpmDataSet.getHfpmDataSetCode(),map);

                map = getDataSetMap(hfpmDataSet,entityCodes,hfpmDataFieldList,2);
                CacheFactory.put(CacheKeyEnum.DS_SHOW_CACHE.name(),hfpmDataSet.getHfpmDataSetCode(),map);
//			if ("element".equals(coreSet.getCoreSetType())) {
//
//			// 4、根据set对象找出对应的enum_group 对象，并精确到具体的某一个enum，或则sql
//
//			if(null == coreSet.getCoreSetQuote() || "".equals(coreSet.getCoreSetQuote())){
//				continue;
//			}
//
//			CoreEnumGroup coreEnumGroup=coreEnumGroupServ.getCoreEnumGroupByCoreEnumGroupId(coreSet.getCoreSetQuote(), 100);
//
//			List optionList=new ArrayList();
//			List<CoreEnum> coreEnumList=coreEnumGroup.getCoreEnumList();
//			for (CoreEnum coreEnum : coreEnumList) {
//				optionList.add(new Option(coreEnum.getCoreEnumValue(),coreEnum.getCoreEnumDisplayValue()));
//			}
//
//			Map map = new HashMap();
//			map.put("optionList", optionList);
//			map.put("Title", coreEnumGroup.getCoreEnumGroupName());
//			map.put("CoreEnumDyn", coreEnumGroup.getCoreEnumDyn());
//			map.put("ViewObject",CreatorUtil.getJavaVarName(coreSet.getCoreSetName()));
//
//			SetCacheFactory.put(coreSet.getCoreSetName(), map,
//					"element");
                Collection<String> all = CacheFactory.getAll(CacheKeyEnum.DS_MODIFY_CACHE.name());
                System.out.println(1);

            }
        }


    }

    private Map getDataSetMap(HfpmDataSet hfpmDataSet, String entityCodes, List<HfpmDataField> hfpmDataFieldList, int showTypeCodeIndex) throws Exception {
        Map map = new HashMap();
        com.hframework.beans.class0.Class defPoClass = CreatorUtil.getDefPoClass("",
                hfpmProgramMap.get(hfpmDataSet.getHfpmProgramId()).getHfpmProgramCode(),
                hfpmProgramMap.get(hfpmDataSet.getHfpmProgramId()).getHfpmProgramCode(),//TODO
                hfpmDataSet.getHfpmDataSetCode());

        map.put(TITLE, hfpmDataSet.getHfpmDataSetName());
        map.put(VIEW_OBJECT, ClassDeclaredUtils.getJavaVarName(hfpmDataSet.getHfpmDataSetCode()));
        map.put(PO_PATH, defPoClass.getClassPath());

        List<com.hframe.tag.bean.Column> columnList = getColumnListFromHfpmDataFieldList(hfpmDataFieldList,showTypeCodeIndex);
        map.put(COLUMNS_LIST, columnList);
        map.put(REL_ENTITYS, entityCodes);
        return map;
    }

    private List<com.hframe.tag.bean.Column> getColumnListFromHfpmDataFieldList(List<HfpmDataField> hfpmDataFieldList, int showTypeCodeIndex) {

        List<com.hframe.tag.bean.Column> columnList = new ArrayList<com.hframe.tag.bean.Column>();

        if (hfpmDataFieldList != null) {
            for (HfpmDataField hfpmDataField : hfpmDataFieldList) {
                HfmdEntityAttr hfmdEntityAttr = hfmdEntityAttrIdEntityAttrMap.get(hfpmDataField.getHfmdEntityAttrId());
                if(!"0".equals(String.valueOf(hfpmDataField.getFieldShowCode().charAt(showTypeCodeIndex)))) {
                    com.hframe.tag.bean.Column column = getColumnFromHfmdEntityAttr(hfmdEntityAttr, hfpmDataField.getHfpmFieldShowTypeId());
                    if (StringUtils.isNotBlank(hfpmDataField.getHfpmDataFieldName())) {
                        column.setDisplayName(hfpmDataField.getHfpmDataFieldName());
                    }
                    columnList.add(column);
                }
            }
        }

        return columnList;
    }

    private Set<HfmdEntity> getEntityFromDataFieldList(List<HfpmDataField> hfpmDataFieldList) {
        Set<HfmdEntity> set = new HashSet<HfmdEntity>();
        if (hfpmDataFieldList != null) {
            for (HfpmDataField hfpmDataField : hfpmDataFieldList) {
                HfmdEntity entity = hfmdEntityIdEntityMap.get(hfpmDataField.getHfmdEntityId());
                set.add(entity);
            }
        }
        return set;
    }

    private List<Field> getFieldListFromDataFieldList(List<HfpmDataField> hfpmDataFieldList) {

        List<Field> fieldList = new ArrayList<Field>();

        if (hfpmDataFieldList != null) {

            for (HfpmDataField hfpmDataField : hfpmDataFieldList) {
                HfmdEntityAttr hfmdEntityAttr = hfmdEntityAttrIdEntityAttrMap.get(hfpmDataField.getHfmdEntityAttrId());
                if(hfmdEntityAttr.getIspk() == 1) {
                    fieldList.add(new Field(hfmdEntityAttr.getHfmdEntityAttrId()+"111",
                            "全选${sys:allSelect}",
                            "checkbox","",
//                            "${column:id}"
                            "${column:" + hfmdEntityAttr.getHfmdEntityAttrCode() + "}"
                    ));
                }
            }
            for (HfpmDataField hfpmDataField : hfpmDataFieldList) {
                //TODO
                HfpmFieldShowType showType = hfpmFieldShowTypeMap.get(Long.valueOf(hfpmDataField.getHfpmFieldShowTypeId()));
                Long hfmdEntityAttrId = hfpmDataField.getHfmdEntityAttrId();
                if(!"0".equals(hfpmDataField.getFieldShowCode().charAt(2))) {
                    fieldList.add(new Field(
                            String.valueOf(hfpmDataField.getHfpmDataFieldId()),
                            hfpmDataField.getHfpmDataFieldName(),
                            "text",
                            "${column:" + hfmdEntityAttrIdEntityAttrMap.get(hfmdEntityAttrId).getHfmdEntityAttrCode() + "}", ""));
                }
            }
        }
        return fieldList;
    }

    private List<Field> getTreeFieldListFromDataFieldList(List<HfpmDataField> hfpmDataFieldList) {

        List<Field> fieldList = new ArrayList<Field>();

        if (hfpmDataFieldList != null) {

            HfmdEntityAttr pkEntityAttr = null;
            for (HfpmDataField hfpmDataField : hfpmDataFieldList) {
                HfmdEntityAttr hfmdEntityAttr = hfmdEntityAttrIdEntityAttrMap.get(hfpmDataField.getHfmdEntityAttrId());
                if(hfmdEntityAttr.getIspk() == 1) {
                    pkEntityAttr = hfmdEntityAttr;
                }
            }

            if(pkEntityAttr == null) {
                return null;
            }


            for (HfpmDataField hfpmDataField : hfpmDataFieldList) {
                HfmdEntityAttr hfmdEntityAttr = hfmdEntityAttrIdEntityAttrMap.get(hfpmDataField.getHfmdEntityAttrId());

                //判断是否为自循环
                if(hfmdEntityAttr.getRelHfmdEntityAttrId() != null && hfmdEntityAttr.getRelHfmdEntityAttrId() > 0L
                        && pkEntityAttr.getHfmdEntityAttrId().equals(hfmdEntityAttr.getRelHfmdEntityAttrId())) {
                    fieldList.add(new Field(hfmdEntityAttr.getHfmdEntityAttrId()+"",
                            "",
                            "virtual_pid","",
                            hfmdEntityAttr.getHfmdEntityAttrCode()
//                            "${column:" + hfmdEntityAttr.getHfmdEntityAttrCode() + "}"
                    ));
                    fieldList.add(new Field(pkEntityAttr.getHfmdEntityAttrId()+"",
                            "",
                            "virtual_id","",
                            pkEntityAttr.getHfmdEntityAttrCode()
//                            "${column:" + pkEntityAttr.getHfmdEntityAttrCode() + "}"
                    ));
                }
            }

            if(fieldList.size() == 0) {
                return null;
            }

            for (HfpmDataField hfpmDataField : hfpmDataFieldList) {
                Long hfmdEntityAttrId = hfpmDataField.getHfmdEntityAttrId();
                if(hfmdEntityAttrIdEntityAttrMap.get(hfmdEntityAttrId).getHfmdEntityAttrCode().toLowerCase().contains("name")) {
                    fieldList.add(new Field(
                            String.valueOf(hfpmDataField.getHfpmDataFieldId()),
                            hfpmDataField.getHfpmDataFieldName(),
                            "href",
                            "${column:" + hfmdEntityAttrIdEntityAttrMap.get(hfmdEntityAttrId).getHfmdEntityAttrCode() + "}", ""));
                }
            }
        }
        return fieldList;
    }

    private void loadEntity(List<HfmdEntity> hfmdEntityAll, Map<Long, List<HfmdEntityAttr>> hfmdEntityAttrMap) throws Exception {
        if (hfmdEntityAll != null) {
            for (HfmdEntity hfmdEntity : hfmdEntityAll) {
                Class defPoClass = CreatorUtil.getDefPoClass("",
                        hfpmProgramMap.get(hfmdEntity.getHfpmProgramId()).getHfpmProgramCode(),
                        hfpmModuleMap.get(hfmdEntity.getHfpmModuleId()).getHfpmModuleCode(),
                        hfmdEntity.getHfmdEntityCode());
                Map<String,Object> map = new HashMap();
                map.put(TITLE, hfmdEntity.getHfmdEntityName());
                map.put(VIEW_OBJECT, ClassDeclaredUtils.getJavaVarName(hfmdEntity.getHfmdEntityCode()));
                map.put(PO_PATH, defPoClass.getClassPath());

                List<HfmdEntityAttr> hfmdEntityAttrs = hfmdEntityAttrMap.get(hfmdEntity.getHfmdEntityId());
                List<com.hframe.tag.bean.Column> columnList = getColumnListFromHfmdEntityAttrs(hfmdEntityAttrs);
                map.put(COLUMNS_LIST, columnList);

                if (hfmdEntityAttrs != null) {
                    for (HfmdEntityAttr hfmdEntityAttr : hfmdEntityAttrs) {
                        if (hfmdEntityAttr.getIspk() == 1) {
                            map.put(PRIMARY_KEY, hfmdEntityAttr.getHfmdEntityAttrCode());
                        }
                    }
                }
//                SetCacheFactory.put(hfmdEntity.getHfmdEntityCode(), map, "table");
                CacheFactory.put(CacheKeyEnum.ENTITY_CACHE.name(),hfmdEntity.getHfmdEntityCode(),map);
            }
        }
    }

    private Map<Long, List<HfpmDataField>> getHfpmDataFieldMap(List<HfpmDataField> hfpmDataFieldAll) {

        Map<Long, List<HfpmDataField>> map = new HashMap<Long, List<HfpmDataField>>();
        for (HfpmDataField hfpmDataField : hfpmDataFieldAll) {
            if (!map.containsKey(hfpmDataField.getHfpmDataSetId())) {
                map.put(hfpmDataField.getHfpmDataSetId(), new ArrayList<HfpmDataField>());
            }
            List<HfpmDataField> hfpmDataFieldList = map.get(hfpmDataField.getHfpmDataSetId());

//            Collections.sort(hfpmDataFieldList, new Comparator<HfpmDataField>() {
//                public int compare(HfpmDataField o1, HfpmDataField o2) {
//                    return (o1.getPri().compareTo(o2.getPri()) < 0) ? -1 : 1;
//                }
//            });
            int index = -1;
            for (int i = 0; i < hfpmDataFieldList.size(); i++) {
                if(hfpmDataFieldList.get(i).getPri().compareTo(hfpmDataField.getPri()) > 0) {
                    index = i;
                }
            }
            if(index != -1) {
                hfpmDataFieldList.add(index,hfpmDataField);
            }else {
                hfpmDataFieldList.add(hfpmDataField);
            }

        }

        for (List<HfpmDataField> hfpmDataFields : map.values()) {
            Collections.sort(hfpmDataFields, new Comparator<HfpmDataField>() {
                public int compare(HfpmDataField o1, HfpmDataField o2) {
                    return (o1.getPri().compareTo(o2.getPri()) < 0) ? -1 : 1;
                }
            });
        }

        return map;
    }

    private Map<Long, List<HfmdEnum>> getHfmdEnumMap(List<HfmdEnum> hfmdEnumAll) {

        Map<Long, List<HfmdEnum>> map = new HashMap<Long, List<HfmdEnum>>();
        for (HfmdEnum hfmdEnum : hfmdEnumAll) {
            if (!map.containsKey(hfmdEnum.getHfmdEnumClassId())) {
                map.put(Long.valueOf(hfmdEnum.getHfmdEnumClassId()), new ArrayList<HfmdEnum>());
            }

            map.get(Long.valueOf(hfmdEnum.getHfmdEnumClassId())).add(hfmdEnum);
        }

        return map;
    }

    private Map<Long, HfmdEnumClass> getHfmdEnumClassMap(List<HfmdEnumClass> hfmdEnumClassAll) {
        Map<Long, HfmdEnumClass> map = new HashMap<Long, HfmdEnumClass>();
        for (HfmdEnumClass hfmdEnumClass : hfmdEnumClassAll) {
            map.put(hfmdEnumClass.getHfmdEnumClassId(), hfmdEnumClass);
        }

        return map;
    }

    private Map<Long, HfpmFieldShowType> getHfpmFieldShowTypeMap(List<HfpmFieldShowType> hfpmFieldShowTypeAll) {
        Map<Long, HfpmFieldShowType> map = new HashMap<Long, HfpmFieldShowType>();
        for (HfpmFieldShowType showType : hfpmFieldShowTypeAll) {
            map.put(showType.getHfpmFieldShowTypeId(), showType);
        }
        return map;
    }

    private List<com.hframe.tag.bean.Column> getColumnListFromHfmdEntityAttrs(List<HfmdEntityAttr> hfmdEntityAttrs) {

        List<com.hframe.tag.bean.Column> columnList = new ArrayList<com.hframe.tag.bean.Column>();

        for (HfmdEntityAttr hfmdEntityAttr : hfmdEntityAttrs) {
            columnList.add(getColumnFromHfmdEntityAttr(hfmdEntityAttr, null));
        }

        return null;
    }

    private com.hframe.tag.bean.Column getColumnFromHfmdEntityAttr(HfmdEntityAttr hfmdEntityAttr, String showTypeIds) {
        com.hframe.tag.bean.Column column = new com.hframe.tag.bean.Column();

        column.setId(String.valueOf(hfmdEntityAttr.getHfmdEntityAttrId()));
        column.setName(hfmdEntityAttr.getHfmdEntityAttrCode());
        column.setJavaVarName(CreatorUtil.getJavaVarName(hfmdEntityAttr.getHfmdEntityAttrCode()));
        column.setDisplayName(hfmdEntityAttr.getHfmdEntityAttrName());
        column.setNullable(hfmdEntityAttr.getNullable());
        ///column.setDefaultValue(coreColumn.get)  //TODO

        //设置showType----------------------------一个column可以配置多个showType,分别用“，”分割
        if (StringUtils.isBlank(showTypeIds)) {
            if (hfmdEntityAttr.getIsBusiAttr() == 0) {
                column.setShowType(new ShowType("hidden"));
            } else {
                column.setShowType(new ShowType("input"));
            }
            return column;
        }

        String[] showTypeIdArray = showTypeIds.split(",");

        List<ShowType> showTypeList = getShowTypesByFieldShowTypeIds(showTypeIdArray);
        Long hfmdEnumClassId = hfmdEntityAttr.getHfmdEnumClassId();
        //设置showType中enum枚举值-------------------一个showType可以有optionList也可以没有optionList
        fillShowTypeList(showTypeList, hfmdEnumClassId);
        if (showTypeList != null) {
            if (showTypeList.size() == 1) {
                if (hfmdEntityAttr.getIsBusiAttr() == 0) {
                    column.setShowType(new ShowType("hidden"));
                } else {
                    column.setShowType(showTypeList.get(0));
                }
            } else {
                column.setShowTypes(showTypeList);
            }
        }
        return column;
    }

    private void fillShowTypeList(List<ShowType> showTypeList, Long hfmdEnumClassId) {
        if (hfmdEnumClassId != null && hfmdEnumClassId > 0) {
            List<HfmdEnum> hfmdEnumList = hfmdEnumMap.get(hfmdEnumClassId);
            if (hfmdEnumList != null) {
                List<Option> optionList = new ArrayList<Option>();
                for (HfmdEnum hfmdEnum : hfmdEnumList) {
                    optionList.add(new Option(hfmdEnum.getHfmdEnumValue(), hfmdEnum.getHfmdEnumText()));
                }
                if (showTypeList != null && showTypeList.size() > 0) {
                    showTypeList.get(0).setOptionList(optionList);
                }
                //TODO
//				showType.setCoreEnumDyn(getCoreEnumDynFromCoreShowType(coreShowType));
            }
        }
    }

    private List<ShowType> getShowTypesByFieldShowTypeIds(String[] showTypeIdArray) {

        List<ShowType> showTypeList = new ArrayList<ShowType>();

        for (String showTypeId : showTypeIdArray) {
            HfpmFieldShowType hfpmFieldShowType = hfpmFieldShowTypeMap.get(Long.valueOf(showTypeId));

            ShowType showType = new ShowType(hfpmFieldShowType.getHfpmFieldShowTypeCode());
            showType.setAfterStr(hfpmFieldShowType.getAfterStr());
            showType.setColSpan(hfpmFieldShowType.getColSpan()==null?1:hfpmFieldShowType.getColSpan());
            showType.setId(hfpmFieldShowType.getHfpmFieldShowTypeId().intValue());
            showType.setPreStr(hfpmFieldShowType.getPreStr());


//			showType.setElementId(coreShowType.getCoreElementId());//为tipinput,openwin使用 //TODO

            if (hfpmFieldShowType.getWidth() != null) {
                showType.setWidth(hfpmFieldShowType.getWidth());
            }
            if (hfpmFieldShowType.getHeight() != null) {
                showType.setHeight(hfpmFieldShowType.getHeight());
            }
//TODO
//			if(coreShowType.getCoreShowTypeAttr()!=null){
//				CoreShowTypeAttr coreShowTypeAttr = coreShowType.getCoreShowTypeAttr();
//				ShowTypeAttr showTypeAttr=new ShowTypeAttr();
//				showTypeAttr.setCondition(coreShowTypeAttr.getCondition());
//				showTypeAttr.setTitle(coreShowTypeAttr.getTitle());
//				showTypeAttr.setType(coreShowTypeAttr.getType());
//				showTypeAttr.setView(coreShowTypeAttr.getView());
//				showTypeAttr.setSrc(coreShowTypeAttr.getSrc()==null||"".equals(coreShowTypeAttr.getSrc())?"myDialog.jsp":coreShowTypeAttr.getSrc());
//				showType.setShowTypeAttr(showTypeAttr);
//			}

            showTypeList.add(showType);
        }

        return showTypeList;
    }

    private Map<Long, HfpmModule> getHfpmModuleMap(List<HfpmModule> hfpmModuleAll) {
        Map<Long, HfpmModule> map = new HashMap<Long, HfpmModule>();
        if (hfpmModuleAll != null) {
            for (HfpmModule hfpmModule : hfpmModuleAll) {
                map.put(hfpmModule.getHfpmModuleId(), hfpmModule);
            }
        }
        return map;
    }

    private Map<Long, HfpmProgram> getHfpmProgramMap(List<HfpmProgram> hfpmProgramAll) {
        Map<Long, HfpmProgram> map = new HashMap<Long, HfpmProgram>();
        if (hfpmProgramAll != null) {
            for (HfpmProgram hfpmProgram : hfpmProgramAll) {
                map.put(hfpmProgram.getHfpmProgramId(), hfpmProgram);
            }
        }
        return map;
    }

    private Map<Long, List<HfmdEntityAttr>> getHfmdEntityAttrMap(List<HfmdEntityAttr> hfmdEntityAttrAll) {
        Map<Long, List<HfmdEntityAttr>> hfmdEntityAttrMap = new HashMap<Long, List<HfmdEntityAttr>>();
        if (hfmdEntityAttrAll != null) {
            for (HfmdEntityAttr hfmdEntityAttr : hfmdEntityAttrAll) {
                if (!hfmdEntityAttrMap.containsKey(hfmdEntityAttr.getHfmdEntityId())) {
                    hfmdEntityAttrMap.put(hfmdEntityAttr.getHfmdEntityId(), new ArrayList<HfmdEntityAttr>());
                }
                hfmdEntityAttrMap.get(hfmdEntityAttr.getHfmdEntityId()).add(hfmdEntityAttr);
            }
        }
        return hfmdEntityAttrMap;
    }

    public static class CreatorUtil {

        public static String getJavaClassName(String tableName) {
            String returnName = "";
            tableName = tableName.toLowerCase();
            String[] parts = tableName.split("[_]+");
            for (String part : parts) {
                if (!"".equals(part)) {
                    returnName += part.substring(0, 1).toUpperCase()
                            + part.substring(1);
                }
            }
            return returnName;
        }

        public static Class getDefPoExampleClass(String companyName,
                                                 String projectName,String moduleName, String tableName) throws Exception {
            if(StringUtils.isBlank(tableName)) {
                throw new Exception("表名称为不能为空！");
            }

            companyName = StringUtils.isBlank(companyName)?"":"."+(companyName);
            projectName = StringUtils.isBlank(projectName)?"":"."+(projectName);
            moduleName = StringUtils.isBlank(moduleName)?"":"."+(moduleName);


            Class class1 = new Class();
            class1.setSrcFilePath(CreatorUtil.getSrcFilePath(companyName, projectName));
            class1.setClassPackage(CreatorUtil.getPoClassPackage(
                    companyName, projectName, moduleName, tableName));
            class1.setClassName(CreatorUtil.getJavaClassName(tableName) + "_Example");
            return class1;
        }
        public static Class getDefServiceClass(String companyName,
                                               String projectName, String moduleName,String tableName) throws Exception {
            if(StringUtils.isBlank(tableName)) {
                throw new Exception("表名称为不能为空！");
            }

            companyName = StringUtils.isBlank(companyName)?"":"."+(companyName);
            projectName = StringUtils.isBlank(projectName)?"":"."+(projectName);
            moduleName = StringUtils.isBlank(moduleName)?"":"."+(moduleName);

            Class class1 = new Class();
            class1.setSrcFilePath(CreatorUtil.getSrcFilePath(companyName, projectName));
            class1.setClassPackage(CreatorUtil.getServiceClassPackage(
                    companyName, projectName, moduleName, tableName));
            class1.setClassName("I" + CreatorUtil.getJavaClassName(tableName) + "SV");
            return class1;
        }

        public static String getServiceClassPackage(String companyName,
                                                    String projectName,String moduleName, String tableName) throws Exception {

            return PropertyConfigurerUtils.getProperty(
                    "service_class_package",
                    companyName.toLowerCase(),
                    projectName.toLowerCase(),
                    moduleName.toLowerCase());
        }

        public static Class getDefServiceImplClass(String companyName,
                                                   String projectName, String moduleName,String tableName) throws Exception {
            if(StringUtils.isBlank(tableName)) {
                throw new Exception("表名称为不能为空！");
            }

            companyName = StringUtils.isBlank(companyName)?"":"."+(companyName);
            projectName = StringUtils.isBlank(projectName)?"":"."+(projectName);
            moduleName = StringUtils.isBlank(moduleName)?"":"."+(moduleName);

            Class class1 = new Class();
            class1.setSrcFilePath(CreatorUtil.getSrcFilePath(companyName, projectName));
            class1.setClassPackage(CreatorUtil.getServiceImplClassPackage(
                    companyName, projectName, moduleName, tableName));
            class1.setClassName(CreatorUtil.getJavaClassName(tableName) + "SVImpl");
            return class1;
        }
        public static Class getDefControllerClass(String companyName,
                                                  String projectName, String moduleName,String tableName) throws Exception {

            if(StringUtils.isBlank(tableName)) {
                throw new Exception("表名称为不能为空！");
            }

            companyName = StringUtils.isBlank(companyName)?"":"."+(companyName);
            projectName = StringUtils.isBlank(projectName)?"":"."+(projectName);
            moduleName = StringUtils.isBlank(moduleName)?"":"."+(moduleName);

            Class class1 = new Class();
            class1.setSrcFilePath(CreatorUtil.getSrcFilePath(companyName, projectName));
            class1.setClassPackage(CreatorUtil.getActionClassPackage(
                    companyName, projectName, moduleName, tableName));
            class1.setClassName(CreatorUtil.getJavaClassName(tableName) + "Controller");
            return class1;
        }

        public static String getActionClassPackage(String companyName,
                                                   String projectName,String moduleName,String tableName) throws Exception {

            return PropertyConfigurerUtils.getProperty(
                    "action_class_package",
                    companyName.toLowerCase(),
                    projectName.toLowerCase(),
                    moduleName.toLowerCase());
        }
        public static String getServiceImplClassPackage(String companyName,
                                                        String projectName,String moduleName, String tableName) throws Exception {

            return PropertyConfigurerUtils.getProperty(
                    "serviceimpl_class_package",
                    companyName.toLowerCase(),
                    projectName.toLowerCase(),
                    moduleName.toLowerCase());
        }

        public static String getJavaVarName(String tableName) {

            String returnName="";
            tableName=tableName.toLowerCase();
            String[] parts=tableName.split("[_]+");
            for (String part : parts) {
                if(!"".equals(part)){
                    returnName+=part.substring(0,1).toUpperCase()+part.substring(1);
                }
            }
            return returnName.substring(0,1).toLowerCase()+returnName.substring(1);
        }


        /**
         * @param companyName
         * @param projectName
         * @return 获取SQL文件在项目中存放的路径即名称
         * @throws Exception
         */
        public static String getSrcFilePath(String companyName,
                                            String projectName) throws Exception {

            if("".equals(companyName) || companyName == null){
                companyName="zqh";
            }


            if(StringUtils.isBlank(projectName)) {
                throw new Exception("项目名称为不能为空！");
            }

            return PropertyConfigurerUtils.getProperty(CreatorConst.PROJECT_SRC_FILE_PATH);
        }



        /**
         * @param companyName
         * @param projectName
         * @param tableName
         * @return 获取SQL文件在项目中存放的路径即名称
         * @throws Exception
         */
        public static String getPoClassPackage(String companyName,
                                               String projectName,String moduleName,String tableName) throws Exception {
            return PropertyConfigurerUtils.getProperty(
                    CreatorConst.PO_CLASS_PACKAGE,
                    companyName.toLowerCase(),
                    projectName.toLowerCase(),
                    moduleName.toLowerCase(),
                    getJavaClassName(tableName.toLowerCase()));
        }


        public static Class getDefPoClass(String companyName,
                                          String projectName, String moduleName,String tableName) throws Exception {
            if(StringUtils.isBlank(tableName)) {
                throw new Exception("表名称为不能为空！");
            }

            companyName = StringUtils.isBlank(companyName)?"":"."+(companyName);
            projectName = StringUtils.isBlank(projectName)?"":"."+(projectName);
            moduleName = StringUtils.isBlank(moduleName)?"":"."+(moduleName);


            Class class1 = new Class();
            class1.setSrcFilePath(CreatorUtil.getSrcFilePath(companyName, projectName));
            class1.setClassPackage(CreatorUtil.getPoClassPackage(
                    companyName, projectName, moduleName,tableName));
            class1.setClassName(CreatorUtil.getJavaClassName(tableName) + "");
            return class1;
        }
    }
    public static class CreatorConst {

        public static String PROJECT_SRC_FILE_PATH = "project_src_file_path";
        public static String PO_CLASS_PACKAGE = "po_class_package";
        public static String PROJECT_BASE_FILE_PATH = "project_base_file_path";

    }



    public void contextDestroyed(ServletContextEvent event) {
    }
}
