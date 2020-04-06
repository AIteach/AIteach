/**
 * 时间：2018年4月7日
 * 地点：
 * 包名：com.example.demo.system.service
 * 项目名：grap
 */
package com.example.demo.system.mysql.service;

import java.util.List;

import com.example.demo.system.mysql.entity.Name;
import com.example.demo.system.mysql.entity.NodeType;


/**
 * @author Administrator
 */
public interface INodeTypeService {
    public List<NodeType> findAll();

    public List<Name> findAllNodeTypeName();
}
