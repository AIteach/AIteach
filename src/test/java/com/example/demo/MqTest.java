package com.example.demo;

import com.example.demo.system.es.esservice.EsNodeService;
import com.example.demo.system.mysql.entity.Node;
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

    @Test
    public void test1() throws InterruptedException {
        for (int i = 0; i < 100; i++)
            nodeService.saveNode(new Node(i, i, "name2" + i, "value2" + i, 30, "/"));
        Thread.sleep(5000);
    }


    @Test
    public void test2() {
        nodeService.findAll().forEach(e -> System.out.println(e));
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
