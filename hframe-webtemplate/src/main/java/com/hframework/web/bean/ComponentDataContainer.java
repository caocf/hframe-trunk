package com.hframework.web.bean;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hframework.beans.controller.Pagination;
import com.hframework.common.util.*;
import com.hframework.web.config.bean.Component;
import com.hframework.web.config.bean.DataSet;
import com.hframework.web.config.bean.component.AppendElement;
import com.hframework.web.config.bean.component.Effect;
import com.hframework.web.config.bean.component.Element;
import com.hframework.web.config.bean.component.Event;
import com.hframework.web.config.bean.dataset.Field;
import com.hframework.web.config.bean.mapper.Mapping;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * User: zhangqh6
 * Date: 2016/5/26 23:40:40
 */
public class ComponentDataContainer {

    private Map<String ,JsonSegmentParser> elements = new HashMap<String, JsonSegmentParser>();

    //每一行开始(如：添加复选框)
    private List<EventElement> beforeOfRowList = new ArrayList<EventElement>();
    //每一行结尾(如：添加操作图标)
    private List<EventElement> endOfRowList = new ArrayList<EventElement>();
    //组件结尾(如：提交按钮)
    private List<EventElement> endOfCompList = new ArrayList<EventElement>();
    //每一行某一列（如：点击名称进行超链接）
    private List<EventElement> elementOfCompList = new ArrayList<EventElement>();
    //每一行某一列（如：点击名称进行超链接）
    private Map<String, EventElement> elementOfRowMap = new HashMap<String, EventElement>();

    private Map<String, JsonSegmentParser> runtimeDataMap = new HashMap<String, JsonSegmentParser>();
    private String json;
    private JSONObject jsonObject;

    public ComponentDataContainer(Component component, com.hframework.web.config.bean.pagetemplates.Element pageElementDesc) {
        if(component == null) {
            return;
        }
        for (com.hframework.web.config.bean.component.Element element : component.getElementList()) {
            if(EnumUtils.compare(ComponentElementType.characters, element.getType())) {
                elements.put(element.getId(), new CharacterJsonSegmentParser(element,component.getType()));
            }else if(EnumUtils.compare(ComponentElementType.enums, element.getType())) {
                elements.put(element.getId(), new EnumJsonSegmentParser(element, component.getType()));
            }else if(EnumUtils.compare(ComponentElementType.object, element.getType())) {
                elements.put(element.getId(), new ObjectJsonSegmentParser(element, component.getType()));
            }else if(EnumUtils.compare(ComponentElementType.objects, element.getType())) {
                elements.put(element.getId(), new ObjectsJsonSegmentParser(element,component.getType()));
            }else if(EnumUtils.compare(ComponentElementType.array, element.getType())) {
                elements.put(element.getId(), new ArrayJsonSegmentParser(element, component.getType()));
            }else if(EnumUtils.compare(ComponentElementType.array2, element.getType())) {
                elements.put(element.getId(), new Array2JsonSegmentParser(element,component.getType()));
            }
        }

        List<Event> allEvent = new ArrayList<Event>();
        allEvent.addAll(component.getBaseEvents().getEventList());
        allEvent.addAll(component.getEvents().getEventList());
        if(pageElementDesc.getEvents() != null) {
            allEvent.addAll(pageElementDesc.getEvents().getEventList());
        }
        for (Event event : allEvent) {
            if(EnumUtils.compare(ComponentEventAnchor.BOFR, event.getAttach().getAnchor())) {
                beforeOfRowList.add(new EventElement(event));
            }else if(EnumUtils.compare(ComponentEventAnchor.EOFR, event.getAttach().getAnchor())) {
                endOfRowList.add(new EventElement(event));
            }else if(EnumUtils.compare(ComponentEventAnchor.EOFC, event.getAttach().getAnchor())) {
                endOfCompList.add(new EventElement(event));
            }else {
                elementOfCompList.add(new EventElement(event));
            }

            AppendElement appendElement = event.getSource().getAppendElement();
            if(appendElement != null && appendElement.getType() !=null) {
                if(EnumUtils.compare(ComponentEventAnchor.BOFR, event.getSource().getScope())) {
                    beforeOfRowList.add(new EventElement(appendElement));
                }else if(EnumUtils.compare(ComponentEventAnchor.EOFR, event.getSource().getScope())) {
                    endOfRowList.add(new EventElement(appendElement));
                }else if(EnumUtils.compare(ComponentEventAnchor.EOFC, event.getSource().getScope())) {
                    endOfCompList.add(new EventElement(appendElement));
                }else {
                    elementOfCompList.add(new EventElement(appendElement));
                }
            }
        }
    }

