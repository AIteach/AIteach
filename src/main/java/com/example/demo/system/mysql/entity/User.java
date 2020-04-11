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

/**
 * @author Administrator
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @JoinColumn(name = "id", referencedColumnName = "teacherId", nullable = false, updatable = false)
    @OneToOne(optional = false, targetEntity = Teacher.class)
    private Teacher teacher;
    private String username;
    private String password;
    private String rank;

}

/*
 * 1、cascade属性
 *
 * 该属性定义类和类之间的级联关系。定义的级联关系将被容器视为对当前类对象及其关联类对象采取相同的操作， 而且这种关系是递归调用的。
 *
 * 默认情况下，JPA 不会将任何持续性操作层叠到关联的目标。如果希望某些或所有持续性操作层叠到关联的目标，请将 cascade 设置为一个或多个
 * CascadeType 实例，其中包括：
 *
 * CascadeType.ALL 针对拥有实体执行的任何持续性操作均层叠到关联的目标。
 *
 * CascadeType.MERGE 如果合并了拥有实体，则将 merge 层叠到关联的目标。
 *
 * CascadeType.PERSIST 如果持久保存拥有实体，则将 persist 层叠到关联的目标。
 *
 * CascadeType.REFRESH 如果刷新了拥有实体，则 refresh 为关联的层叠目标。
 *
 * CascadeType.REMOVE 如果删除了拥有实体，则还删除关联的目标。
 *
 * 2、JoinColumn属性
 *
 * @OneToOne注释只能确定实体与实体的关系是一对一的关系，不能指定数据库表中的保存的关联字段。所以此时要结合@
 * JoinColumn标记来指定保存实体关系的配置。
 *
 * 3、Fetch属性
 *
 * 可设置以下三个值：
 *
 * @Fetch(FetchMode.JOIN) 会使用left join查询，只产生一条sql语句；
 *
 * @Fetch(FetchMode.SELECT) 会产生N+1条sql语句；
 *
 * @Fetch(FetchMode.SUBSELECT) 产生两条sql语句 第二条语句使用id in (…..)查询出所有关联的数据。
 */