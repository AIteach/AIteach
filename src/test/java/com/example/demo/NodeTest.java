package com.example.demo;

import com.example.demo.system.es.esentity.EsNode;
import com.example.demo.system.es.esservice.impl.EsNodeServic;
import com.example.demo.system.mysql.entity.Linking;
import com.example.demo.system.mysql.entity.Node;
import com.example.demo.system.mysql.service.impl.LinkingService;
import com.example.demo.system.mysql.service.impl.NodeService;
import com.example.demo.utils.EsUtil;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class NodeTest {
    @Resource
    private NodeService nodeService;

    @Resource
    private EsNodeServic esNodeServic;

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

}
