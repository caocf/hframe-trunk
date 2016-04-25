package com.hframework.common.util.security;

/**
 * Created by zhangquanhong on 2016/4/17.
 */
public class MD5Util {


    /**
     * 加密
     *
     * @param content  待加密内容
     * @return
     */
    public static String encrypt(String content) {
        return SecurityUtils.MD5DecodeString(content);
    }

}
