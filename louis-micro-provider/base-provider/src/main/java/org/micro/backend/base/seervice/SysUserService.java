package org.micro.backend.base.seervice;

import org.micro.backend.base.entity.SysUser;
import org.micro.backend.base.repository.SysUserRepository;
import org.micro.base.repository.BaseRepository;
import org.micro.base.service.impl.BaseWebService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * @author John·Louis
 * @date created on 2020/3/20
 * description:
 */
@Service
public class SysUserService extends BaseWebService<SysUser, Long> {


    @Resource
    private SysUserRepository sysUserMapper;

    @Override
    protected SysUserRepository getRepository() {
        return getRepository(sysUserMapper.getClass());
    }
}
