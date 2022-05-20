package com.chaoqaing6.consumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutConfig {

    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanout_exchange");
    }
    @Bean
    public Queue queue1(){
        return new Queue("fanout_que1");
    }

    @Bean
    public Queue queue2(){
        return new Queue("fanout_que2");
    }
    @Bean
    public Binding bindingFanoutQue1(FanoutExchange fanoutExchange,Queue queue1){
        return BindingBuilder.bind(queue1).to(fanoutExchange);
    }
    @Bean
    public Binding bindingFanoutQue2(FanoutExchange fanoutExchange,Queue queue2){
        return BindingBuilder.bind(queue2).to(fanoutExchange);
    }


}
