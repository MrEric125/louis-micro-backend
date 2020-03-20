package org.micro.search.common;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author louis
 * <p>
 * Date: 2019/12/30
 * Description:
 */
@Service
public class SearchByAllIndex implements SearchStrategy<SearchAllIndexResponse,CommonSearchRequest>, Serializable {

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public SearchAllIndexResponse execute(CommonSearchRequest request) {
        QueryBuilder queryBuilder = QueryBuilders.queryStringQuery(request.getSearchString());

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withIndices("_all")
                .withQuery(queryBuilder)
                .build();
        AggregatedPage<Object> aggregatedPage = elasticsearchTemplate.queryForPage(searchQuery, null);
        List<Object> list = aggregatedPage.get().collect(Collectors.toList());
        SearchAllIndexResponse response = new SearchAllIndexResponse();
        response.setResult(list);
        return response;

    }
}
