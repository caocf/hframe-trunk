package com.hframe.generator.bean;

import com.hframe.generator.bean.Field;

import java.util.ArrayList;
import java.util.List;

public class Method {

	private String modifier;

	private String name;

	private String returnType;

	private String body;

	private List<Field> parameterList = new ArrayList<Field>();

	public void addParameter(Field field) {
		parameterList.add(field);
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}



}
