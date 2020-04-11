/**
 * 时间：2018年4月13日
 * 地点：
 * 包名：com.example.demo.web
 * 项目名：grap
 */
package com.example.demo.web;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.system.mysql.entity.*;
import com.example.demo.system.mysql.service.impl.*;
import com.example.demo.util.NodeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 */
@Controller
public class KgController {
    @Resource
    private HttpSession httpsession;
    @Resource
    private CourseService courseService;
    @Resource
    private KgService kgService;
    @Resource
    private LinkingService linkingService;
    @Resource
    private NodeService nodeService;
    @Resource
    private NodeTypeService nodeTypeService;
    @Resource
    private SourceService sourceService;
    @Resource
    private ChapterService chapterService;

    @GetMapping("/kgAddKg")
    public String kgAddKg(int kgId, Integer chapterId, Model model) {
        List<Chapter> chapter = chapterService.findOneBySql("chapter", "chapter_id", chapterId);
        model.addAttribute("chapterNode", chapter.get(0));
        return "kg/kgAddKg";
    }

    @PostMapping("/doKgAddKg")
    public String doKgAddKg(Kg kg, String value) {
        Member member = (Member) httpsession.getAttribute("currentUser");
        if (member == null) {
            return "login";
        } else {
            // 新建知识点
            Node kgNode = new Node(kg.getKgName(), 3, 15, "/kg?kgId=" + kg.getKgId(), kg.getNodeId());
            int kgNodeId = this.nodeService.saveCourse(kgNode);
            kg.setCreaterId(member.getId());
            kg.setNodeId(kgNodeId);
            int kgId = this.kgService.save(kg).getKgId();
            // 对知识结点的url进行修改，因此新建一个kgnode2
            Node kgNode2 = kgNode;
            kgNode2.setUrl("/kg?kgId=" + kgId);
            // 修改信息
            this.nodeService.updateNode(kgNode2);

            int targetId = kgService.findOneBySql("kg", "kg_id", kg.getKgParid()).get(0).getNodeId();
            Linking kgKgLink = new Linking(kgNodeId, targetId, 2, value);
            linkingService.save(kgKgLink);
            return "redirect:/kg?kgId=" + kg.getChapterId();
        }

    }

    @GetMapping("/kgAdd")
    public String kgAdd(int courseId) {

        return "kg/kgAdd";
    }

    @PostMapping("/doKgAdd")
    public String doKgAdd(Kg kg, String value) {
        // 获取用户
        Member member = (Member) httpsession.getAttribute("currentUser");
        if (member == null) {
            return "login";
        } else {
            // 新建知识点
            System.out.println(value);
            Node kgNode = new Node(kg.getKgName(), 3, 15, "/kg?kgId=" + kg.getKgId(), kg.getNodeId());
            int kgNodeId = this.nodeService.saveCourse(kgNode);
            kg.setCreaterId(member.getId());
            kg.setCreaterName(member.getName());
            kg.setCreatTime(new Date());
            // kg.setKgUrl("/kg?kgId="+kg.getKgId());
            kg.setNodeId(kgNodeId);
            int kgId = this.kgService.save(kg).getKgId();
            // 对知识结点的url进行修改，因此新建一个kgnode2
            Node kgNode2 = kgNode;
            kgNode2.setUrl("/kg?kgId=" + kgId);
            // 修改信息
            this.nodeService.updateNode(kgNode2);
            int ChapterNodeId = chapterService.findOneBySql("chapter", "id", kg.getChapterId()).get(0).getNodeId();
            Linking kgChapterLink = new Linking(kgNodeId, ChapterNodeId, 2, value);
            linkingService.save(kgChapterLink);
            return "redirect:/chapter?chapterId=" + kg.getChapterId();
        }
    }

