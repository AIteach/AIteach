package com.example.demo.system.es.esservice;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import com.example.demo.system.es.esentity.EsCourse;

public interface EsICourseService {
	EsCourse save(EsCourse item);

	void delete(EsCourse item);

	Iterable<EsCourse> findall();

	Iterable<EsCourse> findall(Sort sort);

	Optional<EsCourse> findone(Integer id);

	Page<EsCourse> search(SearchQuery search);

}
