package com.hframe.generator.bean;

import com.hframe.common.util.CommonUtils;
import com.hframe.domain.model.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 模型数据容器
 */
public class HfModelContainer {

	public static final int TYPE_ADD = 1;
	public static final int TYPE_MOD = 2;
	public static final int TYPE_DEL = 3;

	//项目信息
	private HfpmProgram program;
	//模块信息
	private Map<Long, HfpmModule> moduleMap;

	//实体信息<entityName,HfmdEntity>
	private Map<String,HfmdEntity> entityMap;
	//实体属性信息<entityCode.entityAttrCode,HfmdEntityAttr>
	private Map<String,HfmdEntityAttr> entityAttrMap;

	//实体属计数表
	private Map<String,BigDecimal> entityAttrCountMap;
	//实体关系信息
	private Map<Long,List<HfmdEntityRel>> entityRelMap;

	//枚举类信息
	private Map<Long,HfmdEnumClass> enumClassMap;
	//枚举信息
	private Map<Long,HfmdEnum> enumMap;

	//数据集信息
	private Map<String, HfpmDataSet> dataSetMap;
	//数据列信息
	private Map<String, List<HfpmDataField>> dataFieldListMap;

	//1:新增结构 2：修改结构 3：删除结构
	private int containerType = 0;

	public HfmdEntity getEntity(String entityName) {
		if(entityMap == null) {
			entityMap = new HashMap<String, HfmdEntity>();
		}
		if(!entityMap.containsKey(entityName)) {
			HfmdEntity entity = new HfmdEntity();
			entity.setHfmdEntityCode(entityName.trim());
			entity.setHfmdEntityId(CommonUtils.uuidL());
			entityMap.put(entityName, entity);
		}
		return entityMap.get(entityName.trim());
	}

	public void removeEntity(String entityName) {
		if(entityMap != null) {
			entityMap.remove(entityName);
		}
	}

	public HfmdEntityAttr getEntityAttr(String entityName, String entityAttrName) {
		if(entityAttrMap == null) {
			entityAttrMap = new HashMap<String, HfmdEntityAttr>();
			entityAttrCountMap = new HashMap<String, BigDecimal>();
		}

		if(!entityAttrCountMap.containsKey(entityName)) {
			entityAttrCountMap.put(entityName,new BigDecimal("0.0"));
		}

		if(!entityAttrMap.containsKey(entityName + "." + entityAttrName)) {
			HfmdEntityAttr entity = new HfmdEntityAttr();
			entity.setHfmdEntityAttrCode(entityAttrName);
			entity.setHfmdEntityAttrId(CommonUtils.uuidL());
			entityAttrMap.put(entityName + "." + entityAttrName, entity);

			BigDecimal bigDecimal = entityAttrCountMap.get(entityName);
			entityAttrCountMap.put(entityName,bigDecimal.add(new BigDecimal("1.0")));

			entity.setPri(entityAttrCountMap.get(entityName));
		}
		return entityAttrMap.get(entityName + "."+ entityAttrName);
	}

	public void removeEntityAttr(String entityAttrName) {
		if(entityAttrMap != null) {
			entityAttrMap.remove(entityAttrName);
		}
	}

	public HfpmProgram getProgram() {
		return program;
	}

	public void setProgram(HfpmProgram program) {
		this.program = program;
	}

	public Map<Long, HfpmModule> getModuleMap() {
		if(moduleMap == null) {
			moduleMap = new HashMap<Long, HfpmModule>();
		}
		return moduleMap;
	}

	public void setModuleMap(Map<Long, HfpmModule> moduleMap) {
		this.moduleMap = moduleMap;
	}

	public Map<String, HfmdEntity> getEntityMap() {
		return entityMap;
	}

	public void setEntityMap(Map<String, HfmdEntity> entityMap) {
		this.entityMap = entityMap;
	}

	public Map<String, HfmdEntityAttr> getEntityAttrMap() {
		return entityAttrMap;
	}

	public void setEntityAttrMap(Map<String, HfmdEntityAttr> entityAttrMap) {
		this.entityAttrMap = entityAttrMap;
	}

	public Map<Long, HfmdEnumClass> getEnumClassMap() {
		return enumClassMap;
	}

	public void setEnumClassMap(Map<Long, HfmdEnumClass> enumClassMap) {
		this.enumClassMap = enumClassMap;
	}

	public Map<Long, HfmdEnum> getEnumMap() {
		return enumMap;
	}

	public void setEnumMap(Map<Long, HfmdEnum> enumMap) {
		this.enumMap = enumMap;
	}


	public Map<Long, List<HfmdEntityRel>> getEntityRelMap() {
		return entityRelMap;
	}

	public void setEntityRelMap(Map<Long, List<HfmdEntityRel>> entityRelMap) {
		this.entityRelMap = entityRelMap;
	}

	public Map<String, BigDecimal> getEntityAttrCountMap() {
		return entityAttrCountMap;
	}

	public void setEntityAttrCountMap(Map<String, BigDecimal> entityAttrCountMap) {
		this.entityAttrCountMap = entityAttrCountMap;
	}

	public Map<String, HfpmDataSet> getDataSetMap() {
		return dataSetMap;
	}

	public void setDataSetMap(Map<String, HfpmDataSet> dataSetMap) {
		this.dataSetMap = dataSetMap;
	}

	public Map<String, List<HfpmDataField>> getDataFieldListMap() {
		return dataFieldListMap;
	}

	public void setDataFieldListMap(Map<String, List<HfpmDataField>> dataFieldListMap) {
		this.dataFieldListMap = dataFieldListMap;
	}

	public int getContainerType() {
		return containerType;
	}

	public void setContainerType(int containerType) {
		this.containerType = containerType;
	}
}