    public void addMappingAndDataSetDescriptor(Mapping mapping, DataSetDescriptor dataSetDescriptor, boolean isBaseElement) {
        if(isBaseElement) {
            elements.get(mapping.getId()).setDataSetDescriptorAndMapping(dataSetDescriptor, mapping);
        }else {
            peddingEventElement(beforeOfRowList, mapping, dataSetDescriptor);
            peddingEventElement(endOfRowList, mapping, dataSetDescriptor);
            peddingEventElement(endOfCompList, mapping, dataSetDescriptor);
            peddingEventElement(elementOfCompList, mapping, dataSetDescriptor);
            for (EventElement eventElement : elementOfCompList) {
                elementOfRowMap.put(eventElement.getAnchorName(),eventElement);
            }
        }
    }

    private void peddingEventElement(List<EventElement> eventElementList, Mapping mapping, DataSetDescriptor dataSetDescriptor) {

        String value = mapping.getValue();
        List<String> varList = RegexUtils.findVarList(value);
        for (String var : varList) {
            DataSet dataSet = dataSetDescriptor.getDataSet();
            if("id".equals(var)) {
                value = ResourceWrapper.JavaUtil.getJavaVarName(value.replace("${" + var + "}",dataSet.getCode() + "_" + var));
            }else if("name".equals(var)) {
                value = ResourceWrapper.JavaUtil.getJavaVarName(value.replace("${" + var + "}", dataSet.getCode() + "_" + var));
            }else if(var != null && var.endsWith("ByAjax")) {//"createByAjax".equals(var) || "updateByAjax".equals(var)|| "deleteByAjax".equals(var)
                value = value.replace("${" + var + "}", /*ResourceWrapper.JavaUtil.getJavaVarName(dataSet.getModule())
                        + "/" + */ResourceWrapper.JavaUtil.getJavaVarName(dataSet.getCode()) + "/" + var);
            }else {//create, edit , detail, batchDelete
                value = value.replace("${" + var + "}", dataSet.getCode() + "_" + var);
            }


        }


        for (EventElement eventElement : eventElementList) {
            eventElement.setParams(eventElement.getParams() == null ? null :
                    eventElement.getParams().replace("${" + mapping.getId() + "}", value + "={" + value + "}"));
            eventElement.setAction(eventElement.getAction() == null ? null :
                    eventElement.getAction().replace("${" + mapping.getId() + "}", value));
            eventElement.setAnchorName(eventElement.getAnchorName() == null ? null :
                    eventElement.getAnchorName().replace("${" + mapping.getId() + "}", value));
            eventElement.setComponent(eventElement.getComponent() == null ? null :
                    eventElement.getComponent().replace("${" + mapping.getId() + "}", value));
        }
    }

    public ComponentDataContainer getDataInstance(Object data) {
        if (data instanceof List) {//暂无该情况
            List list = (List) data;
        } else if (data instanceof Map) {//list方法返回
            Map<String, Object> map = (Map) data;
            map.put("pager", getPagers((Pagination) map.get("pagination")));


            for (String key : map.keySet()) {
                if ("list".equals(key)) {
                    JsonSegmentParser jsonSegmentParser = runtimeDataMap.get("${data}");
                    if (jsonSegmentParser instanceof Array2JsonSegmentParser) {
                        Array2JsonSegmentParser segmentParser = (Array2JsonSegmentParser) jsonSegmentParser;
                        JSONArray columns = (JSONArray) this.elements.get("columns").getJsonObject();
                        segmentParser.setData(map.get("list"),columns);
                    }
                } else if ("pager".equals(key)) {
                    JsonSegmentParser jsonSegmentParser = runtimeDataMap.get("${pager}");
                    if (jsonSegmentParser instanceof Array2JsonSegmentParser) {
                        Array2JsonSegmentParser segmentParser = (Array2JsonSegmentParser) jsonSegmentParser;
                        segmentParser.setData(map.get("pager"), null);
                    }
                }
            }
        } else {//详情返回
            JsonSegmentParser jsonSegmentParser = runtimeDataMap.get("${data}");
            if (jsonSegmentParser instanceof ObjectJsonSegmentParser) {
                ObjectJsonSegmentParser segmentParser = (ObjectJsonSegmentParser) jsonSegmentParser;
                JSONArray columns = (JSONArray) this.elements.get("columns").getJsonObject();
                segmentParser.setData(data,columns);
            }
        }
        return this;
    }

