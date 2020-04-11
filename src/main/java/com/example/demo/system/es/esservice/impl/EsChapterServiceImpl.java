package com.example.demo.system.es.esservice.impl;

import com.example.demo.system.es.esdao.EsChapterJpa;
import com.example.demo.system.es.esentity.EsChapter;
import com.example.demo.system.es.esservice.EsChapterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author 84271
 */
@Service
public class EsChapterServiceImpl extends BaseServiceImpl<EsChapter, Integer> implements EsChapterService {

    private EsChapterJpa esChapterJpa;

    @Resource
    public void setEsChapterJpa(EsChapterJpa esChapterJpa) {
        super.setBaseJpa(esChapterJpa);
        this.esChapterJpa = esChapterJpa;
    }


}
