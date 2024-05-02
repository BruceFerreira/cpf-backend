package com.br.cpfbackend.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitMQProducer {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public Object sendMessage(Object message, String exchange, String routingKey) {
        try {
            log.info("Sending message to RabbitMQ. Exchange: {}, Routing Key: {}, Payload: {}", exchange, routingKey, message);
            Object receive = rabbitTemplate.convertSendAndReceive(exchange, routingKey, message);
            log.info("Received response from RabbitMQ: {}", receive);
            return receive;
        } catch (Exception e) {
            log.error("An error occurred while sending message to RabbitMQ: {}", e.getMessage(), e);
            throw new RuntimeException("An error occurred while sending message to RabbitMQ.", e);
        }
    }
    public void sendMessageAsync(Object object, String exchange, String routingKey) {
        try {
            log.info("Sending message asynchronously to RabbitMQ. Exchange: {}, Routing Key: {}, Payload: {}", exchange, routingKey, object);
            rabbitTemplate.convertAndSend(exchange, routingKey, object);
            log.info("Message sent successfully to RabbitMQ");
        } catch (Exception e) {
            log.error("Error sending message to RabbitMQ: {}", e.getMessage(), e);
        }
    }
}
