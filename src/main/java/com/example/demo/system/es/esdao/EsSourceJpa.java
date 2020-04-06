package com.example.demo.system.es.esdao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.example.demo.system.es.esentity.EsSource;

public interface EsSourceJpa extends ElasticsearchRepository<EsSource, Integer> {
}
