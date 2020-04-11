package com.example.demo.system.es.esdao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.example.demo.system.es.esentity.EsSchool;

/**
 * @author 84271
 */
public interface EsSchoolJpa extends ElasticsearchRepository<EsSchool, Integer> {
}
