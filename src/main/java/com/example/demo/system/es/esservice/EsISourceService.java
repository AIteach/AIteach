package com.example.demo.system.es.esservice;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import com.example.demo.system.es.esentity.EsSource;

public interface EsISourceService {
	EsSource save(EsSource item);

	void delete(EsSource item);

	Iterable<EsSource> findall();

	Iterable<EsSource> findall(Sort sort);

	Optional<EsSource> findone(Integer id);

	Page<EsSource> search(SearchQuery search);

}
