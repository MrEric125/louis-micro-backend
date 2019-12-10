package com.security;



import com.google.common.collect.Maps;
import com.louis.common.api.wrapper.WrapMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootApplication
@RestController
public class SecurityDemoApplication {
    @RequestMapping(value = { "/user" }, produces = "application/json")
    public Map<String, Object> user() {
        Map<String, Object> userInfo = Maps.newHashMap();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();


        String header = request.getHeader("Authorization");

        userInfo.put("header", header);

        if (authentication instanceof OAuth2Authentication) {
            OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
            Authentication userAuthentication = oAuth2Authentication.getUserAuthentication();
            List<String> authorities = userAuthentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

            userInfo.put("user", authentication.getPrincipal());
            userInfo.put("authrities", authorities);
            return userInfo;
        }
        userInfo.put("user", authentication.getPrincipal());
        userInfo.put("authrities", authentication.getAuthorities());
        return userInfo;

    }


    public static void main(String[] args) {
        SpringApplication.run(SecurityDemoApplication.class, args);
    }


}
