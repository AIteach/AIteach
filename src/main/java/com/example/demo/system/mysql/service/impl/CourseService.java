/**
 * 时间：2018年4月6日
 * 地点：
 * 包名：com.example.demo.system.service.impl
 * 项目名：grap
 */
package com.example.demo.system.mysql.service.impl;

import com.example.demo.system.mysql.dao.CourseJpaRepository;
import com.example.demo.system.mysql.dao.LinkingJpaRepository;
import com.example.demo.system.mysql.entity.Course;
import com.example.demo.system.mysql.entity.Node;
import com.example.demo.system.mysql.service.ICourseService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@CacheConfig(cacheNames = "courseService")
@Service("courseService")
public class CourseService implements ICourseService {
    @Resource
    private CourseJpaRepository courseJpaRepository;

    private LinkingJpaRepository linkingJpaRepository;
    @Resource
    private LinkingService linkingService;
    @Resource
    private NodeService nodeService;

    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    public Course findCourseByCourseId(int courseId) {
        return courseJpaRepository.findById(courseId).get();
    }

    @Cacheable(key = "#root.methodName")
    @Override
    public List<Course> findAll() {
        // TODO Auto-generated method stub
        return courseJpaRepository.findAll();
    }


    @Cacheable(key = "#root.methodName+#a0+#a1+#a2")
    @Override
    public List<Course> findOneBySql(String tablename, String filed, Object o) {
        // TODO Auto-generated method stub
        String sql = "select * from " + tablename + " u WHERE u." + filed + "=?";
        // "select * from " + tablename + " u WHERE u." + filed + "=?"
        // System.out.println("sql语句：");
        System.out.println(sql);
        Query query = entityManager.createNativeQuery(sql, Course.class);
        query.setParameter(1, o);
        @SuppressWarnings("unchecked")
        List<Course> list = query.getResultList();
        // System.out.println(list);
        entityManager.close();
        return list;
    }

    @Override
    public void deleteById(int courseId) {
        // TODO Auto-generated method stub
        courseJpaRepository.deleteById(courseId);
//        rabbitTemplate.convertAndSend("", EsCustomer.DELETE, JsonUtils.getEsMessage(EsCourseJpa.class.getSimpleName(), courseId));
    }

    /**
     * 方法名:com.example.demo.system.service.impl 文件名:save
     */
    @Override
    // @CacheEvict(cacheNames="grap",allEntries=true)
    public Course save(Course course) {
        Course save = courseJpaRepository.save(course);
//        rabbitTemplate.convertAndSend("", EsCustomer.SAVE, JsonUtils.getEsMessage(EsCourseJpa.class.getSimpleName(), save.toEsCourse()));
        return save;
    }

    @Transactional
    @Override
    @Modifying
    // @org.springframework.data.jpa.repository.Query("update Course a set a.")
    public void updateById(Course course) {
        // TODO Auto-generated method stub
        // System.out.println(course.getCourseId());
        int nodeId = this.courseJpaRepository.save(course).getNodeId(); // 保存课程，获取结点表的Id
        System.out.println(nodeId);
        Node node = nodeService.findOneBySql("node", "id", nodeId).get(0); // 查询结点表中的该id 实体
        System.out.println(node.getId());
        node.setName(course.getCourseName());
        nodeService.updateNode(node);
    }

}
