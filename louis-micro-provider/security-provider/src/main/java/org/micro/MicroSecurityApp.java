package org.micro;

import com.louis.core.repository.SimpleBaseRepository;
import org.louis.micro.MicroServiceClient;
import org.springframework.boot.SpringApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author louis
 * <p>
 * Date: 2019/12/4
 * Description:
 */
@EnableJpaRepositories(repositoryBaseClass = SimpleBaseRepository.class)
@MicroServiceClient
@EnableResourceServer
public class MicroSecurityApp {

    public static void main(String[] args) {
        SpringApplication.run(MicroSecurityApp.class, args);
    }
}