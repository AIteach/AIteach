package com.example.demo.config;

import org.springframework.context.annotation.Configuration;

/**
 * @program: AIteach
 * @ClassName: EsConfig
 * @Description:
 * @Author: 842712494@qq.com
 * @Date: 2020/4/18 10:56
 * @Version: 1.0.0
 */
@Configuration
public class ElasticSearchConfig {

    static {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }

}