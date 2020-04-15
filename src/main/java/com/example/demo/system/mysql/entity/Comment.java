package com.example.demo.system.mysql.entity;

import com.example.demo.system.es.esentity.EsComment;
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
@Entity(name = "Comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // 评论id
    private int nodeId; // 节点id
    private int userid; // 此评论所属用户id
    // private String username; //此评论所属用户名
    private String commentContent; // 评论内容
    private Date ctime; // 评论发表时间
    private int likenum;// 点赞数
    private double positive; // 积极性系数
    private double nagetive; // 消极

    public void addLikeNum() {
        this.likenum++;
    }

    public void subLikeNum() {
        this.likenum--;
        if (this.likenum < 0) {
            this.likenum = 0;
        }
    }

    public void SetPositiveAndNagetive() {
        CalPandN cal = new CalPandN();
        double[] res = cal.getPositiveAndNagetive(getCommentContent());
        this.positive = res[0];
        this.nagetive = res[1];
    }

    public EsComment toEsComment() {
        EsComment esComment = new EsComment();
        esComment.setCommentContent(commentContent);
        esComment.setCtime(ctime);
        esComment.setId(id);
        return esComment;
    }

}
