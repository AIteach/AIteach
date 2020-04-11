package com.example.demo;

import com.example.demo.system.es.esentity.EsChapter;
import com.example.demo.system.es.esentity.EsKg;
import com.example.demo.system.es.esservice.EsCommentService;
import com.example.demo.system.mysql.service.impl.CommentService;
import com.example.demo.system.mysql.service.impl.KgService;
import com.example.demo.system.mysql.service.impl.SourceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EsTest {

    @Autowired
    ElasticsearchTemplate esTemplate;

    @Test
    public void testCutForSearch() {

        esTemplate.deleteIndex(EsChapter.class);

        // 创建索引，会根据Item类的@Document注解信息来创建
        esTemplate.createIndex(EsKg.class);
        // 配置映射，会根据Item类中的id、Field等字段来自动完成映射
        esTemplate.putMapping(EsKg.class);
    }

    @Resource
    private CommentService commentService;

    @Resource
    private EsCommentService escommentservice;
    @Resource
    private SourceService sourceService;


    @Resource
    private KgService kgService;


    @Test
    public void test2() {
        escommentservice.findAll().forEach(e -> System.out.println(e));
    }


}
