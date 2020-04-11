package com.example.demo.mq;

import org.springframework.context.ApplicationContext;

/**
 * @program: AIteach
 * @ClassName: EsJpaFactory
 * @Description: 获取Es Jpa实例
 * @Author: 842712494@qq.com
 * @Date: 2020/4/11 22:47
 * @Version: 1.0.0
 */
public class EsJpaFactory {


    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        EsJpaFactory.applicationContext = applicationContext;
    }

    /**
     * @return
     * @Description: 通过反射机制获取Es Jpa实例
     * @MethodName: getInstance
     * @Param: [className]
     * @param: clazz
     * @Return: high.IMessage
     * @Author: 842712494@qq.com
     * @Date: 2020/4/11 22:22
     */
    public static Object getInstance(String beanName) throws Exception {
        System.out.println(applicationContext);
        return applicationContext.getBean(beanName);
    }


}
