package com.hframework.interceptor;

import com.hframe.domain.model.*;
import com.hframe.service.interfaces.IHfmdEntityAttrSV;
import com.hframe.service.interfaces.IHfmdEntitySV;
import com.hframe.service.interfaces.IHfmdEnumClassSV;
import com.hframe.service.interfaces.IHfpmModuleSV;
import com.hframework.base.bean.BusinessHandlerFactory;
import com.hframework.common.annotation.extension.*;
import com.hframework.common.frame.ServiceFactory;
import com.hframework.common.frame.cache.PropertyConfigurerUtils;
import com.hframework.common.util.ReflectUtils;
import com.hframework.common.util.StringUtils;
import com.hframework.generator.util.CreatorUtil;
import com.hframework.generator.web.bean.HfModelContainer;
import com.hframework.generator.web.bean.HfModelContainerUtil;
import com.hframework.generator.web.sql.HfModelService;
import com.hframework.generator.web.sql.reverse.SQLParseUtil;
import com.hframework.web.bean.WebContextHelper;
import org.apache.commons.beanutils.BeanUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据集连带规则拦截器
 * Created by zhangquanhong on 2016/8/28.
 */
@Component
@Aspect
public class BusinessHandlerInterceptor {

    private static final long ENUM_CLASS_DEFAULT_HOLDER = 2;

    @Pointcut("execution(* com.hframe.service.impl.*.create(..))")
    private void createMethod(){ }

    @Pointcut("execution(int com.hframe.service.impl.*.update*(..))")
    private void updateMethod(){ }

    @Pointcut("execution(int com.hframe.service.impl.*.batchOperate*(..))")
    private void batchOperateMethod(){ }

    @Pointcut("execution(int com.hframe.service.impl.*.delete*(..))")
    private void deleteMethod(){ }

    @Before(value = "createMethod()")
    public void createBefore(JoinPoint joinPoint) throws Exception {
        Object targetObject = joinPoint.getArgs()[0];
        Map<BeforeCreateHandler, Method> handlers = BusinessHandlerFactory.getHandler(targetObject.getClass(), BeforeCreateHandler.class);
        for (Method method : handlers.values()) {
            invokeHandler(method, targetObject);
        }
    }

    @AfterReturning(pointcut = "createMethod()", returning = "retVal")
    public void createAfter(JoinPoint joinPoint, int retVal) throws Exception {
        if(retVal > 0) {
            Object targetObject = joinPoint.getArgs()[0];
            Map<AfterCreateHandler, Method> handlers = BusinessHandlerFactory.getHandler(targetObject.getClass(), AfterCreateHandler.class);
            for (Method method : handlers.values()) {
                invokeHandler(method, targetObject);
            }
        }
    }


    @Before(value = "updateMethod()")
    public void updateBefore(JoinPoint joinPoint) throws Exception {
        if(joinPoint.getArgs().length == 1) {//update方法
            Object targetObject = joinPoint.getArgs()[0];
            Class curServiceClass = joinPoint.getSourceLocation().getWithinType();
            Map<BeforeUpdateHandler, Method> handlers = BusinessHandlerFactory.getHandler(targetObject.getClass(), BeforeUpdateHandler.class);
            for (BeforeUpdateHandler annotation : handlers.keySet()) {
                Method method = handlers.get(annotation);
                checkAndInvokeHandler(targetObject, annotation.attr(), annotation.orig(), annotation.target(), method, curServiceClass);
            }

        }/*else {//updateByExample方法
            Object targetObject = joinPoint.getArgs()[0];
        }*/
    }

    @AfterReturning(value = "updateMethod()")
    public void updateAfter(JoinPoint joinPoint) throws Exception {
        if(joinPoint.getArgs().length == 1) {//update方法
            Object targetObject = joinPoint.getArgs()[0];
            Class curServiceClass = joinPoint.getSourceLocation().getWithinType();
            Map<AfterUpdateHandler, Method> handlers = BusinessHandlerFactory.getHandler(targetObject.getClass(), AfterUpdateHandler.class);
            for (AfterUpdateHandler annotation : handlers.keySet()) {
                Method method = handlers.get(annotation);
                checkAndInvokeHandler(targetObject, annotation.attr(), annotation.orig(), annotation.target(), method, curServiceClass);
            }

        }/*else {//updateByExample方法
            Object targetObject = joinPoint.getArgs()[0];
        }*/
    }

    @Before(value = "deleteMethod()")
    public void deleteBefore(JoinPoint joinPoint) throws Exception {
        Object targetObject = joinPoint.getArgs()[0];
        if (targetObject instanceof Long) {
        }else {
            Map<BeforeDeleteHandler, Method> handlers = BusinessHandlerFactory.getHandler(targetObject.getClass(), BeforeDeleteHandler.class);
            for (Method method : handlers.values()) {
                invokeHandler(method, targetObject);
            }
        }
    }

    @AfterReturning(pointcut = "deleteMethod()", returning = "retVal")
    public void deleteAfter(JoinPoint joinPoint, int retVal) throws Exception {
        Object targetObject = joinPoint.getArgs()[0];
        if (targetObject instanceof Long) {
        }else {
            Map<AfterDeleteHandler, Method> handlers = BusinessHandlerFactory.getHandler(targetObject.getClass(), AfterDeleteHandler.class);
            for (Method method : handlers.values()) {
                invokeHandler(method, targetObject);
            }
        }
    }

    private void checkAndInvokeHandler(Object targetObject, String attr, String orig, String target,
                                       Method method, Class curServiceClass) throws Exception {
        if(StringUtils.isNotBlank(attr)) {
            String propertyName = attr.trim();
            String targetPropertyValue = BeanUtils.getProperty(targetObject, propertyName);
            if(StringUtils.isNotBlank(target) && !target.trim().equals(targetPropertyValue)) {
                return;
            }
            if(StringUtils.isNotBlank(orig)) {
                Object originObject = getOriginObject(targetObject, curServiceClass);
                String originPropertyValue = BeanUtils.getProperty(originObject, propertyName);
                if(!orig.trim().equals(originPropertyValue)) {
                    return;
                }else {
                    invokeHandler(method, targetObject, originObject);
                }
            }else {
                invokeHandler(method, targetObject);
            }

        }else {
            invokeHandler(method, targetObject);
        }
    }



    private void invokeHandler(Method method, Object... targetObject) throws Exception {
        Object handler = ServiceFactory.getService(method.getDeclaringClass());
        if(targetObject == null) targetObject = new Object[0];

        try {

            Object[] args = new Object[method.getParameterTypes().length];
            for (int i = 0; i < targetObject.length; i++) {
                args[i] = targetObject[i];
            }
            method.invoke(handler, args);
        } catch (Exception e) {
            throw e;
        }
    }

    private Object getOriginObject(Object targetObject, Class curServiceClass) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        Object service = ServiceFactory.getService(curServiceClass);
        String keyPropertyGetMethod = "get" + targetObject.getClass().getSimpleName() + "Id";
        String keyPropertyValue = String.valueOf(ReflectUtils.invokeMethod(targetObject, keyPropertyGetMethod, new Class[0], new Object[0]));

        String methodName = "get" + targetObject.getClass().getSimpleName() + "ByPK";

        return ReflectUtils.invokeMethod(service,methodName, new Class[]{long.class}, new Object[]{Long.valueOf(keyPropertyValue)});
    }
}
