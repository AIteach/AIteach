package com.example.demo;

import com.example.demo.system.es.esentity.EsChapter;
import com.example.demo.system.es.esentity.EsKg;
import com.example.demo.system.es.esservice.*;
import com.example.demo.system.mysql.service.impl.*;
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
    private ChapterService chapterService;
    @Resource
    private NodeService nodeService;
    @Resource
    private EsNodeService esNodeService;
    @Resource
    private KgService kgService;
    @Resource
    private EsChapterService esChapterService;

    @Resource
    private SchoolService schoolService;
    @Resource
    private EsSchoolService esSchoolService;

    @Resource
    private CourseService courseService;
    @Resource
    private EsCourseService esCourseService;

    @Resource
    private EsCommentService esCommentService;
    @Resource
    private EsSourceService esSourceService;

    @Resource
    private EsKgService esKgService;

    @Test
    public void test2() {
        escommentservice.findAll().forEach(e -> System.out.println(e));
    }


    @Test
    public void test() {
        commentService.findAll().forEach(e -> {
            esCommentService.save(e.toEs());
        });
        sourceService.findAll().forEach(e -> {
            esSourceService.save(e.toEsSource());
        });
        kgService.findAll().forEach(e -> {
            esKgService.save(e.toEs());
        });

    }

}
