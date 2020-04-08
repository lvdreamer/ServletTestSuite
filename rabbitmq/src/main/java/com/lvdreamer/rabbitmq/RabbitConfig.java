package com.lvdreamer.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class RabbitConfig {

    public static final String SIMPLE_QUEUE = "lvdreamer.simple";
    public static final String FANOUT_EXCHANGE = "lvdreamer.exchange.fanout";
    public static final String DIRECT_EXCHANGE = "lvdreamer.exchange.direct";
    public static final String MANUALACK_EXCHANGE = "lvdreamer.exchange.manualack";
    public static final String TOPIC_EXCHANGE = "lvdreamer.exchange.topic";

    public static final String FANOUT_QUEUE_ONE = "lvdreamer.queue.fanout1";
    public static final String FANOUT_QUEUE_TWO = "lvdreamer.queue.fanout2";

    public static final String MANUALACK_QUEUE = "lvdreamer.queue.manualack";
    public static final String TOPIC_QUEUE_ONE = "lvdreamer.queue.topic1";
    public static final String TOPIC_QUEUE_TWO = "lvdreamer.queue.topic2";




    @Bean
    public Queue simpleQueue() {
        return new Queue(SIMPLE_QUEUE);
    }

    @Bean
    public Queue manualAckQueue() {
        return new Queue(MANUALACK_QUEUE);
    }

    @Bean
    public Queue fanoutQueue1() {
        return new Queue(FANOUT_QUEUE_ONE);
    }

    @Bean
    public Queue fanoutQueue2() {
        return new Queue(FANOUT_QUEUE_TWO);
    }

    @Bean
    public Binding bindingFanout1() {
        return BindingBuilder.bind(fanoutQueue1()).to(fanoutExchange());
    }

    @Bean
    public Binding bindingFanout2() {
        return BindingBuilder.bind(fanoutQueue2()).to(fanoutExchange());
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(DIRECT_EXCHANGE);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        return template;
    }

    @Bean
    public RabbitListenerContainerFactory<?> manualAckRabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory = new SimpleRabbitListenerContainerFactory();
        simpleRabbitListenerContainerFactory.setConnectionFactory(connectionFactory);

        // 设置消费者线程数
        simpleRabbitListenerContainerFactory.setConcurrentConsumers(5);
        // 设置最大消费者线程数
        simpleRabbitListenerContainerFactory.setMaxConcurrentConsumers(10);
        simpleRabbitListenerContainerFactory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return simpleRabbitListenerContainerFactory;
    }

}
