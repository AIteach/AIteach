package com.example.demo.web;


import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.example.demo.system.mysql.entity.*;
import com.example.demo.system.mysql.service.impl.*;
import com.example.demo.utils.NodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;


@Controller
public class ChapterController {
    @Resource
    private HttpSession httpsession;
    @Resource
    private CourseService courseService;
    @Resource
    private ChapterService chapterService;
    @Resource
    private LinkingService linkingService;
    @Resource
    private NodeService nodeService;
    @Resource
    private KgService kgService;
    @Resource
    private NodeTypeService nodeTypeService;
    @Resource
    private SourceService sourceService;


    @GetMapping("/chapter")
    public String chapterList(int chapterId, Model model) {
        EchartJson echartjson = new EchartJson();  //新的返回json类
        echartjson.setType("force");        //设置图的样式

        //设置类目
        echartjson.setCategories(nodeTypeService.findAllNodeTypeName());

        List<Node> nodes = new ArrayList<>();  //遍历子节点
        //知识结点
        List<Chapter> chapter = chapterService.findOneBySql("chapter", "chapter_id", chapterId);
        model.addAttribute("chapterNode", chapter);
        String courseName = courseService.findOneBySql("course", "course_id", chapter.get(0).getCourseId()).get(0).getCourseName();
        model.addAttribute("chapterNode", chapter);
        model.addAttribute("courseName", courseName);
        for (int i = 0; i < chapter.size(); i++) {
            Node newChapterNode = nodeService.findOneBySql("node", "id", chapter.get(0).getNodeId()).get(0);
            nodes.add(newChapterNode);
        }


        //遍历子知识点
        List<Kg> chapterSon = kgService.findOneBySql("kg", "chapter_id", chapterId);

        //遍历子知识点

        model.addAttribute("chapterSonNodes", chapterSon);

        //开始组node数组
        for (int i = 0; i < chapterSon.size(); i++) {
            Node kgSonNodes = new Node(chapterSon.get(i).getKgName(), 3, 15, "/kg?kgId=" + chapterSon.get(i).getKgId(), chapterSon.get(i).getNodeId());
            nodes.add(kgSonNodes);
        }
        echartjson.setNodes(nodes);


        //设置连接开始
        List<Linking> linkings = new ArrayList<>();
        for (int i = 0; i < nodes.size(); i++)
            linkings.addAll(linkingService.findOneBySql("linking", "rear_id", nodes.get(i).getId())); //读取数据库的全部linking连接关系
        echartjson.setLinks(NodeUtil.change(linkings, nodes));
        model.addAttribute("msg", JSONObject.toJSONString(echartjson));
        return "chapter";
    }


    @GetMapping("/chapterAddKg")
    public String chapterAddChapter(Integer kgId, int chapterId, Model model) {
        List<Chapter> chapter = chapterService.findOneBySql("chapter", "chapter_id", chapterId);//建立一个列表来存放课程
        model.addAttribute("chapterNode", chapter.get(0));//往前端传数据
        return "chapter/chapterAddKg";
    }

    @PostMapping("/doChapterAddKg")
    public String doChapterAddChapter(Kg kg, String value) {
        Member member = (Member) httpsession.getAttribute("currentUser");//获取用户信息
        if (member == null) {
            return "login";
        } else {
            //新建知识点
            Node KgNode = new Node(kg.getKgName(), 3, 15, "/kg?kgId=" + kg.getKgId(), kg.getNodeId());
            int KgNodeId = this.nodeService.saveCourse(KgNode);
            kg.setCreaterId(member.getId());
            kg.setNodeId(KgNodeId);
            int kgId = this.kgService.save(kg).getChapterId();
            //对知识结点的url进行修改，因此新建一个kgnode2
            Node kgNode2 = KgNode;
            kgNode2.setUrl("/kg?kgId=" + kgId);
            this.nodeService.updateNode(kgNode2);  //修改信息
            return "redirect:/chapterAddKg?chapterId=" + kg.getChapterId();
        }
    }


    @GetMapping("/chapterEdit")
    public String ChapterEdit(int chapterId, String value, Model model) {
        List<Chapter> chapter = chapterService.findOneBySql("chapter", "chapter_id", chapterId);
        model.addAttribute("chapterNode", chapter.get(0));
        Course course = courseService.findOneBySql("course", "course_id", chapter.get(0).getCourseId()).get(0);
        //	Node courseNode = nodeService.findOneBySql("node", "id", course.getNodeId()).get(0);
        //	nodeService.updateNode(courseNode);
        model.addAttribute("course", course);
        return "chapter/chapterEdit";

    }

    @PostMapping("/doChapterEdit")
    public String doChapterList(Chapter chapter) {
        chapterService.updateByEntiy(chapter);
        Node node = nodeService.findOneBySql("node", "id", chapter.getNodeId()).get(0);
        node.setName(chapter.getChapterName());
        System.out.println("11111111111111" + chapter.getNodeId());
        return "redirect:/chapter?chapterId=" + chapter.getChapterId();
    }

    @GetMapping("/chapterLinkAdd")
    public String linkAdd(int chapterId, Model model) {
        int rearId = chapterService.findOneBySql("chapter", "chapter_id", chapterId).get(0).getNodeId();
        List<Node> nodes = nodeService.findByCategoryGreaterThanEqual(2);
        model.addAttribute("nodes", nodes);
        model.addAttribute("rearId", rearId);
        return "chapter/chapterLinkAdd";

    }

    @PostMapping("/doChapterLinkAdd")
    public String dolinkAdd(int chapterId, Linking linking, Model model) {
        linkingService.save(linking);
        return "redirect:/chapter?chapterId=" + chapterId;

    }

    @GetMapping("/chapterDelete")
    public String KgDelete(int chapterId) {
        //删除结点
        Chapter chapter = chapterService.findOneBySql("chapter", "chapter_id", chapterId).get(0);
        nodeService.deleteById(chapter.getNodeId());
        chapterService.delete(chapterId);
        return "redirect:course?courseId=" + chapter.getCourseId();
    }
}
