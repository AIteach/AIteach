package com.example.demo.system.es.esservice.impl.test;

import com.example.demo.system.es.esdao.EsNodeJpa;
import com.example.demo.system.es.esentity.EsNode;
import com.example.demo.system.es.esservice.EsNodeService;

public class EsNodeServic1 extends BaseServiceImpl2<EsNodeJpa, EsNode, Integer> implements EsNodeService {


   /*@Resource
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
    public void deleteById(Integer id) {
        esNodeJpa.deleteById(id);
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
    }*/
}
