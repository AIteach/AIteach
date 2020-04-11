package com.example.demo.system.es.esdao;

import com.example.demo.system.es.esentity.EsComment;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Date;



/**
 * @author 84271
 */
public interface EsCommentJpa extends ElasticsearchRepository<EsComment, Integer> {
	Iterable<EsComment> findByCtimeBetween(Date date1, Date date2);
}
