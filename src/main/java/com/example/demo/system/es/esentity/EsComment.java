package com.example.demo.system.es.esentity;

import com.example.demo.system.mysql.entity.Comment;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Data
@ToString
@Document(indexName = "comment")
public class EsComment {
    @Id
    private int id; // 评论id

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String commentContent; // 评论内容
    @Field(type = FieldType.Text)
    private Date ctime; // 评论发表时间

    public void change(Comment comment) {
        this.id = comment.getId();
        this.ctime = comment.getCtime();
        this.commentContent = comment.getCommentContent();
    }
}
