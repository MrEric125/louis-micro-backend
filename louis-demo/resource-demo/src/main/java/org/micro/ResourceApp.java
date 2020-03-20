package org.micro;

import org.louis.micro.MicroServiceClient;
import org.springframework.boot.SpringApplication;

/**
 * @author John·Louis
 *  created at 2019/12/6
 * description:
 * 简单的作为资源服务功能来使用
 */
@MicroServiceClient
public class ResourceApp {

    public static void main(String[] args) {
        SpringApplication.run(ResourceApp.class, args);
    }
}
