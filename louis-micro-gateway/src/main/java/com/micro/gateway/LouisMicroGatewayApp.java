package com.micro.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author louis
 * <p>
 * Date: 2019/12/4
 * Description:
 *
 * 网关整合Oauth2 有两种方案
 * 1. 认证服务器生成jwt令牌，所有请求统一在网关层验证
 * 2. 由各个资源服务器处理，网关只做转发
 */
@SpringCloudApplication
@EnableZuulProxy
@EnableOAuth2Sso
@EnableHystrix
public class LouisMicroGatewayApp {

    public static void main(String[] args) {
        SpringApplication.run(LouisMicroGatewayApp.class, args);

    }
}
