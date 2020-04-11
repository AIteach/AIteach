package com.example.demo.web;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.system.es.esentity.EsNode;
import com.example.demo.system.es.esservice.EsNodeService;
import com.example.demo.system.mysql.entity.*;
import com.example.demo.system.mysql.service.impl.*;
import com.example.demo.util.EsUtils;
import com.example.demo.util.NodeUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Administrator
 */
@Controller
public class IndexController {
    @Resource
    private TeacherService teacherService;
    @Resource
    private SchoolService schoolService;
    @Resource
    private NodeTypeService nodeTypeService;
    @Resource
    private CourseService courseService;
    @Resource
    private NodeService nodeService;
    @Resource
    private LinkingService linkingService;
    @Resource
    private KgService kgService;
    @Resource
    private HttpSession httpsession;
    @Resource
    private EsNodeService esNodeServic;


    @GetMapping("/index")
    public String Index(Model model) {
        // System.out.println("INDEX" + httpsession.getAttribute("currentUser"));
        if (httpsession.getAttribute("currentUser") != null) {
            model.addAttribute("loginType", true);
            Member member = (Member) httpsession.getAttribute("currentUser");
            // System.out.println(member.getSchoolId());

            List<School> schoolList = schoolService.findAll();
            List<Course> courses = new ArrayList<Course>();
            for (int s = 0; s < schoolList.size(); s++) {
                School schoolNode = schoolService.findBySchoolId(schoolList.get(s).getId());
                if (courseService.findOneBySql("course", "school_id ", schoolNode.getId()) != null) {
                    courses.addAll(courseService.findOneBySql("course", "school_id ", schoolNode.getId()));
                    model.addAttribute("courseNode", courses);
                }
            }
            // 开始组json
            EchartJson echartjson = new EchartJson(); // 新的返回json类
            echartjson.setType("force"); // 设置图的样式

            // 设置类目
            echartjson.setCategories(nodeTypeService.findAllNodeTypeName());

            List<Node> nodes = nodeService.findAll();
            echartjson.setNodes(nodes);

            // 设置连接开始
            List<Link> links = new ArrayList<>(); // 建立本次查询的links数组,由两个参数的link类组成
            List<Linking> linkings = new ArrayList<>();
//            for (int i = 0; i < nodes.size(); i++) {
//                System.out.println("没有ID" + nodes.get(i).getId());
//            }
            // System.out.println("我来自遥远的草原");
            for (int i = 0; i < nodes.size() && i < 10; i++) {
                linkings.addAll(linkingService.findOneBySql("linking", "rear_id", nodes.get(i).getId())); // 读取数据库的全部linking连接关系
                //System.out.println(nodes.get(i).getId() + "  " + nodes.get(i).getName());
            }

            echartjson.setLinks(NodeUtils.change(linkings, nodes));

            String jsonString = JSONObject.toJSONString(echartjson);
            model.addAttribute("msg", jsonString);
            // 组json完毕
            model.addAttribute("schoolList", schoolList);
            return "index";
        } else {
            model.addAttribute("loginType", false);
            return "login";
        }
    }

    @PostMapping("/doIndex")
    @ResponseBody
    public EchartJson doIndex() {

        EchartJson echartjson = new EchartJson(); // 新的返回json类
        echartjson.setType("force"); // 设置图的样式

        // 设置类目
        List<Name> names = new ArrayList<Name>();
        List<NodeType> nodetypes = nodeTypeService.findAll(); // 找到所有结点,拥有属性ID和属性name,形成name数组
        for (int i = 0; i < nodetypes.size(); i++) {
            Name name = new Name(nodetypes.get(i).getName());
            names.add(name);
        }
        echartjson.setCategories(names);

        List<Node> nodes = nodeService.findAll();
        echartjson.setNodes(nodes);

        // 读取数据库的全部linking连接关系
        List<Linking> linkings = linkingService.findAll();
        echartjson.setLinks(NodeUtils.change(linkings, nodes));
        return echartjson;
    }


