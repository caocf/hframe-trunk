package com.hframework.generator.thirdplatform.bean;

import com.hframe.common.annotation.Key;
import com.hframe.common.annotation.Source;
import com.hframe.common.util.ResourceWrapper;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by zqh on 2016/4/16.
 */

@Source("thirdplatform.properties" )
public class GeneratorConfig{

    @Key( "java.generate.root.path")
    private String javaRootPath;

    @Key( "resource.generate.root.path")
    private String resourceRootPath;

    private String resourceKeyPrefix;

    public String getResourceKeyPrefix() {
        return resourceKeyPrefix;
    }

    public void setResourceKeyPrefix(String resourceKeyPrefix) {
        this.resourceKeyPrefix = resourceKeyPrefix;
    }

    public String getResourceRootPath() {
        return resourceRootPath;
    }

    public void setResourceRootPath(String resourceRootPath) {
        this.resourceRootPath = resourceRootPath;
    }

    public String getJavaRootPath() {
        return javaRootPath;
    }

    public void setJavaRootPath(String javaRootPath) {
        this.javaRootPath = javaRootPath;
    }

    private static GeneratorConfig instance;

    private GeneratorConfig() {
        super();
    }

    public  static GeneratorConfig getInstance(){
        if(instance == null) {
            synchronized (GeneratorConfig.class) {
                if(instance == null) {
                    try {
                        return instance = ResourceWrapper.getResourceBean(GeneratorConfig.class);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    return instance = new GeneratorConfig();
                }
            }
        }

        return instance;
    }
}
