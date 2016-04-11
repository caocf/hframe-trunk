package com.hframe.generator.base;

import java.util.List;

import com.hframe.common.util.FileUtils;
import com.hframe.generator.bean.Table;
import com.hframe.generator.util.CreatorUtil;


/**
 *
 * @author zqh
 *
 */
public class JspCreator {

	public static final String projectBasePath= CreatorUtil.projectBasePath;
	public static final String projectTomcatBasePath=CreatorUtil.projectTomcatBasePath;

	public static void createPageFile(String username, String pageName,
									  String content) {

		if("".equals(username)){
			username="zqh";
		}

		//生成sql将要保存的路径包
		String filePath=projectBasePath+"WebRoot/"+pageName;
		FileUtils.writeFile(filePath, content);

		filePath=projectTomcatBasePath+pageName;
		FileUtils.writeFile(filePath, content);
	}

	public static String createJspFile(String string, String dbName,
									   List<Table> tableList) {

		return null;
	}

//	public static String createCombineJspFile(String string, String dbName,
//											  List<CoreSet> tableList, CoreTableColumnServ coreTableColumnServ) {
//
//		return null;
//	}
}