package com.lvdreamer.rabbitmq.consumer;

import com.lvdreamer.rabbitmq.RabbitConfig;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TopicRabbitMqConsumer {
    private final static Logger logger = LoggerFactory.getLogger(TopicRabbitMqConsumer.class);

    @RabbitHandler
    @RabbitListener(containerFactory = "manualAckRabbitListenerContainerFactory", bindings = @QueueBinding(
            value = @Queue(value = RabbitConfig.TOPIC_QUEUE_ONE,
                    durable = "true"),
            exchange = @Exchange(value = RabbitConfig.TOPIC_EXCHANGE,
                    ignoreDeclarationExceptions = "true", type = ExchangeTypes.TOPIC), key = "*.one"))
    public void receive(String data, Message message, Channel channel) {
        logger.info("Consumer[1] receive data  : {}", data);
        logger.debug("Consumer[1] message data  : {}", message);
        try {
            //休眠3秒钟观察状态
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            logger.error("ack确认异常", e);
        }
    }

    @RabbitHandler
    @RabbitListener(containerFactory = "manualAckRabbitListenerContainerFactory", bindings = @QueueBinding(
            value = @Queue(value = RabbitConfig.TOPIC_QUEUE_ONE,
                    durable = "true"),
            exchange = @Exchange(value = RabbitConfig.TOPIC_EXCHANGE,
                    ignoreDeclarationExceptions = "true", type = ExchangeTypes.TOPIC), key = "*.one"))
    public void receive2(String data, Message message, Channel channel) {
        logger.info("Consumer[2] receive data  : {}", data);
        logger.debug("Consumer[2] message data  : {}", message);
        try {
            //休眠3秒钟观察状态
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            logger.error("ack确认异常", e);
        }
    }

    @RabbitHandler
    @RabbitListener(containerFactory = "manualAckRabbitListenerContainerFactory", bindings = @QueueBinding(
            value = @Queue(value = RabbitConfig.TOPIC_QUEUE_TWO,
                    durable = "true"),
            exchange = @Exchange(value = RabbitConfig.TOPIC_EXCHANGE,
                    ignoreDeclarationExceptions = "true", type = ExchangeTypes.TOPIC), key = "*.one"))
    public void receive3(String data, Message message, Channel channel) {
        logger.info("Consumer[3] receive data  : {}", data);
        logger.debug("Consumer[3] message data  : {}", message);
        try {
            //休眠3秒钟观察状态
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            logger.error("ack确认异常", e);
        }
    }
}
