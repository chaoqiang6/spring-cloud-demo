package com.chaoqiang6.publisher;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AmqpPublisherApplication.class)
public class AmqpPublishTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    private final String queueName = "simple.queue";

    private final String directQueueName = "direct.queue12";

    @Test
    public void testSimplePublish() {
        String msg = "不通过exchange向queue发送消息";
        rabbitTemplate.convertAndSend(queueName, msg);
        rabbitTemplate.convertAndSend(directQueueName, msg);
    }

    @Test
    public void objectQueueTest(){
        Map<String,Object> map = new HashMap<>(2);
        map.put("name","张三");
        map.put("age",18);
        //注意，如果消息队列没有该queue，不会自动新建，所以必须先存在exchange和对应队列
        rabbitTemplate.convertAndSend("obj_exchange",null,map);
    }

    @Test
    public void testSimplePublish50() {
        for (int i = 0; i < 100; i++) {
            String msg = "amq____" + i;
            rabbitTemplate.convertAndSend(queueName, msg);
            rabbitTemplate.convertAndSend(directQueueName, msg);
        }
    }

    @Test
    public void fanoutMsg() {
        rabbitTemplate.convertAndSend("fanout_exchange", "", "fanout消息");
    }

    @Test
    public void directMsg1() {
        rabbitTemplate.convertAndSend("direct_exchange", "route1", "发送到route1的消息");
    }

    @Test
    public void directMsg2() {
        rabbitTemplate.convertAndSend("direct_exchange", "route2", "发送到route2的消息");
    }

    @Test
    public void directMsg3() {
        rabbitTemplate.convertAndSend("direct_exchange", "route3", "发送到route3的消息");
    }


    @Test
    public void topicQ1Test(){
        rabbitTemplate.convertAndSend("topic_exchange","1.2.rabbit","测试Q1Rabbit");
    }
    @Test
    public void topicQ1Test2(){
        rabbitTemplate.convertAndSend("topic_exchange","lazy.2.3.rabbit","测试Q1lazy");
    }
    @Test
    public void topicQ2Test(){
        rabbitTemplate.convertAndSend("topic_exchange","1.orange.2","测试Q2orange");
    }

    @Test
    public void topicQ1Q2Test(){
        rabbitTemplate.convertAndSend("topic_exchange","lazy.orange.rabbit","测试Q1Q2共同关注");
    }
    @Test
    public void topicNoQ1Q2Test(){
        rabbitTemplate.convertAndSend("topic_exchange","1.2.3","测试消息丢失");
    }

}
