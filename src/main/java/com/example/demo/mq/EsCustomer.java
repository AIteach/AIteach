package com.example.demo.mq;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.config.EsBeanNameConfig;
import com.example.demo.util.BeanUtils;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
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
    private BeanUtils beanUtils;

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
        System.out.println("接收到 save消息：" + message);
        String className = message.getString("ClassName");
        //通过反射机制，得到实例化对象
        Object object = message.get("Object");

        ElasticsearchRepository elasticsearchRepository = (ElasticsearchRepository) beanUtils.getBeanByClass(EsBeanNameConfig.getBeanClass(message.getString("JpaBeanClassName")));
        if (elasticsearchRepository != null && object != null) {
            //进行保存或修改操作
            System.out.println(elasticsearchRepository);
            System.out.println(object);
            elasticsearchRepository.save(object);
        }
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
        System.out.println("接收到 delete消息：" + message);
        //获取对应的bean
        ElasticsearchRepository elasticsearchRepository = (ElasticsearchRepository) beanUtils.getBeanByClass(EsBeanNameConfig.getBeanClass(message.getString("JpaBeanClassName")));
        Object object = message.get("Object");
        if (elasticsearchRepository != null && object != null) {
            elasticsearchRepository.deleteById(object);
        }
    }


}
