package com.first.microservices.currency_exchange_service.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE = "currency-exchange";
    public static final String ROUTING_KEY = "currency.conversion";
    public static final String QUEUE = "currency-conversion-queue";

    @Bean
    public Queue queue() {
    	return new Queue(EXCHANGE, false);
    }
    
    @Bean
    public DirectExchange exchange() {
    	return new DirectExchange(EXCHANGE);
    }
    
    @Bean
    public Binding binding(Queue queue, DirectExchange directExchange) {
    	return BindingBuilder.bind(queue).to(directExchange).with(ROUTING_KEY);
    }
    }