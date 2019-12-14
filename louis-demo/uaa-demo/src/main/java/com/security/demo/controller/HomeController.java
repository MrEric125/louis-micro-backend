package com.security.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author louis
 * <p>
 * Date: 2019/12/11
 * Description:
 */
@RestController
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "this is home";

    }
}
