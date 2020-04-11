/**
 * 时间：2018年4月7日
 * 地点：
 * 包名：com.example.demo.system.service
 * 项目名：grap
 */
package com.example.demo.system.mysql.service;

import java.util.List;
import java.util.Set;

import com.example.demo.system.mysql.entity.Node;


/**
 * @author Administrator
 *
 */
public interface INodeService {
    public List<Node> findAll();

    public Node saveNode(Node node);

    /**
     * 方法名:com.example.demo.system.service
     文件名:saveCourse
     */
    int saveCourse(Node courseNode);

    /**
     * 方法名:com.example.demo.system.service
     文件名:updateNode
     */
    int updateNode(Node node);

    /**
     * 方法名:com.example.demo.system.service
     文件名:findOneBySql
     */
    List<Node> findOneBySql(String tablename, String filed, Object o);

    List<Node> findOneBySqlLike(String tablename, String filed, Object o);

    public List<Node> findListById(Integer[] ids);
}
