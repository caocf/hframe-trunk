package com.hframework.generator.web;

import com.hframe.common.util.StringUtils;
import com.hframe.common.util.message.VelocityUtil;
import com.hframework.beans.class0.Field;
import com.hframework.beans.class0.Table;
import com.hframework.generator.util.CreatorUtil;

import java.util.HashMap;
import java.util.Map;

/**
 *  @author zhangqh6
 */
public class ServiceImplGenerator extends AbstractGenerator{

	public ServiceImplGenerator(String companyName, String projectName, String moduleName, Table table) throws Exception {
		super(companyName, projectName, moduleName, table);
		setEditClass(serviceImplClass);
	}
	
	@Override
	public void setImportClass() {

		editClass.addImportClass("java.util.*");
		editClass.addImportClass("org.springframework.stereotype.Service");
		editClass.addImportClass("javax.annotation.Resource");

		editClass.addImportClass(poClass.getClassPath());
		editClass.addImportClass(poExampleClass.getClassPath());
		editClass.addImportClass(mapper.getClassPath());
		editClass.addImportClass(serviceClass.getClassPath());

		editClass.setAnnotation("@Service(\"" + StringUtils.lowerCaseFirstChar(serviceClass.getClassName()) + "\")");
		editClass.addInterface(serviceClass.getClassName());
	}

	@Override
	public void setField() {
		//注入对应的DAO
		editClass.addField(new Field(mapper.getClassName()).addFieldAnno("@Resource"));
	}

	@Override
	public void createMethod() {
		Map contentMap=new HashMap();
		contentMap.put("ClassName", CreatorUtil.getJavaClassName(table.getTableName()));
		contentMap.put("VarName", CreatorUtil.getJavaVarName(table.getTableName()));
		contentMap.put("EntityName", table.getTableDesc());
		String methodStr = VelocityUtil.produceTemplateContent(
				"com/hframework/generator/vm/service_impl_method_content.vm", contentMap);

		editClass.setExtMethodStr(methodStr);
	}
}
