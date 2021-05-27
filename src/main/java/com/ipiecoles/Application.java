package com.ipiecoles;

import com.ipiecoles.Service.ApiService;
import com.ipiecoles.Service.BitcoinService;
import com.ipiecoles.Service.MeteoService;

public class Application {

    public static void main(String[] args) {
        ApiService apiService = new ApiService();
        BitcoinService bitcoinService = new BitcoinService(apiService);
        MeteoService meteoService = new MeteoService(apiService);
        System.out.println(bitcoinService.getPriceForCurrency(3, "EUR,USD"));
        System.out.println(meteoService.getMeteoCurrency("Paris"));
    }

}
