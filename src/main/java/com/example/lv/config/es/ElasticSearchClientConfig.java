package com.example.lv.config.es;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description es 配置类
 * @date 2023/6/23 09:41:07
 */
@Configuration
public class ElasticSearchClientConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticSearchClientConfig.class);

    @Value("${elasticsearch.host}")
    private String host;

    @Value("${elasticsearch.port}")
    private int port;

    @Value("${elasticsearch.connTimeout}")
    private int connTimeout;

    @Value("${elasticsearch.socketTimeout}")
    private int socketTimeout;

    @Value("${elasticsearch.connectionRequestTimeout}")
    private int connectionRequestTimeout;

    @Bean(destroyMethod = "close", name = "client")
    public RestHighLevelClient initRestClient() {
        LOGGER.info("host=="+host+"port=="+port+"connTimeout=="+connTimeout+"socketTimeout"+socketTimeout+
                "connectionRequestTimeout"+connectionRequestTimeout);
        RestClientBuilder builder = RestClient.builder(new HttpHost(host, port))
                .setRequestConfigCallback(requestConfigBuilder -> requestConfigBuilder
                        .setConnectTimeout(connTimeout)
                        .setSocketTimeout(socketTimeout)
                        .setConnectionRequestTimeout(connectionRequestTimeout));
        return new RestHighLevelClient(builder);
    }




}
