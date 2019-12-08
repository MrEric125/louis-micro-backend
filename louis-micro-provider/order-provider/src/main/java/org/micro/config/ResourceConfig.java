package org.micro.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;


/**
 * @author John·Louis
 * @date create in 2019/12/7
 * description:
 */
@Slf4j
@EnableResourceServer
public class ResourceConfig extends ResourceServerConfigurerAdapter {


    public static final String RESOURCE_ID = "order";

    @Autowired
    DiscoveryClient discoveryClient;

    /**
     * 不用zuul做路由的时候可以使用这种方式来配合授权中心和资源中心的数据是匹配的
     * 如果用了网关就不需要这种方式了
     * @return
     */
    @Bean
    public ResourceServerTokenServices tokenServices() {
        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
//        List<ServiceInstance> instances = discoveryClient.getInstances("security-service-A");
//        String securityUri = "";
//        if (CollectionUtils.isNotEmpty(instances)) {
//            securityUri = instances.get(0).getUri().toASCIIString();
//        }
        remoteTokenServices.setCheckTokenEndpointUrl("http://localhost:9311/oauth/check_token");
        log.info("==========get security uri===============");
//        log.info(" security uri ===>{}", securityUri);
        remoteTokenServices.setClientId("client");
        remoteTokenServices.setClientSecret("secret");
        return remoteTokenServices;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(RESOURCE_ID)
                .tokenServices(tokenServices())
                .stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/**").access("#oauth2.hasAnyScope('webclient')")
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ;
    }



}