    private List<Pager> getPagers(Pagination pagination) {

        int pageNo = pagination.getPageNo();
        int totalPage = pagination.getTotalPage();

        List<Pager> list = new ArrayList<Pager>();

        int pageNoStart = pageNo > 2 ? pageNo-2 : 1;

        int pageNoEnd = (pageNoStart + 4) > totalPage ? totalPage : (pageNoStart + 4);

        list.add(new Pager("上一页",pageNoStart,pageNoStart == pageNo ? "disabled" : null ,pageNoStart == pageNo ? "active" : null));
        for(int i = pageNoStart; i <= pageNoEnd; i++) {
            list.add(new Pager(i + "",i,i == pageNo ? "disabled" : null ,i == pageNo ? "active" : null));
        }
        list.add(new Pager("下一页",pageNoEnd,pageNoEnd == pageNo ? "disabled" : null ,pageNoEnd == pageNo ? "active" : null));

        return list;
    }

    public JSONObject getJson() {
        jsonObject = new JSONObject();
        for (String key : elements.keySet()) {
            jsonObject.put(key, elements.get(key).getJsonObject());
        }

        jsonObject.put("BOFR",beforeOfRowList.size() == 0 ? null : beforeOfRowList );
        jsonObject.put("EOFR",endOfRowList.size() == 0 ? null : endOfRowList );
        jsonObject.put("EOF",endOfCompList.size() == 0 ? null : endOfCompList );
        jsonObject.put("ELE",elementOfRowMap.size() == 0 ? null : elementOfRowMap );
        return jsonObject;
    }

    private List getEventJsonString(List<EventElement> eventElements) {
        List tempList = new ArrayList();
        for (EventElement eventElement : eventElements) {
            tempList.add(eventElement.getAction());
        }
        return tempList;
    }

    public class Pager{
        private String text;
        private int value;
        private String disabled;
        private String active;

        public Pager(String text, int value, String disabled, String active) {
            this.text = text;
            this.value = value;
            this.disabled = disabled;
            this.active = active;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getDisabled() {
            return disabled;
        }

        public void setDisabled(String disabled) {
            this.disabled = disabled;
        }

        public String getActive() {
            return active;
        }

        public void setActive(String active) {
            this.active = active;
        }
    }

    public interface JsonSegmentParser {

        public  boolean ok();

        public  String toJson();

        public Object getJsonObject();

        public String getId();

        public void setDataSetDescriptorAndMapping(DataSetDescriptor dataSetDescriptor, Mapping mapping);
    }

    public abstract class AbstractJsonSegmentParser{
        private String id;

        protected String componentType;
        protected Element element;

        protected Mapping mapping;

        protected DataSetDescriptor dataSetDescriptor;

        public AbstractJsonSegmentParser(Element element, String type){
            this.element =element;
            this.componentType = type;
        }

        public abstract boolean ok();

        public abstract String toJson();

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Mapping getMapping() {
            return mapping;
        }

        public void setMapping(Mapping mapping) {
            this.mapping = mapping;
        }

        public DataSetDescriptor getDataSetDescriptor() {
            return dataSetDescriptor;
        }

        public void setDataSetDescriptorAndMapping(DataSetDescriptor dataSetDescriptor, Mapping mapping){
            setDataSetDescriptor(dataSetDescriptor);
            setMapping(mapping);
            afterSetDataSetDescriptorAndMapping();

        }

        public abstract void afterSetDataSetDescriptorAndMapping();

        public void setDataSetDescriptor(DataSetDescriptor dataSetDescriptor) {
            this.dataSetDescriptor = dataSetDescriptor;
        }


    }

    public class Array2JsonSegmentParser extends AbstractJsonSegmentParser implements JsonSegmentParser{

        private String express;

        private Map<String, String> initMap = new HashMap<String, String>();

        private List<String[]> values;
        private Object data;

        public Array2JsonSegmentParser(Element element, String type) {
            super(element, type);
        }

        @Override
        public boolean ok() {
            return values != null;
        }

        @Override
        public String toJson() {
            return null;
        }

        public JSON getJsonObject() {
            JSONArray array = new JSONArray();
            for (String[] value : values) {
                array.add(JSONArray.toJSON(value));
            }
            return array;
        }

