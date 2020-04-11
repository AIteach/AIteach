package com.example.demo.system.es.esservice.impl;

import com.example.demo.system.es.esdao.EsSchoolJpa;
import com.example.demo.system.es.esentity.EsSchool;
import com.example.demo.system.es.esservice.EsSchoolService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author 84271
 */
@Service
public class EsSchoolServiceImpl extends BaseServiceImpl<EsSchool, Integer> implements EsSchoolService {

    private EsSchoolJpa esSchoolJpa;

    @Resource
    public void setEsSchoolJpa(EsSchoolJpa esSchoolJpa) {
        super.setBaseJpa(esSchoolJpa);
        this.esSchoolJpa = esSchoolJpa;
    }

}
