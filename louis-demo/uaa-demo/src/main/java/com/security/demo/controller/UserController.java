package com.security.demo.controller;

import com.google.common.collect.Maps;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author louis
 * <p>
 * Date: 2019/12/14
 * Description:
 */
@RequestMapping("/user")
@RestController
public class UserController {
    @RequestMapping
    public Principal user(Principal member) {
        return member;
    }

//    @RequestMapping(value = {"/current"}, produces = "application/json")
//    public Map<String, Object> user(Principal principal) {
//
//        Map<String, Object> userInfo = Maps.newHashMap();
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//
//
//        String header = request.getHeader("Authorization");
//
//        userInfo.put("header", header);
//
//        if (authentication instanceof OAuth2Authentication) {
//            OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
//            Authentication userAuthentication = oAuth2Authentication.getUserAuthentication();
//            List<String> authorities = userAuthentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
//
//            userInfo.put("user", authentication.getPrincipal());
//            userInfo.put("authrities", authorities);
//            return userInfo;
//        }
//        userInfo.put("user", authentication.getPrincipal());
//        userInfo.put("authrities", authentication.getAuthorities());
//        return userInfo;

//    }

}
