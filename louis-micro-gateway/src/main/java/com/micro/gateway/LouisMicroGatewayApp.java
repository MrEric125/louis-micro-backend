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
