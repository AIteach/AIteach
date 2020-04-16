package com.example.demo.web;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.system.mysql.entity.CreatGraph;
import com.example.demo.system.mysql.entity.Member;
import com.example.demo.system.mysql.service.impl.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * @author 84271
 */
@Controller
public class CreateGraphController {
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
    @Resource
    private CreateGraphService createGraphService;

    @GetMapping("/graphList")
    public String graphList(Model model) {
        Member member = (Member) httpsession.getAttribute("currentUser");
        if (member != null) {
            List<CreatGraph> creatGraph = createGraphService.findOneBySql("create_graph", "member_id", member.getId());
            model.addAttribute("creatGraph", creatGraph);
            return "createGraph/creatList";
        } else {
            return "login";
        }
    }

    @GetMapping("/searchGraph")
    public String graphList(int memberId, Model model) {
        Member member = memberService.findById(memberId);
        if (member != null) {
            List<CreatGraph> creatGraph = createGraphService.findOneBySql("create_graph", "member_id", member.getId());
            model.addAttribute("creatGraph", creatGraph);
            return "createGraph/creatList";
        } else {
            return "login";
        }
    }

    @PostMapping("/searchGraphByAllField")
    public String searchGraphByAllField(String content, int memberId, Model model) {
        List<CreatGraph> creatGraph = createGraphService.findByContentOrMemberId(content, memberId);
        model.addAttribute("creatGraph", creatGraph);
        return "createGraph/creatList";
    }


    @GetMapping("/createIndex")
    public String createIndex(Model model) {
        Member member = (Member) httpsession.getAttribute("currentUser");
        if (member == null) {
            model.addAttribute("loginType", false);
            return "login";
        } else {
            model.addAttribute("loginType", true);
            return "createGraph/createIndex";
        }
    }

    @GetMapping("/createGraphEdit")
    public String createIndex(int createGraphId, Model model) {
        Member member = (Member) httpsession.getAttribute("currentUser");
        if (member == null) {
            model.addAttribute("loginType", false);
            return "login";
        } else {
            model.addAttribute("loginType", true);
            CreatGraph editGraph = createGraphService.findOneBySql("create_graph", "id", createGraphId).get(0);
            model.addAttribute("msg", editGraph);
            model.addAttribute("editType", true);
            return "createGraph/createGraphEdit";
        }
    }

    @PostMapping("/createGrapgSave")
    @ResponseBody
    public String createGrapgSave(int id, String linksOption, String nodesOption, String categories, String title, String content, Model model) {
        Member member = (Member) httpsession.getAttribute("currentUser");
        if (member == null) {
            model.addAttribute("loginType", false);
            return "false";
        } else {
            model.addAttribute("loginType", true);
            String category = JSONObject.toJSONString(categories).replace("\\", "");
            String category1 = category.substring(1, category.length() - 1);
            String links = JSONObject.toJSONString(linksOption).replace("\\", "");
            String links1 = links.substring(1, links.length() - 1);
            String nodes = JSONObject.toJSONString(nodesOption).replace("\\", "");
            String nodes1 = nodes.substring(1, nodes.length() - 1);
            CreatGraph Graph = new CreatGraph();
            if (id == 0) {
                Graph = new CreatGraph(category1, links1, nodes1, content, title, member);
            } else
                Graph = new CreatGraph(id, category1, links1, nodes1, content, title, member);
            createGraphService.save(Graph);
            return "true";
        }
    }

    @GetMapping("/createGrapgDelete")
    public String cereateGrapgDelete(int createGraphId, int currentControllId) {
        createGraphService.deleteById(createGraphId);
        return "redirect:searchGraph?memberId=" + currentControllId;
    }
}


