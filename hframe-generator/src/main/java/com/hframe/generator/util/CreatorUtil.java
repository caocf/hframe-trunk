package com.hframe.generator.util;

import com.hframe.common.frame.cache.PropertyConfigurerUtils;
import com.hframe.common.util.StringUtils;
import com.hframe.generator.bean.*;
import com.hframe.generator.bean.Class;
import com.hframe.generator.constants.CreatorConst;


public class CreatorUtil {

	public static final String projectBasePath =
			PropertyConfigurerUtils.getProperty(CreatorConst.PROJECT_BASE_FILE_PATH);
	public static final String projectTomcatBasePath =
			PropertyConfigurerUtils.getProperty(CreatorConst.PROJECT_TOMCAT_BASE_FILE_PATH);

	public static String getJavaClassName(String tableName) {

		String returnName = "";

		tableName = tableName.toLowerCase();

		String[] parts = tableName.split("[_]+");
		for (String part : parts) {
			if (!"".equals(part)) {
				returnName += part.substring(0, 1).toUpperCase()
						+ part.substring(1);
			}
		}

		return returnName;
	}

	/**
	 * @param companyName
	 * @param projectName
	 * @param tableName
	 * @return 获取SQL文件在项目中存放的路径即名称
	 * @throws Exception
	 */
	public static String getSQLFilePath(String companyName,
										String projectName,String tableName) throws Exception {

		if(StringUtils.isBlank(tableName)) {
			throw new Exception("表名称为不能为空！");
		}

		companyName = StringUtils.isBlank(companyName)?"":"."+(companyName);
		projectName = StringUtils.isBlank(projectName)?"":"."+(projectName);

		return PropertyConfigurerUtils.getProperty(CreatorConst.SQL_FILE_PATH,
				companyName.toLowerCase(),projectName.toLowerCase(),tableName.toLowerCase());
	}


	/**
	 * @param companyName
	 * @param projectName
	 * @return 获取SQL文件在项目中存放的路径即名称
	 * @throws Exception
	 */
	public static String getSrcFilePath(String companyName,
										String projectName) throws Exception {

//		if(StringUtil.isBlank(companyName)) {
//			throw new Exception("公司名称为不能为空！");
//		}
		if("".equals(companyName) || companyName == null){
			companyName="zqh";
		}


		if(StringUtils.isBlank(projectName)) {
			throw new Exception("项目名称为不能为空！");
		}

//		companyName.toLowerCase(),
//		projectName.toLowerCase(),
//		tableName.toLowerCase(),
//		getJavaClassName(tableName.toLowerCase())

//		PropertyConfigurerUtils.getProperty(CreatorConst.PROJECT_SRC_FILE_PATH+"." + projectName);

		return PropertyConfigurerUtils.getProperty(CreatorConst.PROJECT_SRC_FILE_PATH);
	}

	/**
	 * @param companyName
	 * @param projectName
	 * @param tableName
	 * @return 获取SQL文件在项目中存放的路径即名称
	 * @throws Exception
	 */
	public static String getDAOClassPackage(String companyName,
											String projectName,String moduleName, String tableName) throws Exception {

//		if(StringUtils.isBlank(tableName)) {
//			throw new Exception("表名称为不能为空！");
//		}
//
//		companyName = StringUtils.isBlank(companyName)?"":"."+(companyName);
//		projectName = StringUtils.isBlank(projectName)?"":"."+(projectName);
//		projectName = StringUtils.isBlank(projectName)?"":"."+(projectName);
		return PropertyConfigurerUtils.getProperty(
				CreatorConst.DAO_CLASS_PACKAGE,
				companyName.toLowerCase(),
				moduleName.toLowerCase(),
				tableName.toLowerCase());
	}

	public static String getDAOImplClassPackage(String companyName,
												String projectName,String moduleName,String tableName) throws Exception {
//
//		if(StringUtils.isBlank(tableName)) {
//			throw new Exception("表名称为不能为空！");
//		}
//
//		companyName = StringUtils.isBlank(companyName)?"":"."+(companyName);
//		projectName = StringUtils.isBlank(projectName)?"":"."+(projectName);

		return PropertyConfigurerUtils.getProperty(
				CreatorConst.DAOIMPL_CLASS_PACKAGE,
				companyName.toLowerCase(),
				projectName.toLowerCase(),
				moduleName.toLowerCase());
	}

