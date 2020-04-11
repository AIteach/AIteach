package com.example.demo.system.es.esservice.impl.test;


import com.example.demo.system.es.esservice.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;


public class BaseServiceImpl2<J extends ElasticsearchRepository<T, ID>, T, ID> implements BaseService<T, ID> {

    public final static String SAVE = "Save";
    public final static String DELETE = "Delete";

    public J j;

    public J getJ() {
        return j;
    }

    public void setJ(J j) {
        this.j = j;
    }

    @Override
    public T save(T t) {
        return j.save(t);
    }

    @Override
    public void delete(T t) {
        j.delete(t);
    }

    @Override
    public void deleteById(ID id) {
        j.deleteById(id);
    }

    @Override
    public Iterable<T> findAll() {
        return j.findAll();
    }

    @Override
    public Iterable<T> findAll(Sort sort) {
        return j.findAll(sort);
    }

    @Override
    public Optional<T> findById(ID id) {
        return j.findById(id);
    }

    @Override
    public Page<T> search(SearchQuery search) {
        return j.search(search);
    }
}
