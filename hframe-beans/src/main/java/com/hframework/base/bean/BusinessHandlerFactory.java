package com.hframework.base.bean;

import com.google.common.base.Joiner;
import com.hframework.common.annotation.extension.AfterCreateHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangquanhong on 2016/9/22.
 */
public class BusinessHandlerFactory {
//    private static Map<String, BusinessHandler> handlers = new HashMap<String, BusinessHandler>();
    private static Map<String, Map<Annotation, Method>> handlers = new HashMap<String, Map<Annotation, Method>>();

    /**
     * 添加handler
     * @param entryClass
     * @param eventType
     * @param handlerMethod
     * @param <T>
     * @param <V>
     */
    public static <T, V extends Annotation> void addHandler(Class<T> entryClass, V eventType, Method handlerMethod) {

        String key = entryClass.getSimpleName()+ "|" + eventType.annotationType().getSimpleName();

        if(!handlers.containsKey(key)) handlers.put(key, new HashMap<Annotation, Method>());
        handlers.get(key).put(eventType, handlerMethod);
    }

    /**
     * 获取handler
     * @param entryClass
     * @param eventTypeClass
     * @param <T>
     * @return
     */
    public static <T, V extends Annotation> Map<V, Method> getHandler(Class<T> entryClass, Class<V> eventTypeClass) {
        String key = entryClass.getSimpleName()+ "|" + eventTypeClass.getSimpleName();
        return handlers.get(key) == null ? new HashMap<V, Method>() : (Map<V, Method>) handlers.get(key);
    }


}
