package com.example.demo.system.es.esdao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.example.demo.system.es.esentity.EsChapter;

public interface EsChapterJpa extends ElasticsearchRepository<EsChapter, Integer> {

}