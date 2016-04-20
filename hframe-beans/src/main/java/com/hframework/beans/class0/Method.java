package com.hframework.beans.class0;

import java.util.ArrayList;
import java.util.List;

public class Method {

	private String modifier = "public";

	private String name;

	private String returnType = "void";

	private String body;

	private String parameterStr = null;

	private String exceptionStr;

	private List<String> codelnList = new ArrayList<String>();

	private List<Field> parameterList = new ArrayList<Field>();

	public void addParameter(Field field) {
		parameterList.add(field);
		getParameterStr();
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

	public String getParameterStr() {

		if(parameterList != null && parameterList.size() > 0) {
			parameterStr = null;
			for (Field field : parameterList) {
				if(parameterStr == null) {
					parameterStr = field.getType() + " " + field.getName();
				}else {
					parameterStr += (", " + field.getType() + " " + field.getName());
				}

			}
		}
		return parameterStr;
	}

	public void addCodeLn(String codeLn) {
		codelnList.add(codeLn);
	}

	public void setParameterStr(String parameterStr) {
		this.parameterStr = parameterStr;
	}

	public List<String> getCodelnList() {
		return codelnList;
	}

	public void setCodelnList(List<String> codelnList) {
		this.codelnList = codelnList;
	}

	public List<Field> getParameterList() {
		return parameterList;
	}

	public void setParameterList(List<Field> parameterList) {
		this.parameterList = parameterList;
	}

	public String getExceptionStr() {
		return exceptionStr;
	}

	public void setExceptionStr(String exceptionStr) {
		this.exceptionStr = exceptionStr;
	}
}
