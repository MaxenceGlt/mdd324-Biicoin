package com.ipiecoles;

import com.ipiecoles.Service.ApiService;
import com.ipiecoles.Service.BitcoinService;

public class Application {

    public static void main(String[] args) {
        ApiService apiService = new ApiService();
        BitcoinService bitcoinService = new BitcoinService(apiService);
        System.out.println(bitcoinService.getPriceForCurrency(3, "EUR,USD"));
    }

}
