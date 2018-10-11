package com.chenyu.biotomcat;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangyu
 * @description servlet映射配置类
 * @date 2018/10/11 14:27
 */
public class ServletMappingConfig {

    //持有servlet映射集合
    public static List<ServletMapping> servletMappingList = new ArrayList<>();

    //这里模拟在servlet开发中，会在web.xml中通过和来进行指定哪个URL交给哪个servlet进行处理。
    static {
        servletMappingList.add(new ServletMapping("findGirl","/girl","com.chenyu.biotomcat.FindGirlServlet"));
    }

}
