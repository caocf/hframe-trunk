package com.hframework.base.bean;

import com.google.common.base.Enums;
import com.hframework.common.annotation.extension.*;
import com.hframework.common.util.StringUtils;
import org.springframework.beans.factory.InitializingBean;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by zhangquanhong on 2016/9/22.
 */
public abstract class AbstractBusinessHandler<T> implements InitializingBean,BusinessHandler<T>  {

    public void afterPropertiesSet() throws Exception {
        Type genType = this.getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        Class<T> entryClass = (Class<T>) params[0];

        Class handlerClass = this.getClass();
        Method[] declaredMethods = handlerClass.getDeclaredMethods();
        for (Method method : declaredMethods) {
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof AfterCreateHandler
                        || annotation instanceof AfterDeleteHandler
                        || annotation instanceof AfterUpdateHandler
                        || annotation instanceof BeforeCreateHandler
                        || annotation instanceof BeforeDeleteHandler
                        || annotation instanceof BeforeUpdateHandler
                        ) {
                    BusinessHandlerFactory.addHandler(entryClass, annotation, method);
                }
            }
        }

        System.out.println(1);
    }

    public boolean afterCreate(T t) {
        return false;
    }

    public boolean afterUpdate(T t, T ot) {
        return false;
    }

    public boolean afterDelete(T t) {
        return false;
    }
}
