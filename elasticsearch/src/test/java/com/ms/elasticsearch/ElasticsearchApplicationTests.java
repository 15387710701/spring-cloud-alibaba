package com.ms.elasticsearch;

import com.alibaba.fastjson.JSON;
import com.ms.elasticsearch.config.ElasticSearchConfig;
import lombok.Data;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class ElasticsearchApplicationTests {
    @Autowired
    RestHighLevelClient client;
    //��ѯȫ�� ��ƽ������
    @Test
    void search() throws IOException {
        //������������
        SearchRequest searchRequest=new SearchRequest();
        //�ƶ�����
        searchRequest.indices("customer");

        //�ƶ�DSL��������
        SearchSourceBuilder sourceBuilder=new SearchSourceBuilder();
        //��������
        //sourceBuilder.query(QueryBuilders.matchQuery("name","ΰ"));
        TermsAggregationBuilder aggregationBuilder = AggregationBuilders.terms("ageAgg").field("age").size(10);
        sourceBuilder.aggregation(aggregationBuilder);
        searchRequest.source(sourceBuilder);
        System.out.println("sourceBuilder--->"+sourceBuilder.toString());



        SearchResponse index = client.search(searchRequest, ElasticSearchConfig.COMMON_OPTIONS);
        System.out.println("SearchResponse-->"+index.toString());
        SearchHits hits = index.getHits();
        System.out.println(Arrays.toString(hits.getHits()));
    }
    // es��������
    @Test
    void contextLoads() throws IOException {
        System.out.println("�ƻ�");
        IndexRequest indexRequest=new IndexRequest("customer");
        indexRequest.id("12"); //���ݵ�id

        User user = new User();
        user.setAge(12);
        user.setName("hhh");
        String jsonString = JSON.toJSONString(user);
        //��һ��
        IndexRequest request = indexRequest.source(jsonString, XContentType.JSON);//�����������json����

        //�ڶ���
        //indexRequest.source("name","������","age",18);
        System.out.println(request);
        IndexResponse index = client.index(indexRequest, ElasticSearchConfig.COMMON_OPTIONS);
        System.out.println(index);
    }

    /**
     * �������浽es
     */
    @Test
    void batchSave() throws IOException {
        List<User> users=new ArrayList<>();
        User user = new User();
        user.setId(1l);
        user.setAge(12);
        user.setName("user");
        User user1 = new User();
        user1.setId(2l);
        user1.setAge(16);
        user1.setName("user1");
        User user2 = new User();
        user2.setId(3l);
        user2.setAge(32);
        user2.setName("user2");
        users.add(user);
        users.add(user1);
        users.add(user2);
        //���������������
        BulkRequest bulkRequest = new BulkRequest();
        for (User userEntity : users) {
            IndexRequest indexRequest=new IndexRequest("user");
            indexRequest.id(userEntity.getId().toString());
            String string = JSON.toJSONString(userEntity);
            indexRequest.source(string,XContentType.JSON);
            bulkRequest.add(indexRequest);
        }
        BulkResponse bulkResponse = client.bulk(bulkRequest, ElasticSearchConfig.COMMON_OPTIONS);

        //����д���
        boolean b = bulkResponse.hasFailures();
        List<String> list = Arrays.stream(bulkResponse.getItems()).map(it -> {
            return it.getId();
        }).collect(Collectors.toList());
        System.out.println("��Ʒ����-->"+list);
    }

    @Data
    class User{
        private Long id;
        private String name;
        private Integer age;
    }


}
