package com.example.demo.system.mysql.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommentResponseAndName {
    //commentresponse多表查询接收的实体类，加入用户名
    private CommentResponse commentResponse;
    private String username; // 此评论所属id的用户名
    private String rusername; //被评论所属id的用户名
}
