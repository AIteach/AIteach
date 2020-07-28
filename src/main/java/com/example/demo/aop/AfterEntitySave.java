package com.example.demo.aop;

import com.example.demo.mq.EsCustomer;
import com.example.demo.util.JsonUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * @program: AIteach
 * @ClassName: AfterEntitySave
 * @Description:
 * @Author: 842712494@qq.com
 * @Date: 2020/4/21 10:02
 * @Version: 1.0.0
 */
@Component
@Aspect
public class AfterEntitySave {

    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * @Description:  代理dao层，保存时发送mq信息同步更新es数据库
     * @MethodName: EntitySave
     * @Param: []
     * @Return: void
     * @Author: 842712494@qq.com
     * @Date: 2020/4/21 11:59
     */
    @Pointcut("execution(* com.example.demo.system.mysql.dao.*.save(*)))")
    public void EntitySave() {
    }

    @AfterReturning("EntitySave()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Class c1 = args[0].getClass();
        Object EsNode = null;
        try {
            Method toEs = c1.getDeclaredMethod("toEs", null);
            EsNode = toEs.invoke(args[0], null);
        } catch (Exception e) {
        }
        if (EsNode != null) {
            rabbitTemplate.convertAndSend("", EsCustomer.SAVE, JsonUtils.getEsMessage(c1.getSimpleName(), EsNode));
        } else {
            System.out.println("无法获取保存信息" + joinPoint.getTarget());
        }
    }
}
