/**
 * 时间：2018年4月18日
 * 地点：
 * 包名：com.example.demo.web
 * 项目名：grap
 */
package com.example.demo.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.system.mysql.entity.Course;
import com.example.demo.system.mysql.entity.Kg;
import com.example.demo.system.mysql.entity.Link;
import com.example.demo.system.mysql.entity.Linking;
import com.example.demo.system.mysql.entity.School;
import com.example.demo.system.mysql.service.impl.CourseService;
import com.example.demo.system.mysql.service.impl.KgService;
import com.example.demo.system.mysql.service.impl.LinkingService;
import com.example.demo.system.mysql.service.impl.SchoolService;

/**
 * @author Administrator
 *
 */
public class LinkingController {
    @Resource
    private LinkingService linkingService;
    @Resource
    private CourseService courseService;
    @Resource
    private SchoolService schoolService;
    @Resource
    private KgService kgService;

    @GetMapping("/addLink")
    public String addLink(int kgId, Model model) {
        Kg kg = kgService.findOneBySql("kg", "kg_id", kgId).get(0);
        List<School> allSchool = schoolService.findAll();
        model.addAttribute("kgNode", kg);
        model.addAttribute("allSchool", allSchool);
        List<Course> allCourse = courseService.findAll();
        model.addAttribute("allCourse", allCourse);
        List<Kg> allKg = kgService.findAll();
        model.addAttribute("allKg", allKg);
        return "link/addLink";
    }

    @PostMapping("/doAddLink")
    public String doAddLink(Linking link) {
        linkingService.save(link);
        return "";
    }
}