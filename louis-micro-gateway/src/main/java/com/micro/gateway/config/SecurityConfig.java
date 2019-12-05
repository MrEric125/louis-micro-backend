//package com.micro.gateway.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// * @author louis
// * <p>
// * Date: 2019/12/4
// * Description:
// */
//@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
////
////    @Bean
////    public JwtTokenStore jwtTokenStore() {
////        return new JwtTokenStore(jwtAccessTokenConverter());
////    }
////    @Bean
////    public JwtAccessTokenConverter jwtAccessTokenConverter() {
////        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
////        converter.setSigningKey("louismicro");
////        return converter;
////    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//    }
//}
