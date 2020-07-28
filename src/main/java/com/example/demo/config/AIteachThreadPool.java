package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @program: AIteach
 * @ClassName: AIteachThreadPool
 * @Description:
 * @Author: 842712494@qq.com
 * @Date: 2020/7/28 22:02
 * @Version: 1.0.0
 */
@Configuration
public class AIteachThreadPool {

    @Bean
    public ThreadPoolExecutor threadPool() {
        return new ThreadPoolExecutor(2, 10, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(10), Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardOldestPolicy());
    }

}
