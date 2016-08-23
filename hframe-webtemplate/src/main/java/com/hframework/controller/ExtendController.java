package com.hframework.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.hframe.domain.model.HfmdEntity;
import com.hframe.domain.model.HfmdEntity_Example;
import com.hframe.domain.model.HfsecMenu;
import com.hframe.domain.model.HfsecMenu_Example;
import com.hframe.service.interfaces.IHfmdEntitySV;
import com.hframe.service.interfaces.IHfpmPageSV;
import com.hframe.service.interfaces.IHfsecMenuSV;
import com.hframework.base.service.CommonDataService;
import com.hframework.base.service.DataSetLoaderService;
import com.hframework.base.service.ModelLoaderService;
import com.hframework.beans.controller.ResultCode;
import com.hframework.beans.controller.ResultData;
import com.hframework.common.ext.CollectionUtils;
import com.hframework.common.ext.Grouper;
import com.hframework.common.ext.Mapper;
import com.hframework.common.frame.cache.PropertyConfigurerUtils;
import com.hframework.common.util.FileUtils;
import com.hframework.common.util.StringUtils;
import com.hframework.common.util.message.XmlUtils;
import com.hframework.generator.util.CreatorUtil;
import com.hframework.generator.web.BaseGeneratorUtil;
import com.hframework.generator.web.bean.HfClassContainer;
import com.hframework.generator.web.bean.HfClassContainerUtil;
import com.hframework.generator.web.bean.HfModelContainer;
import com.hframework.generator.web.bean.HfModelContainerUtil;
import com.hframework.generator.web.mybatis.MyBatisGeneratorUtil;
import com.hframework.generator.web.sql.SqlGeneratorUtil;
import com.hframework.generator.web.sql.reverse.SQLParseUtil;
import com.hframework.web.bean.WebContext;
import com.hframework.web.config.bean.Module;
import com.hframework.web.config.bean.dataset.Entity;
import com.hframework.web.config.bean.module.Component;
import com.hframework.web.config.bean.module.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

/**
 * User: zhangqh6
 * Date: 2016/5/11 0:16:16
 */
@Controller
@RequestMapping(value = "/extend")
public class ExtendController {
    private static final Logger logger = LoggerFactory.getLogger(ExtendController.class);

    @Resource
    private ModelLoaderService modelLoaderService;
    @Resource
    private CommonDataService commonDataService;

    @Resource
    private IHfsecMenuSV iHfsecMenuSV;

    @Resource
    private IHfmdEntitySV iHfmdEntitySV;

    public enum EntityPageSet{

        SINGLE_MGR("_mgr","管理","qlist",new String[]{"qForm"}),
        SINGLE_CREATE("_create","创建","create",new String[]{}),
        SINGLE_EDIT("_edit","修改","edit",new String[]{}),
        SINGLE_DETAIL("_detail","明细","edit",new String[]{}),

        COMPLEX_MGR("_mgr","管理","qlist",new String[]{"qForm"}),
        COMPLEX_CREATE("_create","添加","cComb",new String[]{"cForm","cList"}),
        COMPLEX_EDIT("_edit","修改","eComb",new String[]{"eForm","eList"}),
        COMPLEX_DETAIL("_detail","查看","dComb",new String[]{"dForm","qList"});


        String id;
         String name;
         String pageTemplate;
         String[] component;

        EntityPageSet(String id, String name, String pageTemplate, String[] component) {
            this.id = id;
            this.name = name;
            this.pageTemplate = pageTemplate;
            this.component = component;
        }
    }

    /**
     * 重新加载（全量）
     * @return
     */
    @RequestMapping(value = "/model_diff.json")
    @ResponseBody
    public ResultData getModelDiff(HttpServletRequest request){
        logger.debug("request : {}");
        try{
            String programCode = "hframe";
            String programeName = "框架";
            String moduleCode = "hframe";
            String moduleName = "框架";
            String  configSqlPath= modelLoaderService.load(null);

            HfModelContainer targetModelContainer = SQLParseUtil.parseModelContainerFromSQLFile(
                    configSqlPath, programCode, programeName, moduleCode, moduleName);

            String dbSqlPath = SqlGeneratorUtil.createSqlFile("hframework", "hframe");
            HfModelContainer curDbModelContainer = SQLParseUtil.parseModelContainerFromSQLFile(
                    dbSqlPath, programCode, programeName, moduleCode, moduleName);

            final HfModelContainer[] resultModelContainers =
                    HfModelContainerUtil.mergerModelContainer(curDbModelContainer, targetModelContainer);
            final List<String> result = HfModelContainerUtil.getSql(resultModelContainers[0], resultModelContainers[1]);
            final List<Map<String, String>> sqls = new ArrayList<Map<String, String>>();
            for (final String sql : result) {
                sqls.add(new HashMap<String, String>(){{
                        put("sql",sql);
                }});
            }

            return ResultData.success(new HashMap<String,Object>(){{
                put("NewEntityId", new HashMap<String, Object>() {{
                    put("list", Lists.newArrayList(resultModelContainers[0].getEntityMap().values()));
                }});
                put("NewEntityAttrId", new HashMap<String, Object>() {{
                    put("list", Lists.newArrayList(resultModelContainers[0].getEntityAttrMap().values()));
                }});
                put("ModEntityId", new HashMap<String, Object>() {{
                    put("list", Lists.newArrayList(resultModelContainers[1].getEntityMap().values()));
                }});
                put("ModEntityAttrId", new HashMap<String, Object>() {{
                    put("list", Lists.newArrayList(resultModelContainers[1].getEntityAttrMap().values()));
                }});
                put("sql", new HashMap<String, Object>() {{
                    put("list", sqls);
                }});
            }});
        }catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
    }

