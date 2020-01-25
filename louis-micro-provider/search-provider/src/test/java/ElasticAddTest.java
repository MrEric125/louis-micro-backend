import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author John·Louis
 *  create at 2019/12/26
 * description:
 */
public class ElasticAddTest {


    public Client setting() throws UnknownHostException {
        Settings settings = Settings.builder().put("cluster.name", "elasticsearch")
                .build();
        return new PreBuiltTransportClient(settings)
                .addTransportAddresses(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9301));

    }

    /**
     * {
     *     "article":{
     *         "properties":{
     *             "id":{
     *                 "type":"long",
     *                 "store":true
     *             },
     *             "title":{
     *                 "type":"text",
     *                 "store":true,
     *                 "index":true,
     *                 "analyzer":"ik_smart"
     *
     *             },
     *            "content":{
     *                   "type":"text",
     *                   "store":true,
     *                   "index":true,
     *                   "analyzer":"ik_smart"
     *
     *           },
     *
     *         }
     *     }
     * }
     */
    public XContentBuilder setMapptings() throws IOException {
        XContentBuilder xContentBuilder= XContentFactory.jsonBuilder()
                .startObject()
                    .startObject("article")
                        .startObject("properties")
                            .startObject("id")
                                .field("type","long")
                                .field("store","true")
                            .endObject()
                            .startObject("title")
                                .field("type","long")
                                .field("store","true")
                                .field("index","true")
                                .field("analyzer","ik_smart")
                            .endObject()
                            .startObject("content")
                                .field("store","long")
                                .field("store","true")
                                .field("type","true")
                                .field("analyzer","ik_smart")
                            .endObject()
                        .endObject()
                    .endObject()
                .endObject();
        return xContentBuilder;
    }

    public void setIndices(Client client) throws IOException {
        client.admin().indices()
                .preparePutMapping("index_hello")
                .setType("article")
//                mapping 信息，也可以是json格式的字符串
                .setSource(setMapptings())
                .get();
        client.close();

    }
    public void addDocument(Client client) throws IOException {
        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject()
                .field("id", "1L")
                .field("title", "dsss")
                .field("content", "ggg")
                .endObject();
        client.prepareIndex()
                .setIndex("index_hello")
                .setType("article")
                .setId("sdf")
                .setSource(builder)
                .get();
    }

    /**
     * 以json的格式添加文档
     * @param client
     * @throws JsonProcessingException
     */
    public void addDocument2(Client client) throws JsonProcessingException {
        Article article = Article.builder().id(2L).content("content").title("title").build();

        ObjectMapper objectMapper = new ObjectMapper();
        String value = objectMapper.writeValueAsString(article);
        client.prepareIndex("index_hello", "article", "3")
                .setSource(value, XContentType.JSON);
    }
}
