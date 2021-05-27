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
        Mockito.when(apiService.getHttpRequest(Mockito.anyString())).thenReturn("{\"EUR\":30000.00}");
        // When
        String result = new BitcoinService(apiService).getPriceForCurrency(3, "EUR");
        // Then
        Assertions.assertThat(result).isEqualTo("{\"bitcoinAmount\":3.0,\"currenciesEquivalent\":{\"EUR\":90000.0}}");
    }

    @Test
    void getPriceForCurrencyWithUnknowDevice() {
        //Given + When
        String result = new BitcoinService(apiService).getPriceForCurrency(3, "AZE");

        //Then
        Assertions.assertThat(result).isEqualTo("{\"error\":\"La devise n\\u0027est pas reconnue\"}"); //problème du retour bizarre dans le JSON ("n\u0027est")
    }

    @Test
    void getPriceForCurrencyWithInnaccessibleAPI() {
        //Given
        Mockito.when(apiService.getHttpRequest(Mockito.anyString())).thenReturn("{\"error\":\"Le service de Bitcoin est indisponible\"}");

        //When
        String result = new BitcoinService(apiService).getPriceForCurrency(3, "EUR");

        //Then
        Assertions.assertThat(result).isEqualTo("{\"error\":\"Le service de Bitcoin est indisponible\"}");
    }

}