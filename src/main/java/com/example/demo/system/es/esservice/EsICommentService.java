package com.example.demo.system.es.esservice;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import com.example.demo.system.es.esentity.EsComment;

public interface EsICommentService {
	EsComment save(EsComment item);

	void delete(EsComment item);

	Iterable<EsComment> findall();

	Iterable<EsComment> findall(Sort sort);

	Optional<EsComment> findone(Integer id);

	Iterable<EsComment> findByCtimeBetween(Date date1, Date date2);

	Page<EsComment> search(SearchQuery search);

}
