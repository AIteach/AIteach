package com.example.demo.system.es.esdao;

import com.example.demo.system.es.esentity.EsNode;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author 84271
 */
public interface EsNodeJpa extends ElasticsearchRepository<EsNode, Integer> {

}
