package com.example.demo.aop;

import com.example.demo.mq.EsCustomer;
import com.example.demo.util.JsonUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @program: AIteach
 * @ClassName: AfterEntityDelete
 * @Description:
 * @Author: 842712494@qq.com
 * @Date: 2020/4/21 10:02
 * @Version: 1.0.0
 */
@Component
@Aspect
public class AfterEntityDelete {

    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * @Description: 代理dao层，保存时发送mq信息同步更新es数据库
     * @MethodName: EntitySave
     * @Param: []
     * @Return: void
     * @Author: 842712494@qq.com
     * @Date: 2020/4/21 11:59
     */
    @Pointcut(value = "execution(* com.example.demo.system.mysql.dao.*.deleteById(*)))")
    public void EntityDelete() {

    }

    @Before("EntityDelete()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        System.out.println(joinPoint.getThis());
        System.out.println(joinPoint.getTarget());
        System.out.println(joinPoint.getSignature().getDeclaringType());
        Object arg = joinPoint.getArgs()[0];
        System.out.println(arg);

        rabbitTemplate.convertAndSend("", EsCustomer.DELETE, JsonUtils.getEsMessage(arg.getClass().getSimpleName(), arg));
    }
}
