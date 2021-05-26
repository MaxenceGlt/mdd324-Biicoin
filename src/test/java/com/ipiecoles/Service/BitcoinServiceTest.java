package com.ipiecoles.Service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BitcoinServiceTest {

    @Mock
    private ApiService apiService;

    @Test
    void getPriceForCurrency() {
        // Given
        Mockito.when(apiService.getPriceCurrencyFromApi(Mockito.anyString())).thenReturn("{\"EUR\":30000.00}");
        // When
        String result = new BitcoinService(apiService).getPriceForCurrency(3, "EUR");
        // Then
        Assertions.assertThat(result).isEqualTo("{\"bitcoinAmount\":3.0,\"currenciesEquivalent\":{\"EUR\":90000.0}}");
    }



}