package com.example.demo.mq;

import com.example.demo.system.es.esdao.EsNodeJpa;
import com.example.demo.system.es.esentity.EsNode;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import javax.annotation.Resource;

/**
 * @ClassName: NodeCustomer2
 * @Description: TODO
 * @Author: 84271
 * @Date: 2020/4/11 18:19
 * @Version: 1.0.0
 */
public class NodeCustomer {
    public final static String SAVE = "Node.Save";
    public final static String DELETE = "Node.Delete";


    /**
     * @Description:
     */
    @Resource
    private EsNodeJpa esNodeJpa;


    /**
     * @Description: 绑定node save队列，当有节点在mysql保存时，此队列会有消息
     * @MethodName: NodeSave
     * @Param: [node]
     * @Return: void
     * @Author: 842712494@qq.com
     * @Date: 2020/4/11 18:44
     */
    @RabbitListener(queuesToDeclare = @Queue(NodeCustomer.SAVE))
    public void NodeSave(EsNode node) {
        System.out.println("接收到Node Save消息" + node);
        esNodeJpa.save(node);
    }


    /**
     * @Description: 绑定node delete队列 ，当有节点在mysql删除时，此队列会有消息
     * @MethodName: NodeDelete
     * @Param: [id]
     * @Return: void
     * @Author: 842712494@qq.com
     * @Date: 2020/4/11 18:43
     */
    @RabbitListener(queuesToDeclare = @Queue(NodeCustomer.DELETE))
    public void NodeDelete(Integer id) {
        System.out.println("接收到Node delete消息" + id);
        esNodeJpa.deleteById(id);
    }
}