	public static String getServiceClassPackage(String companyName,
												String projectName,String moduleName, String tableName) throws Exception {

//		if(StringUtils.isBlank(tableName)) {
//			throw new Exception("表名称为不能为空！");
//		}
//
//		companyName = StringUtils.isBlank(companyName)?"":"."+(companyName);
//		projectName = StringUtils.isBlank(projectName)?"":"."+(projectName);

		return PropertyConfigurerUtils.getProperty(
				CreatorConst.SERVICE_CLASS_PACKAGE,
				companyName.toLowerCase(),
				projectName.toLowerCase(),
				moduleName.toLowerCase());
	}

	public static String getServiceImplClassPackage(String companyName,
													String projectName,String moduleName, String tableName) throws Exception {

//		if(StringUtils.isBlank(tableName)) {
//			throw new Exception("表名称为不能为空！");
//		}
//
//		companyName = StringUtils.isBlank(companyName)?"":"."+(companyName);
//		projectName = StringUtils.isBlank(projectName)?"":"."+(projectName);

		return PropertyConfigurerUtils.getProperty(
				CreatorConst.SERVICEIMPL_CLASS_PACKAGE,
				companyName.toLowerCase(),
				projectName.toLowerCase(),
				moduleName.toLowerCase());
	}

	public static String getActionClassPackage(String companyName,
											   String projectName,String moduleName,String tableName) throws Exception {

//		if(StringUtils.isBlank(tableName)) {
//			throw new Exception("表名称为不能为空！");
//		}
//
//		companyName = StringUtils.isBlank(companyName)?"":"."+(companyName);
//		projectName = StringUtils.isBlank(projectName)?"":"."+(projectName);

		return PropertyConfigurerUtils.getProperty(
				CreatorConst.ACTION_CLASS_PACKAGE,
				companyName.toLowerCase(),
				projectName.toLowerCase(),
				moduleName.toLowerCase());
	}

	/**
	 * @param companyName
	 * @param projectName
	 * @param tableName
	 * @return 获取SQL文件在项目中存放的路径即名称
	 * @throws Exception
	 */
	public static String getPoClassPackage(String companyName,
										   String projectName,String moduleName,String tableName) throws Exception {
//
//		if(StringUtils.isBlank(tableName)) {
//			throw new Exception("表名称为不能为空！");
//		}
//
//		companyName = StringUtils.isBlank(companyName)?"":"."+(companyName);
//		projectName = StringUtils.isBlank(projectName)?"":"."+(projectName);
//		moduleName = StringUtils.isBlank(moduleName)?"":"."+(moduleName);
		return PropertyConfigurerUtils.getProperty(
				CreatorConst.PO_CLASS_PACKAGE,
				companyName.toLowerCase(),
				projectName.toLowerCase(),
				moduleName.toLowerCase(),
				getJavaClassName(tableName.toLowerCase()));
	}



	public static String getDAOClassPath(String companyName,
										 String projectName,String tableName) throws Exception {

		if(StringUtils.isBlank(tableName)) {
			throw new Exception("表名称为不能为空！");
		}

		companyName = StringUtils.isBlank(companyName)?"":"."+(companyName);
		projectName = StringUtils.isBlank(projectName)?"":"."+(projectName);

		return PropertyConfigurerUtils.getProperty(
				CreatorConst.DAO_CLASS_PACKAGE,
				companyName.toLowerCase(),
				projectName.toLowerCase(),
				tableName.toLowerCase(),
				getJavaClassName(tableName.toLowerCase()));
	}

	public static com.hframe.generator.bean.Class getDefPoClass(String companyName,
									  String projectName, String moduleName,String tableName) throws Exception {
		if(StringUtils.isBlank(tableName)) {
			throw new Exception("表名称为不能为空！");
		}

		companyName = StringUtils.isBlank(companyName)?"":"."+(companyName);
		projectName = StringUtils.isBlank(projectName)?"":"."+(projectName);
		moduleName = StringUtils.isBlank(moduleName)?"":"."+(moduleName);


		Class class1 = new Class();
		class1.setSrcFilePath(CreatorUtil.getSrcFilePath(companyName, projectName));
		class1.setClassPackage(CreatorUtil.getPoClassPackage(
				companyName, projectName, moduleName,tableName));
		class1.setClassName(CreatorUtil.getJavaClassName(tableName) + "");
		return class1;
	}


