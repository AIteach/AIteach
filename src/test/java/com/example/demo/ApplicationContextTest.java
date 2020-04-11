package com.example.demo;

import com.example.demo.mq.EsJpaFactory;
import com.example.demo.system.es.esservice.impl.BaseServiceImpl;
import com.example.demo.system.es.esservice.impl.EsNodeServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @program: AIteach
 * @ClassName: ApplicationContextTest
 * @Description: 测试
 * @Author: 842712494@qq.com
 * @Date: 2020/4/11 22:36
 * @Version: 1.0.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ApplicationContextTest {

    @Resource
    private ApplicationContext applicationContext;

    @Test
    public void test1() {
        System.out.println(applicationContext);
        EsNodeServiceImpl esNodeServiceImpl = (EsNodeServiceImpl) applicationContext.getBean("esNodeServiceImpl");
        esNodeServiceImpl.findAll().forEach(esNode -> System.out.println(esNode));
    }

    @Test
    public void test2() throws Exception {
        BaseServiceImpl esNodeServiceImpl = (BaseServiceImpl) EsJpaFactory.getInstance("esChapterServiceImpl");
        esNodeServiceImpl.findAll().forEach(esNode -> System.out.println(esNode));
    }

}
