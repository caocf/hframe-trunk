package com.hframework.generator.thirdplatform.core;

import com.hframe.common.util.FileUtils;
import com.hframe.common.util.ResourceWrapper;
import com.hframe.common.util.message.XmlUtils;
import com.hframe.generator.bean.*;
import com.hframework.generator.thirdplatform.bean.Descriptor;
import com.hframework.generator.thirdplatform.bean.GeneratorConfig;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by zqh on 2016/4/15.
 */
public class Bootstrap {
    public static void main(String[] args) throws IOException {
        String thirdName = "weixinpay.xml";

        GeneratorConfig generatorConfig = GeneratorConfig.getInstance();
//        System.out.println(generatorConfig.getJavaRootPath());
//        System.out.println(generatorConfig.getResourceRootPath());

        Descriptor descriptor = XmlUtils.readValueFromFile("/thirdplatform/" + thirdName, Descriptor.class);

        descriptor.setPlatformName(thirdName.substring(0, thirdName.lastIndexOf(".")));
//        System.out.println(descriptor);

        new PropertiesGenerator().generate(generatorConfig, descriptor);
        new TestRespMessageGenerator().generate(generatorConfig, descriptor);
        new ConfigureBeanGenerator().generate(generatorConfig, descriptor);

        HelperBeanGenerator helperBeanGenerator = new HelperBeanGenerator();
        helperBeanGenerator.generate(generatorConfig, descriptor);

        ClientBeanGenerator clientBeanGenerator = new ClientBeanGenerator();
        clientBeanGenerator.helperClass = helperBeanGenerator.helperClass;
        clientBeanGenerator.generate(generatorConfig,descriptor);
    }
}
