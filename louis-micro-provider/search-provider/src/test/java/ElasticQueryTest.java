import org.apache.lucene.search.Query;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;

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

    public static final String preTags="<em>";
    public static final String postTags="</em>";








    private void executeSearch(Client client,QueryBuilder queryBuilder, String index, String type) {
        String queryName = queryBuilder.queryName();

        HighlightBuilder highlightBuilder = buildHighlight(queryName, preTags, postTags);

        SearchResponse response = client.prepareSearch(index)
                .setTypes(type)
                .setQuery(queryBuilder)
                .highlighter(highlightBuilder)
                .get();

        SearchHits searchHits = response.getHits();

        System.out.println("总记录数" + searchHits.getTotalHits());
        searchHits.forEach(searchHit->{
            System.out.println(searchHit.getSourceAsString());
            Map<String, Object> document = searchHit.getSourceAsMap();
            System.out.println(document.get("id"));
            System.out.println(document.get("title"));
            System.out.println(document.get("content"));
            Map<String, HighlightField> highlightFields = searchHit.getHighlightFields();
            highlightFields.forEach((s, highlightField) -> {
                Text[] fragments = highlightField.getFragments();
                if (fragments!=null) {
                    String highlight = fragments[0].toString();
                    System.out.println(highlight);

                }

            });
        });
    }

    private HighlightBuilder buildHighlight(String fieldName,String preTags,String postTags) {
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field(fieldName)
                .preTags(preTags)
                .postTags(postTags);

        return highlightBuilder;
    }

    //    创建一个QueryBuilders
//    使用client执行查询
//    得到查询结果
//    取得查询的总记录数量
//

    /**
     * 必须指定id
     * @param client
     */
    public void searchById(Client client,Integer from,Integer size) {
        QueryBuilder queryBuilder = QueryBuilders.idsQuery().addIds("1", "2");

        SearchResponse response = client.prepareSearch("index_hello")
                .setTypes("article")
                .setQuery(queryBuilder)
                .setFrom(from == null ? 0 : from)
                .setSize(size == null ? 10 : size)

                .get();
        SearchHits searchHits = response.getHits();

        System.out.println("总记录数" + searchHits.getTotalHits());
        searchHits.forEach(searchHit->{
            System.out.println(searchHit.getSourceAsString());
            Map<String, Object> document = searchHit.getSourceAsMap();
            System.out.println(document.get("id"));
            System.out.println(document.get("title"));
            System.out.println(document.get("content"));

        });
    }


    /**
     * 必须指定所查询的域
     * @param term
     * @param value
     * @return
     */
    public QueryBuilder searchByTerm(String term, String value) {
        return QueryBuilders.termQuery(term, value);
    }

    public QueryBuilder queryStringQuery(String queryString) {
        return QueryBuilders.queryStringQuery(queryString);
    }



}
