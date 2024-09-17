package com.first.microservices.currency_exchange_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.first.microservices.currency_exchange_service.entities.ExchangeValue;
import com.first.microservices.currency_exchange_service.repositories.ExchangeValueRepository;

@Service
public class CurrencyExchangeService {
	
	@Autowired
	private ExchangeValueRepository exchangeValueRepository;
	
	public ExchangeValue retrieveExchangeValue(String from, String to) {
		return exchangeValueRepository.findByFromAndTo(from, to);
	}

}
