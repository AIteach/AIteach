/**
 * 时间：2018年4月5日
 * 地点：
 * 包名：com.example.demo.web
 * 项目名：grap
 */
package com.example.demo.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.system.mysql.entity.Member;
import com.example.demo.system.mysql.entity.Node;
import com.example.demo.system.mysql.entity.School;
import com.example.demo.system.mysql.service.IMemberService;
import com.example.demo.system.mysql.service.impl.NodeService;
import com.example.demo.system.mysql.service.impl.SchoolService;

/**
 * @author Administrator 系统登陆页面
 */
@Controller
public class LoginController {
    @Resource
    private HttpSession httpsession;

    @Resource
    private IMemberService memberService;

    @Resource
    private SchoolService schoolService;

    @Resource
    private NodeService nodeService;

    @GetMapping("/register")
    public String register(Model model) {
        List<School> schools = schoolService.findAll();
        model.addAttribute("schools", schools);
        return "register";
    }

    @GetMapping("/login")
    public String loginIndex(Model model) {
        if (httpsession.getAttribute("currentUser") != null)
//			return "login";
            return "redirect:/index";
        else {
            model.addAttribute("loginType", false);
            return "login";
        }
    }

    @PostMapping("/doLogin")
    public String dologin(Member member) {
        // System.out.println(memberService.checkByUserNameAndPassword(member.getUsername(),
        // member.getPassword()));
        if (memberService.checkByUserNameAndPassword(member.getUsername(), member.getPassword()) != null) {
            System.out.println("密码输入正确");
            httpsession.setAttribute("currentUser",
                    memberService.checkByUserNameAndPassword(member.getUsername(), member.getPassword()));
            return "redirect:/index";
//            return "redirect:/login";
        } else {
            // System.out.println("密码输入错误，设置currentUser为0");
            httpsession.setAttribute("currentUser", null);
            return "redirect:/login";
        }
    }

    @PostMapping("/doRegister")
    public String doRegister(Member member, Model model) {
        // System.out.print("1");
        // System.out.print(member.getUsername());
        // model.addAttribute("loginType","登陆chengg );
        if (memberService.checkByUserName(member.getUsername()) == true) {
            if (member.getStatus() == 2) {
                memberService.saveUser(member);
                School school = new School(member.getName());
                int schoolId = schoolService.save(school);
                member.setSchoolId(schoolId);
                memberService.saveUser(member);
                Node schoolNode = new Node(school.getSchoolName(), 0, 30, "/school?schoolId=" + schoolId,
                        school.getNodeId());
                // 新建结点
                int schoolNodeId = this.nodeService.saveNode(schoolNode).getId(); // 保存结点
                school.setNodeId(schoolNodeId);
                school.setCreaterId(member.getId());
                school.setCreaterName(member.getName());
                schoolService.save(school); // 保存课程
            } else {
                memberService.saveUser(member);
            }
            System.out.println("registerError");
            model.addAttribute("registerError", "注册成功");
            return "login";
        } else {
            model.addAttribute("registerError", "注册失败");
            return "register";
        }
    }

    @GetMapping("/exit")
    public String exit() {
        httpsession.setAttribute("currentUser", null);
        return "login";
    }
}
