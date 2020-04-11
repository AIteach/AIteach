package com.example.demo.system.mysql.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommentandName {

    private Comment comment;
    private String username;
    private List<CommentResponseAndName> commentresponse = new ArrayList<CommentResponseAndName>();

    public CommentandName(Comment comment, String username) {
        this.comment = comment;
        this.username = username;
    }

}
