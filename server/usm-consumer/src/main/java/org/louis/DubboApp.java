package org.louis;

import org.louis.micro.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author John·Louis
 * @date create in 2019/9/14
 * description:
 */
@Slf4j
//@SpringBootApplication
@RestController
@EnableAutoConfiguration
public class DubboApp {

    public static void main(String[] args) {
        SpringApplication.run(DubboApp.class,args);
    }

    @Reference(version = "1.0.0")
    private DemoService demoService;


    @RequestMapping("/consumer")
    public String consumer() {
        return demoService.sayHello("Louis");
    }


}
