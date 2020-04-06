package com.example.demo.system.es.esservice;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import com.example.demo.system.es.esentity.EsSchool;

public interface EsISchoolService {
	EsSchool save(EsSchool item);

	void delete(EsSchool item);

	Iterable<EsSchool> findall();

	Iterable<EsSchool> findall(Sort sort);

	Optional<EsSchool> findone(Integer id);

	Page<EsSchool> search(SearchQuery search);

}
