package com.hframe.generator.bean;

public class Field {

	private String type;
	private String name;
	private String ucName;

	private String fieldAnno;
	private String getMethodAnno;

	public Field(String type) {
		super();
		this.type = type;
		this.name = type.substring(0,1).toLowerCase() + type.substring(1);
		this.ucName = type;
	}

	public Field(String type, String name) {
		super();
		this.type = type;
		this.name = name;
		this.ucName = name.substring(0,1).toUpperCase() + name.substring(1);
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getUcName() {
		return ucName;
	}

	public void setUcName(String ucName) {
		this.ucName = ucName;
	}

	public Field addFieldAnno(String fieldAnno) {
		this.fieldAnno = fieldAnno;
		return this;
	}

	public Field addGetMethodAnno(String getMethodAnno) {
		this.getMethodAnno = getMethodAnno;
		return this;
	}

	public String getFieldAnno() {
		return fieldAnno;
	}

//	public void setFieldAnno(String fieldAnno) {
//		this.fieldAnno = fieldAnno;
//	}

	public String getGetMethodAnno() {
		return getMethodAnno;
	}

//	public void setGetMethodAnno(String getMethodAnno) {
//		this.getMethodAnno = getMethodAnno;
//	}



}
