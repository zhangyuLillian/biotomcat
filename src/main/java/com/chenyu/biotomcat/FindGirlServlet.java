package com.chenyu.biotomcat;

import java.io.IOException;

/**
 * @author zhangyu
 * @description findGirl
 * @date 2018/10/11 16:05
 */
public class FindGirlServlet extends MyServlet {
    @Override
    public void doGet(MyRequest request, MyResponse response) {
        try {
            response.write("get girl : a girl is coming");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(MyRequest request, MyResponse response) {
        try {
            response.write("post girl : a girl is coming");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
