package com.hframe.generator.base;

import com.hframe.common.util.StringUtils;
import com.hframe.common.util.message.VelocityUtil;
import com.hframe.generator.bean.Field;
import com.hframe.generator.bean.Table;
import com.hframe.generator.util.CreatorUtil;

import java.util.HashMap;
import java.util.Map;

public class ControllerGenerator extends AbstractGenerator {

	public ControllerGenerator(String companyName, String projectName, String moduleName, Table table) throws Exception {
		super(companyName, projectName, moduleName, table);
		setEditClass(controller);
	}

	/**
	 * 设置引入包信息
	 */
	@Override
	public void setImportClass() {
		editClass.addImportClass("com.hframe.common.util.ExampleUtils");
		editClass.addImportClass("com.hframe.controller.bean.ResultMessage");
		editClass.addImportClass("org.springframework.stereotype.Controller");
		editClass.addImportClass("org.springframework.web.bind.annotation.ModelAttribute");
		editClass.addImportClass("org.springframework.web.bind.annotation.RequestMapping");
		editClass.addImportClass("org.springframework.web.bind.annotation.ResponseBody");
		editClass.addImportClass("org.springframework.web.servlet.ModelAndView");
		editClass.addImportClass("javax.annotation.Resource");
		editClass.addImportClass("java.util.*");

		editClass.addImportClass(poClass.getClassPath());
		editClass.addImportClass(poExampleClass.getClassPath());
		editClass.addImportClass(serviceClass.getClassPath());

		editClass.addAnnotation("@Controller");
		editClass.addAnnotation("@RequestMapping(value = \"" +
				(StringUtils.isBlank(moduleName)?"":"/"+ moduleName) + "/" + CreatorUtil.getJavaVarName(table.getTableName()) + "\")");
	}

	/**
	 * 设置类字段信息
	 */
	@Override
	public void setField() {
		editClass.addField(new Field(serviceClass.getClassName()).addFieldAnno("@Resource"));
	}

	/**
	 * 创建方法
	 */
	@Override
	public void createMethod() {
		Map contentMap=new HashMap();
		contentMap.put("ClassName", CreatorUtil.getJavaClassName(table.getTableName()));
		contentMap.put("VarName", CreatorUtil.getJavaVarName(table.getTableName()));
		contentMap.put("EntityName", table.getTableDesc());
		contentMap.put("SeviceClassName", CreatorUtil.getJavaClassName(serviceClass.getClassName()));
		contentMap.put("SeviceVarName", StringUtils.lowerCaseFirstChar(serviceClass.getClassName()));
		contentMap.put("PoExampleClassName", poExampleClass.getClassName());

		contentMap.put("ProjectName", projectName);
		contentMap.put("ModuleName", moduleName);

		String methodStr = VelocityUtil.produceTemplateContent(
				"com/hframe/generator/vm/controller_method_content.vm", contentMap);

		editClass.setExtMethodStr(methodStr);
	}
}
