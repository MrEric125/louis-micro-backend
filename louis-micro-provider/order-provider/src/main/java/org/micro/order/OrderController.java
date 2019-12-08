package org.micro.order;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author John·Louis
 * @date create in 2019/12/8
 * description:
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @GetMapping(value = "/r1")
//    @PreAuthorize("hasAnyAuthority('r1')")
    public String r1() {
        return "访问资源";

    }


}
