/**
 * 时间：2018年4月7日
 * 地点：
 * 包名：com.example.demo.system.service.impl
 * 项目名：grap
 */
package com.example.demo.system.mysql.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.example.demo.system.mysql.dao.LinkingJpaRepository;
import com.example.demo.system.mysql.entity.Linking;
import com.example.demo.system.mysql.service.ILinkingService;

/**
 * @author Administrator
 *
 */
@Service("linkingService")
public class LinkingService implements ILinkingService {
    @Resource
    private LinkingJpaRepository linkingJpaRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Linking> findAll() {
        // TODO Auto-generated method stub
        return linkingJpaRepository.findAll();
    }

    /*
     * @see
     * com.example.demo.system.service.IMenberService#saveUser(com.example.demo.
     * system.entity.Member)
     */

    /**
     * 方法名:com.example.demo.system.service.impl 文件名:save
     */
    @Override
    public Linking save(Linking linking) {
        return this.linkingJpaRepository.save(linking);
        // TODO Auto-generated method stub

    }

    @Override
    public List<Linking> findOneBySql(String tablename, String filed, Object o) {
        String sql = "select * from " + tablename + " u WHERE u." + filed + "=?";
        // "select * from " + tablename + " u WHERE u." + filed + "=?"
        // System.out.println("sql语句：");
        // System.out.println(sql);
        Query query = entityManager.createNativeQuery(sql, Linking.class);
        query.setParameter(1, o);
        @SuppressWarnings("unchecked")
        List<Linking> list = query.getResultList();
        // System.out.println(list);
        entityManager.close();
        return list;
    }

    /**
     * 方法名:com.example.demo.system.service.impl 文件名:deleteByPreIdOrReerId
     *
     * @param linking
     */
    @Override
    public void deleteByPreIdOrReerId(Linking linking) {
        this.linkingJpaRepository.delete(linking);
        // TODO Auto-generated method stub

    }

    @Override
    public List<Linking> findAllByPreIdOrRearId(Integer integer) {
        return linkingJpaRepository.findAllByPreIdOrRearId(integer,integer);
    }

}
