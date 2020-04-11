/**
 * 时间：2018年4月7日
 * 地点：
 * 包名：com.example.demo.system.service.impl
 * 项目名：grap
 */
package com.example.demo.system.mysql.service.impl;

import com.example.demo.system.mysql.dao.NodeJpaRepository;
import com.example.demo.system.mysql.entity.Node;
import com.example.demo.system.mysql.service.INodeService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
@Service("nodeService")
public class NodeService implements INodeService {
    @PersistenceContext
    private EntityManager entityManager;
    @Resource
    private NodeJpaRepository nodeJpaRepository;

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    public List<Node> findAll() {
        return nodeJpaRepository.findAll();
    }

    @Override
    public Node saveNode(Node node) {
//        System.out.println(node);
        Node newNode = nodeJpaRepository.save(node);
        //往消息队列发送消息
        rabbitTemplate.convertAndSend("", "", newNode.toEsNode());
        return newNode;
    }

    /**
     * 方法名:com.example.demo.system.service.impl 文件名:save
     */
    @Override
    public int saveCourse(Node courseNode) {
        // this.nodeJpaRepository.save(courseNode);
        System.out.println("加入课程中 。。。。。。。。。。等待");
        Node newNode = nodeJpaRepository.save(courseNode);
        rabbitTemplate.convertAndSend("", "", newNode);
        return newNode.getId();
    }

    @Modifying
    @Transactional
    public int updateNode(Node node) {
        // this.nodeJpaRepository.save(courseNode);
        System.out.println("修改课程中 。。。。。。。。。。等待");
        Node newNode = nodeJpaRepository.save(node);
        rabbitTemplate.convertAndSend("", "", newNode);
        return newNode.getId();
    }

    @Override
    public List<Node> findOneBySql(String tablename, String filed, Object o) {
        String sql = "select * from " + tablename + " u WHERE u." + filed + "=?";
        // "select * from " + tablename + " u WHERE u." + filed + "=?"
        // System.out.println("sql语句：");
        // System.out.println("sql语句："+sql);
        Query query = entityManager.createNativeQuery(sql, Node.class);
        query.setParameter(1, o);
        @SuppressWarnings("unchecked")
        List<Node> list = query.getResultList();
        // System.out.println(list);
        entityManager.close();
        return list;
    }

    @Override
    public List<Node> findOneBySqlLike(String tablename, String filed, Object o) {
        String sql = "select * from " + tablename + " u WHERE u." + filed + " like '%" + o + "%'";
        Query query = entityManager.createNativeQuery(sql, Node.class);
        List<Node> list = query.getResultList();
        // System.out.println(list);
        entityManager.close();
        return list;
    }

    @Override
    public List<Node> findListById(Integer[] ids) {
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < ids.length; i++)
            nodes.add(nodeJpaRepository.findById(ids[i]).get());
        return nodes;
    }

    /**
     * 方法名:com.example.demo.system.service.impl 文件名:deleteById
     */
    public void deleteById(int deleteNodeId) {
        nodeJpaRepository.deleteById(deleteNodeId);
        rabbitTemplate.convertAndSend("", "", deleteNodeId);
    }

    public List<Node> findByCategoryGreaterThanEqual(int i) {
        return this.nodeJpaRepository.findByCategoryGreaterThanEqual(i);
    }

    public List<Node> findNodeNameLike(String searchContent) {
        System.out.println(searchContent + this.nodeJpaRepository.findAllByNameLike(searchContent));
        return this.nodeJpaRepository.findAllByNameLike(searchContent);
    }
}
