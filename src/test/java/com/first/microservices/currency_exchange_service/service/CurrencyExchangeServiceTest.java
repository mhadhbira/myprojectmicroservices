package com.first.microservices.currency_exchange_service.service;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.first.microservices.currency_exchange_service.entities.ExchangeValue;
import com.first.microservices.currency_exchange_service.repositories.ExchangeValueRepository;

import java.math.BigDecimal;

public class CurrencyExchangeServiceTest {

    @Mock
    private ExchangeValueRepository exchangeValueRepository;  // Mock du repository

    @InjectMocks
    private CurrencyExchangeService currencyExchangeService;  // Service à tester

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialiser les mocks
    }

    @Test
    void testRetrieveExchangeValue() {
        // Préparer les données mockées
        ExchangeValue mockExchangeValue = new ExchangeValue(1000L, "USD", "INR", BigDecimal.valueOf(74.85), 0);
        when(exchangeValueRepository.findByFromAndTo("USD", "INR")).thenReturn(mockExchangeValue);

        // Appeler la méthode à tester
        ExchangeValue result = currencyExchangeService.retrieveExchangeValue("USD", "INR");

        // Assertions
        assertNotNull(result);
        assertEquals("USD", result.getFrom());
        assertEquals("INR", result.getTo());
        assertEquals(BigDecimal.valueOf(74.85), result.getConversionMultiple());

        // Vérifier que le mock a été appelé
        verify(exchangeValueRepository, times(1)).findByFromAndTo("USD", "INR");
    }
}
