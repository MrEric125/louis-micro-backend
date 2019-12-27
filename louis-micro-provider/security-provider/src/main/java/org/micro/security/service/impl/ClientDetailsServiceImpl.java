//package org.micro.security.service.impl;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.ArrayUtils;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
//import org.springframework.security.oauth2.config.annotation.builders.JdbcClientDetailsServiceBuilder;
//import org.springframework.security.oauth2.provider.ClientDetails;
//import org.springframework.security.oauth2.provider.ClientDetailsService;
//import org.springframework.security.oauth2.provider.ClientRegistrationException;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.PostConstruct;
//import javax.sql.DataSource;
//
///**
// * @author louis
// * <p>
// * Date: 2019/12/5
// * Description:
// */
//@Slf4j
//@Service
//public class ClientDetailsServiceImpl implements ClientDetailsService, InitializingBean {
//
//
//    @Autowired
//    ClientDetailsService clientDetailsService;
//
//    @Autowired
//    DataSource dataSource;
//
//
//    @Override
//    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
//        return clientDetailsService.loadClientByClientId(clientId);
//    }
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
////        JdbcClientDetailsServiceBuilder builder = new JdbcClientDetailsServiceBuilder();
////
////        builder.withClient("client")
////                .secret("adminClient")
////                .authorizedGrantTypes("refresh_token", "password", "client_credentials", "authorization_code")
////                .accessTokenValiditySeconds(30000)
////                .refreshTokenValiditySeconds(30000)
////                .redirectUris("https://www.baidu.com")
////                .scopes("webclient");
////        try {
////            ClientDetails client = clientDetailsService.loadClientByClientId("client");
////            if (client == null) {
////                builder.dataSource(dataSource);
////                clientDetailsService = builder.build();
////            }
////
////        } catch (Exception e) {
////            log.error("init={}", e.getMessage(), e);
////        }
//    }
//}
