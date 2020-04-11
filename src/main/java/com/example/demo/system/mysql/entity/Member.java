/**
 * 时间：2018年3月28日
 * 地点：
 * 包名：com.example.demo.system.entity
 * 项目名：grap
 */
package com.example.demo.system.mysql.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Administrator
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "member")
public class Member implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id // member的ID是主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(name = "username", unique = true) // username也要是独特的
    private String username;
    private String password;
    private int status;
    private int schoolId;
}
