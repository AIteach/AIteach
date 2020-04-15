package com.example.demo.system.mysql.service;


import com.example.demo.system.mysql.entity.Chapter;

import java.util.List;

public interface IChapterService {

    /**
     * @Description:
     * @MethodName: findChapterByCourseId
     * @Param: [CourseId]
     * @Return: List<Chapter>
     * @Author: 842712494@qq.com
     * @Date: 2020/4/15 0:52
     */
    List<Chapter> findChapterByCourseId(int CourseId);

    /**
     * @Description:
     * @MethodName: findOneByChapterId
     * @Param: [chapterId]
     * @Return: com.example.demo.system.mysql.entity.Chapter
     * @Author: 842712494@qq.com
     * @Date: 2020/4/15 1:37
     */
    Chapter findOneByChapterId(int chapterId);

    List<Chapter> findAll();

    List<Chapter> findOneBySql(String tablename, String filed, Object o);

    Chapter save(Chapter chapter);

    /**
     * 方法名:com.example.demo.system.service
     * 文件名:updateByEntiy
     *
     * @return
     */
    void updateByEntiy(Chapter chapter);
}
