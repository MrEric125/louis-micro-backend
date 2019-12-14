package org.micro.order;

import org.micro.filter.UserDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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
//    @PreAuthorize("hasAuthority('p1')")//拥有p1权限方可访问此url
    public String r1(){
        //获取用户身份信息
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDTO) {
            return ((UserDTO) principal).getFullname();
        }
        return String.valueOf(principal + "访问资源1");
    }

    /**
     * 只有 ROLE_USER 角色的用户才能访问
     * @return 问候信息
     */
    @GetMapping("/hello")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String helloUser(){
        return "hello User";
    }

    /**
     * 只有 ROLE_ADMIN 角色的用户才能访问
     * @return 问候信息
     */
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String helloAdmin(){
        return "hello Admin";
    }

    /**
     * 只有 ROLE_GUEST 角色的用户才能访问
     * @return 问候信息
     */
    @GetMapping("/guest")
    @PreAuthorize("hasRole('ROLE_GUEST')")
    public String helloGuest(){
        return "hello Guest";
    }


}
