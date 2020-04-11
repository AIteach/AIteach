/**
 * 时间：2018年4月19日
 * 地点：
 * 包名：com.example.demo.web
 * 项目名：grap
 */
package com.example.demo.web;

import com.example.demo.system.mysql.entity.*;
import com.example.demo.system.mysql.service.impl.KgService;
import com.example.demo.system.mysql.service.impl.LinkingService;
import com.example.demo.system.mysql.service.impl.NodeService;
import com.example.demo.system.mysql.service.impl.SourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

/**
 * @author Administrator
 */
@Controller
public class SourceController {
    private static final Logger logger = LoggerFactory.getLogger(SourceController.class);
    @Resource
    private HttpSession httpsession;
    @Resource
    private SourceService sourceService;
    @Resource
    private KgService kgService;
    @Resource
    private NodeService nodeService;
    @Resource
    private LinkingService linkingService;

    @GetMapping("/source")
    public String source(int sourceId, Model model) {
        String sourceUrl = sourceService.findOneBySql("source", "id", sourceId).get(0).getSourceUrl();
        model.addAttribute("sourceUrl", sourceUrl);
        model.addAttribute("loadType", "下载成功");
        return "source/sourcePreview";
    }


    @GetMapping("/sourceAdd")
    public String sourceAdd(int kgId, Model model) {
        Kg kg = kgService.findOneBySql("kg", "kg_id", kgId).get(0);
        model.addAttribute("kgNode", kg);
        return "source/sourceAdd";

    }


    @RequestMapping(value = "/upload")
    public String upload(@RequestParam("file") MultipartFile file, Source source, HttpServletRequest request) {
        if (file.isEmpty()) {
            return "文件为空";
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        logger.info("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        logger.info("上传的后缀名为：" + suffixName);
        // 文件上传后的路径
        String filePath = request.getSession().getServletContext().getRealPath("upload/");
        logger.info(filePath);
        //String filePath = "E://test//";
        // 解决中文问题，liunx下中文路径，图片显示问题
        // fileName = UUID.randomUUID() + suffixName;
        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            Member member = (Member) httpsession.getAttribute("currentUser");
            if (member == null) {
                return "login";
            } else {
                file.transferTo(dest);
                source.setSourceUrl("upload/" + fileName);
                source.setType(suffixName);
                source.setCreaterId(member.getId());
                source.setCreaterName(member.getName());
                sourceService.save(source);
                Node newSourceNode = new Node(source.getSourceName(), 3, 15, "/source?sourceId=" + sourceService.save(source).getId());
                nodeService.saveNode(newSourceNode);
                Kg kg = kgService.findOneBySql("kg", "kg_id", source.getKgId()).get(0);
                Linking linking = new Linking(newSourceNode.getId(), kg.getNodeId(), 3, "资源");
                linkingService.save(linking);
                source.setNodeId(newSourceNode.getId());
                sourceService.updateSourceBySourceId(source);
                return "redirect:/kg?kgId=" + source.getKgId();
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "1";
    }


}
