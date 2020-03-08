package org.micro.backend.base.seervice;

import org.micro.backend.base.entity.SysLoginInfo;
import org.micro.backend.base.repository.SysLoginInfoRepository;
import org.micro.base.service.impl.BaseWebService;
import org.springframework.stereotype.Service;

/**
 * @author John·Louis
 * @date created on 2020/3/8
 * description:
 */
@Service
public class SysLoginInfoService extends BaseWebService<SysLoginInfo, Long> {

    @Override
    public SysLoginInfoRepository getRepository() {
        return getRepository(SysLoginInfoRepository.class);
    }





}
