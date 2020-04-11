package com.example.demo.system.es.esservice.impl;

import com.example.demo.system.es.esservice.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;


/**
 * @author 84271
 */
public class BaseServiceImpl< T, ID> implements BaseService<T, ID> {

    private ElasticsearchRepository<T,ID> baseJpa;

    public ElasticsearchRepository<T, ID> getBaseJpa() {
        return baseJpa;
    }

    public void setBaseJpa(ElasticsearchRepository<T, ID> baseJpa) {
        this.baseJpa = baseJpa;
    }

    @Override
    public T save(T t) {
        return baseJpa.save(t);
    }

    @Override
    public void delete(T t) {
        baseJpa.delete(t);
    }

    @Override
    public void deleteById(ID id) {
        baseJpa.deleteById(id);
    }

    @Override
    public Iterable<T> findAll() {
        return baseJpa.findAll();
    }

    @Override
    public Iterable<T> findAll(Sort sort) {
        return baseJpa.findAll(sort);
    }

    @Override
    public Optional<T> findById(ID id) {
        return baseJpa.findById(id);
    }

    @Override
    public Page<T> search(SearchQuery search) {
        return baseJpa.search(search);
    }
}
