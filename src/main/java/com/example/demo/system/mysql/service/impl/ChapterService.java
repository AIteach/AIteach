package com.example.demo.system.mysql.service.impl;

import com.example.demo.mq.EsCustomer;
import com.example.demo.system.es.esdao.EsChapterJpa;
import com.example.demo.system.mysql.dao.ChapterJpaRepository;
import com.example.demo.system.mysql.dao.LinkingJpaRepository;
import com.example.demo.system.mysql.entity.Chapter;
import com.example.demo.system.mysql.entity.Node;
import com.example.demo.system.mysql.service.IChapterService;
import com.example.demo.util.JsonUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service("chapterService")
public class ChapterService implements IChapterService {
    @Resource
    private ChapterJpaRepository chapterJpaRepository;
    @Resource
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
    public List<Chapter> findChapterByCourseId(int courseId) {
        return chapterJpaRepository.findByCourseId(courseId);
    }

    @Override
    public Chapter findOneByChapterId(int chapterId) {
        return chapterJpaRepository.findById(chapterId).get();
    }

    @Override
    public List<Chapter> findAll() {
        // TODO Auto-generated method stub
        return chapterJpaRepository.findAll();
    }

    @Override
    public List<Chapter> findOneBySql(String tablename, String filed, Object o) {
        // TODO Auto-generated method stub
        String sql = "select * from " + tablename + " u WHERE u." + filed + "=?";
        // "select * from " + tablename + " u WHERE u." + filed + "=?"
        // System.out.println("sql语句：");
        System.out.println(sql);
        Query query = entityManager.createNativeQuery(sql, Chapter.class);
        query.setParameter(1, o);
        @SuppressWarnings("unchecked")
        List<Chapter> list = query.getResultList();
        // System.out.println(list);
        entityManager.close();
        return list;
    }

    @Override
    public Chapter save(Chapter chapter) {
        // TODO Auto-generated method stub
        Chapter save = chapterJpaRepository.save(chapter);
        rabbitTemplate.convertAndSend("", EsCustomer.SAVE, JsonUtils.getEsMessage(EsChapterJpa.class.getSimpleName(), chapter.toEsChapter()));
        return save;
    }

    public void delete(int kgId) {
        chapterJpaRepository.deleteById(kgId);
        rabbitTemplate.convertAndSend("", EsCustomer.DELETE, JsonUtils.getEsMessage(EsChapterJpa.class.getSimpleName(), kgId));
    }

    @Override
    public void updateByEntiy(Chapter chapter) {
        // TODO Auto-generated method stub
        int nodeId = this.chapterJpaRepository.save(chapter).getNodeId();
        System.out.println(nodeId);
        Node node = nodeService.findOneBySql("node", "id", nodeId).get(0);
        System.out.println(node.getId());
        node.setName(chapter.getChapterName());
        nodeService.updateNode(node);
    }

}
