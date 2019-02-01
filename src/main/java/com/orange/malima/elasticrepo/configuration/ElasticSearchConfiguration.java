package com.orange.malima.elasticrepo.configuration;


import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.TransportAddress;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.elasticsearch.client.NodeClientFactoryBean;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import javax.annotation.Resource;
import java.util.UUID;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.orange.malima.elasticrepo.repositories")
public class ElasticSearchConfiguration {

    @Bean
    public NodeClientFactoryBean client() {

        NodeClientFactoryBean bean = new NodeClientFactoryBean(true);
        bean.setClusterName(UUID.randomUUID().toString());
        bean.setEnableHttp(false);
        bean.setPathData("target/elasticsearchTestData");
        bean.setPathHome("src/test/resources/test-home-dir");

        return bean;
    }

    @Bean
    public ElasticsearchTemplate elasticsearchTemplate(Client client) throws Exception {
        return new ElasticsearchTemplate(client);
    }



}
