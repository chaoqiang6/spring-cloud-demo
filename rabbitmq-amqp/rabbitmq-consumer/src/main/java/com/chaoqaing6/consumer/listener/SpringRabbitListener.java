package com.chaoqaing6.consumer.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class SpringRabbitListener {

    @RabbitListener(queues = "simple.queue")
    public void listenSimpleQueueMessage(String msg) throws InterruptedException {
        System.err.println("消费者接收到【" + msg + "】");
        TimeUnit.MILLISECONDS.sleep(50);
    }

    @RabbitListener(queues = "simple.queue")
    public void listenSimpleQueueMessage2(String msg) throws InterruptedException {
        System.out.println("消费者接收到【" + msg + "】");
        TimeUnit.MILLISECONDS.sleep(200);
    }

    @RabbitListener(queues = "fanout_que1")
    public void listenFanoutQueue1(String msg) throws InterruptedException {
        System.out.println("fanout_que1接收到" + msg);
    }

    @RabbitListener(queues = "fanout_que2")
    public void listenFanoutQueue2(String msg) throws InterruptedException {
        System.err.println("fanout_que2接收到" + msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue12"),//queue队列名称
            exchange = @Exchange(name = "direct_exchange", type = ExchangeTypes.DIRECT),//交换机名称
            key = {"route1", "route2"}//binding keys
    ))
    public void listenDirectQueue12(String msg) throws InterruptedException {
        System.out.println("direct.queue12接收到消息" + msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue23"), //queue队列名称
            exchange = @Exchange(name = "direct_exchange", type = ExchangeTypes.DIRECT), //交换机名称
            key = {"route2", "route3"}//binding keys
    ))
    public void listenDirectQueue23(String msg) throws InterruptedException {
        System.out.println("direct.queue23接收到消息" + msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.Q1"), //创建一个名为topic.Q1的队列
            exchange = @Exchange(type = ExchangeTypes.TOPIC, name = "topic_exchange"),//创建一个类型为TOPIC，名称为topic_exchange的交换机
            key = {"*.*.rabbit", "lazy.#"} //Q2 wants to hear everything about rabbits, and everything about lazy animals.
    ))
    public void topicQ2(String msg) throws InterruptedException {
        System.out.println("TOPIC.Q1接收到消息" + msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.Q2"),
            exchange = @Exchange(name = "topic_exchange", type = ExchangeTypes.TOPIC),
            key = "*.orange.*")//Q1 is interested in all the orange animals.
    )
    public void topicQ1(String msg) {
        System.err.println("TOPIC.Q2接收到消息" + msg);
    }

    //这里好别扭，还是用Bean的方式将exchange与queue进行绑定看起来地道
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "obj_queue"),
            exchange = @Exchange(name = "obj_exchange",
                    type = ExchangeTypes.FANOUT)))
    public void objectQueue(Map<String, Object> msg) {
        System.out.println(msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "obj_queue2"),
            exchange = @Exchange(name = "obj_exchange",
                    type = ExchangeTypes.FANOUT)))
    public void objectQueue2(Map<Map, Object> msg) {
        System.out.println(msg);
    }

}
