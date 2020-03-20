package org.micro.search.controller;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.micro.common.api.wrapper.MapperWrap;
import org.micro.common.api.wrapper.Wrapper;
import org.micro.search.entity.OrderEs;
import org.micro.search.repository.OrderEsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author John·Louis
 *  created at 2019/12/23
 * description:
 */
@RestController
@RequestMapping("/es/order")
public class OrderESController {

    @Autowired
    private OrderEsRepository orderEsRepository;


    /**
     * save or update data
     * @param orderEs
     * @return
     */
    @RequestMapping("/save")
    public Wrapper save(@RequestBody OrderEs orderEs) {
        return MapperWrap.wrap(orderEsRepository.save(orderEs));
    }

    /**
     * 通过id查询
     * @param orderId 查询id
     * @return 查询到的document信息
     */
    @RequestMapping("/searchById/{orderId}")
    public Wrapper search(@PathVariable Long orderId ) {
        OrderEs orderEs = orderEsRepository.findById(orderId).orElse(null);
        return MapperWrap.nonullWrap(orderEs);
    }

    /**
     * 通过关键字查询
     * @param searchWorld 查询关键字
     * @return 分页信息
     */
    @RequestMapping("/search/{searchWorld}")
    public Wrapper searchAnyWorld(@PathVariable String searchWorld) {
        QueryBuilder queryBuilder = new QueryStringQueryBuilder(searchWorld);
        SearchQuery searchQuery = new NativeSearchQuery(queryBuilder);
        Page<OrderEs> esPage = orderEsRepository.search(searchQuery);
        return MapperWrap.nonullWrap(esPage);

    }

}
