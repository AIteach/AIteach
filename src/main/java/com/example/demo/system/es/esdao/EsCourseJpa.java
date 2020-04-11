package com.example.demo.system.es.esdao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.example.demo.system.es.esentity.EsCourse;

/**
 * @author 84271
 */
public interface EsCourseJpa extends ElasticsearchRepository<EsCourse, Integer> {
}
