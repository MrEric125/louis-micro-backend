package org.micro.provider.controller;

import org.micro.common.api.wrapper.WrapMapper;
import org.micro.common.api.wrapper.Wrapper;
import org.micro.provider.entity.OrderEs;
import org.micro.provider.repository.OrderEsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author John·Louis
 * @date create in 2019/12/23
 * description:
 */
@RestController
@RequestMapping("/es/order")
public class OrderESController {

    @Autowired
    private OrderEsRepository orderEsRepository;


    @RequestMapping("/save")
    public Wrapper save(@RequestBody OrderEs orderEs) {
        return WrapMapper.wrap(orderEsRepository.save(orderEs));
    }

    @RequestMapping("/searchById/{orderId}")
    public Wrapper search(@PathVariable Long orderId ) {
        OrderEs orderEs = orderEsRepository.findById(orderId).orElseThrow(() -> new NullPointerException("sss"));
        return WrapMapper.wrap(orderEs);
    }

}
