package com.lvdreamer.rabbitmq;

import com.lvdreamer.rabbitmq.producer.SimpleRabbitMqProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleRabbitMqProducerTest {

    @Resource
    private SimpleRabbitMqProducer simpleRabbitMqProducer;
    @Test
    public void send() {
        simpleRabbitMqProducer.send();
    }
}