    /**
     * menuChart初始化处理
     * @return
     */
    @RequestMapping(value = "/menu_chart.json")
    @ResponseBody
    public ResultData getMenuChart(HttpServletRequest request){
        logger.debug("request : {}");
        try{
            String programCode = "hframe";
            String programeName = "框架";
            String moduleCode = "hframe";
            String moduleName = "框架";

            return ResultData.success(new HashMap<String,Object>(){{
            }});
        }catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
    }



    /**
     * 重新加载（全量）
     * @return
     */
    @RequestMapping(value = "/model_execute.json")
    @ResponseBody
    public ResultData modelExecute(@RequestParam(value="checkIds[]",required=false) List<String> sqls){
        logger.debug("request : {}", sqls);
        try{
            commonDataService.executeDBStructChange(sqls);
            return ResultData.success();
        }catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
    }

    /**
     * 重新加载（全量）
     * @return
     */
    @RequestMapping(value = "/code_generate.json")
    @ResponseBody
    public ResultData codeGenerate(@RequestParam(value="checkIds[]",required=false) Set<String> entityCodes){
        logger.debug("request : {}", entityCodes);
        try{
            String sql = SqlGeneratorUtil.getSqlContent(entityCodes);
            final HfModelContainer hfModelContainer = SQLParseUtil.parseModelContainerFromSQL(sql, null, null, null, null);

            List<Map<String, String>> tables = new ArrayList<Map<String, String>>();
            for (final String tableName : entityCodes) {
                tables.add(new HashMap<String, String>() {{
                    put("tableName", tableName);
                    put("tableDesc", hfModelContainer.getEntity(tableName).getHfmdEntityName());
                    put("generatedKey", tableName + "_id");
                }});
            }

            String mybatisConfigFilePath = BaseGeneratorUtil.generateMybatisConfig(tables, CreatorUtil.projectBasePath + "/hframe-core");
            MyBatisGeneratorUtil.generate(new File(mybatisConfigFilePath));
            BaseGeneratorUtil.generator(mybatisConfigFilePath);

            return ResultData.success();
        }catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
    }


