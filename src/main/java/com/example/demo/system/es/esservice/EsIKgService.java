package com.example.demo.system.es.esservice;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import com.example.demo.system.es.esentity.EsKg;

public interface EsIKgService {
	EsKg save(EsKg item);

	void delete(EsKg item);

	Iterable<EsKg> findall();

	Iterable<EsKg> findall(Sort sort);

	Optional<EsKg> findone(Integer id);

	Page<EsKg> search(SearchQuery search);

}
