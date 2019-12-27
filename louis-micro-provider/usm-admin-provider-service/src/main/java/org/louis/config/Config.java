package org.louis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author John·Louis
 * @date create in 2019/9/15
 * description:
 */
@Configuration
public class Config {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
