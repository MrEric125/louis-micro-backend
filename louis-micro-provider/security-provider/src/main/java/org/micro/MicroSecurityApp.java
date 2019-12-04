package org.micro;

import org.louis.micro.MicroServiceClient;
import org.springframework.boot.SpringApplication;

/**
 * @author louis
 * <p>
 * Date: 2019/12/4
 * Description:
 */
@MicroServiceClient
public class MicroSecurityApp {

    public static void main(String[] args) {
        SpringApplication.run(MicroSecurityApp.class, args);
    }
}
