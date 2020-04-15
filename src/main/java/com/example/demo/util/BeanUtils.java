package com.example.demo.util;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @program: synchronize
 * @ClassName: BeanUtils
 * @Description: 获取bean
 * @Author: 842712494@qq.com
 * @Date: 2020/4/13 12:57
 * @Version: 1.0.0
 */
@Component
public class BeanUtils {
    @Resource
    private ApplicationContext applicationContext;

    public Object getBeanByBeanName(String beanName) {
        try {
            return applicationContext.getBean(beanName);

        } catch (Exception e) {
            return null;
        }
    }

    public Object getBeanByClass(Class<?> clazz) {
        try {
            return applicationContext.getBean(clazz);
        } catch (Exception e) {
            return null;
        }
    }
}
