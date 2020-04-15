/**
 * 时间：2018年4月6日
 * 地点：
 * 包名：com.example.demo.system.dao
 * 项目名：grap
 */
package com.example.demo.system.mysql.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.system.mysql.entity.Kg;

import java.util.List;

/**
 * @author Administrator
 */
public interface KgJpaRepository extends JpaRepository<Kg, Integer> {
    /**
     * @Description:
     * @MethodName: findAllByChapterId
     * @Param: [chapterId]
     * @Return: java.util.List<com.example.demo.system.mysql.entity.Kg>
     * @Author: 842712494@qq.com
     * @Date: 2020/4/15 1:45
     */
    List<Kg> findAllByChapterId(int chapterId);

}
