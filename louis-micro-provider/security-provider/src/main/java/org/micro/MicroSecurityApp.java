package org.micro;

import org.louis.micro.MicroServiceClient;
import org.springframework.boot.SpringApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author louis
 * <p>
 * Date: 2019/12/4
 * Description:
 */
//@EnableJpaRepositories(repositoryBaseClass = SimpleBaseRepository.class)
@EnableJpaRepositories
@MicroServiceClient
public class MicroSecurityApp {

    public static void main(String[] args) {
        SpringApplication.run(MicroSecurityApp.class, args);
    }
}
