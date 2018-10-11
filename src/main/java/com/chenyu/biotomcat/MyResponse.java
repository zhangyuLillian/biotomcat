package com.chenyu.biotomcat;

import lombok.Data;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author zhangyu
 * @description 我的response类
 * @date 2018/10/11 15:43
 */
@Data
public class MyResponse {

    private OutputStream outputStream;

    public MyResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    //基于HTTP协议的格式进行输出写入
    public void write(String content) throws IOException {
        //HTTP响应报文
        //HTTP1.1 200 OK
        //Content-Type: text/html
        //
        //<html><body></body></html>
        StringBuilder httpResponse = new StringBuilder();
        //\r是将当前位置移到本行的开头
        //\n是将当前位置移到下一行的开头
        httpResponse.append("HTTP1.1 200 OK\n")
                    .append("Content-Type: text/html\n")
                    .append("\r\n")
                    .append("<html><body>")
                    .append(content)
                    .append("</body></html>");
        outputStream.write(httpResponse.toString().getBytes());
        outputStream.close();
    }
}
