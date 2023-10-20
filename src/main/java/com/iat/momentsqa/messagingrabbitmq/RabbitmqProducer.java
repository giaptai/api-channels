package com.iat.momentsqa.messagingrabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitmqProducer {
    private final String directExchangeName = "spring-boot-direct_exchange";
    private final String routingkey = "direct_exchange.routingKey";
    static final String routingkey2 = "direct_exchange.routingKey2";

    private RabbitTemplate rabbitTemplate; // auto build and configured by spring boot

    public RabbitmqProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(directExchangeName, routingkey, message);
    }

    public void sendMessage2(String message) {
        rabbitTemplate.convertAndSend(directExchangeName, routingkey2, message);
    }
}
