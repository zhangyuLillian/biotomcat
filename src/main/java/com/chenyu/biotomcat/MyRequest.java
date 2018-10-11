package com.chenyu.biotomcat;

import lombok.Data;
import sun.plugin2.os.windows.Windows;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author zhangyu
 * @description 我的request类
 * @date 2018/10/11 14:47
 */
@Data
public class MyRequest {
    private String url;
    //get、post ...
    private String method;

    public MyRequest(InputStream inputStream) throws IOException {

        String httpRequest = "";
        byte[] httpRequestBytes = new byte[1024];
        int length = 0;
        if ((length = inputStream.read(httpRequestBytes)) > 0) {
            httpRequest = new String(httpRequestBytes,0,length);
        }
        //HTTP请求报文
        //GET /girl HTTP/1.1
        //Host: localhost:8080
        //Connection: keep-alive
        //Cache-Control: max-age=0
        //User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.26 Safari/537.36 Core/1.63.6756.400 QQBrowser/10.3.2473.400
        //Upgrade-Insecure-Requests: 1
        //Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8
        //Accept-Encoding: gzip, deflate, br
        //Accept-Language: zh-CN,zh;q=0.9
        String httpHead = httpRequest.split("\n")[0];
        System.out.println(httpHead);
        //"\\s"是代表所有空白字符，比如空格或者tab
        url = httpHead.split("\\s")[1];
        method = httpHead.split("\\s")[0];
        System.out.println(this);
    }
}
