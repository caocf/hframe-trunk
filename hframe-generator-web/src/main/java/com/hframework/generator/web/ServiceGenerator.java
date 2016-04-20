package com.hframework.generator.web;


/**
 *
 * @author zqh
 *
 */
@Deprecated
public class ServiceGenerator /* extends AbstractGenerator */{

//	public ServiceGenerator(String companyName, String projectName, String moduleName, Table table) throws Exception {
//		super(companyName, projectName, moduleName, table);
//		setEditClass(serviceImplClass);
//	}
//
//	@Override
//	public void setImportClass() {
//		editClass.addImportClass("java.util.*");
//		editClass.addImportClass("javax.annotation.Resource");
//		editClass.addImportClass("org.springframework.stereotype.Component");
//		editClass.addImportClass("java.io.Serializable");
//		editClass.addImportClass(poClass.getClassPath());
//		editClass.addImportClass(daoClass.getClassPath());
//
//		editClass.setAnnotation("@Component");
//
////		List<CoreTableColumnRelationVo> relationList =  table.getCoreTableColumnRelationVo();
////
////		for (CoreTableColumnRelationVo coreTableColumnRelationVo : relationList) {
////			CreatorContainer relatContainer = CreatorUtil.getCreatorContainer(
////					container.companyName, container.projectName, coreTableColumnRelationVo.getTableNameTo());
////			editClass.addImportClass(relatContainer.Service.getClassPath());
////		}
//	}
//
//	@Override
//	public void setField() {
//		//注入对应的DAO
//		editClass.addField(new Field(daoClass.getClassName()).addGetMethodAnno("@Resource"));
//
//		//注入迭代运算所需要的Service
////		for (CoreTableColumnRelationVo coreTableColumnRelationVo : relationList) {
////			CreatorContainer relatContainer = CreatorUtil.getCreatorContainer(
////					container.companyName, container.projectName, coreTableColumnRelationVo.getTableNameTo());
////			editClass.addField(new Field(relatContainer.Service.getClassName()).addGetMethodAnno("@Resource"));
////		}
//	}
//
//	@Override
//	public void createMethod() {
//		Map contentMap=new HashMap();
//		contentMap.put("ClassName", CreatorUtil.getJavaClassName(table.getTableName()));
//		contentMap.put("VarName", CreatorUtil.getJavaVarName(table.getTableName()));
//		List<Map<String, String>> fklist = getFKMap(table, table.getCoreTableColumnRelationVo());
//		contentMap.put("FKList", fklist);
//		String Hql=getHqlCascade(table,poClass.getClassPath());
//		contentMap.put("HQL", Hql);
//		String CascadeContent=getCascadeContent(table,poClass.getClassPath());
//		contentMap.put("CascadeContent", CascadeContent);
////			contentMap.put("FkClassName", CreatorUtil.getJavaClassName(coreTableColumnRelationVo.getColumnName()));
////			contentMap.put("FkVarName", CreatorUtil.getJavaVarName(coreTableColumnRelationVo.getColumnName()));
//		String methodStr = VelocityUtil.produceTemplateContent("com/hframe/creator/vm/service_content.vm", contentMap);
//
//		editClass.setExtMethodStr(methodStr);
//	}
//
//
//	private static List<Map<String, String>> getFKMap(
//			Table table, List<CoreTableColumnRelationVo> relationList) {
//
//		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
//
//		//获取该table的关系vo对象
//		for (CoreTableColumnRelationVo coreTableColumnRelationVo : relationList) {
//			Map<String, String> inputParam=new HashMap<String, String>();
//			inputParam.put("FkClassName", CreatorUtil.getJavaClassName(coreTableColumnRelationVo.getColumnName()));
//			inputParam.put("FkVarName", CreatorUtil.getJavaVarName(coreTableColumnRelationVo.getColumnName()));
//			inputParam.put("Sign","int".equals(coreTableColumnRelationVo.getColumnType()) ? "":"'");
//			inputParam.put("FKType",String.valueOf(coreTableColumnRelationVo.getType()));
//			list.add(inputParam);
//		}
//
//
//		return list;
//	}
//
//	private static String getFKParamQueryList(
//			List<CoreTableColumnRelationVo> relationList) {
//
//		String s="";
//
//
//		for (int i = 0; i < relationList.size(); i++) {
//
//			if(2==relationList.get(i).getType()) continue;
//			s+=CreatorUtil.getJavaVarName(relationList.get(i).getTableNameTo());
//			s+=",";
//		}
//		return s.length()>0?s.substring(0,s.length()-1):s;
//	}
//
//	private static String getHqlCascade(Table table, String poPath) {
//
//		List<CoreTableColumnRelationVo> relationList= table.getCoreTableColumnRelationVo();
//		String str="select new "+poPath+"("+CreatorUtil.getJavaVarName(table.getTableName())+(getFKParamQueryList(relationList)==""?"":(","+getFKParamQueryList(relationList)))+")";
//
//		String headStr=" from "+CreatorUtil.getJavaClassName(table.getTableName())+" "+CreatorUtil.getJavaVarName(table.getTableName())+(CreatorUtil.getFKParamList(relationList)==""?"":(", "+CreatorUtil.getFKParamList(relationList)));
//
//		String endStr=" where 1=1 ";
//
//		for (CoreTableColumnRelationVo coreTableColumnRelationVo : relationList) {
//			if(2!=coreTableColumnRelationVo.getType()){
//				endStr+="and "+CreatorUtil.getJavaVarName(table.getTableName())+"."+CreatorUtil.getJavaVarName(coreTableColumnRelationVo.getColumnName())+"="+CreatorUtil.getJavaVarName(coreTableColumnRelationVo.getTableNameTo())+"."+CreatorUtil.getJavaVarName(coreTableColumnRelationVo.getColumnNameTo());
//			}
//		}
//
//		return str+headStr+endStr+" ";
//	}
//
//	private static String getCascadeContent(Table table, String poPath) {
//
//		String str="";
//
//		//获取该table的关系vo对象
//		List<CoreTableColumnRelationVo> relationList= table.getCoreTableColumnRelationVo();
//
//		for (CoreTableColumnRelationVo coreTableColumnRelationVo : relationList) {
//			if(2==coreTableColumnRelationVo.getType()){
//				str+=CreatorUtil.getJavaVarName(table.getTableName())+".set"+CreatorUtil.getJavaClassName(coreTableColumnRelationVo.getTableNameTo())+"List("+
//						CreatorUtil.getJavaVarName(coreTableColumnRelationVo.getTableNameTo())+"Serv.get"+CreatorUtil.getJavaClassName(coreTableColumnRelationVo.getTableNameTo())+
//						"By"+CreatorUtil.getJavaClassName(coreTableColumnRelationVo.getColumnNameTo())+"("+CreatorUtil.getJavaVarName(table.getTableName())+".get"+
//						CreatorUtil.getJavaClassName(coreTableColumnRelationVo.getColumnName())+"(),cascadeLevel-1));\n\n";
//			}
//		}
//
//		return str;
//	}
}
