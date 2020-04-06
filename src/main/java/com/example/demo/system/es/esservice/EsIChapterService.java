package com.example.demo.system.es.esservice;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import com.example.demo.system.es.esentity.EsChapter;

public interface EsIChapterService {
	EsChapter save(EsChapter item);

	void delete(EsChapter item);

	Iterable<EsChapter> findall();

	Iterable<EsChapter> findall(Sort sort);

	Optional<EsChapter> findone(Integer id);

	Page<EsChapter> search(SearchQuery search);

}
