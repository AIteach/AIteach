package com.example.demo.system.es.esdao;

import com.example.demo.system.es.esentity.EsNode;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 84271
 */
@Repository
public interface EsNodeJpa extends ElasticsearchRepository<EsNode, Integer> {

}
