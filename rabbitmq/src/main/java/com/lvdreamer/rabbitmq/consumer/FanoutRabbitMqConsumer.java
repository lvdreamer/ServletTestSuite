package com.lvdreamer.rabbitmq.consumer;

import com.lvdreamer.rabbitmq.RabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutRabbitMqConsumer {
    private final static Logger logger = LoggerFactory.getLogger(FanoutRabbitMqConsumer.class);

    @RabbitHandler
    @RabbitListener(queues = RabbitConfig.FANOUT_QUEUE_ONE)
    public void receive(String data) {
        logger.info("Fanout Consumer[1] receive data  : {}", data);
    }

    @RabbitHandler
    @RabbitListener(queues = RabbitConfig.FANOUT_QUEUE_TWO)
    public void receive2(String data) {
        logger.info("Fanout Consumer[2-1] receive data  : {}", data);
    }

    @RabbitHandler
    @RabbitListener(queues = RabbitConfig.FANOUT_QUEUE_TWO)
    public void receive3(String data) {
        logger.info("Fanout Consumer[2-2] receive data  : {}", data);
    }
}
