package com.br.cpfbackend.component;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RabbitMQProducerTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private RabbitMQProducer producer;

    @Test
    public void testSendMessage() {
        Object message = new Object();
        String exchange = "testExchange";
        String routingKey = "testRoutingKey";
        producer.sendMessage(message, exchange, routingKey);
        verify(rabbitTemplate, times(1)).convertSendAndReceive(exchange, routingKey, message);
    }

    @Test
    public void testSendMessageAsync() {
        Object message = new Object();
        String exchange = "testExchange";
        String routingKey = "testRoutingKey";
        producer.sendMessageAsync(message, exchange, routingKey);
        verify(rabbitTemplate, times(1)).convertAndSend(exchange, routingKey, message);
    }
}
