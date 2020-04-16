package com.example.demo.web;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.system.mysql.entity.*;
import com.example.demo.system.mysql.service.impl.*;
import com.example.demo.util.CommentUtils;
import com.example.demo.util.DateUtils;
import com.example.demo.util.EchartjsonUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class CommentController {
    @Resource
    private CommentResponseService commentResponseService;
    @Resource
    private CommentService commentService;
    @Resource
    private NodeService nodeService;
    @Resource
    private HttpSession httpsession;
    @Resource
    private CommentLikeService CommentLikeService;
    @Resource
    private ResponseLikeService ResponseLikeService;

    @RequestMapping("/GetComment")
    public JSONObject GetComment(int nodeid) {
        // 获取节点评论数据 返回json数据
        JSONObject json = new JSONObject();
        List<CommentandName> comment = this.commentService.findComentByNodeId(nodeid);
        for (CommentandName i : comment) {
            i.setCommentresponse(this.commentResponseService.findCommentResponseByCommentId(i.getComment().getId()));
        }
        json.put("code", 1);
        json.put("body", comment);
        // System.out.println(json);
        return json;
    }

    @RequestMapping("/setCommentData")
    public JSONObject setCommentData(String comment_sign, String comment_id, String user_id, String comment_data) {
        // 添加评论
        // System.out.println("comment_data: " + comment_data);
        // System.out.println("comment_id: " + comment_id);
        // System.out.println("comment_sign: " + comment_sign);
        // System.out.println("user_id: " + user_id);
        Member me = (Member) httpsession.getAttribute("currentUser");
        JSONObject json = new JSONObject();
        if (me == null) {
            json.put("code", 0);
            json.put("message", "用户未登录！");
        } else {
            Boolean sign = null;
            if (comment_sign.equals("1")) {
                sign = setComment(me.getId(), Integer.valueOf(comment_id), comment_data);
            } else if (comment_sign.equals("2")) {
                sign = setResponse(me.getId(), Integer.valueOf(user_id), Integer.valueOf(comment_id), comment_data);
            }
            if (sign) {
                json.put("code", 1);
                json.put("message", "添加成功！");
            } else {
                json.put("code", 0);
                json.put("message", "添加失败！");
            }
        }
        return json;
    }

    @Transactional
    public Boolean setComment(int user_id, int comment_id, String comment_data) {
        Comment com = new Comment();
        com.setCommentContent(comment_data);
        com.setCtime(new Date());
        com.setLikenum(0);
        com.setNodeId(comment_id);
        com.setUserid(user_id);
        com.SetPositiveAndNagetive();
        try {
            this.commentService.save(com);
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return false;
        }
    }

    @Transactional
    public Boolean setResponse(int user_id, int ruser_id, int comment_id, String comment_data) {
        CommentResponse com = new CommentResponse();
        com.setCommentId(comment_id);
        com.setContent(comment_data);
        com.setCtime(new Date());
        com.setLikenum(0);
        com.setResponseUserId(ruser_id);
        com.setUserId(user_id);
        com.SetPositiveAndNagetive();
        try {
            this.commentResponseService.save(com);
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("/comment")
    public EchartJson Comment(int commentId) {
        List<Name> names = CommentUtils.getCategories();
        CommentandName com = this.commentService.findCommentById(commentId);
        com.setCommentresponse(this.commentResponseService.findCommentResponseByCommentId(com.getComment().getId()));
        List<Node> nodes = new ArrayList<>();
        List<Link> links = new ArrayList<>();
        //评论根节点
        nodes.add(new Node(com.getComment().getCommentContent(), 0, 30, "/", nodes.size() + 1));
        //评论人
        nodes.add(new Node(com.getUsername(), 1, 10, "/", nodes.size() + 1));
        links.add(new Link(0, 1, ""));
        //课程id，通过id再查找课程名称
        nodes.add(new Node(Integer.toString(com.getComment().getNodeId()), 3, 10, "/", nodes.size() + 1));
        links.add(new Link(0, 2, ""));
        //评论时间
        nodes.add(new Node(DateUtils.dateFormat(com.getComment().getCtime()), 5, 10, "/", nodes.size() + 1));
        links.add(new Link(0, 3, ""));
        for (Node node : nodes) {
            System.out.println(node);
        }
        List<CommentResponseAndName> commentresponse = com.getCommentresponse();
        for (CommentResponseAndName commentResponseAndName : commentresponse) {
            if (commentResponseAndName.getCommentResponse().getResponseUserId() == com.getComment().getUserid()) {
                nodes.add(new Node(commentResponseAndName.getCommentResponse().getContent(), 7, 10, "/", nodes.size() + 1));
                int target = nodes.size() - 1;
                links.add(new Link(0, target, "回复"));
                nodes.add(new Node(commentResponseAndName.getUsername(), 1, 10, "/", nodes.size() + 1));
                links.add(new Link(target, nodes.size() - 1, ""));
            }
        }
        return EchartjsonUtils.getEchartJson(names, nodes, links);
    }

    @RequestMapping("/comment/getClassName")
    public String getClassName(int nodeid) {
        List<Node> node = this.nodeService.findOneBySql("node", "id", nodeid);
        return node.get(0).getName();
    }

    @RequestMapping("/comment/LikeClick")
    public JSONObject getCommentLike(@RequestParam(value = "commentId[]", required = false) String[] commentId,
                                     @RequestParam(value = "responId[]", required = false) String[] responId) {
        // required = false 避免数组为空时后台报错。
        Member me = (Member) httpsession.getAttribute("currentUser");
        JSONObject json = new JSONObject();
        if (me == null) {
            json.put("code", 0);
            json.put("body", "");
        } else {
            json.put("code", 1);
            JSONObject json1 = new JSONObject();
            if (commentId != null) {
                for (String i : commentId) {
                    if (this.CommentLikeService.findByUserIdAndCommentId(me.getId(), Integer.parseInt(i)) != null) {
                        json1.put(i, true);
                    } else {
                        json1.put(i, false);
                    }
                }
            }

            JSONObject json2 = new JSONObject();
            if (responId != null) {
                for (String i : responId) {
                    if (this.ResponseLikeService.findByUserIdAndCommentId(me.getId(), Integer.parseInt(i)) != null) {
                        json2.put(i, true);
                    } else {
                        json2.put(i, false);
                    }
                }
            }
            JSONObject jsonall = new JSONObject();
            jsonall.put("commentClick", json1);
            jsonall.put("responseClick", json2);
            json.put("body", jsonall);
        }
        return json;
    }

    @Transactional
    public Boolean cancelClickComment(int id, int user_id) {
        // 取消主评论点赞
        Comment comment = this.commentService.findById(id).get();
        comment.subLikeNum();
        try {
            this.CommentLikeService.deleteByUserIdAndCommentId(user_id, id);
            this.commentService.save(comment);
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return false;
        }
    }

    @Transactional
    public Boolean setClickComment(int id, int user_id) {
        // 主评论点赞
        Comment comment = this.commentService.findById(id).get();
        comment.addLikeNum();
        CommentLike comlike = new CommentLike();
        comlike.setCommentId(id);
        comlike.setCtime(new Date());
        comlike.setUserId(user_id);
        try {
            this.CommentLikeService.save(comlike);
            this.commentService.save(comment);
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return false;
        }
    }

    @Transactional
    public Boolean cancelClickResponse(int id, int user_id) {
        // 取消回复点赞
        CommentResponse comresponse = this.commentResponseService.findById(id).get();
        comresponse.subLikeNum();
        try {
            this.commentResponseService.save(comresponse);
            this.ResponseLikeService.deleteByUserIdAndCommentId(user_id, id);
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return false;
        }

    }

    @Transactional
    public Boolean setClickResponse(int id, int user_id) {
        // 回复点赞
        CommentResponse comresponse = this.commentResponseService.findById(id).get();
        comresponse.addLikeNum();
        ResponseLike response = new ResponseLike();
        response.setCommentId(id);
        response.setCtime(new Date());
        response.setUserId(user_id);
        try {
            this.commentResponseService.save(comresponse);
            this.ResponseLikeService.save(response);
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("/setClick")
    public JSONObject setClick(String num, String id, Boolean sign) {
        // 点赞动作,如果num为1主评论操作，num为2回复操作
        // id为对应评论的目标id
        Member me = (Member) httpsession.getAttribute("currentUser");
        JSONObject json = new JSONObject();
        if (sign) {
            // sign为true，取消点赞操作。
            if (num.equals("1")) {
                Boolean getSign = cancelClickComment(Integer.valueOf(id), me.getId());
                if (getSign) {
                    json.put("code", 1);
                    json.put("message", "操作成功！");
                } else {
                    json.put("code", 0);
                    json.put("message", "操作失败！");
                }
            } else if (num.equals("2")) {
                Boolean getSign = cancelClickResponse(Integer.valueOf(id), me.getId());
                if (getSign) {
                    json.put("code", 1);
                    json.put("message", "操作成功！");
                } else {
                    json.put("code", 0);
                    json.put("message", "操作失败！");
                }
            } else {
                json.put("code", 0);
                json.put("message", "未知的操作代码！");
            }
        } else {
            // sign为false,点赞操作。
            if (num.equals("1")) {
                Boolean getSign = setClickComment(Integer.valueOf(id), me.getId());
                if (getSign) {
                    json.put("code", 1);
                    json.put("message", "操作成功！");
                } else {
                    json.put("code", 0);
                    json.put("message", "操作失败！");
                }
            } else if (num.equals("2")) {
                Boolean getSign = setClickResponse(Integer.valueOf(id), me.getId());
                if (getSign) {
                    json.put("code", 1);
                    json.put("message", "操作成功！");
                } else {
                    json.put("code", 0);
                    json.put("message", "操作失败！");
                }
            } else {
                json.put("code", 0);
                json.put("message", "未知的操作代码！");
            }
        }
        return json;
    }
}
