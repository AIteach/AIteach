package com.example.demo.util;

import com.example.demo.system.mysql.entity.Link;
import com.example.demo.system.mysql.entity.Linking;
import com.example.demo.system.mysql.entity.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 84271
 */
public class NodeUtils {
    public static List<Link> change(List<Linking> linkings, List<Node> nodes) {
        //将节点与linking转换
        List<Link> links = new ArrayList<>();
        //对于任何符合以上结点间关系的查询出来
        for (int i = 0; i < linkings.size(); i++) {
            for (int j = 0; j < nodes.size(); j++) {
                if (linkings.get(i).getPreId() == nodes.get(j).getId()) {
                    linkings.get(i).setPreId(j);
                }
                if (linkings.get(i).getRearId() == nodes.get(j).getId()) {
                    linkings.get(i).setRearId(j);
                }
            }
            Link link = new Link(linkings.get(i).getPreId(), linkings.get(i).getRearId(), linkings.get(i).getValue());
            links.add(link);
        }
        return links;
    }
}
