package com.hframework.web;

import com.hframework.common.util.ReflectUtils;
import com.hframework.web.bean.DataSetDescriptor;
import com.hframework.web.bean.WebContext;
import com.hframework.web.config.bean.dataset.Field;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangquanhong on 2016/6/23.
 */
public class ControllerHelper {

    public static <T> T[] reorderProperty(T[] objects) {
        if(objects != null && objects.length > 0) {
            Class<?> orderSequenceClass = BeanUtils.findPropertyType("pri", objects[0].getClass());
            if(orderSequenceClass == BigDecimal.class) {
                BigDecimal tmpDecimal = BigDecimal.ZERO;
                for (int i = 0; i < objects.length; i++) {
                    BigDecimal pri = (BigDecimal) ReflectUtils.getFieldValue(objects[i], "pri");
                    if (pri == null) {
                        ReflectUtils.setFieldValue(objects[i], "pri", tmpDecimal.add(BigDecimal.ONE));
                    } else if (i > 0) {
                        BigDecimal prevPri = (BigDecimal) ReflectUtils.getFieldValue(objects[i - 1], "pri");
                        if( pri.compareTo(prevPri) <= 0) {
                            ReflectUtils.setFieldValue(objects[i], "pri", tmpDecimal.add(BigDecimal.ONE));
                        }
                    }
                    tmpDecimal = (BigDecimal)ReflectUtils.getFieldValue(objects[i], "pri");
                }
            }
        }
        return null;
    }

    public static <T> void setDefaultValue(T object, String keyPropertyName) throws Exception {
        Object key = ReflectUtils.getFieldValue(object, keyPropertyName);
        if(key == null) {
            setDefaultValue(object,OperateType.CREATE);
            setRelationFieldValue(object, OperateType.CREATE);
        }else {
            setDefaultValue(object,OperateType.UPDATE);
            setRelationFieldValue(object, OperateType.UPDATE);
        }


    }

    public static <T> void setDefaultValue(T object, OperateType operateType) throws Exception {
        if(object == null) {
            return;
        }
        switch (operateType) {
            case CREATE:
                setDefaultValue(object, CustomProperty.createTime.name(), new Date());
                setDefaultValue(object, CustomProperty.opId.name(), 999L);
                setDefaultValue(object, CustomProperty.delFlag.name(), 0);
                break;
            default:
                setDefaultValue(object, CustomProperty.modifyTime.name(), new Date());
                setDefaultValue(object, CustomProperty.modifyOpId.name(), 999L);
                break;
        }
    }

