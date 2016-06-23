package com.hframework.common.helper;

import com.hframework.common.util.ReflectUtils;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

/**
 * Created by zhangquanhong on 2016/6/23.
 */
public class ControllerHelper {

    public static <T> T[] reorderProperty(T[] objects) {
        if(objects != null && objects.length > 0) {
            Class<?> orderSequenceClass = BeanUtils.findPropertyType("pri", objects[0].getClass());
            if(orderSequenceClass == BigDecimal.class) {
                BigDecimal tmpDecimal = BigDecimal.ZERO;
                for (int i = 0; i < objects.length; i++) {
                    BigDecimal pri = (BigDecimal) ReflectUtils.getFieldValue(objects[i], "pri");
                    if (pri == null) {
                        ReflectUtils.setFieldValue(objects[i], "pri", tmpDecimal.add(BigDecimal.ONE));
                    } else if (i > 0) {
                        BigDecimal prevPri = (BigDecimal) ReflectUtils.getFieldValue(objects[i - 1], "pri");
                        if( pri.compareTo(prevPri) <= 0) {
                            ReflectUtils.setFieldValue(objects[i], "pri", tmpDecimal.add(BigDecimal.ONE));
                        }
                    }
                    tmpDecimal = (BigDecimal)ReflectUtils.getFieldValue(objects[i], "pri");
                }
            }
        }
        return null;
    }
}
