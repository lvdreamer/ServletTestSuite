package com.lvdreamer.rabbitmq.consumer;

import com.lvdreamer.rabbitmq.RabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SimpleRabbitMqConsumer {
    private final static Logger logger = LoggerFactory.getLogger(SimpleRabbitMqConsumer.class);

    @RabbitHandler
    @RabbitListener(queues = RabbitConfig.SIMPLE_QUEUE)
    public void receive(String data) {
        logger.info("Consumer[1] receive data  : {}", data);
    }

    @RabbitHandler
    @RabbitListener(queues = RabbitConfig.SIMPLE_QUEUE)
    public void receive2(String data) {
        logger.info("Consumer[2] receive data  : {}", data);
    }
}
