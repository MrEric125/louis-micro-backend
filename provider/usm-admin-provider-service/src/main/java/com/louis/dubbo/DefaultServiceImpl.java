package com.louis.dubbo;


import com.louis.cloudplus.DemoService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author John·Louis
 * @date create in 2019/9/14
 * description:
 */
@Service(version = "1.0.0")
public class DefaultServiceImpl implements DemoService {

    @Value("${dubbo.application.name}")
    private String serviceName;

    public String sayHello(String name) {
        return String.format("[%s] : Hello, %s", serviceName, name);
    }
}
