package com.hframework.generator.web.mybatis;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.TableConfiguration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangqh6 on 2015/9/4.
 */
public class MyBatisGeneratorUtil {

    public static void main(String[] args) throws IOException, XMLParserException, InvalidConfigurationException, InterruptedException, SQLException {
        generate();
    }

    public static  void generate() throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
       generate("mybatis-generator-config-lcs.xml");
    }

    public static  List<TableConfiguration> getTableCfg() throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
        return getTableCfg("mybatis-generator-config.xml");
    }

    public static  List<TableConfiguration> getTableCfg(String cfgFileName) throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        String rootClassPath = Thread.currentThread().getContextClassLoader ().getResource("").getPath();
        System.out.println(rootClassPath);
        File configFile = new File(rootClassPath + cfgFileName);
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        List<TableConfiguration> tableConfigurations = config.getContexts().get(0).getTableConfigurations();
//        for (TableConfiguration tableConfiguration : tableConfigurations) {
//            System.out.println(tableConfiguration.getTableName() + "：" + tableConfiguration.getProperty("chineseName"));
//        }
       return tableConfigurations;
    }

    public static  void generate(String cfgFileName) throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        String rootClassPath = Thread.currentThread().getContextClassLoader ().getResource("").getPath();
        System.out.println(rootClassPath);
        File configFile = new File(rootClassPath + cfgFileName);
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }

}
