package com.hframe.generator.bean;


import com.hframe.generator.util.CreatorUtil;

public class CreatorContainer {

	public static final String projectBasePath= CreatorUtil.projectBasePath;
	public static final String projectTomcatBasePath=CreatorUtil.projectTomcatBasePath;

	public  String companyName; //公司名称
	public  String projectName; //项目名称
	public  String moduleName; //模块名称
	public  String tableName; //表名称


	public Class Po;
	public Class PoExample; //PO查询对象 mybatis用到
	public Class Dao;
	public Class DaoImpl;
	public Class Mapper;
	public Class Service;
	public Class ServiceImpl;
	public Class Action;
	public Class Controller;

}