        @Override
        public void afterSetDataSetDescriptorAndMapping() {
            List<Mapping> mappingList = mapping.getMappingList();
            for (Mapping mapping1 : mappingList) {
                initMap.put(mapping1.getId(), mapping1.getValue());
            }
            express = mapping.getValue();
            runtimeDataMap.put(express,this);
        }

        public void setData(Object data, JSONArray columns) {
            this.data = data;
            if (data instanceof List) {
                values = new ArrayList<String[]>();
                List list = (List) data;
                for (Object object : list) {
                    List<String> value= new ArrayList<String>();
                    if(initMap.size() != 0) {
                        for (String string : initMap.values()) {
                            String propertyName = string.substring(2,string.length()-1);
                            try {
                                value.add(org.apache.commons.beanutils.BeanUtils.getProperty(object,propertyName));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }else {
                        for (Object column : columns) {
                            String propertyName = ResourceWrapper.JavaUtil.getJavaVarName(((JSONObject) column).getString("code"));
                            try {
                                Class<?> type = BeanUtils.findPropertyType(propertyName, object.getClass());
                                if(type == Date.class) {
                                    Date date = (Date)ReflectUtils.getFieldValue(object, propertyName);
                                    if(date != null) {
                                        value.add(DateUtils.getDateYYYYMMDDHHMMSS(date));
                                    }else {
                                        value.add("");
                                    }
                                }else {
                                    value.add(org.apache.commons.beanutils.BeanUtils.getProperty(object,
                                            ResourceWrapper.JavaUtil.getJavaVarName(((JSONObject) column).getString("code"))));
                                }
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            } catch (NoSuchMethodException e) {
                                e.printStackTrace();
                            }
                        }
//                        values.add(BeanUtils.getPropertiesArray(object));
                    }
                    values.add(value.toArray(new String[0]));
                }
            }
        }
    }

    public class ArrayJsonSegmentParser extends AbstractJsonSegmentParser implements JsonSegmentParser{


        public ArrayJsonSegmentParser(Element element, String type) {
            super(element, type);
        }

        @Override
        public boolean ok() {
            return false;
        }

        public String[] values;

        @Override
        public String toJson() {
            return null;
        }

        public Object getJsonObject() {
//            JSONArray array = new JSONArray();
//            array.addAll(Arrays.asList(values));
            return JSONArray.toJSON(values);
        }

        @Override
        public void afterSetDataSetDescriptorAndMapping() {

        }
    }

    public class ObjectJsonSegmentParser extends AbstractJsonSegmentParser implements JsonSegmentParser{

        private Map<String, String> expressesMap= new LinkedHashMap<String, String>();

        private Map<String, String> resultMap= new LinkedHashMap<String, String>();

        private String express;

        public ObjectJsonSegmentParser(Element element,String type) {
            super(element, type);
        }

        @Override
        public boolean ok() {
            return expressesMap.size() == 0;
        }

        @Override
        public String toJson() {
            return null;
        }

        public JSONObject getJsonObject() {
            JSONObject jsonObject = new JSONObject();
            jsonObject.putAll(resultMap);
            return jsonObject;
        }

        @Override
        public void afterSetDataSetDescriptorAndMapping() {
            List<Mapping> mappingList = mapping.getMappingList();
            for (Mapping mapping1 : mappingList) {
                expressesMap.put(mapping1.getId(), mapping1.getValue());
            }
            express = mapping.getValue();
            if("${data}".equals(express)) {
                runtimeDataMap.put(express,this);
            }
        }

        public void setData(Object data, JSONArray columns) {

            if (data != null) {
                if (expressesMap.size() != 0) {
                    for (String string : expressesMap.values()) {
                        String propertyName = string.substring(2, string.length() - 1);
                        try {
                            resultMap.put(propertyName, org.apache.commons.beanutils.BeanUtils.getProperty(data, propertyName));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    for (Object column : columns) {
                        try {
                            resultMap.put(((JSONObject) column).getString("code"),
                                    org.apache.commons.beanutils.BeanUtils.getProperty(data,
                                            ResourceWrapper.JavaUtil.getJavaVarName(((JSONObject) column).getString("code"))));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public class ObjectsJsonSegmentParser extends AbstractJsonSegmentParser implements JsonSegmentParser{

        private String express;

        private Map<String, String> initMap= new LinkedHashMap<String, String>();

        private List<Map<String, String>> resultList= new ArrayList<Map<String, String>>();

        public ObjectsJsonSegmentParser(Element element, String type) {
            super(element, type);
        }

        @Override
        public boolean ok() {
            return resultList.size() != 0;
        }

        @Override
        public String toJson() {
            return null;
        }

        public JSONArray getJsonObject() {
            JSONArray jsonArray = new JSONArray();
            for (Map<String, String> stringStringMap : resultList) {
                jsonArray.add(JSONArray.toJSON(stringStringMap));
            }
            return jsonArray;
        }

        @Override
        public void afterSetDataSetDescriptorAndMapping() {

            List<Mapping> mappingList = mapping.getMappingList();
            for (Mapping mapping1 : mappingList) {
                initMap.put(mapping1.getId(), mapping1.getValue());
            }
            express = mapping.getValue();
            List<String> varList = RegexUtils.findVarList(express);
            for (String var : varList) {
                DataSet dataSet = dataSetDescriptor.getDataSet();
                if("columns".equals(var)) {
                    List<Field> fields = dataSet.getFields().getFieldList();
                    for (Field field : fields) {
                        Map<String, String> tempMap= new LinkedHashMap<String, String>();
                        for (String key : initMap.keySet()) {
                            tempMap.put(key, getValueFromField(field, initMap.get(key)));
                        }
                        resultList.add(tempMap);
                    }
                }
            }
        }

        private String getValueFromField(Field field, String code) {

            code = code.substring(2, code.length() - 1).trim();
            if("code".equals(code)) {
                return ResourceWrapper.JavaUtil.getJavaVarName(field.getCode());
            }else if("name".equals(code)) {
                return field.getName();
            }else if("editType".equals(code)) {
                if(this.componentType.startsWith("c")){
                    return StringUtils.isNotBlank(field.getCreateEditType()) ? field.getCreateEditType() : field.getEditType();
                }else if(this.componentType.startsWith("e")){
                    return StringUtils.isNotBlank(field.getUpdateEditType()) ? field.getUpdateEditType() : field.getEditType();
                }else if(this.componentType.startsWith("list")){//eList组件
                    return StringUtils.isNotBlank(field.getCreateEditType()) ? field.getCreateEditType() : field.getEditType();
                }else{
                    return field.getEditType();
                }
//            }else if("enumClass".equals(code)  && field.getEnumClass() != null) {
//                    return field.getEnumClass().getCode();
//            }else if("entityCode".equals(code) && field.getRel() != null) {
//                return field.getRel().getEntityCode().replaceAll("/", ".");
            }else if("relColumns".equals(code)  && field.getRel() != null) {
                return field.getRel().getRelField();
            }else {//${enumClass||entityCode}
                if(field.getEnumClass() != null && field.getEnumClass().getCode() != null) {
                    return field.getEnumClass().getCode();
                }
                if(field.getRel() != null && field.getRel().getEntityCode() != null) {
//                    String entityCode = field.getRel().getEntityCode();
//                    String entityName = entityCode.substring(0,entityCode.indexOf("/"));
//                    String entityKey = entityCode.substring(entityCode.indexOf("/") + 1, entityCode.lastIndexOf("/"));
//                    String entityShowName = entityCode.substring(entityCode.lastIndexOf("/"));
                    return field.getRel().getEntityCode().replaceAll("/",".");
                }
            }
            return null;
        }
    }



    public class EnumJsonSegmentParser extends AbstractJsonSegmentParser implements JsonSegmentParser {

        private String express;
        private String value;

        public EnumJsonSegmentParser(Element element, String type) {
            super(element, type);
        }

        @Override
        public boolean ok() {
            return StringUtils.isNotBlank(value);
        }

        @Override
        public String toJson() {
            return null;
        }

        public String getJsonObject() {
            return value;
        }

        @Override
        public void afterSetDataSetDescriptorAndMapping() {
            express = mapping.getValue();
            value = mapping.getValue();
        }

        public String getExpress() {
            return express;
        }

        public void setExpress(String express) {
            this.express = express;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public class CharacterJsonSegmentParser extends AbstractJsonSegmentParser implements JsonSegmentParser {

        private String express;
        private String value;

        public CharacterJsonSegmentParser(Element element, String type) {
            super(element,type);
        }

        @Override
        public boolean ok() {
            return StringUtils.isNotBlank(value);
        }

        @Override
        public String toJson() {
            return null;
        }

        public String getJsonObject() {
            return value;
        }

        @Override
        public void afterSetDataSetDescriptorAndMapping() {
            express = mapping.getValue();
            List<String> varList = RegexUtils.findVarList(express);
            for (String var : varList) {
                DataSet dataSet = dataSetDescriptor.getDataSet();
                if("code".equals(var)) {
                    express = express.replace("${code}",dataSet.getCode());
                }else if("name".equals(var)) {
                    express = express.replace("${name}",dataSet.getName());
                }else if("module".equals(var)) {
                    express = express.replace("${module}",dataSet.getModule());
                }
            }
            if(!express.contains("${") && !express.contains("}")) {
                value = express;
            }
        }

        public String getExpress() {
            return express;
        }

        public void setExpress(String express) {
            this.express = express;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public Map<String, JsonSegmentParser> getElements() {
        return elements;
    }

    public void setElements(Map<String, JsonSegmentParser> elements) {
        this.elements = elements;
    }

    public void addElement(JsonSegmentParser jonSegmentParser) {
        this.elements.put(jonSegmentParser.getId(), jonSegmentParser);
    }

    public class EventElement {
        private String component;
        private String params;
        private String action;
        private String fillclass;
        private JSONObject actionJsonObject = new JSONObject(true);

        private String anchorName;

        public EventElement(Event event) {
            if(event.getSource() != null) {
                params = event.getSource().getParam();
            }
            for (Effect effect : event.getEffectList()) {
                JSONObject subJsonObject = new JSONObject();
                subJsonObject.put("action",effect.getAction());
                subJsonObject.put("isStack",effect.getIsStack());
                subJsonObject.put("param",effect.getParam());
                subJsonObject.put("content",effect.getContent());
                subJsonObject.put("targetId",effect.getTargetId());
                actionJsonObject.put(effect.getType(),subJsonObject);
            }

            action = actionJsonObject.toJSONString();

            anchorName = event.getAttach().getAnchor();
            if(event.getAttach().getAppendElementList() != null) {
                for (AppendElement appendElement : event.getAttach().getAppendElementList()) {
                    component = parseComponent(appendElement, params, action);
                    fillclass = (String) JSONObject.parseObject(appendElement.getParam()).get("fillclass");
                }
            }
        }

        public String getAnchorName() {
            return anchorName;
        }

        public void setAnchorName(String anchorName) {
            this.anchorName = anchorName;
        }

        public String getFillclass() {
            return fillclass;
        }

        public void setFillclass(String fillclass) {
            this.fillclass = fillclass;
        }

        public EventElement(AppendElement appendElement) {
            component = parseComponent(appendElement, appendElement.getParam(), action);
            if(StringUtils.isNotBlank(appendElement.getParam())) {
                params = appendElement.getParam();
            }
        }

        private String parseComponent(AppendElement appendElement, String params, String action) {

            if("icon".equals(appendElement.getType())) {
                JSONObject jsonObject = JSONObject.parseObject(appendElement.getParam());
                return "<i class=\"${iconclass}\"></i>"
                        .replace("${iconclass}", (String) jsonObject.get("iconclass"));
            }else if("button".equals(appendElement.getType())) {
                JSONObject jsonObject = JSONObject.parseObject(appendElement.getParam());

                return "<button  class=\"btn hfhref ${btnclass}\" onclick=\"javascript:void(0)\"  params=\"${params}\" action='${action}'>${btnText}</button>"
                        .replace("${btnclass}", (String) jsonObject.get("btnclass"))
                        .replace("${btnText}", (String) jsonObject.get("btnText"))
                        .replace("${params}", StringUtils.isNotBlank(params) ? params : "")
                        .replace("${action}", StringUtils.isNotBlank(action) ? action : "");
            }else if("checkbox".equals(appendElement.getType())) {
                return "<input type=\"checkbox\" name=\"${id}\", value=\"${value}\">";
//                        .replace("${id}", (String) jsonObject.get("id"))
//                        .replace("${value}", (String) jsonObject.get("${id}"));
            }
            return null;
        }

        public String getComponent() {
            return component;
        }

        public void setComponent(String component) {
            this.component = component;
        }

        public String getParams() {
            return params;
        }

        public void setParams(String params) {
            this.params = params;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }
    }

    public enum ComponentElementType{
        characters("characters", "字符串"),
        enums("enums", "枚举"),
        object("object", "对象"),
        objects("objects","对象数组"),
        array("array","数组"),
        array2("array2", "二维数组");


        private String code;
        private String name;

        ComponentElementType(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public enum ComponentEventAnchor{
        BOFR("BOFR", "行开始"),
        EOFR("EOFR", "行结尾"),
        EOFC("EOFC", "组件结尾");

        private String code;
        private String name;

        ComponentEventAnchor(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
