package com.banco.clienteservice.infrastructure.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // Este bean reemplaza el RabbitTemplate por defecto
    @Bean
    public org.springframework.amqp.rabbit.core.RabbitTemplate rabbitTemplate(
            org.springframework.amqp.rabbit.connection.ConnectionFactory connectionFactory) {
        org.springframework.amqp.rabbit.core.RabbitTemplate template = new org.springframework.amqp.rabbit.core.RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }

    @Bean
    public org.springframework.amqp.core.Queue clienteCreadoQueue() {
        return new org.springframework.amqp.core.Queue("cliente.creado", false);
    }
}