package org.micro.feign;

import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

/**
 * @author John·Louis
 * @date create in 2019/12/21
 * description: feign 接口测试
 */
@FeignClient(value = "feign-server1")
public interface FeignApi {

    List<String> findByName(String name);

    String findOne(String id);
}
