package com.chenyu.biotomcat;

/**
 * @author zhangyu
 * @description 我的servlet抽象类
 * @date 2018/10/11 14:45
 */
public abstract class MyServlet {

    public abstract void doGet(MyRequest request,MyResponse response);

    public abstract void doPost(MyRequest request,MyResponse response);

    public void service(MyRequest request,MyResponse response) {
        if (request.getMethod().equalsIgnoreCase("GET")) {
            doGet(request,response);
        }else if (request.getMethod().equalsIgnoreCase("POST")) {
            doPost(request,response);
        }
    }
}
