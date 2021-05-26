package com.ipiecoles.Handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.ipiecoles.Model.Bitcoin;
import com.ipiecoles.Service.ApiService;
import com.ipiecoles.Service.BitcoinService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
public class Handler implements RequestHandler<BitcoinInput, GatewayResponse> {
    @Override
    public GatewayResponse handleRequest(BitcoinInput bitcoinInput, Context context) {
        ApiService apiService = new ApiService();
        BitcoinService bitcoinService = new BitcoinService(apiService);
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Access-Control-Allow-Origin", "https://pjvilloud.github.io");
        String result = "";
        try {
            result = bitcoinService.getPriceForCurrency(bitcoinInput.getBitcoinAmount(),bitcoinInput.getCurrencyList());

        } catch (IOException e) {
            //Gestion d'erreur
            return new GatewayResponse("{\"error\":\"Problème lors de la récupération de la citation du jour\")", headers, 500);
        }
        String body = new Gson().toJson(result);

        return new GatewayResponse(body, headers, 200);
    }
}
