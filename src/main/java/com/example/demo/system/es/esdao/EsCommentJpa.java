package com.example.demo.system.es.esdao;

import java.util.Date;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.example.demo.system.es.esentity.EsComment;



public interface EsCommentJpa extends ElasticsearchRepository<EsComment, Integer> {
	Iterable<EsComment> findByCtimeBetween(Date date1, Date date2);
}
