package org.micro.backend.base.controller;

import org.micro.backend.base.entity.SysUser;
import org.micro.backend.base.seervice.SysUserService;
import org.micro.web.common.BaseWebController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author John·Louis
 * @date created on 2020/3/20
 * description:
 */
@RequestMapping("/user")
@RestController
public class SysUserController extends BaseWebController<SysUser, Long> {

    @Resource
    private SysUserService sysUserService;

    @Override
    protected  SysUserService getService() {
        return getService(sysUserService.getClass());
    }

}
