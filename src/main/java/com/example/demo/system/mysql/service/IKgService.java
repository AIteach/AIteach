/**
 * 时间：2018年4月6日
 * 地点：
 * 包名：com.example.demo.system.service.impl
 * 项目名：grap
 */
package com.example.demo.system.mysql.service;

import com.example.demo.system.mysql.entity.Kg;

import java.util.List;

/**
 * @author Administrator
 */
public interface IKgService {

    /**
     * @Description:
     * @MethodName: findAllBychapterId
     * @Param: [chapterId]
     * @Return: List<Kg>
     * @Author: 842712494@qq.com
     * @Date: 2020/4/15 1:44
     */
    List<Kg> findKgBychapterId(int chapterId);

    List<Kg> findAll();

    List<Kg> findOneBySql(String tablename, String filed, Object o);

    Kg save(Kg kg);

    /**
     * 方法名:com.example.demo.system.service 文件名:updateByEntiy
     *
     * @return
     */
    void updateByEntiy(Kg kg);
}
