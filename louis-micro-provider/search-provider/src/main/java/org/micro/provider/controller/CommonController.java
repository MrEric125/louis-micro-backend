package org.micro.provider.controller;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.micro.common.api.wrapper.MapperWrap;
import org.micro.common.api.wrapper.Wrapper;
import org.micro.provider.SearchConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author John·Louis
 * created on  2020/1/25
 *
 * description:
 * 方便自定义全局查询功能，
 */
@RestController
@RequestMapping("/es")
public class CommonController  {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


    /**
     * todo 后期优化分页功能
     * @param pageable 分页信息
     * @param index 查询索引
     * @param searchWorld 查询关键词
     * @return entity
     */
    @RequestMapping("/search/{searchWorld}/{index}")
    public Wrapper search(@RequestParam(required = false) Pageable pageable,
                          @PathVariable String searchWorld,
                          @PathVariable(required = false) String index) {

        QueryStringQueryBuilder builder = QueryBuilders.queryStringQuery(searchWorld);

        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withIndices(Objects.isNull(index) ? SearchConstant.SEARCH_ALL : index)
                .withQuery(builder)
                .withPageable(PageRequest.of(0, 10))
                .build();
        List<Map<String, Object>> collect = Arrays.stream(elasticsearchTemplate.query(query, SearchResponse::getHits).getHits())
                .map(SearchHit::getSourceAsMap).collect(Collectors.toList());
        return MapperWrap.wrap(collect);

    }
}
