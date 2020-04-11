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
@Entity(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int stuId;
    private String username; //用户名
    private String password; //密码
    private String sex; //性别
    private String mobile;//电话
    private String studDesc; //学生描述
    private String school;
    private String address;
    private String creatime;
    private String city;
    @Column(name = "birth", unique = true)
    private Date birth;
    private int SchoolId;
    private int classId;

}
