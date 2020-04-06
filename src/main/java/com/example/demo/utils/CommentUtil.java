package com.example.demo.utils;

import com.example.demo.system.mysql.entity.Name;

import java.util.ArrayList;
import java.util.List;

public class CommentUtil {
    public static List<Name> getCategories() {
        List<Name> names = new ArrayList<Name>();
        names.add(new Name("评论内容"));
        names.add(new Name("评论人"));
        names.add(new Name("评论对象"));
        names.add(new Name("所属课程"));
        names.add(new Name("知识点"));
        names.add(new Name("评论时间"));
        names.add(new Name("评论情感"));
        names.add(new Name("回复"));
        return names;
    }
}