    @GetMapping("/kg")
    public String kgList(int kgId, Model model) {
        // 新的返回json类
        EchartJson echartjson = new EchartJson();
        // 设置图的样式
        echartjson.setType("force");

        // 设置类目

        echartjson.setCategories(nodeTypeService.findAllNodeTypeName());

        // 遍历子节点
        List<Node> nodes = new ArrayList<Node>();
        // 知识结点
        List<Kg> kg = kgService.findOneBySql("kg", "kg_id", kgId);
        model.addAttribute("kgNode", kg);
        String courseName = courseService.findOneBySql("course", "course_id", kg.get(0).getCourseId()).get(0)
                .getCourseName();
        model.addAttribute("kgNode", kg);
        model.addAttribute("courseName", courseName);
        // System.out.println(nodes.toString());
        for (int i = 0; i < kg.size(); i++) {
            Node newKgNode = nodeService.findOneBySql("node", "id", kg.get(0).getNodeId()).get(0);
            nodes.add(newKgNode);
        }
        // System.out.println(nodes.toString());

        // 遍历子知识点
        List<Kg> kgSon = kgService.findOneBySql("kg", "kg_parid", kgId);

        // 遍历子知识点
        List<Source> source = sourceService.findOneBySql("source", "kg_id", kgId);
        for (int i = 0; i < source.size(); i++) {
            Node sourceNodes = new Node(source.get(i).getSourceName(), 4, 10,
                    "/source?sourceId=" + source.get(i).getId(), source.get(i).getNodeId());
            nodes.add(sourceNodes);
        }
        model.addAttribute("kgSonNodes", kgSon);
        model.addAttribute("sourceNodes", source);
        // 开始组node数组
        for (int i = 0; i < kgSon.size(); i++) {
            Node kgSonNodes = new Node(kgSon.get(i).getKgName(), 3, 15, "/kg?kgId=" + kgSon.get(i).getKgId(),
                    kgSon.get(i).getNodeId());
            nodes.add(kgSonNodes);
            List<Source> sonSource = sourceService.findOneBySql("source", "kg_id", kgSon.get(i).getKgId());
            for (int j = 0; j < source.size(); j++) {
                Node sourceNodes = new Node(sonSource.get(j).getSourceName(), 4, 10,
                        "/source?sourceId=" + sonSource.get(j).getKgId(), sonSource.get(j).getNodeId());
                nodes.add(sourceNodes);
            }
        }
        echartjson.setNodes(nodes);

        // 设置连接开始
        List<Linking> linkings = new ArrayList<Linking>();
        for (int i = 0; i < nodes.size(); i++) {
            // 读取数据库的全部linking连接关系
            linkings.addAll(linkingService.findOneBySql("linking", "rear_id", nodes.get(i).getId()));
        }

        echartjson.setLinks(NodeUtils.change(linkings, nodes));
        model.addAttribute("msg", JSONObject.toJSONString(echartjson));
        return "kg";
    }

    @GetMapping("/kgEdit")
    public String KgEdit(int kgId, String value, Model model) {
        List<Kg> kg = kgService.findOneBySql("kg", "kg_id", kgId);
        model.addAttribute("kgNode", kg.get(0));
        Chapter chapter = chapterService.findOneBySql("chapter", "id", kg.get(0).getChapterId()).get(0);
        // Node courseNode = nodeService.findOneBySql("node", "id",
        // course.getNodeId()).get(0);
        // nodeService.updateNode(courseNode);
        model.addAttribute("chapter", chapter);
        return "kg/kgEdit";

    }

    @PostMapping("/doKgEdit")
    public String doKgList(Kg kg) {
        kgService.updateByEntiy(kg);
        Node node = nodeService.findOneBySql("node", "id", kg.getNodeId()).get(0);
        node.setName(kg.getKgName());
        System.out.println("11111111111111" + kg.getNodeId());
        return "redirect:/kg?kgId=" + kg.getKgId();
    }

    @GetMapping("/kgLinkAdd")
    public String linkAdd(int kgId, Model model) {
        int rearId = kgService.findOneBySql("kg", "kg_id", kgId).get(0).getNodeId();
        List<Node> nodes = nodeService.findByCategoryGreaterThanEqual(2);
        model.addAttribute("nodes", nodes);
        model.addAttribute("rearId", rearId);
        return "kg/kgLinkAdd";

    }

    @PostMapping("/doKgLinkAdd")
    public String dolinkAdd(int kgId, Linking linking, Model model) {
        linkingService.save(linking);
        return "redirect:/kg?kgId=" + kgId;

    }

    @GetMapping("/kgDelete")
    public String KgDelete(int kgId) {
        // 删除结点
        Kg kg = kgService.findOneBySql("kg", "kg_id", kgId).get(0);
        nodeService.deleteById(kg.getNodeId());
        kgService.delete(kgId);
        return "redirect:chapter?chapterId" + kg.getChapterId();
    }
}