    /**
     * 页面生成
     * @return
     */
    @RequestMapping(value = "/page_generate.json")
    @ResponseBody
    public ResultData pageGenerate(@RequestParam(value="dataIds[]",required=false) List<String> entityCodes, String moduleCode){
        logger.debug("request : {}", entityCodes, moduleCode);
        try{

            HfmdEntity_Example example = new HfmdEntity_Example();
            example.createCriteria().andHfmdEntityCodeLike("hf%");
            final List<HfmdEntity> hfmdEntityListByExample = iHfmdEntitySV.getHfmdEntityListByExample(example);

            Map<String, HfmdEntity> cache = CollectionUtils.convert(hfmdEntityListByExample, new Mapper<String, HfmdEntity>() {
                public <K> K getKey(HfmdEntity hfmdEntity) {
                    return (K) hfmdEntity.getHfmdEntityCode();
                }
            });

            Module module = new Module();
            module.setCode(moduleCode);
            List<Page> pageList = new ArrayList<Page>();
            module.setPageList(pageList);

            List<Page> menuList = new ArrayList<Page>();
            for (String entityCode : entityCodes) {
                if(StringUtils.isBlank(entityCode)) {
                    continue;
                }
                String[] lineEntityCodes = entityCode.substring(0,entityCode.length()-1).split(",");
                String rootEntityCode = lineEntityCodes[0];
                String rootEntityName = cache.get(rootEntityCode).getHfmdEntityName();

                String[] relEntityCodes = Arrays.copyOfRange(lineEntityCodes, 1, lineEntityCodes.length);

                List<Page> pages = getPages(moduleCode, rootEntityCode, rootEntityName, relEntityCodes);
                menuList.add(pages.get(0));
                pageList.addAll(pages);
            }

            String xml = XmlUtils.writeValueAsString(module);
            String pageFilePath =  PropertyConfigurerUtils.getProperty(DataSetLoaderService.CreatorConst.PROJECT_BASE_FILE_PATH) +
                    "/hframe-webtemplate/src/main/resources/program/hframe/module/" + moduleCode + ".xml";
            System.out.println(pageFilePath);
            System.out.println(xml);
            FileUtils.writeFile(pageFilePath, xml);

            List<HfsecMenu> hfsecMenuList = iHfsecMenuSV.getHfsecMenuListByExample(new HfsecMenu_Example());
            Iterator<Page> iterator = menuList.iterator();
            while(iterator.hasNext()) {
                Page page = iterator.next();
                for (HfsecMenu hfsecMenu : hfsecMenuList) {
                    if(page.getId().equals(hfsecMenu.getHfsecMenuCode())) {
                        iterator.remove();
                        break;
                    }
                }
            }

            for (Page page : menuList) {
                HfsecMenu hfsecMenu = new HfsecMenu();
                hfsecMenu.setHfsecMenuCode(page.getId());
                hfsecMenu.setHfsecMenuName(page.getName());
                hfsecMenu.setHfsecMenuDesc(page.getName());
                hfsecMenu.setMenuLevel(1);
                hfsecMenu.setParentHfsecMenuId(-2L);
                hfsecMenu.setIcon("");
                hfsecMenu.setUrl("/" + moduleCode + "/" + page.getId() + ".html");
                iHfsecMenuSV.create(hfsecMenu);
            }



//            String sql = SqlGeneratorUtil.getSqlContent(entityCodes);
//            final HfModelContainer hfModelContainer = SQLParseUtil.parseModelContainerFromSQL(sql, null, null, null, null);
//
//            List<Map<String, String>> tables = new ArrayList<Map<String, String>>();
//            for (final String tableName : entityCodes) {
//                tables.add(new HashMap<String, String>() {{
//                    put("tableName", tableName);
//                    put("tableDesc", hfModelContainer.getEntity(tableName).getHfmdEntityName());
//                    put("generatedKey", tableName + "_id");
//                }});
//            }
//
//            String mybatisConfigFilePath = BaseGeneratorUtil.generateMybatisConfig(tables, CreatorUtil.projectBasePath + "/hframe-core");
//            MyBatisGeneratorUtil.generate(new File(mybatisConfigFilePath));
//            BaseGeneratorUtil.generator(mybatisConfigFilePath);

            return ResultData.success();
        }catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
    }

    private List<Page> getPages(String moduleCode, String rootEntityCode, String rootEntityName, String[] relEntityCodes) {

        List<Page> pages = new ArrayList<Page>();


        if(relEntityCodes == null || relEntityCodes.length == 0) {
            pages.add(getPage(EntityPageSet.SINGLE_MGR, moduleCode, rootEntityCode, rootEntityName,null));
            pages.add(getPage(EntityPageSet.SINGLE_CREATE, moduleCode, rootEntityCode, rootEntityName,null));
            pages.add(getPage(EntityPageSet.SINGLE_EDIT, moduleCode, rootEntityCode, rootEntityName,null));
            pages.add(getPage(EntityPageSet.SINGLE_DETAIL, moduleCode, rootEntityCode, rootEntityName,null));

        }else {
            pages.add(getPage(EntityPageSet.COMPLEX_MGR, moduleCode, rootEntityCode, rootEntityName,relEntityCodes));
            pages.add(getPage(EntityPageSet.COMPLEX_CREATE, moduleCode, rootEntityCode, rootEntityName, relEntityCodes));
            pages.add(getPage(EntityPageSet.COMPLEX_EDIT, moduleCode, rootEntityCode, rootEntityName, relEntityCodes));
            pages.add(getPage(EntityPageSet.COMPLEX_DETAIL, moduleCode, rootEntityCode, rootEntityName, relEntityCodes));
        }

        return pages;
    }

