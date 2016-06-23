import org.apache.commons.httpclient.util.DateUtil;

import java.util.Date;

/**
 * Created by zhangquanhong on 2016/6/22.
 */
public class Test {
    public static void main(String[] args) {
        Date date = new Date();
        DateUtil.formatDate(date,"yyyy");
        System.out.println(date + DateUtil.formatDate(date,""));
    }
}