    private static <T> void setRelationFieldValue(T object, OperateType operateType) throws Exception {
        DataSetDescriptor dataSet = WebContext.get().getDataSet(object.getClass());
        Map<String, String> relFieldKeyMap = dataSet.getRelFieldKeyMap();
        Map<String, DataSetDescriptor> relDataSetMap = dataSet.getRelDataSetMap();
        Map<String, Object> relFieldValueMap = new HashMap<String, Object>();
        //对页面及页面流中对象外键直接赋值
        for (String fieldCode : relFieldKeyMap.keySet()) {
            String propertyName = CreatorUtil.getJavaVarName(fieldCode);
            Object origFieldValue = ReflectUtils.getFieldValue(object, propertyName);
            if(origFieldValue != null && !"".equals(origFieldValue)) {
                continue;
            }

            String relFieldCode = relFieldKeyMap.get(fieldCode).substring(relFieldKeyMap.get(fieldCode).indexOf("/") + 1);
            DataSetDescriptor relDataSetDescriptor = relDataSetMap.get(relFieldKeyMap.get(fieldCode));

            com.hframework.beans.class0.Class relPoClass =
                    CreatorUtil.getDefPoClass("",
                            WebContext.get().getProgram().getCode(), "hframe", relDataSetDescriptor.getDataSet().getCode());
            Object relPo = WebContext.get(Class.forName(relPoClass.getClassPath()).getName());

            //从页面上下文中获取数据
            boolean fillResult = WebContext.fillProperty(
                    Class.forName(relPoClass.getClassPath()).getName(), object, propertyName, CreatorUtil.getJavaVarName(relFieldCode));
            if(relPo != null) {
                relFieldValueMap.putAll(relDataSetDescriptor.getRelFieldValueMap(relPo));;
            }
            if(!fillResult) {
                //从页面流上线文中获取数据
                WebContext.fillProperty(Map.class.getName(), object, propertyName, CreatorUtil.getJavaVarName(relFieldCode));
            }
        }
        //对页面及页面流中对象的对象进行赋值
        for (String fieldCode : relFieldKeyMap.keySet()) {
            String propertyName = CreatorUtil.getJavaVarName(fieldCode);
            Object origFieldValue = ReflectUtils.getFieldValue(object, propertyName);
            if(origFieldValue != null && !"".equals(origFieldValue)) {
                continue;
            }
            String key = relFieldKeyMap.get(fieldCode);
            if(relFieldValueMap.containsKey(key)) {
                ReflectUtils.setFieldValue(object,propertyName, relFieldValueMap.get(key));
            }
        }

        //TODO 对页面及页面流中对象的对象的对象(迭代）进行赋值
//        List<Field> fieldList = dataSet.getDataSet().getFields().getFieldList();
//        for (Field field : fieldList) {
//            if(field.getRel() != null && field.getRel().getEntityCode() != null) {
//                String fieldCode = CreatorUtil.getJavaVarName(field.getCode());
//                Object origFieldValue = ReflectUtils.getFieldValue(object, fieldCode);
//                if(origFieldValue != null && !"".equals(origFieldValue)) {
//                    continue;
//                }
//                String entityCode = field.getRel().getEntityCode();
//                String dataSetCode = entityCode.substring(0, entityCode.indexOf("/"));
//                String relFieldCode = entityCode.substring(entityCode.indexOf("/") + 1, entityCode.lastIndexOf("/"));
//                com.hframework.beans.class0.Class relPoClass =
//                        CreatorUtil.getDefPoClass("",
//                                WebContext.get().getProgram().getCode(), "hframe", dataSetCode);
//                //从页面上下文中获取数据
//                boolean fillResult = WebContext.fillProperty(
//                        Class.forName(relPoClass.getClassPath()).getName(), object, fieldCode, CreatorUtil.getJavaVarName(relFieldCode));
//                if(!fillResult) {
//                    //从页面流上线文中获取数据
//                    WebContext.fillProperty(Map.class.getName(), object, fieldCode, CreatorUtil.getJavaVarName(relFieldCode));
//                }
//
//                if(!fillResult) {
//
//                }
////                Object relPo = WebContext.get(Class.forName(relPoClass.getClassPath()).getName());
////                if(relPo != null) {
////                    String relFieldName = CreatorUtil.getJavaVarName(relFieldCode);
////                    Object fieldValue = ReflectUtils.getFieldValue(relPo, relFieldName);
////                    ReflectUtils.setFieldValue(object,fieldCode, fieldValue);
////                }
//
//            }
//        }
    }

    public static <T> void setDefaultValue(T[] objects, String keyPropertyName) throws Exception {
        if(objects == null) {
            return;
        }
        for (T object : objects) {
            setDefaultValue(object,keyPropertyName);
        }
    }

    public static <T> void setDefaultValue(T object,  String propertyName, Object propertyValue) {
        if(object != null) {
            Class<?> clazz = BeanUtils.findPropertyType(propertyName, object.getClass());
            if(clazz == Date.class) {
                ReflectUtils.setFieldValue(object,propertyName, propertyValue);
            }else {
                ReflectUtils.setFieldValue(object,propertyName, propertyValue);
            }
        }
    }

    public enum CustomProperty {
        createTime,modifyTime,opId,modifyOpId,delFlag
    }

    public enum OperateType{
        CREATE,UPDATE,DELETE
    }
}