package com.example.demo.system.es.esdao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.example.demo.system.es.esentity.EsKg;

public interface EsKgJpa extends ElasticsearchRepository<EsKg, Integer> {
}