	public static com.hframe.generator.bean.Class getDefPoExampleClass(String companyName,
																String projectName,String moduleName, String tableName) throws Exception {
		if(StringUtils.isBlank(tableName)) {
			throw new Exception("表名称为不能为空！");
		}

		companyName = StringUtils.isBlank(companyName)?"":"."+(companyName);
		projectName = StringUtils.isBlank(projectName)?"":"."+(projectName);
		moduleName = StringUtils.isBlank(moduleName)?"":"."+(moduleName);


		Class class1 = new Class();
		class1.setSrcFilePath(CreatorUtil.getSrcFilePath(companyName, projectName));
		class1.setClassPackage(CreatorUtil.getPoClassPackage(
				companyName, projectName, moduleName,tableName));
		class1.setClassName(CreatorUtil.getJavaClassName(tableName) + "_Example");
		return class1;
	}

	public static Class getDefDaoClass(String companyName,
									   String projectName, String moduleName,String tableName) throws Exception {
		if(StringUtils.isBlank(tableName)) {
			throw new Exception("表名称为不能为空！");
		}

		companyName = StringUtils.isBlank(companyName)?"":"."+(companyName);
		projectName = StringUtils.isBlank(projectName)?"":"."+(projectName);
		moduleName = StringUtils.isBlank(moduleName)?"":"."+(moduleName);

		Class class1 = new Class();
		class1.setSrcFilePath(CreatorUtil.getSrcFilePath(companyName, projectName));
		class1.setClassPackage(CreatorUtil.getDAOClassPackage(
				companyName, projectName, moduleName,tableName));
		class1.setClassName(CreatorUtil.getJavaClassName(tableName) + "DAO");
		return class1;
	}

	public static Class getDefMapperClass(String companyName,
									   String projectName, String moduleName,String tableName) throws Exception {
		if(StringUtils.isBlank(tableName)) {
			throw new Exception("表名称为不能为空！");
		}

		companyName = StringUtils.isBlank(companyName)?"":"."+(companyName);
		projectName = StringUtils.isBlank(projectName)?"":"."+(projectName);
		moduleName = StringUtils.isBlank(moduleName)?"":"."+(moduleName);

		Class class1 = new Class();
		class1.setSrcFilePath(CreatorUtil.getSrcFilePath(companyName, projectName));
		class1.setClassPackage(CreatorUtil.getDAOClassPackage(
				companyName, projectName, moduleName,tableName));
		class1.setClassName(CreatorUtil.getJavaClassName(tableName) + "Mapper");
		return class1;
	}


	public static CreatorContainer getCreatorContainer(String companyName, String projectName,
													   String moduleName, String tableName)  throws Exception {
		CreatorContainer container= new CreatorContainer();
		container.companyName = companyName;
		container.projectName = projectName;
		container.Po = getDefPoClass(companyName, projectName, moduleName, tableName);
		container.Dao = getDefDaoClass(companyName, projectName, moduleName, tableName);
		container.DaoImpl = getDefDaoImplClass(companyName, projectName, moduleName, tableName);
		container.Mapper = getDefMapperClass(companyName, projectName, moduleName, tableName);
		container.Service = getDefServiceClass(companyName, projectName, moduleName, tableName);
		container.ServiceImpl = getDefServiceImplClass(companyName, projectName, moduleName, tableName);
		container.Action = getDefActionClass(companyName, projectName, moduleName, tableName);
		container.Controller = getDefControllerClass(companyName, projectName, moduleName, tableName);

		container.PoExample = getDefPoExampleClass(companyName, projectName, moduleName, tableName);

		return container;

	}

