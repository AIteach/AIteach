package com.example.demo.mq;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.system.es.esservice.impl.BaseServiceImpl;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName: NodeCustomer2
 * @Description: TODO
 * @Author: 84271
 * @Date: 2020/4/11 18:19
 * @Version: 1.0.0
 */
@Component
public class EsCustomer {
    public final static String SAVE = "Save";
    public final static String DELETE = "Delete";

    @Resource
    private ApplicationContext applicationContext;


    /**
     * @Description: 对es和mysql进行同步
     * @MethodName: NodeSave
     * @Param: [node]
     * @Return: void
     * @Author: 842712494@qq.com
     * @Date: 2020/4/11 18:44
     * 当此队列有消息时，说明mysql表有进行增改操作，读取队列里面的json数据，通过beanName获取对应bean实例
     * 进行增改操作
     */
    @RabbitListener(queuesToDeclare = @Queue(EsCustomer.SAVE))
    public void NodeSave(JSONObject message) {
        //获取对应的bean name
        String jpaBeanName = message.getString("JpaBeanName");
        Object object = message.get("Object");
        BaseServiceImpl baseService = (BaseServiceImpl) applicationContext.getBean(jpaBeanName);
        baseService.save(object);
    }


    /**
     * @Description: 绑定node delete队列 ，当有节点在mysql删除时，此队列会有消息
     * @MethodName: NodeDelete
     * @Param: [id]
     * @Return: void
     * @Author: 842712494@qq.com
     * @Date: 2020/4/11 18:43
     */
    @RabbitListener(queuesToDeclare = @Queue(EsCustomer.DELETE))
    public void NodeDelete(JSONObject message) {
        String jpaBeanName = message.getString("JpaBeanName");
        Object object = message.get("Object");
        BaseServiceImpl baseService = (BaseServiceImpl) applicationContext.getBean(jpaBeanName);
        baseService.deleteById(object);
    }
}
