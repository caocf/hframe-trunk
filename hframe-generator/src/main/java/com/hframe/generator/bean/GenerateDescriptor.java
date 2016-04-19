package com.hframe.generator.bean;

/**
 * Created by zhangquanhong on 2016/4/19.
 */
public interface GenerateDescriptor {

    public void setJavaPackage(String javaPackage);

    public String getJavaPackage();

    public void setJavaRootPath(String javaRootPath);

    public String getJavaRootPath();

    public void setTemplatePath(String templatePath);

    public String getTemplatePath();

    public void execute(com.hframe.generator.bean.Class clazz,XmlNode xmlNode);
}
