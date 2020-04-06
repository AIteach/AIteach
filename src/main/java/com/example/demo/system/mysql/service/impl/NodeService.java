/**
 * 时间：2018年4月7日
 * 地点：
 * 包名：com.example.demo.system.service.impl
 * 项目名：grap
 */
package com.example.demo.system.mysql.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.example.demo.system.mysql.dao.NodeJpaRepository;
import com.example.demo.system.mysql.entity.Node;
import com.example.demo.system.mysql.service.INodeService;

/**
 * @author Administrator
 */
@Service("nodeService")
public class NodeService implements INodeService {
    @PersistenceContext
    private EntityManager entityManager;
    @Resource
    private NodeJpaRepository nodeJpaRepository;

    @Override
    public List<Node> findAll() {
        // TODO Auto-generated method stub
        return nodeJpaRepository.findAll();
    }

    @Override
    public Node saveNode(Node node) {
        return this.nodeJpaRepository.save(node);
        // TODO Auto-generated method stub

    }

    /**
     * 方法名:com.example.demo.system.service.impl 文件名:save
     */
    @Override
    public int saveCourse(Node courseNode) {
        // this.nodeJpaRepository.save(courseNode);
        System.out.println("加入课程中 。。。。。。。。。。等待");
        return this.nodeJpaRepository.save(courseNode).getId();
        // TODO Auto-generated method stub

    }

    @Modifying
    @Transactional
    public int updateNode(Node node) {
        // this.nodeJpaRepository.save(courseNode);
        System.out.println("修改课程中 。。。。。。。。。。等待");
        return this.nodeJpaRepository.save(node).getId();
        // TODO Auto-generated method stub

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
        this.nodeJpaRepository.deleteById(deleteNodeId);

    }

    public List<Node> findByCategoryGreaterThanEqual(int i) {
        return this.nodeJpaRepository.findByCategoryGreaterThanEqual(i);
        // TODO Auto-generated method stub

    }

    public List<Node> findNodeNameLike(String searchContent) {
        // TODO Auto-generated method stub
        System.out.println(searchContent + this.nodeJpaRepository.findAllByNameLike(searchContent));
        return this.nodeJpaRepository.findAllByNameLike(searchContent);
    }
}
