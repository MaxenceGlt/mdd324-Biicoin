package com.ipiecoles;

import com.ipiecoles.Service.BitcoinService;

public class Application {

    public static void main(String[] args) {
        BitcoinService bitcoinService = new BitcoinService();
        System.out.println(bitcoinService.getPriceForCurrency(3, "EUR,USD"));
    }

}
