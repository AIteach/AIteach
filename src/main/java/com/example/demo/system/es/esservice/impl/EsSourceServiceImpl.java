package com.example.demo.system.es.esservice.impl;

import com.example.demo.system.es.esdao.EsSourceJpa;
import com.example.demo.system.es.esentity.EsSource;
import com.example.demo.system.es.esservice.EsSourceService;

import javax.annotation.Resource;


/**
 * @author 84271
 */
public class EsSourceServiceImpl extends BaseServiceImpl<EsSource, Integer> implements EsSourceService {

    private EsSourceJpa esSourceJpa;

    @Resource
    public void setEsSourceJpa(EsSourceJpa esSourceJpa) {
        super.setBaseJpa(esSourceJpa);
        this.esSourceJpa = esSourceJpa;
    }
}
