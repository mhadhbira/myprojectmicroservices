package com.first.microservices.currency_exchange_service.resources;
import java.math.BigDecimal;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.boot.autoconfigure.SpringBootApplication;  
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;  
import org.springframework.web.bind.annotation.PathVariable;  
import org.springframework.web.bind.annotation.RestController;

import com.first.microservices.currency_exchange_service.entities.ExchangeValue;
import com.first.microservices.currency_exchange_service.repositories.ExchangeValueRepository;
import com.first.microservices.currency_exchange_service.service.CurrencyExchangeProducer;
import com.first.microservices.currency_exchange_service.service.CurrencyExchangeService;  
@SpringBootApplication  
@RestController   
public class CurrencyExchangeController   
{  
@Autowired  
private Environment environment;  

@Autowired
private CurrencyExchangeService currencyExchangeService;

@Autowired
private CurrencyExchangeProducer producer;

@GetMapping("/currency-exchange/from/{from}/to/{to}") //where {from} and {to} are path variable  
public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to)  //from map to USD and to map to INR  
{     
    //taking the exchange value  
    //ExchangeValue exchangeValue= new ExchangeValue (1000L, from, to, BigDecimal.valueOf(65));
	ExchangeValue exchangeValue=currencyExchangeService.retrieveExchangeValue(from, to);
    //picking port from the environment 
	// Récupérer la propriété et définir une valeur par défaut si null
    String portString = environment.getProperty("local.server.port");
    int port = (portString != null) ? Integer.parseInt(portString) : 0;  // 0 ou une autre valeur par défaut

    exchangeValue.setPort(port);  
    return exchangeValue;  
} 

@GetMapping("/currency-exchange-rabbitmq/from/{from}/to/{to}") //where {from} and {to} are path variable  
public String retrieveExchangeValueFromQueue(@PathVariable String from, @PathVariable String to)  //from map to USD and to map to INR  
{     
	// Logic to get exchange value   
    String message = "Exchange from " + from + " to " + to;
    producer.sendMessage(message);
    return "Message sent: " + message; 
} 
}  