package com.example.demo.system.es.esservice.impl;

import com.example.demo.system.es.esdao.EsCourseJpa;
import com.example.demo.system.es.esentity.EsCourse;
import com.example.demo.system.es.esservice.EsCourseService;

import javax.annotation.Resource;

/**
 * @author 84271
 */
@Resource
public class EsCourseServiceImpl extends BaseServiceImpl<EsCourse, Integer> implements EsCourseService {
    private EsCourseJpa esCourseJpa;

    @Resource
    public void setEsCourseJpa(EsCourseJpa esCourseJpa) {
        super.setBaseJpa(esCourseJpa);
        this.esCourseJpa = esCourseJpa;
    }
}
