package com.hframework.generator.thirdplatform.core;

import com.hframe.common.util.FileUtils;
import com.hframe.common.util.ResourceWrapper;
import com.hframe.common.util.StringUtils;
import com.hframe.common.util.message.VelocityUtil;
import com.hframe.common.util.message.XmlUtils;
import com.hframe.generator.base.BeanGeneratorUtil;
import com.hframe.generator.bean.Field;
import com.hframe.generator.util.CreatorUtil;
import com.hframework.generator.thirdplatform.bean.Descriptor;
import com.hframework.generator.thirdplatform.bean.GeneratorConfig;
import com.hframework.generator.thirdplatform.bean.InterfaceExample;
import com.hframework.generator.thirdplatform.bean.descriptor.Interface;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zqh on 2016/4/16.
 */
public class ConfigureBeanGenerator extends AbstractGenerator implements Generator<GeneratorConfig,Descriptor>{
    @Override
    protected boolean generateInternal(GeneratorConfig generatorConfig, Descriptor descriptor) {

        com.hframe.generator.bean.Class beanClass = new com.hframe.generator.bean.Class();
        beanClass.setSrcFilePath(javaRootPath);
        beanClass.setClassPackage(javaPackage);
        beanClass.setClassName(CreatorUtil.getJavaClassName(platformName) + "Config");
        beanClass.addAnnotation("@Source(\"" + resourceFolder + "/" + platformName + ".properties\")");


        beanClass.addImportClass("com.hframe.common.annotation.*");
        beanClass.addImportClass("com.hframe.common.util.ResourceWrapper");
        beanClass.addImportClass("java.lang.reflect.InvocationTargetException");


        List<String> staticParameterList = descriptor.getGlobal().getStaticParameters().getStaticParameterList();

        for (String staticParameter : staticParameterList) {
            Field field = new Field("String",
                    CreatorUtil.getJavaVarName(staticParameter.replaceAll(resourceKeyPrefix,"")));
            field.addFieldAnno("@Key( \"" + MessageFormat.format(resourceKeyPrefix,platformName) + staticParameter + "\")");
            beanClass.addField(field);
        }

        //写入接口明细
        List<Interface> interface1List = descriptor.getInterfaces().getInterface1List();
        for (Interface anInterface : interface1List) {
            Field field = new Field("String", ResourceWrapper.JavaUtil.getJavaVarName(anInterface.getName()));
            field.addFieldAnno("@Key( \"" + MessageFormat.format(resourceKeyPrefix,platformName) + "interface." +  anInterface.getName() + "\")");
            beanClass.addField(field);
        }

        Map map = new HashMap();
        map.put("CLASS", beanClass);
        String content = VelocityUtil.produceTemplateContent("com/hframe/generator/vm/configureBeanByTemplate.vm", map);
        System.out.println(content);
        FileUtils.writeFile(beanClass.getFilePath(), content);

        return false;
    }
}
