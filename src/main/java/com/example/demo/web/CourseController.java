package com.example.demo.web;

import java.util.ArrayList;
import java.util.List;

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

@Controller
public class CourseController {
    @Resource
    private CourseService courseService;
    @Resource
    private KgService kgService;
    @Resource
    private NodeTypeService nodeTypeService;
    @Resource
    private NodeService nodeService;
    @Resource
    private LinkingService linkingService;
    @Resource
    private CommentResponseService commentResponseService;
    @Resource
    private CommentService commentService;
    @Resource
    private HttpSession httpsession;
    @Resource
    private SchoolService schoolService;

    @Resource
    private ChapterService chapterService;

    @GetMapping({"/course"})
    public String courseIndex(int courseId, Model model) {
        EchartJson echartjson = new EchartJson();
        echartjson.setType("force");

        echartjson.setCategories(nodeTypeService.findAllNodeTypeName());

        List<Node> nodes = new ArrayList();
        List<Course> course = this.courseService.findOneBySql("course", "course_id", Integer.valueOf(courseId));
        model.addAttribute("courseNode", course);
        for (int i = 0; i < course.size(); i++) {
            Node courseNode = (Node) this.nodeService
                    .findOneBySql("node", "id", Integer.valueOf(((Course) course.get(0)).getNodeId())).get(0);
            nodes.add(courseNode);
        }
        List<Chapter> chapters = chapterService.findOneBySql("chapter", "chapter_id", Integer.valueOf(courseId));

        model.addAttribute("chapterNode", chapters);
        //System.out.println(kg);
        for (int i = 0; i < chapters.size(); i++) {
            Node node = new Node(chapters.get(i).getChapterName(), 2, 20, "/kg?kgId=" + chapters.get(i).getChapterId(), chapters.get(i).getNodeId());
            nodes.add(node);
        }
        echartjson.setNodes(nodes);


        List<Linking> linkings = new ArrayList();
        //System.out.println("我来自遥远的草原");
        for (int i = 0; i < nodes.size(); i++)
            linkings.addAll(this.linkingService.findOneBySql("linking", "rear_id", Integer.valueOf(((Node) nodes.get(i)).getId())));
        echartjson.setLinks(NodeUtil.change(linkings, nodes));
        String jsonString = JSONObject.toJSONString(echartjson);
        model.addAttribute("msg", jsonString);
        model.addAttribute("nodeid", Integer.valueOf(((Course) course.get(0)).getNodeId()));
        System.out.println("nodeid " + ((Course) course.get(0)).getNodeId());
        return "course";
    }

    @GetMapping({"/courseAdd"})
    public String courserAdd(int schoolId) {
        return "course/courseAdd";
    }

    @PostMapping({"/courseAdd"})
    public String doCourseAdd(Course course, String value) {
        Member member = (Member) this.httpsession.getAttribute("currentUser");
        if (member == null) {
            return "login";
        }
        Node courseNode = new Node(course.getCourseName(), 1, 25, "/course?courseId=" + course.getCourseId(),
                course.getNodeId());
        int courseNodeId = this.nodeService.saveCourse(courseNode);
        course.setNodeId(courseNodeId);
        course.setCreaterId(member.getId());
        course.setCreaterName(member.getName());
        int courseId = this.courseService.save(course).getCourseId();
        Node courseNode2 = courseNode;
        courseNode2.setUrl("/course?courseId=" + courseId);
        this.nodeService.updateNode(courseNode2);
        int schoolNodeId = ((School) this.schoolService
                .findOneBySql("school", "id", Integer.valueOf(course.getSchoolId())).get(0)).getNodeId();
        Linking courseSchoolLink = new Linking(courseNodeId, schoolNodeId, 1, value);
        this.linkingService.save(courseSchoolLink);

        return "redirect:/school?schoolId=" + course.getSchoolId();
    }

    @GetMapping({"/courseDelete"})
    public String doCourseDelete(int courseId) {
        Course deleteCourse = (Course) this.courseService.findOneBySql("course", "course_id", Integer.valueOf(courseId))
                .get(0);
        int deleteNodeId = deleteCourse.getNodeId();

        List<Linking> linking = this.linkingService.findOneBySql("linking", "Pre_id", Integer.valueOf(deleteNodeId));
        linking.addAll(this.linkingService.findOneBySql("linking", "rear_id", Integer.valueOf(deleteNodeId)));
        for (int i = 0; i < linking.size(); i++) {
            this.linkingService.deleteByPreIdOrReerId((Linking) linking.get(i));
        }
        this.courseService.deleteById(courseId);
        return "redirect:/school?schoolId=" + deleteCourse.getSchoolId();
    }

    @GetMapping({"/courseEdit"})
    public String courseEdit(int courseId, Model model) {
        List<Course> course = this.courseService.findOneBySql("course", "course_id", Integer.valueOf(courseId));
        List<School> school = this.schoolService.findOneBySql("school", "id",
                Integer.valueOf(((Course) course.get(0)).getSchoolId()));
        model.addAttribute("course", course.get(0));
        model.addAttribute("school", school.get(0));
        return "course/courseEdit";
    }

    @PostMapping({"/doCourseEdit"})
    public String doCourseEdit(Course course) {
        this.courseService.updateById(course);
        return "redirect:/school";
    }

    /*
     * @GetMapping({ "/comment" }) public String comment(Model model) {
     * List<CommentandName> comment = this.commentService.findComentByNodeId(11);
     * List<List<CommentResponseAndName>> response = new ArrayList(); for
     * (CommentandName i : comment) { List<CommentResponseAndName> commentresponse =
     * this.commentResponseService
     * .findCommentResponseByCommentId(i.getComment().getId());
     * response.add(commentresponse); } model.addAttribute("comment", comment);
     * model.addAttribute("response", response); return "comment"; }
     */
}
