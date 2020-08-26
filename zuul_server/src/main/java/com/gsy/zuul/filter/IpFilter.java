package com.gsy.zuul.filter;


import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * fileName:IpFilter
 * description: 过滤非法IP ，不合法IP不允许请求任何微服务
 * author:zz
 * createTime:2020/8/22 13:44
 * version:1.0.0
 */
@Component
public class IpFilter extends ZuulFilter{


    //使用value读取配置文件中的配置
    @Value("${illegalIp}")
    private String illegalIp;

    @Override
    public String filterType() {
        //pre   在业务执行之前，执行的方法
        //route  在业务执行时，执行的方法
        //post   在业务执行后，执行的方法
        //error  在业务执行出错时，执行的方法
        return "pre";
    }

    @Override
    public int filterOrder() {
        //如果有多个filter   该方法的返回值确定该filter执行的顺序   返回值越小，优先级越高
        return 10;
    }

    @Override
    public boolean shouldFilter() {
        //该方法确定当前过滤器是否生效   返回true生效  返回false 不生效
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //该过滤器核心部分，要执行主要业务，都在这编写
        //使用zuul组件中提供的请求上下文类获取请求上下文对象
        RequestContext requestContext = RequestContext.getCurrentContext();
        //使用上下文对象获取HttpServletRequest
        HttpServletRequest  request =requestContext.getRequest();
        //使用上下文对象获取HttpServletResponse
        HttpServletResponse response = requestContext.getResponse();
        //获取IP地址
        String remoteAddr = request.getRemoteAddr();//获取IP地址
        //System.out.println("要禁用的IP为："+illegalIp);
        System.out.println("进入了IP过滤器，访问的IP为："+remoteAddr);
        //判断IP地址是否合法
        if(illegalIp.contains(remoteAddr)){ //"172.16.22.107,172.16.22.71,172.16.22.32,172.16.22.50,172.16.22.35,172.16.22.76".contains("172.16.22.107")  true
            /*requestContext.setResponseStatusCode(HttpStatus.SC_FORBIDDEN);
            requestContext.setResponseBody("FORBIDDEN");*/
            try {
                response.setCharacterEncoding("utf-8");
                response.sendError(HttpStatus.SC_FORBIDDEN,"你的IP进制访问我的服务");
                return null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
