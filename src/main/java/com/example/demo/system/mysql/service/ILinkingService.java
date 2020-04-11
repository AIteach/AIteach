/**
 * 时间：2018年4月7日
 * 地点：
 * 包名：com.example.demo.system.service
 * 项目名：grap
 */
package com.example.demo.system.mysql.service;

import java.util.List;

import com.example.demo.system.mysql.entity.Linking;

/**
 * @author Administrator
 *
 */
public interface ILinkingService {

    /**
     * 方法名:com.example.demo.system.service
     文件名:saveLinking
     */
    //void saveLinking(Linking linking);

    /**
     * 方法名:com.example.demo.system.service
     文件名:findAll
     */
    public List<Linking> findAll();

    /**
     * 方法名:com.example.demo.system.service
     文件名:save
     * @return
     */
    Linking save(Linking linking);

    /**
     * 方法名:com.example.demo.system.service
     文件名:findOneBySql
     */
    public List<Linking> findOneBySql(String tablename, String filed, Object o);

    /**
     * 方法名:com.example.demo.system.service
     文件名:deleteByPreIdOrReerId
     */
    void deleteByPreIdOrReerId(Linking linking);

    List<Linking> findAllByPreIdOrRearId(Integer integer);

}
