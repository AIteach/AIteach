package com.example.demo.system.es.esdao;

import com.example.demo.system.es.esentity.EsChapter;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author 84271
 */

public interface EsChapterJpa extends ElasticsearchRepository<EsChapter, Integer> {

}
