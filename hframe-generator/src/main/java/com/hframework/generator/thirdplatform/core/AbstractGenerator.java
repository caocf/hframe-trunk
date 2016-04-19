package com.hframework.generator.thirdplatform.core;

import com.hframework.generator.thirdplatform.bean.Descriptor;
import com.hframework.generator.thirdplatform.bean.GeneratorConfig;

/**
 * Created by zhangquanhong on 2016/4/16.
 */
public abstract class AbstractGenerator implements Generator<GeneratorConfig,Descriptor> {
    protected String javaRootPath = null;
    protected String resourceKeyPrefix = null;
    protected String platformName = null;
    protected String javaPackage = null;
    protected String resourceFolder = null;
    protected String resourceRootPath = null;

    protected  com.hframe.generator.bean.Class helperClass;

    public AbstractGenerator() {

    }

    public boolean generate(GeneratorConfig generatorConfig, Descriptor descriptor) {
        javaRootPath = generatorConfig.getJavaRootPath();
        resourceRootPath = generatorConfig.getResourceRootPath();

        javaPackage = descriptor.getGlobal().getProperties().getJavaPackage();
        resourceFolder = descriptor.getGlobal().getProperties().getResourceFolder();

        resourceKeyPrefix =
                generatorConfig.getResourceKeyPrefix() == null ? "" : generatorConfig.getResourceKeyPrefix();
        platformName = descriptor.getPlatformName();

        return generateInternal(generatorConfig, descriptor);
    }

    protected abstract boolean generateInternal(GeneratorConfig generatorConfig, Descriptor descriptor);


}
