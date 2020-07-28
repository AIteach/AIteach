package com.example.demo.config;

import com.example.demo.system.es.esdao.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: AIteach
 * @ClassName: BeanNameconfig
 * @Description:
 * @Author: 842712494@qq.com
 * @Date: 2020/4/15 0:16
 * @Version: 1.0.0
 */
public class EsBeanNameConfig {
    private static Map<String, Class<?>> esBeanName = new HashMap<>();

    static {
        esBeanName.put("Chapter", EsChapterJpa.class);
        esBeanName.put("Comment", EsCommentJpa.class);
        esBeanName.put("Course", EsCourseJpa.class);
        esBeanName.put("Kg", EsKgJpa.class);
        esBeanName.put("Node", EsNodeJpa.class);
        esBeanName.put("School", EsNodeJpa.class);
        esBeanName.put("Source", EsSourceJpa.class);
    }

    public static Class<?> getBeanClass(String name) {
        return esBeanName.get(name);
    }

}
