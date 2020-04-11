/**
 * 时间：2018年4月7日
 * 地点：
 * 包名：com.example.demo.system.service.impl
 * 项目名：grap
 */
package com.example.demo.system.mysql.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.example.demo.system.mysql.entity.Name;
import org.springframework.stereotype.Service;

import com.example.demo.system.mysql.dao.NodeTypeRepository;
import com.example.demo.system.mysql.entity.NodeType;
import com.example.demo.system.mysql.service.INodeTypeService;

/**
 * @author Administrator
 *
 */
@Service("nodeType")
public class NodeTypeService implements INodeTypeService {
    @Resource
    private NodeTypeRepository NodeTypeJpaRepository;

    @Override
    public List<NodeType> findAll() {
        // TODO Auto-generated method stub
        return NodeTypeJpaRepository.findAll();
    }

    @Override
    public List<Name> findAllNodeTypeName() {
        List<Name> names = new ArrayList();
        List<NodeType> nodetypes = NodeTypeJpaRepository.findAll();
        for (int i = 0; i < nodetypes.size(); i++) {
            Name name = new Name(nodetypes.get(i).getName());
            names.add(name);
        }
        return names;
    }

}
