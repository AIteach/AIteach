package com.example.demo.system.es.esservice.impl;

import com.example.demo.system.es.esdao.EsNodeJpa;
import com.example.demo.system.es.esentity.EsNode;
import com.example.demo.system.es.esservice.EsNodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 84271
 */
@Service
public class EsNodeServiceImpl extends BaseServiceImpl<EsNode, Integer> implements EsNodeService {
    private EsNodeJpa esNodeJpa;

    @Resource
    public void setEsNodeJpa(EsNodeJpa esNodeJpa) {
        super.setBaseJpa(esNodeJpa);
        this.esNodeJpa = esNodeJpa;
    }
}
