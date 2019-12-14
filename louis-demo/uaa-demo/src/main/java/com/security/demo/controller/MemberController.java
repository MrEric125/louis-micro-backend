package com.security.demo.controller;

import com.louis.common.api.wrapper.WrapMapper;
import com.louis.common.api.wrapper.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * 〈会员Controller〉
 *
 * @author Curise
 * @create 2018/12/13
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api")
public class MemberController {


    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @GetMapping("/member")
    public Principal user(Principal member) {
        return member;
    }

    /**
     * // TODO: 2019/12/14  注销操作，后期可以写到handler中去
     * @param access_token
     * @return
     */
    @DeleteMapping(value = "/exit")
    public Wrapper revokeToken(String access_token) {
        boolean revokeToken = consumerTokenServices.revokeToken(access_token);
        return WrapMapper.wrap(revokeToken);
    }

}
