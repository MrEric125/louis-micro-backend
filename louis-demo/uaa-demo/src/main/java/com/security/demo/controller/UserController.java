package com.security.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author louis
 * <p>
 * Date: 2019/12/14
 * Description:
 */
@RestController
public class UserController {
    @GetMapping("/user")
    public Principal user(Principal member) {
        return member;
    }

}
