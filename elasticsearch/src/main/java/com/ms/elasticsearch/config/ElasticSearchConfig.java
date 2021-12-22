package com.ms.elasticsearch.config;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.nio.protocol.HttpAsyncResponseConsumer;
import org.elasticsearch.client.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticSearchConfig {
    public static final RequestOptions COMMON_OPTIONS;
    static {
        RequestOptions.Builder builder=RequestOptions.DEFAULT.toBuilder();
        //集群时候用到
       // builder.addHeader("Authorization","Bearer"+"TOKEN");
       // builder.setHttpAsyncResponseConsumerFactory(new HttpAsyncResponseConsumerFactory.HeapBufferedResponseConsumerFactory(30*1024*1024*1024));
        COMMON_OPTIONS=builder.build();
    }

    @Bean
    public RestHighLevelClient esRestClient(){
        RestHighLevelClient restHighLevelClient= new RestHighLevelClient(
                //多个节点 用逗号隔开
                //RestClient.builder(new HttpHost("42.194.149.227",9201,"http"),new HttpHost("42.194.149.227",9201,"http")));
                RestClient.builder(new HttpHost("42.194.149.227",9201,"http")));
        return restHighLevelClient;
    }
}
