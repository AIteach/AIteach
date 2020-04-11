/**
 * 时间：2018年4月4日
 * 地点：
 * 包名：com.example.demo.system.entity
 * 项目名：grap-1
 */
package com.example.demo.system.mysql.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Administrator
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseId;
    private String courseName;
    private String courseDesc;
    private int nodeId;
    private int courseScore;
    private int courseEvaluate;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "teacher_teacher_id")//关联的表为address表，其主键是id
    private Teacher teacher;
    private int schoolId;
    private int createrId;
    private String createrName;
    private Date creatTime;
    private int stuNum;
    private int LinkNum;
    private String url;
}
