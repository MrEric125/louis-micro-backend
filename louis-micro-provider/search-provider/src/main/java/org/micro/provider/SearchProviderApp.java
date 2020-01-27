package org.micro.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author John·Louis
 *  created at 2019/12/23
 * description:
 */
@SpringBootApplication
//@EnableElasticsearchRepositories
public class SearchProviderApp {
    public static void main(String[] args) {
        SpringApplication.run(SearchProviderApp.class, args);
    }
}

