package com.lvdreamer.rabbitmq.controller;

import com.lvdreamer.rabbitmq.producer.FanoutRabbitMqProducer;
import com.lvdreamer.rabbitmq.producer.SimpleRabbitMqProducer;
import com.lvdreamer.rabbitmq.producer.TopicRabbitMqProducer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/rabbitmq")
public class SendController {
    @Resource
    private SimpleRabbitMqProducer simpleRabbitMqProducer;
    @Resource
    private FanoutRabbitMqProducer fanoutRabbitMqProducer;
    @Resource
    private TopicRabbitMqProducer topicRabbitMqProducer;

    @RequestMapping("/simple-send")
    public String useSimpleProducer() {
        simpleRabbitMqProducer.send();
        return "success";
    }

    @RequestMapping("/fanout-send")
    public String useFanoutProducer() {
        fanoutRabbitMqProducer.send();
        return "success";
    }

    @RequestMapping("/manualack-send")
    public String sendManualAck() {
        simpleRabbitMqProducer.sendToManualEx();
        return "success";
    }

    @RequestMapping("/topic-send")
    public String sendTopic() {
        topicRabbitMqProducer.send();
        return "success";
    }
}
