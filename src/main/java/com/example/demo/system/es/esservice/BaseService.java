package com.example.demo.system.es.esservice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.Optional;


/**
 * @author 84271
 */
public interface BaseService<T, ID> {

    T save(T t);

    void delete(T t);

    void deleteById(ID id);

    Iterable<T> findAll();

    Iterable<T> findAll(Sort sort);

    Optional<T> findById(ID id);

    Page<T> search(SearchQuery search);
}
