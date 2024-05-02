package com.br.cpfbackend.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.TimeZone;

@Configuration
public class RabbitMQConfig {

    private final ConnectionFactory connectionFactory;

    public static final String CPF_EXCHANGE = "cpf-exchange";
    public static final String REPORT_EXCHANGE = "report-exchange";

    public static final String NEW_CPF_QUEUE = "new-cpf-queue";
    public static final String DUPLICATED_CPF_QUEUE = "duplicated-cpf-queue";
    public static final String GENERATE_REPORT_QUEUE = "generate-report-queue";

    public static final String NEW_CPF_ROUTING_KEY = "new-cpf";
    public static final String DUPLICATED_CPF_ROUTING_KEY = "duplicated-cpf";
    public static final String GENERATE_REPORT_ROUTING_KEY = "generate-report";

    public RabbitMQConfig(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Bean
    public DirectExchange cpfExchange() {
        return new DirectExchange(CPF_EXCHANGE);
    }

    @Bean
    public DirectExchange reportExchange() {
        return new DirectExchange(REPORT_EXCHANGE);
    }

    @Bean
    public Queue newCpfQueue() {
        return new Queue(NEW_CPF_QUEUE, true);
    }

    @Bean
    public Queue duplicatedCpfQueue() {
        return new Queue(DUPLICATED_CPF_QUEUE, true);
    }


    @Bean
    public Queue generateReportQueue() {
        return new Queue(GENERATE_REPORT_QUEUE, true);
    }

    @Bean
    public Binding cpfBinding() {
        return BindingBuilder.bind(newCpfQueue()).to(cpfExchange()).with(NEW_CPF_ROUTING_KEY);
    }

    @Bean
    public Binding duplicatedCpfBinding() {
        return BindingBuilder.bind(duplicatedCpfQueue()).to(cpfExchange()).with(DUPLICATED_CPF_ROUTING_KEY);
    }

    @Bean
    public Binding generateReportBinding() {
        return BindingBuilder.bind(generateReportQueue()).to(reportExchange()).with(GENERATE_REPORT_ROUTING_KEY);
    }

    @Bean
    public RabbitAdmin rabbitAdmin() {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.declareQueue(newCpfQueue());
        rabbitAdmin.declareQueue(duplicatedCpfQueue());
        rabbitAdmin.declareQueue(generateReportQueue());
        return rabbitAdmin;
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory,
                                                                               Jackson2JsonMessageConverter messageConverter) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(messageConverter);
        factory.setDefaultRequeueRejected(false);
        return factory;
    }

}