    @PostMapping("/search")
    public String search(String searchContent, Model model) {
        // 新的返回json类
        EchartJson echartjson = new EchartJson();
        // 设置图的样式
        echartjson.setType("force");
        // 设置类目
        echartjson.setCategories(nodeTypeService.findAllNodeTypeName());
        Page<EsNode> search = esNodeServic.search(EsUtils.getSearchQuery("name", searchContent));
        List<EsNode> content = search.getContent();
        //  for (EsNode esNode : content)
        //  System.out.println(esNode);

        List<Linking> linkings = new ArrayList<>();
        for (int i = 0; i < content.size() && i < 5; i++) {
            linkings.addAll(linkingService.findAllByPreIdOrRearId(content.get(i).getId()));
        }

//        List<Link> link = new ArrayList<>();
        Set<Integer> nodeId = new HashSet<>();
        for (Linking linking : linkings) {
            //System.out.println(linking);
            nodeId.add(linking.getPreId());
            nodeId.add(linking.getRearId());
//            link.add(new Link(linking.getRearId(), linking.getPreId(), linking.getValue()));
        }
        Integer[] ids = new Integer[nodeId.size()];
        nodeId.toArray(ids);
        // System.out.println(Arrays.toString(ids));
        List<Node> nodes = nodeService.findListById(ids);
        echartjson.setNodes(nodes);
        echartjson.setLinks(NodeUtils.change(linkings, nodes));
        model.addAttribute("msg", JSONObject.toJSONString(echartjson));
        model.addAttribute("loginType", true);
        // 组json完毕
        return "index";
    }

    @PostMapping("/search1")
    public String search1(String searchContent, Model model) {
        EchartJson echartjson = new EchartJson(); // 新的返回json类
        echartjson.setType("force"); // 设置图的样式

        // 设置类目
        echartjson.setCategories(nodeTypeService.findAllNodeTypeName());

        List<Node> searchNode = nodeService.findOneBySqlLike("node", "name", searchContent);

        List<Node> nodes = new ArrayList<Node>();
        for (int i = 0; i < searchNode.size(); i++) {
            nodes.add(searchNode.get(i));
        }
        List<Linking> linkings = linkingService.findAll();
        //	System.out.println(searchNode.size()+"  "+linkings.size());
        for (int j = 0; j < searchNode.size(); j++) {
            //	System.out.println(searchNode.get(j).getId());
            //	System.out.println(searchNode.size()+"  "+linkings.size());
            for (int i = 0; i < linkings.size(); i++) {
                if (linkings.get(i).getRearId() == searchNode.get(j).getId()) {
                    Node sourceNode = nodeService.findOneBySql("node", "id", linkings.get(i).getPreId()).get(0);
                    boolean doubleNode = false;  //是否出现重复结点标识
                    for (int z = 0; z < nodes.size(); z++) {
                        if (sourceNode.getId() == nodes.get(z).getId()) {
                            doubleNode = true;
                        } else {
                            doubleNode = false;
                        }
                    }
                    //出现重复则不添加，不重复则添加
                    if (!doubleNode) {
                        nodes.add(sourceNode);
                    }
                }

                if (linkings.get(i).getPreId() == searchNode.get(j).getId()) {
                    Node targetNode = nodeService.findOneBySql("node", "id", linkings.get(i).getRearId()).get(0);
                    //	System.out.println(linkings.get(i).getPreId()+"  "+targetNode.getName()+"  "+targetNode.getId());
                    boolean doubleNode = false;
                    for (int z = 0; z < nodes.size(); z++) {
                        if (targetNode.getId() == nodes.get(z).getId()) {
                            doubleNode = true;
                        } else {
                            doubleNode = false;
                        }
                    }
                    if (!doubleNode) {
                        nodes.add(targetNode);
                    }
                }
            }
        }
        echartjson.setNodes(nodes);

        echartjson.setLinks(NodeUtils.change(linkings, nodes));

        model.addAttribute("msg", JSONObject.toJSONString(echartjson));
        model.addAttribute("loginType", true);
        // 组json完毕
        return "index";
    }

}

