package com.example.demo.classsearch.to;

import java.util.List;

/**
 * @program: AIteach
 * @ClassName: ClassVO
 * @Description:
 * @Author: 842712494@qq.com
 * @Date: 2020/7/28 22:13
 * @Version: 1.0.0
 */
public class ClassVO {
    private String CourseName;
    private String CourseUrl;
    private String CourseContent;
    private List<ClassTeacher> TeacherList;

    @Override
    public String toString() {
        return "ClassVO{" +
                "CourseName='" + CourseName + '\'' +
                ", CourseUrl='" + CourseUrl + '\'' +
                ", CourseContent='" + CourseContent + '\'' +
                ", TeacherList=" + TeacherList +
                '}';
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public String getCourseUrl() {
        return CourseUrl;
    }

    public void setCourseUrl(String courseUrl) {
        CourseUrl = courseUrl;
    }

    public String getCourseContent() {
        return CourseContent;
    }

    public void setCourseContent(String courseContent) {
        CourseContent = courseContent;
    }

    public List<ClassTeacher> getTeacherList() {
        return TeacherList;
    }

    public void setTeacherList(List<ClassTeacher> teacherList) {
        TeacherList = teacherList;
    }
}
