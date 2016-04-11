package com.hframe.generator;

import com.hframe.dao.HfpmProgramMapper;
import com.hframe.domain.model.HfpmProgram_Example;
import com.hframe.generator.base.BaseGeneratorUtil;
import com.hframe.generator.bean.Table;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by zhangqh6 on 2015/9/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-config.xml")
public class CommonGeneratorTest {


    @Test
    public void serviceGenerate() throws Exception {
        Table table = new Table();
        table.setTableName("sec_user");
        table.setTableDesc("用户");

        BaseGeneratorUtil.serviceGenerate("", "hframe", "hframe", table);
    }

    @Test
    public void controllerGenerate() throws Exception {
        Table table = new Table();
        table.setTableName("sec_user");
        table.setTableDesc("用户");

        BaseGeneratorUtil.controllerGenerate("", "hframe", "hframe", table);
    }

    @Test
    public void generator() throws Exception {
        BaseGeneratorUtil.generator();
    }
}
