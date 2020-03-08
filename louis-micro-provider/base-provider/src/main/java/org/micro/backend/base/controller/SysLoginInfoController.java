package org.micro.backend.base.controller;

import org.micro.backend.base.entity.SysLoginInfo;
import org.micro.backend.base.seervice.SysLoginInfoService;
import org.micro.common.api.wrapper.MapperWrap;
import org.micro.common.api.wrapper.Wrapper;
import org.micro.web.common.BaseWebController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author John·Louis
 * @date created on 2020/3/8
 * description:
 */
@RequestMapping("/loginInfo")
@RestController
public class SysLoginInfoController extends BaseWebController<SysLoginInfo, Long> {


    @SuppressWarnings("unchecked")
    @Override
    protected SysLoginInfoService getService() {
        return getService(SysLoginInfoService.class);
    }


    @RequestMapping("/insert")
    public Wrapper insert() {
        SysLoginInfo loginInfo = SysLoginInfo.builder()
                .lastLogin(new Date())
                .browser("test")
                .ip("test")
                .os("window")
                .requestUrl("insert")
                .userId(1L)
                .username("admin")
                .build();

        SysLoginInfo sysLoginInfo = getService().save(loginInfo);

        return MapperWrap.nonullWrap(sysLoginInfo);

    }


}
