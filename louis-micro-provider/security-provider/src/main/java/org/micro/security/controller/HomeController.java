package org.micro.security.controller;

import org.micro.common.api.wrapper.MapperWrap;
import org.micro.common.api.wrapper.Wrapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author louis
 * <p>
 * Date: 2019/12/4
 * Description:
 */
@RestController
public class HomeController {

    @RequestMapping("/")
    public Wrapper homePage() {
        return MapperWrap.wrap("this is home");
    }

}
