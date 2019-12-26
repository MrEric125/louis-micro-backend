package org.micro.provider.controller;

import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.micro.common.api.wrapper.WrapMapper;
import org.micro.common.api.wrapper.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author John·Louis
 * @date create in 2019/12/23
 * description:
 */
@RestController
public class CommonController  {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


    @RequestMapping("/search/{searchWorld}")
    public Wrapper search(@PathVariable String searchWorld) {
        MatchAllQueryBuilder matchAllQuery = QueryBuilders.matchAllQuery();
        SearchQuery query = new NativeSearchQuery(matchAllQuery);
        query.addIndices("_all");
        long count = elasticsearchTemplate.count(query);
        return WrapMapper.wrap(count);

    }
}
