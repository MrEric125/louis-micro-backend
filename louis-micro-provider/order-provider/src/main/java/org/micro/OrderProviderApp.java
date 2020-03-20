package org.micro;

import org.louis.micro.MicroServiceClient;
import org.springframework.boot.SpringApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author John·Louis
 *  created at 2019/12/6
 * description:
 * 简单的作为资源服务功能来使用
 */
@EnableJpaRepositories
@MicroServiceClient
public class OrderProviderApp {

    public static void main(String[] args) {
        SpringApplication.run(OrderProviderApp.class, args);
    }
}
