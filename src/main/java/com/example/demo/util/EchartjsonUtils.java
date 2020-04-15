package com.example.demo.util;

import com.example.demo.system.mysql.entity.EchartJson;
import com.example.demo.system.mysql.entity.Link;
import com.example.demo.system.mysql.entity.Name;
import com.example.demo.system.mysql.entity.Node;

import java.util.List;

/**
 * @program: AIteach
 * @ClassName: EchartjsonUtils
 * @Description: echartjson工具类
 * @Author: 842712494@qq.com
 * @Date: 2020/4/15 10:13
 * @Version: 1.0.0
 */
public class EchartjsonUtils {
    public static EchartJson getEchartJson(List<Name> typeName, List<Node> nodes, List<Link> links) {
        EchartJson echartjson = new EchartJson();
        echartjson.setType("force");
        echartjson.setCategories(typeName);
        echartjson.setNodes(nodes);
        echartjson.setLinks(links);
        return echartjson;
    }
}
