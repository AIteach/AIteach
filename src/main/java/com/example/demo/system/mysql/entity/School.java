/**
 * 时间：2018年4月6日
 * 地点：
 * 包名：com.example.demo.system.entity
 * 项目名：grap
 */
package com.example.demo.system.mysql.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "school")
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int nodeId;
    private int createrId;
    private String createrName;
    private String schoolName;
    private String schoolDesc;
    private int score; // 学校分值
    private int linkNum; // 实体数
    private int teacherNum;// 教师数
    private String province;
    private String city;
    private int locatedX;
    private int locatedY;
    private String imgUrl;
    private int courseNum;// 课程数
    private int StuNum;

    public School(String schoolName) {
        this.schoolName = schoolName;
    }
}
