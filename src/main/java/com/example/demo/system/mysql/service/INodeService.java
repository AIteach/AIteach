/**
 * 时间：2018年4月7日
 * 地点：
 * 包名：com.example.demo.system.service
 * 项目名：grap
 */
package com.example.demo.system.mysql.service;

import com.example.demo.system.mysql.entity.Node;

import java.util.List;


/**
 * @author Administrator
 */
public interface INodeService {
    List<Node> findAll();

    Node saveNode(Node node);

    /**
     * 方法名:com.example.demo.system.service
     * 文件名:saveCourse
     */
    int saveCourse(Node courseNode);

    /**
     * 方法名:com.example.demo.system.service
     * 文件名:updateNode
     */
    int updateNode(Node node);

    /**
     * 方法名:com.example.demo.system.service
     * 文件名:findOneBySql
     */
    List<Node> findOneBySql(String tablename, String filed, Object o);

    List<Node> findOneBySqlLike(String tablename, String filed, Object o);

    List<Node> findListById(Integer[] ids);
}
