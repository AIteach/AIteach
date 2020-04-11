package com.example.demo.mq;

import com.example.demo.system.es.esentity.EsNode;
import com.example.demo.system.es.esservice.EsNodeService;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import javax.annotation.Resource;


/**
 * @author 84271
 */
public class NodeCustomer {
    public final static String SAVE = "Node.Save";
    public final static String DELETE = "Node.Delete";

    @Resource
    private EsNodeService esNodeServic;


    //绑定node save队列 ，当有节点在mysql保存时，此队列会有消息
    @RabbitListener(queuesToDeclare = @Queue(NodeCustomer.SAVE))
    public void NodeSave(EsNode node) {
        System.out.println("接收到Node Save消息" + node);
        esNodeServic.save(node);
    }

    //绑定node delete队列 ，当有节点在mysql删除时，此队列会有消息
    @RabbitListener(queuesToDeclare = @Queue(NodeCustomer.DELETE))
    public void NodeDelete(Integer id) {
        System.out.println("接收到Node delete消息" + id);
        esNodeServic.deleteById(id);
    }
}
