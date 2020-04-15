package com.example.demo.web;


import com.alibaba.fastjson.JSONObject;
import com.example.demo.system.mysql.entity.*;
import com.example.demo.system.mysql.service.impl.*;
import com.example.demo.util.EchartjsonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


/**
 * @author 84271
 */
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
        List<Name> allNodeTypeName = nodeTypeService.findAllNodeTypeName();
        List<Node> nodes = new ArrayList();
        Chapter chapter = chapterService.findOneByChapterId(chapterId);
        nodes.add(new Node(chapter.getChapterId(), 2, chapter.getChapterName(), chapter.getChapterName(), 30, ""));
        kgService.findKgBychapterId(chapterId).forEach(kg -> nodes.add(new Node(kg.getKgName(), 3, 20, "/kg?kgId=" + kg.getKgId(), kg.getNodeId())));
        List<Link> links = new ArrayList();
        for (int i = 1; i <= nodes.size(); i++) {
            links.add(new Link(0, i, ""));
        }
        EchartJson echartjson = EchartjsonUtils.getEchartJson(allNodeTypeName, nodes, links);
        model.addAttribute("msg", JSONObject.toJSONString(echartjson));
        return "course";
    }


    @GetMapping("/chapterAddKg")
    public String chapterAddChapter(Integer kgId, int chapterId, Model model) {
        //建立一个列表来存放课程
        List<Chapter> chapter = chapterService.findOneBySql("chapter", "chapter_id", chapterId);
        //往前端传数据
        model.addAttribute("chapterNode", chapter.get(0));
        return "chapter/chapterAddKg";
    }

    @PostMapping("/doChapterAddKg")
    public String doChapterAddChapter(Kg kg, String value) {
        //获取用户信息
        Member member = (Member) httpsession.getAttribute("currentUser");
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
