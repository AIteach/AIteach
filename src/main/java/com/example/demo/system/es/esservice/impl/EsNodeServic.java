package com.example.demo.system.es.esservice.impl;

import com.example.demo.system.es.esdao.EsNodeJpa;
import com.example.demo.system.es.esentity.EsNode;
import com.example.demo.system.es.esservice.EsINodeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class EsNodeServic implements EsINodeService {

    @Resource
    private EsNodeJpa esNodeJpa;

    @Override
    public EsNode save(EsNode esNode) {
        return esNodeJpa.save(esNode);
    }

    @Override
    public void delete(EsNode esNode) {
        esNodeJpa.delete(esNode);
    }

    @Override
    public Iterable<EsNode> findAll() {
        return esNodeJpa.findAll();
    }

    @Override
    public Iterable<EsNode> findAll(Sort sort) {
        return esNodeJpa.findAll(sort);
    }

    @Override
    public Optional<EsNode> findById(Integer integer) {
        return esNodeJpa.findById(integer);
    }

    @Override
    public Page<EsNode> search(SearchQuery search) {
        return esNodeJpa.search(search);
    }
}
