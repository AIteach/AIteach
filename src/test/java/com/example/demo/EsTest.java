package com.example.demo;

import java.util.List;

import javax.annotation.Resource;

import com.example.demo.system.es.esdao.EsNodeJpa;
import com.example.demo.system.es.esentity.*;
import com.example.demo.system.mysql.entity.Node;
import com.example.demo.system.mysql.service.impl.NodeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.system.es.esservice.impl.EsCommentService;
import com.example.demo.system.es.esservice.impl.EsSchoolService;
import com.example.demo.system.es.esservice.impl.EsSourceService;
import com.example.demo.system.mysql.entity.Comment;
import com.example.demo.system.mysql.entity.School;
import com.example.demo.system.mysql.entity.Source;
import com.example.demo.system.mysql.service.impl.CommentService;
import com.example.demo.system.mysql.service.impl.SchoolService;
import com.example.demo.system.mysql.service.impl.SourceService;

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
    private SourceService kgse;

    @Resource
    private EsSourceService eskg;

    @Test
    public void fjeijwq() {
        List<Source> comments = kgse.findAll();
        for (Source i : comments) {
            EsSource escomment = new EsSource();
            escomment.change(i);
            eskg.save(escomment);
        }
    }

    @Test
    public void fjeijwqdd() {
        Iterable<EsComment> comments = escommentservice.findall();
        for (EsComment i : comments) {
            System.out.println(i);
        }
    }

//	@Test
//	public void ejwqjf() {
//		Page<EsComment> comments = escommentservice.search("真好", SortBuilders.fieldSort("ctime").order(SortOrder.ASC));
//		long total = comments.getTotalElements();
//		System.out.println(total);
//		for (EsComment I : comments) {
//			System.out.println(I);
//		}
//	}


    @Resource
    private NodeService nodeService;

    @Resource
    private EsNodeJpa esNodeJpa;

    @Test
    public void saveEsnode() {
        List<Node> all = nodeService.findAll();
        for (Node node : all) {
            EsNode esNode = new EsNode();
            esNode.setCategory(node.getCategory());
            esNode.setId(node.getId());
            esNode.setName(node.getName());
            esNode.setSymbolSize(node.getSymbolSize());
            esNode.setUrl(node.getUrl());
            esNode.setValue(node.getValue());
            esNodeJpa.save(esNode);
        }
    }


    @Test
    public void finfjfj() {
        Iterable<EsNode> all = esNodeJpa.findAll();
        for (EsNode esNode : all)
            System.out.println(esNode);
    }


    @Test
    public void test222() {
        List<Node> all = nodeService.findAll();
        for (Node node : all) {
            System.out.println(node);
        }
    }
}
