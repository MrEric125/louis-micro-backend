package org.micro.feignServer2;

import org.micro.feign.FeignApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author John·Louis
 *  created at 2019/12/21
 * description:
 */
@Service
public class FeignApiConsumer {

    @Autowired
    private FeignApi feignApi;

    public List<String> findByName(String name) {
        return feignApi.findByName(name);
    }

    public String findOne(String id) {
        return feignApi.findOne(id);
    }


}