	public static Class getDefDaoImplClass(String companyName,
										   String projectName, String moduleName,String tableName) throws Exception {
		if(StringUtils.isBlank(tableName)) {
			throw new Exception("表名称为不能为空！");
		}

		companyName = StringUtils.isBlank(companyName)?"":"."+(companyName);
		projectName = StringUtils.isBlank(projectName)?"":"."+(projectName);
		moduleName = StringUtils.isBlank(moduleName)?"":"."+(moduleName);

		Class class1 = new Class();
		class1.setSrcFilePath(CreatorUtil.getSrcFilePath(companyName, projectName));
		class1.setClassPackage(CreatorUtil.getDAOImplClassPackage(
				companyName, projectName, moduleName,tableName));
		class1.setClassName(CreatorUtil.getJavaClassName(tableName) + "DAOImpl");
		return class1;
	}

	public static Class getDefServiceClass(String companyName,
										   String projectName, String moduleName,String tableName) throws Exception {
		if(StringUtils.isBlank(tableName)) {
			throw new Exception("表名称为不能为空！");
		}

		companyName = StringUtils.isBlank(companyName)?"":"."+(companyName);
		projectName = StringUtils.isBlank(projectName)?"":"."+(projectName);
		moduleName = StringUtils.isBlank(moduleName)?"":"."+(moduleName);

		Class class1 = new Class();
		class1.setSrcFilePath(CreatorUtil.getSrcFilePath(companyName, projectName));
		class1.setClassPackage(CreatorUtil.getServiceClassPackage(
				companyName, projectName, moduleName,tableName));
		class1.setClassName("I" + CreatorUtil.getJavaClassName(tableName) + "SV");
		return class1;
	}

	public static Class getDefServiceImplClass(String companyName,
											   String projectName, String moduleName,String tableName) throws Exception {
		if(StringUtils.isBlank(tableName)) {
			throw new Exception("表名称为不能为空！");
		}

		companyName = StringUtils.isBlank(companyName)?"":"."+(companyName);
		projectName = StringUtils.isBlank(projectName)?"":"."+(projectName);
		moduleName = StringUtils.isBlank(moduleName)?"":"."+(moduleName);

		Class class1 = new Class();
		class1.setSrcFilePath(CreatorUtil.getSrcFilePath(companyName, projectName));
		class1.setClassPackage(CreatorUtil.getServiceImplClassPackage(
				companyName, projectName, moduleName,tableName));
		class1.setClassName(CreatorUtil.getJavaClassName(tableName) + "SVImpl");
		return class1;
	}

	public static Class getDefActionClass(String companyName,
										  String projectName, String moduleName,String tableName) throws Exception {
		if(StringUtils.isBlank(tableName)) {
			throw new Exception("表名称为不能为空！");
		}

		companyName = StringUtils.isBlank(companyName)?"":"."+(companyName);
		projectName = StringUtils.isBlank(projectName)?"":"."+(projectName);
		moduleName = StringUtils.isBlank(moduleName)?"":"."+(moduleName);

		Class class1 = new Class();
		class1.setSrcFilePath(CreatorUtil.getSrcFilePath(companyName, projectName));
		class1.setClassPackage(CreatorUtil.getActionClassPackage(
				companyName, projectName, moduleName,tableName));
		class1.setClassName(CreatorUtil.getJavaClassName(tableName) + "Action");
		return class1;
	}

	public static Class getDefControllerClass(String companyName,
										  String projectName, String moduleName,String tableName) throws Exception {

		if(StringUtils.isBlank(tableName)) {
			throw new Exception("表名称为不能为空！");
		}

		companyName = StringUtils.isBlank(companyName)?"":"."+(companyName);
		projectName = StringUtils.isBlank(projectName)?"":"."+(projectName);
		moduleName = StringUtils.isBlank(moduleName)?"":"."+(moduleName);

		Class class1 = new Class();
		class1.setSrcFilePath(CreatorUtil.getSrcFilePath(companyName, projectName));
		class1.setClassPackage(CreatorUtil.getActionClassPackage(
				companyName, projectName, moduleName,tableName));
		class1.setClassName(CreatorUtil.getJavaClassName(tableName) + "Controller");
		return class1;
	}


	public static String getJavaVarName(String tableName) {

		String returnName="";

		tableName=tableName.toLowerCase();

		String[] parts=tableName.split("[_]+");
		for (String part : parts) {
			if(!"".equals(part)){
				returnName+=part.substring(0,1).toUpperCase()+part.substring(1);
			}
		}

		return returnName.substring(0,1).toLowerCase()+returnName.substring(1);
	}
}
