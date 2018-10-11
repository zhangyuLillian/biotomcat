package com.chenyu.biotomcat;

import lombok.Data;

/**
 * @author zhangyu
 * @description servlet映射类
 * @date 2018/10/11 14:29
 */
@Data
public class ServletMapping {

    //名字.
    private String servletName;

    private String url;

    //包名+类名，用于反射
    private String clazz;

    public ServletMapping(String servletName, String url, String clazz) {
        this.servletName = servletName;
        this.url = url;
        this.clazz = clazz;
    }
}
