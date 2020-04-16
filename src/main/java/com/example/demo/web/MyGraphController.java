package com.example.demo.web;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.system.mysql.entity.*;
import com.example.demo.system.mysql.service.impl.*;
import com.example.demo.util.EchartjsonUtils;
import com.example.demo.util.NodeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 84271
 */
@Controller
public class MyGraphController {
    @Resource
    private LinkingService linkingService;
    @Resource
    private CourseService courseService;
    @Resource
    private HttpSession httpsession;
    @Resource
    private NodeTypeService nodeTypeService;
    @Resource
    private NodeService nodeService;
    @Resource
    private MemberService memberService;
    @Resource
    private KgService kgService;


    @GetMapping("/createrGraph")
    public String graphIndex(Model model) {
        Member member = (Member) httpsession.getAttribute("currentUser");
        if (member == null) {
            model.addAttribute("loginType", false);
            return "login";
        } else {
            model.addAttribute("loginType", true);
            List<Name> allNodeTypeName = nodeTypeService.findAllNodeTypeName();
            //遍历子节点
            List<Node> nodes = new ArrayList<>();
            //课程结点
            List<Course> course = courseService.findOneBySql("course", "creater_id", member.getId());
            model.addAttribute("courseNode", course);
            for (int i = 0; i < course.size(); i++) {
                Node courseNode = nodeService.findOneBySql("node", "id", course.get(i).getNodeId()).get(0);
                nodes.add(courseNode);
            }
            //遍历知识点
            for (int i = 0; i < course.size(); i++) {
                List<Kg> kg = kgService.findOneBySql("kg", "course_id", course.get(i).getCourseId());
                //保存这节课的所有知识到前台
                model.addAttribute("kgNode", kg);
                System.out.println(kg);
                //开始组node数组
                for (int j = 0; j < kg.size(); j++) {
                    Node node = new Node(kg.get(j).getKgName(), 2, 20, "/kg?kgId=" + kg.get(j).getKgId(), kg.get(j).getNodeId());
                    nodes.add(node);
                    //nodeService.saveUser(node);
                }
            }
            //设置连接开始
            List<Linking> linkings = new ArrayList<>();
            for (int i = 0; i < nodes.size(); i++) {
                //读取数据库的全部linking连接关系
                linkings.addAll(linkingService.findOneBySql("linking", "rear_id", nodes.get(i).getId()));
            }
            List<Link> links = NodeUtils.change(linkings, nodes);
            EchartJson echartjson = EchartjsonUtils.getEchartJson(allNodeTypeName,nodes,links);
            model.addAttribute("msg", JSONObject.toJSONString(echartjson));
            return "graph/createrGraph";
        }
    }

    @GetMapping("/joinerGraph")
    public String createrGraph(Model model) {
        Member member = (Member) httpsession.getAttribute("currentUser");
        if (member == null) {
            model.addAttribute("loginType", false);
            return "login";
        } else {
            model.addAttribute("loginType", true);

            List<Kg> myCreateKg = kgService.findOneBySql("kg", "creater_id", member.getId());
            System.out.println(myCreateKg.toString());
            //新的返回json类
            EchartJson echartjson = new EchartJson();
            //设置图的样式
            echartjson.setType("force");
            //设置类目
            List<Name> names = new ArrayList<>();
            //找到所有结点,拥有属性ID和属性name,形成name数组
            List<Course> course = courseService.findAll();
            for (int i = 0; i < course.size(); i++) {
                Name name = new Name(course.get(i).getCourseName());
                names.add(name);
            }
            echartjson.setCategories(names);
            //遍历子节点
            List<Node> nodes = new ArrayList<Node>();
            for (int j = 0; j < myCreateKg.size(); j++) {
                Node node = new Node(myCreateKg.get(j).getKgName(), myCreateKg.get(j).getCourseId(), 20, "/kg?kgId=" + myCreateKg.get(j).getKgId(), myCreateKg.get(j).getNodeId());
                nodes.add(node);
            }
            echartjson.setNodes(nodes);
            String jsonString = JSONObject.toJSONString(echartjson);
            model.addAttribute("msg", jsonString);
            return "graph/joinerGraph";
        }
    }
}


