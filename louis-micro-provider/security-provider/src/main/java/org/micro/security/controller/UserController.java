package org.micro.security.controller;

import com.google.common.collect.Maps;
import org.micro.common.api.wrapper.WrapMapper;
import org.micro.common.api.wrapper.Wrapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author John·Louis
 *  create at 2019/12/5
 * description:
 */
@RestController
public class UserController {

    @RequestMapping("/user")
    public Wrapper user() {

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
            return WrapMapper.wrap(userInfo);
        }
        userInfo.put("user", authentication.getPrincipal());
        userInfo.put("authrities", authentication.getAuthorities());
        return WrapMapper.wrap(userInfo);


    }
}