    private Page getPage(EntityPageSet entityPage, String moduleCode, String rootEntityCode, String rootEntityName, String[] relEntityCodes) {
        Page page = new Page();

        page.setId(rootEntityCode + entityPage.id);
        page.setName(rootEntityName + entityPage.name);
        page.setPageTemplate(entityPage.pageTemplate);
        page.setDataSet(moduleCode + "/" + rootEntityCode);
        page.setComponentList(new ArrayList<Component>());
        String[] components = entityPage.component;
        for (String componentId : components) {
            Component component = new Component();
            component.setId(componentId);
            if(componentId.equals("qForm")) {
                component.setDataSet(moduleCode + "/" + rootEntityCode + "_DS4Q");
            }else if(componentId.equals("cList") && relEntityCodes != null) {
                component.setDataSet(moduleCode + "/" + relEntityCodes[0]);
            }else if(componentId.equals("eList") && relEntityCodes != null) {
                component.setDataSet(moduleCode + "/" + relEntityCodes[0]);
            }else if(componentId.equals("qList") && relEntityCodes != null) {
                component.setDataSet(moduleCode + "/" + relEntityCodes[0]);
            }else {
                component.setDataSet(moduleCode + "/" + rootEntityCode);
            }
            page.getComponentList().add(component);
        }

        return page;
    }


    /**
     * 代码差异比对
     * @return
     */
    @RequestMapping(value = "/code_diff.json")
    @ResponseBody
    public ResultData getCodeDiff(HttpServletRequest request){
        logger.debug("request : {}");
        try{
            String programCode = "hframe";
            String programName = "框架";
            String moduleCode = "hframe";
            String moduleName = "框架";
            String dbSqlPath = SqlGeneratorUtil.createSqlFile("hframework", "hframe");
            HfModelContainer targetFileModelContainer = SQLParseUtil.parseModelContainerFromSQLFile(
                    dbSqlPath, programCode, programName, moduleCode, moduleName);
            HfClassContainer targetClassContainer = HfClassContainerUtil.getClassInfoContainer(targetFileModelContainer);

            String filePath = "/D:/my_workspace/hframe-trunk" + "\\hframe-core\\target\\classes\\";
            String classPackage = "com.hframe.domain.model.";
            HfClassContainer originClassContainer = HfClassContainerUtil.fromClassPath(filePath, classPackage, programCode, programName);

            final List<Map<String, String>>[] result = HfClassContainerUtil.compare(originClassContainer, targetClassContainer);

            return ResultData.success(new HashMap<String,Object>(){{
                put("AddClassInfo", new HashMap<String, Object>() {{
                    put("list", result[0]);
                }});
                put("ModClassInfo", new HashMap<String, Object>() {{
                    put("list", result[1]);
                }});
                put("DelClassInfo", new HashMap<String, Object>() {{
                    put("list", result[2]);
                }});
            }});
        }catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
    }

    /**
     * 代码差异比对
     * @return
     */
    @RequestMapping(value = "/page_load.json")
    @ResponseBody
    public ResultData getPageLoad(HttpServletRequest request){
        logger.debug("request : {}");
        try{
            String programCode = "hframe";
            String programName = "框架";



            HfmdEntity_Example example = new HfmdEntity_Example();
            example.createCriteria().andHfmdEntityCodeLike("hf%");
            final List<HfmdEntity> hfmdEntityListByExample = iHfmdEntitySV.getHfmdEntityListByExample(example);

            Map<Module, List<List<Entity>>> entityRelats = WebContext.get().getEntityRelats();
            final Map<Module, List<List<HfmdEntity>>> todoList = new HashMap<Module, List<List<HfmdEntity>>>();
            for (Module module : entityRelats.keySet()) {
                todoList.put(module, new ArrayList<List<HfmdEntity>>());
                List<List<HfmdEntity>> targetList = todoList.get(module);
                List<List<Entity>> originList = entityRelats.get(module);
                for (List<Entity> entities : originList) {
                    List<HfmdEntity> target = new ArrayList<HfmdEntity>();
                    for (Entity entity : entities) {
                        if(StringUtils.isNotBlank(entity.getText())) {
                            target.add(getHfmdEntity(hfmdEntityListByExample, entity));
                        }
                    }
                    targetList.add(target);
                }
            }


            System.out.println("==>" + JSON.toJSONString(entityRelats));
            return ResultData.success(new HashMap<String,Object>(){{
                put("TodoList", hfmdEntityListByExample);
                put("DownList", todoList);
            }});
        }catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
    }

    private HfmdEntity getHfmdEntity(List<HfmdEntity> hfmdEntityListByExample, Entity entity) {
        Iterator<HfmdEntity> iterator = hfmdEntityListByExample.iterator();
        while (iterator.hasNext()) {
            HfmdEntity hfmdEntity = iterator.next();
            if(entity.getText().equals(hfmdEntity.getHfmdEntityCode())) {
                iterator.remove();
                return hfmdEntity;
            }
        }
        return null;
    }

}
