package com.example.demo.system.es.esservice;

import com.example.demo.system.es.esentity.EsChapter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.Optional;

public interface BaseService<T, ID> {
    T save(T t);

    void delete(T t);

    Iterable<T> findAll();

    Iterable<T> findAll(Sort sort);

    Optional<T> findById(ID id);

    Page<T> search(SearchQuery search);
}
