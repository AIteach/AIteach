package com.example.demo.system.mysql.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "CommentResponse")
public class CommentResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // 评论id
    private int commentId; // 评论id
    private int userId; // 回复评论的id
    private int responseUserId; // 被回复评论的id
    private String Content; // 回复内容
    private Date ctime; // 回复发表时间
    private int likenum; // 点赞数
    private double positive; // 积极性系数
    private double nagetive; // 消极

    public void SetPositiveAndNagetive() {
        CalPandN cal = new CalPandN();
        double[] res = cal.getPositiveAndNagetive(getContent());
        this.positive = res[0];
        this.nagetive = res[1];
    }

    public void addLikeNum() {
        this.likenum++;
    }

    public void subLikeNum() {
        this.likenum--;
        if (this.likenum < 0)
            this.likenum = 0;
    }

}
