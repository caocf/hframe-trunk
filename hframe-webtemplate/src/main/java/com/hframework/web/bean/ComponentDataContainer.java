package com.hframework.web.bean;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hframework.beans.controller.Pagination;
import com.hframework.common.util.BeanUtils;
import com.hframework.common.util.EnumUtils;
import com.hframework.common.util.RegexUtils;
import com.hframework.web.config.bean.Component;
import com.hframework.web.config.bean.DataSet;
import com.hframework.web.config.bean.component.AppendElement;
import com.hframework.web.config.bean.component.Effect;
import com.hframework.web.config.bean.component.Element;
import com.hframework.web.config.bean.component.Event;
import com.hframework.web.config.bean.dataset.Field;
import com.hframework.web.config.bean.mapper.Mapping;
import org.apache.commons.lang.StringUtils;

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
    private Map<String, EventElement> elementOfRowMap = new HashMap<String, EventElement>();

    private Map<String, JsonSegmentParser> runtimeDataMap = new HashMap<String, JsonSegmentParser>();
    private String json;
    private JSONObject jsonObject;

    public ComponentDataContainer(Component component) {
        if(component == null) {
            return;
        }
        for (com.hframework.web.config.bean.component.Element element : component.getElementList()) {
            if(EnumUtils.compare(ComponentElementType.characters, element.getType())) {
                elements.put(element.getId(), new CharacterJsonSegmentParser(element));
            }else if(EnumUtils.compare(ComponentElementType.enums, element.getType())) {
                elements.put(element.getId(), new EnumJsonSegmentParser(element));
            }else if(EnumUtils.compare(ComponentElementType.object, element.getType())) {
                elements.put(element.getId(), new ObjectJsonSegmentParser(element));
            }else if(EnumUtils.compare(ComponentElementType.objects, element.getType())) {
                elements.put(element.getId(), new ObjectsJsonSegmentParser(element));
            }else if(EnumUtils.compare(ComponentElementType.array, element.getType())) {
                elements.put(element.getId(), new ArrayJsonSegmentParser(element));
            }else if(EnumUtils.compare(ComponentElementType.array2, element.getType())) {
                elements.put(element.getId(), new Array2JsonSegmentParser(element));
            }

            List<Event> allEvent = new ArrayList<Event>();
            allEvent.addAll(component.getBaseEvents().getEventList());
            allEvent.addAll(component.getEvents().getEventList());
            for (Event event : allEvent) {
                if(EnumUtils.compare(ComponentEventAnchor.BOFR, event.getAttach().getAnchor())) {
                    beforeOfRowList.add(new EventElement(event));
                }else if(EnumUtils.compare(ComponentEventAnchor.EOFR, event.getAttach().getAnchor())) {
                    endOfRowList.add(new EventElement(event));
                }else if(EnumUtils.compare(ComponentEventAnchor.EOFC, event.getAttach().getAnchor())) {
                    endOfCompList.add(new EventElement(event));
                }else {
                    elementOfRowMap.put(event.getName(), new EventElement(event));
                }

                AppendElement appendElement = event.getSource().getAppendElement();
                if(appendElement != null) {
                    if(EnumUtils.compare(ComponentEventAnchor.BOFR, appendElement.getType())) {
                        beforeOfRowList.add(new EventElement(appendElement));
                    }else if(EnumUtils.compare(ComponentEventAnchor.EOFR, appendElement.getType())) {
                        endOfRowList.add(new EventElement(appendElement));
                    }else if(EnumUtils.compare(ComponentEventAnchor.EOFC, appendElement.getType())) {
                        endOfCompList.add(new EventElement(appendElement));
                    }else {
                        elementOfRowMap.put(event.getName(), new EventElement(event));
                    }
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
            peddingEventElement(new ArrayList<EventElement>(elementOfRowMap.values()), mapping, dataSetDescriptor);
        }
    }

    private void peddingEventElement(List<EventElement> eventElementList, Mapping mapping, DataSetDescriptor dataSetDescriptor) {
        for (EventElement eventElement : eventElementList) {
            eventElement.setParams(eventElement.getParams() == null ? null :
                    eventElement.getParams().replace("${" + mapping.getId() + "}", mapping.getValue()));
            eventElement.setAction(eventElement.getAction() == null ? null :
                    eventElement.getAction().replace("${" + mapping.getId() + "}", mapping.getValue()));
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
                        segmentParser.setData(map.get("list"));
                    }
                } else if ("pager".equals(key)) {
                    JsonSegmentParser jsonSegmentParser = runtimeDataMap.get("${pager}");
                    if (jsonSegmentParser instanceof Array2JsonSegmentParser) {
                        Array2JsonSegmentParser segmentParser = (Array2JsonSegmentParser) jsonSegmentParser;
                        segmentParser.setData(map.get("pager"));
                    }
                }
            }
        } else {//详情返回

        }
        return null;
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

    public String getJson() {
        jsonObject = new JSONObject();
        for (String key : elements.keySet()) {
            jsonObject.put(key,elements.get(key).getJsonObject());
        }
        return jsonObject.toJSONString();
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

        private Element element;

        protected Mapping mapping;

        protected DataSetDescriptor dataSetDescriptor;

        public AbstractJsonSegmentParser(Element element){
            this.element =element;
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

        public Array2JsonSegmentParser(Element element) {
            super(element);
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

        public void setData(Object data) {
            this.data = data;
            if (data instanceof List) {
                values = new ArrayList<String[]>();
                List list = (List) data;
                for (Object object : list) {
                    if(initMap.size() == 0) {
                        List<String> value= new ArrayList<String>();
                        for (String string : initMap.values()) {
                            String propertyName = string.substring(2,string.length()-1);
                            try {
                                value.add(org.apache.commons.beanutils.BeanUtils.getProperty(object,propertyName));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        values.add(value.toArray(new String[0]));
                    }else {
                        values.add(BeanUtils.getPropertiesArray(object));
                    }

                }
            }
        }
    }

    public class ArrayJsonSegmentParser extends AbstractJsonSegmentParser implements JsonSegmentParser{


        public ArrayJsonSegmentParser(Element element) {
            super(element);
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

        public ObjectJsonSegmentParser(Element element) {
            super(element);
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

        }
    }

    public class ObjectsJsonSegmentParser extends AbstractJsonSegmentParser implements JsonSegmentParser{

        private String express;

        private Map<String, String> initMap= new LinkedHashMap<String, String>();

        private List<Map<String, String>> resultList= new ArrayList<Map<String, String>>();

        public ObjectsJsonSegmentParser(Element element) {
            super(element);
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
                return field.getCode();
            }else if("name".equals(code)) {
                return field.getName();
            }else if("editType".equals(code)) {
                return field.getEditType();
            }
            return null;
        }
    }



    public class EnumJsonSegmentParser extends AbstractJsonSegmentParser implements JsonSegmentParser {

        private String express;
        private String value;

        public EnumJsonSegmentParser(Element element) {
            super(element);
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

        public CharacterJsonSegmentParser(Element element) {
            super(element);
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
        private JSONObject actionJsonObject = new JSONObject();

        public EventElement(Event event) {
            if(event.getAttach().getAppendElementList() != null) {
                for (AppendElement appendElement : event.getAttach().getAppendElementList()) {
                    component = parseComponent(appendElement);
                }
            }
            if(event.getSource() != null) {
                params = event.getSource().getParam();
            }
            for (Effect effect : event.getEffectList()) {
                JSONObject subJsonObject = new JSONObject();
                subJsonObject.put("action",effect.getAction());
                subJsonObject.put("isStack",effect.getIsStack());
                subJsonObject.put("param",effect.getParam());
                actionJsonObject.put(effect.getType(),subJsonObject);
            }

            action = actionJsonObject.toJSONString();
        }

        public EventElement(AppendElement appendElement) {
            component = parseComponent(appendElement);
            if(StringUtils.isNotBlank(appendElement.getParam())) {
                params = appendElement.getParam();
            }
        }

        private String parseComponent(AppendElement appendElement) {
            JSONObject jsonObject = JSONObject.parseObject(appendElement.getParam());
            if("icon".equals(appendElement.getType())) {
                return "<i class=\"${iconclass}\"></i>"
                        .replace("${iconclass}", (String) jsonObject.get("iconclass"));
            }else if("button".equals(appendElement.getType())) {
                return "<button  class=\"btn ${btnclass}\">${btnText}</button>"
                        .replace("${btnclass}", (String) jsonObject.get("btnclass"))
                        .replace("${btnText}", (String) jsonObject.get("btnText"));
            }else if("checkbox".equals(appendElement.getType())) {
                return "<input type=\"checkbox\" name=\"${id}\", value=\"${value}\">"
                        .replace("${id}", (String) jsonObject.get("id"))
                        .replace("${value}", (String) jsonObject.get("${id}"));
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
