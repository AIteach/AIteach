/**
 * 时间：2018年4月6日
 * 地点：
 * 包名：com.example.demo.system.service
 * 项目名：grap
 */
package com.example.demo.system.mysql.service;

import com.example.demo.system.mysql.entity.Course;

import java.util.List;

/**
 * @author Administrator
 */
public interface ICourseService {


    /**
     * @Description: 通过 CourseId查找课程
     * @MethodName: findCourseByCourseId
     * @Param: [courseId]
     * @Return: com.example.demo.system.mysql.entity.Course
     * @Author: 842712494@qq.com
     * @Date: 2020/4/15 0:48
     */
    Course findCourseByCourseId(int courseId);

    List<Course> findAll();

    List<Course> findOneBySql(String tablename, String filed, Object o);

    Course save(Course course);

    void deleteById(int courseId);

    void updateById(Course course);
    // public List<Course> kgNodeId(String tablename,String filed, Object o);

}
