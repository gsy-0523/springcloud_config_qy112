package com.gsy.ps;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.gsy.ps.dao")
@EnableDiscoveryClient
@EnableSwagger2
public class PowerApp {
    public static void main(String[] args) {
        SpringApplication.run(PowerApp.class,args);
    }
}
