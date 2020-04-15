package com.example.demo;

import com.example.demo.system.es.esservice.EsNodeService;
import com.example.demo.system.mysql.entity.Node;
import com.example.demo.system.mysql.service.impl.ChapterService;
import com.example.demo.system.mysql.service.impl.CourseService;
import com.example.demo.system.mysql.service.impl.KgService;
import com.example.demo.system.mysql.service.impl.NodeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MqTest {
    @Resource
    private NodeService nodeService;
    @Resource
    private EsNodeService esNodeService;
    @Resource
    private ChapterService chapterService;
    @Resource
    private CourseService courseService;
    @Resource
    private KgService kgService;

    @Test
    public void test222() {
        courseService.findAll().forEach(course -> courseService.save(course));
    }

    @Test
    public void test333() {
        kgService.findAll().forEach(kg -> {
            System.out.println(kg);
            kgService.save(kg);
        });

    }

    @Test
    public void test33() {
        chapterService.findAll().forEach(chapter -> chapterService.save(chapter));
    }

    @Test
    public void test44() {
        chapterService.findAll().forEach(chapter -> chapterService.delete(chapter.getChapterId()));
    }

    @Test
    public void test11() {
        nodeService.findAll().forEach(node -> nodeService.deleteById(node.getId()));
    }

    @Test
    public void test1() {
        nodeService.findAll().forEach(node -> nodeService.saveNode(node));
    }


    @Test
    public void test3() throws InterruptedException {
        List<Node> all = nodeService.findAll();
        all.forEach(e -> nodeService.deleteById(e.getId()));
        Thread.sleep(5000);
    }

    @Test
    public void test4() {
        esNodeService.findAll().forEach(e -> System.out.println(e));
    }

}
