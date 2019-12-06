package org.micro.security.controller;

import com.google.common.collect.Maps;
import com.louis.common.api.wrapper.WrapMapper;
import com.louis.common.api.wrapper.Wrapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author John·Louis
 * @date create in 2019/12/5
 * description:
 */
@RestController
public class UserController {

    @RequestMapping("/user")
    public Wrapper user() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Map<String, Object> userInfo = Maps.newHashMap();
        userInfo.put("user", authentication.getPrincipal());
        userInfo.put("authrities", authentication.getAuthorities());
        return WrapMapper.wrap(userInfo);
    }
}
