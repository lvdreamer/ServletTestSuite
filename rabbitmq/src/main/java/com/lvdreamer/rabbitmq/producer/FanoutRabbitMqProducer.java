package com.lvdreamer.rabbitmq.producer;

import com.lvdreamer.rabbitmq.RabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public abstract class FanoutRabbitMqProducer {
    private final static Logger logger = LoggerFactory.getLogger(FanoutRabbitMqProducer.class);

    @Lookup
    public abstract RabbitTemplate rabbitTemplate();

    public void send() {
        RabbitTemplate rabbitTemplate = rabbitTemplate();
        rabbitTemplate.setConfirmCallback((correlation, ack, reason) -> {
            logger.info("消息发送确认状态:correlationData({}),ack({}),cause({})", correlation, ack, reason);
        });
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            logger.info("消息被退回:message({}),replyCode({}),replyText({}),exchange({}),routingKey({})", message, replyCode, replyText, exchange, routingKey);
        });
        rabbitTemplate.convertAndSend(RabbitConfig.FANOUT_EXCHANGE, "", LocalTime.now());
    }
}
