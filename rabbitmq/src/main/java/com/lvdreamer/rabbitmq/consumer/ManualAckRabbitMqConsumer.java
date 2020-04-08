package com.lvdreamer.rabbitmq.consumer;

import com.lvdreamer.rabbitmq.RabbitConfig;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class ManualAckRabbitMqConsumer {
    private final static Logger logger = LoggerFactory.getLogger(ManualAckRabbitMqConsumer.class);

    @RabbitHandler
    @RabbitListener(containerFactory = "manualAckRabbitListenerContainerFactory", bindings = @QueueBinding(
            value = @Queue(value = RabbitConfig.MANUALACK_QUEUE,
                    durable = "true"),
            exchange = @Exchange(value = RabbitConfig.MANUALACK_EXCHANGE,
                    ignoreDeclarationExceptions = "true")))
    public void receive(String data, Message message, Channel channel) {
        logger.info("Consumer[1] receive data  : {}", data);
        logger.info("Consumer[1] message data  : {}", message);
        try {
            //休眠3秒钟观察状态
            Thread.sleep(3000L);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            logger.error("ack确认异常", e);
        } catch (InterruptedException e) {
            logger.error("ack确认异常", e);
        }
    }

}
