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
import java.util.Enumeration;

/**
 * fileName:IllegalCharFilter
 * description:  限制非法字符访问我的所有微服务
 * author:zz
 * createTime:2020/8/22 14:32
 * version:1.0.0
 */
@Component
public class IllegalCharFilter extends ZuulFilter {

    @Value("${illegalCharacter}")
    private  String illegalChar;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 5;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("进入了非法字符过滤器。。。。。。。。。。。。。。。。。。。。");
        //使用zuul组件中提供的请求上下文类获取请求上下文对象
        RequestContext requestContext = RequestContext.getCurrentContext();
        //使用上下文对象获取HttpServletRequest
        HttpServletRequest request =requestContext.getRequest();
        //使用上下文对象获取HttpServletResponse
        HttpServletResponse response = requestContext.getResponse();
        //http://172.16.22.102:8888/os/orderlectAll?a=1&b=2&c=3&aa=11&cc=2sb2&ddd=333
        //获取请求中参数的名称的集合 [a,b,c,aa,cc,ddd]
        Enumeration<String> parameterNames =request.getParameterNames();
        //分割非法字符串，使用逗号隔开
        String[] illegalCharArray = illegalChar.split(",");//tmd,nnd,mmd,sb
        //循环获取每一个参数名称
        while(parameterNames.hasMoreElements()){ //判断有没有下一个
            String paramName = parameterNames.nextElement();//第1次循环a  第2次循环 b .....最后ddd
            //根据参数名称获取值
            String paramValue = request.getParameter(paramName); //第1次循环1sb1  第2次 2  ....最后333
            //判断是否含有非法字符
            for (String illegalC : illegalCharArray) {
                //第1次 1sb1.contains(tmd)  第2次 1sb1.contains(nnd)  。。。 最后  1sb1.contains(sb)
                if(paramValue.contains(illegalC)){
                    try {
                        response.setCharacterEncoding("utf-8");
                        response.sendError(HttpStatus.SC_FORBIDDEN,"您的请求中含有敏感词，不允许访问我的服务");
                        return null;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }
}

