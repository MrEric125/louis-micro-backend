package org.louis.dubbo;


import lombok.extern.slf4j.Slf4j;
import org.louis.micro.DemoService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author John·Louis
 *  create at 2019/9/14
 * description:
 */
@Slf4j
@Service(version = "1.0.0")
public class DefaultServiceImpl implements DemoService {

    @Value("${dubbo.application.name}")
    private String serviceName;

    public String sayHello(String name) {

        String format = String.format("[%s] : Hello, %s", serviceName, name);
        log.info("客户端调用了>>>{}", format);
        return format;
    }
}
