package com.chenyu.biotomcat;

import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangyu
 * @description 我的tomcat
 * @date 2018/10/11 14:21
 */
@Data
public class MyTomcat {

    //端口,默认8080
    private int port = 8080;

    //url与servlet的映射关系
    private Map<String,String> urlServletMap = new HashMap<>();

    public MyTomcat(int port) {
        this.port = port;
    }

    public MyTomcat(int port, Map<String, String> urlServletMap) {
        this.port = port;
        this.urlServletMap = urlServletMap;
    }

    //启动tomcat
    public void start() {
        //初始化servlet映射
        initServletMapping();

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("mytomcat is start at 端口:" + port);

            while (true) {
                //监听阻塞
                Socket socket = serverSocket.accept();
                MyRequest request = new MyRequest(socket.getInputStream());
                MyResponse response = new MyResponse(socket.getOutputStream());

                //请求分发
                dispatch(request,response);
                socket.close();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                        e.printStackTrace();
                }
            }
        }
    }

    private void initServletMapping() {
        if (!CollectionUtils.isEmpty(ServletMappingConfig.servletMappingList)) {
            for (ServletMapping servletMapping:ServletMappingConfig.servletMappingList) {
                urlServletMap.put(servletMapping.getUrl(),servletMapping.getClazz());
            }
        }
    }

    private void dispatch(MyRequest request,MyResponse response) {
        //用于反射拿到servlet
        String clazz = urlServletMap.get(request.getUrl());
        try {
            Class<MyServlet> myServletClass = (Class<MyServlet>) Class.forName(clazz);
            MyServlet myServlet = myServletClass.newInstance();
            myServlet.service(request,response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new MyTomcat(8080).start();
    }
}
