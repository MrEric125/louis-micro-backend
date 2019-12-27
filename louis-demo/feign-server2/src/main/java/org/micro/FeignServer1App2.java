package org.micro;

import org.louis.micro.MicroServiceClient;
import org.springframework.boot.SpringApplication;

/**
 * @author John·Louis
 * @date create in 2019/12/21
 * description:
 */
@MicroServiceClient
public class FeignServer1App2 {
    public static void main(String[] args) {
        SpringApplication.run(FeignServer1App2.class, args);
    }
}
