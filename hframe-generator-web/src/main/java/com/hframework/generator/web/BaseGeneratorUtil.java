package com.hframework.generator.web;

import com.hframework.beans.class0.Table;
import com.hframework.generator.web.mybatis.MyBatisGeneratorUtil;
import org.mybatis.generator.config.TableConfiguration;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zhangqh6 on 2015/10/18.
 */
public class BaseGeneratorUtil {

    /**
     * 服务生成
     * @param companyName
     * @param projectName
     * @param moduleName
     * @param table
     */
    public static void serviceGenerate(String companyName, String projectName, String moduleName, Table table) throws Exception {
        serviceGenerate(companyName, projectName, moduleName, Arrays.asList(new Table[]{table}));
    }


    /**
     * 服务生成
     * @param companyName
     * @param projectName
     * @param moduleName
     * @param tables
     */
    public static void serviceGenerate(String companyName, String projectName, String moduleName, List<Table> tables) throws Exception {

        if(tables != null && tables.size() > 0) {
            for (Table table : tables) {
                ServiceInterfaceGenerator serviceInterfaceGenerator = new ServiceInterfaceGenerator(companyName, projectName, moduleName, table);
                serviceInterfaceGenerator.create();
                ServiceImplGenerator serviceImplGenerator = new ServiceImplGenerator(companyName, projectName, moduleName, table);
                serviceImplGenerator.create();
            }
        }
    }

    /**
     * Controller生成
     * @param companyName
     * @param projectName
     * @param moduleName
     * @param table
     */
    public static void controllerGenerate(String companyName, String projectName, String moduleName, Table table) throws Exception {
        controllerGenerate(companyName, projectName, moduleName,  Arrays.asList(new Table[]{table}));
    }

    /**
     * Controller生成
     * @param companyName
     * @param projectName
     * @param moduleName
     * @param tables
     */
    public static void controllerGenerate(String companyName, String projectName, String moduleName,  List<Table> tables) throws Exception {
        if(tables != null && tables.size() > 0) {
            for (Table table : tables) {
                ControllerV2Generator controllerGenerator = new ControllerV2Generator(companyName, projectName, moduleName, table);
                controllerGenerator.create();
            }
        }
    }

    public static void generator() throws Exception {
        List<TableConfiguration> tableConfigurations = MyBatisGeneratorUtil.getTableCfg();
        for (TableConfiguration tableConfiguration : tableConfigurations) {
            Table table = new Table();
            table.setTableName(tableConfiguration.getTableName());
            table.setTableDesc(tableConfiguration.getProperty("chineseName"));
            serviceGenerate("", "hframe", "hframe", table);
            controllerGenerate("", "hframe", "hframe", table);
        }
    }


    public static void main(String[] args) throws Exception {
        Table table = new Table();
        table.setTableName("sec_user");
        table.setTableDesc("用户");

        BaseGeneratorUtil.serviceGenerate("zqh","studuent","sec",table);
    }


}
