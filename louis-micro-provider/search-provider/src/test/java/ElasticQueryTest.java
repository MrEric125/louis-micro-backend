import org.apache.lucene.search.Query;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryShardContext;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.elasticsearch.search.SearchHits;

import java.io.IOException;
import java.util.Map;

/**
 * @author John·Louis
 * @date create in 2019/12/26
 * description:
 * 1.根据id查询
 * 2. 根据关键字（term）查询
 * 3.QueryString查询
 */
public class ElasticQueryTest {

    //    创建一个QueryBuilders
//    使用client执行查询
//    得到查询结果
//    取得查询的总记录数量
//
    public void searchById(Client client) {
        QueryBuilder queryBuilder = QueryBuilders.idsQuery().addIds("1", "2");

        SearchResponse response = client.prepareSearch("index_hello")
                .setTypes("article")
                .setQuery(queryBuilder)
                .get();
        SearchHits searchHits = response.getHits();

        System.out.println("总记录数" + searchHits.getTotalHits());
        searchHits.forEach(x->{
            System.out.println(x.getSourceAsString());
            Map<String, Object> document = x.getSourceAsMap();
            System.out.println(document.get("id"));
            System.out.println(document.get("title"));
            System.out.println(document.get("content"));


        });
    }


}
