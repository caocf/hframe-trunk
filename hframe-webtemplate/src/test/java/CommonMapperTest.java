
import com.google.common.collect.Maps;
import com.hframework.base.service.CommonDataService;
import javafx.util.Pair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by zhangqh6 on 2015/9/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-config.xml")
public class CommonMapperTest {

    @Resource
    private CommonDataService commonDataService;

    @Test
    public void doCreate() throws Exception {
        commonDataService.executeDBStructChange(new HashMap(){{
            put("sql","create table test(id varchar(32), name varchar(32))");
        }});
    }

    @Test
    public void doAlter() throws Exception {
        commonDataService.executeDBStructChange("alter table test add column desc1 varchar(128);");
    }

    @Test
    public void showTables() throws Exception {
        System.out.println(Arrays.toString(commonDataService.showTables().toArray(new String[0])));
    }

    @Test
    public void showCreateTable() throws Exception {
        System.out.println("==>" + commonDataService.showCreateTableSql("test"));
    }

}
