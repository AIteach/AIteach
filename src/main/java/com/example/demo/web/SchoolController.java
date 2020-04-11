/**
 * 时间：2018年4月6日
 * 地点：
 * 包名：com.example.demo.web
 * 项目名：grap
 */
package com.example.demo.web;


import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.example.demo.utils.NodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.system.mysql.entity.Course;
import com.example.demo.system.mysql.entity.EchartJson;
import com.example.demo.system.mysql.entity.Kg;
import com.example.demo.system.mysql.entity.Link;
import com.example.demo.system.mysql.entity.Linking;
import com.example.demo.system.mysql.entity.Name;
import com.example.demo.system.mysql.entity.Node;
import com.example.demo.system.mysql.entity.NodeType;
import com.example.demo.system.mysql.entity.School;
import com.example.demo.system.mysql.entity.Source;
import com.example.demo.system.mysql.service.impl.CourseService;
import com.example.demo.system.mysql.service.impl.KgService;
import com.example.demo.system.mysql.service.impl.LinkingService;
import com.example.demo.system.mysql.service.impl.NodeService;
import com.example.demo.system.mysql.service.impl.NodeTypeService;
import com.example.demo.system.mysql.service.impl.SchoolService;
import com.example.demo.system.mysql.service.impl.SourceService;

/**
 * @author Administrator
 */
@Controller
public class SchoolController {
    @Resource
    private NodeService nodeService;
    @Resource
    private LinkingService linkingService;
    @Resource
    private SchoolService schoolService;
    @Resource
    private CourseService courseService;
    @Resource
    private KgService kgService;
    @Resource
    private NodeTypeService nodeTypeService;
    @Resource
    private HttpSession httpsession;
    @Resource
    private SourceService sourceService;

    @GetMapping("/schoolDelete")
    public String schoolDelete(int schoolId) {
        schoolService.deleteSchool(schoolId);
        ///没有删除结点
        return "redirect:/index";
    }

    @GetMapping("/schoolEdit")
    public String schoolEdit(int schoolId, Model model) {
        List<School> school = schoolService.findOneBySql("school", "id", schoolId);
        model.addAttribute("schoolNode", school.get(0));
        return "school/schoolEdit";

    }

    @PostMapping("/doSchoolEdit")
    public String doSchoolEdit(String schoolName, School school) {
        //System.out.println("修改好的学校ID："+school.getId());
        //System.out.println("修改好的学校名字："+schoolName);
        school.setSchoolName(schoolName);
        schoolService.save(school);
        //schoolService.findBySchoolId(school.getId());
        return "redirect:/index";
    }

    @GetMapping("/school")
    public String school(int schoolId, Model model) {

        if (httpsession.getAttribute("currentUser") != null) {
            // 开始组json
            EchartJson echartjson = new EchartJson(); // 新的返回json类
            echartjson.setType("force"); // 设置图的样式

            // 设置类目
            echartjson.setCategories(nodeTypeService.findAllNodeTypeName());


            //遍历结点
            List<Node> nodes = new ArrayList<>();  //遍历子节点

            School school = schoolService.findBySchoolId(schoolId);
            model.addAttribute("schoolNode", school);
            nodes.add(nodeService.findOneBySql("node", "id", school.getNodeId()).get(0));
            //课程结点
            List<Course> course = courseService.findOneBySql("course", "school_id", schoolId);
            model.addAttribute("courseNode", course);
            for (int i = 0; i < course.size(); i++) {
                //Node node = new //Node(course.get(i).getCourseName(),course.get(i).getCourseId(),1,25,"/course?courseId="+course.get(i).getCourseId());
                Node courseNode = nodeService.findOneBySql("node", "id", course.get(i).getNodeId()).get(0);
                nodes.add(courseNode);
            }

            //遍历知识点
            List<Kg> kg = new ArrayList<>();
            for (int j = 0; j < course.size(); j++) {
                kg.addAll(kgService.findOneBySql("kg", "course_id", course.get(j).getCourseId()));
                //保存这节课的所有知识到前台
                //开始组node数组
            }
            for (int i = 0; i < kg.size(); i++) {
                Node node = new Node(kg.get(i).getKgName(), 2, 20, "/kg?kgId=" + kg.get(i).getKgId(), kg.get(i).getNodeId());
                nodes.add(node);
                //nodeService.saveUser(node);
            }
            model.addAttribute("kgNode", kg);


            //遍历子知识点
            List<Source> source = new ArrayList<>();
            for (int j = 0; j < kg.size(); j++) {
                source.addAll(sourceService.findOneBySql("source", "kg_id", kg.get(j).getKgId()));
            }
            for (int i = 0; i < source.size(); i++) {
                Node sourceNodes = new Node(source.get(i).getSourceName(), 3, 15, "/source?sourceId=" + source.get(i).getId(), source.get(i).getNodeId());
                nodes.add(sourceNodes);
            }

            model.addAttribute("kgSonNodes", source);
            echartjson.setNodes(nodes);

            // 设置连接开始
            List<Linking> linkings = new ArrayList<Linking>();

            for (int i = 0; i < nodes.size(); i++)
                linkings.addAll(linkingService.findOneBySql("linking", "rear_id", nodes.get(i).getId())); // 读取数据库的全部linking连接关系

            echartjson.setLinks(NodeUtil.change(linkings, nodes));

            model.addAttribute("msg", JSONObject.toJSONString(echartjson));
            model.addAttribute("loginType", true);
            return "school";
        } else {
            model.addAttribute("loginType", false);
            return "login";
        }

    }
}
