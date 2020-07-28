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
public class Comment implements MySqlEntityToEsEntity<EsComment> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 评论id
    private int id;
    // 节点id
    private int nodeId;
    // 此评论所属用户id
    private int userid;
    // private String username; //此评论所属用户名
    // 评论内容
    private String commentContent;
    // 评论发表时间
    private Date ctime;
    // 点赞数
    private int likenum;
    // 积极性系数
    private double positive;
    // 消极
    private double nagetive;

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

    @Override
    public EsComment toEs() {
        EsComment esComment = new EsComment();
        esComment.setCommentContent(commentContent);
        esComment.setCtime(ctime);
        esComment.setId(id);
        return esComment;
    }

}
