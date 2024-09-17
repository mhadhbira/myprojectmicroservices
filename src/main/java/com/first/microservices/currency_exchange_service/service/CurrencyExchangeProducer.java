package com.first.microservices.currency_exchange_service.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.first.microservices.currency_exchange_service.config.RabbitMQConfig;

@Service
public class CurrencyExchangeProducer {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void sendMessage(Object message) {
		
		rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, message);
	}

}
