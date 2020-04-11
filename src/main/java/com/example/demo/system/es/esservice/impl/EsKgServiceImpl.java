package com.example.demo.system.es.esservice.impl;


import com.example.demo.system.es.esdao.EsKgJpa;
import com.example.demo.system.es.esentity.EsKg;
import com.example.demo.system.es.esservice.EsKgService;

import javax.annotation.Resource;

/**
 * @author 84271
 */
public class EsKgServiceImpl extends BaseServiceImpl<EsKg, Integer> implements EsKgService {
    private EsKgJpa esKgJpa;

    @Resource
    public void setEsKgJpa(EsKgJpa esKgJpa) {
        super.setBaseJpa(esKgJpa);
        this.esKgJpa = esKgJpa;
    }

}
