package com.example.demo.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.example.demo.utils.NodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.system.mysql.entity.Course;
import com.example.demo.system.mysql.entity.EchartJson;
import com.example.demo.system.mysql.entity.Kg;
import com.example.demo.system.mysql.entity.Link;
import com.example.demo.system.mysql.entity.Linking;
import com.example.demo.system.mysql.entity.Member;
import com.example.demo.system.mysql.entity.Name;
import com.example.demo.system.mysql.entity.Node;
import com.example.demo.system.mysql.entity.NodeType;
import com.example.demo.system.mysql.service.impl.CourseService;
import com.example.demo.system.mysql.service.impl.KgService;
import com.example.demo.system.mysql.service.impl.LinkingService;
import com.example.demo.system.mysql.service.impl.MemberService;
import com.example.demo.system.mysql.service.impl.NodeService;
import com.example.demo.system.mysql.service.impl.NodeTypeService;

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
            EchartJson echartjson = new EchartJson();  //新的返回json类
            echartjson.setType("force");        //设置图的样式

            //设置类目
            echartjson.setCategories(nodeTypeService.findAllNodeTypeName());


            List<Node> nodes = new ArrayList<Node>();  //遍历子节点
            //课程结点
            List<Course> course = courseService.findOneBySql("course", "creater_id", member.getId());
            model.addAttribute("courseNode", course);
            for (int i = 0; i < course.size(); i++) {
                //Node node = new //Node(course.get(i).getCourseName(),course.get(i).getCourseId(),1,25,"/course?courseId="+course.get(i).getCourseId());
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
            echartjson.setNodes(nodes);

            //设置连接开始
            List<Linking> linkings = new ArrayList<Linking>();
            for (int i = 0; i < nodes.size(); i++)
                linkings.addAll(linkingService.findOneBySql("linking", "rear_id", nodes.get(i).getId())); //读取数据库的全部linking连接关系

            echartjson.setLinks(NodeUtil.change(linkings,nodes));

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

            List<Kg> myCreaterKg = kgService.findOneBySql("kg", "creater_id", member.getId());
            System.out.println(myCreaterKg.toString());
            EchartJson echartjson = new EchartJson();  //新的返回json类
            echartjson.setType("force");        //设置图的样式
            //设置类目
            List<Name> names = new ArrayList<Name>();
            List<Course> course = courseService.findAll();  //找到所有结点,拥有属性ID和属性name,形成name数组
            for (int i = 0; i < course.size(); i++) {
                Name name = new Name(course.get(i).getCourseName());
                names.add(name);
            }
            echartjson.setCategories(names);
            List<Node> nodes = new ArrayList<Node>();  //遍历子节点
            for (int j = 0; j < myCreaterKg.size(); j++) {
                Node node = new Node(myCreaterKg.get(j).getKgName(), myCreaterKg.get(j).getCourseId(), 20, "/kg?kgId=" + myCreaterKg.get(j).getKgId(), myCreaterKg.get(j).getNodeId());
                nodes.add(node);
                //nodeService.saveUser(node);
            }
            echartjson.setNodes(nodes);
            String jsonString = JSONObject.toJSONString(echartjson);
            model.addAttribute("msg", jsonString);

            return "graph/joinerGraph";
        }
    }
}


