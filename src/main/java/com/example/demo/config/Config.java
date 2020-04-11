package com.example.demo.config;

import com.example.demo.mq.EsJpaFactory;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * @author 84271
 */
@SpringBootConfiguration
public class Config {

    @Resource
    private ApplicationContext applicationContext;

    @Bean
    public void setEsJpaFactoryApplicationContext() {
        EsJpaFactory.setApplicationContext(applicationContext);
    }

}
