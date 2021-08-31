package com.example.elasticsearch;

import com.example.elasticsearch.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Disabled
class ElasticsearchApplicationTest {

    /**
     * 默认索引
     */
    public static final String ELASTIC_SEARCH_INDEX = "test";


    /**
     * ElasticSearch 官方高级客户端
     */
    @Autowired
    private RestHighLevelClient client;

    /**
     * Spring Boot 封装的 ElasticSearch 客户端
     */
    @Autowired
    private ElasticsearchRestTemplate template;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void notNull() {
        assertNotNull(client);
        assertNotNull(template);
    }

    @SneakyThrows
    @Test
    void createIndex() {
        CreateIndexRequest request = new CreateIndexRequest(ELASTIC_SEARCH_INDEX);
        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(response);
    }

    @SneakyThrows
    @Test
    void getIndex() {
        GetIndexRequest request = new GetIndexRequest(ELASTIC_SEARCH_INDEX);
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        assertTrue(exists, "索引不存在");
    }

    @SneakyThrows
    @Test
    void deleteIndex() {
        DeleteIndexRequest request = new DeleteIndexRequest(ELASTIC_SEARCH_INDEX);
        AcknowledgedResponse response = client
                .indices()
                .delete(request, RequestOptions.DEFAULT);

        assertTrue(response.isAcknowledged(), "删除索引失败");
    }

    @SneakyThrows
    @Test
    void addDocument() {
        User user = new User()
                .setName("小明")
                .setAge(3);
        IndexRequest request = new IndexRequest(ELASTIC_SEARCH_INDEX);

        request
                .id("1")
                .source(objectMapper.writeValueAsString(user), XContentType.JSON);

        IndexResponse response = client.index(request, RequestOptions.DEFAULT);

        assertEquals(RestStatus.CREATED.getStatus(), response.status().getStatus(), "创建文档失败");
    }

    @SneakyThrows
    @Test
    void existAndGetDocument() {
        GetRequest request = new GetRequest(ELASTIC_SEARCH_INDEX, "1");
        boolean exists = client.exists(request, RequestOptions.DEFAULT);
        assertTrue(exists);

        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        String source = response.getSourceAsString();
        User user = objectMapper.readValue(source, User.class);
        assertEquals("小明", user.getName());
        assertEquals(3, user.getAge());
    }

    @SneakyThrows
    @Test
    void updateDocument() {
        User user = new User()
                .setName("李四")
                .setAge(20);
        UpdateRequest request = new UpdateRequest(ELASTIC_SEARCH_INDEX, "1")
                .doc(objectMapper.writeValueAsString(user), XContentType.JSON);

        UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
        assertEquals(RestStatus.OK, response.status());
    }


    @SneakyThrows
    @Test
    void deleteDocument() {
        DeleteRequest request = new DeleteRequest(ELASTIC_SEARCH_INDEX, "1");
        DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
        assertEquals(RestStatus.OK, response.status());
    }

    /**
     * 批量插入数据
     */
    @SneakyThrows
    @Test
    void bulkRequest() {
        List<User> users = Arrays.asList(
                new User().setName("张三").setAge(20),
                new User().setName("李四").setAge(12),
                new User().setName("王五").setAge(30),
                new User().setName("赵六").setAge(17)
        );

        //批处理请求
        BulkRequest request = new BulkRequest();
        for (int i = 0; i < users.size(); i++) {
            request.add(
                    new IndexRequest(ELASTIC_SEARCH_INDEX)
                            .id(i + 1 + "")
                            .source(objectMapper.writeValueAsString(users.get(i)), XContentType.JSON)
            );
        }

        BulkResponse response = client.bulk(request, RequestOptions.DEFAULT);
        assertEquals(RestStatus.OK, response.status());
    }

    @SneakyThrows
    @Test
    void search() {
        TermQueryBuilder query = QueryBuilders
                .termQuery("age", 20);

        SearchRequest request = new SearchRequest(ELASTIC_SEARCH_INDEX)
                .source(
                        new SearchSourceBuilder()
                                .query(query)
                );
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();
        assertEquals(1,hits.getTotalHits().value);
    }
}