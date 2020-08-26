package com.gsy.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * fileName:ZuuApp
 * description:
 * author:zz
 * createTime:2020/8/21 17:09
 * version:1.0.0
 */
@SpringBootApplication
@EnableDiscoveryClient //开启客户端注册功能
@EnableZuulProxy  //开启路径代理功能
public class ZuulApp {
    public static void main(String[] args) {
        SpringApplication.run(ZuulApp.class,args);
    }
}
