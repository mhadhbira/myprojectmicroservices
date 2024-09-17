package com.first.microservices.currency_exchange_service.ressource;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Environment;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.first.microservices.currency_exchange_service.entities.ExchangeValue;
import com.first.microservices.currency_exchange_service.resources.CurrencyExchangeController;
import com.first.microservices.currency_exchange_service.service.CurrencyExchangeProducer;
import com.first.microservices.currency_exchange_service.service.CurrencyExchangeService;

import java.math.BigDecimal;

@WebMvcTest(CurrencyExchangeController.class)  // Charge uniquement le contrôleur pour les tests
public class CurrencyExchangeControllerTest {

    @Autowired
    private MockMvc mockMvc;  // Utilisé pour simuler les requêtes HTTP

    @MockBean
    private CurrencyExchangeService currencyExchangeService;  // Mock du service
    
    @MockBean
    private CurrencyExchangeProducer producer;
    
    @Test
    void testRetrieveExchangeValue() throws Exception {
        // Préparer les données mockées
        ExchangeValue mockExchangeValue = new ExchangeValue(1000L, "USD", "INR", BigDecimal.valueOf(74.85), 0);
        
        when(currencyExchangeService.retrieveExchangeValue("USD", "INR")).thenReturn(mockExchangeValue);

        // Simuler la requête HTTP GET et vérifier les réponses
        mockMvc.perform(MockMvcRequestBuilders.get("/currency-exchange/from/USD/to/INR"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.from").value("USD"))
                .andExpect(jsonPath("$.to").value("INR"))
                .andExpect(jsonPath("$.conversionMultiple").value(74.85))
                .andExpect(jsonPath("$.port").isNumber());

        // Vérifier que le mock a été appelé
        verify(currencyExchangeService, times(1)).retrieveExchangeValue("USD", "INR");
    }
}
