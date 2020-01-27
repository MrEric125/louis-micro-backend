import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author John·Louis
 *  created at 2019/7/9
 * description:
 *
 *  1.创建一个Settings 对象 相当于一个配置信息，主要配置集群信息
 *  2. 创建一个客户端client
 *  3.shiyong client 对象创建一个索引库
 *  4，关闭client 对象
 *
 */
public class EsInitDemo {


    Settings settings = Settings.builder().build();

    public static final String INDEX = "article";

    TransportClient client = null;

    @Before
    public void before() {
        try {
            settings = Settings.builder().put("cluster.name", "docker-cluster").build();
            client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new TransportAddress
                            (InetAddress.getByName("localhost"), 9300));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建索引
     */
    @Test
    public void createIndex() {
        client.admin()
                .indices()
                .prepareCreate(INDEX)
                //执行操作
                .get();
    }

    /**
     * 创建mapping
     */
    @Test
    public void createMapping() {
        try {
            XContentBuilder contentBuilder = XContentFactory.jsonBuilder()
                    .startObject()
                        .startObject("license")
                            .startObject("properties")
                                .startObject("id")
                                    .field("type", "long")
                                     .field("store", true)
                                .endObject()
                                .startObject("title")
                                    .field("type","text")
                                    .field("store",true)
//                                    .field("analyzer","ik_smart")
                                .endObject()
                                .startObject("content")
                                    .field("type","text")
                                    .field("store",true)
//                                    .field("analyzer","ik_smart")
                                .endObject()
                            .endObject()
                        .endObject()
                    .endObject();

            client.admin().indices()
//                    设置要做映射的索引
                    .preparePutMapping("article")
//                    设置type
                    .setType("license")
                    .setSource(contentBuilder)

                    .get();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }



    @Test
    public void createDocument() {
        try {
            XContentBuilder builder = XContentFactory
                    .jsonBuilder()
                    .startObject()
                    .field("id", 1)
                    .field("title", "this is titile")
                    .field("content", "this is content")
                    .endObject();

            client.prepareIndex()
                    .setIndex(INDEX)
                    .setType("article")
                    .setId("1")
//                    设置文档信息
                    .setSource(builder)
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 批量产生测试数据
     */
    @Test
    public void creteDocumentByDomain() {
        ObjectMapper objectMapper = new ObjectMapper();
        LicenseWord licenseWord = new LicenseWord();
        licenseWord.initList(null);
        int size = licenseWord.list.size();
        int id = 0;


        for (int i = 0; i <size-1 ; i=i+2) {
            Article article = Article
                    .builder()
                    .id(id)
                    .title(licenseWord.getContentWord(i))
                    .content(licenseWord.getContentWord(i+1))
                    .build();

            try {
                String string = objectMapper.writeValueAsString(article);
                client.prepareIndex().setIndex(INDEX)
                        .setType("license")
                        .setId(String.valueOf(id))
                        .setSource(string, XContentType.JSON).get();
                System.out.println(string);
//                Thread.sleep();


            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }finally {
                id++;
            }

        }


    }


    @After
    public void after() {
        client.close();

    }

}
