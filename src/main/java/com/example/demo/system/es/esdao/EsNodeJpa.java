package com.example.demo.system.es.esdao;

import com.example.demo.system.es.esentity.EsChapter;
import com.example.demo.system.es.esentity.EsNode;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EsNodeJpa extends ElasticsearchRepository<EsNode, Integer> {

}
