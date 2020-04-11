package com.example.demo.system.es.esservice.impl;

import com.example.demo.system.es.esdao.EsCommentJpa;
import com.example.demo.system.es.esentity.EsComment;
import com.example.demo.system.es.esservice.EsCommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author 84271
 */
@Service
public class EsCommentServiceImpl extends BaseServiceImpl<EsComment, Integer> implements EsCommentService {

    private EsCommentJpa esCommentJpa;

    @Resource
    public void setEsCommentJpa(EsCommentJpa esCommentJpa) {
        super.setBaseJpa(esCommentJpa);
        this.esCommentJpa = esCommentJpa;
    }
}
