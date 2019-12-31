package org.micro.search.controller;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.micro.common.api.wrapper.WrapMapper;
import org.micro.common.api.wrapper.Wrapper;
import org.micro.search.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        QueryBuilder queryBuilder = QueryBuilders.queryStringQuery(searchWorld);
//        NativeSearchQuery query = new NativeSearchQuery(matchAllQuery);
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .withPageable(PageRequest.of(0, 10))
                .build();
        List<Article> articleList = elasticsearchTemplate.queryForList(query, Article.class);
        return WrapMapper.wrap(articleList);

    }
}
