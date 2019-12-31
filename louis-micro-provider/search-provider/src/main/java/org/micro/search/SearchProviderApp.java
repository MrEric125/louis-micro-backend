package org.micro.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @author John·Louis
 * @date create in 2019/12/23
 * description:
 */
@SpringBootApplication
//@EnableElasticsearchRepositories
public class SearchProviderApp {
    public static void main(String[] args) {
        SpringApplication.run(SearchProviderApp.class, args);
    }
}

