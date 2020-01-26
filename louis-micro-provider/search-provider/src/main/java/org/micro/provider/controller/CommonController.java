package org.micro.provider.controller;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.micro.common.api.wrapper.MapperWrap;
import org.micro.common.api.wrapper.Wrapper;
import org.micro.provider.entity.Article;
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
 * created on  2020/1/25
 *
 * description:
 */
@RestController
public class CommonController  {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


    @RequestMapping("/search/{searchWorld}")
    public Wrapper search(@PathVariable String searchWorld) {

        QueryBuilder builder = QueryBuilders.queryStringQuery(searchWorld);
//        NativeSearchQuery query = new NativeSearchQuery(matchAllQuery);
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(builder)
                .withPageable(PageRequest.of(0, 10))
                .build();
        List<Article> articleList = elasticsearchTemplate.queryForList(query, Article.class);
        return MapperWrap.wrap(articleList);

    }
}
