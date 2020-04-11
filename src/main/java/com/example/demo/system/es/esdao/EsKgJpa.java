package com.example.demo.system.es.esdao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.example.demo.system.es.esentity.EsKg;

/**
 * @author 84271
 */
public interface EsKgJpa extends ElasticsearchRepository<EsKg, Integer> {
}
