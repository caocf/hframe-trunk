import com.hframework.base.service.CommonDataService;
import com.hframework.generator.web.bean.HfModelContainerUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
    public void selectDynamicTableDataSome() throws Exception {
        List list = commonDataService.selectDynamicTableDataSome(new HashMap<String, String>() {{
            put("sql","SELECT hfmd_enum_id,hfmd_enum_value,hfmd_enum_desc,is_default,pri,ext1,ext2,hfmd_enum_class_id,hfmd_enum_class_code,op_id,create_time,del_flag FROM hfmd_enum t WHERE t.hfpm_program_id IN (151031375397) OR hfpm_program_id IS NULL");
        }});

        List<String> hfmd_enum = HfModelContainerUtil.getSql(list, "hfmd_enum", true);
        for (String s : hfmd_enum) {
            System.out.println("sql = > " + s);
        }

    }

    @Test
    public void showCreateTable() throws Exception {
        System.out.println("==>" + commonDataService.showCreateTableSql("test"));
    }

}
