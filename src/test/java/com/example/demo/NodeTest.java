package com.example.demo;

import com.example.demo.system.es.esentity.EsNode;
import com.example.demo.system.es.esservice.EsNodeService;
import com.example.demo.system.es.esservice.impl.test.EsNodeServic1;
import com.example.demo.system.mysql.entity.Linking;
import com.example.demo.system.mysql.entity.Node;
import com.example.demo.system.mysql.service.impl.LinkingService;
import com.example.demo.system.mysql.service.impl.NodeService;
import com.example.demo.utils.EsUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class NodeTest {
    @Resource
    private NodeService nodeService;

    @Resource
    private EsNodeService esNodeServic;

    @Resource
    private LinkingService linkingService;

    @Test
    public void test1() {
        List<Node> all = nodeService.findAll();
        for (Node node : all)
            System.out.println(node);
    }

    @Test
    public void test2() {
        Page<EsNode> search = esNodeServic.search(EsUtil.getSearchQuery("name", "系统"));
        System.out.println(search.getTotalElements());
        System.out.println(search.getTotalPages());
        List<EsNode> content = search.getContent();

        for (EsNode esNode : content)
            System.out.println(esNode);
    }

    @Test
    public void test3() {
        List<Linking> allByPreIdOrRearId = linkingService.findAllByPreIdOrRearId(2);
        for (Linking linking : allByPreIdOrRearId)
            System.out.println(linking);
    }

    @Resource
    private EsNodeServic1 esNodeServic1;

    @Test
    public void test4() {
        //lamda表达式
        //jdk8新特性
        //stream流
        esNodeServic1.findAll().forEach(e -> System.out.println(e));
    }

}
